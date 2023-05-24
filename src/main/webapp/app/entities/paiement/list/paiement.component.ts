import { Component, OnInit } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, Data, ParamMap, Router } from '@angular/router';
import { combineLatest, filter, Observable, switchMap, tap } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPaiement } from '../paiement.model';

import { ITEMS_PER_PAGE } from 'app/config/pagination.constants';
import { ASC, DESC, SORT, ITEM_DELETED_EVENT, DEFAULT_SORT_DATA } from 'app/config/navigation.constants';
import { EntityArrayResponseType, PaiementService } from '../service/paiement.service';
import { PaiementDeleteDialogComponent } from '../delete/paiement-delete-dialog.component';
import { ParseLinks } from 'app/core/util/parse-links.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { jsPDF } from 'jspdf';
import 'jspdf-autotable';
import autoTable from 'jspdf-autotable';

@Component({
  selector: 'jhi-paiement',
  templateUrl: './paiement.component.html',
})
export class PaiementComponent implements OnInit {
  paiements?: IPaiement[];
  isLoading = false;

  predicate = 'id';
  ascending = true;

  editForm: FormGroup;
  paiementss: IPaiement[] = []; // Assurez-vous d'importer la classe Paiement depuis votre modèle de données

  itemsPerPage = ITEMS_PER_PAGE;
  links: { [key: string]: number } = {
    last: 0,
  };
  page = 1;
  recherche: any;

  constructor(
    protected paiementService: PaiementService,
    protected activatedRoute: ActivatedRoute,
    public router: Router,
    protected parseLinks: ParseLinks,
    protected modalService: NgbModal,
    private formBuilder: FormBuilder
  ) {
    this.editForm = this.formBuilder.group({
      matricule: ['']
    });

  }

  reset(): void {
    this.page = 1;
    this.paiements = [];
    this.load();
  }

  loadPage(page: number): void {
    this.page = page;
    this.load();
  }

  trackId = (_index: number, item: IPaiement): number => this.paiementService.getPaiementIdentifier(item);

  ngOnInit(): void {
   this.load();


    
  }

  delete(paiement: IPaiement): void {
    const modalRef = this.modalService.open(PaiementDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.paiement = paiement;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed
      .pipe(
        filter(reason => reason === ITEM_DELETED_EVENT),
        switchMap(() => this.loadFromBackendWithRouteInformations())
      )
      .subscribe({
        next: (res: EntityArrayResponseType) => {
          this.onResponseSuccess(res);
        },
      });
  }

  load(): void {
    this.loadFromBackendWithRouteInformations().subscribe({
      
      next: (res: EntityArrayResponseType) => {
        console.log('d5al load ')
        this.paiementss = res.body ?? [];
        
        this.onResponseSuccess(res);
      },
    });
   
  }

  navigateToWithComponentValues(): void {
    this.handleNavigation(this.page, this.predicate, this.ascending);
  }

  navigateToPage(page = this.page): void {
    this.handleNavigation(page, this.predicate, this.ascending);
  }

  protected loadFromBackendWithRouteInformations(): Observable<EntityArrayResponseType> {
    return combineLatest([this.activatedRoute.queryParamMap, this.activatedRoute.data]).pipe(
      tap(([params, data]) => this.fillComponentAttributeFromRoute(params, data)),
      switchMap(() => this.queryBackend(this.page, this.predicate, this.ascending))
    );
  }

  protected fillComponentAttributeFromRoute(params: ParamMap, data: Data): void {
    const sort = (params.get(SORT) ?? data[DEFAULT_SORT_DATA]).split(',');
    this.predicate = sort[0];
    this.ascending = sort[1] === ASC;
  }

  protected onResponseSuccess(response: EntityArrayResponseType): void {
    this.fillComponentAttributesFromResponseHeader(response.headers);
    const dataFromBody = this.fillComponentAttributesFromResponseBody(response.body);
    this.paiements = dataFromBody;
  }

  protected fillComponentAttributesFromResponseBody(data: IPaiement[] | null): IPaiement[] {
    const paiementsNew = this.paiements ?? [];
    if (data) {
      for (const d of data) {
        if (paiementsNew.map(op => op.id).indexOf(d.id) === -1) {
          paiementsNew.push(d);
        }
      }
    }
    return paiementsNew;
  }

  protected fillComponentAttributesFromResponseHeader(headers: HttpHeaders): void {
    const linkHeader = headers.get('link');
    if (linkHeader) {
      this.links = this.parseLinks.parse(linkHeader);
    } else {
      this.links = {
        last: 0,
      };
    }
  }

  protected queryBackend(page?: number, predicate?: string, ascending?: boolean): Observable<EntityArrayResponseType> {
    this.isLoading = true;
    const pageToLoad: number = page ?? 1;
    const queryObject = {
      page: pageToLoad - 1,
      size: this.itemsPerPage,
      eagerload: true,
      sort: this.getSortQueryParam(predicate, ascending),
    };
    return this.paiementService.query(queryObject).pipe(tap(() => (this.isLoading = false)));
  }

  protected handleNavigation(page = this.page, predicate?: string, ascending?: boolean): void {
    const queryParamsObj = {
      page,
      size: this.itemsPerPage,
      sort: this.getSortQueryParam(predicate, ascending),
    };

    this.router.navigate(['./'], {
      relativeTo: this.activatedRoute,
      queryParams: queryParamsObj,
    });
  }

  protected getSortQueryParam(predicate = this.predicate, ascending = this.ascending): string[] {
    const ascendingQueryParam = ascending ? ASC : DESC;
    if (predicate === '') {
      return [];
    } else {
      return [predicate + ',' + ascendingQueryParam];
    }
  }
  searchPaymentsByMatricule(): void {
    const searchValue = this.editForm.get('matricule')?.value;
    if (searchValue) {
      this.paiements = this.paiementss.filter(paiement => paiement.matricule?.matricule === searchValue);
    } else {
      this.paiements = this.paiementss;
    }
  }

  calculateTotalAmountPaid(): number {
    let totalAmountPaid = 0;
    if (this.paiements) {
      for (const paiement of this.paiements) {
        totalAmountPaid += paiement.montantPaye?? 0;
      }
    }
    return totalAmountPaid;
  }
  
  paiementsss: IPaiement[]=[]; // Liste des paiements correspondants au matricule recherché
  generatePDF(     ): void {
    const doc = new jsPDF();  
    const tableData: any[] = [];
    const headers = ['ID', 'Mode De Paiement', 'Numero Tranche', 'Type De Paiement', 'Montant Paye','matricule'];
    console.log(headers)
    doc.text('                                              Recu de paiement                                         ',5,10)
 
// Ajouter les données des paiements au tableau de données
    if (this.editForm.get('matricule')?.value !=""){

 
    this.paiementss?.forEach(paiement => {
   if( paiement.matricule?.matricule==this.editForm.get('matricule')?.value){
       
    const rowData = [
            paiement.id.toString(),
            paiement.modeDePaiement,
            paiement.numeroTranche,
            paiement.typeDePaiement,
            paiement.montantPaye,
            paiement.matricule?.matricule];
      tableData.push(rowData);
    }
    });
    console.log(tableData)
  }else{
    this.paiementss?.forEach(paiement => {
          
       const rowData = [
               paiement.id.toString(),
               paiement.modeDePaiement,
               paiement.numeroTranche,
               paiement.typeDePaiement,
               paiement.montantPaye,
               paiement.matricule?.matricule];
         tableData.push(rowData);
       
       });
       console.log(tableData)
  }
    
    autoTable(doc, { // use autoTable function from jspdf-autotable package
      head: [
        ['ID', 'Mode De Paiement', 'Numero Tranche', 'Type De Paiement', 'Montant Paye','matricule']
      ],
      body: tableData,
     
    });   
      // Signature
  const signatureX = 20;
  const signatureY = doc.internal.pageSize.getHeight() - 20;
  doc.text('Signature ..................... ', signatureX, signatureY);

      doc.save('paiements.pdf');
    }
  
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IPaiement, NewPaiement } from '../paiement.model';

export type PartialUpdatePaiement = Partial<IPaiement> & Pick<IPaiement, 'id'>;

export type EntityResponseType = HttpResponse<IPaiement>;
export type EntityArrayResponseType = HttpResponse<IPaiement[]>;

@Injectable({ providedIn: 'root' })
export class PaiementService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/paiements');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(paiement: NewPaiement): Observable<EntityResponseType> {
    return this.http.post<IPaiement>(this.resourceUrl, paiement, { observe: 'response' });
  }

  update(paiement: IPaiement): Observable<EntityResponseType> {
    return this.http.put<IPaiement>(`${this.resourceUrl}/${this.getPaiementIdentifier(paiement)}`, paiement, { observe: 'response' });
  }

  partialUpdate(paiement: PartialUpdatePaiement): Observable<EntityResponseType> {
    return this.http.patch<IPaiement>(`${this.resourceUrl}/${this.getPaiementIdentifier(paiement)}`, paiement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPaiement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPaiement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getPaiementIdentifier(paiement: Pick<IPaiement, 'id'>): number {
    return paiement.id;
  }

  comparePaiement(o1: Pick<IPaiement, 'id'> | null, o2: Pick<IPaiement, 'id'> | null): boolean {
    return o1 && o2 ? this.getPaiementIdentifier(o1) === this.getPaiementIdentifier(o2) : o1 === o2;
  }

  addPaiementToCollectionIfMissing<Type extends Pick<IPaiement, 'id'>>(
    paiementCollection: Type[],
    ...paiementsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const paiements: Type[] = paiementsToCheck.filter(isPresent);
    if (paiements.length > 0) {
      const paiementCollectionIdentifiers = paiementCollection.map(paiementItem => this.getPaiementIdentifier(paiementItem)!);
      const paiementsToAdd = paiements.filter(paiementItem => {
        const paiementIdentifier = this.getPaiementIdentifier(paiementItem);
        if (paiementCollectionIdentifiers.includes(paiementIdentifier)) {
          return false;
        }
        paiementCollectionIdentifiers.push(paiementIdentifier);
        return true;
      });
      return [...paiementsToAdd, ...paiementCollection];
    }
    return paiementCollection;
  }

  searchPayments(matricule: string): Observable<any> {
    // Effectuer la requête HTTP pour récupérer les paiements correspondants à la matricule donnée
    // Assurez-vous d'ajuster l'URL de l'API appropriée selon votre configuration
    return this.http.get<any>(`/api/paiements?matricule=${matricule}`);
  }
}

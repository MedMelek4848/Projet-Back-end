import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { CoursFormService, CoursFormGroup } from './cours-form.service';
import { ICours } from '../cours.model';
import { CoursService } from '../service/cours.service';
import { IProfesseur } from 'app/entities/professeur/professeur.model';
import { ProfesseurService } from 'app/entities/professeur/service/professeur.service';
import { IClasse } from 'app/entities/classe/classe.model';
import { ClasseService } from 'app/entities/classe/service/classe.service';
import { IMatiere } from 'app/entities/matiere/matiere.model';
import { MatiereService } from 'app/entities/matiere/service/matiere.service';

@Component({
  selector: 'jhi-cours-update',
  templateUrl: './cours-update.component.html',
})
export class CoursUpdateComponent implements OnInit {
  isSaving = false;
  cours: ICours | null = null;

  professeursSharedCollection: IProfesseur[] = [];
  classesSharedCollection: IClasse[] = [];
  matieresSharedCollection: IMatiere[] = [];

  editForm: CoursFormGroup = this.coursFormService.createCoursFormGroup();

  constructor(
    protected coursService: CoursService,
    protected coursFormService: CoursFormService,
    protected professeurService: ProfesseurService,
    protected classeService: ClasseService,
    protected matiereService: MatiereService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareProfesseur = (o1: IProfesseur | null, o2: IProfesseur | null): boolean => this.professeurService.compareProfesseur(o1, o2);

  compareClasse = (o1: IClasse | null, o2: IClasse | null): boolean => this.classeService.compareClasse(o1, o2);

  compareMatiere = (o1: IMatiere | null, o2: IMatiere | null): boolean => this.matiereService.compareMatiere(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cours }) => {
      this.cours = cours;
      if (cours) {
        this.updateForm(cours);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cours = this.coursFormService.getCours(this.editForm);
    if (cours.id !== null) {
      this.subscribeToSaveResponse(this.coursService.update(cours));
    } else {
      this.subscribeToSaveResponse(this.coursService.create(cours));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICours>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(cours: ICours): void {
    this.cours = cours;
    this.coursFormService.resetForm(this.editForm, cours);

    this.professeursSharedCollection = this.professeurService.addProfesseurToCollectionIfMissing<IProfesseur>(
      this.professeursSharedCollection,
      cours.enseignerPar
    );
    this.classesSharedCollection = this.classeService.addClasseToCollectionIfMissing<IClasse>(
      this.classesSharedCollection,
      cours.pourLaClasse
    );
    this.matieresSharedCollection = this.matiereService.addMatiereToCollectionIfMissing<IMatiere>(
      this.matieresSharedCollection,
      cours.nomMatiere
    );
  }

  protected loadRelationshipsOptions(): void {
    this.professeurService
      .query()
      .pipe(map((res: HttpResponse<IProfesseur[]>) => res.body ?? []))
      .pipe(
        map((professeurs: IProfesseur[]) =>
          this.professeurService.addProfesseurToCollectionIfMissing<IProfesseur>(professeurs, this.cours?.enseignerPar)
        )
      )
      .subscribe((professeurs: IProfesseur[]) => (this.professeursSharedCollection = professeurs));

    this.classeService
      .query()
      .pipe(map((res: HttpResponse<IClasse[]>) => res.body ?? []))
      .pipe(map((classes: IClasse[]) => this.classeService.addClasseToCollectionIfMissing<IClasse>(classes, this.cours?.pourLaClasse)))
      .subscribe((classes: IClasse[]) => (this.classesSharedCollection = classes));

    this.matiereService
      .query()
      .pipe(map((res: HttpResponse<IMatiere[]>) => res.body ?? []))
      .pipe(map((matieres: IMatiere[]) => this.matiereService.addMatiereToCollectionIfMissing<IMatiere>(matieres, this.cours?.nomMatiere)))
      .subscribe((matieres: IMatiere[]) => (this.matieresSharedCollection = matieres));
  }
}

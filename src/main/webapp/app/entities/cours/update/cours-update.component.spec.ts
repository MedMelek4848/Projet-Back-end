import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CoursFormService } from './cours-form.service';
import { CoursService } from '../service/cours.service';
import { ICours } from '../cours.model';
import { IProfesseur } from 'app/entities/professeur/professeur.model';
import { ProfesseurService } from 'app/entities/professeur/service/professeur.service';
import { IClasse } from 'app/entities/classe/classe.model';
import { ClasseService } from 'app/entities/classe/service/classe.service';
import { IMatiere } from 'app/entities/matiere/matiere.model';
import { MatiereService } from 'app/entities/matiere/service/matiere.service';

import { CoursUpdateComponent } from './cours-update.component';

describe('Cours Management Update Component', () => {
  let comp: CoursUpdateComponent;
  let fixture: ComponentFixture<CoursUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let coursFormService: CoursFormService;
  let coursService: CoursService;
  let professeurService: ProfesseurService;
  let classeService: ClasseService;
  let matiereService: MatiereService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CoursUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(CoursUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CoursUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    coursFormService = TestBed.inject(CoursFormService);
    coursService = TestBed.inject(CoursService);
    professeurService = TestBed.inject(ProfesseurService);
    classeService = TestBed.inject(ClasseService);
    matiereService = TestBed.inject(MatiereService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Professeur query and add missing value', () => {
      const cours: ICours = { id: 456 };
      const enseignerPar: IProfesseur = { id: 8869 };
      cours.enseignerPar = enseignerPar;

      const professeurCollection: IProfesseur[] = [{ id: 1188 }];
      jest.spyOn(professeurService, 'query').mockReturnValue(of(new HttpResponse({ body: professeurCollection })));
      const additionalProfesseurs = [enseignerPar];
      const expectedCollection: IProfesseur[] = [...additionalProfesseurs, ...professeurCollection];
      jest.spyOn(professeurService, 'addProfesseurToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ cours });
      comp.ngOnInit();

      expect(professeurService.query).toHaveBeenCalled();
      expect(professeurService.addProfesseurToCollectionIfMissing).toHaveBeenCalledWith(
        professeurCollection,
        ...additionalProfesseurs.map(expect.objectContaining)
      );
      expect(comp.professeursSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Classe query and add missing value', () => {
      const cours: ICours = { id: 456 };
      const pourLaClasse: IClasse = { id: 9354 };
      cours.pourLaClasse = pourLaClasse;

      const classeCollection: IClasse[] = [{ id: 8641 }];
      jest.spyOn(classeService, 'query').mockReturnValue(of(new HttpResponse({ body: classeCollection })));
      const additionalClasses = [pourLaClasse];
      const expectedCollection: IClasse[] = [...additionalClasses, ...classeCollection];
      jest.spyOn(classeService, 'addClasseToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ cours });
      comp.ngOnInit();

      expect(classeService.query).toHaveBeenCalled();
      expect(classeService.addClasseToCollectionIfMissing).toHaveBeenCalledWith(
        classeCollection,
        ...additionalClasses.map(expect.objectContaining)
      );
      expect(comp.classesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Matiere query and add missing value', () => {
      const cours: ICours = { id: 456 };
      const nomMatiere: IMatiere = { id: 90081 };
      cours.nomMatiere = nomMatiere;

      const matiereCollection: IMatiere[] = [{ id: 76279 }];
      jest.spyOn(matiereService, 'query').mockReturnValue(of(new HttpResponse({ body: matiereCollection })));
      const additionalMatieres = [nomMatiere];
      const expectedCollection: IMatiere[] = [...additionalMatieres, ...matiereCollection];
      jest.spyOn(matiereService, 'addMatiereToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ cours });
      comp.ngOnInit();

      expect(matiereService.query).toHaveBeenCalled();
      expect(matiereService.addMatiereToCollectionIfMissing).toHaveBeenCalledWith(
        matiereCollection,
        ...additionalMatieres.map(expect.objectContaining)
      );
      expect(comp.matieresSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const cours: ICours = { id: 456 };
      const enseignerPar: IProfesseur = { id: 61146 };
      cours.enseignerPar = enseignerPar;
      const pourLaClasse: IClasse = { id: 43868 };
      cours.pourLaClasse = pourLaClasse;
      const nomMatiere: IMatiere = { id: 25455 };
      cours.nomMatiere = nomMatiere;

      activatedRoute.data = of({ cours });
      comp.ngOnInit();

      expect(comp.professeursSharedCollection).toContain(enseignerPar);
      expect(comp.classesSharedCollection).toContain(pourLaClasse);
      expect(comp.matieresSharedCollection).toContain(nomMatiere);
      expect(comp.cours).toEqual(cours);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICours>>();
      const cours = { id: 123 };
      jest.spyOn(coursFormService, 'getCours').mockReturnValue(cours);
      jest.spyOn(coursService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cours });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cours }));
      saveSubject.complete();

      // THEN
      expect(coursFormService.getCours).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(coursService.update).toHaveBeenCalledWith(expect.objectContaining(cours));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICours>>();
      const cours = { id: 123 };
      jest.spyOn(coursFormService, 'getCours').mockReturnValue({ id: null });
      jest.spyOn(coursService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cours: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cours }));
      saveSubject.complete();

      // THEN
      expect(coursFormService.getCours).toHaveBeenCalled();
      expect(coursService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICours>>();
      const cours = { id: 123 };
      jest.spyOn(coursService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cours });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(coursService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareProfesseur', () => {
      it('Should forward to professeurService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(professeurService, 'compareProfesseur');
        comp.compareProfesseur(entity, entity2);
        expect(professeurService.compareProfesseur).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareClasse', () => {
      it('Should forward to classeService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(classeService, 'compareClasse');
        comp.compareClasse(entity, entity2);
        expect(classeService.compareClasse).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareMatiere', () => {
      it('Should forward to matiereService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(matiereService, 'compareMatiere');
        comp.compareMatiere(entity, entity2);
        expect(matiereService.compareMatiere).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});

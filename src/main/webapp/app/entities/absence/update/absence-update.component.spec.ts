import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { AbsenceFormService } from './absence-form.service';
import { AbsenceService } from '../service/absence.service';
import { IAbsence } from '../absence.model';
import { IEleve } from 'app/entities/eleve/eleve.model';
import { EleveService } from 'app/entities/eleve/service/eleve.service';
import { IMatiere } from 'app/entities/matiere/matiere.model';
import { MatiereService } from 'app/entities/matiere/service/matiere.service';

import { AbsenceUpdateComponent } from './absence-update.component';

describe('Absence Management Update Component', () => {
  let comp: AbsenceUpdateComponent;
  let fixture: ComponentFixture<AbsenceUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let absenceFormService: AbsenceFormService;
  let absenceService: AbsenceService;
  let eleveService: EleveService;
  let matiereService: MatiereService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [AbsenceUpdateComponent],
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
      .overrideTemplate(AbsenceUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AbsenceUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    absenceFormService = TestBed.inject(AbsenceFormService);
    absenceService = TestBed.inject(AbsenceService);
    eleveService = TestBed.inject(EleveService);
    matiereService = TestBed.inject(MatiereService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Eleve query and add missing value', () => {
      const absence: IAbsence = { id: 456 };
      const matriculeEleve: IEleve = { id: 19243 };
      absence.matriculeEleve = matriculeEleve;

      const eleveCollection: IEleve[] = [{ id: 86130 }];
      jest.spyOn(eleveService, 'query').mockReturnValue(of(new HttpResponse({ body: eleveCollection })));
      const additionalEleves = [matriculeEleve];
      const expectedCollection: IEleve[] = [...additionalEleves, ...eleveCollection];
      jest.spyOn(eleveService, 'addEleveToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ absence });
      comp.ngOnInit();

      expect(eleveService.query).toHaveBeenCalled();
      expect(eleveService.addEleveToCollectionIfMissing).toHaveBeenCalledWith(
        eleveCollection,
        ...additionalEleves.map(expect.objectContaining)
      );
      expect(comp.elevesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Matiere query and add missing value', () => {
      const absence: IAbsence = { id: 456 };
      const nomMatiere: IMatiere = { id: 78842 };
      absence.nomMatiere = nomMatiere;

      const matiereCollection: IMatiere[] = [{ id: 89143 }];
      jest.spyOn(matiereService, 'query').mockReturnValue(of(new HttpResponse({ body: matiereCollection })));
      const additionalMatieres = [nomMatiere];
      const expectedCollection: IMatiere[] = [...additionalMatieres, ...matiereCollection];
      jest.spyOn(matiereService, 'addMatiereToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ absence });
      comp.ngOnInit();

      expect(matiereService.query).toHaveBeenCalled();
      expect(matiereService.addMatiereToCollectionIfMissing).toHaveBeenCalledWith(
        matiereCollection,
        ...additionalMatieres.map(expect.objectContaining)
      );
      expect(comp.matieresSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const absence: IAbsence = { id: 456 };
      const matriculeEleve: IEleve = { id: 92249 };
      absence.matriculeEleve = matriculeEleve;
      const nomMatiere: IMatiere = { id: 66153 };
      absence.nomMatiere = nomMatiere;

      activatedRoute.data = of({ absence });
      comp.ngOnInit();

      expect(comp.elevesSharedCollection).toContain(matriculeEleve);
      expect(comp.matieresSharedCollection).toContain(nomMatiere);
      expect(comp.absence).toEqual(absence);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAbsence>>();
      const absence = { id: 123 };
      jest.spyOn(absenceFormService, 'getAbsence').mockReturnValue(absence);
      jest.spyOn(absenceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ absence });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: absence }));
      saveSubject.complete();

      // THEN
      expect(absenceFormService.getAbsence).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(absenceService.update).toHaveBeenCalledWith(expect.objectContaining(absence));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAbsence>>();
      const absence = { id: 123 };
      jest.spyOn(absenceFormService, 'getAbsence').mockReturnValue({ id: null });
      jest.spyOn(absenceService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ absence: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: absence }));
      saveSubject.complete();

      // THEN
      expect(absenceFormService.getAbsence).toHaveBeenCalled();
      expect(absenceService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAbsence>>();
      const absence = { id: 123 };
      jest.spyOn(absenceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ absence });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(absenceService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareEleve', () => {
      it('Should forward to eleveService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(eleveService, 'compareEleve');
        comp.compareEleve(entity, entity2);
        expect(eleveService.compareEleve).toHaveBeenCalledWith(entity, entity2);
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

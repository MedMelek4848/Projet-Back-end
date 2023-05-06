import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../eleve.test-samples';

import { EleveFormService } from './eleve-form.service';

describe('Eleve Form Service', () => {
  let service: EleveFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EleveFormService);
  });

  describe('Service methods', () => {
    describe('createEleveFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createEleveFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            matricule: expect.any(Object),
            nom: expect.any(Object),
            prenom: expect.any(Object),
            adress: expect.any(Object),
            ville: expect.any(Object),
            pays: expect.any(Object),
            telephone: expect.any(Object),
            email: expect.any(Object),
            dateDeNaissance: expect.any(Object),
            genre: expect.any(Object),
            typeDePaiement: expect.any(Object),
            appartientA: expect.any(Object),
          })
        );
      });

      it('passing IEleve should create a new form with FormGroup', () => {
        const formGroup = service.createEleveFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            matricule: expect.any(Object),
            nom: expect.any(Object),
            prenom: expect.any(Object),
            adress: expect.any(Object),
            ville: expect.any(Object),
            pays: expect.any(Object),
            telephone: expect.any(Object),
            email: expect.any(Object),
            dateDeNaissance: expect.any(Object),
            genre: expect.any(Object),
            typeDePaiement: expect.any(Object),
            appartientA: expect.any(Object),
          })
        );
      });
    });

    describe('getEleve', () => {
      it('should return NewEleve for default Eleve initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createEleveFormGroup(sampleWithNewData);

        const eleve = service.getEleve(formGroup) as any;

        expect(eleve).toMatchObject(sampleWithNewData);
      });

      it('should return NewEleve for empty Eleve initial value', () => {
        const formGroup = service.createEleveFormGroup();

        const eleve = service.getEleve(formGroup) as any;

        expect(eleve).toMatchObject({});
      });

      it('should return IEleve', () => {
        const formGroup = service.createEleveFormGroup(sampleWithRequiredData);

        const eleve = service.getEleve(formGroup) as any;

        expect(eleve).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IEleve should not enable id FormControl', () => {
        const formGroup = service.createEleveFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewEleve should disable id FormControl', () => {
        const formGroup = service.createEleveFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});

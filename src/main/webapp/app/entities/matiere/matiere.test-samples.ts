import { IMatiere, NewMatiere } from './matiere.model';

export const sampleWithRequiredData: IMatiere = {
  id: 60871,
};

export const sampleWithPartialData: IMatiere = {
  id: 14954,
  nomMatiere: 'Granite',
  nombreExamen: 82611,
};

export const sampleWithFullData: IMatiere = {
  id: 28180,
  nomMatiere: 'Team-oriented frame',
  coefficient: 16483,
  nombreHeures: 68160,
  nombreExamen: 50388,
};

export const sampleWithNewData: NewMatiere = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

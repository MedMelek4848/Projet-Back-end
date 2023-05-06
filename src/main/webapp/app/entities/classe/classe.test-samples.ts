import { IClasse, NewClasse } from './classe.model';

export const sampleWithRequiredData: IClasse = {
  id: 50071,
};

export const sampleWithPartialData: IClasse = {
  id: 64183,
  nombreEleves: 41068,
};

export const sampleWithFullData: IClasse = {
  id: 16949,
  nomClasse: 'grey Metal invoice',
  nombreEleves: 53162,
};

export const sampleWithNewData: NewClasse = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

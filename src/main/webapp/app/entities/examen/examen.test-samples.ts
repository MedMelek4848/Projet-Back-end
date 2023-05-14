import dayjs from 'dayjs/esm';

import { IExamen, NewExamen } from './examen.model';

export const sampleWithRequiredData: IExamen = {
  id: 47763,
};

export const sampleWithPartialData: IExamen = {
  id: 49109,
  valide: false,
  nomExamen: 'repurpose Bedfordshire synergy',
};

export const sampleWithFullData: IExamen = {
  id: 13005,
  numExamen: 8948,
  pourcentage: 12542,
  valide: true,
  dateExamen: dayjs('2023-05-06'),
  duree: 65597,
  nomExamen: 'panel',
};

export const sampleWithNewData: NewExamen = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

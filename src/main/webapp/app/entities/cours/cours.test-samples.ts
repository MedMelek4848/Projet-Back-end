import dayjs from 'dayjs/esm';

import { ICours, NewCours } from './cours.model';

export const sampleWithRequiredData: ICours = {
  id: 60788,
};

export const sampleWithPartialData: ICours = {
  id: 7607,
  numeroCours: 72980,
  dateCour: dayjs('2023-05-09'),
};

export const sampleWithFullData: ICours = {
  id: 66863,
  numeroCours: 22003,
  dateCour: dayjs('2023-05-09'),
};

export const sampleWithNewData: NewCours = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

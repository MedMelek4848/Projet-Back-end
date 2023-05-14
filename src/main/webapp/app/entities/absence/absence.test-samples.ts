import dayjs from 'dayjs/esm';

import { IAbsence, NewAbsence } from './absence.model';

export const sampleWithRequiredData: IAbsence = {
  id: 36911,
};

export const sampleWithPartialData: IAbsence = {
  id: 617,
  dateAbsence: dayjs('2023-05-06'),
  justifie: true,
  nombreAbsence: 14162,
};

export const sampleWithFullData: IAbsence = {
  id: 25983,
  dateAbsence: dayjs('2023-05-06'),
  justifie: true,
  commentaire: 'Sausages',
  nombreAbsence: 60965,
};

export const sampleWithNewData: NewAbsence = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

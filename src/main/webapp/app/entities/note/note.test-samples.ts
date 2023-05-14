import { INote, NewNote } from './note.model';

export const sampleWithRequiredData: INote = {
  id: 79739,
};

export const sampleWithPartialData: INote = {
  id: 19247,
  remarque: 'Liaison Borders',
};

export const sampleWithFullData: INote = {
  id: 65167,
  noteExam: 52340,
  remarque: 'Garden Developer',
};

export const sampleWithNewData: NewNote = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

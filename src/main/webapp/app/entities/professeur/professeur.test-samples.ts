import dayjs from 'dayjs/esm';

import { typeContrat } from 'app/entities/enumerations/type-contrat.model';
import { typeDeGenre } from 'app/entities/enumerations/type-de-genre.model';

import { IProfesseur, NewProfesseur } from './professeur.model';

export const sampleWithRequiredData: IProfesseur = {
  id: 57826,
};

export const sampleWithPartialData: IProfesseur = {
  id: 81481,
  prenom: 'Graphic',
  pays: 'disintermediate',
  telephone: '1-817-299-9734',
  email: 'Emmanuel16@yahoo.com',
  grade: 'web-enabled Burgs e-business',
  spetialite: 'instruction neutral',
  dateContrat: dayjs('2023-05-09'),
};

export const sampleWithFullData: IProfesseur = {
  id: 22620,
  nom: 'structure CSS',
  prenom: 'card Ball Data',
  cin: 'Games Car',
  adress: 'Maine back-end even-keeled',
  ville: 'networks',
  pays: 'encoding',
  telephone: '1-447-261-5546 x206',
  email: 'Stone.Hane@gmail.com',
  grade: 'Rubber AGP',
  spetialite: 'Gourde engineer transmitting',
  typeDeContrat: typeContrat['CDI'],
  dateContrat: dayjs('2023-05-09'),
  genre: typeDeGenre['FEMME'],
};

export const sampleWithNewData: NewProfesseur = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

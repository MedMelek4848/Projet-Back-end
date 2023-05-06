import dayjs from 'dayjs/esm';

import { Egenre } from 'app/entities/enumerations/egenre.model';
import { typeDePaiement } from 'app/entities/enumerations/type-de-paiement.model';

import { IEleve, NewEleve } from './eleve.model';

export const sampleWithRequiredData: IEleve = {
  id: 8062,
};

export const sampleWithPartialData: IEleve = {
  id: 84688,
  ville: 'Home Intelligent Mandatory',
  telephone: '(964) 266-5238 x27741',
  typeDePaiement: typeDePaiement['ESPECE'],
};

export const sampleWithFullData: IEleve = {
  id: 65814,
  matricule: 'Identity extend Shoes',
  nom: 'model',
  prenom: 'Zealand',
  adress: 'circuit withdrawal',
  ville: 'transmit Licensed',
  pays: 'synergies Bosnia Director',
  telephone: '(535) 652-2141 x51937',
  email: 'Stephanie.White@gmail.com',
  dateDeNaissance: dayjs('2023-05-04'),
  genre: Egenre['FEMME'],
  typeDePaiement: typeDePaiement['CHEQUE'],
};

export const sampleWithNewData: NewEleve = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

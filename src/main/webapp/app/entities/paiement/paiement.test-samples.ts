import { modes } from 'app/entities/enumerations/modes.model';
import { payType } from 'app/entities/enumerations/pay-type.model';

import { IPaiement, NewPaiement } from './paiement.model';

export const sampleWithRequiredData: IPaiement = {
  id: 69996,
};

export const sampleWithPartialData: IPaiement = {
  id: 68329,
  modeDePaiement: modes['TRANCHES'],
  numeroTranche: 4235,
  montantPaye: 93107,
};

export const sampleWithFullData: IPaiement = {
  id: 15271,
  modeDePaiement: modes['TRANCHES'],
  numeroTranche: 63302,
  typeDePaiement: payType['VIREMMENT'],
  montantPaye: 88278,
};

export const sampleWithNewData: NewPaiement = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

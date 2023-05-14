import { IEleve } from 'app/entities/eleve/eleve.model';
import { modes } from 'app/entities/enumerations/modes.model';
import { payType } from 'app/entities/enumerations/pay-type.model';

export interface IPaiement {
  id: number;
  modeDePaiement?: modes | null;
  numeroTranche?: number | null;
  typeDePaiement?: payType | null;
  montantPaye?: number | null;
  matricule?: Pick<IEleve, 'id' | 'matricule'> | null;
}

export type NewPaiement = Omit<IPaiement, 'id'> & { id: null };

import dayjs from 'dayjs/esm';
import { IEleve } from 'app/entities/eleve/eleve.model';
import { IMatiere } from 'app/entities/matiere/matiere.model';

export interface IAbsence {
  id: number;
  dateAbsence?: dayjs.Dayjs | null;
  justifie?: boolean | null;
  commentaire?: string | null;
  nombreAbsence?: number | null;
  matriculeEleve?: Pick<IEleve, 'id' | 'matricule'> | null;
  nomMatiere?: Pick<IMatiere, 'id' | 'nomMatiere'> | null;
}

export type NewAbsence = Omit<IAbsence, 'id'> & { id: null };

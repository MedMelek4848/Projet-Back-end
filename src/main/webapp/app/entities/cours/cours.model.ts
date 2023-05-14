import dayjs from 'dayjs/esm';
import { IProfesseur } from 'app/entities/professeur/professeur.model';
import { IClasse } from 'app/entities/classe/classe.model';
import { IMatiere } from 'app/entities/matiere/matiere.model';

export interface ICours {
  id: number;
  numeroCours?: number | null;
  dateCour?: dayjs.Dayjs | null;
  enseignerPar?: Pick<IProfesseur, 'id' | 'cin'> | null;
  pourLaClasse?: Pick<IClasse, 'id' | 'nomClasse'> | null;
  nomMatiere?: Pick<IMatiere, 'id' | 'nomMatiere'> | null;
}

export type NewCours = Omit<ICours, 'id'> & { id: null };

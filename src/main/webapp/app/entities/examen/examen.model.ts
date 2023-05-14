import dayjs from 'dayjs/esm';
import { IMatiere } from 'app/entities/matiere/matiere.model';
import { IClasse } from 'app/entities/classe/classe.model';

export interface IExamen {
  id: number;
  numExamen?: number | null;
  pourcentage?: number | null;
  valide?: boolean | null;
  dateExamen?: dayjs.Dayjs | null;
  duree?: number | null;
  nomExamen?: string | null;
  nomMatiere?: Pick<IMatiere, 'id' | 'nomMatiere'> | null;
  nomClasse?: Pick<IClasse, 'id' | 'nomClasse'> | null;
}

export type NewExamen = Omit<IExamen, 'id'> & { id: null };

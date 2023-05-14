import dayjs from 'dayjs/esm';
import { typeContrat } from 'app/entities/enumerations/type-contrat.model';
import { typeDeGenre } from 'app/entities/enumerations/type-de-genre.model';

export interface IProfesseur {
  id: number;
  nom?: string | null;
  prenom?: string | null;
  cin?: string | null;
  adress?: string | null;
  ville?: string | null;
  pays?: string | null;
  telephone?: string | null;
  email?: string | null;
  grade?: string | null;
  spetialite?: string | null;
  typeDeContrat?: typeContrat | null;
  dateContrat?: dayjs.Dayjs | null;
  genre?: typeDeGenre | null;
}

export type NewProfesseur = Omit<IProfesseur, 'id'> & { id: null };

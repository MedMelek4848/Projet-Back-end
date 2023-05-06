import dayjs from 'dayjs/esm';
import { IClasse } from 'app/entities/classe/classe.model';
import { Egenre } from 'app/entities/enumerations/egenre.model';
import { typeDePaiement } from 'app/entities/enumerations/type-de-paiement.model';

export interface IEleve {
  id: number;
  matricule?: string | null;
  nom?: string | null;
  prenom?: string | null;
  adress?: string | null;
  ville?: string | null;
  pays?: string | null;
  telephone?: string | null;
  email?: string | null;
  dateDeNaissance?: dayjs.Dayjs | null;
  genre?: Egenre | null;
  typeDePaiement?: typeDePaiement | null;
  appartientA?: Pick<IClasse, 'id' | 'nomClasse'> | null;
}

export type NewEleve = Omit<IEleve, 'id'> & { id: null };

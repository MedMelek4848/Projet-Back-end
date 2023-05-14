import { IEleve } from 'app/entities/eleve/eleve.model';
import { IExamen } from 'app/entities/examen/examen.model';

export interface INote {
  id: number;
  noteExam?: number | null;
  remarque?: string | null;
  matriculeEleve?: Pick<IEleve, 'id' | 'matricule'> | null;
  nomExamen?: Pick<IExamen, 'id' | 'nomExamen'> | null;
}

export type NewNote = Omit<INote, 'id'> & { id: null };

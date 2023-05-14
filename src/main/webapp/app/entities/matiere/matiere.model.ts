export interface IMatiere {
  id: number;
  nomMatiere?: string | null;
  coefficient?: number | null;
  nombreHeures?: number | null;
  nombreExamen?: number | null;
}

export type NewMatiere = Omit<IMatiere, 'id'> & { id: null };

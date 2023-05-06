export interface IClasse {
  id: number;
  nomClasse?: string | null;
  nombreEleves?: number | null;
}

export type NewClasse = Omit<IClasse, 'id'> & { id: null };

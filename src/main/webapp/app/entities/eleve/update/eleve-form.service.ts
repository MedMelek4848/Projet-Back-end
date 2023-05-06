import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IEleve, NewEleve } from '../eleve.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IEleve for edit and NewEleveFormGroupInput for create.
 */
type EleveFormGroupInput = IEleve | PartialWithRequiredKeyOf<NewEleve>;

type EleveFormDefaults = Pick<NewEleve, 'id'>;

type EleveFormGroupContent = {
  id: FormControl<IEleve['id'] | NewEleve['id']>;
  matricule: FormControl<IEleve['matricule']>;
  nom: FormControl<IEleve['nom']>;
  prenom: FormControl<IEleve['prenom']>;
  adress: FormControl<IEleve['adress']>;
  ville: FormControl<IEleve['ville']>;
  pays: FormControl<IEleve['pays']>;
  telephone: FormControl<IEleve['telephone']>;
  email: FormControl<IEleve['email']>;
  dateDeNaissance: FormControl<IEleve['dateDeNaissance']>;
  genre: FormControl<IEleve['genre']>;
  typeDePaiement: FormControl<IEleve['typeDePaiement']>;
  appartientA: FormControl<IEleve['appartientA']>;
};

export type EleveFormGroup = FormGroup<EleveFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class EleveFormService {
  createEleveFormGroup(eleve: EleveFormGroupInput = { id: null }): EleveFormGroup {
    const eleveRawValue = {
      ...this.getFormDefaults(),
      ...eleve,
    };
    return new FormGroup<EleveFormGroupContent>({
      id: new FormControl(
        { value: eleveRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      matricule: new FormControl(eleveRawValue.matricule),
      nom: new FormControl(eleveRawValue.nom),
      prenom: new FormControl(eleveRawValue.prenom),
      adress: new FormControl(eleveRawValue.adress),
      ville: new FormControl(eleveRawValue.ville),
      pays: new FormControl(eleveRawValue.pays),
      telephone: new FormControl(eleveRawValue.telephone),
      email: new FormControl(eleveRawValue.email),
      dateDeNaissance: new FormControl(eleveRawValue.dateDeNaissance),
      genre: new FormControl(eleveRawValue.genre),
      typeDePaiement: new FormControl(eleveRawValue.typeDePaiement),
      appartientA: new FormControl(eleveRawValue.appartientA),
    });
  }

  getEleve(form: EleveFormGroup): IEleve | NewEleve {
    return form.getRawValue() as IEleve | NewEleve;
  }

  resetForm(form: EleveFormGroup, eleve: EleveFormGroupInput): void {
    const eleveRawValue = { ...this.getFormDefaults(), ...eleve };
    form.reset(
      {
        ...eleveRawValue,
        id: { value: eleveRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): EleveFormDefaults {
    return {
      id: null,
    };
  }
}

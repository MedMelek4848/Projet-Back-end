import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IProfesseur, NewProfesseur } from '../professeur.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IProfesseur for edit and NewProfesseurFormGroupInput for create.
 */
type ProfesseurFormGroupInput = IProfesseur | PartialWithRequiredKeyOf<NewProfesseur>;

type ProfesseurFormDefaults = Pick<NewProfesseur, 'id'>;

type ProfesseurFormGroupContent = {
  id: FormControl<IProfesseur['id'] | NewProfesseur['id']>;
  nom: FormControl<IProfesseur['nom']>;
  prenom: FormControl<IProfesseur['prenom']>;
  cin: FormControl<IProfesseur['cin']>;
  adress: FormControl<IProfesseur['adress']>;
  ville: FormControl<IProfesseur['ville']>;
  pays: FormControl<IProfesseur['pays']>;
  telephone: FormControl<IProfesseur['telephone']>;
  email: FormControl<IProfesseur['email']>;
  grade: FormControl<IProfesseur['grade']>;
  spetialite: FormControl<IProfesseur['spetialite']>;
  typeDeContrat: FormControl<IProfesseur['typeDeContrat']>;
  dateContrat: FormControl<IProfesseur['dateContrat']>;
  genre: FormControl<IProfesseur['genre']>;
};

export type ProfesseurFormGroup = FormGroup<ProfesseurFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ProfesseurFormService {
  createProfesseurFormGroup(professeur: ProfesseurFormGroupInput = { id: null }): ProfesseurFormGroup {
    const professeurRawValue = {
      ...this.getFormDefaults(),
      ...professeur,
    };
    return new FormGroup<ProfesseurFormGroupContent>({
      id: new FormControl(
        { value: professeurRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      nom: new FormControl(professeurRawValue.nom),
      prenom: new FormControl(professeurRawValue.prenom),
      cin: new FormControl(professeurRawValue.cin),
      adress: new FormControl(professeurRawValue.adress),
      ville: new FormControl(professeurRawValue.ville),
      pays: new FormControl(professeurRawValue.pays),
      telephone: new FormControl(professeurRawValue.telephone),
      email: new FormControl(professeurRawValue.email),
      grade: new FormControl(professeurRawValue.grade),
      spetialite: new FormControl(professeurRawValue.spetialite),
      typeDeContrat: new FormControl(professeurRawValue.typeDeContrat),
      dateContrat: new FormControl(professeurRawValue.dateContrat),
      genre: new FormControl(professeurRawValue.genre),
    });
  }

  getProfesseur(form: ProfesseurFormGroup): IProfesseur | NewProfesseur {
    return form.getRawValue() as IProfesseur | NewProfesseur;
  }

  resetForm(form: ProfesseurFormGroup, professeur: ProfesseurFormGroupInput): void {
    const professeurRawValue = { ...this.getFormDefaults(), ...professeur };
    form.reset(
      {
        ...professeurRawValue,
        id: { value: professeurRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ProfesseurFormDefaults {
    return {
      id: null,
    };
  }
}

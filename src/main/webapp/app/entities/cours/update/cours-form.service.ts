import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICours, NewCours } from '../cours.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICours for edit and NewCoursFormGroupInput for create.
 */
type CoursFormGroupInput = ICours | PartialWithRequiredKeyOf<NewCours>;

type CoursFormDefaults = Pick<NewCours, 'id'>;

type CoursFormGroupContent = {
  id: FormControl<ICours['id'] | NewCours['id']>;
  numeroCours: FormControl<ICours['numeroCours']>;
  dateCour: FormControl<ICours['dateCour']>;
  enseignerPar: FormControl<ICours['enseignerPar']>;
  pourLaClasse: FormControl<ICours['pourLaClasse']>;
  nomMatiere: FormControl<ICours['nomMatiere']>;
};

export type CoursFormGroup = FormGroup<CoursFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CoursFormService {
  createCoursFormGroup(cours: CoursFormGroupInput = { id: null }): CoursFormGroup {
    const coursRawValue = {
      ...this.getFormDefaults(),
      ...cours,
    };
    return new FormGroup<CoursFormGroupContent>({
      id: new FormControl(
        { value: coursRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      numeroCours: new FormControl(coursRawValue.numeroCours),
      dateCour: new FormControl(coursRawValue.dateCour),
      enseignerPar: new FormControl(coursRawValue.enseignerPar),
      pourLaClasse: new FormControl(coursRawValue.pourLaClasse),
      nomMatiere: new FormControl(coursRawValue.nomMatiere),
    });
  }

  getCours(form: CoursFormGroup): ICours | NewCours {
    return form.getRawValue() as ICours | NewCours;
  }

  resetForm(form: CoursFormGroup, cours: CoursFormGroupInput): void {
    const coursRawValue = { ...this.getFormDefaults(), ...cours };
    form.reset(
      {
        ...coursRawValue,
        id: { value: coursRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CoursFormDefaults {
    return {
      id: null,
    };
  }
}

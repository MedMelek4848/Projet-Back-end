import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IAbsence, NewAbsence } from '../absence.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAbsence for edit and NewAbsenceFormGroupInput for create.
 */
type AbsenceFormGroupInput = IAbsence | PartialWithRequiredKeyOf<NewAbsence>;

type AbsenceFormDefaults = Pick<NewAbsence, 'id' | 'justifie'>;

type AbsenceFormGroupContent = {
  id: FormControl<IAbsence['id'] | NewAbsence['id']>;
  dateAbsence: FormControl<IAbsence['dateAbsence']>;
  justifie: FormControl<IAbsence['justifie']>;
  commentaire: FormControl<IAbsence['commentaire']>;
  nombreAbsence: FormControl<IAbsence['nombreAbsence']>;
  matriculeEleve: FormControl<IAbsence['matriculeEleve']>;
  nomMatiere: FormControl<IAbsence['nomMatiere']>;
};

export type AbsenceFormGroup = FormGroup<AbsenceFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AbsenceFormService {
  createAbsenceFormGroup(absence: AbsenceFormGroupInput = { id: null }): AbsenceFormGroup {
    const absenceRawValue = {
      ...this.getFormDefaults(),
      ...absence,
    };
    return new FormGroup<AbsenceFormGroupContent>({
      id: new FormControl(
        { value: absenceRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      dateAbsence: new FormControl(absenceRawValue.dateAbsence),
      justifie: new FormControl(absenceRawValue.justifie),
      commentaire: new FormControl(absenceRawValue.commentaire),
      nombreAbsence: new FormControl(absenceRawValue.nombreAbsence),
      matriculeEleve: new FormControl(absenceRawValue.matriculeEleve),
      nomMatiere: new FormControl(absenceRawValue.nomMatiere),
    });
  }

  getAbsence(form: AbsenceFormGroup): IAbsence | NewAbsence {
    return form.getRawValue() as IAbsence | NewAbsence;
  }

  resetForm(form: AbsenceFormGroup, absence: AbsenceFormGroupInput): void {
    const absenceRawValue = { ...this.getFormDefaults(), ...absence };
    form.reset(
      {
        ...absenceRawValue,
        id: { value: absenceRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): AbsenceFormDefaults {
    return {
      id: null,
      justifie: false,
    };
  }
}

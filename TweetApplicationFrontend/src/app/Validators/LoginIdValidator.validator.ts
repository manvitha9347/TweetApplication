import { AbstractControl, ValidatorFn } from "@angular/forms";

export function loginIdValidator(loginIds:string[]): ValidatorFn {
    return (control: AbstractControl): { [key: string]: boolean } | null => {
      return loginIds.includes(control.value)?{'loginIdValidator':true}:null
    };

}

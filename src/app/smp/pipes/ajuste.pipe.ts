import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'ajuste'
})
export class AjustePipe implements PipeTransform {

  transform(value: string): string {
    
    
    const result = value.replace(/([A-Z])/g, ' $1');
    return result.charAt(0).toUpperCase() + result.slice(1);
   
  }

}

import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'verificarData'
})
export class VerificarDataPipe implements PipeTransform {

  transform(value: any, coluna:string): any {
    
    
    if (!coluna.toLowerCase().includes('data')){
      
      return value;
    }
    
    let dateValues = value.split('-');
    let s = dateValues[2] + '/' + dateValues[1] + '/' + dateValues[0]
    
    return s ;
  }

}

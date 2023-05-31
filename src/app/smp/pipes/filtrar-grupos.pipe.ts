import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filtrarGrupos'
})
export class FiltrarGruposPipe implements PipeTransform {

  transform(value: Array<any>, filtro: string,atributo:string): any {
    
    
    if (filtro) {
      filtro = filtro.toUpperCase();

      return value.filter(a =>
        a[atributo].toUpperCase().indexOf(filtro) >= 0
      );
    } else {
      return value;
    }
  }

  

}

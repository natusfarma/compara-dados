import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'ordenarTabela'
})
export class OrdenarTabelaPipe implements PipeTransform {

  transform(value: Array<any>, tipo:string,asc:boolean,atributo:string): Array<any> {

    if(atributo == ''){
      return value
    }

    let obj = 'obj1'
    if (tipo === 'totvs') {
      obj = 'obj2'
    }
      return value.sort((a, b) => {
        let valorA = a[obj][atributo];
        let valorB = b[obj][atributo];
        
        if (typeof valorA === 'string' && typeof valorB === 'string' &&
          !isNaN(Number(valorA)) && !isNaN(Number(valorB))) {
          valorA = Number(valorA);
          valorB = Number(valorB);
        }
        if (valorA > valorB) {
          return asc ? -1 : 1;
        } else if (valorA < valorB) {
          return asc ? 1 : -1;
        } else {
          return 0;
        }
      });

  }

}

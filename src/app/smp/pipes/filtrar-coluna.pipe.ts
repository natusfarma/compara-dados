import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filtrarColuna'
})
export class FiltrarColunaPipe implements PipeTransform {

  transform(value: Array<any>, coluna: string, valores: Array<string>): Array<any> {


    if (coluna === '' || valores.length === 0) {
      return value
    }


    return value.filter(item => {
      const valorObj1 = item.obj1?.[coluna]?.toString();
      const valorObj2 = item.obj2?.[coluna]?.toString();

      return valores.some(valor => {
         if (isNaN(parseFloat(valor))) {
          return (valorObj1 && valorObj1.toLowerCase().includes(valor.toLowerCase())) ||
          (valorObj2 && valorObj2.toLowerCase().includes(valor.toLowerCase()))

        } else {
          return (valorObj1 && valorObj1.includes(valor)) ||
            (valorObj2 && valorObj2.includes(valor))
        }
      })
    }

    );
  }

}

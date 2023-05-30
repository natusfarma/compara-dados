package com.natusfarma.pc.itecvstotvs.ordenacao.notafiscal;

import com.natusfarma.pc.itecvstotvs.model.ModeloNotaFiscal;

import java.util.Comparator;

public class OrdenarFilialNumeroSerieEmissao implements Comparator<ModeloNotaFiscal> {


    @Override
    public int compare(ModeloNotaFiscal o1, ModeloNotaFiscal o2) {
        int compararFilial = Integer.compare(o1.getFilial(), o2.getFilial());
        if (compararFilial != 0){
            return compararFilial;
        }
        int compararNumero = Integer.compare(o1.getNumero(), o2.getNumero());
        if (compararNumero != 0){
            return compararNumero;
        }
        int compararSerie = Integer.compare(o1.getSerie(), o2.getSerie());
        if (compararSerie != 0){
            return compararSerie;
        }
        return o1.getDataEmissao().compareTo(o2.getDataEmissao());
    }


}

package com.natusfarma.pc.itecvstotvs.ordenacao;

import com.natusfarma.pc.itecvstotvs.model.ModeloPadrao;

import java.util.Comparator;

public class OrdenarFornTituloParcela implements Comparator<ModeloPadrao> {

    @Override
    public int compare(ModeloPadrao o1, ModeloPadrao o2) {
        int compararFornecedor = Long.compare(o1.getCodFornecedor(), o2.getCodFornecedor());
        if (compararFornecedor != 0){
            return compararFornecedor;
        }
        int compararTitulo = o1.getTitulo().compareTo(o2.getTitulo());
        if (compararTitulo != 0) {
            return compararTitulo;
        }
        return Integer.compare(o1.getParcela(), o2.getParcela());
    }
}

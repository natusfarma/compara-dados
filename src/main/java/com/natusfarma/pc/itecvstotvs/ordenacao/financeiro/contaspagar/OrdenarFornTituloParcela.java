package com.natusfarma.pc.itecvstotvs.ordenacao.financeiro.contaspagar;

import com.natusfarma.pc.itecvstotvs.model.ModeloContasPagar;

import java.util.Comparator;

public class OrdenarFornTituloParcela implements Comparator<ModeloContasPagar> {

    @Override
    public int compare(ModeloContasPagar o1, ModeloContasPagar o2) {
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

package com.natusfarma.pc.itecvstotvs.ordenacao.financeiro.fech_estoque;

import com.natusfarma.pc.itecvstotvs.model.ModeloFechEstoque;
import com.natusfarma.pc.itecvstotvs.model.ModeloNdf;

import java.util.Comparator;

public class OrdenarFilial implements Comparator<ModeloFechEstoque> {

    @Override
    public int compare(ModeloFechEstoque o1, ModeloFechEstoque o2) {
        return Integer.compare(o1.getFilial(), o2.getFilial());
    }
}

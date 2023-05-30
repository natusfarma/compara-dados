package com.natusfarma.pc.itecvstotvs.ordenacao.cadastro;

import com.natusfarma.pc.itecvstotvs.model.ModeloProduto;

public class OrdenaCodigoProduto implements java.util.Comparator<ModeloProduto> {

    @Override
    public int compare(ModeloProduto o1, ModeloProduto o2) {
        return Integer.compare(o1.getCodigo(), o2.getCodigo());
    }

}

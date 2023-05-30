package com.natusfarma.pc.itecvstotvs.ordenacao.cadastro;

import com.natusfarma.pc.itecvstotvs.model.ModeloFornecedor;

import java.util.Comparator;

public class OrdenarCodigoFornecedor implements Comparator<ModeloFornecedor> {


    @Override
    public int compare(ModeloFornecedor o1, ModeloFornecedor o2) {
        return Integer.compare(o1.getCodigo(),o2.getCodigo());
    }
}

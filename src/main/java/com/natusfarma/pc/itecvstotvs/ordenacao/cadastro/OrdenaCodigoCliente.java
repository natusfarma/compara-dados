package com.natusfarma.pc.itecvstotvs.ordenacao.cadastro;

import com.natusfarma.pc.itecvstotvs.model.ModeloClientes;

import java.util.Comparator;

public class OrdenaCodigoCliente implements Comparator<ModeloClientes> {

    @Override
    public int compare(ModeloClientes o1, ModeloClientes o2) {
        return Integer.compare(o1.getCodigo(),o2.getCodigo());
    }
}

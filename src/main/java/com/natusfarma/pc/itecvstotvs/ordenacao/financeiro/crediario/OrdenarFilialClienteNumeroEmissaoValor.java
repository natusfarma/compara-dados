package com.natusfarma.pc.itecvstotvs.ordenacao.financeiro.crediario;

import com.natusfarma.pc.itecvstotvs.model.ModeloCrediario;

import java.util.Comparator;

public class OrdenarFilialClienteNumeroEmissaoValor implements Comparator<ModeloCrediario> {


    @Override
    public int compare(ModeloCrediario o1, ModeloCrediario o2) {
        int compararFilial = Integer.compare(o1.getFilial(), o2.getFilial());
        if (compararFilial != 0){
            return compararFilial;
        }
        int compararCliente = Integer.compare(o1.getCodCliente(), o2.getCodCliente());
        if (compararCliente != 0){
            return compararCliente;
        }
        int compararNumero = Integer.compare(o1.getNumero(), o2.getNumero());
        if (compararNumero != 0){
            return compararNumero;
        }
        int compararEmissao = o1.getDataEmissao().compareTo(o2.getDataEmissao());
        if (compararEmissao != 0){
            return compararEmissao;
        }
        return Double.compare(o1.getValor(), o2.getValor());
    }
}

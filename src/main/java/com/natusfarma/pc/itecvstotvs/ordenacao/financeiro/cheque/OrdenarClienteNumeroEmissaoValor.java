package com.natusfarma.pc.itecvstotvs.ordenacao.financeiro.cheque;

import com.natusfarma.pc.itecvstotvs.model.ModeloCheque;

import java.util.Comparator;

public class OrdenarClienteNumeroEmissaoValor implements Comparator<ModeloCheque> {


    @Override
    public int compare(ModeloCheque o1, ModeloCheque o2) {
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

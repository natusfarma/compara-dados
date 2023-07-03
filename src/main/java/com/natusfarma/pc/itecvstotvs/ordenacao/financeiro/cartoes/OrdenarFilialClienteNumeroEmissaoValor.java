package com.natusfarma.pc.itecvstotvs.ordenacao.financeiro.cartoes;

import com.natusfarma.pc.itecvstotvs.model.ModeloCartoes;
import com.natusfarma.pc.itecvstotvs.model.ModeloCheque;

import java.util.Comparator;

public class OrdenarFilialClienteNumeroEmissaoValor implements Comparator<ModeloCartoes> {


    @Override
    public int compare(ModeloCartoes o1, ModeloCartoes o2) {
        int compararFilial = Integer.compare(o1.getFilial(), o2.getFilial());
        if (compararFilial != 0){
            return compararFilial;
        }
        int compararCliente = Integer.compare(o1.getCodCliente(), o2.getCodCliente());
        if (compararCliente != 0){
            return compararCliente;
        }
        int compararNumero = o1.getNumero().compareTo(o2.getNumero());
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

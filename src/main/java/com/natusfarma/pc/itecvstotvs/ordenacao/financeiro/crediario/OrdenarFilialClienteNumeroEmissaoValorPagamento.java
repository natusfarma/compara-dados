package com.natusfarma.pc.itecvstotvs.ordenacao.financeiro.crediario;

import com.natusfarma.pc.itecvstotvs.model.ModeloCrediarioEmissao;
import com.natusfarma.pc.itecvstotvs.model.ModeloCrediarioPagamento;

import java.util.Comparator;

public class OrdenarFilialClienteNumeroEmissaoValorPagamento implements Comparator<ModeloCrediarioPagamento> {


    @Override
    public int compare(ModeloCrediarioPagamento o1, ModeloCrediarioPagamento o2) {
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
        int compararValor= Double.compare(o1.getValor(), o2.getValor());;
        if (compararValor != 0){
            return compararValor;
        }
        return Double.compare(o1.getValorPago(), o2.getValorPago());
    }
}

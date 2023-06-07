package com.natusfarma.pc.itecvstotvs.ordenacao.financeiro.ndf;

import com.natusfarma.pc.itecvstotvs.model.ModeloNdf;

import java.util.Comparator;

public class OrdenarFornTituloFilialEmissao implements Comparator<ModeloNdf> {

    @Override
    public int compare(ModeloNdf o1, ModeloNdf o2) {
        int compararFornecedor = Long.compare(o1.getCodFornecedor(), o2.getCodFornecedor());
        if (compararFornecedor != 0){
            return compararFornecedor;
        }
        int compararTitulo = o1.getTitulo().compareTo(o2.getTitulo());
        if (compararTitulo != 0) {
            return compararTitulo;
        }
        int compararFilial = Integer.compare(o1.getFilial(), o2.getFilial());
        if (compararFilial != 0) {
            return compararFilial;
        }
        return o1.getDataEmissao().compareTo(o2.getDataEmissao());
    }
}

package com.natusfarma.pc.itecvstotvs.service;

import com.natusfarma.pc.itecvstotvs.componente.CompararDadosTipo;
import com.natusfarma.pc.itecvstotvs.componente.MapClass;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.produto.primario.PriProduto;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.produto.secundario.SecProduto;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.model.ModeloProduto;
import com.natusfarma.pc.itecvstotvs.ordenacao.cadastro.OrdenaCodigoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService extends CompararDadosTipo<ModeloProduto> {

    @Autowired
    private PriProduto bancoPrimarioService;
    @Autowired
    private SecProduto bancoSecundarioService;


    public ProdutoService() {
        setComparator(new OrdenaCodigoProduto());
    }


    public ModeloListas<ModeloProduto> processarPorIds(String[] ids) {
        List<ModeloProduto> listaBancoPrimario = bancoPrimarioService.processarPorIds(ids);
        List<ModeloProduto> listaBancoSecundario = bancoSecundarioService.processarPorIds(ids);
        comparaValor(listaBancoPrimario, listaBancoSecundario);
        return getModeloListas();
    }


    @Override
    public int quantidadeDeColunaDoTipo() {
        return MapClass.totalDeAtributos(this.getClass());
    }



}

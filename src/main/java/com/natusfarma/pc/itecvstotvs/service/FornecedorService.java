package com.natusfarma.pc.itecvstotvs.service;

import com.natusfarma.pc.itecvstotvs.componente.CompararDadosTipo;
import com.natusfarma.pc.itecvstotvs.componente.MapClass;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.fornecedor.primario.PriFornecedor;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.fornecedor.secundario.SecFornecedor;
import com.natusfarma.pc.itecvstotvs.model.ModeloFornecedor;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.ordenacao.cadastro.OrdenarCodigoFornecedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService extends CompararDadosTipo<ModeloFornecedor> {

    @Autowired
    private PriFornecedor bancoPrimarioService;
    @Autowired
    private SecFornecedor bancoSecundarioService;


    public FornecedorService() {
        setComparator(new OrdenarCodigoFornecedor());
    }


    public ModeloListas<ModeloFornecedor> processarPorIds(String[] ids) {
        List<ModeloFornecedor> listaBancoPrimario = bancoPrimarioService.processarPorIds(ids);
        List<ModeloFornecedor> listaBancoSecundario = bancoSecundarioService.processarPorIds(ids);
        comparaValor(listaBancoPrimario, listaBancoSecundario);
        return getModeloListas();
    }


    public ModeloListas<ModeloFornecedor> processarPorCgcCpf(String[] cgcCpf) {
        List<ModeloFornecedor> listaBancoPrimario = bancoPrimarioService.processarPorCgcCpf(cgcCpf);
        List<ModeloFornecedor> listaBancoSecundario = bancoSecundarioService.processarPorCgcCpf(cgcCpf);
        comparaValor(listaBancoPrimario, listaBancoSecundario);
        return getModeloListas();
    }

    @Override
    public int quantidadeDeColunaDoTipo() {
        return MapClass.totalDeAtributos(this.getClass());
    }



}

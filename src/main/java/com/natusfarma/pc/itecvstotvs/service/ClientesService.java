package com.natusfarma.pc.itecvstotvs.service;

import com.natusfarma.pc.itecvstotvs.componente.CompararDadosTipo;
import com.natusfarma.pc.itecvstotvs.componente.MapClass;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.cliente.primario.PriClientes;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.cliente.secundario.SecClientes;
import com.natusfarma.pc.itecvstotvs.model.ModeloClientes;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.ordenacao.cadastro.OrdenaCodigoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesService extends CompararDadosTipo<ModeloClientes> {

    @Autowired
    private PriClientes bancoPrimarioService;
    @Autowired
    private SecClientes bancoSecundarioService;


    public ClientesService() {
        setComparator(new OrdenaCodigoCliente());
    }


    public ModeloListas<ModeloClientes> processarPorIds(String[] ids) {
        List<ModeloClientes> listaBancoPrimario = bancoPrimarioService.processarPorIds(ids);
        List<ModeloClientes> listaBancoSecundario = bancoSecundarioService.processarPorIds(ids);
        comparaValor(listaBancoPrimario, listaBancoSecundario);
        return getModeloListas();
    }


    public ModeloListas<ModeloClientes> processarPorCgcCpf(String[] cgcCpf) {
        List<ModeloClientes> listaBancoPrimario = bancoPrimarioService.processarPorCgcCpf(cgcCpf);
        List<ModeloClientes> listaBancoSecundario = bancoSecundarioService.processarPorCgcCpf(cgcCpf);
        comparaValor(listaBancoPrimario, listaBancoSecundario);
        return getModeloListas();
    }

    @Override
    public int quantidadeDeColunaDoTipo() {
        return MapClass.totalDeAtributos(this.getClass());
    }



}

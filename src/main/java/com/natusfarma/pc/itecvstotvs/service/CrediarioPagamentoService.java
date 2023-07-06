package com.natusfarma.pc.itecvstotvs.service;

import com.natusfarma.pc.itecvstotvs.componente.CompararDadosTipo;
import com.natusfarma.pc.itecvstotvs.componente.MapClass;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.primario.PriCrediarioEmissao;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.primario.PriCrediarioPagamento;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.secundario.SecCrediarioEmissao;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.secundario.SecCrediarioPagamento;
import com.natusfarma.pc.itecvstotvs.model.ModeloCrediarioEmissao;
import com.natusfarma.pc.itecvstotvs.model.ModeloCrediarioPagamento;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.ordenacao.financeiro.crediario.OrdenarFilialClienteNumeroEmissaoValor;
import com.natusfarma.pc.itecvstotvs.ordenacao.financeiro.crediario.OrdenarFilialClienteNumeroEmissaoValorPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CrediarioPagamentoService extends CompararDadosTipo<ModeloCrediarioPagamento> {

    @Autowired
    private PriCrediarioPagamento bancoPrimarioService;
    @Autowired
    private SecCrediarioPagamento bancoSecundarioService;

    public CrediarioPagamentoService() {
        setComparator(new OrdenarFilialClienteNumeroEmissaoValorPagamento());
    }


    public ModeloListas<ModeloCrediarioPagamento> processarDataPagamento(LocalDate inicio, LocalDate fim) {
        dataInicio = inicio;
        dataFinal = fim;
        List<ModeloCrediarioPagamento> listaBancoPrimario = bancoPrimarioService.processarDataPagamento(inicio, fim);
        List<ModeloCrediarioPagamento> listaBancoSecundario = bancoSecundarioService.processarDataPagamento(inicio, fim);
        comparaValor(listaBancoPrimario, listaBancoSecundario);
        return getModeloListas();
    }

    public ModeloListas<ModeloCrediarioPagamento> processarNumerosFiliais(String[] numeros, String[] filiais) {
        List<ModeloCrediarioPagamento> listaBancoPrimario = bancoPrimarioService.processarNumerosFiliais(numeros, filiais);
        List<ModeloCrediarioPagamento> listaBancoSecundario = bancoSecundarioService.processarNumerosFiliais(numeros, filiais);
        comparaValor(listaBancoPrimario, listaBancoSecundario);
        return getModeloListas();
    }

    @Override
    public int quantidadeDeColunaDoTipo() {
        return MapClass.totalDeAtributos(this.getClass());
    }



}

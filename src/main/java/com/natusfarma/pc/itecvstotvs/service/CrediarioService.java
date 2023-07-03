package com.natusfarma.pc.itecvstotvs.service;

import com.natusfarma.pc.itecvstotvs.componente.CompararDadosTipo;
import com.natusfarma.pc.itecvstotvs.componente.MapClass;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.primario.PriCrediario;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.secundario.SecCrediario;
import com.natusfarma.pc.itecvstotvs.model.ModeloCrediario;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.ordenacao.financeiro.crediario.OrdenarFilialClienteNumeroEmissaoValor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CrediarioService extends CompararDadosTipo<ModeloCrediario> {

    @Autowired
    private PriCrediario bancoPrimarioService;
    @Autowired
    private SecCrediario bancoSecundarioService;

    public CrediarioService() {
        setComparator(new OrdenarFilialClienteNumeroEmissaoValor());
    }

    /**
     * Método principal onde realiza a regra de negócio.
     * pega a data de início e fim e carrega a informação de cada banco em listas diferentes.
     * depois é ordenada cada lista para melhor desempenho.
     * @param inicio
     * @param fim
     */
    public ModeloListas<ModeloCrediario> processar(LocalDate inicio, LocalDate fim) {
        dataInicio = inicio;
        dataFinal = fim;
        List<ModeloCrediario> listaBancoPrimario = bancoPrimarioService.processar(inicio, fim);
        List<ModeloCrediario> listaBancoSecundario = bancoSecundarioService.processar(inicio, fim);
        comparaValor(listaBancoPrimario, listaBancoSecundario);
        return getModeloListas();
    }

    public ModeloListas<ModeloCrediario> processarDataPagamento(LocalDate inicio, LocalDate fim) {
        dataInicio = inicio;
        dataFinal = fim;
        List<ModeloCrediario> listaBancoPrimario = bancoPrimarioService.processarDataPagamento(inicio, fim);
        List<ModeloCrediario> listaBancoSecundario = bancoSecundarioService.processarDataPagamento(inicio, fim);
        comparaValor(listaBancoPrimario, listaBancoSecundario);
        return getModeloListas();
    }

    public ModeloListas<ModeloCrediario> processarNumerosFiliais(String[] numeros, String[] filiais) {
        List<ModeloCrediario> listaBancoPrimario = bancoPrimarioService.processarNumerosFiliais(numeros, filiais);
        List<ModeloCrediario> listaBancoSecundario = bancoSecundarioService.processarNumerosFiliais(numeros, filiais);
        comparaValor(listaBancoPrimario, listaBancoSecundario);
        return getModeloListas();
    }

    @Override
    public int quantidadeDeColunaDoTipo() {
        return MapClass.totalDeAtributos(this.getClass());
    }



}

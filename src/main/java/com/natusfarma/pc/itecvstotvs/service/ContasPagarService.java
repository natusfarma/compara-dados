package com.natusfarma.pc.itecvstotvs.service;

import com.natusfarma.pc.itecvstotvs.componente.CompararDadosTipo;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.contaspagar.primario.PriContasPagar;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.contaspagar.secundario.SecContasPagar;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.ordenacao.financeiro.contaspagar.OrdenarFornTituloFilialParcelaVencimento;
import com.natusfarma.pc.itecvstotvs.model.ModeloContasPagar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContasPagarService extends CompararDadosTipo<ModeloContasPagar> {

    @Autowired
    private PriContasPagar bancoPrimarioService;
    @Autowired
    private SecContasPagar bancoSecundarioService;

    public ContasPagarService() {
        setComparator(new OrdenarFornTituloFilialParcelaVencimento());
    }

    /**
     * Método principal onde realiza a regra de negócio.
     * pega a data de início e fim e carrega a informação de cada banco em listas diferentes.
     * depois é ordenada cada lista para melhor desempenho.
     * @param inicio
     * @param fim
     */
    public ModeloListas<ModeloContasPagar> processar(LocalDate inicio, LocalDate fim) {
        dataInicio = inicio;
        dataFinal = fim;
        List<ModeloContasPagar> listaBancoPrimario = bancoPrimarioService.processar(inicio, fim);
        List<ModeloContasPagar> listaBancoSecundario = bancoSecundarioService.processar(inicio, fim);
        comparaValor(listaBancoPrimario, listaBancoSecundario);
        return getModeloListas();
    }

    /**
     * Método principal onde realiza a regra de negócio.
     * pega a data de início e fim e mais a filial e carrega a informação de cada banco em listas diferentes.
     * depois é ordenada cada lista para melhor desempenho.
     * @param filialId
     * @param inicio
     * @param fim
     */
    public ModeloListas<ModeloContasPagar> processarPorId(int filialId, LocalDate inicio, LocalDate fim) {
        dataInicio = inicio;
        dataFinal = fim;
        List<ModeloContasPagar> listaBancoPrimario = bancoPrimarioService.processarPorFilial(filialId, inicio, fim);
        List<ModeloContasPagar> listaBancoSecundario = bancoSecundarioService.processarPorFilial(filialId, inicio, fim);
        comparaValor(listaBancoPrimario, listaBancoSecundario);
        return getModeloListas();
    }

    @Override
    public int quantidadeDeColunaDoTipo() {
        return 8;
    }



}

package com.natusfarma.pc.itecvstotvs.service;

import com.natusfarma.pc.itecvstotvs.componente.CompararDadosTipo;
import com.natusfarma.pc.itecvstotvs.componente.MapClass;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.cheque.primario.PriCheque;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.cheque.secundario.SecCheque;
import com.natusfarma.pc.itecvstotvs.model.ModeloCheque;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.ordenacao.financeiro.cheque.OrdenarClienteNumeroEmissaoValor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ChequeService extends CompararDadosTipo<ModeloCheque> {

    @Autowired
    private PriCheque bancoPrimarioService;
    @Autowired
    private SecCheque bancoSecundarioService;

    public ChequeService() {
        setComparator(new OrdenarClienteNumeroEmissaoValor());
    }

    /**
     * Método principal onde realiza a regra de negócio.
     * pega a data de início e fim e carrega a informação de cada banco em listas diferentes.
     * depois é ordenada cada lista para melhor desempenho.
     * @param inicio
     * @param fim
     */
    public ModeloListas<ModeloCheque> processar(LocalDate inicio, LocalDate fim) {
        dataInicio = inicio;
        dataFinal = fim;
        List<ModeloCheque> listaBancoPrimario = bancoPrimarioService.processar(inicio, fim);
        List<ModeloCheque> listaBancoSecundario = bancoSecundarioService.processar(inicio, fim);
        comparaValor(listaBancoPrimario, listaBancoSecundario);
        return getModeloListas();
    }

    @Override
    public int quantidadeDeColunaDoTipo() {
        return MapClass.totalDeAtributos(this.getClass());
    }



}

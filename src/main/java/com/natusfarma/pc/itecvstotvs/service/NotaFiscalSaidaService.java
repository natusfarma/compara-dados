package com.natusfarma.pc.itecvstotvs.service;

import com.natusfarma.pc.itecvstotvs.componente.CompararDadosTipo;
import com.natusfarma.pc.itecvstotvs.componente.notafiscal.saida.primario.PriNotaFiscalSaida;
import com.natusfarma.pc.itecvstotvs.componente.notafiscal.saida.secundario.SecNotaFiscalSaida;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.model.ModeloNotaFiscal;
import com.natusfarma.pc.itecvstotvs.ordenacao.notafiscal.OrdenarFilialNumeroSerieEmissao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotaFiscalSaidaService extends CompararDadosTipo<ModeloNotaFiscal> {

    @Autowired
    private PriNotaFiscalSaida bancoPrimarioService;
    @Autowired
    private SecNotaFiscalSaida bancoSecundarioService;

    public NotaFiscalSaidaService() {
        setComparator(new OrdenarFilialNumeroSerieEmissao());
    }

    /**
     * Método principal onde realiza a regra de negócio.
     * pega a data de início e fim e carrega a informação de cada banco em listas diferentes.
     * depois é ordenada cada lista para melhor desempenho.
     * @param inicio
     * @param fim
     */
    public ModeloListas<ModeloNotaFiscal> processar(LocalDate inicio, LocalDate fim) {
        dataInicio = inicio;
        dataFinal = fim;
        List<ModeloNotaFiscal> listaBancoPrimario = bancoPrimarioService.processar(inicio, fim);
        List<ModeloNotaFiscal> listaBancoSecundario = bancoSecundarioService.processar(inicio, fim);
        comparaValor(listaBancoPrimario, listaBancoSecundario);
        return getModeloListas();
    }

    @Override
    public int quantidadeDeColunaDoTipo() {
        return 8;
    }



}

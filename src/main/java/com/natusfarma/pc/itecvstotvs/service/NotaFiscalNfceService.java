package com.natusfarma.pc.itecvstotvs.service;

import com.natusfarma.pc.itecvstotvs.componente.CompararDadosTipo;
import com.natusfarma.pc.itecvstotvs.componente.MapClass;
import com.natusfarma.pc.itecvstotvs.componente.notafiscal.nfce.primario.PriNotaFiscalNfce;
import com.natusfarma.pc.itecvstotvs.componente.notafiscal.nfce.secundario.SecNotaFiscalNfce;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.model.ModeloNotaFiscal;
import com.natusfarma.pc.itecvstotvs.ordenacao.notafiscal.OrdenarFilialNumeroSerieEmissao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotaFiscalNfceService extends CompararDadosTipo<ModeloNotaFiscal> {

    @Autowired
    private PriNotaFiscalNfce bancoPrimarioService;
    @Autowired
    private SecNotaFiscalNfce bancoSecundarioService;

    public NotaFiscalNfceService() {
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
        return MapClass.totalDeAtributos(this.getClass());
    }



}

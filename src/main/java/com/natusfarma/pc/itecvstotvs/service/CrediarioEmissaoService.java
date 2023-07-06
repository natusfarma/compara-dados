package com.natusfarma.pc.itecvstotvs.service;

import com.natusfarma.pc.itecvstotvs.componente.CompararDadosTipo;
import com.natusfarma.pc.itecvstotvs.componente.MapClass;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.primario.PriCrediarioEmissao;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.secundario.SecCrediarioEmissao;
import com.natusfarma.pc.itecvstotvs.model.ModeloCrediarioEmissao;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.ordenacao.financeiro.crediario.OrdenarFilialClienteNumeroEmissaoValor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CrediarioEmissaoService extends CompararDadosTipo<ModeloCrediarioEmissao> {

    @Autowired
    private PriCrediarioEmissao bancoPrimarioService;
    @Autowired
    private SecCrediarioEmissao bancoSecundarioService;

    public CrediarioEmissaoService() {
        setComparator(new OrdenarFilialClienteNumeroEmissaoValor());
    }

    /**
     * Método principal onde realiza a regra de negócio.
     * pega a data de início e fim e carrega a informação de cada banco em listas diferentes.
     * depois é ordenada cada lista para melhor desempenho.
     * @param inicio
     * @param fim
     */
    public ModeloListas<ModeloCrediarioEmissao> processar(LocalDate inicio, LocalDate fim) {
        dataInicio = inicio;
        dataFinal = fim;
        List<ModeloCrediarioEmissao> listaBancoPrimario = bancoPrimarioService.processar(inicio, fim);
        List<ModeloCrediarioEmissao> listaBancoSecundario = bancoSecundarioService.processar(inicio, fim);
        comparaValor(listaBancoPrimario, listaBancoSecundario);
        return getModeloListas();
    }


    @Override
    public int quantidadeDeColunaDoTipo() {
        return MapClass.totalDeAtributos(this.getClass());
    }



}

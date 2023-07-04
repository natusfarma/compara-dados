package com.natusfarma.pc.itecvstotvs.service;

import com.natusfarma.pc.itecvstotvs.componente.CompararDadosTipo;
import com.natusfarma.pc.itecvstotvs.componente.MapClass;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.cartoes.primario.PriCartoes;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.cartoes.secundario.SecCartoes;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.midia.primario.PriMidia;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.midia.secundario.SecMidia;
import com.natusfarma.pc.itecvstotvs.model.ModeloCartoes;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.model.ModeloMidia;
import com.natusfarma.pc.itecvstotvs.ordenacao.financeiro.midia.OrdenarFilialClienteNumeroEmissaoValor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MidiaService extends CompararDadosTipo<ModeloMidia> {


    @Autowired
    private PriMidia bancoPrimarioService;
    @Autowired
    private SecMidia bancoSecundarioService;

    public MidiaService() {
        setComparator(new OrdenarFilialClienteNumeroEmissaoValor());
    }

    /**
     * Método principal onde realiza a regra de negócio.
     * pega a data de início e fim e carrega a informação de cada banco em listas diferentes.
     * depois é ordenada cada lista para melhor desempenho.
     * @param inicio
     * @param fim
     */
    public ModeloListas<ModeloMidia> processar(LocalDate inicio, LocalDate fim) {
        dataInicio = inicio;
        dataFinal = fim;
        List<ModeloMidia> listaBancoPrimario = bancoPrimarioService.processar(inicio, fim);
        List<ModeloMidia> listaBancoSecundario = bancoSecundarioService.processar(inicio, fim);
        comparaValor(listaBancoPrimario, listaBancoSecundario);
        return getModeloListas();
    }

    @Override
    public int quantidadeDeColunaDoTipo() {
        return MapClass.totalDeAtributos(this.getClass());
    }



}

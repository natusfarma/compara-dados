package com.natusfarma.pc.itecvstotvs.service;

import com.natusfarma.pc.itecvstotvs.componente.CompararDadosTipo;
import com.natusfarma.pc.itecvstotvs.componente.MapClass;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.ndf.primario.PriNdf;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.ndf.secundario.SecNdf;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.model.ModeloNdf;
import com.natusfarma.pc.itecvstotvs.ordenacao.financeiro.ndf.OrdenarFornTituloFilialEmissao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NdfService extends CompararDadosTipo<ModeloNdf> {

    @Autowired
    private PriNdf bancoPrimarioService;
    @Autowired
    private SecNdf bancoSecundarioService;

    public NdfService() {
        setComparator(new OrdenarFornTituloFilialEmissao());
    }

    /**
     * Método principal onde realiza a regra de negócio.
     * pega a data de início e fim e carrega a informação de cada banco em listas diferentes.
     * depois é ordenada cada lista para melhor desempenho.
     * @param inicio
     * @param fim
     */
    public ModeloListas<ModeloNdf> processar(LocalDate inicio, LocalDate fim) {
        dataInicio = inicio;
        dataFinal = fim;
        List<ModeloNdf> listaBancoPrimario = bancoPrimarioService.processar(inicio, fim);
        List<ModeloNdf> listaBancoSecundario = bancoSecundarioService.processar(inicio, fim);
        comparaValor(listaBancoPrimario, listaBancoSecundario);
        return getModeloListas();
    }

    public ModeloListas<ModeloNdf> processarCancelado(LocalDate inicio, LocalDate fim) {
        dataInicio = inicio;
        dataFinal = fim;
        List<ModeloNdf> listaBancoPrimario = bancoPrimarioService.processarCancelado(inicio, fim);
        //consulta não deve retornar nada.
        List<ModeloNdf> listaBancoSecundario = bancoSecundarioService.processarCancelado();
        comparaValor(listaBancoPrimario, listaBancoSecundario);
        return getModeloListas();
    }



    @Override
    public int quantidadeDeColunaDoTipo() {
        return MapClass.totalDeAtributos(this.getClass());
    }


}

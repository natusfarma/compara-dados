package com.natusfarma.pc.itecvstotvs.service;

import com.natusfarma.pc.itecvstotvs.componente.CompararDadosTipo;
import com.natusfarma.pc.itecvstotvs.componente.MapClass;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.fechamento_estoque.primario.PriFechEstoque;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.fechamento_estoque.secundario.SecFechEstoque;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.ndf.primario.PriNdf;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.ndf.secundario.SecNdf;
import com.natusfarma.pc.itecvstotvs.model.ModeloFechEstoque;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.model.ModeloNdf;
import com.natusfarma.pc.itecvstotvs.ordenacao.financeiro.fech_estoque.OrdenarFilial;
import com.natusfarma.pc.itecvstotvs.ordenacao.financeiro.ndf.OrdenarFornTituloFilialEmissao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FechEstoqueService extends CompararDadosTipo<ModeloFechEstoque> {

    @Autowired
    private PriFechEstoque bancoPrimarioService;
    @Autowired
    private SecFechEstoque bancoSecundarioService;

    public FechEstoqueService() {
        setComparator(new OrdenarFilial());
    }

    /**
     * Método principal onde realiza a regra de negócio.
     * pega a data de início e fim e carrega a informação de cada banco em listas diferentes.
     * depois é ordenada cada lista para melhor desempenho.
     * @param inicio
     * @param fim
     */
    public ModeloListas<ModeloFechEstoque> processar(LocalDate inicio, LocalDate fim) {
        dataInicio = inicio;
        dataFinal = fim;
        List<ModeloFechEstoque> listaBancoPrimario = bancoPrimarioService.processar(inicio, fim);
        List<ModeloFechEstoque> listaBancoSecundario = bancoSecundarioService.processar(inicio, fim);
        comparaValor(listaBancoPrimario, listaBancoSecundario);
        return getModeloListas();
    }


    @Override
    public int quantidadeDeColunaDoTipo() {
        return MapClass.totalDeAtributos(this.getClass());
    }


}

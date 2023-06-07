package com.natusfarma.pc.itecvstotvs.componente.financeiro.fechamento_estoque.secundario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.contaspagar.RowMapperContasPagar;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.fechamento_estoque.RowMapperFechEstoque;
import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import com.natusfarma.pc.itecvstotvs.model.ModeloContasPagar;
import com.natusfarma.pc.itecvstotvs.model.ModeloFechEstoque;
import com.natusfarma.pc.itecvstotvs.util.DiretorioArquivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class SecFechEstoque extends Databases<ModeloFechEstoque> {


    @Autowired
    @TipoConexao(ChooseDB.SECONDARY)
    private JdbcTemplate secundaJdbcTemplate;

    @Autowired
    private RowMapperFechEstoque rowMapperFechEstoque;

    private final String NOME_ARQUIVO_SQL = "totvs.sql";
    private File file = new File(DiretorioArquivos.FECHAMENTO_ESTOQUE+ NOME_ARQUIVO_SQL);

    public List<ModeloFechEstoque> processar(LocalDate inicio, LocalDate fim){
        String condicao = montaCondicaoPeriodo("SE2010.E2_VENCTO", inicio, fim );
        String query = lerArquivoConsulta(file, "", "");
        return consulta(query, secundaJdbcTemplate, (rs, rowNum) -> rowMapperFechEstoque.modeloPadrao(rs));
    }



}

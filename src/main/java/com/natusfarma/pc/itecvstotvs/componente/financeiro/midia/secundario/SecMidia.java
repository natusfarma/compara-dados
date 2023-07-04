package com.natusfarma.pc.itecvstotvs.componente.financeiro.midia.secundario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.RowMapperCrediario;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.midia.RowMapperMidia;
import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import com.natusfarma.pc.itecvstotvs.model.ModeloCrediario;
import com.natusfarma.pc.itecvstotvs.model.ModeloMidia;
import com.natusfarma.pc.itecvstotvs.util.ConcatenarString;
import com.natusfarma.pc.itecvstotvs.util.DiretorioArquivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class SecMidia extends Databases<ModeloMidia> {


    @Autowired
    @TipoConexao(ChooseDB.SECONDARY)
    private JdbcTemplate secundaJdbcTemplate;

    @Autowired
    private RowMapperMidia rowMapperMidia;

    private final String NOME_ARQUIVO_SQL = "totvs.sql";
    private File file = new File(DiretorioArquivos.MIDIA + NOME_ARQUIVO_SQL);

    public List<ModeloMidia> processar(LocalDate inicio, LocalDate fim) {
        String condicao = montaCondicaoPeriodo("E1_EMISSAO", inicio, fim );
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, secundaJdbcTemplate, (rs, rowNum) -> rowMapperMidia.modeloPadrao(rs));
    }



}

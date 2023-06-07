package com.natusfarma.pc.itecvstotvs.componente.financeiro.ndf.primario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.contaspagar.RowMapperContasPagar;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.ndf.RowMapperNdf;
import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import com.natusfarma.pc.itecvstotvs.model.ModeloContasPagar;
import com.natusfarma.pc.itecvstotvs.model.ModeloNdf;
import com.natusfarma.pc.itecvstotvs.util.DiretorioArquivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class PriNdf extends Databases<ModeloNdf> {


    @Autowired
    @TipoConexao(ChooseDB.PRIMARY)
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    private RowMapperNdf rowMapperNdf;

    private final String NOME_ARQUIVO_SQL = "itec.sql";
    private File file = new File(DiretorioArquivos.NDF+ NOME_ARQUIVO_SQL);


    public List<ModeloNdf> processar(LocalDate inicio, LocalDate fim){
        String condicao = montaCondicaoPeriodo("DT_EMI_NF", inicio, fim );
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperNdf.modeloPadrao(rs));
    }

    public List<ModeloNdf> processarCancelado(LocalDate inicio, LocalDate fim){
        String condicao = montaCondicaoPeriodo("EST_NF_SAI.DT_CANCEL", inicio, fim );
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperNdf.modeloPadrao(rs));
    }



}

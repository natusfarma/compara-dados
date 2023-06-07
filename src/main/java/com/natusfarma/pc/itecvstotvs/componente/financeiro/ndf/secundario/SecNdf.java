package com.natusfarma.pc.itecvstotvs.componente.financeiro.ndf.secundario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.ndf.RowMapperNdf;
import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import com.natusfarma.pc.itecvstotvs.model.ModeloNdf;
import com.natusfarma.pc.itecvstotvs.util.DiretorioArquivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class SecNdf extends Databases<ModeloNdf> {


    @Autowired
    @TipoConexao(ChooseDB.SECONDARY)
    private JdbcTemplate secundaJdbcTemplate;

    @Autowired
    private RowMapperNdf rowMapperNdf;

    private final String NOME_ARQUIVO_SQL = "totvs.sql";
    private File file = new File(DiretorioArquivos.NDF+ NOME_ARQUIVO_SQL);

    public List<ModeloNdf> processar(LocalDate inicio, LocalDate fim){
        String condicao = montaCondicaoPeriodo("SE2010.E2_EMISSAO", inicio, fim );
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, secundaJdbcTemplate, (rs, rowNum) -> rowMapperNdf.modeloPadrao(rs));
    }

    public List<ModeloNdf> processarCancelado(){
        String condicao = " AND SE2010.E2_TIPO = '?' ";
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, secundaJdbcTemplate, (rs, rowNum) -> rowMapperNdf.modeloPadrao(rs));
    }



}

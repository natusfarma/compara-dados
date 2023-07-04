package com.natusfarma.pc.itecvstotvs.componente.financeiro.midia.primario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.RowMapperCrediario;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.midia.RowMapperMidia;
import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import com.natusfarma.pc.itecvstotvs.model.ModeloCrediario;
import com.natusfarma.pc.itecvstotvs.model.ModeloMidia;
import com.natusfarma.pc.itecvstotvs.util.ConcatenarNumeros;
import com.natusfarma.pc.itecvstotvs.util.DiretorioArquivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class PriMidia extends Databases<ModeloMidia> {


    @Autowired
    @TipoConexao(ChooseDB.PRIMARY)
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    private RowMapperMidia rowMapperMidia;


    private final String NOME_ARQUIVO_SQL = "itec.sql";
    private File file = new File(DiretorioArquivos.MIDIA+ NOME_ARQUIVO_SQL);


    /*Criado para processar os dados por data de emissão*/
    public List<ModeloMidia> processar(LocalDate inicio, LocalDate fim){
        String condicao1 = montaCondicaoPeriodo("RC_DEB.DT_DP", inicio, fim );
        String condicao2 = "";
        String query = lerArquivoConsulta(file, condicao1,condicao2);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperMidia.modeloPadrao(rs));
    }


}

package com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.primario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.RowMapperCrediarioEmissao;
import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import com.natusfarma.pc.itecvstotvs.model.ModeloCrediarioEmissao;
import com.natusfarma.pc.itecvstotvs.util.ConcatenarNumeros;
import com.natusfarma.pc.itecvstotvs.util.DiretorioArquivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class PriCrediarioEmissao extends Databases<ModeloCrediarioEmissao> {


    @Autowired
    @TipoConexao(ChooseDB.PRIMARY)
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    private RowMapperCrediarioEmissao rowMapperCrediarioEmissao;

    private final String NOME_ARQUIVO_SQL = "itec.sql";
    private File file = new File(DiretorioArquivos.CREDIARIO + NOME_ARQUIVO_SQL);


    /*Criado para processar os dados por data de emissão*/
    public List<ModeloCrediarioEmissao> processar(LocalDate inicio, LocalDate fim){
        String condicao1 = montaCondicaoPeriodo("RC_DEB.DT_DP", inicio, fim );
        String condicao2 = ""; //montaCondicaoPeriodo("DT_LANC_CRED", inicio, fim );
        String query = lerArquivoConsulta(file, condicao1,condicao2);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperCrediarioEmissao.modeloPadrao(rs));
    }



}

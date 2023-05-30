package com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.primario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.cheque.RowMapperCheque;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.RowMapperCrediario;
import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import com.natusfarma.pc.itecvstotvs.model.ModeloCheque;
import com.natusfarma.pc.itecvstotvs.model.ModeloCrediario;
import com.natusfarma.pc.itecvstotvs.util.DiretorioArquivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class PriCrediario extends Databases<ModeloCrediario> {


    @Autowired
    @TipoConexao(ChooseDB.PRIMARY)
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    private RowMapperCrediario rowMapperCrediario;

    private final String NOME_ARQUIVO_SQL = "itec.sql";
    private File file = new File(DiretorioArquivos.CREDIARIO+ NOME_ARQUIVO_SQL);


    public List<ModeloCrediario> processar(LocalDate inicio, LocalDate fim){
        String condicao1 = montaCondicaoPeriodo("RC_DEB.DT_DP", inicio, fim );
        String condicao2 = montaCondicaoPeriodo("DT_LANC_CRED", inicio, fim );
        String query = lerArquivoConsulta(file, condicao1,condicao2);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperCrediario.modeloPadrao(rs));
    }


}

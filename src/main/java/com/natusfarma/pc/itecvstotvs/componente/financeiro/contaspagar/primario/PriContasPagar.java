package com.natusfarma.pc.itecvstotvs.componente.financeiro.contaspagar.primario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.contaspagar.RowMapperContasPagar;
import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import com.natusfarma.pc.itecvstotvs.model.ModeloContasPagar;
import com.natusfarma.pc.itecvstotvs.util.DiretorioArquivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class PriContasPagar extends Databases<ModeloContasPagar> {


    @Autowired
    @TipoConexao(ChooseDB.PRIMARY)
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    private RowMapperContasPagar rowMapperContasPagar;

    private final String NOME_ARQUIVO_SQL = "itec.sql";
    private File file = new File(DiretorioArquivos.CONTAS_A_PAGAR+ NOME_ARQUIVO_SQL);


    public List<ModeloContasPagar> processar(LocalDate inicio, LocalDate fim){
        String condicao = montaCondicaoPeriodo("PG_CRED.DT_VENCTO", inicio, fim );
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperContasPagar.modeloPadrao(rs));
    }

    public List<ModeloContasPagar> processarEmissao(LocalDate inicio, LocalDate fim) {
        String condicao = montaCondicaoPeriodo("PG_CRED.DT_EMIS", inicio, fim );
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperContasPagar.modeloPadrao(rs));
    }

    public List<ModeloContasPagar> processarPorFilial(int filialId, LocalDate inicio, LocalDate fim){
        String[] condicoes = new String[] {
                montaCondicaoPeriodo("PG_CRED.DT_VENCTO", inicio, fim ),
                String.format(" AND PG_CRED.CD_FILIAL = %s ",filialId)
        };
        String query = lerArquivoConsulta(file, condicoes);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperContasPagar.modeloPadrao(rs));
    }


}

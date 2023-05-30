package com.natusfarma.pc.itecvstotvs.componente.financeiro.contaspagar.secundario;

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

public class SecContasPagar extends Databases<ModeloContasPagar> {


    @Autowired
    @TipoConexao(ChooseDB.SECONDARY)
    private JdbcTemplate secundaJdbcTemplate;

    @Autowired
    private RowMapperContasPagar rowMapperContasPagar;

    private final String NOME_ARQUIVO_SQL = "totvs.sql";
    private File file = new File(DiretorioArquivos.CONTAS_A_PAGAR+ NOME_ARQUIVO_SQL);

    public List<ModeloContasPagar> processar(LocalDate inicio, LocalDate fim){
        String condicao = montaCondicaoPeriodo("SE2010.E2_VENCTO", inicio, fim );
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, secundaJdbcTemplate, (rs, rowNum) -> rowMapperContasPagar.modeloPadrao(rs));
    }

    public List<ModeloContasPagar> processarPorFilial(int filialId, LocalDate inicio, LocalDate fim){
        String[] condicoes = new String[] {
                montaCondicaoPeriodo("SE2010.E2_VENCTO", inicio, fim ),
                String.format(" AND ZZ1010.ZZ1_FILLIN = %s ",filialId)
        };
        String query = lerArquivoConsulta(file, condicoes);
        return consulta(query, secundaJdbcTemplate, (rs, rowNum) -> rowMapperContasPagar.modeloPadrao(rs));
    }





}

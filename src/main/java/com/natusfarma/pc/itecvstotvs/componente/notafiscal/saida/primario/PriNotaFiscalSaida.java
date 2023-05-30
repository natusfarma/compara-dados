package com.natusfarma.pc.itecvstotvs.componente.notafiscal.saida.primario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.componente.notafiscal.RowMapperNotaFiscal;
import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import com.natusfarma.pc.itecvstotvs.model.ModeloNotaFiscal;
import com.natusfarma.pc.itecvstotvs.util.DiretorioArquivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class PriNotaFiscalSaida extends Databases<ModeloNotaFiscal> {

    @Autowired
    @TipoConexao(ChooseDB.PRIMARY)
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    private RowMapperNotaFiscal rowMapperNotaFiscal;

    private final String NOME_ARQUIVO_SQL = "itec.sql";
    private File file = new File(DiretorioArquivos.NOTAFISCAL_SAIDA+ NOME_ARQUIVO_SQL);

    public List<ModeloNotaFiscal> processar(LocalDate inicio, LocalDate fim){
        String condicao = montaCondicaoPeriodo("DT_EMI_NF", inicio, fim );
        String query = lerArquivoConsulta(file, condicao, "");
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperNotaFiscal.modeloPadrao(rs));
    }

}

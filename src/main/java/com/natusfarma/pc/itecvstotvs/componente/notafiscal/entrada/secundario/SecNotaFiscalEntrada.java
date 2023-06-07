package com.natusfarma.pc.itecvstotvs.componente.notafiscal.entrada.secundario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.cheque.RowMapperCheque;
import com.natusfarma.pc.itecvstotvs.componente.notafiscal.RowMapperNotaFiscal;
import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import com.natusfarma.pc.itecvstotvs.model.ModeloCheque;
import com.natusfarma.pc.itecvstotvs.model.ModeloNotaFiscal;
import com.natusfarma.pc.itecvstotvs.util.DiretorioArquivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class SecNotaFiscalEntrada extends Databases<ModeloNotaFiscal> {


    @Autowired
    @TipoConexao(ChooseDB.SECONDARY)
    private JdbcTemplate secundaJdbcTemplate;

    @Autowired
    private RowMapperNotaFiscal rowMapperNotaFiscal;

    private final String NOME_ARQUIVO_SQL = "totvs.sql";
    private File file = new File(DiretorioArquivos.NOTAFISCAL_ENTRADA+ NOME_ARQUIVO_SQL);

    public List<ModeloNotaFiscal> processar(LocalDate inicio, LocalDate fim){
        String condicao1 = montaCondicaoPeriodo("F1_RECBMTO", inicio, fim );
        String condicao2 = montaCondicaoPeriodo("F3_ENTRADA", inicio, fim );
        String query = lerArquivoConsulta(file, condicao1,condicao2);
        return consulta(query, secundaJdbcTemplate, (rs, rowNum) -> rowMapperNotaFiscal.modeloPadrao(rs));
    }


}

package com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.secundario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.RowMapperCrediarioPagamento;
import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import com.natusfarma.pc.itecvstotvs.model.ModeloCrediarioPagamento;
import com.natusfarma.pc.itecvstotvs.util.ConcatenarString;
import com.natusfarma.pc.itecvstotvs.util.DiretorioArquivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class SecCrediarioPagamento extends Databases<ModeloCrediarioPagamento> {


    @Autowired
    @TipoConexao(ChooseDB.SECONDARY)
    private JdbcTemplate secundaJdbcTemplate;

    @Autowired
    private RowMapperCrediarioPagamento rowMapperCrediarioPagamento;

    private final String NOME_ARQUIVO_SQL = "totvs.sql";
    private File file = new File(DiretorioArquivos.CREDIARIO + NOME_ARQUIVO_SQL);

    public List<ModeloCrediarioPagamento> processarDataPagamento(LocalDate inicio, LocalDate fim) {
        String condicao = montaCondicaoPeriodo("E5_DATA", inicio, fim );
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, secundaJdbcTemplate, (rs, rowNum) -> rowMapperCrediarioPagamento.modeloPadrao(rs));
    }


    public List<ModeloCrediarioPagamento> processarNumerosFiliais(String[] numeros, String[] filiais) {
        String condicao = String.format(" AND E1_NUM IN ( %s ) AND ZZ1_FILLIN IN ( %s )",
                    ConcatenarString.concatenar(numeros), ConcatenarString.concatenar(filiais));
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, secundaJdbcTemplate, (rs, rowNum) -> rowMapperCrediarioPagamento.modeloPadrao(rs));
    }


}

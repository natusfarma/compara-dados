package com.natusfarma.pc.itecvstotvs.componente.cadastro.fornecedor.primario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.fornecedor.RowMapperFornecedor;
import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import com.natusfarma.pc.itecvstotvs.model.ModeloFornecedor;
import com.natusfarma.pc.itecvstotvs.util.ConcatenarNumeros;
import com.natusfarma.pc.itecvstotvs.util.ConcatenarString;
import com.natusfarma.pc.itecvstotvs.util.DiretorioArquivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.util.List;

public class PriFornecedor extends Databases<ModeloFornecedor> {


    @Autowired
    @TipoConexao(ChooseDB.PRIMARY)
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    private RowMapperFornecedor rowMapperFornecedor;

    private final String NOME_ARQUIVO_SQL = "itec.sql";
    private File file = new File(DiretorioArquivos.FORNECEDORES+ NOME_ARQUIVO_SQL);


    public List<ModeloFornecedor> processarPorIds(String[] ids){
        String condicao = String.format(" AND CD_FORN IN (%s) ", ConcatenarNumeros.concatenar(ids));
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperFornecedor.modeloPadrao(rs));
    }

    public List<ModeloFornecedor> processarPorCgcCpf(String[] cgcCpf){
        String condicao = String.format(" AND CGC_CPF IN (%s) ", ConcatenarString.concatenar(cgcCpf));
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperFornecedor.modeloPadrao(rs));
    }



}

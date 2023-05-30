package com.natusfarma.pc.itecvstotvs.componente.cadastro.fornecedor.secundario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.fornecedor.RowMapperFornecedor;
import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import com.natusfarma.pc.itecvstotvs.model.ModeloFornecedor;
import com.natusfarma.pc.itecvstotvs.util.ConcatenarString;
import com.natusfarma.pc.itecvstotvs.util.DiretorioArquivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.util.List;

public class SecFornecedor extends Databases<ModeloFornecedor> {


    @Autowired
    @TipoConexao(ChooseDB.SECONDARY)
    private JdbcTemplate secundaJdbcTemplate;

    @Autowired
    private RowMapperFornecedor rowMapperFornecedor;

    private final String NOME_ARQUIVO_SQL = "totvs.sql";
    private File file = new File(DiretorioArquivos.FORNECEDORES+ NOME_ARQUIVO_SQL);

    public List<ModeloFornecedor> processarPorIds(String[] ids){
        String condicao = String.format(" AND A2_COD IN (%s) ", ConcatenarString.concatenar(ids, 6));
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, secundaJdbcTemplate, (rs, rowNum) -> rowMapperFornecedor.modeloPadrao(rs));
    }

    public List<ModeloFornecedor> processarPorCgcCpf(String[] cgcCpf){
        String condicao = String.format(" AND A2_CGC IN (%s) ", ConcatenarString.concatenar(cgcCpf));
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, secundaJdbcTemplate, (rs, rowNum) -> rowMapperFornecedor.modeloPadrao(rs));
    }





}

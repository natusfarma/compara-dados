package com.natusfarma.pc.itecvstotvs.componente.cadastro.produto.secundario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.produto.RowMapperProduto;
import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import com.natusfarma.pc.itecvstotvs.model.ModeloProduto;
import com.natusfarma.pc.itecvstotvs.util.ConcatenarString;
import com.natusfarma.pc.itecvstotvs.util.DiretorioArquivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.util.List;

public class SecProduto extends Databases<ModeloProduto> {


    @Autowired
    @TipoConexao(ChooseDB.SECONDARY)
    private JdbcTemplate secundaJdbcTemplate;

    @Autowired
    private RowMapperProduto rowMapperProduto;

    private final String NOME_ARQUIVO_SQL = "totvs.sql";
    private File file = new File(DiretorioArquivos.PRODUTOS+ NOME_ARQUIVO_SQL);


    public List<ModeloProduto> processarPorIds(String[] valores){
        String condicao = String.format(" AND B1_COD IN (%s) ", ConcatenarString.concatenar(valores));
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, secundaJdbcTemplate, (rs, rowNum) -> rowMapperProduto.modeloPadrao(rs));
    }





}

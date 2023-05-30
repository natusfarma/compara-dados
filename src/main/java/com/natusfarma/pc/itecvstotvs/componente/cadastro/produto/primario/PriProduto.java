package com.natusfarma.pc.itecvstotvs.componente.cadastro.produto.primario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.produto.RowMapperProduto;
import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import com.natusfarma.pc.itecvstotvs.model.ModeloFornecedor;
import com.natusfarma.pc.itecvstotvs.model.ModeloProduto;
import com.natusfarma.pc.itecvstotvs.util.ConcatenarNumeros;
import com.natusfarma.pc.itecvstotvs.util.ConcatenarString;
import com.natusfarma.pc.itecvstotvs.util.DiretorioArquivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.util.List;

public class PriProduto extends Databases<ModeloProduto> {


    @Autowired
    @TipoConexao(ChooseDB.PRIMARY)
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    private RowMapperProduto rowMapperProduto;

    private final String NOME_ARQUIVO_SQL = "itec.sql";
    private File file = new File(DiretorioArquivos.PRODUTOS+ NOME_ARQUIVO_SQL);


    public List<ModeloProduto> processarPorIds(String[] valores){
        String condicao = String.format(" AND CD_PROD IN (%s) ", ConcatenarNumeros.concatenar(valores));
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperProduto.modeloPadrao(rs));
    }


}

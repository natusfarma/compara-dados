package com.natusfarma.pc.itecvstotvs.componente.cadastro.cliente.secundario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.cliente.RowMapperClientes;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.contaspagar.RowMapperContasPagar;
import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import com.natusfarma.pc.itecvstotvs.model.ModeloClientes;
import com.natusfarma.pc.itecvstotvs.model.ModeloContasPagar;
import com.natusfarma.pc.itecvstotvs.util.ConcatenarNumeros;
import com.natusfarma.pc.itecvstotvs.util.ConcatenarString;
import com.natusfarma.pc.itecvstotvs.util.DiretorioArquivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class SecClientes extends Databases<ModeloClientes> {


    @Autowired
    @TipoConexao(ChooseDB.SECONDARY)
    private JdbcTemplate secundaJdbcTemplate;

    @Autowired
    private RowMapperClientes rowMapperClientes;

    private final String NOME_ARQUIVO_SQL = "totvs.sql";
    private File file = new File(DiretorioArquivos.CLIENTES+ NOME_ARQUIVO_SQL);

    public List<ModeloClientes> processarPorIds(String[] ids){
        String condicao = String.format(" AND A1_COD IN (%s) ", ConcatenarString.concatenar(ids, 6));
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, secundaJdbcTemplate, (rs, rowNum) -> rowMapperClientes.modeloPadrao(rs));
    }

    public List<ModeloClientes> processarPorCgcCpf(String[] cgcCpf){
        String condicao = String.format(" AND A1_CGC IN (%s) ", ConcatenarString.concatenar(cgcCpf));
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, secundaJdbcTemplate, (rs, rowNum) -> rowMapperClientes.modeloPadrao(rs));
    }





}

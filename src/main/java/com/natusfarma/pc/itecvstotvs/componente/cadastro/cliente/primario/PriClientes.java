package com.natusfarma.pc.itecvstotvs.componente.cadastro.cliente.primario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.cliente.RowMapperClientes;
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
import java.util.List;

public class PriClientes extends Databases<ModeloClientes> {


    @Autowired
    @TipoConexao(ChooseDB.PRIMARY)
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    private RowMapperClientes rowMapperClientes;

    private final String NOME_ARQUIVO_SQL = "itec.sql";
    private File file = new File(DiretorioArquivos.CLIENTES+ NOME_ARQUIVO_SQL);


    public List<ModeloClientes> processarPorIds(String[] ids){
        String condicao = String.format(" AND CD_CLI IN (%s) ", ConcatenarNumeros.concatenar(ids));
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperClientes.modeloPadrao(rs));
    }

    public List<ModeloClientes> processarPorCgcCpf(String[] cgcCpf){
        String condicao = String.format(" AND CGC_CPF IN (%s) ", ConcatenarString.concatenar(cgcCpf));
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperClientes.modeloPadrao(rs));
    }



}

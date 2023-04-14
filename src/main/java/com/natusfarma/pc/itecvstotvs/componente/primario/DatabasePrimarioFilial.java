package com.natusfarma.pc.itecvstotvs.componente.primario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import com.natusfarma.pc.itecvstotvs.model.ModeloFilial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.util.List;

public class DatabasePrimarioFilial extends Databases<ModeloFilial> {


    @Autowired
    @TipoConexao(ChooseDB.PRIMARY)
    private JdbcTemplate primaryJdbcTemplate;
    @Autowired
    private RowMapperFiliais rowMapperFiliais;


    public List<ModeloFilial> processar(){
        File file = new File("consultas/filiais.sql");
        String query = lerArquivoConsulta(file);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperFiliais.modeloFilial(rs));
    }

    public ModeloFilial buscarPorId(int id){
        File file = new File("consultas/filiais.sql");
        String condicao = String.format(" AND CD_FILIAL = %s ",id);
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperFiliais.modeloFilial(rs)).get(0);
    }

    public void imprimirListas(List<ModeloFilial> lista){
        lista.stream().forEach(System.out::println);
    }



}

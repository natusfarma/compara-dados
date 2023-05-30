package com.natusfarma.pc.itecvstotvs.componente.filial.primario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import com.natusfarma.pc.itecvstotvs.model.ModeloFilial;
import com.natusfarma.pc.itecvstotvs.util.DiretorioArquivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.util.List;

public class PriFilial extends Databases<ModeloFilial> {


    @Autowired
    @TipoConexao(ChooseDB.PRIMARY)
    private JdbcTemplate primaryJdbcTemplate;
    @Autowired
    private RowMapperFiliais rowMapperFiliais;

    private final String NOME_ARQUIVO_SQL = "filiais.sql";
    private File file = new File(DiretorioArquivos.FILIAL+ NOME_ARQUIVO_SQL);

    public List<ModeloFilial> processar(){
        String query = lerArquivoConsulta(file);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperFiliais.modeloFilial(rs));
    }

    public ModeloFilial buscarPorId(int id){
        String condicao = String.format(" AND CD_FILIAL = %s ",id);
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperFiliais.modeloFilial(rs)).get(0);
    }

    public void imprimirListas(List<ModeloFilial> lista){
        lista.stream().forEach(System.out::println);
    }



}

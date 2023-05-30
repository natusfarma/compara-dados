package com.natusfarma.pc.itecvstotvs.componente;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public abstract class Databases<T> extends  LerArquivoSql{

    public List<Map<String, Object>> consulta(String query, JdbcTemplate jdbcTemplate){
        return jdbcTemplate.queryForList(query);
    }

    public List<T> consulta(String query, JdbcTemplate jdbcTemplate, RowMapper<T> rowMapper){
        return jdbcTemplate.query(query, rowMapper);
    }

}

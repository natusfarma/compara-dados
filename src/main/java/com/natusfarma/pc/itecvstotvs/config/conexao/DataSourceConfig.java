package com.natusfarma.pc.itecvstotvs.config.conexao;

import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @TipoConexao(ChooseDB.PRIMARY)
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @TipoConexao(ChooseDB.PRIMARY)
    public JdbcTemplate primaryJdbcTemplate(DataSource primaryDataSource){
        return new JdbcTemplate(primaryDataSource);
    }

    @Bean
    @TipoConexao(ChooseDB.SECONDARY)
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @TipoConexao(ChooseDB.SECONDARY)
    public JdbcTemplate secondaryDJdbcTemplate(DataSource secondaryDataSource){
        return new JdbcTemplate(secondaryDataSource);
    }



}


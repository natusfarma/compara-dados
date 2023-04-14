package com.natusfarma.pc.itecvstotvs.componente.primario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.componente.RowMapperPadrao;
import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import com.natusfarma.pc.itecvstotvs.model.ModeloPadrao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class DatabasePrimario extends Databases<ModeloPadrao> {


    @Autowired
    @TipoConexao(ChooseDB.PRIMARY)
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    private RowMapperPadrao rowMapperPadrao;


    public List<ModeloPadrao> processar(LocalDate inicio, LocalDate fim){
        File file = new File("consultas/itec.sql");
        String condicao = montaCondicaoPeriodo("PG_CRED.DT_VENCTO", inicio, fim );
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperPadrao.modeloPadrao(rs));
    }

    public List<ModeloPadrao> processarPorFilial(int filialId, LocalDate inicio, LocalDate fim){
        File file = new File("consultas/itec.sql");
        String[] condicoes = new String[] {
                montaCondicaoPeriodo("PG_CRED.DT_VENCTO", inicio, fim ),
                String.format(" AND PG_CRED.CD_FILIAL = %s ",filialId)
        };
        String query = lerArquivoConsulta(file, condicoes);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperPadrao.modeloPadrao(rs));
    }


}

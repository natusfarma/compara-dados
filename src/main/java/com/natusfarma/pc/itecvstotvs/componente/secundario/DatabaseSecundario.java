package com.natusfarma.pc.itecvstotvs.componente.secundario;

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

public class DatabaseSecundario extends Databases<ModeloPadrao> {


    @Autowired
    @TipoConexao(ChooseDB.SECONDARY)
    private JdbcTemplate secundaJdbcTemplate;

    @Autowired
    private RowMapperPadrao rowMapperPadrao;

    public List<ModeloPadrao> processar(LocalDate inicio, LocalDate fim){
        File file = new File("consultas/totvs.sql");
        String condicao = montaCondicaoPeriodo("SE2010.E2_VENCTO", inicio, fim );
        String query = lerArquivoConsulta(file, condicao);
        return consulta(query, secundaJdbcTemplate, (rs, rowNum) -> rowMapperPadrao.modeloPadrao(rs));
    }

    public List<ModeloPadrao> processarPorFilial(int filialId, LocalDate inicio, LocalDate fim){
        File file = new File("consultas/totvs.sql");
        String[] condicoes = new String[] {
                montaCondicaoPeriodo("SE2010.E2_VENCTO", inicio, fim ),
                String.format(" AND ZZ1010.ZZ1_FILLIN = %s ",filialId)
        };
        String query = lerArquivoConsulta(file, condicoes);
        return consulta(query, secundaJdbcTemplate, (rs, rowNum) -> rowMapperPadrao.modeloPadrao(rs));
    }





}

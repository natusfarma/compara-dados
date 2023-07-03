package com.natusfarma.pc.itecvstotvs.componente.financeiro.cartoes.primario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.cartoes.RowMapperCartoes;
import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import com.natusfarma.pc.itecvstotvs.model.ModeloCartoes;
import com.natusfarma.pc.itecvstotvs.util.DiretorioArquivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class PriCartoes extends Databases<ModeloCartoes> {


    @Autowired
    @TipoConexao(ChooseDB.PRIMARY)
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    private RowMapperCartoes rowMapperCartoes;

    private final String NOME_ARQUIVO_SQL = "itec.sql";
    private File file = new File(DiretorioArquivos.CARTOES+ NOME_ARQUIVO_SQL);


    public List<ModeloCartoes> processar(LocalDate inicio, LocalDate fim){
        String condicao1 = montaCondicaoPeriodo("PDV_VD.DT_VD", inicio, fim );
        String condicao2 = montaCondicaoPeriodo("PDV_CUPOM_NAO_FISCAL.DT_CUPOM", inicio, fim);
        String query = lerArquivoConsulta(file, condicao1, condicao2);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperCartoes.modeloPadrao(rs));
    }


}

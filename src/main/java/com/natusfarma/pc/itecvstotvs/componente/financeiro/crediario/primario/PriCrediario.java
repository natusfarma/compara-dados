package com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.primario;

import com.natusfarma.pc.itecvstotvs.componente.Databases;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.RowMapperCrediario;
import com.natusfarma.pc.itecvstotvs.config.ChooseDB;
import com.natusfarma.pc.itecvstotvs.config.TipoConexao;
import com.natusfarma.pc.itecvstotvs.model.ModeloCrediario;
import com.natusfarma.pc.itecvstotvs.util.ConcatenarNumeros;
import com.natusfarma.pc.itecvstotvs.util.DiretorioArquivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class PriCrediario extends Databases<ModeloCrediario> {


    @Autowired
    @TipoConexao(ChooseDB.PRIMARY)
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    private RowMapperCrediario rowMapperCrediario;

    private final String NOME_ARQUIVO_SQL = "itec.sql";
    private File file = new File(DiretorioArquivos.CREDIARIO+ NOME_ARQUIVO_SQL);


    /*Criado para processar os dados por data de emissão*/
    public List<ModeloCrediario> processar(LocalDate inicio, LocalDate fim){
        String condicao1 = montaCondicaoPeriodo("RC_DEB.DT_DP", inicio, fim );
        String condicao2 = ""; //montaCondicaoPeriodo("DT_LANC_CRED", inicio, fim );
        String query = lerArquivoConsulta(file, condicao1,condicao2);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperCrediario.modeloPadrao(rs));
    }


    public List<ModeloCrediario> processarDataPagamento(LocalDate inicio, LocalDate fim){
        //String condicao1 = montaCondicaoPeriodo("Q_HIST.DT_PAG", inicio, fim );
        String condicao1 = montaCondicaoPeriodo("Q.DT_PGTO", inicio, fim );
        String condicao2 = ""; //montaCondicaoPeriodo("PDV_CUPOM_NAO_FISCAL.DT_CUPOM", inicio, fim );
        String query = lerArquivoConsulta(file, condicao1,condicao2);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperCrediario.modeloPadrao(rs));
    }

    public List<ModeloCrediario> processarNumerosFiliais(String[] numeros, String[] filiais){
        String condicao1 = String.format(
                " AND RC_DEB.NR_DP IN ( %s ) AND RC_DEB.CD_FILIAL IN ( %s ) ",
                ConcatenarNumeros.concatenar(numeros), ConcatenarNumeros.concatenar(filiais));
        String condicao2 = ""; /*String.format(
                " AND PDV_CUPOM_NAO_FISCAL.CD_CTR IN ( %s ) AND PDV_CUPOM_NAO_FISCAL.CD_FILIAL IN ( %s ) ",
                ConcatenarNumeros.concatenar(numeros), ConcatenarNumeros.concatenar(filiais));*/
        String query = lerArquivoConsulta(file, condicao1,condicao2);
        return consulta(query, primaryJdbcTemplate, (rs, rowNum) -> rowMapperCrediario.modeloPadrao(rs));
    }


}

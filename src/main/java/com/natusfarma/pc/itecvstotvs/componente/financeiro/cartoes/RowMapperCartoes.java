package com.natusfarma.pc.itecvstotvs.componente.financeiro.cartoes;

import com.natusfarma.pc.itecvstotvs.exception.ConversaoNaoEncontradaException;
import com.natusfarma.pc.itecvstotvs.model.ModeloCartoes;
import com.natusfarma.pc.itecvstotvs.model.ModeloCheque;
import com.natusfarma.pc.itecvstotvs.util.ConverterTipo;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class RowMapperCartoes {

    public ModeloCartoes modeloPadrao(ResultSet rs) {
        ModeloCartoes modeloCartoes = new ModeloCartoes();
        try{
            LocalDate dataEmissao = ConverterTipo.sqlDateToLocalDate(rs.getDate("EMISSAO"));
            modeloCartoes.setFilial(rs.getInt("FILIAL"));
            modeloCartoes.setNumero(rs.getString("NUMERO").trim());
            modeloCartoes.setCodCliente(rs.getInt("CODCLIENTE"));
            modeloCartoes.setDataEmissao(dataEmissao);
            modeloCartoes.setValor(rs.getDouble("VALOR"));
            modeloCartoes.setTipo(rs.getString("TIPO"));
            modeloCartoes.setNome(rs.getString("NOME_OPERADORA"));
            modeloCartoes.setObs(rs.getString("OBS").trim());
        }catch (SQLException e){
            throw new ConversaoNaoEncontradaException(e.getMessage(), e.getCause());
        }
        return modeloCartoes;
    }


}

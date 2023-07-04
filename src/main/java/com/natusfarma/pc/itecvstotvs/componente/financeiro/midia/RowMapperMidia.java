package com.natusfarma.pc.itecvstotvs.componente.financeiro.midia;

import com.natusfarma.pc.itecvstotvs.exception.ConversaoNaoEncontradaException;
import com.natusfarma.pc.itecvstotvs.model.ModeloCrediario;
import com.natusfarma.pc.itecvstotvs.model.ModeloMidia;
import com.natusfarma.pc.itecvstotvs.util.ConverterTipo;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class RowMapperMidia {

    public ModeloMidia modeloPadrao(ResultSet rs) {
        ModeloMidia modeloMidia = new ModeloMidia();
        try{
            LocalDate dataEmissao = ConverterTipo.sqlDateToLocalDate(rs.getDate("EMISSAO"));
            LocalDate dataVencimento = ConverterTipo.sqlDateToLocalDate(rs.getDate("VENCIMENTO"));
            modeloMidia.setFilial(rs.getInt("FILIAL"));
            modeloMidia.setNumero(rs.getString("NUMERO").trim());
            modeloMidia.setCodCliente(rs.getInt("CODCLIENTE"));
            modeloMidia.setValor(rs.getDouble("VALOR"));
            modeloMidia.setSaldo(rs.getDouble("SALDO"));
            modeloMidia.setStatus(rs.getString("STS_DP"));
            modeloMidia.setNome(rs.getString("NOME"));
            modeloMidia.setDataEmissao(dataEmissao);
            modeloMidia.setDataVenciamento(dataVencimento);
        }catch (SQLException e){
            throw new ConversaoNaoEncontradaException(e.getMessage(), e.getCause());
        }
        return modeloMidia;
    }


}

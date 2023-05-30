package com.natusfarma.pc.itecvstotvs.componente.financeiro.contaspagar;

import com.natusfarma.pc.itecvstotvs.exception.ConversaoNaoEncontradaException;
import com.natusfarma.pc.itecvstotvs.model.ModeloContasPagar;
import com.natusfarma.pc.itecvstotvs.util.ConverterTipo;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class RowMapperContasPagar {

    public ModeloContasPagar modeloPadrao(ResultSet rs) {
        ModeloContasPagar modeloContasPagar = new ModeloContasPagar();
        try{
            modeloContasPagar.setCodFornecedor(rs.getLong("CODFORN"));
            modeloContasPagar.setTitulo(rs.getString("TITULO").trim());
            modeloContasPagar.setNomeFornecedor(rs.getString("NMFORN"));
            modeloContasPagar.setParcela(rs.getInt("PARCELA"));
            modeloContasPagar.setNumNota(rs.getInt("NUM_NOTA"));
            modeloContasPagar.setValor(rs.getDouble("VALOR"));
            modeloContasPagar.setFilial(rs.getInt("FILIAL"));
            LocalDate data = ConverterTipo.sqlDateToLocalDate(rs.getDate("DT_VENCIMENTO"));
            modeloContasPagar.setDataVencimento(data);
            LocalDate dataEmissao = ConverterTipo.sqlDateToLocalDate(rs.getDate("DT_EMISSAO"));
            modeloContasPagar.setDataEmissao(dataEmissao);
        }catch (SQLException e){
            throw new ConversaoNaoEncontradaException(e.getMessage(), e.getCause());
        }
        return modeloContasPagar;
    }

}

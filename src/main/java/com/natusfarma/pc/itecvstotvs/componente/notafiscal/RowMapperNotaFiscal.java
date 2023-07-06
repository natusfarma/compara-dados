package com.natusfarma.pc.itecvstotvs.componente.notafiscal;

import com.natusfarma.pc.itecvstotvs.exception.ConversaoNaoEncontradaException;
import com.natusfarma.pc.itecvstotvs.model.ModeloNotaFiscal;
import com.natusfarma.pc.itecvstotvs.util.ConverterTipo;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class RowMapperNotaFiscal {

    public ModeloNotaFiscal modeloPadrao(ResultSet rs) {
        ModeloNotaFiscal modeloNotaFiscal = new ModeloNotaFiscal();
        try{
            LocalDate dataEmissao = ConverterTipo.sqlDateToLocalDate(rs.getDate("EMISSAO"));
            modeloNotaFiscal.setFilial(rs.getInt("FILIAL"));
            modeloNotaFiscal.setNumero(rs.getInt("NUMERO"));
            modeloNotaFiscal.setSerie(rs.getInt("SERIE"));
            modeloNotaFiscal.setDataEmissao(dataEmissao);
            modeloNotaFiscal.setChave(rs.getString("CHAVE").trim());
            modeloNotaFiscal.setCodCliente(rs.getInt("CLIENTE"));
            modeloNotaFiscal.setStatus(rs.getString("CANCELADO"));
            modeloNotaFiscal.setFilialTotvs(rs.getString("FILIAL_TOTVS"));
            modeloNotaFiscal.setValor(rs.getDouble("VALOR"));
        }catch (SQLException e){
            throw new ConversaoNaoEncontradaException(e.getMessage(), e.getCause());
        }
        return modeloNotaFiscal;
    }


}

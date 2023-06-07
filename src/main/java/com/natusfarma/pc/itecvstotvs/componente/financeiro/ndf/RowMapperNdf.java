package com.natusfarma.pc.itecvstotvs.componente.financeiro.ndf;

import com.natusfarma.pc.itecvstotvs.exception.ConversaoNaoEncontradaException;
import com.natusfarma.pc.itecvstotvs.model.ModeloNdf;
import com.natusfarma.pc.itecvstotvs.util.ConverterTipo;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class RowMapperNdf {

    public ModeloNdf modeloPadrao(ResultSet rs) {
        ModeloNdf modeloNdf = new ModeloNdf();
        try{
            modeloNdf.setFilial(rs.getInt("FILIAL"));
            modeloNdf.setTitulo(rs.getString("TITULO").trim());
            modeloNdf.setCodFornecedor(rs.getLong("CODFORN"));
            modeloNdf.setNome(rs.getString("NMFORN"));
            LocalDate dataEmissao = ConverterTipo.sqlDateToLocalDate(rs.getDate("DT_EMISSAO"));
            modeloNdf.setDataEmissao(dataEmissao);
            LocalDate data = ConverterTipo.sqlDateToLocalDate(rs.getDate("DT_VENCIMENTO"));
            modeloNdf.setDataVencimento(data);
            modeloNdf.setNumero(rs.getInt("NUMERO"));
            modeloNdf.setValor(rs.getDouble("VALOR"));
            modeloNdf.setHist1(rs.getString("HISTORICO"));
            modeloNdf.setHist2(rs.getString("OBS2"));
            modeloNdf.setHist3(rs.getString("OBS3"));
            modeloNdf.setFilialTotvs(rs.getString("FILIAL_TOTVS"));
            modeloNdf.setStatus(rs.getString("CANCELADO"));
        }catch (SQLException e){
            throw new ConversaoNaoEncontradaException(e.getMessage(), e.getCause());
        }
        return modeloNdf;
    }

}

package com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario;

import com.natusfarma.pc.itecvstotvs.exception.ConversaoNaoEncontradaException;
import com.natusfarma.pc.itecvstotvs.model.ModeloCrediarioEmissao;
import com.natusfarma.pc.itecvstotvs.util.ConverterTipo;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class RowMapperCrediarioEmissao {

    public ModeloCrediarioEmissao modeloPadrao(ResultSet rs) {
        ModeloCrediarioEmissao modeloCrediarioEmissao = new ModeloCrediarioEmissao();
        try{
            LocalDate dataEmissao = ConverterTipo.sqlDateToLocalDate(rs.getDate("EMISSAO"));
            LocalDate dataVencimento = ConverterTipo.sqlDateToLocalDate(rs.getDate("VENCIMENTO"));
            modeloCrediarioEmissao.setFilial(rs.getInt("FILIAL"));
            modeloCrediarioEmissao.setNumero(rs.getInt("NUMERO"));
            modeloCrediarioEmissao.setCodCliente(rs.getInt("CODCLIENTE"));
            modeloCrediarioEmissao.setNome(rs.getString("NOME"));
            modeloCrediarioEmissao.setValor(rs.getDouble("VALOR"));
            modeloCrediarioEmissao.setDataEmissao(dataEmissao);
            modeloCrediarioEmissao.setDataVencimento(dataVencimento);
        }catch (SQLException e){
            throw new ConversaoNaoEncontradaException(e.getMessage(), e.getCause());
        }
        return modeloCrediarioEmissao;
    }


}

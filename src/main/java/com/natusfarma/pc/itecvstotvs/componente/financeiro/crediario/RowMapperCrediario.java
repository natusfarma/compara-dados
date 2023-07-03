package com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario;

import com.natusfarma.pc.itecvstotvs.exception.ConversaoNaoEncontradaException;
import com.natusfarma.pc.itecvstotvs.model.ModeloCrediario;
import com.natusfarma.pc.itecvstotvs.util.ConverterTipo;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class RowMapperCrediario {

    public ModeloCrediario modeloPadrao(ResultSet rs) {
        ModeloCrediario modeloCrediario = new ModeloCrediario();
        try{
            LocalDate dataEmissao = ConverterTipo.sqlDateToLocalDate(rs.getDate("EMISSAO"));
            LocalDate dataVencimento = ConverterTipo.sqlDateToLocalDate(rs.getDate("VENCIMENTO"));
            LocalDate dataPagamento = ConverterTipo.sqlDateToLocalDate(rs.getDate("DT_BAIXA"));
            modeloCrediario.setFilial(rs.getInt("FILIAL"));
            modeloCrediario.setNumero(rs.getInt("NUMERO"));
            modeloCrediario.setCodCliente(rs.getInt("CODCLIENTE"));
            modeloCrediario.setNome(rs.getString("NOME"));
            modeloCrediario.setValor(rs.getDouble("VALOR"));
            modeloCrediario.setValorPago(rs.getDouble("VALOR_BAIXA"));
            modeloCrediario.setDataEmissao(dataEmissao);
            modeloCrediario.setDataVencimento(dataVencimento);
            modeloCrediario.setDataPagamento(dataPagamento);
        }catch (SQLException e){
            throw new ConversaoNaoEncontradaException(e.getMessage(), e.getCause());
        }
        return modeloCrediario;
    }


}

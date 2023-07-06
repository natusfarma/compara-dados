package com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario;

import com.natusfarma.pc.itecvstotvs.exception.ConversaoNaoEncontradaException;
import com.natusfarma.pc.itecvstotvs.model.ModeloCrediarioEmissao;
import com.natusfarma.pc.itecvstotvs.model.ModeloCrediarioPagamento;
import com.natusfarma.pc.itecvstotvs.util.ConverterTipo;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class RowMapperCrediarioPagamento {

    public ModeloCrediarioPagamento modeloPadrao(ResultSet rs) {
        ModeloCrediarioPagamento modeloCrediarioPagamento = new ModeloCrediarioPagamento();
        try{
            LocalDate dataEmissao = ConverterTipo.sqlDateToLocalDate(rs.getDate("EMISSAO"));
            LocalDate dataVencimento = ConverterTipo.sqlDateToLocalDate(rs.getDate("VENCIMENTO"));
            LocalDate dataPagamento = ConverterTipo.sqlDateToLocalDate(rs.getDate("DT_BAIXA"));
            modeloCrediarioPagamento.setFilial(rs.getInt("FILIAL"));
            modeloCrediarioPagamento.setNumero(rs.getInt("NUMERO"));
            modeloCrediarioPagamento.setCodCliente(rs.getInt("CODCLIENTE"));
            modeloCrediarioPagamento.setNome(rs.getString("NOME"));
            modeloCrediarioPagamento.setValor(rs.getDouble("VALOR"));
            modeloCrediarioPagamento.setValorPago(rs.getDouble("VALOR_BAIXA"));
            modeloCrediarioPagamento.setDataEmissao(dataEmissao);
            modeloCrediarioPagamento.setDataVencimento(dataVencimento);
            modeloCrediarioPagamento.setDataPagamento(dataPagamento);
        }catch (SQLException e){
            throw new ConversaoNaoEncontradaException(e.getMessage(), e.getCause());
        }
        return modeloCrediarioPagamento;
    }


}

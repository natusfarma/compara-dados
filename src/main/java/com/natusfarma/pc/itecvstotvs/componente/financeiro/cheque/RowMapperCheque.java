package com.natusfarma.pc.itecvstotvs.componente.financeiro.cheque;

import com.natusfarma.pc.itecvstotvs.exception.ConversaoNaoEncontradaException;
import com.natusfarma.pc.itecvstotvs.model.ModeloCheque;
import com.natusfarma.pc.itecvstotvs.model.ModeloContasPagar;
import com.natusfarma.pc.itecvstotvs.util.ConverterTipo;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class RowMapperCheque {

    public ModeloCheque modeloPadrao(ResultSet rs) {
        ModeloCheque modeloCheque = new ModeloCheque();
        try{
            LocalDate dataEmissao = ConverterTipo.sqlDateToLocalDate(rs.getDate("EMISSAO"));
            LocalDate dataVencimento = ConverterTipo.sqlDateToLocalDate(rs.getDate("VENCIMENTO"));
            modeloCheque.setFilial(rs.getInt("FILIAL"));
            modeloCheque.setNumero(rs.getInt("NUMERO"));
            modeloCheque.setDataEmissao(dataEmissao);
            modeloCheque.setDataVencimento(dataVencimento);
            modeloCheque.setValor(rs.getDouble("VALOR"));
            modeloCheque.setCupom(rs.getInt("CUPOM"));
            modeloCheque.setCaixa(rs.getInt("CAIXA"));
            modeloCheque.setSaldo(rs.getDouble("SALDO"));
            modeloCheque.setBanco(rs.getInt("BANCO"));
            modeloCheque.setAgencia(rs.getString("AGENCIA").trim());
            modeloCheque.setCodCliente(rs.getInt("CODCLIENTE"));
            modeloCheque.setNome(rs.getString("NOME"));
            modeloCheque.setCgcCpf(rs.getString("CGC_CPF").trim());
        }catch (SQLException e){
            throw new ConversaoNaoEncontradaException(e.getMessage(), e.getCause());
        }
        return modeloCheque;
    }


}

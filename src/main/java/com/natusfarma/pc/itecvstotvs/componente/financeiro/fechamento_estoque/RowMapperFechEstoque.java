package com.natusfarma.pc.itecvstotvs.componente.financeiro.fechamento_estoque;

import com.natusfarma.pc.itecvstotvs.exception.ConversaoNaoEncontradaException;
import com.natusfarma.pc.itecvstotvs.model.ModeloContasPagar;
import com.natusfarma.pc.itecvstotvs.model.ModeloFechEstoque;
import com.natusfarma.pc.itecvstotvs.util.ConverterTipo;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class RowMapperFechEstoque {

    public ModeloFechEstoque modeloPadrao(ResultSet rs) {
        ModeloFechEstoque modeloFechEstoque = new ModeloFechEstoque();
        try{
            modeloFechEstoque.setFilial(rs.getInt("FILIAL"));
            modeloFechEstoque.setTotalItens(rs.getLong("QTDE_ITENS"));
            modeloFechEstoque.setTotalCusto(rs.getDouble("CUSTO"));
            modeloFechEstoque.setDataReferencia(rs.getString("DT_REF"));
            //LocalDate data = ConverterTipo.sqlDateToLocalDate(rs.getDate("DT_VENCIMENTO"));
            //modeloFechEstoque.setDataVencimento(data);
        }catch (SQLException e){
            throw new ConversaoNaoEncontradaException(e.getMessage(), e.getCause());
        }
        return modeloFechEstoque;
    }

}

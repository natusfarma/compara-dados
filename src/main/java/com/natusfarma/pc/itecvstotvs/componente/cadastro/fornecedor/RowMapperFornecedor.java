package com.natusfarma.pc.itecvstotvs.componente.cadastro.fornecedor;

import com.natusfarma.pc.itecvstotvs.exception.ConversaoNaoEncontradaException;
import com.natusfarma.pc.itecvstotvs.model.ModeloFornecedor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RowMapperFornecedor {

    public ModeloFornecedor modeloPadrao(ResultSet rs) {
        ModeloFornecedor modeloFornecedor = new ModeloFornecedor();
        try{
            modeloFornecedor.setCodigo(rs.getInt("CODIGO"));
            modeloFornecedor.setNome(rs.getString("NOME").trim());
            modeloFornecedor.setCnpjCpf(rs.getString("CGC_CPF").trim());
            modeloFornecedor.setInscricao(rs.getString("INSC_EST").trim());
            modeloFornecedor.setTipoFOrJ(rs.getString("TIPO"));
            modeloFornecedor.setUf(rs.getString("UF"));
        }catch (SQLException e){
            throw new ConversaoNaoEncontradaException(e.getMessage(), e.getCause());
        }
        return modeloFornecedor;
    }

}

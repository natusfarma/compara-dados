package com.natusfarma.pc.itecvstotvs.componente.cadastro.cliente;

import com.natusfarma.pc.itecvstotvs.exception.ConversaoNaoEncontradaException;
import com.natusfarma.pc.itecvstotvs.model.ModeloClientes;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RowMapperClientes {

    public ModeloClientes modeloPadrao(ResultSet rs) {
        ModeloClientes modeloClientes = new ModeloClientes();
        try{
            modeloClientes.setCodigo(rs.getInt("CODIGO"));
            modeloClientes.setNome(rs.getString("NOME").trim());
            modeloClientes.setCnpgCpf(rs.getString("CGC_CPF").trim());
            modeloClientes.setInscricao(rs.getString("INSC_EST").trim());
            modeloClientes.setTipoFOrJ(rs.getString("TIPO"));
            modeloClientes.setUf(rs.getString("UF"));
        }catch (SQLException e){
            throw new ConversaoNaoEncontradaException(e.getMessage(), e.getCause());
        }
        return modeloClientes;
    }


}

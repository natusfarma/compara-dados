package com.natusfarma.pc.itecvstotvs.componente.cadastro.produto;

import com.natusfarma.pc.itecvstotvs.exception.ConversaoNaoEncontradaException;
import com.natusfarma.pc.itecvstotvs.model.ModeloFornecedor;
import com.natusfarma.pc.itecvstotvs.model.ModeloProduto;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RowMapperProduto {

    public ModeloProduto modeloPadrao(ResultSet rs) {
        ModeloProduto modeloProduto = new ModeloProduto();
        try{
            modeloProduto.setCodigo(rs.getInt("CODIGO"));
            modeloProduto.setDescricao(rs.getString("DESCRICAO").trim());
            modeloProduto.setNcm(rs.getString("NCM").trim());
            modeloProduto.setBarras(rs.getString("BARRAS").trim());
            modeloProduto.setAtivo(rs.getString("ATIVO").trim());
        }catch (SQLException e){
            throw new ConversaoNaoEncontradaException(e.getMessage(), e.getCause());
        }
        return modeloProduto;
    }

}

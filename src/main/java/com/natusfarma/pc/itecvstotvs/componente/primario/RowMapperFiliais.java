package com.natusfarma.pc.itecvstotvs.componente.primario;

import com.natusfarma.pc.itecvstotvs.exception.ConversaoNaoEncontradaException;
import com.natusfarma.pc.itecvstotvs.model.ModeloFilial;
import com.natusfarma.pc.itecvstotvs.model.ModeloPadrao;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RowMapperFiliais {

    public ModeloFilial modeloFilial(ResultSet rs) {
        ModeloFilial modeloFilial = new ModeloFilial();
        try{
            modeloFilial.setFilial(rs.getInt("FILIAL"));
            modeloFilial.setNome(rs.getString("NOME"));
        }catch (SQLException e){
            throw new ConversaoNaoEncontradaException(e.getMessage(), e.getCause());
        }
        return modeloFilial;
    }
}

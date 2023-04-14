package com.natusfarma.pc.itecvstotvs.componente;

import com.natusfarma.pc.itecvstotvs.exception.ConversaoNaoEncontradaException;
import com.natusfarma.pc.itecvstotvs.model.ModeloFilial;
import com.natusfarma.pc.itecvstotvs.model.ModeloPadrao;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class RowMapperPadrao {

    public ModeloPadrao modeloPadrao(ResultSet rs) {
        ModeloPadrao modeloPadrao = new ModeloPadrao();
        try{
            modeloPadrao.setCodFornecedor(rs.getLong("CODFORN"));
            modeloPadrao.setTitulo(rs.getString("TITULO").trim());
            modeloPadrao.setNomeFornecedor(rs.getString("NMFORN"));
            modeloPadrao.setParcela(rs.getInt("PARCELA"));
            modeloPadrao.setNumNota(rs.getInt("NUM_NOTA"));
            modeloPadrao.setValor(rs.getDouble("VALOR"));
            modeloPadrao.setFilial(rs.getInt("FILIAL"));
            LocalDate data = converterData(rs.getDate("DT_VENCIMENTO"));
            modeloPadrao.setDataVencimento(data);
        }catch (SQLException e){
            throw new ConversaoNaoEncontradaException(e.getMessage(), e.getCause());
        }
        return modeloPadrao;
    }

    /**
     * método é utilizado para converter uma data String que vem do banco de dados.
     * @param dataStr
     * @return
     */
    @Deprecated
    private LocalDate converterData(String dataStr) {
        if (dataStr.length() > 8){
            return LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
        }else{
            return LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("yyyyMMdd"));
        }
    }


    /**
     * Método é utilizado para converter a sql.Date para LocalDate lembrando que o campo data não pode ser Varchar
     * convertendo a coluna varchar em String no sql CONVERT(DATE, '20230403', 103)  AS DT_VENCIMENTO,
     * @param data
     * @return
     */
    private LocalDate converterData(java.sql.Date data) {
        return data.toLocalDate();
    }
}

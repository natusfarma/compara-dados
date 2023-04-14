package com.natusfarma.pc.itecvstotvs.componente;

import com.natusfarma.pc.itecvstotvs.util.FileStringUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Component
public abstract class Databases<T> {


    public String lerArquivoConsulta(File file, String[] args){
        FileStringUtil fileStringUtil = new FileStringUtil();
        StringBuilder sb = new StringBuilder();
        sb.append(fileStringUtil.FileToString(file));
        for (String arg: args) {
            sb.append(arg);
            sb.append(" ");
        }
        return sb.toString();
    }

    public String lerArquivoConsulta(File file, String arg){
        return lerArquivoConsulta(file, new String[] {arg});
    }

    public String lerArquivoConsulta(File file){
        return lerArquivoConsulta(file, "");
    }

    public String montaCondicaoPeriodo(String coluna, LocalDate inicio, LocalDate fim){
        String dataInicial = inicio.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String dataFinal = fim.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String condicaoPeriodo = String.format(" AND %s BETWEEN '%s' AND '%s'", coluna, dataInicial, dataFinal);
        return condicaoPeriodo;
    }

    public List<Map<String, Object>> consulta(String query, JdbcTemplate jdbcTemplate){
        return jdbcTemplate.queryForList(query);
    }

    public List<T> consulta(String query, JdbcTemplate jdbcTemplate, RowMapper<T> rowMapper){
        return jdbcTemplate.query(query, rowMapper);
    }

//    public ModeloPadrao modeloPadrao(ResultSet rs) {
//        ModeloPadrao modeloPadrao = new ModeloPadrao();
//        try{
//            modeloPadrao.setCodFornecedor(rs.getLong("CODFORN"));
//            modeloPadrao.setTitulo(rs.getString("TITULO").trim());
//            modeloPadrao.setNomeFornecedor(rs.getString("NMFORN"));
//            modeloPadrao.setParcela(rs.getInt("PARCELA"));
//            modeloPadrao.setNumNota(rs.getInt("NUM_NOTA"));
//            modeloPadrao.setValor(rs.getDouble("VALOR"));
//            modeloPadrao.setFilial(rs.getInt("FILIAL"));
//            LocalDate data = converterData(rs.getDate("DT_VENCIMENTO"));
//            modeloPadrao.setDataVencimento(data);
//        }catch (SQLException e){
//            throw new ConversaoNaoEncontradaException(e.getMessage(), e.getCause());
//        }
//        return modeloPadrao;
//    }
//
//    /**
//     * método é utilizado para converter uma data String que vem do banco de dados.
//     * @param dataStr
//     * @return
//     */
//    @Deprecated
//    private LocalDate converterData(String dataStr) {
//        if (dataStr.length() > 8){
//            return LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
//        }else{
//            return LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("yyyyMMdd"));
//        }
//    }
//
//
//    /**
//     * Método é utilizado para converter a sql.Date para LocalDate lembrando que o campo data não pode ser Varchar
//     * convertendo a coluna varchar em String no sql CONVERT(DATE, '20230403', 103)  AS DT_VENCIMENTO,
//     * @param data
//     * @return
//     */
//    private LocalDate converterData(java.sql.Date data) {
//        return data.toLocalDate();
//    }




}

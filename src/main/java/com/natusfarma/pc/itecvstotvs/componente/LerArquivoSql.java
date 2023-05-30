package com.natusfarma.pc.itecvstotvs.componente;

import com.natusfarma.pc.itecvstotvs.util.FileStringUtil;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class LerArquivoSql {

    public String lerArquivoConsulta(File file, String[] args1, String[] args2){
        return concatenarQueryUnion(FileStringUtil.FileToString(file), args1, args2);
    }

    public String lerArquivoConsulta(File file, String arg1, String arg2){
        return lerArquivoConsulta(file, new String[] {arg1}, new String[] {arg2});
    }

    public String lerArquivoConsulta(File file, String[] args){
        return concatenarQueryGroup(FileStringUtil.FileToString(file), args);
    }

    public String lerArquivoConsulta(File file, String arg){
        return lerArquivoConsulta(file, new String[] {arg});
    }

    public String lerArquivoConsulta(File file){
        return lerArquivoConsulta(file, "", "");
    }

    public String montaCondicaoPeriodo(String coluna, LocalDate inicio, LocalDate fim) {
        String dataInicial = inicio.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String dataFinal = fim.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String condicaoPeriodo = String.format("AND %s BETWEEN '%s' AND '%s'", coluna, dataInicial, dataFinal);
        return condicaoPeriodo;
    }

    private int contemValor(String result, String str){
        return result.toUpperCase().indexOf(str);
    }

    private int contemUnion(String result){
        return contemValor(result, "UNION");
    }

    private int contemGroupBy(String result){
        return contemValor(result, "GROUP BY");
    }

    private String concatenarQuery(String query, String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append(query);
        for (String arg: args) {
            sb.append(" ");
            sb.append(arg);
            sb.append(" ");
        }
        return sb.toString();
    }

    private String concatenarQueryUnion(String query, String[] args1, String[] args2) {
        StringBuilder sb = new StringBuilder();
        String[] queryUnion = tratarQueryUnion(query);
        if (queryUnion != null){
            sb.append(concatenarQueryGroup(queryUnion[0],args1));
            sb.append(concatenarQueryGroup(queryUnion[1],args2));
        }else{
            sb.append(concatenarQueryGroup(query,args1));
        }
        return sb.toString();
    }

    private String concatenarQueryGroup(String query, String[] args) {
        StringBuilder sb = new StringBuilder();
        String[] queryGroup = tratarQueryGroup(query);
        if (queryGroup != null){
            sb.append(concatenarQuery(queryGroup[0], args));
            sb.append(queryGroup[1]);
        }else{
            sb.append(concatenarQuery(query, args));
        }
        return sb.toString();
    }

    private String[] tratarQueryUnion(String query){
        int indexUnion = contemUnion(query);
        String[] dualQuery = null;
        if (indexUnion >= 0){
            dualQuery = new String[]{
                    query.substring(0, indexUnion),
                    query.substring(indexUnion)
            };
        }
        return dualQuery;
    }

    private String[] tratarQueryGroup(String query){
        int indexGroup = contemGroupBy(query);
        String[] querys = null;
        if (indexGroup >= 0) {
            querys = new String[]{
                query.substring(0, indexGroup),
                query.substring(indexGroup)
            };
        }
        return querys;
    }



}

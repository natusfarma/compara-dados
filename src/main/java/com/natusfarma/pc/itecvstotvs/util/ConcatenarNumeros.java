package com.natusfarma.pc.itecvstotvs.util;

public abstract class ConcatenarNumeros {

    public static String concatenar(String[] valor){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < valor.length; i++){
            if (i != 0) sb.append(",");
            sb.append(valor[i]);
        }
        return sb.toString();
    }
}

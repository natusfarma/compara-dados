package com.natusfarma.pc.itecvstotvs.util;

public abstract class  ConcatenarString {

    /**
     * Métodos utilizado para retornar o valor com aspas simples
     * ex: valor[] {"aaa","bb","c"} quantidade = 5 return 'aaa', 'bb' , 'c'
     * @param valor
     * @return
     */
    public static String concatenar(String[] valor){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < valor.length; i++){
            if (i != 0) sb.append(",");
            sb.append("'");
            sb.append(valor[i]);
            sb.append("'");
        }
        return sb.toString();
    }

    /**
     * Métodos utilizado para retornar o valor com aspas simples e completar com zero a esquerda
     * ex: valor[] {"aaa","bb","c"} quantidade = 5 return '00aaa', '000bb' , '0000c'
     * @param valor
     * @param quantidade
     * @return
     */
    public static String concatenar(String[] valor, int quantidade){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < valor.length; i++){
            if (i != 0) sb.append(",");
            sb.append("'");
            sb.append(zeroEsquerda(valor[i], quantidade));
            sb.append("'");
        }
        return sb.toString();
    }

    /**
     * Método que completa zeros a esqueda ex: valor = "10" tamanho = 5 return = 00010
     * @param valor
     * @param tamanho
     * @return
     */
    private static String zeroEsquerda(String valor, int tamanho){
        StringBuilder sb = new StringBuilder();
        for (int i = valor.length(); i < tamanho; i++){
            sb.append("0");
        }
        sb.append(valor);
        return sb.toString();
    }

    /**
     * Método que retorna o caracter informado e a quantidade de vezes ex: valor = ";" quantidade = 5 return = ;;;;;
     * @param valor
     * @param quantidade
     * @return
     */
    public static String concatenar(String valor , int quantidade){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < quantidade; i++){
            sb.append(valor);
        }
        return sb.toString();
    }
}

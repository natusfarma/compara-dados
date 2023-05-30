package com.natusfarma.pc.itecvstotvs.util;

public abstract class DiretorioArquivos {

    private static final String PADRAO = "consultas/";
    public static final String CONTAS_A_PAGAR = PADRAO +"financeiro/contaspagar/";
    public static final String CLIENTES = PADRAO +"cadastros/cliente/";
    public static final String FORNECEDORES = PADRAO +"cadastros/fornecedor/";
    public static final String PRODUTOS = PADRAO +"cadastros/produto/";

    public static final String FILIAL = PADRAO +"filial/";
    public static final String CHEQUE = PADRAO + "financeiro/cheque/";
    public static final String CREDIARIO = PADRAO + "financeiro/crediario/";
    public static final String NOTAFISCAL_ENTRADA = PADRAO + "notasfiscais/entrada/";
    public static final String NOTAFISCAL_SAIDA = PADRAO + "notasfiscais/saida/";
    public static final String NOTAFISCAL_NFCE = PADRAO + "notasfiscais/nfce/";


}

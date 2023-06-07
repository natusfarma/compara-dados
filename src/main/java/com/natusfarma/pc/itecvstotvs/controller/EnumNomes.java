package com.natusfarma.pc.itecvstotvs.controller;

public enum EnumNomes {

    EMISSAO("Data de Emissão"),
    VENCIMENTO("Data do Vencimento"),
    CANCELADO("Data de Cancelamento"),
    MES_ANO_REF("Mês e Ano de Referência"),
    ENTRADA("Data da Entrada"),
    CNPJ_CPF("Cpf/Cnpj"),
    IDS("Código");

    private String value;
    EnumNomes(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

package com.natusfarma.pc.itecvstotvs.controller;

public enum TipoNomes {

    EMISSAO("Data de Emissão"),
    VENCIMENTO("Data de Validade"),
    CNPJ_CPF("Cnpj/Cpf"),
    IDS("Códigos");

    private String value;
    TipoNomes(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

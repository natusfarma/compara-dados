package com.natusfarma.pc.itecvstotvs.controller;

public enum EnumGrupos {

    NOTAFISCAL("Nota Fiscal"),
    CADASTRO("Cadastro"),
    FINANCEIRO("Financeiro");

    private String value;
    EnumGrupos(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

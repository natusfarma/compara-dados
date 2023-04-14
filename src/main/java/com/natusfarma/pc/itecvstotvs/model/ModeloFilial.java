package com.natusfarma.pc.itecvstotvs.model;

public class ModeloFilial {

    private int filial;
    private String nome;

    public int getFilial() {
        return filial;
    }

    public void setFilial(int filial) {
        this.filial = filial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "ModeloFilial{" +
                "filial=" + filial +
                ", nome='" + nome + '\'' +
                '}';
    }
}

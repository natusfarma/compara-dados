package com.natusfarma.pc.itecvstotvs.model;

import java.util.Arrays;

public class ModeloUrlMetodo {

    private String nome;
    private String url;
    private String[] tipos;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String[] getTipos() {
        return tipos;
    }

    public void setTipos(String[] tipos) {
        this.tipos = tipos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "ModeloUrlMetodo{" +
                "url='" + url + '\'' +
                ", tipos=" + Arrays.toString(tipos) +
                ", nome='" + nome + '\'' +
                '}';
    }
}

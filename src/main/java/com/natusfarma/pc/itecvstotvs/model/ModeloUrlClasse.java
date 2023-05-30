package com.natusfarma.pc.itecvstotvs.model;

import java.util.List;

public class ModeloUrlClasse {

    private String nome;
    private String grupo;
    private String urlClass;
    private List<ModeloUrlMetodo> modeloUrlMetodos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getUrlClass() {
        return urlClass;
    }

    public void setUrlClass(String urlClass) {
        this.urlClass = urlClass;
    }

    public List<ModeloUrlMetodo> getModeloUrlMetodos() {
        return modeloUrlMetodos;
    }

    public void setModeloUrlMetodos(List<ModeloUrlMetodo> modeloUrlMetodos) {
        this.modeloUrlMetodos = modeloUrlMetodos;
    }

    @Override
    public String toString() {
        return "ModeloUrlClasse{" +
                "nome='" + nome + '\'' +
                ", grupo='" + grupo + '\'' +
                ", urlClass='" + urlClass + '\'' +
                ", modeloUrlMetodos=" + modeloUrlMetodos +
                '}';
    }
}

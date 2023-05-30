package com.natusfarma.pc.itecvstotvs.model;

import java.util.List;

public class ModeloListas<T> {

    private ModeloTotais modeloTotais;
    private List<ModeloTipo<T>> listaNaoEncontrada;
    private List<ModeloTipo<T>> listaSecundariaNaoEncontrada;
    private List<ModeloTipo<T>> listaIguais;

    public ModeloListas(List<ModeloTipo<T>> listaIguais, List<ModeloTipo<T>> listaNaoEncontrada, List<ModeloTipo<T>> listaSecundariaNaoEncontrada) {
        this.listaIguais = listaIguais;
        this.listaNaoEncontrada = listaNaoEncontrada;
        this.listaSecundariaNaoEncontrada = listaSecundariaNaoEncontrada;
        this.modeloTotais = getModeloTotaisTipo();
    }

    private ModeloTotais getModeloTotaisTipo() {
        ModeloTotais modeloTotais = new ModeloTotais();
        modeloTotais.setIgual(listaIguais.size());
        modeloTotais.setNaoEncontrado(listaNaoEncontrada.size());
        modeloTotais.setNaoEncontradoSecundario(listaSecundariaNaoEncontrada.size());
        return modeloTotais;
    }

    public List<ModeloTipo<T>> getListaIguais() {
        return listaIguais;
    }

    public List<ModeloTipo<T>> getListaNaoEncontrada() {
        return listaNaoEncontrada;
    }

    public List<ModeloTipo<T>> getListaSecundariaNaoEncontrada() {
        return listaSecundariaNaoEncontrada;
    }

    public ModeloTotais getModeloTotais() {
        return modeloTotais;
    }
}

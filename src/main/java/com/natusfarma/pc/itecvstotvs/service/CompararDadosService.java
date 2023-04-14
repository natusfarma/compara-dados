package com.natusfarma.pc.itecvstotvs.service;

import com.natusfarma.pc.itecvstotvs.componente.CompararDados;
import com.natusfarma.pc.itecvstotvs.model.ModeloPadrao;
import com.natusfarma.pc.itecvstotvs.model.ModeloTotais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CompararDadosService {

    @Autowired
    private CompararDados compararDados;

    private List<ModeloPadrao> listaIguais;
    private List<ModeloPadrao> listaNaoEncontrada;
    private List<ModeloPadrao> listaSecundariaNaoEncontrada;

    public ModeloTotais realizarComparativo(LocalDate dataInicial, LocalDate dataFinal){
        compararDados.processar(dataInicial,dataFinal);
        listaIguais = compararDados.getListaIguais();
        listaNaoEncontrada = compararDados.getListaNaoEncontrada();
        listaSecundariaNaoEncontrada = compararDados.getListaSecundariaNaoEncontrada();
        return getModeloTotais();
    }

    private ModeloTotais getModeloTotais() {
        ModeloTotais modeloTotais = new ModeloTotais();
        modeloTotais.setIgual(listaIguais.size());
        modeloTotais.setNaoEncontrado(listaNaoEncontrada.size());
        modeloTotais.setNaoEncontradoSecundario(listaSecundariaNaoEncontrada.size());
        return modeloTotais;
    }


    public List<ModeloPadrao> getListaIguais() {
        return listaIguais;
    }

    public List<ModeloPadrao> getListaNaoEncontrada() {
        return listaNaoEncontrada;
    }

    public List<ModeloPadrao> getListaSecundariaNaoEncontrada() {
        return listaSecundariaNaoEncontrada;
    }
}

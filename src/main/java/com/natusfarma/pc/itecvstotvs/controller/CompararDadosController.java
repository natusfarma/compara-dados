package com.natusfarma.pc.itecvstotvs.controller;

import com.natusfarma.pc.itecvstotvs.model.ModeloPadrao;
import com.natusfarma.pc.itecvstotvs.model.ModeloTotais;
import com.natusfarma.pc.itecvstotvs.service.CompararDadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/comparar")
public class CompararDadosController {

    @Autowired
    private CompararDadosService compararDadosService;

    @GetMapping("/processar")
    //processar/?ini=2023-04-14&fim=2023-04-15
    public ModeloTotais processar(@RequestParam("ini") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ini,
                                  @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim){
        return compararDadosService.realizarComparativo(ini,fim);
    }

    @GetMapping("/igual")
    public List<ModeloPadrao> listaIguais(){
        return compararDadosService.getListaIguais();
    }

    @GetMapping("/nao-encontrado")
    public List<ModeloPadrao> listaNaoEncotrada(){
        return compararDadosService.getListaNaoEncontrada();
    }

    @GetMapping("/nao-encontrado-secundario")
    public List<ModeloPadrao> listaNaoEncontradaTotvs(){
        return compararDadosService.getListaSecundariaNaoEncontrada();
    }


}

package com.natusfarma.pc.itecvstotvs.controller;

import com.natusfarma.pc.itecvstotvs.model.ModeloFilial;
import com.natusfarma.pc.itecvstotvs.service.FiliaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/filiais")
public class FiliaisController {

    private static final String NOME = "Filiais";
    private static final String GRUPO = "";

    @Autowired
    private FiliaisService filiaisService;

    @GetMapping
    public List<ModeloFilial> buscarTodos(){
        return filiaisService.buscarTodas();
    }

    @GetMapping("/{id}")
    public ModeloFilial buscarPorId(@PathVariable int id){
        return filiaisService.buscarPorId(id);
    }

}

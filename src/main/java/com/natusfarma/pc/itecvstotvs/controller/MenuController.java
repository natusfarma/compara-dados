package com.natusfarma.pc.itecvstotvs.controller;

import com.natusfarma.pc.itecvstotvs.model.ModeloUrlClasse;
import com.natusfarma.pc.itecvstotvs.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<ModeloUrlClasse> menus(){
        return menuService.retornaMenu();
    }

    @GetMapping("/grupos")
    public Set<String> grupos(){
        return menuService.getGrupos();
    }

}

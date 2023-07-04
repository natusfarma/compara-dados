package com.natusfarma.pc.itecvstotvs.controller.financeiro;

import com.natusfarma.pc.itecvstotvs.controller.EnumGrupos;
import com.natusfarma.pc.itecvstotvs.controller.EnumNomes;
import com.natusfarma.pc.itecvstotvs.controller.TipoRetorno;
import com.natusfarma.pc.itecvstotvs.model.ModeloCartoes;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.model.ModeloMidia;
import com.natusfarma.pc.itecvstotvs.service.CartoesService;
import com.natusfarma.pc.itecvstotvs.service.MidiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
@RequestMapping("/financeiro/midia")
public class MidiaController {

    private static final String NOME = "Mídia";
    private static final String GRUPO = EnumGrupos.FINANCEIRO.getValue();

    @Autowired
    private MidiaService midiaService;


    @TipoRetorno(EnumNomes.EMISSAO)
    @GetMapping("/processar")
    //processar/?ini=2023-04-14&fim=2023-04-15
    public ModeloListas<ModeloMidia> processar(@RequestParam("ini") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ini,
                                               @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim){
        return midiaService.processar(ini,fim);
    }

}

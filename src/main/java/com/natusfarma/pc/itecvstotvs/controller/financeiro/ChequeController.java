package com.natusfarma.pc.itecvstotvs.controller.financeiro;

import com.natusfarma.pc.itecvstotvs.controller.TipoNomes;
import com.natusfarma.pc.itecvstotvs.controller.TipoRetorno;
import com.natusfarma.pc.itecvstotvs.model.ModeloCheque;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.service.ChequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/financeiro/cheques")
public class ChequeController {

    private static final String NOME = "Cheque";
    private static final String GRUPO = "Financeiro";

    @Autowired
    private ChequeService chequeService;


    @TipoRetorno(TipoNomes.EMISSAO)
    @GetMapping("/processar")
    //processar/?ini=2023-04-14&fim=2023-04-15
    public ModeloListas<ModeloCheque> processar(@RequestParam("ini") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ini,
                                                @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim){
        return chequeService.processar(ini,fim);
    }

}

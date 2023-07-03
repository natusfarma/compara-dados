package com.natusfarma.pc.itecvstotvs.controller.financeiro;

import com.natusfarma.pc.itecvstotvs.controller.EnumGrupos;
import com.natusfarma.pc.itecvstotvs.controller.EnumNomes;
import com.natusfarma.pc.itecvstotvs.controller.TipoRetorno;
import com.natusfarma.pc.itecvstotvs.model.ModeloContasPagar;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.service.ContasPagarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/financeiro/contaspagar")
public class ContasPagarController {

    private static final String NOME = "Contas a Pagar";
    private static final String GRUPO = EnumGrupos.FINANCEIRO.getValue();

    @Autowired
    private ContasPagarService contasPagarService;

    @TipoRetorno(EnumNomes.VENCIMENTO)
    @GetMapping("/processar")
    //processar/?ini=2023-04-14&fim=2023-04-15
    public ModeloListas<ModeloContasPagar> processar(@RequestParam("ini") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ini,
                                                     @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim){
        return contasPagarService.processar(ini,fim);
    }

    @TipoRetorno(EnumNomes.EMISSAO)
    @GetMapping("/processarEmissao")
    //processar/?ini=2023-04-14&fim=2023-04-15
    public ModeloListas<ModeloContasPagar> processarEmissao(@RequestParam("ini") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ini,
                                                     @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim){
        return contasPagarService.processarEmissao(ini,fim);
    }

}

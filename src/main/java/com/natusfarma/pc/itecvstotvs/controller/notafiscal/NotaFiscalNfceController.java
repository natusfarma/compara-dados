package com.natusfarma.pc.itecvstotvs.controller.notafiscal;

import com.natusfarma.pc.itecvstotvs.controller.TipoNomes;
import com.natusfarma.pc.itecvstotvs.controller.TipoRetorno;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.model.ModeloNotaFiscal;
import com.natusfarma.pc.itecvstotvs.service.NotaFiscalNfceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
@RequestMapping("/notafiscal/nfce")
public class NotaFiscalNfceController {

    private static final String NOME = "NFCE";
    private static final String GRUPO = "Nota Fiscal";
    @Autowired
    private NotaFiscalNfceService compararService;


    @TipoRetorno(TipoNomes.EMISSAO)
    @GetMapping("/processar")
    //processar/?ini=2023-04-14&fim=2023-04-15
    public ModeloListas<ModeloNotaFiscal> processar(@RequestParam("ini") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ini,
                                                    @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim){
        return compararService.processar(ini,fim);
    }

}

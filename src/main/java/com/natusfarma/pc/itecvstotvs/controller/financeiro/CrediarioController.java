package com.natusfarma.pc.itecvstotvs.controller.financeiro;

import com.natusfarma.pc.itecvstotvs.controller.EnumGrupos;
import com.natusfarma.pc.itecvstotvs.controller.EnumNomes;
import com.natusfarma.pc.itecvstotvs.controller.TipoRetorno;
import com.natusfarma.pc.itecvstotvs.model.ModeloCrediario;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.service.CrediarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/financeiro/crediario")
public class CrediarioController {

    private static final String NOME = "Crediário";
    private static final String GRUPO = EnumGrupos.FINANCEIRO.getValue();

    @Autowired
    private CrediarioService crediarioService;

    @TipoRetorno(EnumNomes.EMISSAO)
    @GetMapping("/processar")
    //processar/?ini=2023-04-14&fim=2023-04-15
    public ModeloListas<ModeloCrediario> processar(@RequestParam("ini") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ini,
                                                   @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim){
        return crediarioService.processar(ini,fim);
    }

    @TipoRetorno(EnumNomes.PAGAMENTO)
    @GetMapping("/processar/pagamento")
    public ModeloListas<ModeloCrediario> processarDataPagamento(@RequestParam("ini") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ini,
                                                   @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim){
        return crediarioService.processarDataPagamento(ini,fim);
    }


    @TipoRetorno(EnumNomes.NUMEROS_FILIAIS)
    @GetMapping("/processar/numeros_filais")
    //processar/id/?ids=2,3,53562,80000
    public ModeloListas<ModeloCrediario> processarNumerosFiliais(@RequestParam("numeros") String[] numeros,
                                                      @RequestParam("filiais") String[] filiais){
        return crediarioService.processarNumerosFiliais(numeros, filiais);
    }



}

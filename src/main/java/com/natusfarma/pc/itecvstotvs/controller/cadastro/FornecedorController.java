package com.natusfarma.pc.itecvstotvs.controller.cadastro;

import com.natusfarma.pc.itecvstotvs.controller.TipoNomes;
import com.natusfarma.pc.itecvstotvs.controller.TipoRetorno;
import com.natusfarma.pc.itecvstotvs.model.ModeloFornecedor;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cadastro/fornecedores")
public class FornecedorController {

    private static final String NOME = "Fornecedor";
    private static final String GRUPO = "Cadastro";

    @Autowired
    private FornecedorService fornecedorService;

    @TipoRetorno(TipoNomes.IDS)
    @GetMapping("/processar/id")
    //processar/id/?ids=2,3,53562,80000
    public ModeloListas<ModeloFornecedor> processarIds(@RequestParam("ids") String[] valor){
        return fornecedorService.processarPorIds(valor);
    }

    @TipoRetorno(TipoNomes.CNPJ_CPF)
    @GetMapping("/processar/cgcCpf")
    public ModeloListas<ModeloFornecedor> processarCgcCpf(@RequestParam("ids") String[] valor){
        return fornecedorService.processarPorCgcCpf(valor);
    }



}

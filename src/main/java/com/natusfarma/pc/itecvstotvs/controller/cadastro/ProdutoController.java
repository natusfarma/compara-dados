package com.natusfarma.pc.itecvstotvs.controller.cadastro;

import com.natusfarma.pc.itecvstotvs.controller.EnumGrupos;
import com.natusfarma.pc.itecvstotvs.controller.EnumNomes;
import com.natusfarma.pc.itecvstotvs.controller.TipoRetorno;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.model.ModeloProduto;
import com.natusfarma.pc.itecvstotvs.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cadastro/produtos")
public class ProdutoController {

    private static final String NOME = "Produto";
    private static final String GRUPO = EnumGrupos.CADASTRO.getValue();

    @Autowired
    private ProdutoService compararProduto;

    @TipoRetorno(EnumNomes.IDS)
    @GetMapping("/processar/id")
    //processar/id/?ids=2,3,53562,80000
    public ModeloListas<ModeloProduto> processarIds(@RequestParam("ids") String[] valor){
        return compararProduto.processarPorIds(valor);
    }



}

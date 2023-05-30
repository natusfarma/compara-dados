package com.natusfarma.pc.itecvstotvs.controller.cadastro;

import com.natusfarma.pc.itecvstotvs.controller.TipoNomes;
import com.natusfarma.pc.itecvstotvs.controller.TipoRetorno;
import com.natusfarma.pc.itecvstotvs.model.ModeloClientes;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cadastro/clientes")
public class ClientesController {
    private static final String NOME = "Clientes";
    private static final String GRUPO = "Cadastro";

    @Autowired
    private ClientesService compararClientes;


    @TipoRetorno(TipoNomes.IDS)
    @GetMapping("/processar/id")
    //processar/id/?ids=2,3,53562,80000
    public ModeloListas<ModeloClientes> processarIds(@RequestParam("ids") String[] valor){
        return compararClientes.processarPorIds(valor);
    }

    @TipoRetorno(TipoNomes.CNPJ_CPF)
    @GetMapping("/processar/cgcCpf")
    public ModeloListas<ModeloClientes> processarCgcCpf(@RequestParam("ids") String[] valor){
        return compararClientes.processarPorCgcCpf(valor);
    }

}

package com.natusfarma.pc.itecvstotvs.controller.cadastro;

import com.natusfarma.pc.itecvstotvs.controller.EnumGrupos;
import com.natusfarma.pc.itecvstotvs.controller.EnumNomes;
import com.natusfarma.pc.itecvstotvs.controller.TipoRetorno;
import com.natusfarma.pc.itecvstotvs.model.ModeloClientes;
import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cadastro/clientes")
public class ClientesController {
    private static final String NOME = "Cliente";
    private static final String GRUPO = EnumGrupos.CADASTRO.getValue();

    @Autowired
    private ClientesService compararClientes;


    @TipoRetorno(EnumNomes.IDS)
    @GetMapping("/processar/id")
    //processar/id/?ids=2,3,53562,80000
    public ModeloListas<ModeloClientes> processarIds(@RequestParam("ids") String[] valor){
        return compararClientes.processarPorIds(valor);
    }

    @TipoRetorno(EnumNomes.CNPJ_CPF)
    @GetMapping("/processar/cgcCpf")
    public ModeloListas<ModeloClientes> processarCgcCpf(@RequestParam("ids") String[] valor){
        return compararClientes.processarPorCgcCpf(valor);
    }

}

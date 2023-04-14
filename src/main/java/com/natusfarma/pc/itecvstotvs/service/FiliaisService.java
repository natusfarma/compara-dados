package com.natusfarma.pc.itecvstotvs.service;

import com.natusfarma.pc.itecvstotvs.componente.primario.DatabasePrimarioFilial;
import com.natusfarma.pc.itecvstotvs.model.ModeloFilial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiliaisService {

    @Autowired
    private DatabasePrimarioFilial filiais;

    public List<ModeloFilial> buscarTodas(){
        return filiais.processar();
    }

    public ModeloFilial buscarPorId(int id){
        return filiais.buscarPorId(id);
    }


}

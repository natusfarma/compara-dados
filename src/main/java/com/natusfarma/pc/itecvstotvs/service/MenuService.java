package com.natusfarma.pc.itecvstotvs.service;

import com.natusfarma.pc.itecvstotvs.componente.MapClass;
import com.natusfarma.pc.itecvstotvs.model.ModeloUrlClasse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MenuService extends MapClass {

    public List<ModeloUrlClasse> retornaMenu(){
        List<ModeloUrlClasse> urlClasses = new ArrayList<>();
        //grupos = new ArrayList<>();
        List<String> pacotes = nomesDePacotes();
        for (String pacote: pacotes) {
            //grupos.add(grupo(pacote));
            for (Class<?> controllerClass : nomeDaClasses(pacote)) {
                urlClasses.add(criarModelo(controllerClass));
            }
        }
        return urlClasses;
    }


//    private String grupo(String pacote){
//        String ch = ".";
//        int lastIndex = pacote.lastIndexOf(ch);
//        String grupo = pacote.substring(lastIndex+ch.length());
//        return grupo;
//    }

    private ModeloUrlClasse criarModelo(Class<?> controllerClass){
        ModeloUrlClasse modeloUrlClasse = new ModeloUrlClasse();
        modeloUrlClasse.setNome(valorAtributoNome(controllerClass));
        modeloUrlClasse.setGrupo(valorAtributoGrupo(controllerClass));
        modeloUrlClasse.setUrlClass(valueRequestMapping(controllerClass));
        modeloUrlClasse.setModeloUrlMetodos(valueGetMapping(controllerClass));
        return modeloUrlClasse;
    }

    public Set<String> getGrupos() {
        return  retornaMenu().stream()
                .map(e -> e.getGrupo())
                .collect(Collectors.toSet());
    }
}

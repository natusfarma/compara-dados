package com.natusfarma.pc.itecvstotvs.componente;

import com.natusfarma.pc.itecvstotvs.controller.TipoRetorno;
import com.natusfarma.pc.itecvstotvs.model.ModeloCheque;
import com.natusfarma.pc.itecvstotvs.model.ModeloNdf;
import com.natusfarma.pc.itecvstotvs.model.ModeloUrlMetodo;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

@Component
public abstract class MapClass {

    private String nomePacotePai = "com.natusfarma.pc.itecvstotvs.controller";

    public List<String> nomesDePacotes(){
        List<String> childPackages = new ArrayList<>();

        Package[] packages = Package.getPackages();
        for (Package pkg : packages) {
            String pacote = pkg.getName();
            if (pacote.startsWith(nomePacotePai + ".")) {
                childPackages.add(pacote);
            }
        }

        return childPackages;
    }

    public Set<Class<?>> nomeDaClasses(String packageName) {
        Reflections reflections = new Reflections(packageName);

        //retorna o nome das classes que contem a anotação @RestController
        return reflections.getTypesAnnotatedWith(RestController.class);
    }


    public String valueRequestMapping(Class<?> controllerClass){
        RequestMapping requestMapping = controllerClass.getAnnotation(RequestMapping.class);

        //retorna o nome do mapeamento da classe que contem a anotação @RequestMapping
        String[] values = requestMapping.value();
        return String.join(", ", values);
    }

    public String valorAtributoNome(Class<?> controllerClass){
        return valorAtributo(controllerClass, "NOME");
    }

    public String valorAtributoGrupo(Class<?> controllerClass){
        return valorAtributo(controllerClass, "GRUPO");
    }

    private String valorAtributo(Class<?> controllerClass, String nomeAtributo){
        Field field = null;
        try {
            //obtem o nome do atribudo passado como parâmetro.
            field = controllerClass.getDeclaredField(nomeAtributo);
            if (field == null){
                System.out.println("nulo");
            }
            field.setAccessible(true);
            Object value = field.get(null);
            return String.valueOf(value);
        } catch (NoSuchFieldException | IllegalAccessException e) {

        }
        return "";
    }

    public List<ModeloUrlMetodo>  valueGetMapping(Class<?> controllerClass){

        // Obtenha o método desejado dentro do controlador
        Method[] methods = controllerClass.getMethods();
        List<ModeloUrlMetodo> stringList =  new ArrayList<>();
        for (Method method : methods) {
            // Verifique se o método possui a anotação @RequestMapping
            if (method != null && method.isAnnotationPresent(GetMapping.class)) {
                GetMapping getMapping = method.getAnnotation(GetMapping.class);
                // Obtenha o valor do @GetMapping
                String[] values = getMapping.value();
                ModeloUrlMetodo modeloUrlMetodo = new ModeloUrlMetodo();
                modeloUrlMetodo.setUrl(String.join(", ", values));
                modeloUrlMetodo.setTipos(retornaTypos(method));
                modeloUrlMetodo.setNome(TipoRetorno(method));
                modeloUrlMetodo.setParametros(TipoParametro(method));
                stringList.add(modeloUrlMetodo);
            }

        }
        return stringList;
    }

    public static int totalDeAtributos(Class<?> controllerClass){
        Field[] fields = controllerClass.getDeclaredFields();
        return fields.length;
    }


    private String TipoRetorno(Method method){
        if (!method.isAnnotationPresent(TipoRetorno.class)) return "SEM NOME";
        TipoRetorno tipoRetorno = method.getAnnotation(TipoRetorno.class);
        String value = tipoRetorno.value().getValue();
        return value;
    }

    private String[] TipoParametro(Method method){
        Parameter[] parameters = method.getParameters();
        String[] par = new String[parameters.length];
        for(int i = 0; i < parameters.length; i++){
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null){
                par[i] = requestParam.value();
            }
        }
        return par;
    }

    private String[] retornaTypos(Method method) {
        int totalParametros = method.getParameters().length;
        String[] typos = new String[totalParametros];
        for(int i = 0; i < totalParametros; i++){
            Parameter p = method.getParameters()[i];
            typos[i] = analisarTypo(p.getType().getTypeName());
        }
        return typos;
    }


    private String analisarTypo(String  typeName){
        String valor = typeName;
        switch (valor){
            case "java.time.LocalDate": valor = "date"; break;
            case "java.lang.String[]": valor = "text"; break;
            case "java.lang.String": valor = "text"; break;
            case "int": valor = "number"; break;
        }
        return valor;
    }


}

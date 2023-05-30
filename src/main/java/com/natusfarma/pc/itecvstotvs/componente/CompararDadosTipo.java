package com.natusfarma.pc.itecvstotvs.componente;

import com.natusfarma.pc.itecvstotvs.model.ModeloListas;
import com.natusfarma.pc.itecvstotvs.model.ModeloTipo;
import com.natusfarma.pc.itecvstotvs.util.ConcatenarString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public abstract class CompararDadosTipo<T> implements QuantidadeColuna {

    @Autowired
    private CsvFileWriter csvFileWriter;
    private List<ModeloTipo<T>> listaIguais;
    private List<ModeloTipo<T>> listaNaoEncontrada;
    private List<ModeloTipo<T>> listaSecundariaNaoEncontrada;
    protected LocalDate dataInicio;
    protected LocalDate dataFinal;
    private Comparator<T> comparator;
    private ModeloListas<T> modeloListas;


    protected void comparaValor(List<T> listaBancoPrimario, List<T> listaBancoSecundario) {
        //ordenando as listas
        List<T> listaOrdenadaPrimario = ordenarLista(listaBancoPrimario);
        List<T> listaOrdenadaSecundario = ordenarLista(listaBancoSecundario);

        compararValores(listaOrdenadaPrimario,listaOrdenadaSecundario);
    }

    private List<T> ordenarLista(List<T> listaBanco) {
        if (comparator == null){
            return listaBanco.stream()
                    .collect(Collectors.toList());
        }
        return listaBanco.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    /**
     * Método onde realiza a comparação dos dados dos dois bancos de dados.
     * @param bancoPrimario
     * @param bancoSecundario
     */
    private void compararValores(List<T> bancoPrimario,
                                 List<T> bancoSecundario) {

        boolean encontrado = false;
        inicializarListas();

        System.out.println("ITEC  "+bancoPrimario.size());
        System.out.println("TOTVS "+bancoSecundario.size());

        for (T modeloPrimario : bancoPrimario) {
            encontrado = false;
            for (T modeloSecundario : bancoSecundario) {
                if (isComparacao(modeloPrimario, modeloSecundario)) {
                    addIguais(modeloPrimario, modeloSecundario);
                    encontrado = true;
                    //System.out.println("ITEC; "+modeloPrimario+" TOTVS; "+modeloSecundario);
                    bancoSecundario.remove(modeloSecundario);
                    break;
                }
            }
            if (!encontrado) {
                //System.out.println("ITEC; "+modeloPrimario+" TOTVS; ");
                addNaoEncontrada(modeloPrimario);
            }
        }
        preencheNovaListaSecundariaTipo(bancoSecundario);
        modeloListas = criaModeloListas();
    }

    private void inicializarListas() {
        listaIguais = new ArrayList<>();
        listaNaoEncontrada = new ArrayList<>();
        listaSecundariaNaoEncontrada = new ArrayList<>();
    }


    private void preencheNovaListaSecundariaTipo(List<T> bancoSecundario){
        addSecundariaNaoEncontrada(bancoSecundario
                .stream()
                .map(e -> criarTipo(null, e))
                .collect(Collectors.toList()));
    }


    /**
     * realiza a comparação dos valores do banco de dados 1 e banco de dados 2
     * compara com titulo, código fornecedor , parcela e data de vencimento.
     * @param bancoPrimario
     * @param bancoSecundario
     * @return
     */
    private boolean isComparacao(T bancoPrimario, T bancoSecundario) {
        return bancoPrimario.equals(bancoSecundario);
    }

    private ModeloTipo<T> criarTipo(T bancoPrimario, T bancoSecundario){
        return new ModeloTipo<T>(bancoPrimario, bancoSecundario);
    }

    private void addIguais(T bancoPrimario, T bancoSecundario) {
        listaIguais.add(criarTipo(bancoPrimario, bancoSecundario));
    }

    private void addNaoEncontrada(T bancoPrimario) {
        listaNaoEncontrada.add(criarTipo(bancoPrimario, null));
    }

    private void addSecundariaNaoEncontrada(List<ModeloTipo<T>> listaSecundariaNaoEncontradaModeloTipoLocal) {
        listaSecundariaNaoEncontrada.addAll(listaSecundariaNaoEncontradaModeloTipoLocal);
    }

    private ModeloListas<T> criaModeloListas(){
        return new ModeloListas<T>(
                listaIguais,
                listaNaoEncontrada,
                listaSecundariaNaoEncontrada
        );
    }

    public List<String> concatenarListas(){
        List<String> listas = new ArrayList<>();
        listas.addAll(listaIguais
                .stream()
                .map(e -> "ITEC;"+e.getObj1()+" TOTVS;"+e.getObj2())
                .collect(Collectors.toList()));
        listas.addAll(listaNaoEncontrada
                .stream()
                .map(e -> "ITEC;"+e.getObj1()+" TOTVS;")
                .collect(Collectors.toList()));
        listas.addAll(listaSecundariaNaoEncontrada
                .stream()
                .map(e -> "ITEC;"+ concatenar() + " TOTVS;"+e.getObj2())
                .collect(Collectors.toList()));
        return listas;
    }

    private String concatenar(){
        return ConcatenarString.concatenar(";",quantidadeDeColunaDoTipo());
    }

    public void imprimirListas(){
        System.out.println(cabecalho());
        concatenarListas().stream().forEach(System.out::println);
    }

    public String cabecalho(){
        return  "-------------------------IMPRIMINDO LISTAS-------------------     ------------" + System.lineSeparator() +
                "PERIODO INFORMADO;     "+formatarData(dataInicio) +" e "+formatarData(dataFinal) + System.lineSeparator() +
                "TOTAIS Iguais;         "+listaIguais.size() + System.lineSeparator() +
                "TOTAIS Nao Encontrado; "+listaNaoEncontrada.size() + System.lineSeparator() +
                "TOTAIS Linhas Sobrando;"+listaSecundariaNaoEncontrada.size() + System.lineSeparator() +
                "------------------------------------------------------------------------------"
                ;
    }

    public void gerarArquivoCsv(File file){
        csvFileWriter.setConcatenarArquivo(new String[] {cabecalho()});
        csvFileWriter.gerarArquivo(file, concatenarListas());

    }

    private String formatarData(LocalDate data){
        if (data == null){
            data = LocalDate.now();
        }
        return data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public Comparator<T> getComparator() {
        return comparator;
    }

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public ModeloListas<T> getModeloListas() {
        return modeloListas;
    }
}

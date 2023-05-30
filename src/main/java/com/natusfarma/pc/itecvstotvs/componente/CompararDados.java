package com.natusfarma.pc.itecvstotvs.componente;

import com.natusfarma.pc.itecvstotvs.model.ModeloTipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public abstract class CompararDados<T> implements QuantidadeColuna {

    @Autowired
    private CsvFileWriter csvFileWriter;
    private List<T> listaIguais;
    private List<T> listaNaoEncontrada;
    private List<T> listaSecundariaNaoEncontrada;
    protected LocalDate dataInicio;
    protected LocalDate dataFinal;
    private Comparator<T> comparator;



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

        //listaSecundariaNaoEncontrada.addAll(bancoSecundario);
        listaSecundariaNaoEncontrada = bancoSecundario;
        for (T modeloPrimario : bancoPrimario) {
            encontrado = false;
            for (T modeloSecundario : bancoSecundario) {
                if (isComparacao(modeloPrimario, modeloSecundario)) {
                    listaIguais.add(modeloPrimario);
                    encontrado = true;
                    //System.out.println("ITEC; "+modeloPrimario+" TOTVS; "+modeloSecundario);
                    listaSecundariaNaoEncontrada.remove(modeloSecundario);
                    break;
                }
            }
            if (!encontrado) {
                //System.out.println("ITEC; "+modeloPrimario+" TOTVS; ");
                listaNaoEncontrada.add(modeloPrimario);
            }
        }
    }

    private void inicializarListas() {
        listaIguais = new ArrayList<>();
        listaNaoEncontrada = new ArrayList<>();
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

    public List<String> concatenarListas(){
        List<String> listas = new ArrayList<>();
        listas.addAll(getListaIguais()
                .stream()
                .map(e -> "ITEC;"+e+" TOTVS;"+e)
                .collect(Collectors.toList()));
        listas.addAll(getListaNaoEncontrada()
                .stream()
                .map(e -> "ITEC;"+e+" TOTVS;")
                .collect(Collectors.toList()));
        listas.addAll(getListaSecundariaNaoEncontrada()
                .stream()
                .map(e -> "ITEC;"+concatenar() + " TOTVS;"+e)
                .collect(Collectors.toList()));
        return listas;
    }

    /**
     * Quantidade de colunas para concatenar listas do itec
     * @return
     */
    private String concatenar(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < quantidadeDeColunaDoTipo(); i++){
            sb.append(";");
        }
        return sb.toString();
    }


    public void imprimirListas(){
        System.out.println(cabecalho());
        concatenarListas().stream().forEach(System.out::println);
    }

    public String cabecalho(){
        return  "-------------------------IMPRIMINDO LISTAS-----------------------------------" + System.lineSeparator() +
                "PERIODO INFORMADO;     "+formatarData(dataInicio) +" e "+formatarData(dataFinal) + System.lineSeparator() +
                "TOTAIS Iguais;         "+getListaIguais().size() + System.lineSeparator() +
                "TOTAIS Nao Encontrado; "+getListaNaoEncontrada().size() + System.lineSeparator() +
                "TOTAIS Linhas Sobrando;"+getListaSecundariaNaoEncontrada().size() + System.lineSeparator() +
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

    public List<T> getListaIguais() {
        return listaIguais;
    }

    public List<T> getListaNaoEncontrada() {
        return listaNaoEncontrada;
    }

    public List<T> getListaSecundariaNaoEncontrada() {
        return listaSecundariaNaoEncontrada;
    }


}

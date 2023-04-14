package com.natusfarma.pc.itecvstotvs.componente;

import com.natusfarma.pc.itecvstotvs.componente.primario.DatabasePrimario;
import com.natusfarma.pc.itecvstotvs.componente.secundario.DatabaseSecundario;
import com.natusfarma.pc.itecvstotvs.ordenacao.OrdenarFornTituloFilialParcelaVencimento;
import com.natusfarma.pc.itecvstotvs.model.ModeloPadrao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompararDados {

    @Autowired
    private DatabasePrimario bancoPrimarioService;
    @Autowired
    private DatabaseSecundario bancoSecundarioService;
    @Autowired
    private CsvFileWriter csvFileWriter;

    private List<ModeloPadrao> listaIguais;
    private List<ModeloPadrao> listaNaoEncontrada;
    private List<ModeloPadrao> listaSecundariaNaoEncontrada;
    private LocalDate dataInicio;
    private LocalDate dataFinal;


    /**
     * Método principal onde realiza a regra de negócio.
     * pega a data de início e fim e carrega a informação de cada banco em listas diferentes.
     * depois é ordenada cada lista para melhor desempenho.
     * @param inicio
     * @param fim
     */
    public void processar(LocalDate inicio, LocalDate fim) {
        dataInicio = inicio;
        dataFinal = fim;
        List<ModeloPadrao> listaBancoPrimario = bancoPrimarioService.processar(inicio, fim);
        List<ModeloPadrao> listaBancoSecundario = bancoSecundarioService.processar(inicio, fim);
        comparaValor(listaBancoPrimario, listaBancoSecundario);
    }

    /**
     * Método principal onde realiza a regra de negócio.
     * pega a data de início e fim e mais a filial e carrega a informação de cada banco em listas diferentes.
     * depois é ordenada cada lista para melhor desempenho.
     * @param filialId
     * @param inicio
     * @param fim
     */
    public void processarPorId(int filialId, LocalDate inicio, LocalDate fim) {
        dataInicio = inicio;
        dataFinal = fim;
        List<ModeloPadrao> listaBancoPrimario = bancoPrimarioService.processarPorFilial(filialId, inicio, fim);
        List<ModeloPadrao> listaBancoSecundario = bancoSecundarioService.processarPorFilial(filialId, inicio, fim);
        comparaValor(listaBancoPrimario, listaBancoSecundario);
    }

    private void comparaValor(List<ModeloPadrao> listaBancoPrimario, List<ModeloPadrao> listaBancoSecundario) {
        //ordenando as listas
        List<ModeloPadrao> listaOrdenadaPrimario = ordenarLista(listaBancoPrimario);
        List<ModeloPadrao> listaOrdenadaSecundario = ordenarLista(listaBancoSecundario);

        compararValores(listaOrdenadaPrimario,listaOrdenadaSecundario);
    }

    private List<ModeloPadrao> ordenarLista(List<ModeloPadrao> listaBanco) {
        return listaBanco.stream()
                .sorted(new OrdenarFornTituloFilialParcelaVencimento())
                .collect(Collectors.toList());
    }

    /**
     * Método onde realiza a comparação dos dados dos dois bancos de dados.
     * @param bancoPrimario
     * @param bancoSecundario
     */
    private void compararValores(List<ModeloPadrao> bancoPrimario,
                                 List<ModeloPadrao> bancoSecundario) {

        boolean encontrado = false;
        listaIguais = new ArrayList<>();
        listaNaoEncontrada = new ArrayList<>();

        //listaSecundariaNaoEncontrada.addAll(bancoSecundario);
        listaSecundariaNaoEncontrada = bancoSecundario;
        for (ModeloPadrao modeloPrimario : bancoPrimario) {
            encontrado = false;
            for (ModeloPadrao modeloSecundario : bancoSecundario) {
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

    /**
     * realiza a comparação dos valores do banco de dados 1 e banco de dados 2
     * compara com titulo, código fornecedor , parcela e data de vencimento.
     * @param bancoPrimario
     * @param bancoSecundario
     * @return
     */
    private boolean isComparacao(ModeloPadrao bancoPrimario, ModeloPadrao bancoSecundario) {
        return bancoPrimario.getTitulo().equals(bancoSecundario.getTitulo()) &&
                bancoPrimario.getCodFornecedor().equals(bancoSecundario.getCodFornecedor()) &&
                bancoPrimario.getParcela() == bancoSecundario.getParcela() &&
                bancoPrimario.getFilial() == bancoSecundario.getFilial() &&
                bancoPrimario.getDataVencimento().isEqual(bancoSecundario.getDataVencimento());
    }

    public List<ModeloPadrao> getListaIguais() {
        return listaIguais;
    }

    public List<ModeloPadrao> getListaNaoEncontrada() {
        return listaNaoEncontrada;
    }

    public List<ModeloPadrao> getListaSecundariaNaoEncontrada() {
        return listaSecundariaNaoEncontrada;
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
                .map(e -> "ITEC;;;;;;;;;;" + " TOTVS;"+e)
                .collect(Collectors.toList()));
        return listas;
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
                "TOTAIS Linhas Sobrando;"+getListaSecundariaNaoEncontrada( ).size() + System.lineSeparator() +
                "------------------------------------------------------------------------------"
                ;
    }

    public void gerarArquivoCsv(File file){
        csvFileWriter.setConcatenarArquivo(new String[] {cabecalho()});
        csvFileWriter.gerarArquivo(file, concatenarListas());

    }

    private String formatarData(LocalDate data){
        return data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

}

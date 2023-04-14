package com.natusfarma.pc.itecvstotvs.componente;

import com.natusfarma.pc.itecvstotvs.exception.ArquivoNaoEncontradoException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class CsvFileWriter {

    private FileWriter writer;

    private String[] concatenarArquivo = new String[] {};

    public void gerarArquivo(File csvFile, List<String> lista)  {
        try {
            writer = new FileWriter(csvFile);
            for (String str: lista){
                writer.write(str);
                writer.write(System.lineSeparator());
            }
            for (String str: concatenarArquivo){
                writer.write(str);
                writer.write(System.lineSeparator());
            }
            System.out.printf("O Arquivo %s gerado com sucesso.",csvFile.getName());
        } catch (IOException e) {
            throw new ArquivoNaoEncontradoException(csvFile, e.getCause());
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                throw new ArquivoNaoEncontradoException(csvFile, e.getCause());
            }
        }
    }

    public String[] getConcatenarArquivo() {
        return concatenarArquivo;
    }

    public void setConcatenarArquivo(String[] concatenarArquivo) {
        this.concatenarArquivo = concatenarArquivo;
    }
}

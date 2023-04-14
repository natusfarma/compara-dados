package com.natusfarma.pc.itecvstotvs.model;

import java.time.LocalDate;

public class ModeloPadrao {

    private String titulo;
    private Long codFornecedor;
    private String nomeFornecedor;
    private LocalDate dataVencimento;
    private int parcela;
    private int numNota;
    private double valor;
    private int filial;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getCodFornecedor() {
        return codFornecedor;
    }

    public void setCodFornecedor(Long codFornecedor) {
        this.codFornecedor = codFornecedor;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public int getParcela() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
    }

    public int getNumNota() {
        return numNota;
    }

    public void setNumNota(int numNota) {
        this.numNota = numNota;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getFilial() {
        return filial;
    }

    public void setFilial(int filial) {
        this.filial = filial;
    }

    @Override
    public String toString() {
        return         titulo +
                 ";" + filial +
                 ";" + codFornecedor +
                 ";" + nomeFornecedor +
                 ";" + dataVencimento +
                 ";" + String.valueOf(valor).replace(".",",") +
                 ";" + parcela +
                 ";" + numNota + 
                 ";" ;

    }



}

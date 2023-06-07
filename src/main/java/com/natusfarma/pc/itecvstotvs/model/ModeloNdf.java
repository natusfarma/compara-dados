package com.natusfarma.pc.itecvstotvs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class ModeloNdf {

    private int filial;
    private String titulo;
    private Long codFornecedor;
    private String nome;
    private double valor;
    private int numero;
    private LocalDate dataEmissao;
    @JsonIgnore
    private LocalDate dataVencimento;
    private String hist1;
    private String hist2;
    private String hist3;
    private String filialTotvs;
    private String status;


    public int getFilial() {
        return filial;
    }

    public void setFilial(int filial) {
        this.filial = filial;
    }

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getHist1() {
        return hist1;
    }

    public void setHist1(String hist1) {
        this.hist1 = hist1;
    }

    public String getHist2() {
        return hist2;
    }

    public void setHist2(String hist2) {
        this.hist2 = hist2;
    }

    public String getHist3() {
        return hist3;
    }

    public void setHist3(String hist3) {
        this.hist3 = hist3;
    }

    public String getFilialTotvs() {
        return filialTotvs;
    }

    public void setFilialTotvs(String filialTotvs) {
        this.filialTotvs = filialTotvs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModeloNdf)) return false;

        ModeloNdf modeloNdf = (ModeloNdf) o;

        if (filial != modeloNdf.filial) return false;
        if (!titulo.equals(modeloNdf.titulo)) return false;
        if (!codFornecedor.equals(modeloNdf.codFornecedor)) return false;
        return dataEmissao.equals(modeloNdf.dataEmissao);
    }

    @Override
    public int hashCode() {
        int result = filial;
        result = 31 * result + titulo.hashCode();
        result = 31 * result + codFornecedor.hashCode();
        result = 31 * result + dataEmissao.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return       filial +
                ";" + titulo +
                ";" + codFornecedor +
                ";" + nome +
                ";" + valor +
                ";" + numero +
                ";" + dataEmissao +
                ";" + dataVencimento +
                ";" + hist1 +
                ";" + hist2 +
                ";" + hist3 +
                ";" + filialTotvs +
                ";" + status +
                ";" ;
    }


}

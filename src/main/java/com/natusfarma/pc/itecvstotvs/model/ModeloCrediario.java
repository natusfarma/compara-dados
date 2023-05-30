package com.natusfarma.pc.itecvstotvs.model;

import java.time.LocalDate;

public class ModeloCrediario {

    private int filial;
    private int numero;
    private int codCliente;
    private String nome;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private double valor;
    private double saldo;
    private int cupom;
    private int caixa;
    private LocalDate dataBaixa;

    public int getFilial() {
        return filial;
    }

    public void setFilial(int filial) {
        this.filial = filial;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCupom() {
        return cupom;
    }

    public void setCupom(int cupom) {
        this.cupom = cupom;
    }

    public int getCaixa() {
        return caixa;
    }

    public void setCaixa(int caixa) {
        this.caixa = caixa;
    }

    public LocalDate getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(LocalDate dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    @Override
    public String toString() {
        return        filial +
                ";" + numero +
                ";" + codCliente +
                ";" + nome +
                ";" + dataEmissao +
                ";" + dataVencimento +
                ";" + dataBaixa +
                ";" + String.valueOf(valor).replace(".",",") +
                ";" + String.valueOf(saldo).replace(".",",") +
                ";" + cupom +
                ";" + caixa +
                ";" ;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModeloCrediario)) return false;

        ModeloCrediario that = (ModeloCrediario) o;

        if (numero != that.numero) return false;
        if (codCliente != that.codCliente) return false;
        return dataEmissao.equals(that.dataEmissao);
    }

    @Override
    public int hashCode() {
        int result = numero;
        result = 31 * result + codCliente;
        result = 31 * result + dataEmissao.hashCode();
        return result;
    }
}

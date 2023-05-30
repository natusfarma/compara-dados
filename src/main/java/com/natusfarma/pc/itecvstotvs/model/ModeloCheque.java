package com.natusfarma.pc.itecvstotvs.model;

import java.time.LocalDate;

public class ModeloCheque {

    private int filial;
    private int numero;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private double valor;
    private int cupom;
    private int caixa;
    private double saldo;
    private int banco;
    private String agencia;
    private int codCliente;
    private String nome;
    private String cgcCpf;

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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getBanco() {
        return banco;
    }

    public void setBanco(int banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCgcCpf() {
        return cgcCpf;
    }

    public void setCgcCpf(String cgcCpf) {
        this.cgcCpf = cgcCpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModeloCheque)) return false;

        ModeloCheque that = (ModeloCheque) o;

        if (numero != that.numero) return false;
        if (Double.compare(that.valor, valor) != 0) return false;
        if (codCliente != that.codCliente) return false;
        return dataEmissao.equals(that.dataEmissao);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = numero;
        result = 31 * result + dataEmissao.hashCode();
        temp = Double.doubleToLongBits(valor);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + codCliente;
        return result;
    }

    @Override
    public String toString() {
        return        filial +
                ";" + numero +
                ";" + dataEmissao +
                ";" + dataVencimento +
                ";" + String.valueOf(valor).replace(".",",") +
                ";" + cupom +
                ";" + caixa +
                ";" + String.valueOf(saldo).replace(".",",") +
                ";" + banco +
                ";" + agencia +
                ";" + codCliente +
                ";" + nome +
                ";" + cgcCpf +
                ";" ;
    }


}

package com.natusfarma.pc.itecvstotvs.model;

import java.time.LocalDate;

public class ModeloMidia {

    private int filial;
    private String numero;
    private int codCliente;
    private String nome;
    private LocalDate dataEmissao;
    private LocalDate dataVenciamento;
    private double valor;
    private double saldo;
    private String status;

    public int getCodCliente() {
        return codCliente;
    }
    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getFilial() {
        return filial;
    }

    public void setFilial(int filial) {
        this.filial = filial;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataVenciamento() {
        return dataVenciamento;
    }

    public void setDataVenciamento(LocalDate dataVenciamento) {
        this.dataVenciamento = dataVenciamento;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return
                 ";" + codCliente +
                 ";" + filial +
                 ";" + numero +
                 ";" + dataEmissao +
                 ";" + dataVenciamento +
                 ";" + valor +
                 ";" + saldo  +
                 ";" + nome  +
                 ";" + status +
                 ";" ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModeloMidia)) return false;

        ModeloMidia that = (ModeloMidia) o;

        if (filial != that.filial) return false;
        if (codCliente != that.codCliente) return false;
        if (!numero.equals(that.numero)) return false;
        if (Double.compare(that.valor, valor) != 0) return false;
        return dataEmissao.equals(that.dataEmissao);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = filial;
        result = 31 * result + numero.hashCode();
        result = 31 * result + codCliente;
        result = 31 * result + dataEmissao.hashCode();
        temp = Double.doubleToLongBits(valor);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }


}

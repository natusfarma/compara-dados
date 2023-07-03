package com.natusfarma.pc.itecvstotvs.model;

import java.time.LocalDate;

public class ModeloCrediario {

    private int filial;
    private int numero;
    private int codCliente;
    private String nome;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private double valor;
    private double valorPago;

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

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Override
    public String toString() {
        return        filial +
                ";" + numero +
                ";" + codCliente +
                ";" + nome +
                ";" + dataEmissao +
                ";" + dataVencimento +
                ";" + dataPagamento +
                ";" + String.valueOf(valor).replace(".",",") +
                ";" + String.valueOf(valorPago).replace(".",",") +
                ";" ;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModeloCrediario)) return false;

        ModeloCrediario that = (ModeloCrediario) o;

        if (filial != that.filial) return false;
        if (numero != that.numero) return false;
        if (codCliente != that.codCliente) return false;
        if (Double.compare(that.valor, valor) != 0) return false;
        if (Double.compare(that.valorPago, valorPago) != 0) return false;
        return dataEmissao.equals(that.dataEmissao);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = filial;
        result = 31 * result + numero;
        result = 31 * result + codCliente;
        result = 31 * result + dataEmissao.hashCode();
        temp = Double.doubleToLongBits(valor);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(valorPago);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof ModeloCrediario)) return false;
//
//        ModeloCrediario that = (ModeloCrediario) o;
//
//        if (filial != that.filial) return false;
//        if (numero != that.numero) return false;
//        if (codCliente != that.codCliente) return false;
//        if (Double.compare(that.valorPago, valorPago) != 0) return false;
//        return dataEmissao.equals(that.dataEmissao);
//    }
//
//    @Override
//    public int hashCode() {
//        int result;
//        long temp;
//        result = filial;
//        result = 31 * result + numero;
//        result = 31 * result + codCliente;
//        result = 31 * result + dataEmissao.hashCode();
//        temp = Double.doubleToLongBits(valorPago);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        return result;
//    }
}

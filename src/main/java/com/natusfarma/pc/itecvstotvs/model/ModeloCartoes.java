package com.natusfarma.pc.itecvstotvs.model;

import java.time.LocalDate;

public class ModeloCartoes {

    private int codCliente;
    private int filial;
    private String numero;
    private LocalDate dataEmissao;
    private double valor;
    private String tipo;
    private String nome;
    private String obs;

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof ModeloCartoes)) return false;
//
//        ModeloCartoes that = (ModeloCartoes) o;
//
//        if (codCliente != that.codCliente) return false;
//        if (filial != that.filial) return false;
//        if (!numero.equals(that.numero)) return false;
//        return dataEmissao.equals(that.dataEmissao);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = codCliente;
//        result = 31 * result + filial;
//        result = 31 * result + numero.hashCode();
//        result = 31 * result + dataEmissao.hashCode();
//        return result;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModeloCartoes)) return false;

        ModeloCartoes that = (ModeloCartoes) o;
        double precisao = 0.04; // precisão é de 0.03 porém o double subtrai com mais casas decimais. ex: 0.030000000000010232

        if (codCliente != that.codCliente) return false;
        if (filial != that.filial) return false;
        if (Math.abs(valor - that.valor) > precisao) return false;
        //if (Double.compare(that.valor, valor) != 0) return false;
        if (!numero.equals(that.numero)) return false;
        return dataEmissao.equals(that.dataEmissao);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = codCliente;
        result = 31 * result + filial;
        result = 31 * result + numero.hashCode();
        result = 31 * result + dataEmissao.hashCode();
        temp = Double.doubleToLongBits(valor);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return
                 ";" + codCliente +
                 ";" + filial +
                 ";" + numero +
                 ";" + dataEmissao +
                 ";" + valor +
                 ";" + tipo  +
                 ";" + nome  +
                 ";" + obs +
                 ";" ;
    }

}

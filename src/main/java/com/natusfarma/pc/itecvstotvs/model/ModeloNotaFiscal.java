package com.natusfarma.pc.itecvstotvs.model;

import java.time.LocalDate;

public class ModeloNotaFiscal {

    private int filial;
    private int numero;
    private int serie;
    private LocalDate dataEmissao;
    private String chave;
    private int codCliente;
    private String status;
    private String filialTotvs;
    private double valor;

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

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFilialTotvs() {
        return filialTotvs;
    }

    public void setFilialTotvs(String filialTotvs) {
        this.filialTotvs = filialTotvs;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModeloNotaFiscal)) return false;

        ModeloNotaFiscal that = (ModeloNotaFiscal) o;

        if (filial != that.filial) return false;
        if (numero != that.numero) return false;
        if (serie != that.serie) return false;
        return dataEmissao.equals(that.dataEmissao);
    }

    @Override
    public int hashCode() {
        int result = filial;
        result = 31 * result + numero;
        result = 31 * result + serie;
        result = 31 * result + dataEmissao.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return       filial +
                ";" + numero +
                ";" + serie +
                ";" + dataEmissao +
                ";" + chave +
                ";" + codCliente +
                ";" + status +
                ";" + filialTotvs +
                ";" + valor +
                ";" ;
    }


}

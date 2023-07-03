package com.natusfarma.pc.itecvstotvs.model;

import com.natusfarma.pc.itecvstotvs.componente.QuantidadeColuna;

import java.time.LocalDate;

public class ModeloContasPagar {

    private String titulo;
    private Long codFornecedor;
    private String nomeFornecedor;
    private LocalDate dataVencimento;
    private LocalDate dataEmissao;
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

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    @Override
    public String toString() {
        return         titulo +
                 ";" + filial +
                 ";" + codFornecedor +
                 ";" + nomeFornecedor +
                 ";" + dataVencimento +
                 ";" + dataEmissao +
                 ";" + String.valueOf(valor).replace(".",",") +
                 ";" + parcela +
                 ";" + numNota + 
                 ";" ;

    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof ModeloContasPagar)) return false;
//
//        ModeloContasPagar that = (ModeloContasPagar) o;
//
//        if (filial != that.filial) return false;
//        if (!titulo.equals(that.titulo)) return false;
//        if (!codFornecedor.equals(that.codFornecedor)) return false;
//        if (parcela != that.parcela) return false;
//        return dataVencimento.equals(that.dataVencimento);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = titulo.hashCode();
//        result = 31 * result + codFornecedor.hashCode();
//        result = 31 * result + dataVencimento.hashCode();
//        result = 31 * result + parcela;
//        result = 31 * result + filial;
//        return result;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModeloContasPagar)) return false;

        ModeloContasPagar that = (ModeloContasPagar) o;

        if (parcela != that.parcela) return false;
        if (filial != that.filial) return false;
        if (!titulo.equals(that.titulo)) return false;
        if (!codFornecedor.equals(that.codFornecedor)) return false;
        return dataEmissao.equals(that.dataEmissao);
    }

    @Override
    public int hashCode() {
        int result = titulo.hashCode();
        result = 31 * result + codFornecedor.hashCode();
        result = 31 * result + dataEmissao.hashCode();
        result = 31 * result + parcela;
        result = 31 * result + filial;
        return result;
    }
}

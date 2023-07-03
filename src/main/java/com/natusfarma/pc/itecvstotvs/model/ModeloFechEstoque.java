package com.natusfarma.pc.itecvstotvs.model;

import java.math.BigDecimal;

public class ModeloFechEstoque {

    private int filial;
    private long totalItens;
    private double totalCusto;
    private String dataReferencia;
    private BigDecimal diferenca;

    public int getFilial() {
        return filial;
    }

    public void setFilial(int filial) {
        this.filial = filial;
    }

    public long getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(long totalItens) {
        this.totalItens = totalItens;
    }

    public double getTotalCusto() {
        return totalCusto;
    }

    public void setTotalCusto(double totalCusto) {
        this.totalCusto = totalCusto;
    }

    public String getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(String dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public BigDecimal getDiferenca() {
        return diferenca;
    }

    public void setDiferenca(BigDecimal diferenca) {
        this.diferenca = diferenca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModeloFechEstoque)) return false;

        ModeloFechEstoque that = (ModeloFechEstoque) o;

        if (filial != that.filial) return false;
        return totalItens == that.totalItens;
    }

    @Override
    public int hashCode() {
        int result = filial;
        result = 31 * result + (int) (totalItens ^ (totalItens >>> 32));
        return result;
    }
}

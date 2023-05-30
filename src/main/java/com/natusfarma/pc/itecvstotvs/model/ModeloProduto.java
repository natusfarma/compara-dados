package com.natusfarma.pc.itecvstotvs.model;

public class ModeloProduto {

    private int codigo;
    private String descricao;
    private String ncm;
    private String barras;
    private String ativo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getBarras() {
        return barras;
    }

    public void setBarras(String barras) {
        this.barras = barras;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModeloProduto)) return false;

        ModeloProduto that = (ModeloProduto) o;

        if (codigo != that.codigo) return false;
        return ncm.equals(that.ncm);
    }

    @Override
    public int hashCode() {
        int result = codigo;
        result = 31 * result + ncm.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return       codigo +
                ";" + descricao +
                ";" + ncm +
                ";" + barras +
                ";" + ativo +
                ";" ;
    }
}

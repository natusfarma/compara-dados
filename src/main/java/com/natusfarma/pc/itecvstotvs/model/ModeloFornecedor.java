package com.natusfarma.pc.itecvstotvs.model;

public class ModeloFornecedor {

    private int codigo;
    private String nome;
    private String cnpjCpf;
    private String inscricao;
    private String tipoFOrJ;
    private String uf;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public String getInscricao() {
        return inscricao;
    }

    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    public String getTipoFOrJ() {
        return tipoFOrJ;
    }

    public void setTipoFOrJ(String tipoFOrJ) {
        this.tipoFOrJ = tipoFOrJ;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModeloFornecedor)) return false;

        ModeloFornecedor that = (ModeloFornecedor) o;

        if (codigo != that.codigo) return false;
        if (!cnpjCpf.equals(that.cnpjCpf)) return false;
        return inscricao.equals(that.inscricao);
    }

    @Override
    public int hashCode() {
        int result = codigo;
        result = 31 * result + cnpjCpf.hashCode();
        result = 31 * result + inscricao.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return       codigo +
                ";" + nome +
                ";" + cnpjCpf +
                ";" + inscricao +
                ";" + tipoFOrJ +
                ";" + uf +
                ";" ;
    }
}

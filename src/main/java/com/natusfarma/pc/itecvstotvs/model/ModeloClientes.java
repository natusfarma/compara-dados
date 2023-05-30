package com.natusfarma.pc.itecvstotvs.model;

public class ModeloClientes {

    private int codigo;
    private String nome;
    private String cnpgCpf;
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

    public String getCnpgCpf() {
        return cnpgCpf;
    }

    public void setCnpgCpf(String cnpgCpf) {
        this.cnpgCpf = cnpgCpf;
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
        if (!(o instanceof ModeloClientes)) return false;

        ModeloClientes that = (ModeloClientes) o;

        if (codigo != that.codigo) return false;
        if (!cnpgCpf.equals(that.cnpgCpf)) return false;
        return inscricao.equals(that.inscricao);
    }

    @Override
    public int hashCode() {
        int result = codigo;
        result = 31 * result + cnpgCpf.hashCode();
        result = 31 * result + inscricao.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return       codigo +
               ";" + nome +
               ";" + cnpgCpf +
               ";" + inscricao +
               ";" + tipoFOrJ +
               ";" + uf +
               ";" ;
    }

}

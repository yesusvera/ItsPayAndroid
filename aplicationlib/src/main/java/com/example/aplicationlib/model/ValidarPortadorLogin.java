package com.example.aplicationlib.model;

/**
 * Created by juniorbraga on 25/04/17.
 */

public class ValidarPortadorLogin {

    private String credencial;
    private String dataNascimento;
    private String cpf;
    private long idInstituicao;
    private long idProcessadora;
    private String chave;
    private String celular;

    public ValidarPortadorLogin(){}

    public String getCredencial() {
        return credencial;
    }

    public void setCredencial(String credencial) {
        this.credencial = credencial;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public long getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(long idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public long getIdProcessadora() {
        return idProcessadora;
    }

    public void setIdProcessadora(long idProcessadora) {
        this.idProcessadora = idProcessadora;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}

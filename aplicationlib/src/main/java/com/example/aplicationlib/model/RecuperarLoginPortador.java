package com.example.aplicationlib.model;

/**
 * Created by juniorbraga on 13/03/17.
 */

public class RecuperarLoginPortador {

    private String documento;
    private String dataNascimento;
    private long idInstituicao;
    private long idProcessadora;


    public RecuperarLoginPortador(){}

    public RecuperarLoginPortador(String cpf,  String dataNascimento, long idInstituicao, long idProcessadora){
        this.documento = cpf;
        this.dataNascimento = dataNascimento;
        this.idInstituicao = idInstituicao;
        this.idProcessadora = idProcessadora;
    }

    public String getCpf() {
        return documento;
    }

    public void setCpf(String cpf) {
        this.documento = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
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

}

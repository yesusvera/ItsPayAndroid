package com.example.aplicationlib.model;

/**
 * Created by juniorbraga on 27/03/17.
 */

public class RequestToken {

    private String chave;
    private String celular;
    private long idInstituicao;
    private long idProcessadora;

    public RequestToken() {
    }

    private RequestToken(String chave, String celular,long idInstituicao, long idProcessadora){
        this.chave = chave;
        this.celular = celular;
        this.idInstituicao = idInstituicao;
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

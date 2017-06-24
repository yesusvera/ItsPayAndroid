package com.example.aplicationlib.model;

/**
 * Created by yesus on 21/01/17.
 */

public class Caracteristica {
    /**
     * idCaracteristicaReferencia : 1
     * idReferenciaSKU : 1
     * nome : Marca
     * valor : Sony
     */

    private int idCaracteristicaReferencia;
    private int idReferenciaSKU;
    private String nome;
    private String valor;

    public int getIdCaracteristicaReferencia() {
        return idCaracteristicaReferencia;
    }

    public void setIdCaracteristicaReferencia(int idCaracteristicaReferencia) {
        this.idCaracteristicaReferencia = idCaracteristicaReferencia;
    }

    public int getIdReferenciaSKU() {
        return idReferenciaSKU;
    }

    public void setIdReferenciaSKU(int idReferenciaSKU) {
        this.idReferenciaSKU = idReferenciaSKU;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
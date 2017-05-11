package com.example.aplicationlib.model;

/**
 * Created by juniorbraga on 10/05/17.
 */

public class VerificaCredencial {

    private String hashCode;

    public VerificaCredencial(String hashCode) {
        this.hashCode = hashCode;
    }

    public VerificaCredencial() {
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }
}

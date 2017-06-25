package com.example.aplicationlib.enumclass;

/**
 * Created by juniorbraga on 23/05/17.
 */

public enum EstadoCivilEnum {

    SOLTEIRO(1, "Solteiro(a)"),
    CASADO(2, "Casado(a)"),
    DIVORCIADO(3,"Divorciado(a)"),
    VIUCO(4, "Viúvo(a)"),
    HOME_PAGE(5, "Separado(a)"),
    TREASURE(6, "Não Informado");

    public enum standardHomePagereturnType {
        RETURN_NUMBER,
        RETURN_REPRESENTATIVE_LETTER
    }

    private String mRepresentative;
    private int mNumberEstado;

    EstadoCivilEnum(int completeName, String representative) {
        this.mNumberEstado = completeName;
        this.mRepresentative = representative;
    }

    public static EstadoCivilEnum getFromRepresentative(String representativeLetter) {
        for (EstadoCivilEnum eDefaultEstadoCivil : EstadoCivilEnum.values()) {
            if (eDefaultEstadoCivil.getRepresentative().equals(representativeLetter)) {
                return eDefaultEstadoCivil;
            }
        }
        return SOLTEIRO;
    }

    public int getNumberEstado() {
        return mNumberEstado;
    }

    public String getRepresentative() {
        return mRepresentative;
    }

}

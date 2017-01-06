package itspay.br.com.model;

import java.util.Arrays;

/**
 * Created by yesus on 06/01/17.
 */

public class GetPerfilTarifarioResponse {
    private PerfilsTarifario perfilsTarifarios[];

    public GetPerfilTarifarioResponse(PerfilsTarifario[] perfilsTarifarios){
        this.perfilsTarifarios = perfilsTarifarios;
    }

    public GetPerfilTarifarioResponse() {
    }

    public PerfilsTarifario[] getPerfilsTarifarios() {
        return perfilsTarifarios;
    }

    public void setPerfilsTarifarios(PerfilsTarifario[] perfilsTarifarios) {
        this.perfilsTarifarios = perfilsTarifarios;
    }

    @Override
    public String toString() {
        return "GetPerfilTarifarioResponse{" +
                "perfilsTarifarios=" + Arrays.toString(perfilsTarifarios) +
                '}';
    }
}
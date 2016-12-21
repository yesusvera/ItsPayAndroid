package itspay.br.com.model;

import java.util.Arrays;

/**
 * Created by yesus on 19/12/16.
 */

public class GetCredenciaisResponse {
    private Credencial credenciais[];

    public GetCredenciaisResponse(){}

    public GetCredenciaisResponse(Credencial[] credenciais){
        this.credenciais = credenciais;
    }

    public Credencial[] getCredenciais() {
        return credenciais;
    }

    public void setCredenciais(Credencial[] credenciais) {
        this.credenciais = credenciais;
    }

    @Override
    public String toString() {
        return "GetCredenciaisResponse{" +
                "credenciais=" + Arrays.toString(credenciais) +
                '}';
    }
}
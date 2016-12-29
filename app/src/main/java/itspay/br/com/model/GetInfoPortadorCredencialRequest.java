package itspay.br.com.model;

/**
 * Created by yesus on 29/12/16.
 */

public class GetInfoPortadorCredencialRequest {
    private String credencial;

    public GetInfoPortadorCredencialRequest() {
    }

    public GetInfoPortadorCredencialRequest(String credencial){
        this.credencial = credencial;
    }

    public String getCredencial() {
        return credencial;
    }

    public void setCredencial(String credencial) {
        this.credencial = credencial;
    }
}

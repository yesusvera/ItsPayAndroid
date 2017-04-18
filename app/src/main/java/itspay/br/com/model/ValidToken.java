package itspay.br.com.model;

/**
 * Created by juniorbraga on 27/03/17.
 */

public class ValidToken {

    public String token;
    public String chaveExterna;

    public ValidToken(){}


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getChaveExterna() {
        return chaveExterna;
    }

    public void setChaveExterna(String chaveExterna) {
        this.chaveExterna = chaveExterna;
    }
}

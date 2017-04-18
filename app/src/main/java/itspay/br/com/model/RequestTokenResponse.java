package itspay.br.com.model;

/**
 * Created by juniorbraga on 27/03/17.
 */

public class RequestTokenResponse {

    public boolean code;
    public int token;

    public RequestTokenResponse(){}

    public RequestTokenResponse(boolean code, int msg){
        this.code = code;
        this.token = msg;
    }

    public boolean getCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    public int getMsg() {
        return token;
    }

    public void setMsg(int msg) {
        this.token = msg;
    }


    @Override
    public String toString() {
        return token+"";
    }

}

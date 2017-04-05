package itspay.br.com.model;

/**
 * Created by juniorbraga on 27/03/17.
 */

public class RequestToken {

    public String code;
    public String msg;

    public RequestToken(){}

    public RequestToken(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    @Override
    public String toString() {
        return msg;
    }

}

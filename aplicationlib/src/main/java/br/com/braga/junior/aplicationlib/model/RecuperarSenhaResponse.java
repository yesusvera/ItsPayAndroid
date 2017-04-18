package br.com.braga.junior.aplicationlib.model;

/**
 * Created by juniorbraga on 13/03/17.
 */
public class RecuperarSenhaResponse {
    public String code;
    public String msg;

    public RecuperarSenhaResponse(){}

    public RecuperarSenhaResponse(String code, String msg){
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

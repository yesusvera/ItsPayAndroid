package itspay.br.com.model;

/**
 * Created by yesus on 23/12/16.
 */

public class ItsPayResponse {

    private String msg;

    public ItsPayResponse(String msg) {
        this.msg = msg;
    }

    public ItsPayResponse() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ItsPayResponse{" +
                "msg='" + msg + '\'' +
                '}';
    }
}

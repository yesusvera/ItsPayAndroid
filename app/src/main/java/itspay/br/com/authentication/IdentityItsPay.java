package itspay.br.com.authentication;

import br.com.braga.junior.aplicationlib.model.FazerLoginPortador;
import br.com.braga.junior.aplicationlib.model.FazerLoginPortadorResponse;

/**
 * Created by yesus on 18/12/16.
 */

public class IdentityItsPay {

    private static IdentityItsPay identity;

    private FazerLoginPortadorResponse loginPortadorResponse;

    private FazerLoginPortador loginPortador;

    private String setCookie;

    private boolean mPemissionMarketPlace;


    private IdentityItsPay(){}

    public static IdentityItsPay getInstance(){
        if(identity == null) identity = new IdentityItsPay();

        return identity;
    }

    public FazerLoginPortadorResponse getLoginPortadorResponse() {
        return loginPortadorResponse;
    }

    public void setLoginPortadorResponse(FazerLoginPortadorResponse loginPortadorResponse) {
        this.loginPortadorResponse = loginPortadorResponse;
    }

    public FazerLoginPortador getLoginPortador() {
        return loginPortador;
    }

    public void setLoginPortador(FazerLoginPortador loginPortador) {
        this.loginPortador = loginPortador;
    }

    public String getToken() {
        if(loginPortadorResponse != null && loginPortadorResponse.getToken() != null){
            return loginPortadorResponse.getToken();
        }
        return "";
    }

    public String getSetCookie() {
        return setCookie;
    }

    public void setSetCookie(String setCookie) {
        this.setCookie = setCookie;
    }

    public void clean(){
        loginPortadorResponse = null;
        loginPortador = null;
        identity = null;
    }

    public boolean ismPemissionMarketPlace() {
        return mPemissionMarketPlace;
    }

    public void setmPemissionMarketPlace(boolean mPemissionMarketPlace) {
        this.mPemissionMarketPlace = mPemissionMarketPlace;
    }

}

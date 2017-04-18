package itspay.br.com.controller;

import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Date;

import itspay.br.com.activity.CadastroLoginPage2Activity;
import itspay.br.com.activity.TokenActivity;
import itspay.br.com.model.RequestToken;
import itspay.br.com.model.RequestTokenResponse;
import itspay.br.com.model.ValidToken;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.singleton.CadastroSingleton;
import itspay.br.com.util.ItsPayConstants;
import itspay.br.com.util.UtilsActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by juniorbraga on 27/03/17.
 */

public class TokenController  extends BaseActivityController<TokenActivity>  {

    String mKey;
    public TokenController(TokenActivity activity) {
        super(activity);
    }

    public void requestCode(){

        mKey = CadastroSingleton.getInstance().getmNumerocelular() +
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date());

        final RequestToken requestTokenPortador = new RequestToken();
        requestTokenPortador.setIdInstituicao(ItsPayConstants.ID_INSTITUICAO);
        requestTokenPortador.setIdProcessadora(ItsPayConstants.ID_PROCESSADORA);
        requestTokenPortador.setChave(mKey);
        requestTokenPortador.setCelular(CadastroSingleton.getInstance().getmNumerocelular());

        Call<RequestTokenResponse> call =
                ConnectPortadorService.getService().requestToken(requestTokenPortador);

        call.enqueue(new Callback<RequestTokenResponse>() {
            @Override
            public void onResponse(Call<RequestTokenResponse> call, Response<RequestTokenResponse> response) {
                if(response.body()!=null){

                }else{
                    UtilsActivity.alertMsg(response.errorBody(), activity);
                }
            }

            @Override
            public void onFailure(Call<RequestTokenResponse> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
                t.printStackTrace();
            }
        });
    }


    public void validToken(String token){

        final ValidToken validTokenPortador = new ValidToken();
        validTokenPortador.setChaveExterna(mKey);
        validTokenPortador.setToken(token);


        Call<ResponseBody> call =
                ConnectPortadorService.getService().validToken(validTokenPortador);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.body()!=null){
                    Intent intent = new Intent(activity,CadastroLoginPage2Activity.class);
                    activity.startActivity(intent);

                }else{
                    UtilsActivity.alertMsg(response.errorBody(), activity);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
                t.printStackTrace();
            }
        });
    }

}

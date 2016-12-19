package itspay.br.com.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import itspay.br.com.activity.LoginActivity;
import itspay.br.com.activity.MeusCartoesActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.model.FazerLoginPortador;
import itspay.br.com.model.FazerLoginPortadorResponse;
import itspay.br.com.services.ConnectPortadorService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 17/12/16.
 */
public class LoginController extends BaseActivityController<LoginActivity>{

    public LoginController(LoginActivity activity){
        super(activity);
    }

    public void login(){
        final FazerLoginPortador fazerLoginPortador = new FazerLoginPortador();
        fazerLoginPortador.setArchitectureInfo("string");
        fazerLoginPortador.setCpf(activity.getmCpfView().getText().toString().replace(".", "").replace("-", ""));
        fazerLoginPortador.setDeviceId("string");
        fazerLoginPortador.setIdInstituicao(101);
        fazerLoginPortador.setIdProcessadora(10);
        fazerLoginPortador.setLatitude(castCoordenada(activity.getLatitude()));
        fazerLoginPortador.setLongitude(castCoordenada(activity.getLongitude()));
        fazerLoginPortador.setModel("string");
        fazerLoginPortador.setOrigemAcesso(1);
        fazerLoginPortador.setPlataformVersion("string");
        fazerLoginPortador.setPlatformName("string");
        fazerLoginPortador.setSenha(activity.getmPasswordView().getText().toString());
        fazerLoginPortador.setSistemaOperacional(1);
        fazerLoginPortador.setVersaoConhecida("1.0.0");
        fazerLoginPortador.setVersaoInstalada("1.0.0");

        Call<FazerLoginPortadorResponse> fazerLoginPortadorResponseCall =   ConnectPortadorService.getService().login(fazerLoginPortador);

        fazerLoginPortadorResponseCall.enqueue(new Callback<FazerLoginPortadorResponse>() {
            @Override
            public void onResponse(Call<FazerLoginPortadorResponse> call, Response<FazerLoginPortadorResponse> response) {
               if(response.body()!=null) {
                   Log.i("RESPOSTA SERVICO LOGIN", response.body().toString());
                   activity.showProgress(false);

                   IdentityItsPay.getInstance().setLoginPortadorResponse(response.body());
                   IdentityItsPay.getInstance().setLoginPortador(fazerLoginPortador);
                   IdentityItsPay.getInstance().setSetCookie(response.headers().get("Set-Cookie"));

                   //redirecionando para meus cartões
                   Intent intent = new Intent(activity, MeusCartoesActivity.class);
                   activity.startActivity(intent);

               }else if(response.errorBody() != null){
                   try {
                       JSONObject jsonObject = new JSONObject(response.errorBody().string());
                       String msg = jsonObject.getString("msg");

                       AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                       builder.setCancelable(false).setMessage(msg)
                               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialogInterface, int i) {
                                       activity.showProgress(false);
                                       IdentityItsPay.getInstance().clean();
                                   }
                               });
                       builder.create().show();
                   }catch (IOException ex){
                       ex.printStackTrace();
                   }catch (JSONException ex){
                       ex.printStackTrace();
                   }
               }
            }

            @Override
            public void onFailure(Call<FazerLoginPortadorResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public long castCoordenada(double coordenada){
        String str = String.valueOf(coordenada);
        str = str.replace(".", "").replace(",", "");
        return Long.valueOf(str);
    }
}

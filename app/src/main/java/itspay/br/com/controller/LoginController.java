package itspay.br.com.controller;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;

import itspay.br.com.activity.LoginActivity;
import itspay.br.com.model.FazerLoginPortador;
import itspay.br.com.model.FazerLoginPortadorResponse;
import itspay.br.com.services.ConnectPortadorService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static itspay.br.com.services.ConnectPortadorService.getService;

/**
 * Created by yesus on 17/12/16.
 */

public class LoginController {

    private LoginActivity loginActivity;

    public LoginController(LoginActivity activity){
        loginActivity = activity;
    }

    public void login(String cpf, String password){

        FazerLoginPortador fazerLoginPortador = new FazerLoginPortador();

        fazerLoginPortador.setArchitectureInfo("string");
        fazerLoginPortador.setCpf("04128946109");
        fazerLoginPortador.setDeviceId("string");
        fazerLoginPortador.setIdInstituicao(101);
        fazerLoginPortador.setIdProcessadora(10);
        fazerLoginPortador.setLatitude(0);
        fazerLoginPortador.setLongitude(0);
        fazerLoginPortador.setModel("string");
        fazerLoginPortador.setOrigemAcesso(1);
        fazerLoginPortador.setPlataformVersion("string");
        fazerLoginPortador.setPlatformName("string");
        fazerLoginPortador.setSenha("123");
        fazerLoginPortador.setSistemaOperacional(1);
        fazerLoginPortador.setVersaoConhecida("1.0.0");
        fazerLoginPortador.setVersaoInstalada("1.0.0");

        Call<FazerLoginPortadorResponse> fazerLoginPortadorResponseCall =   ConnectPortadorService.getService().login(fazerLoginPortador);


        fazerLoginPortadorResponseCall.enqueue(new Callback<FazerLoginPortadorResponse>() {
            @Override
            public void onResponse(Call<FazerLoginPortadorResponse> call, Response<FazerLoginPortadorResponse> response) {
                Log.i("RESPOSTA SERVICO LOGIN", response.body().toString());
            }

            @Override
            public void onFailure(Call<FazerLoginPortadorResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

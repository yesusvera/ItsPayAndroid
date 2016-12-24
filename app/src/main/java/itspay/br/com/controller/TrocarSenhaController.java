package itspay.br.com.controller;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import itspay.br.com.activity.TrocarSenhaActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.itspay.R;
import itspay.br.com.model.ItsPayResponse;
import itspay.br.com.model.TrocarSenhaPortador;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.ItsPayConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 23/12/16.
 */

public class TrocarSenhaController extends BaseActivityController<TrocarSenhaActivity> {

    public TrocarSenhaController(TrocarSenhaActivity activity) {
        super(activity);
    }

    public void trocarSenha(){
        if(campoVazio(activity.getSenha())) return;
        if(campoVazio(activity.getNovaSenha())) return;
        if(campoVazio(activity.getConfirmarSenha())) return;

        if(!activity.getNovaSenha().getText().toString().equals(activity.getConfirmarSenha().getText().toString())){
            activity.getConfirmarSenha().setError(activity.getString(R.string.error_senhas_incompativeis));
            activity.getConfirmarSenha().requestFocus();

            return;
        }

        TrocarSenhaPortador trocarSenhaPortador = new TrocarSenhaPortador();
        trocarSenhaPortador.setIdProcessadora(ItsPayConstants.ID_PROCESSADORA);
        trocarSenhaPortador.setIdInstituicao(ItsPayConstants.ID_INSTITUICAO);
        trocarSenhaPortador.setCpf(IdentityItsPay.getInstance().getLoginPortador().getCpf());
        trocarSenhaPortador.setSenha(activity.getSenha().getText().toString());
        trocarSenhaPortador.setNovaSenha(activity.getNovaSenha().getText().toString());

        Call<ItsPayResponse> call =
                ConnectPortadorService
                        .getService()
                        .trocarSenha(trocarSenhaPortador, IdentityItsPay.getInstance().getToken());

        call.enqueue(new Callback<ItsPayResponse>() {
            @Override
            public void onResponse(Call<ItsPayResponse> call, Response<ItsPayResponse> response) {
                if (response.body() != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setCancelable(false).setMessage(response.body().getMsg())
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    activity.finish();
                                }
                            });
                    builder.create().show();
                }else if (response.errorBody() != null){
                    try {
                        String jsonStr = response.errorBody().string();

                        JSONObject reader = new JSONObject(jsonStr);
                        String msg = reader.getString("msg");

                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setCancelable(false).setMessage(msg)
                                .setPositiveButton("OK", null);
                        builder.create().show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ItsPayResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public boolean campoVazio(EditText editText){
        if(editText.getText()!=null && editText.getText().toString()!= null && editText.getText().toString().trim().length() == 0 ){
            editText.setError(activity.getString(R.string.error_field_required));
            editText.requestFocus();
            return true;
        }else{
            return false;
        }

    }

}

package itspay.br.com.controller;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;

import itspay.br.com.activity.TrocarSenhaActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.itspay.R;
import itspay.br.com.model.ItsPayResponse;
import itspay.br.com.model.TrocarSenhaPortador;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.EncriptSHA512;
import itspay.br.com.util.ItsPayConstants;
import itspay.br.com.util.UtilsActivity;
import itspay.br.com.util.validations.ValidationsForms;
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

        if(!ValidationsForms.senhaValida(activity.getNovaSenha().getText().toString())){
            activity.getNovaSenha().setError(activity.getString(R.string.error_incorrect_password_input));
            activity.getNovaSenha().requestFocus();
            return;
        }

        if(!activity.getNovaSenha().getText().toString().equals(activity.getConfirmarSenha().getText().toString())){
            activity.getConfirmarSenha().setError(activity.getString(R.string.error_senhas_incompativeis));
            activity.getConfirmarSenha().requestFocus();

            return;
        }

        String senhaCriptografada = EncriptSHA512.encript(activity.getSenha().getText().toString() +
                IdentityItsPay.getInstance().getToken()
        );


        String novaSenhaCriptografada = EncriptSHA512.encript(activity.getNovaSenha().getText().toString() +
                IdentityItsPay.getInstance().getToken()
        );

        TrocarSenhaPortador trocarSenhaPortador = new TrocarSenhaPortador();
        trocarSenhaPortador.setIdProcessadora(ItsPayConstants.ID_PROCESSADORA);
        trocarSenhaPortador.setIdInstituicao(ItsPayConstants.ID_INSTITUICAO);
        trocarSenhaPortador.setCpf(IdentityItsPay.getInstance().getLoginPortador().getCpf());
        trocarSenhaPortador.setSenha(senhaCriptografada);
        trocarSenhaPortador.setNovaSenha(novaSenhaCriptografada);

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
                }else{
                    UtilsActivity.alertMsg(response.errorBody(), activity);
                }
            }

            @Override
            public void onFailure(Call<ItsPayResponse> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
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

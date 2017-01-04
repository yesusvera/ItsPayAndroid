package itspay.br.com.controller;


import android.content.DialogInterface;
import android.widget.EditText;

import itspay.br.com.activity.TrocarSenhaCartaoActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.itspay.R;
import itspay.br.com.model.TrocarPinRequest;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.UtilsActivity;
import itspay.br.com.util.validations.ValidationsForms;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 23/12/16.
 */

public class TrocarSenhaCartaoController extends BaseActivityController<TrocarSenhaCartaoActivity> {

    public TrocarSenhaCartaoController(TrocarSenhaCartaoActivity activity) {
        super(activity);
    }

    public void trocarSenha(){
        if(campoVazio(activity.senha)) return;
        if(campoVazio(activity.novaSenha)) return;
        if(campoVazio(activity.confirmarSenha)) return;

        if(!ValidationsForms.senhaCartaoValida(activity.novaSenha.getText().toString())){
            activity.novaSenha.setError(activity.getString(R.string.error_incorrect_password_input));
            activity.novaSenha.requestFocus();
            return;
        }

        if(!activity.novaSenha.getText().toString().equals(activity.confirmarSenha.getText().toString())){
            activity.confirmarSenha.setError(activity.getString(R.string.error_senhas_incompativeis));
            activity.confirmarSenha.requestFocus();

            return;
        }

        TrocarPinRequest request = new TrocarPinRequest();
        request.setIdCredencial(activity.credencialDetalhe.getIdCredencial());
        request.setSenha(activity.senha.getText().toString());
        request.setNovaSenha(activity.novaSenha.getText().toString());

        Call<ResponseBody> call =
                ConnectPortadorService
                        .getService()
                        .trocarSenhaCartao(request, IdentityItsPay.getInstance().getToken());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {

                    UtilsActivity.alertMsg(response.body(), activity, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            activity.finish();
                        }
                    });

                }else{
                    UtilsActivity.alertMsg(response.errorBody(), activity);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
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

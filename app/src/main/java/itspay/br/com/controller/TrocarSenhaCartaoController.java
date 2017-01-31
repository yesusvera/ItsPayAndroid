package itspay.br.com.controller;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;

import itspay.br.com.activity.TrocarSenhaCartaoActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.itspay.R;
import itspay.br.com.model.TrocarPinRequest;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.EncriptSHA512;
import itspay.br.com.util.UtilsActivity;
import itspay.br.com.util.validations.ValidationsForms;
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

    public void trocarSenha() {
        if (campoVazio(activity.senha)) return;
        if (campoVazio(activity.novaSenha)) return;
        if (campoVazio(activity.confirmarSenha)) return;

        if (!ValidationsForms.senhaCartaoValida(activity.novaSenha.getText().toString())) {
            activity.novaSenha.setError(activity.getString(R.string.error_incorrect_password_input));
            activity.novaSenha.requestFocus();
            return;
        }

        if (!activity.novaSenha.getText().toString().equals(activity.confirmarSenha.getText().toString())) {
            activity.confirmarSenha.setError(activity.getString(R.string.error_senhas_incompativeis));
            activity.confirmarSenha.requestFocus();

            return;
        }

        String senhaCriptografada = EncriptSHA512.encript(activity.senha.getText().toString() +
                IdentityItsPay.getInstance().getToken()
        );


        String novaSenhaCriptografada = EncriptSHA512.encript(activity.novaSenha.getText().toString() +
                IdentityItsPay.getInstance().getToken()
        );

        TrocarPinRequest request = new TrocarPinRequest();
        request.setIdCredencial(activity.credencialDetalhe.getIdCredencial());
        request.setSenha(senhaCriptografada);
        request.setNovaSenha(novaSenhaCriptografada);

        Call<Boolean> call =
                ConnectPortadorService
                        .getService()
                        .trocarSenhaCartao(request, IdentityItsPay.getInstance().getToken());

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.body() != null) {

                    final boolean resposta = response.body();

                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage(resposta ? "Senha alterada com sucesso" : "Erro ao alterar a senha.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (resposta) {
                                        activity.finish();
                                    }
                                }
                            });
                    builder.create().show();

                } else {
                    UtilsActivity.alertMsg(response.errorBody(), activity);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
            }
        });

    }

    public boolean campoVazio(EditText editText) {
        if (editText.getText() != null && editText.getText().toString() != null && editText.getText().toString().trim().length() == 0) {
            editText.setError(activity.getString(R.string.error_field_required));
            editText.requestFocus();
            return true;
        } else {
            return false;
        }

    }

}

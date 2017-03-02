package itspay.br.com.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import itspay.br.com.controller.TrocarSenhaCartaoController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Credencial;
import itspay.br.com.util.notification.CustomNotification;
import itspay.br.com.util.usersharepreferences.SharedPreferenceUtil;

public class TrocarSenhaCartaoActivity extends AppCompatActivity {

    public EditText senha;
    public EditText novaSenha;
    public EditText confirmarSenha;
    public Button trocar_senha_button;

    public Credencial credencialDetalhe;

    private TrocarSenhaCartaoController trocarSenhaCartaoController = new TrocarSenhaCartaoController(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trocar_senha_cartao);

        credencialDetalhe = CartaoActivity.credencialDetalhe;

        senha = (EditText)findViewById(R.id.senha_atual);
        novaSenha = (EditText)findViewById(R.id.nova_senha);
        confirmarSenha = (EditText)findViewById(R.id.confirmar_senha);

        trocar_senha_button = (Button)findViewById(R.id.trocar_senha_button);
        trocar_senha_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToastNotification();
                alterarSenhaDoCartao();

            }
        });
    }

    public void alterarSenhaDoCartao(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog_token, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.etValor);
        final Button btnOK = (Button)dialogView.findViewById(R.id.btn_request_token);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToastNotification();
            }
        });

        dialogBuilder.setTitle("Alterar Senha");
        dialogBuilder.setMessage("Para alterar senha do cartão digite a chave de acesso");
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                InputMethodManager inputManager = (InputMethodManager) getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(edt.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                String tokenResponse = "123";
                if (edt.getText().toString().equals(tokenResponse)){
                    trocarSenhaCartaoController.trocarSenha();
                }else{
                    Toast.makeText(getBaseContext(), "Erro", Toast.LENGTH_LONG).show();
                }

            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
                InputMethodManager inputManager = (InputMethodManager) getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(edt.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    private void showToastNotification(){
        CustomNotification.getInstance().notificationBuilder(getBaseContext(), R.mipmap.ic_launch_financial, R.color.red_itspay_bkp, getString(R.string.app_name), "A Chave de acesso é: 123");
    }
}

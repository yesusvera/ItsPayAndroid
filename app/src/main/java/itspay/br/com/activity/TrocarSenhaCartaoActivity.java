package itspay.br.com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import itspay.br.com.controller.TrocarSenhaCartaoController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Credencial;

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
                trocarSenhaCartaoController.trocarSenha();
            }
        });
    }
}

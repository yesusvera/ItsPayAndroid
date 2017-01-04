package itspay.br.com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import itspay.br.com.controller.TrocarSenhaController;
import itspay.br.com.itspay.R;

public class TrocarSenhaActivity extends AppCompatActivity {

    private EditText senha;
    private EditText novaSenha;
    private EditText confirmarSenha;
    private Button trocar_senha_button;

    private TrocarSenhaController trocarSenhaController = new TrocarSenhaController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trocar_senha);

        senha = (EditText)findViewById(R.id.senha_atual);
        novaSenha = (EditText)findViewById(R.id.nova_senha);
        confirmarSenha = (EditText)findViewById(R.id.confirmar_senha);

        trocar_senha_button = (Button)findViewById(R.id.trocar_senha_button);
        trocar_senha_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trocarSenhaController.trocarSenha();
            }
        });
    }

    public EditText getSenha() {
        return senha;
    }

    public void setSenha(EditText senha) {
        this.senha = senha;
    }

    public EditText getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(EditText novaSenha) {
        this.novaSenha = novaSenha;
    }

    public EditText getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(EditText confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    public Button getTrocar_senha_button() {
        return trocar_senha_button;
    }

    public void setTrocar_senha_button(Button trocar_senha_button) {
        this.trocar_senha_button = trocar_senha_button;
    }
}

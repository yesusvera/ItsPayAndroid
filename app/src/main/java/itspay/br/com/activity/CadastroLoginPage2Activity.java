package itspay.br.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import itspay.br.com.controller.CadastroLoginController;
import itspay.br.com.itspay.R;


public class CadastroLoginPage2Activity extends AppCompatActivity {


    private EditText email;
    private EditText senha;
    private EditText confirmacaoSenha;

    private TextView txtViewTermosDeUso;

    private CheckBox termosDeUso;

    private Button criarLogin;

    private CadastroLoginController mCadastroLoginController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cadastro_loginpage2);

        initView();

        clickListeners();
    }

    private void initView(){
        mCadastroLoginController = new CadastroLoginController(getBaseContext());

        senha = (EditText)findViewById(R.id.senha);
        confirmacaoSenha = (EditText)findViewById(R.id.confirmacaoSenha);
        termosDeUso = (CheckBox)findViewById(R.id.checkTermosDeUso);
        criarLogin = (Button)findViewById(R.id.buttonCriarLogin);
        txtViewTermosDeUso = (TextView) findViewById(R.id.txtViewTermosDeUso);
        email =(EditText)findViewById(R.id.email);
    }

    private void clickListeners(){
        criarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valida();
            }
        });

        txtViewTermosDeUso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), TermosDeUsoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void valida(){
        if (mCadastroLoginController.validaFormulario(this)){
            mCadastroLoginController.criarLogin(this);
        }
    }
//
    public EditText getEmail() {
        return email;
    }

    public void setEmail(EditText email) {
        this.email = email;
    }

    public EditText getSenha() {
        return senha;
    }

    public void setSenha(EditText senha) {
        this.senha = senha;
    }

    public CheckBox getTermosDeUso() {
        return termosDeUso;
    }

    public void setTermosDeUso(CheckBox termosDeUso) {
        this.termosDeUso = termosDeUso;
    }

    public EditText getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(EditText confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }
}

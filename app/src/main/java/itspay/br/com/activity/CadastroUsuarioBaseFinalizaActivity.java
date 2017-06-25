package itspay.br.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import itspay.br.com.controller.CadastroUsuarioBaseFinalController;
import itspay.br.com.itspay.R;


public class CadastroUsuarioBaseFinalizaActivity extends AppCompatActivity {

    private android.widget.EditText email;
    private android.widget.EditText emailprofissional;
    private android.widget.EditText senha;
    private android.widget.EditText confirmacaoSenha;
    private android.widget.ScrollView cadastrologinform;
    private android.widget.CheckBox checkTermosDeUso;
    private android.widget.TextView txtViewTermosDeUso;
    private android.widget.Button buttonCriarLogin;
    private android.widget.LinearLayout layoutcriarlogin;

    CadastroUsuarioBaseFinalController mController = new CadastroUsuarioBaseFinalController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario_base_finaliza);

        initView();

        clickListeners();
    }

    public void initView(){

        this.layoutcriarlogin = (LinearLayout) findViewById(R.id.layout_criarlogin);
        this.buttonCriarLogin = (Button) findViewById(R.id.buttonCriarLogin);
        this.txtViewTermosDeUso = (TextView) findViewById(R.id.txtViewTermosDeUso);
        this.checkTermosDeUso = (CheckBox) findViewById(R.id.checkTermosDeUso);
        this.cadastrologinform = (ScrollView) findViewById(R.id.cadastro_login_form);
        this.confirmacaoSenha = (EditText) findViewById(R.id.confirmacaoSenha);
        this.senha = (EditText) findViewById(R.id.senha);
        this.emailprofissional = (EditText) findViewById(R.id.email_profissional);
        this.email = (EditText) findViewById(R.id.email);
    }

    private void clickListeners(){

        txtViewTermosDeUso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), TermosDeUsoActivity.class);
                startActivity(intent);
            }
        });

        buttonCriarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valida();
            }
        });
    }

    private void valida(){
        if (mController.validaFormulario()){
            mController.criarLogin();
        }
    }

    public EditText getEmail() {
        return email;
    }

    public void setEmail(EditText email) {
        this.email = email;
    }

    public EditText getEmailprofissional() {
        return emailprofissional;
    }

    public void setEmailprofissional(EditText emailprofissional) {
        this.emailprofissional = emailprofissional;
    }

    public EditText getSenha() {
        return senha;
    }

    public void setSenha(EditText senha) {
        this.senha = senha;
    }

    public EditText getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(EditText confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public CheckBox getCheckTermosDeUso() {
        return checkTermosDeUso;
    }

    public void setCheckTermosDeUso(CheckBox checkTermosDeUso) {
        this.checkTermosDeUso = checkTermosDeUso;
    }

    public TextView getTxtViewTermosDeUso() {
        return txtViewTermosDeUso;
    }

    public void setTxtViewTermosDeUso(TextView txtViewTermosDeUso) {
        this.txtViewTermosDeUso = txtViewTermosDeUso;
    }
}

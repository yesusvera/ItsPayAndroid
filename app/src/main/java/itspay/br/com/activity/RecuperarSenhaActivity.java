package itspay.br.com.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import itspay.br.com.controller.RecuperarSenhaController;
import itspay.br.com.itspay.R;
import itspay.br.com.util.mask.MaskEditTextChangedListener;

public class RecuperarSenhaActivity extends AppCompatActivity {

    protected EditText cpf;
    protected EditText dataNascimento;
    protected Button buttonCriarLogin;
    public ProgressDialog progress;

    private RecuperarSenhaController recuperarSenhaController = new RecuperarSenhaController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_recuperar_senha);
        initView();

        cpf.addTextChangedListener(new MaskEditTextChangedListener("###.###.###-##", cpf));
        dataNascimento.addTextChangedListener(new MaskEditTextChangedListener("##/##/####", dataNascimento));

        buttonCriarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarSenhaController.criarLogin();
                ((InputMethodManager) getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                        cpf.getWindowToken(), 0);
                ((InputMethodManager) getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                        dataNascimento.getWindowToken(), 0);

            }
        });
    }

    private void initView() {
        cpf = (EditText) findViewById(R.id.cpf);
        dataNascimento = (EditText) findViewById(R.id.dataNascimento);
        buttonCriarLogin = (Button) findViewById(R.id.buttonCriarLogin);
    }

    public EditText getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(EditText dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public EditText getCpf() {
        return cpf;
    }

    public void setCpf(EditText cpf) {
        this.cpf = cpf;
    }
}

package itspay.br.com.itspay;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import itspay.br.com.mask.MaskEditTextChangedListener;

public class CadastroLogin extends AppCompatActivity {

    private EditText numeroCartao;
    private EditText dataNascimento;
    private EditText cpf;
    private EditText email;
    private EditText senha;

    private TextView txtViewTermosDeUso;

    private CheckBox termosDeUso;

    private Button criarLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_login);
        setTitle(R.string.title_activity_cadastro_login);

        numeroCartao = (EditText)findViewById(R.id.numeroCartao);
        dataNascimento = (EditText)findViewById(R.id.dataNascimento);
        cpf = (EditText)findViewById(R.id.cpf);
        email = (EditText)findViewById(R.id.email);
        senha = (EditText)findViewById(R.id.senha);
        termosDeUso = (CheckBox)findViewById(R.id.checkTermosDeUso);
        criarLogin = (Button)findViewById(R.id.buttonCriarLogin);
        txtViewTermosDeUso = (TextView) findViewById(R.id.txtViewTermosDeUso);

        numeroCartao.addTextChangedListener(new MaskEditTextChangedListener("####.####.####.####", numeroCartao));
        cpf.addTextChangedListener(new MaskEditTextChangedListener("###.###.###-##", cpf));
        dataNascimento.addTextChangedListener(new MaskEditTextChangedListener("##/##/####", dataNascimento));


        criarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!termosDeUso.isChecked()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(CadastroLogin.this);
                    builder.setMessage("Você precisa concordar com os termos de uso e políticas de privacidade.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    termosDeUso.requestFocus();
                                }
                            });

                    builder.create().show();

                }
            }
        });

        txtViewTermosDeUso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastroLogin.this, TermosDeUsoActivity.class);
                startActivity(intent);
            }
        });

    }
}

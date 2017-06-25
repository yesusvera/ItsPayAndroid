package itspay.br.com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicationlib.util.mask.MaskEditTextChangedListener;

import itspay.br.com.controller.CadastroUsuarioBase2Controller;
import itspay.br.com.itspay.R;
import itspay.br.com.util.Utils;

public class CadastroUsuarioBase2Activity extends AppCompatActivity {

    private android.widget.EditText cep;
    private android.widget.EditText bairro;
    private android.widget.EditText endereco;
    private android.widget.EditText numeroresidencial;
    private android.widget.EditText numerocomplemento;
    private android.widget.TextView txtcidade;
    private android.widget.TextView txtestado;
    private android.widget.ScrollView cadastrologinform;
    private android.widget.Button btnnextpage;


    CadastroUsuarioBase2Controller mCadastroUsuarioBase2Controller = new CadastroUsuarioBase2Controller(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario_base2);

        initView();


        btnnextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCadastroUsuarioBase2Controller.nextPage();
            }
        });

        cep.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    mCadastroUsuarioBase2Controller.getEnderecoCep(cep.getText().toString());

                }
            }
        });
    }

    private void initView() {

        this.btnnextpage = (Button) findViewById(R.id.btn_next_page);
        this.cadastrologinform = (ScrollView) findViewById(R.id.cadastro_login_form);
        this.txtestado = (TextView) findViewById(R.id.txt_estado);
        this.txtcidade = (TextView) findViewById(R.id.txt_cidade);
        this.numerocomplemento = (EditText) findViewById(R.id.numero_complemento);
        this.numeroresidencial = (EditText) findViewById(R.id.numero_residencial);
        this.endereco = (EditText) findViewById(R.id.endereco);
        this.bairro = (EditText) findViewById(R.id.bairro);
        this.cep = (EditText) findViewById(R.id.cep);

//        numeroresidencial.setText("13");
        cep.addTextChangedListener(new MaskEditTextChangedListener("#####-###", cep));

        Utils.nextInputOnMaxLength(this,cep,bairro,9);
    }


    public EditText getCep() {
        return cep;
    }

    public void setCep(EditText cep) {
        this.cep = cep;
    }

    public EditText getBairro() {
        return bairro;
    }

    public void setBairro(EditText bairro) {
        this.bairro = bairro;
    }

    public EditText getEndereco() {
        return endereco;
    }

    public void setEndereco(EditText endereco) {
        this.endereco = endereco;
    }

    public EditText getNumeroresidencial() {
        return numeroresidencial;
    }

    public void setNumeroresidencial(EditText numeroresidencial) {
        this.numeroresidencial = numeroresidencial;
    }

    public EditText getNumerocomplemento() {
        return numerocomplemento;
    }

    public void setNumerocomplemento(EditText numerocomplemento) {
        this.numerocomplemento = numerocomplemento;
    }

    public TextView getTxtcidade() {
        return txtcidade;
    }

    public void setTxtcidade(TextView txtcidade) {
        this.txtcidade = txtcidade;
    }

    public TextView getTxtestado() {
        return txtestado;
    }

    public void setTxtestado(TextView txtestado) {
        this.txtestado = txtestado;
    }

}

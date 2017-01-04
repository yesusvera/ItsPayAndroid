package itspay.br.com.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import itspay.br.com.controller.NovoCartaoVirtualController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Credencial;

public class NovoCartaoVirtualActivity extends AppCompatActivity {
    private Button button1M;
    private Button button2M;
    private Button button3M;
    private Button buttonRequisitarNovo;

    private EditText nomeCartaoVirtual;
    private static final String UM_MES = "1";
    private static final String DOIS_MESES = "2";
    private static final String TRES_MESES = "2";

    public static final int CONCORDO_TERMOS_DE_USO_CODE = 34;

    private String qtdMesesSelecionado = "1";
    private Credencial credencialDetalhe;

    private NovoCartaoVirtualController controller = new NovoCartaoVirtualController(this);

    View.OnClickListener clickButtons = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int color = ContextCompat.getColor(NovoCartaoVirtualActivity.this,R.color.cardview_light_background);
            button1M.setBackgroundColor(color);
            button2M.setBackgroundColor(color);
            button3M.setBackgroundColor(color);

            qtdMesesSelecionado = view.getTag().toString();

            view.setBackgroundColor(Color.LTGRAY);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_cartao_virtual);

        button1M = (Button)findViewById(R.id.button1M);
        button2M = (Button)findViewById(R.id.button2M);
        button3M = (Button)findViewById(R.id.button3M);
        buttonRequisitarNovo = (Button)findViewById(R.id.requisitar_button);
        nomeCartaoVirtual = (EditText)findViewById(R.id.nomeCartaoVirtual);

        button1M.setOnClickListener(clickButtons);
        button2M.setOnClickListener(clickButtons);
        button3M.setOnClickListener(clickButtons);

        button1M.setBackgroundColor(Color.LTGRAY);

        credencialDetalhe = CartaoActivity.credencialDetalhe;

        buttonRequisitarNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NovoCartaoVirtualActivity.this, TermosDeUsoCartaoVirtualActivity.class);
                startActivityForResult(intent, CONCORDO_TERMOS_DE_USO_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode== CONCORDO_TERMOS_DE_USO_CODE){
            if(resultCode == CONCORDO_TERMOS_DE_USO_CODE){
                controller.requisitarNovoCartao();
            }
        }
    }

    public EditText getNomeCartaoVirtual() {
        return nomeCartaoVirtual;
    }

    public void setNomeCartaoVirtual(EditText nomeCartaoVirtual) {
        this.nomeCartaoVirtual = nomeCartaoVirtual;
    }

    public String getQtdMesesSelecionado() {
        return qtdMesesSelecionado;
    }

    public void setQtdMesesSelecionado(String qtdMesesSelecionado) {
        this.qtdMesesSelecionado = qtdMesesSelecionado;
    }

    public Credencial getCredencialDetalhe() {
        return credencialDetalhe;
    }

    public void setCredencialDetalhe(Credencial credencialDetalhe) {
        this.credencialDetalhe = credencialDetalhe;
    }
}
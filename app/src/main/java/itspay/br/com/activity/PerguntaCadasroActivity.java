package itspay.br.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import itspay.br.com.itspay.R;

public class PerguntaCadasroActivity extends AppCompatActivity {

    private Button mPossuoDadosCadastrados;
    private Button mNaoDadosCadastrados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta_cadasro);

        this.mNaoDadosCadastrados = (Button) findViewById(R.id.btn_nao_dados_cadastrados);
        this.mPossuoDadosCadastrados = (Button) findViewById(R.id.btn_possuo_dados_cadartrados);

        mNaoDadosCadastrados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarTela(CadastroUsuarioBase1Activity.class);
            }
        });

        mPossuoDadosCadastrados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarTela(CadastroLoginActivity.class);
            }
        });
    }

    private void chamarTela(Class nextClass){
        Intent intent = new Intent(PerguntaCadasroActivity.this,nextClass);
        startActivity(intent);
    }
}

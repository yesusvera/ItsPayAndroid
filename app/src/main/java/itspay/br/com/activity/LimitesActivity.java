package itspay.br.com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.aplicationlib.model.Credencial;

import itspay.br.com.itspay.R;
import itspay.br.com.util.Utils;

public class LimitesActivity extends AppCompatActivity {

    private android.widget.TextView cvlimite;
    private android.widget.TextView cvdisponivel;
    public static Credencial credencialDetalhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limites);

        this.cvdisponivel = (TextView) findViewById(R.id.cv_disponivel);
        this.cvlimite = (TextView) findViewById(R.id.cv_limite);
        String saldo = "R$" + Utils.formataMoeda(credencialDetalhe.getSaldo());
        String limite = "R$" + Utils.formataMoeda(credencialDetalhe.getLimite());


        cvdisponivel.setText(saldo);
        cvlimite.setText(limite);
    }
}

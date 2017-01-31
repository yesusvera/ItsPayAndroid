package itspay.br.com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.blackcat.currencyedittext.CurrencyEditText;

import java.util.Locale;

import itspay.br.com.controller.InserirCargaController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Credencial;

public class InserirCargaActivity extends AppCompatActivity {

    private Credencial credencialDetalhe;

    private CurrencyEditText valor;
    private Button gerarBoletoButton;

    InserirCargaController controller = new InserirCargaController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_carga);

        credencialDetalhe =  CartaoActivity.credencialDetalhe;
//        setTitle("Saldo R$ "+ credencialDetalhe.getSaldo());

        valor = (CurrencyEditText)findViewById(R.id.valor);
        gerarBoletoButton = (Button)findViewById(R.id.gerar_boleto_button);

        valor.setLocale(new Locale("pt", "BR"));

        gerarBoletoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.gerarBoleto();
            }
        });
    }

    public Credencial getCredencialDetalhe() {
        return credencialDetalhe;
    }

    public void setCredencialDetalhe(Credencial credencialDetalhe) {
        this.credencialDetalhe = credencialDetalhe;
    }

    public CurrencyEditText getValor() {
        return valor;
    }

    public void setValor(CurrencyEditText valor) {
        this.valor = valor;
    }
}

package itspay.br.com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.blackcat.currencyedittext.CurrencyEditText;

import java.util.Locale;

import itspay.br.com.itspay.R;
import itspay.br.com.model.Credencial;
import itspay.br.com.util.mask.MaskEditTextChangedListener;

public class TransferirContaCorrenteActivity extends AppCompatActivity {

    private EditText bancoFavorecido;
    private EditText agencia;
    private EditText conta;
    private EditText cpf;
    private EditText favorecido;
    private CurrencyEditText valor;
    private EditText senhaCartao;
    private Button transferirButton;

    private Credencial credencialDetalhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferir_conta_corrente);

        credencialDetalhe =  CartaoActivity.credencialDetalhe;

        setTitle("Saldo R$ "+ credencialDetalhe.getSaldo());

        bancoFavorecido = (EditText)findViewById(R.id.banco_favorecido);
        agencia = (EditText)findViewById(R.id.agencia);
        conta = (EditText)findViewById(R.id.conta);
        cpf = (EditText)findViewById(R.id.cpf);
        favorecido = (EditText)findViewById(R.id.favorecido);
        valor = (CurrencyEditText)findViewById(R.id.valor);
        senhaCartao = (EditText)findViewById(R.id.senhaCartao);
        transferirButton = (Button)findViewById(R.id.transferir_button);


        valor.setLocale(new Locale("pt", "BR"));
        agencia.addTextChangedListener(new MaskEditTextChangedListener("####", agencia));
        conta.addTextChangedListener(new MaskEditTextChangedListener("#####-#", conta));
        cpf.addTextChangedListener(new MaskEditTextChangedListener("###.###.###-##", cpf));


    }
}

package itspay.br.com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blackcat.currencyedittext.CurrencyEditText;

import java.util.Locale;

import itspay.br.com.controller.TransferirOutroCartaoController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Credencial;
import itspay.br.com.util.mask.MaskEditTextChangedListener;

public class TransferirOutroCartaoActivity extends AppCompatActivity {

    private EditText numeroCartaoDestino;
    private EditText favorecido;
    private CurrencyEditText valor;
    private EditText tarifa;
    private EditText senhaCartao;
    private Button transferirButton;
    private Credencial credencialDetalhe;

    private TransferirOutroCartaoController controller = new TransferirOutroCartaoController(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferir_outro_cartao);

        credencialDetalhe =  CartaoActivity.credencialDetalhe;

        setTitle("Saldo R$:"+ credencialDetalhe.getSaldo());

        numeroCartaoDestino = (EditText)findViewById(R.id.numeroCartaoDestino);
        favorecido = (EditText)findViewById(R.id.favorecido);
        valor = (CurrencyEditText)findViewById(R.id.valor);
        tarifa = (EditText)findViewById(R.id.tarifa);
        senhaCartao = (EditText)findViewById(R.id.senhaCartao);
        transferirButton = (Button)findViewById(R.id.transferir_button);

        numeroCartaoDestino.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasfocus) {
                if(!hasfocus){
                    favorecido.setText("");
                    if(numeroCartaoDestino.getText().toString().length() == 19){
                        controller.preencherNomePortadorCredencial();
                    }else{
                        numeroCartaoDestino.setError("Preencha corretamente o número do cartão");
                    }
                }
            }
        });

        transferirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.transferirParaOutroCartao();
            }
        });

        valor.setLocale(new Locale("pt", "BR"));
        numeroCartaoDestino.addTextChangedListener(new MaskEditTextChangedListener("####.####.####.####", numeroCartaoDestino));
    }


    public Credencial getCredencialDetalhe() {
        return credencialDetalhe;
    }

    public EditText getSenhaCartao() {
        return senhaCartao;
    }

    public EditText getFavorecido() {
        return favorecido;
    }

    public CurrencyEditText getValor() {
        return valor;
    }

    public EditText getTarifa() {
        return tarifa;
    }

    public EditText getNumeroCartaoDestino() {
        return numeroCartaoDestino;
    }
}

package itspay.br.com.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.blackcat.currencyedittext.CurrencyEditText;

import java.util.Locale;

import itspay.br.com.controller.TransferirOutroCartaoController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Credencial;
import itspay.br.com.util.Utils;
import itspay.br.com.util.mask.MaskEditTextChangedListener;

public class TransferirOutroCartaoActivity extends AppCompatActivity {

    private EditText numeroCartaoDestino;
    private EditText favorecido;
    private CurrencyEditText valor;
    private EditText tarifa;
    private EditText senhaCartao;
    public Button transferirButton;
    private Credencial credencialDetalhe;
    public LinearLayout mainLayout;
    public ProgressBar progress;

    private TransferirOutroCartaoController controller = new TransferirOutroCartaoController(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferir_outro_cartao);

        credencialDetalhe =  CartaoActivity.credencialDetalhe;

        setTitle("Saldo R$ "+ Utils.formataMoeda(credencialDetalhe.getSaldo()));

        mainLayout = (LinearLayout)findViewById(R.id.mainLayout);
        progress = (ProgressBar)findViewById(R.id.progress);

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
                    if(numeroCartaoDestino.getText().toString().length() >= 19){
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
                AlertDialog.Builder builder = new AlertDialog.Builder(TransferirOutroCartaoActivity.this);
                builder.setMessage(getString(R.string.mensagem_confirmacao_transf))
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                controller.transferirParaOutroCartao();
                            }
                        })
                        .setNegativeButton("Não", null);
                builder.create().show();

            }
        });

        valor.setLocale(new Locale("pt", "BR"));
        numeroCartaoDestino.addTextChangedListener(new MaskEditTextChangedListener("####.####.####.####.###", numeroCartaoDestino));
    }


    public void setLoading(boolean loading){
        if(loading){
            mainLayout.setVisibility(View.INVISIBLE);
            progress.setVisibility(View.VISIBLE);
        }else{
            mainLayout.setVisibility(View.VISIBLE);
            progress.setVisibility(View.INVISIBLE);
        }
    }

    public void dismissKeyboard(){
        InputMethodManager inputMethodManager =
                (InputMethodManager) this.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                this.getCurrentFocus().getWindowToken(), 0);
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

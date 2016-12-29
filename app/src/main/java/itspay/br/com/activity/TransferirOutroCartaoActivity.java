package itspay.br.com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import itspay.br.com.itspay.R;
import itspay.br.com.util.mask.MaskEditTextChangedListener;

public class TransferirOutroCartaoActivity extends AppCompatActivity {

    private EditText numeroCartaoDestino;
    private EditText favorecido;
    private EditText valor;
    private EditText tarifa;
    private EditText senhaCartao;
    private Button transferirButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferir_outro_cartao);

        numeroCartaoDestino = (EditText)findViewById(R.id.numeroCartaoDestino);
        favorecido = (EditText)findViewById(R.id.favorecido);
        valor = (EditText)findViewById(R.id.valor);
        tarifa = (EditText)findViewById(R.id.tarifa);
        senhaCartao = (EditText)findViewById(R.id.senhaCartao);
        transferirButton = (Button)findViewById(R.id.transferir_button);


        numeroCartaoDestino.addTextChangedListener(new MaskEditTextChangedListener("####.####.####.####", numeroCartaoDestino));
    }
}

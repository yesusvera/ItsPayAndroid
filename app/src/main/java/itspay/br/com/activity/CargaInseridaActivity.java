package itspay.br.com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import itspay.br.com.controller.CargaInseridaController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.BoletoCarga;
import itspay.br.com.model.GerarBoletoCarga;

public class CargaInseridaActivity extends AppCompatActivity {

    private EditText valorCarga;
    private EditText vencimento;
    private EditText linhaDigitavel;
    private Button enviarEmail;
    public static BoletoCarga boleto;
    public static GerarBoletoCarga gerarBoletoCarga;

    private CargaInseridaController controller = new CargaInseridaController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_inserida);

        valorCarga = (EditText)findViewById(R.id.valorCarga);
        vencimento = (EditText)findViewById(R.id.vencimento);
        linhaDigitavel = (EditText)findViewById(R.id.linhaDigitavel);
        enviarEmail = (Button)findViewById(R.id.enviar_email);

        if(boleto!=null && gerarBoletoCarga!=null){
            valorCarga.setText("R$ "+ gerarBoletoCarga.getValor());
            vencimento.setText(boleto.getDataVencimentoFmtMes());
            linhaDigitavel.setText(boleto.getLinhaDigitavel());
        }

        linhaDigitavel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linhaDigitavel.selectAll();
            }
        });
    }
}

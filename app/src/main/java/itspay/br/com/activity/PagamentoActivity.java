package itspay.br.com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import itspay.br.com.controller.PagamentoController;
import itspay.br.com.itspay.R;
import itspay.br.com.singleton.CarrinhoSingleton;
import itspay.br.com.util.Utils;

public class PagamentoActivity extends AppCompatActivity {

    public LinearLayout mainLayout;
    public ProgressBar progress;
    private TextView txtValor;
    private EditText txtSenha;
    private Button btnPagar;

    private CarrinhoSingleton carrinho = CarrinhoSingleton.getInstance();
    private PagamentoController controller = new PagamentoController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        progress = (ProgressBar) findViewById(R.id.progress);
        txtValor = (TextView) findViewById(R.id.text_valor);
        txtSenha = (EditText) findViewById(R.id.text_senha_credencial);
        btnPagar = (Button) findViewById(R.id.btn_pagar);

        txtValor.setText("R$ " + Utils.formataMoeda(carrinho.getParcela().getValorParcela() * carrinho.getParcela().getQuantidadeParcelas()));

        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.efetuarPedido();
            }
        });
    }

    public void setLoading(boolean loading) {
        if (loading) {
            mainLayout.setVisibility(View.INVISIBLE);
            progress.setVisibility(View.VISIBLE);
        } else {
            mainLayout.setVisibility(View.VISIBLE);
            progress.setVisibility(View.INVISIBLE);
        }
    }
}

package itspay.br.com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import itspay.br.com.controller.ProdutoDetalheController;
import itspay.br.com.itspay.R;

public class ProdutoDetalheActivity extends AppCompatActivity {

    private ProdutoDetalheController controller = new ProdutoDetalheController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_detalhe);
    }
}

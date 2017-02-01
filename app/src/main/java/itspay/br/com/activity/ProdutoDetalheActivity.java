package itspay.br.com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import itspay.br.com.controller.ProdutoDetalheController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.ProdutoDetalhe;
import itspay.br.com.util.Utils;

public class ProdutoDetalheActivity extends AppCompatActivity {

    private ProdutoDetalheController controller =
                    new ProdutoDetalheController(this);

    private TextView txtQtde;
    private TextView txtNomeProduto;
    private TextView txtDescricaoProduto;
    private TextView txtValorDe;
    private TextView txtValorPor;
    private TextView txtParcelas;
    private TextView txtValorSubtotal;

    private Button btnMenos;
    private Button btnMais;

    private short quantidade = 1;
    private double subtotal;

    public static ProdutoDetalhe produtoDetalhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_detalhe);

        txtQtde = (TextView) findViewById(R.id.text_quantidade);
        txtNomeProduto = (TextView) findViewById(R.id.text_nome_produto);
        txtDescricaoProduto = (TextView) findViewById(R.id.text_descricao_produto);
        txtValorDe = (TextView) findViewById(R.id.text_valor_de);
        txtValorPor = (TextView) findViewById(R.id.text_valor_por);
        txtParcelas = (TextView) findViewById(R.id.text_parcelas);
        txtValorSubtotal = (TextView) findViewById(R.id.text_subtotal);

        btnMenos = (Button)findViewById(R.id.btn_menos);
        btnMais = (Button)findViewById(R.id.btn_mais);


        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantidade--;
                atualizaValores();
            }
        });

        btnMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantidade++;
                atualizaValores();
            }
        });


        preencherValores();
        atualizaValores();
    }

    private void preencherValores(){
        if(produtoDetalhe.getProduto()!=null){
            String precoDe = "De R$" + Utils.formataMoeda(produtoDetalhe.getProduto().getReferencias()[0].getPrecoDe());
            String precoPor = "R$" + Utils.formataMoeda(produtoDetalhe.getProduto().getReferencias()[0].getPrecoPor());

            txtNomeProduto.setText(produtoDetalhe.getProduto().getNomeProduto());
            txtDescricaoProduto.setText(produtoDetalhe.getProduto().getDescricao());
            txtValorDe.setText(precoDe);
            txtValorPor.setText(precoPor);

            if(produtoDetalhe.getParceiroResponse()!=null) {
                txtParcelas.setText("em até " + produtoDetalhe.getParceiroResponse().getQuantMaxParcelaSemJuros() + " vezes");
            }

        }
    }

    private void atualizaValores(){
        txtQtde.setText(quantidade + "");

        subtotal = quantidade * produtoDetalhe.getProduto().getReferencias()[0].getPrecoPor();
        String strSubtotal = "R$ "+Utils.formataMoeda(subtotal);

        txtValorSubtotal.setText(strSubtotal);

        if(quantidade==1){
            btnMenos.setEnabled(false);
        }else{
            btnMenos.setEnabled(true);
        }
    }
}

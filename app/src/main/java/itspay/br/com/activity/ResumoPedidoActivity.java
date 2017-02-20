package itspay.br.com.activity;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.view.MaterialListView;

import java.util.ArrayList;
import java.util.List;

import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Produto;
import itspay.br.com.model.ProdutoCarrinho;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.singleton.CarrinhoSingleton;
import itspay.br.com.util.Utils;
import itspay.br.com.util.UtilsActivity;
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ResumoPedidoActivity extends AppCompatActivity {

    private MaterialListView materialListView;
    private TextView textValorTotal;
    private TextView textGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_pedido);

        materialListView = (MaterialListView) findViewById(R.id.material_listView_produtos);
        textValorTotal = (TextView) findViewById(R.id.text_valor_total);
        textGrupo = (TextView) findViewById(R.id.text_grupo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listarProdutos();
    }

    public void listarProdutos() {

        materialListView.getAdapter().clearAll();
        materialListView.setItemAnimator(new FlipInTopXAnimator());
        materialListView.getItemAnimator().setAddDuration(500);
        materialListView.getItemAnimator().setRemoveDuration(300);

        List<Card> cards = new ArrayList<>();

        double valorTotal = 0;

        for (ProdutoCarrinho produtoCarrinho : CarrinhoSingleton.getInstance().getListaProdutosCarrinho()) {

            textGrupo.setText(produtoCarrinho.getProdutoDetalhe().getParceiroResponse().getNomeParceiro());

            Produto produto = produtoCarrinho.getProdutoDetalhe().getProduto();

            String precoPor = "R$" + Utils.formataMoeda(produto.getReferencias()[0].getPrecoPor());

            double subtotal = produtoCarrinho.getQuantidade() * produto.getReferencias()[0].getPrecoPor();

            valorTotal += subtotal;

            String strSubtotal = "R$ " + Utils.formataMoeda(subtotal);

            final Card card = new Card.Builder(this)
                    .setTag(produtoCarrinho)
                    .withProvider(new CardProvider())
                    .setLayout(R.layout.item_produto_carrinho)
                    .setTitle(produto.getNomeProduto())
                    .setTitleColor(Color.DKGRAY)
                    .setSubtitle("Quantidade: " + produtoCarrinho.getQuantidade())
                    .setSubtitle2(precoPor)
                    .setSubtitle3(strSubtotal)
                    .setSubtitleColor(Color.BLACK)
                    .setDescription("Subtotal:")
                    .setKeepLayoutXml(true)
                    .endConfig()
                    .build();

            cards.add(card);

            if (produto.getImagens() != null && produto.getImagens().length > 0) {
                Call<ResponseBody> call = ConnectPortadorService.getService().abrirImagemProduto(produto.getImagens()[0].getIdImagem(),
                        IdentityItsPay.getInstance().getToken());

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.body() != null && response.body().byteStream() != null) {
                            card.getProvider().setDrawable(new BitmapDrawable(response.body().byteStream()));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        UtilsActivity.alertIfSocketException(t, ResumoPedidoActivity.this);
                    }
                });
            }
        }

        textValorTotal.setText("R$ " + Utils.formataMoeda(valorTotal));
        materialListView.getAdapter().addAll(cards);
    }
}

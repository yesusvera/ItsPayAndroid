package itspay.br.com.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aplicationlib.model.Caracteristica;
import com.example.aplicationlib.model.Imagen;
import com.example.aplicationlib.model.MarketPlaceResponse;
import com.example.aplicationlib.model.ProdutoCarrinho;
import com.example.aplicationlib.model.ProdutoDetalhe;
import com.example.aplicationlib.model.Referencia;
import com.example.aplicationlib.util.cache.CacheImageView;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import itspay.br.com.adapter.MainPagerAdapter;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.controller.ProdutoDetalheController;
import itspay.br.com.itspay.R;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.singleton.CarrinhoSingleton;
import itspay.br.com.util.Utils;
import itspay.br.com.util.UtilsActivity;
import me.relex.circleindicator.CircleIndicator;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private Button btnAdicionarCarrinho;

    private short quantidade = 1;
    private double subtotal;

    public static ProdutoDetalhe produtoDetalhe;

    private AutoScrollViewPager pager;
    private MainPagerAdapter pagerAdapter;

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

        btnMenos = (Button) findViewById(R.id.btn_menos);
        btnMais = (Button) findViewById(R.id.btn_mais);
        btnAdicionarCarrinho = (Button) findViewById(R.id.btn_adicionar_carrinho);


        pagerAdapter = new MainPagerAdapter();
        pager = (AutoScrollViewPager) findViewById(R.id.view_pager);
        pager.setAdapter(pagerAdapter);
        pager.setInterval(4000);
        pager.startAutoScroll();

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);
        pagerAdapter.registerDataSetObserver(indicator.getDataSetObserver());


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

        btnAdicionarCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escolherReferencia();
            }
        });


        preencherValores();
        atualizaValores();
        preencherImagens();
    }

    private void preencherImagens() {

        if (produtoDetalhe.getProduto().getImagens() != null && produtoDetalhe.getProduto().getImagens().size() > 0) {

            for (final MarketPlaceResponse.ProdutoBean.ImagensBean img : produtoDetalhe.getProduto().getImagens()) {
                if (CacheImageView.temCache(getApplicationContext(), img.getIdImagem() + "")) {
                    BitmapDrawable bitmapDrawable = CacheImageView.lerCacheBitmapDraw(getApplicationContext(), img.getIdImagem() + "");

                    ImageView imageView = new ImageView(ProdutoDetalheActivity.this);
                    imageView.setImageBitmap(bitmapDrawable.getBitmap());
                    pagerAdapter.addView(imageView);
                    pagerAdapter.notifyDataSetChanged();
                } else {
                    Call<ResponseBody> call = ConnectPortadorService.getService().abrirImagemProduto(img.getIdImagem(),
                            IdentityItsPay.getInstance().getToken());

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.body() != null && response.body().byteStream() != null) {

                                try {
                                    CacheImageView.salvarCache(getApplicationContext(), img.getIdImagem() + "", response.body().byteStream());
                                } catch (Exception e) {
                                }

                                BitmapDrawable bitmapDrawable = CacheImageView.lerCacheBitmapDraw(getApplicationContext(), img.getIdImagem() + "");

                                ImageView imageView = new ImageView(ProdutoDetalheActivity.this);
                                imageView.setImageBitmap(bitmapDrawable.getBitmap());
                                pagerAdapter.addView(imageView);
                                pagerAdapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            UtilsActivity.alertIfSocketException(t, ProdutoDetalheActivity.this);
                        }
                    });
                }
            }
        }
    }

    private void preencherValores() {
        if (produtoDetalhe.getProduto() != null) {
            String precoDe = "De R$" + Utils.formataMoeda(produtoDetalhe.getProduto().getReferencias().get(0).getPrecoDe());
            String precoPor = "R$" + Utils.formataMoeda(produtoDetalhe.getProduto().getReferencias().get(0).getPrecoPor());

            txtNomeProduto.setText(produtoDetalhe.getProduto().getNomeProduto());
            txtDescricaoProduto.setText(produtoDetalhe.getProduto().getDescricao());
            txtValorDe.setText(precoDe);
            txtValorPor.setText(precoPor);

            if (produtoDetalhe.getParceiroResponse() != null) {
                txtParcelas.setText("em até " + produtoDetalhe.getParceiroResponse().getParceiro().getQuantMaxParcelaSemJuros() + " vezes");
            }

        }
    }

    private void atualizaValores() {
        txtQtde.setText(quantidade + "");

        subtotal = quantidade * produtoDetalhe.getProduto().getReferencias().get(0).getPrecoPor();
        String strSubtotal = "R$ " + Utils.formataMoeda(subtotal);

        txtValorSubtotal.setText(strSubtotal);

        if (quantidade == 1) {
            btnMenos.setEnabled(false);
        } else {
            btnMenos.setEnabled(true);
        }
    }

    public void escolherReferencia() {
        ArrayList<CharSequence> listaReferencia = new ArrayList<>();

        for(MarketPlaceResponse.ProdutoBean.ReferenciasBean ref: produtoDetalhe.getProduto().getReferencias()){
            String str =  produtoDetalhe.getProduto().getNomeProduto();

            if(ref.getCaracteristicas()!=null && ref.getCaracteristicas().size()>0) {
                for(MarketPlaceResponse.ProdutoBean.ReferenciasBean.CaracteristicasBean caracteristica : ref.getCaracteristicas()){
                    str = str.concat(caracteristica.getValor()).concat(" ");
                }
            }

            listaReferencia.add(str);
        }
        if (listaReferencia.size() > 1) {
            final CharSequence[] items = new CharSequence[listaReferencia.size()];
            listaReferencia.toArray(items);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Escolha uma referência para adicionar ao carrinho.").setCancelable(true).setNegativeButton("Cancelar", null)
                    .setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            adicionarAoCarrinho(produtoDetalhe.getProduto().getReferencias().get(i));
                        }
                    });
            builder.create().show();
        }else{
            adicionarAoCarrinho(produtoDetalhe.getProduto().getReferencias().get(0));
        }
    }

    public void adicionarAoCarrinho(MarketPlaceResponse.ProdutoBean.ReferenciasBean referencia){
        ProdutoCarrinho produtoCarrinho = new ProdutoCarrinho();
        produtoCarrinho.setQuantidade(quantidade);
        produtoCarrinho.setProdutoDetalhe(produtoDetalhe);
        produtoCarrinho.setReferencia(referencia);

        CarrinhoSingleton.getInstance().adicionarProduto(produtoCarrinho);

        finish();
    }


    //-----------------------------------------------------------------------------
    // Here's what the app should do to add a view to the ViewPager.
    public void addView(View newPage) {
        int pageIndex = pagerAdapter.addView(newPage);
        // You might want to make "newPage" the currently displayed page:
        pager.setCurrentItem(pageIndex, true);
    }

    //-----------------------------------------------------------------------------
    // Here's what the app should do to remove a view from the ViewPager.
    public void removeView(View defunctPage) {
        int pageIndex = pagerAdapter.removeView(pager, defunctPage);
        // You might want to choose what page to display, if the current page was "defunctPage".
        if (pageIndex == pagerAdapter.getCount())
            pageIndex--;
        pager.setCurrentItem(pageIndex);
    }

    //-----------------------------------------------------------------------------
    // Here's what the app should do to get the currently displayed page.
    public View getCurrentPage() {
        return pagerAdapter.getView(pager.getCurrentItem());
    }

    //-----------------------------------------------------------------------------
    // Here's what the app should do to set the currently displayed page.  "pageToShow" must
    // currently be in the adapter, or this will crash.
    public void setCurrentPage(View pageToShow) {
        pager.setCurrentItem(pagerAdapter.getItemPosition(pageToShow), true);
    }
}

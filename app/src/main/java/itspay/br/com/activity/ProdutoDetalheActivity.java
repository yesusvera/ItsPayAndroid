package itspay.br.com.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import itspay.br.com.adapter.MainPagerAdapter;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.controller.ProdutoDetalheController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Imagen;
import itspay.br.com.model.ProdutoDetalhe;
import itspay.br.com.services.ConnectPortadorService;
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

        btnMenos = (Button)findViewById(R.id.btn_menos);
        btnMais = (Button)findViewById(R.id.btn_mais);

        pagerAdapter = new MainPagerAdapter();
        pager = (AutoScrollViewPager) findViewById (R.id.view_pager);
        pager.setAdapter (pagerAdapter);
        pager.setInterval(5000);
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


        preencherValores();
        atualizaValores();
        preencherImagens();
    }

    private void preencherImagens(){

        if(produtoDetalhe.getProduto().getImagens()!=null && produtoDetalhe.getProduto().getImagens().length>0) {

            for(Imagen img: produtoDetalhe.getProduto().getImagens() ) {
                Call<ResponseBody> call = ConnectPortadorService.getService().abrirImagemProduto(img.getIdImagem(),
                        IdentityItsPay.getInstance().getToken());

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.body() != null && response.body().byteStream() != null) {
                            BitmapDrawable bitmapDrawable = new BitmapDrawable(response.body().byteStream());

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

    //-----------------------------------------------------------------------------
    // Here's what the app should do to add a view to the ViewPager.
    public void addView (View newPage)
    {
        int pageIndex = pagerAdapter.addView (newPage);
        // You might want to make "newPage" the currently displayed page:
        pager.setCurrentItem (pageIndex, true);
    }

    //-----------------------------------------------------------------------------
    // Here's what the app should do to remove a view from the ViewPager.
    public void removeView (View defunctPage)
    {
        int pageIndex = pagerAdapter.removeView (pager, defunctPage);
        // You might want to choose what page to display, if the current page was "defunctPage".
        if (pageIndex == pagerAdapter.getCount())
            pageIndex--;
        pager.setCurrentItem (pageIndex);
    }

    //-----------------------------------------------------------------------------
    // Here's what the app should do to get the currently displayed page.
    public View getCurrentPage ()
    {
        return pagerAdapter.getView (pager.getCurrentItem());
    }

    //-----------------------------------------------------------------------------
    // Here's what the app should do to set the currently displayed page.  "pageToShow" must
    // currently be in the adapter, or this will crash.
    public void setCurrentPage (View pageToShow)
    {
        pager.setCurrentItem (pagerAdapter.getItemPosition (pageToShow), true);
    }
}

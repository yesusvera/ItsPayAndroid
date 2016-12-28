package itspay.br.com.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TabHost;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.card.OnActionClickListener;
import com.dexafree.materialList.card.action.TextViewAction;
import com.dexafree.materialList.view.MaterialListView;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.OnBoomListener;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import itspay.br.com.controller.CartaoController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Credencial;
import itspay.br.com.model.LinhaExtratoCredencial;
import itspay.br.com.util.Utils;
import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;

public class CartaoActivity extends AppCompatActivity {

    private TabHost host;
    private MaterialListView mListView;
    private MaterialListView material_listViewExtrato;
    public static Credencial credencialDetalhe;
    private CartaoController cartaoController = new CartaoController(this);
    private SwipeRefreshLayout swipeRefreshExtrato;
    private String periodo = "15";
    private BoomMenuButton bmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartao);

        setTitle("Cartão " + credencialDetalhe.getNomeProduto() + " - " + credencialDetalhe.getCredencialUltimosDigitos());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mListView = (MaterialListView) findViewById(R.id.material_listview);
        material_listViewExtrato = (MaterialListView)findViewById(R.id.material_listViewExtrato);


        host = (TabHost)findViewById(R.id.tabhost);
        host.setup();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        assert bmb != null;
        bmb.setButtonEnum(ButtonEnum.TextInsideCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_6_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_6_1);
        bmb.setNormalColor(Color.parseColor("#e82c36"));

        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++)
            bmb.addBuilder(BuilderManagerFloatingButton.getTextInsideCircleButtonBuilder());


        bmb.setOnBoomListener(new OnBoomListener() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {
               if(index == 5){
                   logout();
               }
            }

            @Override
            public void onBackgroundClick() {

            }

            @Override
            public void onBoomWillHide() {

            }

            @Override
            public void onBoomDidHide() {

            }

            @Override
            public void onBoomWillShow() {

            }

            @Override
            public void onBoomDidShow() {

            }
        });
        configureTabs();

        swipeRefreshExtrato = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshExtrato);

        swipeRefreshExtrato.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cartaoController.carregarCredencialDetalhe();
                cartaoController.carregarExtrato();
            }
        });

        cartaoController.carregarExtrato();
        cartaoController.carregarCredencialDetalhe();
    }

    public void configureTabs(){
        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("15");
        spec.setContent(R.id.tab1);
        spec.setIndicator("15 Dias");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("30");
        spec.setContent(R.id.tab2);
        spec.setIndicator("30 Dias");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("45");
        spec.setContent(R.id.tab3);
        spec.setIndicator("45 Dias");
        host.addTab(spec);

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                setPeriodo(s);
                cartaoController.carregarExtrato();
            }
        });
    }

    public void configuraCartao(){
        mListView.setItemAnimator(new FadeInLeftAnimator());
        mListView.getItemAnimator().setAddDuration(300);
        mListView.getItemAnimator().setRemoveDuration(300);

        mListView.getAdapter().add(Utils.novoCartaoCredencial(credencialDetalhe, this));
    }


    public void configurarExtrato(LinhaExtratoCredencial[] extrato){

        material_listViewExtrato.setItemAnimator(new FadeInLeftAnimator());
        material_listViewExtrato.getItemAnimator().setAddDuration(300);
        material_listViewExtrato.getItemAnimator().setRemoveDuration(300);

        adicionarLinhasExtrato(extrato);

    }

    private void adicionarLinhasExtrato(LinhaExtratoCredencial[] extrato) {
        List<Card> cards = new ArrayList<>();
        for (LinhaExtratoCredencial linhaExtrato: extrato) {
            cards.add(novaLinhaExtrato(linhaExtrato));
        }
        material_listViewExtrato.getAdapter().addAll(cards);
    }


    private Card novaLinhaExtrato(final LinhaExtratoCredencial linhaExtrato) {
        DecimalFormat formatoDois = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        formatoDois.setMinimumFractionDigits(2);
        formatoDois.setParseBigDecimal (true);
        String valorTransacaoFormatado = formatoDois.format(linhaExtrato.getValorTransacao());

        int corValorTransacao = Color.RED;

        if(linhaExtrato.getSinal() == 1){
            corValorTransacao = Color.GREEN;
        }

        return new Card.Builder(this)
                .setDismissible()
                .setTag("eye")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_itspay_extrato)
                .setTitle(linhaExtrato.getDescLocal())
                .setTitleColor(Color.BLACK)
                .setSubtitle(linhaExtrato.getDataTransacaoFmtMes() + "")
                .setSubtitle2(linhaExtrato.getDescSeguimento()+ "")
                .setSubtitleColor(Color.GRAY)
                .setDescription((linhaExtrato.getSinal()==1?"+":"-") + " R$ "+ valorTransacaoFormatado + "")
                .setDescriptionColor(corValorTransacao)
                .setDescriptionGravity(Gravity.LEFT + Gravity.CENTER_VERTICAL)
                .addAction(R.id.left_text_button, new TextViewAction(this)
                        .setListener(new OnActionClickListener() {
                            @Override
                            public void onActionClicked(View view, Card card) {
                                if(card.getTag().equals("eye")){
                                    view.setBackgroundDrawable(CartaoActivity.this.getResources().getDrawable(R.drawable.eye_slash));
//                                    linhaExtrato.
                                    card.setTag("eye_slash");
                                }else{
                                   view.setBackgroundDrawable(CartaoActivity.this.getResources().getDrawable(R.drawable.eye));
                                    card.setTag("eye");
                                }

                            }
                        })
                )

//                .setDrawable(R.drawable.card1)
//                .setDrawable(cred.getDrawable())
                .endConfig()
                .build();
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public SwipeRefreshLayout getSwipeRefreshExtrato() {
        return swipeRefreshExtrato;
    }

    public void setSwipeRefreshExtrato(SwipeRefreshLayout swipeRefreshExtrato) {
        this.swipeRefreshExtrato = swipeRefreshExtrato;
    }

    public MaterialListView getMaterial_listViewExtrato() {
        return material_listViewExtrato;
    }

    public void setMaterial_listViewExtrato(MaterialListView material_listViewExtrato) {
        this.material_listViewExtrato = material_listViewExtrato;
    }

    public static Credencial getCredencialDetalhe() {
        return credencialDetalhe;
    }

    public static void setCredencialDetalhe(Credencial credencialDetalhe) {
        CartaoActivity.credencialDetalhe = credencialDetalhe;
    }

    public MaterialListView getmListView() {
        return mListView;
    }

    public void setmListView(MaterialListView mListView) {
        this.mListView = mListView;
    }

    public void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false).setTitle("ItsPay").setMessage("Tem certeza que deseja sair?")
                .setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MeusCartoesActivity.FORCE_LOGOUT = true;
                        finish();
                    }
                })
                .setNegativeButton("Não", null)
                .setCancelable(true);
        builder.create().show();
    }




}

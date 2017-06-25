package itspay.br.com.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.card.OnActionClickListener;
import com.dexafree.materialList.card.action.TextViewAction;
import com.dexafree.materialList.listeners.RecyclerItemClickListener;
import com.dexafree.materialList.view.MaterialListView;
import com.example.aplicationlib.model.Credencial;
import com.example.aplicationlib.model.LinhaExtratoCredencial;
import com.example.aplicationlib.util.usersharepreferences.SharedPreferenceUtil;
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
import itspay.br.com.util.Utils;
import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;
import jp.wasabeef.recyclerview.animators.FlipInBottomXAnimator;

public class CartaoActivity extends AppCompatActivity {

    private TabHost host;
    private MaterialListView mListView;
    private MaterialListView material_listViewExtrato;
    public static Credencial credencialDetalhe;
    private CartaoController cartaoController = new CartaoController(this);
    private SwipeRefreshLayout swipeRefreshExtrato;
    private String periodo = "15";
    private BoomMenuButton bmb;
    public TextView txtMensagemExtrato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartao);

        setTitle("Cartão " + credencialDetalhe.getNomeProduto() + " - " + credencialDetalhe.getCredencialUltimosDigitos());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mListView = (MaterialListView) findViewById(R.id.material_listview);
        mListView.addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Card card, int position) {
                if(position==0){
                    if(bmb!=null){
                        bmb.boom();
                    }
                }
            }

            @Override
            public void onItemLongClick(@NonNull Card card, int position) {
                return;
            }
        });
        material_listViewExtrato = (MaterialListView)findViewById(R.id.material_listViewExtrato);
        txtMensagemExtrato = (TextView) findViewById(R.id.text_mensagem_extrato);

        host = (TabHost)findViewById(R.id.tabhost);
        host.setup();

        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        assert bmb != null;
        bmb.setButtonEnum(ButtonEnum.TextInsideCircle);

        if(credencialDetalhe.getIdProdutoPlataforma()==2 || credencialDetalhe.getIdProdutoPlataforma()==3){

            if(credencialDetalhe.getTipoConta() == 2){

                BuilderManagerFloatingButton.imageResourceIndex = 0;
                BuilderManagerFloatingButton.imageResources = new int[]{
                        R.drawable.menu_icon4,
                        R.drawable.ic_password_card,
                        R.drawable.menu_icon5,
                        R.drawable.menu_icon6,

                };
                BuilderManagerFloatingButton.textResources = new int[]{
                        R.string.str_icone_ajustes_seguranca,
                        R.string.str_icone_alterar_senha,
                        R.string.str_icone_limites,
                        R.string.str_icone_logout,
                };
                bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_4_1);
                bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_4_1);

        }else {

                BuilderManagerFloatingButton.imageResourceIndex = 0;
                BuilderManagerFloatingButton.imageResources = new int[]{
                        R.drawable.menu_icon4,
                        R.drawable.ic_password_card,
                        R.drawable.menu_icon6,
                };
                BuilderManagerFloatingButton.textResources = new int[]{
                        R.string.str_icone_ajustes_seguranca,
                        R.string.str_icone_alterar_senha,
                        R.string.str_icone_logout,
                };
                bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_4_1);
                bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_4_1);

            }



        }else if(credencialDetalhe.getIdProdutoPlataforma()==4){


            if(credencialDetalhe.getTipoConta() == 2) {

                BuilderManagerFloatingButton.imageResourceIndex = 0;
                BuilderManagerFloatingButton.imageResources = new int[]{
                        R.drawable.menu_icon3,
                        R.drawable.menu_icon4,
                        R.drawable.menu_icon6,
                        R.drawable.menu_icon5
                };
                BuilderManagerFloatingButton.textResources = new int[]{
                        R.string.str_icone_cartoes_virtuais,
                        R.string.str_icone_ajustes_seguranca,
                        R.string.str_icone_logout,
                        R.string.str_icone_limites
                };
                bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_4_1);
                bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_4_1);

            }else {
                BuilderManagerFloatingButton.imageResourceIndex = 0;
                BuilderManagerFloatingButton.imageResources = new int[]{
                        R.drawable.menu_icon3,
                        R.drawable.menu_icon4,
                        R.drawable.menu_icon6

                };

                BuilderManagerFloatingButton.textResources = new int[]{
                        R.string.str_icone_cartoes_virtuais,
                        R.string.str_icone_ajustes_seguranca,
                        R.string.str_icone_logout
                };

                bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_3_1);
                bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_3_1);
            }

        }else {
            if (credencialDetalhe.getTipoConta() == 2) {

                BuilderManagerFloatingButton.imageResourceIndex = 0;
                BuilderManagerFloatingButton.imageResources = new int[]{
                        R.drawable.menu_icon1,
                        R.drawable.menu_icon2,
                        R.drawable.menu_icon3,
                        R.drawable.menu_icon4,
                        R.drawable.menu_icon5,
                        R.drawable.menu_icon6,
                        R.drawable.menu_icon6
                };
                BuilderManagerFloatingButton.textResources = new int[]{
                        R.string.str_icone_transferir,
                        R.string.str_icone_inserir_carga,
                        R.string.str_icone_cartoes_virtuais,
                        R.string.str_icone_ajustes_seguranca,
                        R.string.str_icone_tarifas,
                        R.string.str_icone_logout,
                        R.string.str_icone_limites
                };

                bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_7_1);
                bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_7_1);


            } else {
                BuilderManagerFloatingButton.imageResourceIndex = 0;
                BuilderManagerFloatingButton.imageResources = new int[]{
                        R.drawable.menu_icon1,
                        R.drawable.menu_icon2,
                        R.drawable.menu_icon3,
                        R.drawable.menu_icon4,
                        R.drawable.menu_icon5,
                        R.drawable.menu_icon6
                };
                BuilderManagerFloatingButton.textResources = new int[]{
                        R.string.str_icone_transferir,
                        R.string.str_icone_inserir_carga,
                        R.string.str_icone_cartoes_virtuais,
                        R.string.str_icone_ajustes_seguranca,
                        R.string.str_icone_tarifas,
                        R.string.str_icone_logout
                };
                bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_6_1);
                bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_6_1);

            }
//            bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_3_1);
//            bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_3_1);
        }

        bmb.setNormalColor(Color.parseColor("#00273f"));

        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++)
            bmb.addBuilder(BuilderManagerFloatingButton.getTextInsideCircleButtonBuilder());


        bmb.setOnBoomListener(new OnBoomListener() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {
               int strSelectedResource = BuilderManagerFloatingButton.textResources[index];

               switch(strSelectedResource){
                   case R.string.str_icone_logout: logout(); break;
                   case R.string.str_icone_transferir : escolherTipoTransferencia(); break;
                   case R.string.str_icone_inserir_carga : inserirCarga(); break;
                   case R.string.str_icone_cartoes_virtuais : cartoesVirtuais(); break;
                   case R.string.str_icone_ajustes_seguranca : ajustesDeSeguranca(); break;
                   case R.string.str_icone_tarifas : tarifas(); break;
                   case R.string.str_icone_limites : limites(); break;
                   case R.string.str_icone_alterar_senha : alterarSenhaDoCartao(); break;

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

    }

    @Override
    protected void onResume() {
        super.onResume();

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
        mListView.setItemAnimator(new FlipInBottomXAnimator());
        mListView.getItemAnimator().setAddDuration(500);
        mListView.getItemAnimator().setRemoveDuration(300);
        mListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bmb.callOnClick();
            }
        });

        mListView.getAdapter().clearAll();
        mListView.getAdapter().add(new Utils().novoCartaoCredencial(credencialDetalhe, CartaoActivity.this));

        //PEGANDO A FOTO
//        Call<ResponseBody> call = ConnectPortadorService
//                .getService()
//                .abrirPlastico(
//                        credencialDetalhe.getIdPlastico(),
//                        IdentityItsPay.getInstance().getToken());
//
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if(response.body()!=null && response.body().byteStream()!=null) {
//                    credencialDetalhe.setDrawable(new BitmapDrawable(response.body().byteStream()));
//                }
//
//                mListView.getAdapter().clearAll();
//                mListView.getAdapter().add(new Utils().novoCartaoCredencial(credencialDetalhe, CartaoActivity.this));
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                UtilsActivity.alertIfSocketException(t, CartaoActivity.this);
//
//                mListView.getAdapter().clearAll();
//                mListView.getAdapter().add(new Utils().novoCartaoCredencial(credencialDetalhe, CartaoActivity.this));
//            }
//        });


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
            corValorTransacao = getResources().getColor(R.color.green_bahamas);
        }

        return new Card.Builder(this)
                .setTag("eye")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_itspay_extrato)
                .setTitle(linhaExtrato.getDescLocal() == null || linhaExtrato.getDescLocal() == "" ? linhaExtrato.getDescTransacao():linhaExtrato.getDescLocal())
                .setTitleColor(Color.BLACK)
                .setSubtitle(linhaExtrato.getDataTransacaoFmtMes() + "")
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
                                    card.setTag("eye_slash");
                                }else{
                                   view.setBackgroundDrawable(CartaoActivity.this.getResources().getDrawable(R.drawable.eye));
                                    card.setTag("eye");
                                }

                            }
                        })
                )
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
        builder.setCancelable(false).setTitle(getString(R.string.app_name)).setMessage("Tem certeza que deseja sair?")
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

    public void escolherTipoTransferencia(){
        final CharSequence[] items = {"Para outro Cartão", "Para uma Conta Corrente"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name)).setCancelable(true).setNegativeButton("Cancelar", null)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0 : {
                                Intent intent = new Intent(CartaoActivity.this, TransferirOutroCartaoActivity.class);
                                CartaoActivity.this.startActivity(intent);
                                break;
                            }
                            case 1 : {
                                Intent intent = new Intent(CartaoActivity.this, TransferirContaCorrenteActivity.class);
                                CartaoActivity.this.startActivity(intent);
                                break;
                            }
                        }
                    }
                });
        builder.create().show();
    }

    public void inserirCarga(){
        Intent intent = new Intent(this, InserirCargaActivity.class);
        this.startActivity(intent);
    }

    public void cartoesVirtuais(){
        Intent intent = new Intent(this, CartoesVirtuaisActivity.class);
        this.startActivity(intent);
    }

    public void ajustesDeSeguranca(){
        Intent intent = new Intent(this, AjustesSegurancaCartaoActivity.class);
        this.startActivity(intent);
    }

    public void tarifas(){
        Intent intent = new Intent(this, TarifasActivity.class);
        this.startActivity(intent);
    }

    public void limites(){
        LimitesActivity.credencialDetalhe = credencialDetalhe;
        Intent intent = new Intent(this, LimitesActivity.class);
        this.startActivity(intent);
    }

    public void alterarSenhaDoCartao(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);

        dialogBuilder.setTitle("Alterar Senha");
        dialogBuilder.setMessage("Para alterar senha do cartão digite a senha de acesso");
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                InputMethodManager inputManager = (InputMethodManager) getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(edt.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                String password = SharedPreferenceUtil.getStringPreference(getBaseContext(),"lastPasswordLogged");

                if (edt.getText().toString().equals(password)){
                    Intent intent = new Intent(getApplicationContext(), TrocarSenhaCartaoActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getBaseContext(), "Erro", Toast.LENGTH_LONG).show();
                }

            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
                InputMethodManager inputManager = (InputMethodManager) getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(edt.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}
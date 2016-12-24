package itspay.br.com.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TabHost;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.view.MaterialListView;

import itspay.br.com.controller.CartaoController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Credencial;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class CartaoActivity extends AppCompatActivity {

    private TabHost host;
    private MaterialListView mListView;
    public static Card cartaoDetalhe;
    public static Credencial credencialDetalhe;
    private CartaoController cartaoController = new CartaoController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartao);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mListView = (MaterialListView) findViewById(R.id.material_listview);

        configuraCartao();

        host = (TabHost)findViewById(R.id.tabhost);
        host.setup();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        configureTabs();

        cartaoController.carregarExtrato();
    }

    public void configureTabs(){
        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("15 Dias");
        spec.setContent(R.id.tab1);
        spec.setIndicator("15 Dias");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("30 Dias");
        spec.setContent(R.id.tab2);
        spec.setIndicator("30 Dias");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("45 Dias");
        spec.setContent(R.id.tab3);
        spec.setIndicator("45 Dias");
        host.addTab(spec);
    }

    public void configuraCartao(){
        mListView.setItemAnimator(new SlideInUpAnimator());
        mListView.getItemAnimator().setAddDuration(300);
        mListView.getItemAnimator().setRemoveDuration(300);

        mListView.getAdapter().add(cartaoDetalhe);
    }


}

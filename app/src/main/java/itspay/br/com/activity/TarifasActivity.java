package itspay.br.com.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.view.MaterialListView;

import java.util.ArrayList;
import java.util.List;

import itspay.br.com.controller.TarifaController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Credencial;
import itspay.br.com.model.PerfilsTarifario;
import itspay.br.com.util.Utils;
import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;

public class TarifasActivity extends AppCompatActivity {

    private MaterialListView materialListView;
    public SwipeRefreshLayout swipeRefreshLayout;

    private TarifaController controller = new TarifaController(this);
    public Credencial credencialDetalhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarifas);

        credencialDetalhe = CartaoActivity.credencialDetalhe;

        
        materialListView = (MaterialListView) findViewById(R.id.material_listview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                controller.listarTarifas();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        controller.listarTarifas();
    }

    public void configurarTarifas(PerfilsTarifario[] listaTarifas) {
        materialListView.setItemAnimator(new FadeInLeftAnimator());
        materialListView.getItemAnimator().setAddDuration(300);
        materialListView.getItemAnimator().setRemoveDuration(300);

        adicionarLinhasTarifa(listaTarifas);
    }

    private void adicionarLinhasTarifa(PerfilsTarifario[] listaTarifas) {
        materialListView.getAdapter().clearAll();
        List<Card> cards = new ArrayList<>();
        for (PerfilsTarifario tarifa : listaTarifas) {
            cards.add(novaLinhaTarifa(tarifa));
        }
        materialListView.getAdapter().addAll(cards);
    }


    private Card novaLinhaTarifa(final PerfilsTarifario tarifa) {

        String valorTarifa = Utils.formataMoeda(tarifa.getValorTarifa());

        return new Card.Builder(this)
                .setTag("tarifa")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_itspay_tarifa)
                .setTitle(tarifa.getDescTransacaoEstendida())
                .setTitleColor(Color.BLACK)
//                .setSubtitle(tarifa.getDescTransacaoReduzida())
//                .setSubtitleColor(Color.GRAY)
                .setDescription(" R$ " + valorTarifa + "")
                .setDescriptionGravity(Gravity.CENTER_VERTICAL + Gravity.RIGHT)
                .setDividerColor(Color.LTGRAY)
                .endConfig()
                .build();
    }
}

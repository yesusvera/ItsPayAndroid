package itspay.br.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.listeners.RecyclerItemClickListener;
import com.dexafree.materialList.view.MaterialListView;

import java.util.ArrayList;
import java.util.List;

import itspay.br.com.controller.ParcelamentoController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Parcela;
import itspay.br.com.singleton.CarrinhoSingleton;
import itspay.br.com.util.Utils;
import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;

public class ParcelamentoActivity extends AppCompatActivity {

    private MaterialListView mListView;
    public SwipeRefreshLayout swipeRefreshLayout;
    public Parcela parcelas[];

    private ParcelamentoController controller = new ParcelamentoController(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcelamento);

        mListView = (MaterialListView) findViewById(R.id.material_listview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                controller.listarParcelamento();
            }
        });

        mListView.addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Card card, int position) {
                Parcela parcela = (Parcela) card.getTag();
                CarrinhoSingleton.getInstance().getRequestPedido().setQuantidadeParcelas(parcela.getQuantidadeParcelas());
                CarrinhoSingleton.getInstance().setParcela(parcela);

                Intent intent = new Intent(ParcelamentoActivity.this, ResumoPedidoActivity.class);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(@NonNull Card card, int position) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        controller.listarParcelamento();
    }

    public void listaParcelamento() {
        mListView.setItemAnimator(new FadeInLeftAnimator());
        mListView.getItemAnimator().setAddDuration(300);
        mListView.getItemAnimator().setRemoveDuration(300);
        mListView.getAdapter().clearAll();

        List<Card> cards = new ArrayList<>();

        for (Parcela parcela : parcelas) {
            Card card = new Card.Builder(this)
                    .setTag(parcela)
                    .withProvider(new CardProvider())
                    .setLayout(R.layout.material_itspay_parcelamento)
                    .setTitle(parcela.getQuantidadeParcelas() + "x")
                    .setSubtitle("R$ " + Utils.formataMoeda(parcela.getValorParcela()))
                    .setSubtitle2("R$ " + Utils.formataMoeda(parcela.getValorParcela() * parcela.getQuantidadeParcelas()))
                    .setKeepLayoutXml(true)
                    .endConfig()
                    .build();
            cards.add(card);
        }
        mListView.getAdapter().addAll(cards);
    }
}

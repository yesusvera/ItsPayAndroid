package itspay.br.com.activity;

import android.content.Intent;
import android.graphics.Color;
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

import itspay.br.com.controller.FormaEnvioController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.GetFormasEnvioResponse;
import itspay.br.com.singleton.CarrinhoSingleton;
import itspay.br.com.util.Utils;
import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;

public class FormaEnvioActivity extends AppCompatActivity {

    private MaterialListView mListView;
    public SwipeRefreshLayout swipeRefreshLayout;

    private FormaEnvioController controller = new FormaEnvioController(this);
    public GetFormasEnvioResponse[] formasEnvio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forma_envio);

        mListView = (MaterialListView) findViewById(R.id.material_listview);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                controller.listarFormasEnvio();
            }
        });

        mListView.addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Card card, int position) {
                GetFormasEnvioResponse formaEnvio = (GetFormasEnvioResponse)card.getTag();
                CarrinhoSingleton.getInstance().getRequestPedido().setValorFrete(formaEnvio.getValor());
                CarrinhoSingleton.getInstance().setFormaEnvio(formaEnvio);

                Intent intent = new Intent(FormaEnvioActivity.this, CartoesLojaActivity.class);
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
        controller.listarFormasEnvio();

    }

    public void listaFormaEnvio(){
        mListView.setItemAnimator(new FadeInLeftAnimator());
        mListView.getItemAnimator().setAddDuration(300);
        mListView.getItemAnimator().setRemoveDuration(300);
        mListView.getAdapter().clearAll();

        List<Card> cards = new ArrayList<>();

        for(GetFormasEnvioResponse formaEnvio : formasEnvio) {
            Card card = new Card.Builder(this)
                    .setTag(formaEnvio)
                    .withProvider(new CardProvider())
                    .setLayout(R.layout.material_itspay_forma_envio)
                    .setTitle(formaEnvio.getTitulo())
                    .setSubtitle(formaEnvio.getDescricao())
                    .setDescription("R$"+ Utils.formataMoeda(formaEnvio.getValor()))
                    .setTitleColor(Color.BLACK)
                    .setSubtitleColor(Color.GRAY)
                    .setDescriptionColor(Color.GREEN)
                    .setKeepLayoutXml(true)
                    .endConfig()
                    .build();
            cards.add(card);
        }
        mListView.getAdapter().addAll(cards);
    }
}

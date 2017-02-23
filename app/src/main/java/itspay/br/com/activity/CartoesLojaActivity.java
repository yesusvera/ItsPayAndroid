package itspay.br.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.listeners.RecyclerItemClickListener;
import com.dexafree.materialList.view.MaterialListView;

import java.util.ArrayList;
import java.util.List;

import itspay.br.com.controller.CartoesLojaController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Credencial;
import itspay.br.com.singleton.CarrinhoSingleton;
import itspay.br.com.util.Utils;
import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;

public class CartoesLojaActivity extends AppCompatActivity {

    private MaterialListView mListView;
    public SwipeRefreshLayout swipeRefreshLayout;

    public Credencial credenciais[];

    private CartoesLojaController controller = new CartoesLojaController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoes_loja);

        mListView = (MaterialListView) findViewById(R.id.material_listview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                controller.listarCredenciais();
            }
        });

        mListView.addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Card card, int position) {
                Credencial credencial = (Credencial) card.getTag();
                CarrinhoSingleton.getInstance().getRequestPedido().setIdCredencial(credencial.getIdCredencial());
                CarrinhoSingleton.getInstance().setCredencial(credencial);

                Intent intent = new Intent(CartoesLojaActivity.this, ParcelamentoActivity.class);
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
        controller.listarCredenciais();
    }

    public void listarCartoes() {
        mListView.setItemAnimator(new FadeInLeftAnimator());
        mListView.getItemAnimator().setAddDuration(300);
        mListView.getItemAnimator().setRemoveDuration(300);
        mListView.getAdapter().clearAll();

        List<Card> cards = new ArrayList<>();

        for (Credencial cred : credenciais) {
            cards.add(new Utils().novoCartaoCredencial(cred, this));
        }
        mListView.getAdapter().addAll(cards);
    }
}

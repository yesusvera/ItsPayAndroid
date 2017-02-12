package itspay.br.com.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.view.MaterialListView;

import java.util.ArrayList;
import java.util.List;

import itspay.br.com.controller.EnderecoController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.EnderecoPessoa;
import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;

public class EnderecoActivity extends AppCompatActivity {

    public EnderecoPessoa[] enderecos;
    private MaterialListView mListView;
    public SwipeRefreshLayout swipeRefreshLayout;
    private EnderecoController controller = new EnderecoController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco);

        mListView = (MaterialListView) findViewById(R.id.material_listview);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                controller.buscarEnderecos();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        controller.buscarEnderecos();
    }

    public void listarEnderecos(){
        mListView.setItemAnimator(new FadeInLeftAnimator());
        mListView.getItemAnimator().setAddDuration(300);
        mListView.getItemAnimator().setRemoveDuration(300);
        mListView.getAdapter().clearAll();

        List<Card> cards = new ArrayList<>();

        for(EnderecoPessoa endTmp : enderecos) {
            Card card = new Card.Builder(this)
                    .setTag(endTmp)
                    .withProvider(new CardProvider())
                    .setLayout(R.layout.material_item_simples)
                    .setTitle(endTmp.getLogradouro()    + " " +
                              endTmp.getNumero()        + " " +
                              endTmp.getComplemento()   + " " +
                              endTmp.getBairro()        + " ")
                    .setSubtitle(endTmp.getCidade() + "/" + endTmp.getUf())
                    .setTitleColor(Color.BLACK)
                    .setSubtitleColor(Color.GRAY)
                    .endConfig()
                    .build();
            cards.add(card);
        }
        mListView.getAdapter().addAll(cards);
    }
}

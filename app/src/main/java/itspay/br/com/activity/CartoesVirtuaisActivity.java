package itspay.br.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.view.MaterialListView;

import java.util.ArrayList;
import java.util.List;

import itspay.br.com.controller.CartoesVirtuaisController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Credencial;
import itspay.br.com.util.Utils;
import jp.wasabeef.recyclerview.animators.FlipInBottomXAnimator;

public class CartoesVirtuaisActivity extends AppCompatActivity {

    private MaterialListView mListView;
    private Button requisitarButton;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CartoesVirtuaisController controller = new CartoesVirtuaisController(this);
    private Credencial credenciais[];
    private Credencial credencialDetalhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoes_virtuais);

        credencialDetalhe = CartaoActivity.credencialDetalhe;

        mListView = (MaterialListView)findViewById(R.id.material_listview);
        requisitarButton = (Button)findViewById(R.id.requisitar_button);


        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                controller.listarCartoesVirtuais();
            }
        });

        requisitarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartoesVirtuaisActivity.this, NovoCartaoVirtualActivity.class);
                CartoesVirtuaisActivity.this.startActivity(intent);
            }
        });
    }

    public void configurarCartoes(){

        mListView.setItemAnimator(new FlipInBottomXAnimator());
        mListView.getItemAnimator().setAddDuration(300);
        mListView.getItemAnimator().setRemoveDuration(300);

        adicionarCartoes();

    }


    private void adicionarCartoes() {

        mListView.getAdapter().clearAll();

        List<Card> cards = new ArrayList<>();
        for(int i=credenciais.length-1; i>=0;i--){
            Credencial cred = credenciais[i];
            cards.add(Utils.novoCartaoVirtual(cred, this));
        }
        mListView.getAdapter().addAll(cards);
    }

    @Override
    protected void onResume() {
        super.onResume();
        controller.listarCartoesVirtuais();
    }

    public MaterialListView getmListView() {
        return mListView;
    }

    public void setmListView(MaterialListView mListView) {
        this.mListView = mListView;
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    public void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    public Credencial[] getCredenciais() {
        return credenciais;
    }

    public void setCredenciais(Credencial[] credenciais) {
        this.credenciais = credenciais;
    }

    public Credencial getCredencialDetalhe() {
        return credencialDetalhe;
    }

    public void setCredencialDetalhe(Credencial credencialDetalhe) {
        this.credencialDetalhe = credencialDetalhe;
    }
}

package itspay.br.com.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.view.MaterialListView;

import java.util.ArrayList;
import java.util.List;

import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.controller.CartoesVirtuaisController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Credencial;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.Utils;
import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartoesVirtuaisActivity extends AppCompatActivity {

    private MaterialListView mListView;
    private Button requisitarButton;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CartoesVirtuaisController controller = new CartoesVirtuaisController(this);
    private Credencial credenciais[];
    private int countConexaoServicoPlastico;
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
    }

    public void configurarCartoes(){

        mListView.setItemAnimator(new FadeInLeftAnimator());
        mListView.getItemAnimator().setAddDuration(300);
        mListView.getItemAnimator().setRemoveDuration(300);

        countConexaoServicoPlastico = credenciais.length;

        for(final Credencial cred : credenciais){
            Call<ResponseBody> call = ConnectPortadorService
                    .getService()
                    .abrirPlastico(
                            cred.getIdPlastico(),
                            IdentityItsPay.getInstance().getToken());

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    countConexaoServicoPlastico--;

                    if(response.body()!=null && response.body().byteStream()!=null) {
                        cred.setDrawable(new BitmapDrawable(response.body().byteStream()));
                    }

                    if(countConexaoServicoPlastico==0){
                        adicionarCartoes();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    countConexaoServicoPlastico--;

                    if(countConexaoServicoPlastico==0){
                        adicionarCartoes();
                    }
                }
            });
        }
    }


    private void adicionarCartoes() {
        List<Card> cards = new ArrayList<>();
        for (Credencial cred: credenciais) {
            cards.add(Utils.novoCartaoCredencial(cred, this));
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

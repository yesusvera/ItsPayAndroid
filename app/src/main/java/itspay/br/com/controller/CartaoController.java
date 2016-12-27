package itspay.br.com.controller;

import itspay.br.com.activity.CartaoActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.model.LinhaExtratoCredencial;
import itspay.br.com.services.ConnectPortadorService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 24/12/16.
 */

public class CartaoController extends BaseActivityController<CartaoActivity> {
    public CartaoController(CartaoActivity activity) {
        super(activity);
    }

    public void carregarExtrato(){
        activity.getSwipeRefreshExtrato().setRefreshing(true);
        activity.getMaterial_listViewExtrato().getAdapter().clearAll();

        Call<LinhaExtratoCredencial[]> call =  ConnectPortadorService.getService().extratoCredencial(
                    activity.credencialDetalhe.getIdCredencial(),
                    activity.getPeriodo(),
                    IdentityItsPay.getInstance().getToken());

        call.enqueue(new Callback<LinhaExtratoCredencial[]>() {
            @Override
            public void onResponse(Call<LinhaExtratoCredencial[]> call, Response<LinhaExtratoCredencial[]> response) {

                try {
                    activity.configurarExtrato(response.body());
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    activity.getSwipeRefreshExtrato().setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<LinhaExtratoCredencial[]> call, Throwable t) {
                t.printStackTrace();
                activity.getSwipeRefreshExtrato().setRefreshing(false);
            }
        });

    }
}

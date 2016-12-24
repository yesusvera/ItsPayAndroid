package itspay.br.com.controller;

import itspay.br.com.activity.CartaoActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.model.GetExtratoCredencial;
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
        Call<GetExtratoCredencial> call =  ConnectPortadorService.getService().extratoCredencial(
                    activity.credencialDetalhe.getIdCredencial(),
                    "45",
                    IdentityItsPay.getInstance().getToken());

        call.enqueue(new Callback<GetExtratoCredencial>() {
            @Override
            public void onResponse(Call<GetExtratoCredencial> call, Response<GetExtratoCredencial> response) {
                response.toString();
            }

            @Override
            public void onFailure(Call<GetExtratoCredencial> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}

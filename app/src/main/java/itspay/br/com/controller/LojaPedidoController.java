package itspay.br.com.controller;

import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.fragment.LojaPedidosFragment;
import itspay.br.com.model.Pedido;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.ItsPayConstants;
import itspay.br.com.util.UtilsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 17/01/17.
 */

public class LojaPedidoController {

    public void listarPedidos(final LojaPedidosFragment rootView){

        Call<Pedido[]> call =  ConnectPortadorService.getService().buscarPedidos(
                IdentityItsPay.getInstance().getLoginPortador().getCpf(),
                ItsPayConstants.ID_PROCESSADORA,
                ItsPayConstants.ID_INSTITUICAO,
                IdentityItsPay.getInstance().getToken()
        );

        call.enqueue(new Callback<Pedido[]>() {
            @Override
            public void onResponse(Call<Pedido[]> call, Response<Pedido[]> response) {

                if(response.body() !=null){
                    rootView.configurarPedidos(response.body());
                }else{
                    UtilsActivity.alertMsg(response.errorBody(), rootView.getContext());
                }
                rootView.swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<Pedido[]> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, rootView.getContext());
            }
        });

    }

}

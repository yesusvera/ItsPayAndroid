package itspay.br.com.controller;

import java.util.ArrayList;

import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.fragment.LojaProdutosFragment;
import itspay.br.com.model.ParceiroResponse;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.ItsPayConstants;
import itspay.br.com.util.UtilsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 24/01/17.
 */

public class LojaProdutosController {

    public void listaParceiros(final LojaProdutosFragment fragment){
        fragment.materialListView.getAdapter().clearAll();
        fragment.swipeRefreshLayout.setRefreshing(true);
        Call<ArrayList<ParceiroResponse>> call = ConnectPortadorService.getService().getParceiros(
                                ItsPayConstants.ID_PROCESSADORA,
                                ItsPayConstants.ID_INSTITUICAO,
                                IdentityItsPay.getInstance().getToken());


        call.enqueue(new Callback<ArrayList<ParceiroResponse> >() {
            @Override
            public void onResponse(Call<ArrayList<ParceiroResponse>>call, Response<ArrayList<ParceiroResponse> > response) {

                if(response.body()!=null){
                    fragment.listaParceiroResponse = response.body();
                    fragment.listarProdutos();
                }else{
                    UtilsActivity.alertMsg(response.errorBody(), fragment.getContext());
                }
                fragment.swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ArrayList<ParceiroResponse>> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, fragment.getContext());
                fragment.swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


}

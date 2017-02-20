package itspay.br.com.controller;

import itspay.br.com.activity.ParcelamentoActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.model.ParcelasResponse;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.singleton.CarrinhoSingleton;
import itspay.br.com.util.UtilsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 16/02/17.
 */

public class ParcelamentoController extends BaseActivityController<ParcelamentoActivity>{
    public ParcelamentoController(ParcelamentoActivity activity) {
        super(activity);
    }

    public void listarParcelamento(){

        activity.swipeRefreshLayout.setRefreshing(true);

        Long idParceiro = null;
        CarrinhoSingleton carrinho = CarrinhoSingleton.getInstance();

        try {
            idParceiro = carrinho.getListaProdutosCarrinho().get(0).getProdutoDetalhe().getParceiroResponse().getIdParceiro();
        }catch (NullPointerException npe){
            npe.printStackTrace();
        }
        Call<ParcelasResponse> call =
                ConnectPortadorService
                        .getService()
                        .getParcelamento(
                                idParceiro,
                                carrinho.getValorTotal(),
                                IdentityItsPay.getInstance().getToken()
                            );

        call.enqueue(new Callback<ParcelasResponse>() {
            @Override
            public void onResponse(Call<ParcelasResponse> call, Response<ParcelasResponse> response) {
                if(response.body()!=null){
                    activity.parcelas = response.body().getParcelas();
                    activity.listaParcelamento();
                }else{
                    UtilsActivity.alertMsg(response.errorBody(), activity);
                }

                activity.swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ParcelasResponse> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
                activity.swipeRefreshLayout.setRefreshing(false);
            }
        });

    }
}

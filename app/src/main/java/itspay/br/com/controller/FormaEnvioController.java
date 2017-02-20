package itspay.br.com.controller;

import itspay.br.com.activity.FormaEnvioActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.model.GetFormasEnvioResponse;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.singleton.CarrinhoSingleton;
import itspay.br.com.util.UtilsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 12/02/17.
 */

public class FormaEnvioController extends BaseActivityController<FormaEnvioActivity> {

    public FormaEnvioController(FormaEnvioActivity activity) {
        super(activity);
    }

    public void listarFormasEnvio(){
        activity.swipeRefreshLayout.setRefreshing(true);

        Long idParceiro = null;
        CarrinhoSingleton carrinho =CarrinhoSingleton.getInstance();

        try {
            idParceiro = carrinho.getListaProdutosCarrinho().get(0).getProdutoDetalhe().getParceiroResponse().getIdParceiro();
        }catch (NullPointerException npe){
            npe.printStackTrace();
        }

        if(idParceiro == null){
            activity.swipeRefreshLayout.setRefreshing(false);
            return;
        }

        Call<GetFormasEnvioResponse[]> call =
                ConnectPortadorService.getService().getFormasEnvio(
                                                    idParceiro,
                                                    carrinho.getRequestPedido().getIdEndereco(),
                                                    IdentityItsPay.getInstance().getToken());

        call.enqueue(new Callback<GetFormasEnvioResponse[]>() {
            @Override
            public void onResponse(Call<GetFormasEnvioResponse[]> call, Response<GetFormasEnvioResponse[]> response) {

                if(response.body()!=null){
                    activity.formasEnvio = response.body();
                    activity.listaFormaEnvio();
                }else{
                    UtilsActivity.alertMsg(response.errorBody(), activity);
                }
                activity.swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<GetFormasEnvioResponse[]> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
                activity.swipeRefreshLayout.setRefreshing(false);
            }
        });

    }


}

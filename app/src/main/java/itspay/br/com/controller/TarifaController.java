package itspay.br.com.controller;

import itspay.br.com.activity.TarifasActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.model.GetPerfilTarifarioResponse;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.UtilsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 06/01/17.
 */

public class TarifaController extends BaseActivityController<TarifasActivity> {

    public TarifaController(TarifasActivity activity) {
        super(activity);
    }

    public void listarTarifas(){
        activity.swipeRefreshLayout.setRefreshing(true);
        Call<GetPerfilTarifarioResponse> call = ConnectPortadorService
                                            .getService()
                                            .listaTarifas(activity.credencialDetalhe.getIdConta(),
                                                    IdentityItsPay.getInstance().getToken());

        call.enqueue(new Callback<GetPerfilTarifarioResponse>() {
            @Override
            public void onResponse(Call<GetPerfilTarifarioResponse> call, Response<GetPerfilTarifarioResponse> response) {
                if(response.body()!=null){
                    activity.configurarTarifas(response.body().getPerfilsTarifarios());
                }else{
                    UtilsActivity.alertMsg(response.errorBody(), activity);
                }
                activity.swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<GetPerfilTarifarioResponse> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
                activity.swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}

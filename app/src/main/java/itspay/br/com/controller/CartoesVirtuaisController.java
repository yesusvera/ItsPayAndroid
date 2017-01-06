package itspay.br.com.controller;

import android.util.Log;

import itspay.br.com.activity.CartoesVirtuaisActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.model.GetCredenciaisResponse;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.UtilsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 01/01/17.
 */

public class CartoesVirtuaisController extends BaseActivityController<CartoesVirtuaisActivity> {

    public CartoesVirtuaisController(CartoesVirtuaisActivity activity) {
        super(activity);
    }

    public void listarCartoesVirtuais() {

        activity.getSwipeRefreshLayout().setRefreshing(true);

        IdentityItsPay identity = IdentityItsPay.getInstance();

        Call<GetCredenciaisResponse> callListaCredencial
                = ConnectPortadorService
                .getService()
                .listaCartoesVirtuais(activity.getCredencialDetalhe().getIdConta(),
                        identity.getLoginPortadorResponse().getToken()
                );

        callListaCredencial.enqueue(new Callback<GetCredenciaisResponse>() {
            @Override
            public void onResponse(Call<GetCredenciaisResponse> call, Response<GetCredenciaisResponse> response) {
                if (response.body() != null) {
                    try {
                        activity.setCredenciais(response.body().getCredenciais());
                        activity.configurarCartoes();
                        Log.i("teste", response.toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    UtilsActivity.alertMsg(response.errorBody(), activity);
                }

                activity.getSwipeRefreshLayout().setRefreshing(false);
            }

            @Override
            public void onFailure(Call<GetCredenciaisResponse> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
                t.printStackTrace();
                activity.getSwipeRefreshLayout().setRefreshing(false);
            }
        });
    }
}

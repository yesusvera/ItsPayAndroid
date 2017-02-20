package itspay.br.com.controller;

import itspay.br.com.activity.CartoesLojaActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.model.GetCredenciaisResponse;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.ItsPayConstants;
import itspay.br.com.util.UtilsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 12/02/17.
 */

public class CartoesLojaController extends BaseActivityController<CartoesLojaActivity> {

    public CartoesLojaController(CartoesLojaActivity activity) {
        super(activity);
    }

    public void listarCredenciais() {
        activity.swipeRefreshLayout.setRefreshing(true);

        Call<GetCredenciaisResponse> call =
                ConnectPortadorService.getService().listaCredenciaisLoja(
                        IdentityItsPay.getInstance().getLoginPortador().getCpf().replace(".", "").replace("-", ""),
                        ItsPayConstants.TIPO_PESSOA,
                        ItsPayConstants.ID_PROCESSADORA,
                        ItsPayConstants.ID_INSTITUICAO,
                        IdentityItsPay.getInstance().getLoginPortadorResponse().getToken());

        call.enqueue(new Callback<GetCredenciaisResponse>() {
            @Override
            public void onResponse(Call<GetCredenciaisResponse> call, Response<GetCredenciaisResponse> response) {
                if (response.body() != null) {
                    activity.credenciais = response.body().getCredenciais();
                    activity.listarCartoes();
                } else {
                    UtilsActivity.alertMsg(response.errorBody(), activity);
                }

                activity.swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<GetCredenciaisResponse> call, Throwable t) {
                activity.swipeRefreshLayout.setRefreshing(false);
                UtilsActivity.alertIfSocketException(t, activity);
            }
        });
    }
}

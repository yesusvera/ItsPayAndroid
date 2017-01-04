package itspay.br.com.controller;

import itspay.br.com.activity.CargaInseridaActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.UtilsActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 30/12/16.
 */

public class CargaInseridaController extends BaseActivityController<CargaInseridaActivity> {
    public CargaInseridaController(CargaInseridaActivity activity) {
        super(activity);
    }

    public void enviarBoletoEmail(){

        Call<ResponseBody>  call = ConnectPortadorService
                .getService()
                .enviarBoletoEmail(activity.getGerarBoletoCarga(),
                        IdentityItsPay.getInstance().getToken());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.body()!=null){
                    UtilsActivity.alertMsg(response.body(), activity);
                }else {
                    UtilsActivity.alertMsg(response.errorBody(), activity);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
                t.printStackTrace();
            }
        });
    }

}

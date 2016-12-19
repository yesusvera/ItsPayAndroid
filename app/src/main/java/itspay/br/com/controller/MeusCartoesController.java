package itspay.br.com.controller;

import android.util.Log;

import java.util.List;

import itspay.br.com.activity.MeusCartoesActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.model.Credencial;
import itspay.br.com.model.GetCredenciaisResponse;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.ItsPayConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 19/12/16.
 */

public class MeusCartoesController extends BaseActivityController<MeusCartoesActivity> {

    public MeusCartoesController(MeusCartoesActivity activity){
        super(activity);
    }

    public void listarCredenciais(){

        IdentityItsPay identity = IdentityItsPay.getInstance();

        Call<GetCredenciaisResponse> callListaCredencial
                =  ConnectPortadorService
                        .getService()
                        .listaCredenciais(
                                identity.getLoginPortador().getCpf().replace(".", "").replace("-", ""),
                                ItsPayConstants.TIPO_PESSOA,
                                ItsPayConstants.ID_PROCESSADORA,
                                ItsPayConstants.ID_INSTITUICAO,
                                identity.getLoginPortadorResponse().getToken()
                                        );

        callListaCredencial.enqueue(new Callback<GetCredenciaisResponse>() {
            @Override
            public void onResponse(Call<GetCredenciaisResponse> call, Response<GetCredenciaisResponse> response) {
                if (response != null){
                    Log.i("teste", response.toString());
                }
            }

            @Override
            public void onFailure(Call<GetCredenciaisResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

}

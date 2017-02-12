package itspay.br.com.controller;

import itspay.br.com.activity.EnderecoActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.model.EnderecoPessoa;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.ItsPayConstants;
import itspay.br.com.util.UtilsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 11/02/17.
 */

public class EnderecoController extends BaseActivityController<EnderecoActivity> {

    public EnderecoController(EnderecoActivity activity) {
        super(activity);
    }

    public void buscarEnderecos(){
        activity.swipeRefreshLayout.setRefreshing(true);
        Call<EnderecoPessoa[]> call =
                ConnectPortadorService.getService().getEnderecoPortador(
                        IdentityItsPay.getInstance().getLoginPortador().getCpf().replace(".", "").replace("-", ""),
                        ItsPayConstants.TIPO_PESSOA,
                        ItsPayConstants.ID_PROCESSADORA,
                        ItsPayConstants.ID_INSTITUICAO,
                        ItsPayConstants.STATUS,
                        IdentityItsPay.getInstance().getToken());

        call.enqueue(new Callback<EnderecoPessoa[]>() {
            @Override
            public void onResponse(Call<EnderecoPessoa[]> call, Response<EnderecoPessoa[]> response) {
                if(response.body()!=null){
                    activity.enderecos = response.body();
                    activity.listarEnderecos();
                }else{
                    UtilsActivity.alertMsg(response.errorBody(), activity);
                }
                activity.swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<EnderecoPessoa[]> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
                activity.swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}

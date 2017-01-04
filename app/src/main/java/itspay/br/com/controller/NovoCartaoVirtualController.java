package itspay.br.com.controller;

import android.content.DialogInterface;

import itspay.br.com.activity.NovoCartaoVirtualActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.model.CredencialGerada;
import itspay.br.com.model.GerarCredencialRequest;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.ItsPayConstants;
import itspay.br.com.util.UtilsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 02/01/17.
 */

public class NovoCartaoVirtualController extends BaseActivityController<NovoCartaoVirtualActivity>{
    public NovoCartaoVirtualController(NovoCartaoVirtualActivity activity) {
        super(activity);
    }

    public void requisitarNovoCartao(){
        GerarCredencialRequest request = new GerarCredencialRequest();
        request.setIdConta(activity.getCredencialDetalhe().getIdConta());
        request.setIdPessoa(activity.getCredencialDetalhe().getIdPessoa());
        request.setVirtualApelido(activity.getNomeCartaoVirtual().getText().toString());
        request.setVirtualMesesValidade(new Long(activity.getQtdMesesSelecionado()));
        request.setVirtual(true);
        request.setIdUsuario(ItsPayConstants.ID_USUARIO);

        Call<CredencialGerada> call =
                ConnectPortadorService
                        .getService()
                        .novoCartaoVirtual(request,
                                           IdentityItsPay.getInstance().getToken());

        call.enqueue(new Callback<CredencialGerada>() {
            @Override
            public void onResponse(Call<CredencialGerada> call, Response<CredencialGerada> response) {
                if(response.body()!=null && response.body().getCredencial()!=null) {
                    activity.finish();
                }else{
                    UtilsActivity.alertMsg(response.errorBody(), activity, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            activity.finish();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<CredencialGerada> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
                activity.finish();
            }
        });
    }
}
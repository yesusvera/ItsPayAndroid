package itspay.br.com.controller;

import android.content.DialogInterface;

import itspay.br.com.activity.TransferirOutroCartaoActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.model.GetInfoPortadorCredencialRequest;
import itspay.br.com.model.PortadorCredencial;
import itspay.br.com.model.TransferenciaMesmaInstituicao;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.EncriptSHA512;
import itspay.br.com.util.ItsPayConstants;
import itspay.br.com.util.UtilsActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 29/12/16.
 */

public class TransferirOutroCartaoController extends BaseActivityController<TransferirOutroCartaoActivity> {

    public TransferirOutroCartaoController(TransferirOutroCartaoActivity activity) {
        super(activity);
    }

    public void preencherNomePortadorCredencial(){
        GetInfoPortadorCredencialRequest request = new GetInfoPortadorCredencialRequest();

        String credencial = activity.getNumeroCartaoDestino().getText().toString().replace(".", "");
        credencial = EncriptSHA512.encript(credencial);

        request.setCredencial(credencial);

        Call<PortadorCredencial> call = ConnectPortadorService.getService()
                        .getPortadorCredencial(request, IdentityItsPay.getInstance().getToken());

        call.enqueue(new Callback<PortadorCredencial>() {
            @Override
            public void onResponse(Call<PortadorCredencial> call, Response<PortadorCredencial> response) {
                if(response.body()!=null){
                    activity.getFavorecido().setText(response.body().getNome());
                }else {
                    UtilsActivity.alertMsg(response.errorBody(), activity);
                }
            }

            @Override
            public void onFailure(Call<PortadorCredencial> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
            }
        });
    }

    public void transferirParaOutroCartao(){

        activity.dismissKeyboard();
        activity.setLoading(true);

        String credencialDestino = activity.getNumeroCartaoDestino().getText().toString().replace(".", "");
        credencialDestino = EncriptSHA512.encript(credencialDestino);

        TransferenciaMesmaInstituicao request = new TransferenciaMesmaInstituicao();
        request.setContaOrigem(activity.getCredencialDetalhe().getContaPagamento());
        request.setIdCredencialOrigem(activity.getCredencialDetalhe().getIdCredencial());
        request.setCredencialDestino(credencialDestino);
        request.setIdInstituicaoOrigem(ItsPayConstants.ID_INSTITUICAO);
        request.setValorTransferencia(Double.parseDouble(activity.getValor().getText().toString().replace("R$","").replace(".", "").replace(",",".")));
        request.setPinCredencialOrigem(EncriptSHA512.encript(activity.getSenhaCartao().getText().toString() +  IdentityItsPay.getInstance().getToken()));

        Call<ResponseBody> call = ConnectPortadorService.getService()
                                        .transferenciaOutroCartao(request, IdentityItsPay.getInstance().getToken() );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.body()!=null){
                    UtilsActivity.alertMsg(response.body(), activity, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            activity.finish();
                        }
                    });
                }else {
                    UtilsActivity.alertMsg(response.errorBody(), activity);
                }

                activity.setLoading(false);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
                activity.setLoading(false);
            }
        });
    }
}

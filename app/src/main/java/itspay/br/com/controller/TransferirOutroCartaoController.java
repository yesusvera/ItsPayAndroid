package itspay.br.com.controller;

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
                    UtilsActivity.alertIfError(response.errorBody(), activity);
                }

//                if(response.errorBody() != null){
//                        try {
//                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
//                            String msg = jsonObject.getString("msg");
//
//                            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//                            builder.setMessage(msg)
//                                    .setPositiveButton("OK", null);
//                            builder.create().show();
//                        }catch (IOException ex){
//                            ex.printStackTrace();
//                        }catch (JSONException ex){
//                            ex.printStackTrace();
//                        }
//                    }
//                }
            }

            @Override
            public void onFailure(Call<PortadorCredencial> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void transferirParaOutroCartao(){
        String credencialDestino = activity.getNumeroCartaoDestino().getText().toString().replace(".", "");
        credencialDestino = EncriptSHA512.encript(credencialDestino);

        TransferenciaMesmaInstituicao request = new TransferenciaMesmaInstituicao();
        request.setContaOrigem(activity.getCredencialDetalhe().getContaPagamento());
        request.setIdCredencialOrigem(activity.getCredencialDetalhe().getIdCredencial());
        request.setCredencialDestino(credencialDestino);
        request.setIdInstituicaoOrigem(ItsPayConstants.ID_INSTITUICAO);
        request.setValorTransferencia(Double.parseDouble(activity.getValor().getText().toString().replace("R$","").replace(".", "").replace(",",".")));

        request.setPinCredencialOrigem(activity.getSenhaCartao().getText().toString());

        Call<ResponseBody> call = ConnectPortadorService.getService()
                                        .transferenciaOutroCartao(request, IdentityItsPay.getInstance().getToken() );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.body()!=null){

                }else {
                    UtilsActivity.alertIfError(response.errorBody(), activity);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

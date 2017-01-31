package itspay.br.com.controller;

import android.content.DialogInterface;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import itspay.br.com.activity.TransferirContaCorrenteActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.model.Banco;
import itspay.br.com.model.TransferenciaContaCorrente;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.EncriptSHA512;
import itspay.br.com.util.ItsPayConstants;
import itspay.br.com.util.UtilsActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 30/12/16.
 */
public class TransferirContaCorrenteController extends BaseActivityController<TransferirContaCorrenteActivity> {

    public TransferirContaCorrenteController(TransferirContaCorrenteActivity activity) {
        super(activity);
    }

    public void carregarListaBancos() {
        Call<Banco[]> call = ConnectPortadorService
                .getService()
                .listaBancos(IdentityItsPay.getInstance().getToken());

        call.enqueue(new Callback<Banco[]>() {
            @Override
            public void onResponse(Call<Banco[]> call, Response<Banco[]> response) {
                if (response.body() != null) {
                    List<String> listaBancos = new ArrayList<String>();

                    activity.setBancos(response.body());

                    for (Banco banco : response.body()) {
                        listaBancos.add(banco.getDescBanco() + " - " + banco.getIdBanco());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            activity, android.R.layout
                            .simple_list_item_1, listaBancos);

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    activity.getBancoFavorecidoSpinner().setAdapter(adapter);

                } else {
                    UtilsActivity.alertMsg(response.errorBody(), activity);
                }
            }

            @Override
            public void onFailure(Call<Banco[]> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
            }
        });
    }

    public void transferir() {
        activity.setLoading(true);

        TransferenciaContaCorrente request = new TransferenciaContaCorrente();
        request.setPinCredencialOrigem(EncriptSHA512.encript(activity.getSenhaCartao().getText().toString() +  IdentityItsPay.getInstance().getToken()));
        request.setValorTransferencia(Double.parseDouble(activity.getValor().getText().toString().replace("R$", "").replace(".", "").replace(",", ".")));
        request.setIdCredencialOrigem(activity.getCredencialDetalhe().getIdCredencial());
        request.setIdInstituicaoOrigem(ItsPayConstants.ID_INSTITUICAO);
        try {
            request.setIdAgenciaDestino(Long.parseLong(activity.getAgencia().getText().toString()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        try {
            request.setContaCorrenteDestino(activity.getConta().getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        if(activity.getBancoSelecionado()!=null) {
            request.setIdBancoDestino(activity.getBancoSelecionado().getIdBanco());
        }

        request.setIdInstituicaoOrigem(ItsPayConstants.ID_INSTITUICAO);

        Call<ResponseBody> call = ConnectPortadorService
                .getService()
                .transferenciaContaCorrente(
                        request,
                        IdentityItsPay.getInstance().getToken()
                );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    UtilsActivity.alertMsg(response.body(), activity, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            activity.finish();
                        }
                    });

                } else {
                    UtilsActivity.alertMsg(response.errorBody(), activity);
                }

                activity.setLoading(false);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
            }
        });
    }
}

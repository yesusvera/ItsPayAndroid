package itspay.br.com.controller;

import android.content.Intent;

import com.example.aplicationlib.model.BoletoCarga;
import com.example.aplicationlib.model.GerarBoletoCarga;
import com.example.aplicationlib.util.ItsPayConstants;

import java.text.SimpleDateFormat;
import java.util.Date;

import itspay.br.com.activity.CargaInseridaActivity;
import itspay.br.com.activity.InserirCargaActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.UtilsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 30/12/16.
 */

public class InserirCargaController extends BaseActivityController<InserirCargaActivity> {

    public InserirCargaController(InserirCargaActivity activity) {
        super(activity);
    }


    public void gerarBoleto(){

        mProgresDialogUtil.show("Inserindo Carga","Aguarde.");

        final GerarBoletoCarga request = new GerarBoletoCarga();
        request.setContaPagamento(activity.getCredencialDetalhe().getContaPagamento());
        request.setDocumentoPortador(IdentityItsPay.getInstance().getLoginPortador().getCpf());
        request.setEmailDestino(activity.getCredencialDetalhe().getEmail());
        request.setIdInstituicao(ItsPayConstants.ID_INSTITUICAO);
        request.setIdProcessadora(ItsPayConstants.ID_PROCESSADORA);

        try {
            request.setValor(Double.parseDouble(activity.getValor().getText().toString().replace("R$", "").replace(".", "").replace(",", ".")));
        }catch (NumberFormatException nfe){
            nfe.printStackTrace();
        }
        request.setIdProduto(activity.getCredencialDetalhe().getIdProduto());
        request.setVencimento(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));

        Call<BoletoCarga> call =
                ConnectPortadorService
                        .getService()
                        .gerarLinhaDigitavel(request, IdentityItsPay.getInstance().getToken());

        call.enqueue(new Callback<BoletoCarga>() {
            @Override
            public void onResponse(Call<BoletoCarga> call, Response<BoletoCarga> response) {
                if(response.body()!=null){
                    redirecionaCargaInserida(response.body(), request);
                }else{
                    UtilsActivity.alertMsg(response.errorBody(), activity);
                }
                mProgresDialogUtil.dismiss();
            }

            @Override
            public void onFailure(Call<BoletoCarga> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
                mProgresDialogUtil.dismiss();
                t.printStackTrace();
            }
        });
    }

    public void redirecionaCargaInserida(BoletoCarga boletoCarga, GerarBoletoCarga gerarBoletoCarga){
        CargaInseridaActivity.gerarBoletoCarga = gerarBoletoCarga;
        CargaInseridaActivity.boleto = boletoCarga;

        Intent intent = new Intent(activity, CargaInseridaActivity.class);
        activity.startActivity(intent);
    }
}

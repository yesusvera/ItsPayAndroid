package itspay.br.com.controller;

import android.text.format.DateFormat;
import android.view.View;

import java.util.Calendar;
import java.util.Date;

import itspay.br.com.activity.CartaoActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.model.Credencial;
import itspay.br.com.model.LinhaExtratoCredencial;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.UtilsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 24/12/16.
 */

public class CartaoController extends BaseActivityController<CartaoActivity> {
    public CartaoController(CartaoActivity activity) {
        super(activity);
    }

    public void carregarExtrato() {
        activity.txtMensagemExtrato.setVisibility(View.GONE);
        activity.getSwipeRefreshExtrato().setRefreshing(true);
        activity.getMaterial_listViewExtrato().getAdapter().clearAll();

        DateFormat df = new DateFormat();
        String dtFinal = df.format("yyyy-MM-dd", new Date()).toString();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, -(new Integer(activity.getPeriodo())));

        String dtInicial = df.format("yyyy-MM-dd", calendar.getTime()).toString();

//        Call<LinhaExtratoCredencial[]> call =  ConnectPortadorService.getService().extratoCredencial(
//                    activity.credencialDetalhe.getIdCredencial(),
//                    activity.getPeriodo(),
//                    IdentityItsPay.getInstance().getToken());

        Call<LinhaExtratoCredencial[]> call = ConnectPortadorService.getService()
                .extratoPeriodo(
                        activity.credencialDetalhe.getIdCredencial(),
                        dtInicial,
                        dtFinal,
                        IdentityItsPay.getInstance().getToken());

        call.enqueue(new Callback<LinhaExtratoCredencial[]>() {
            @Override
            public void onResponse(Call<LinhaExtratoCredencial[]> call, Response<LinhaExtratoCredencial[]> response) {

                try {
                    if (response.body() != null) {
                        activity.configurarExtrato(response.body());

                        if(response.body().length == 0 ){
                            activity.txtMensagemExtrato.setVisibility(View.VISIBLE);
                        }
                    } else {
                        UtilsActivity.alertMsg(response.errorBody(), activity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    activity.getSwipeRefreshExtrato().setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<LinhaExtratoCredencial[]> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
                t.printStackTrace();
                activity.getSwipeRefreshExtrato().setRefreshing(false);
            }
        });

    }

    public void carregarCredencialDetalhe() {

        activity.getmListView().getAdapter().clearAll();

        Call<Credencial> call = ConnectPortadorService
                                    .getService()
                                    .credencialDetalhes(
                                                activity.getCredencialDetalhe().getIdCredencial(),
                                                IdentityItsPay.getInstance().getToken());

        call.enqueue(new Callback<Credencial>() {
            @Override
            public void onResponse(Call<Credencial> call, Response<Credencial> response) {
                if(response.body()!=null){
                    activity.setCredencialDetalhe(response.body());
                    activity.configuraCartao();
                }
            }

            @Override
            public void onFailure(Call<Credencial> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
            }
        });
    }

}

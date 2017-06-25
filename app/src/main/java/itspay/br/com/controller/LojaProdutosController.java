package itspay.br.com.controller;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.aplicationlib.model.MarketPlaceResponse;
import com.example.aplicationlib.model.ParceiroResponse;
import com.example.aplicationlib.util.ItsPayConstants;
import com.example.aplicationlib.util.ProgresDialogUtil;

import java.util.ArrayList;

import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.fragment.LojaProdutosFragment;
import itspay.br.com.services.ConnectPortadorService;

import itspay.br.com.util.UtilsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 24/01/17.
 */

public class LojaProdutosController {
    ProgresDialogUtil mProgresDialogUtil;
    boolean initialProgress;

    public LojaProdutosController(Context context, LojaProdutosFragment fragment){
        this.mProgresDialogUtil = new ProgresDialogUtil(fragment.getActivity());

        if(mProgresDialogUtil == null) {
            mProgresDialogUtil.setProgress(new ProgressDialog(fragment.getActivity()));
        }

    }

    public void listaParceiros (Call<ArrayList<MarketPlaceResponse>> call, final LojaProdutosFragment fragment , final boolean isUpdate) {
        if (!initialProgress) {
            mProgresDialogUtil.show("Loja Virtual", "Carregando Produtos.");
            initialProgress = true;
        } else {
            if (!mProgresDialogUtil.getProgress().getWindow().isActive()) {
                mProgresDialogUtil.show("Loja Virtual", "Carregando Produtos.");
            }
        }
        call.enqueue(new Callback<ArrayList<MarketPlaceResponse>>() {
            public void onResponse(Call<ArrayList<MarketPlaceResponse>> call, Response<ArrayList<MarketPlaceResponse>> response) {
                if (response.body() != null) {
                    fragment.listaParceiroResponse = response.body();
                    fragment.listarProdutos(isUpdate);
                } else {
                    UtilsActivity.alertMsg(response.errorBody(), fragment.getContext());
                }
                mProgresDialogUtil.dismiss();
                fragment.swipeRefreshLayout.setRefreshing(false);
            }


            public void onFailure(Call<ArrayList<MarketPlaceResponse>> call, Throwable t) {
                mProgresDialogUtil.dismiss();
                UtilsActivity.alertIfSocketException(t, fragment.getContext());
                fragment.swipeRefreshLayout.setRefreshing(false);

            }
        });
    }

    public void initFragment(LojaProdutosFragment fragment){
        fragment.materialListView.getAdapter().clearAll();
        fragment.swipeRefreshLayout.setRefreshing(true);
        Call<ArrayList<MarketPlaceResponse>> call = ConnectPortadorService.getService().getParceiros(
                ItsPayConstants.ID_PROCESSADORA,
                ItsPayConstants.ID_INSTITUICAO, 0, 10,
                IdentityItsPay.getInstance().getToken());

        listaParceiros(call,fragment,false);
    }

    public void updateItem(LojaProdutosFragment fragment ,long initialNumber){
        long maxNumber = initialNumber +10;
        Call<ArrayList<MarketPlaceResponse>> call = ConnectPortadorService.getService().getParceiros(
                ItsPayConstants.ID_PROCESSADORA,
                ItsPayConstants.ID_INSTITUICAO, initialNumber, maxNumber,
                IdentityItsPay.getInstance().getToken());

        listaParceiros(call,fragment,true);
    }
}

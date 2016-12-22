package itspay.br.com.controller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
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
                    activity.setCredenciais(response.body().getCredenciais());
                    activity.configurarCartoes();
                    Log.i("teste", response.toString());
                }
            }

            @Override
            public void onFailure(Call<GetCredenciaisResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void ligar(String numero){
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            String uri = "tel:" + numero;
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse(uri));
            activity.startActivity(intent);
        }
    }

    public void enviarEmail(String address, String subject, String text, String title){
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{address});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, text);
        email.setType("message/rfc822");
        activity.startActivity(Intent.createChooser(email, title));
    }

}

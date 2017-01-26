package itspay.br.com.controller;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import itspay.br.com.activity.MeusCartoesActivity;
import itspay.br.com.activity.TrocarEmailActivity;
import itspay.br.com.activity.TrocarSenhaActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.model.GetCredenciaisResponse;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.ItsPayConstants;
import itspay.br.com.util.UtilsActivity;
import okhttp3.ResponseBody;
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
        activity.getmListView().getAdapter().clearAll();
        activity.getSwipeRefreshLayout().setRefreshing(true);

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
                if (response.body() != null){
                    try {
                        activity.setCredenciais(response.body().getCredenciais());
                        activity.configurarCartoes();
                        Log.i("teste", response.toString());

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    UtilsActivity.alertMsg(response.errorBody(),activity);
                }

                activity.getSwipeRefreshLayout().setRefreshing(false);
            }

            @Override
            public void onFailure(Call<GetCredenciaisResponse> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
                activity.getSwipeRefreshLayout().setRefreshing(false);
            }
        });
    }

    public void ligar(final String numero){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(false).setTitle("ItsPay").setMessage("Deseja realmente ligar para " + numero + "?")
                .setPositiveButton("Ligar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                            String uri = "tel:" + numero;
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse(uri));
                            activity.startActivity(intent);
                        }
                    }
                })
                .setNegativeButton("Cancelar", null)
                .setCancelable(true);
        builder.create().show();
    }

    public void enviarEmail(final String address, final String subject, final String text, final String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(false).setTitle("ItsPay").setMessage("Deseja realmente mandar um email para " + address)
                .setPositiveButton("Enviar email", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent email = new Intent(Intent.ACTION_SEND);
                        email.putExtra(Intent.EXTRA_EMAIL, new String[]{address});
                        email.putExtra(Intent.EXTRA_SUBJECT, subject);
                        email.putExtra(Intent.EXTRA_TEXT, text);
                        email.setType("message/rfc822");
                        activity.startActivity(Intent.createChooser(email, title));
                    }
                })
                .setNegativeButton("Cancelar", null)
                .setCancelable(true);
        builder.create().show();

    }

    public void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(false).setTitle("ItsPay").setMessage("Tem certeza que deseja sair?")
                .setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        forceLogout();
                    }
                })
                .setNegativeButton("Não", null)
                .setCancelable(true);
        builder.create().show();

    }

    public void forceLogout(){
        Call<ResponseBody> callLogout = ConnectPortadorService.getService().logout();

        callLogout.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                IdentityItsPay.getInstance().clean();
                activity.finish();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                IdentityItsPay.getInstance().clean();
                activity.finish();
                IdentityItsPay.getInstance().clean();
            }
        });
    }




    public void abrirTrocarEmail(){
        Intent trocarEmailIntent = new Intent(activity, TrocarEmailActivity.class);
        activity.startActivity(trocarEmailIntent);
    }

    public void abrirTrocarSenha(){
        Intent trocarSenhaIntent = new Intent(activity, TrocarSenhaActivity.class);
        activity.startActivity(trocarSenhaIntent);
    }

}

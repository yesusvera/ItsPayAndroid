package itspay.br.com.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import itspay.br.com.activity.TrocarEmailActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.itspay.R;
import itspay.br.com.model.BuscarEmailResponse;
import itspay.br.com.model.TrocarEmail;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.ItsPayConstants;
import itspay.br.com.util.validations.ValidationsForms;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 23/12/16.
 */

public class TrocarEmailController extends BaseActivityController<TrocarEmailActivity> {

    public TrocarEmailController(TrocarEmailActivity activity) {
        super(activity);
    }

    public void trocarEmail(){
        if(!ValidationsForms.isEmail(activity.getTxtEmail().getText().toString())){
            activity.getTxtEmail().setError(activity.getString(R.string.error_invalid_email));
            activity.getTxtEmail().requestFocus();
            return;
        }

        TrocarEmail trocarEmail = new TrocarEmail();
        trocarEmail.setIdProcessadora(ItsPayConstants.ID_PROCESSADORA);
        trocarEmail.setIdInstituicao(ItsPayConstants.ID_INSTITUICAO);
        trocarEmail.setDocumento(IdentityItsPay.getInstance().getLoginPortador().getCpf());
        trocarEmail.setEmail(activity.getTxtEmail().getText().toString());

        Call<ResponseBody> callTrocarEmail =
                ConnectPortadorService.getService().trocarEmail(
                        trocarEmail,
                        IdentityItsPay.getInstance().getToken());

        callTrocarEmail.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.body() != null) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        String msg = jsonObject.getString("msg");

                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setCancelable(false).setMessage(msg)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        activity.finish();
                                    }
                                });
                        builder.create().show();
                    } else if (response.errorBody() != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            String msg = jsonObject.getString("msg");

                            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                            builder.setCancelable(false).setMessage(msg)
                                    .setPositiveButton("OK", null);
                            builder.create().show();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void buscarEmail(){

        Call<BuscarEmailResponse> call =
                ConnectPortadorService
                                    .getService()
                                    .buscarEmail(
                                            ItsPayConstants.ID_PROCESSADORA,
                                            ItsPayConstants.ID_INSTITUICAO,
                                            IdentityItsPay.getInstance().getLoginPortador().getCpf(),
                                            IdentityItsPay.getInstance().getToken()
                                            );

        call.enqueue(new Callback<BuscarEmailResponse>() {
            @Override
            public void onResponse(Call<BuscarEmailResponse> call, Response<BuscarEmailResponse> response) {
                if(response.body()!=null){
                    activity.getTxtEmail().setText(response.body().getEmail());
                }
            }

            @Override
            public void onFailure(Call<BuscarEmailResponse> call, Throwable t) {
            }
        });
    }


}

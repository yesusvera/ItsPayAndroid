package itspay.br.com.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import itspay.br.com.activity.RecuperarSenhaActivity;
import itspay.br.com.itspay.R;
import itspay.br.com.model.RecuperarLoginPortador;
import itspay.br.com.model.RecuperarSenhaResponse;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.ItsPayConstants;
import itspay.br.com.util.UtilsActivity;
import itspay.br.com.util.validations.ValidationsForms;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by juniorbraga on 13/03/17.
 */

public class RecuperarSenhaController extends BaseActivityController<RecuperarSenhaActivity> {


    public RecuperarSenhaController(RecuperarSenhaActivity activity) {
        super(activity);
    }

    public void criarLogin() {
        if(validaFormulario()){

            mProgresDialogUtil.show("Recuperando Login","Aguarde.");

            SimpleDateFormat rs = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date convertedCurrentDate = null;

            RecuperarLoginPortador portadorRecuperarLogin = new RecuperarLoginPortador();
            portadorRecuperarLogin.setCpf(activity.getCpf().getText().toString().replace(".", "").replace("-",""));
            portadorRecuperarLogin.setIdInstituicao(ItsPayConstants.ID_INSTITUICAO);
            portadorRecuperarLogin.setIdProcessadora(ItsPayConstants.ID_PROCESSADORA);

            try {
                convertedCurrentDate = rs.parse(activity.getDataNascimento().getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(convertedCurrentDate != null){
                String date=sdf.format(convertedCurrentDate);
                portadorRecuperarLogin.setDataNascimento(date);
            }

            Call<RecuperarSenhaResponse> criarLoginCall = ConnectPortadorService.getService().recuperarSenha(portadorRecuperarLogin);
            criarLoginCall.enqueue(new Callback<RecuperarSenhaResponse>() {
                @Override
                public void onResponse(Call<RecuperarSenhaResponse> call, Response<RecuperarSenhaResponse> response) {
                    if(response.body()!=null) {
                        Log.i("CRIALOGIN", response.body().toString());

                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setCancelable(false).setTitle("Sucesso").setMessage(response.body().toString())
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        activity.finish();
                                    }
                                });
                        builder.create().show();

                    }else if(response.errorBody() !=null){
                        try {
                            String jsonStr = response.errorBody().string();

                            JSONObject reader = new JSONObject(jsonStr);
                            String msg = reader.getString("msg");
                            String DTL = reader.getString("DTL");

                            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                            builder.setCancelable(false).setTitle("Erro").setMessage(msg)
                                    .setPositiveButton("OK", null);
                            builder.create().show();
                        }catch (JSONException ex){
                            ex.printStackTrace();
                        }catch (IOException ex){
                            ex.printStackTrace();
                        }

                    }
                    mProgresDialogUtil.dismiss();
//                    activity.progress.dismiss();
                }

                @Override
                public void onFailure(Call<RecuperarSenhaResponse> call, Throwable t) {
                    UtilsActivity.alertIfSocketException(t, activity);
                    mProgresDialogUtil.dismiss();
//                    activity.progress.dismiss();
                    t.printStackTrace();
                }
            });
        }
    }

    public boolean validaFormulario(){

        if(activity.getDataNascimento().getText().toString().length() < 10){
            activity.getDataNascimento().setError(activity.getString(R.string.error_data_nascimento_invalida));
            activity.getDataNascimento().requestFocus();
            return false;
        }

        if(!ValidationsForms.isCPF(activity.getCpf().getText().toString())){
            activity.getCpf().setError(activity.getString(R.string.error_invalid_cpf));
            activity.getCpf().requestFocus();
            return false;
        }


        return true;
    }

}
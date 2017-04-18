package itspay.br.com.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import itspay.br.com.activity.CadastroLoginActivity;
import itspay.br.com.activity.CadastroLoginPage2Activity;
import itspay.br.com.itspay.R;
import itspay.br.com.model.CriarLoginResponse;
import itspay.br.com.model.PortadorLogin;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.singleton.CadastroSingleton;
import itspay.br.com.util.EncriptSHA512;
import itspay.br.com.util.ItsPayConstants;
import itspay.br.com.util.UtilsActivity;
import itspay.br.com.util.validations.ValidationsForms;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroLoginController  {

    public Context mContext;
    CadastroSingleton mCadastroSingleton;

    public CadastroLoginController(Context activity) {
        mContext = activity;
        mCadastroSingleton = CadastroSingleton.getInstance();

    }

    public void criarLogin(final CadastroLoginPage2Activity activity){

        SimpleDateFormat rs = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedCurrentDate = null;

        String credencial = EncriptSHA512.encript(mCadastroSingleton.getmNumeroCartao().toString());

        PortadorLogin portadorLogin = new PortadorLogin();
        portadorLogin.setCpf(mCadastroSingleton.getmCpf().replace(".", "").replace("-",""));
        portadorLogin.setCredencial(credencial);
        portadorLogin.setEmail(mCadastroSingleton.getmEmail().toString());
        portadorLogin.setIdInstituicao(ItsPayConstants.ID_INSTITUICAO);
        portadorLogin.setIdProcessadora(ItsPayConstants.ID_PROCESSADORA);
        portadorLogin.setOrigemCadastroLogin(ItsPayConstants.ORIGEM_ACESSO);
        portadorLogin.setSenha(mCadastroSingleton.getmSenha().toString());

        try {
            convertedCurrentDate = rs.parse(mCadastroSingleton.getmDataNascimento());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(convertedCurrentDate != null){
            String date=sdf.format(convertedCurrentDate);
            portadorLogin.setDataNascimento(date);
        }

        Call<CriarLoginResponse> criarLoginCall =   ConnectPortadorService.getService().criarLogin(portadorLogin);
        criarLoginCall.enqueue(new Callback<CriarLoginResponse>() {
            @Override
            public void onResponse(Call<CriarLoginResponse> call, Response<CriarLoginResponse> response) {
                if(response.body()!=null) {
                    Log.i("CRIALOGIN", response.body().toString());
                }else if(response.errorBody() !=null){
                    try {
                        String jsonStr = response.errorBody().string();

                        JSONObject reader = new JSONObject(jsonStr);
                        String msg = reader.getString("msg");
                        String DTL = reader.getString("DTL");
                        Boolean created = reader.getBoolean("created");

                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setCancelable(false).setTitle(msg).setMessage(DTL)
                                .setPositiveButton("OK", null);
                        builder.create().show();
                    }catch (JSONException ex){
                        ex.printStackTrace();
                    }catch (IOException ex){
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CriarLoginResponse> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
                t.printStackTrace();
            }
        });
    }

    public boolean validaFormulario(final CadastroLoginPage2Activity activity){
        if (!activity.getTermosDeUso().isChecked()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage("Você precisa concordar com os termos de uso e políticas de privacidade.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            activity.getTermosDeUso().requestFocus();
                        }
                    });
            builder.create().show();
            return false;
        }

        if(!ValidationsForms.isEmail(activity.getEmail().getText().toString())){
            activity.getEmail().setError(mContext.getString(R.string.error_invalid_email));
            activity.getEmail().requestFocus();
            return false;
        }

        if(!ValidationsForms.senhaValida(activity.getSenha().getText().toString())){
            activity.getSenha().setError(mContext.getString(R.string.error_incorrect_password_input));
            activity.getSenha().requestFocus();
            return false;
        }

        if(!activity.getSenha().getText().toString().equals(activity.getConfirmacaoSenha().getText().toString())){
            activity.getConfirmacaoSenha().setError(mContext.getString(R.string.error_senhas_incompativeis));
            activity.getConfirmacaoSenha().requestFocus();
            return false;
        }

        mCadastroSingleton.setmEmail(activity.getEmail().getText().toString());
        mCadastroSingleton.setmSenha(activity.getSenha().getText().toString());

        return true;
    }


    public boolean validaFormulario1(final CadastroLoginActivity activity){

        if(activity.getNumeroCartao().getText().length() < 19){
            activity.getNumeroCartao().setError(activity.getString(R.string.error_invalid_card_number));
            activity.getNumeroCartao().requestFocus();
            return false;
        }

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

        if(activity.getNumerocelular().getText().toString() == ""){
            activity.getNumerocelular().setError("Numero de Celular Invalido");
            activity.getNumerocelular().requestFocus();
            return false;
        }

        mCadastroSingleton.setmNumeroCartao(activity.getNumeroCartao().getText().toString());
        mCadastroSingleton.setmDataNascimento(activity.getDataNascimento().getText().toString());
        mCadastroSingleton.setmCpf(activity.getCpf().getText().toString());
        mCadastroSingleton.setmNumerocelular(activity.getNumerocelular().getText().toString()
                .replace("(","")
                .replace(")","")
                .replace("-",""));

        return true;
    }

}

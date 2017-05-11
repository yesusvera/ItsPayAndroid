package itspay.br.com.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.example.aplicationlib.model.CriarLoginResponse;
import com.example.aplicationlib.model.PortadorLogin;
import com.example.aplicationlib.model.ValidarPortadorLogin;
import com.example.aplicationlib.util.EncriptSHA512;
import com.example.aplicationlib.util.ItsPayConstants;
import com.example.aplicationlib.util.ProgresDialogUtil;
import com.example.aplicationlib.util.validations.ValidationsForms;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import itspay.br.com.activity.CadastroLoginActivity;
import itspay.br.com.activity.CadastroLoginPage2Activity;
import itspay.br.com.activity.TokenActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.itspay.R;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.singleton.CadastroSingleton;
import itspay.br.com.util.UtilsActivity;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.aplicationlib.util.UtilsAplication.parserDataService;

public class CadastroLoginController  {

    public Context mContext;
    CadastroSingleton mCadastroSingleton;
    ProgresDialogUtil mProgresDialogUtil;

    public CadastroLoginController(Context activity) {
        mContext = activity;
        mCadastroSingleton = CadastroSingleton.getInstance();
        mProgresDialogUtil = new ProgresDialogUtil(activity);
    }

    //  Finalizar Cadastro
    public void criarLogin(final CadastroLoginPage2Activity activity) {
        if (validaFormulario(activity)) {

            mProgresDialogUtil.show("Cadastrandro Usuario", "Aguarde.");


            String credencial = EncriptSHA512.encript(mCadastroSingleton.getmNumeroCartao().toString());

            PortadorLogin portadorLogin = new PortadorLogin();
            portadorLogin.setCpf(mCadastroSingleton.getmCpf().
                    replace(".", "").
                    replace("-", "").
                    replaceAll("/",""));

            portadorLogin.setCredencial(credencial);
            portadorLogin.setEmail(mCadastroSingleton.getmEmail().toString());
            portadorLogin.setIdInstituicao(ItsPayConstants.ID_INSTITUICAO);
            portadorLogin.setIdProcessadora(ItsPayConstants.ID_PROCESSADORA);
            portadorLogin.setOrigemCadastroLogin(ItsPayConstants.ORIGEM_ACESSO);
            portadorLogin.setSenha(mCadastroSingleton.getmSenha().toString());

            portadorLogin.setDataNascimento(parserDataService(mCadastroSingleton.getmDataNascimento()));

            Call<CriarLoginResponse> criarLoginCall = ConnectPortadorService.getService().criarLogin(portadorLogin);
            criarLoginCall.enqueue(new Callback<CriarLoginResponse>() {
                @Override
                public void onResponse(Call<CriarLoginResponse> call, Response<CriarLoginResponse> response) {
                    if (response.body() != null) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setCancelable(false).setTitle("Sucesso").setMessage("Login Criado com Sucesso")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        activity.finish();
                                    }
                                });
                        builder.create().show();

                        Log.i("CRIALOGIN", response.body().toString());


                    } else if (response.errorBody() != null) {
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
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    mProgresDialogUtil.dismiss();
                }

                @Override
                public void onFailure(Call<CriarLoginResponse> call, Throwable t) {
                    UtilsActivity.alertIfSocketException(t, activity);
                    mProgresDialogUtil.dismiss();
                    t.printStackTrace();
                }
            });
        }
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




    //  Verificar Cadastrdo existe
    public void verificarLogin(final CadastroLoginActivity activity) {

        if(validaFormulario1(activity)){

            mProgresDialogUtil.show("Validando Usuario", "Aguarde.");

            mCadastroSingleton.setmKey(CadastroSingleton.getInstance().getmNumerocelular() +
                    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));

            String credencial = EncriptSHA512.encript(mCadastroSingleton.getmNumeroCartao().toString().replace(".","")).toUpperCase();

            ValidarPortadorLogin validarPortadorLogin = new ValidarPortadorLogin();
            validarPortadorLogin.setCpf(mCadastroSingleton.getmCpf().replace(".", "").replace("-", ""));
            validarPortadorLogin.setCredencial(credencial);
            validarPortadorLogin.setIdInstituicao(ItsPayConstants.ID_INSTITUICAO);
            validarPortadorLogin.setIdProcessadora(ItsPayConstants.ID_PROCESSADORA);
            validarPortadorLogin.setDataNascimento(parserDataService(mCadastroSingleton.getmDataNascimento()));
            validarPortadorLogin.setChave(mCadastroSingleton.getmKey());
            validarPortadorLogin.setCelular(mCadastroSingleton.getmNumerocelular());

            Call<CriarLoginResponse> criarLoginCall = ConnectPortadorService.getService().validarPortador(validarPortadorLogin);
            criarLoginCall.enqueue(new Callback<CriarLoginResponse>() {
                @Override
                public void onResponse(Call<CriarLoginResponse> call, Response<CriarLoginResponse> response) {
                    if (response.body() != null) {

                        Intent intent = new Intent(activity,TokenActivity.class);
                        intent.putExtra("tipoActivity",0);
                        activity.startActivity(intent);


                    } else if (response.errorBody() != null) {
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
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    mProgresDialogUtil.dismiss();
                }

                @Override
                public void onFailure(Call<CriarLoginResponse> call, Throwable t) {
                    UtilsActivity.alertIfSocketException(t, activity);
                    mProgresDialogUtil.dismiss();
                    t.printStackTrace();
                }
            });

        }
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

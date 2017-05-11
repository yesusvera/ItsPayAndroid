package itspay.br.com.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import com.example.aplicationlib.model.CriarLoginResponse;
import com.example.aplicationlib.model.PortadorLogin;
import com.example.aplicationlib.util.EncriptSHA512;
import com.example.aplicationlib.util.ItsPayConstants;
import com.example.aplicationlib.util.validations.ValidationsForms;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import itspay.br.com.activity.CadastroLoginPage2Activity;
import itspay.br.com.activity.CadastroUsuarioBaseActivity;
import itspay.br.com.activity.CadastroUsuarioBaseFinalizaActivity;
import itspay.br.com.itspay.R;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.singleton.CadastoBaseSingleton;
import itspay.br.com.util.UtilsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.aplicationlib.util.UtilsAplication.parserDataService;

/**
 * Created by juniorbraga on 11/05/17.
 */

public class CadastroUsuarioBaseFinalController extends BaseActivityController<CadastroUsuarioBaseFinalizaActivity> {

    CadastoBaseSingleton mCadastroBaseSingleton;


    public CadastroUsuarioBaseFinalController(CadastroUsuarioBaseFinalizaActivity activity) {
        super(activity);
        mCadastroBaseSingleton = CadastoBaseSingleton.getInstance();
    }


    //  Finalizar Cadastro
    public void criarLogin() {
//        if (validaFormulario(activity)) {
//
//            mProgresDialogUtil.show("Cadastrandro Usuario", "Aguarde.");
//
//
//            String credencial = EncriptSHA512.encript(mCadastroSingleton.getmNumeroCartao().toString());
//
//            PortadorLogin portadorLogin = new PortadorLogin();
//            portadorLogin.setCpf(mCadastroSingleton.getmCpf().
//                    replace(".", "").
//                    replace("-", "").
//                    replaceAll("/",""));
//
//            portadorLogin.setCredencial(credencial);
//            portadorLogin.setEmail(mCadastroSingleton.getmEmail().toString());
//            portadorLogin.setIdInstituicao(ItsPayConstants.ID_INSTITUICAO);
//            portadorLogin.setIdProcessadora(ItsPayConstants.ID_PROCESSADORA);
//            portadorLogin.setOrigemCadastroLogin(ItsPayConstants.ORIGEM_ACESSO);
//            portadorLogin.setSenha(mCadastroSingleton.getmSenha().toString());
//
//            portadorLogin.setDataNascimento(parserDataService(mCadastroSingleton.getmDataNascimento()));
//
//            Call<CriarLoginResponse> criarLoginCall = ConnectPortadorService.getService().criarLogin(portadorLogin);
//            criarLoginCall.enqueue(new Callback<CriarLoginResponse>() {
//                @Override
//                public void onResponse(Call<CriarLoginResponse> call, Response<CriarLoginResponse> response) {
//                    if (response.body() != null) {
//
//                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//                        builder.setCancelable(false).setTitle("Sucesso").setMessage("Login Criado com Sucesso")
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        activity.finish();
//                                    }
//                                });
//                        builder.create().show();
//
//                        Log.i("CRIALOGIN", response.body().toString());
//
//
//                    } else if (response.errorBody() != null) {
//                        try {
//                            String jsonStr = response.errorBody().string();
//
//                            JSONObject reader = new JSONObject(jsonStr);
//                            String msg = reader.getString("msg");
//                            String DTL = reader.getString("DTL");
//                            Boolean created = reader.getBoolean("created");
//
//                            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//                            builder.setCancelable(false).setTitle(msg).setMessage(DTL)
//                                    .setPositiveButton("OK", null);
//                            builder.create().show();
//                        } catch (JSONException ex) {
//                            ex.printStackTrace();
//                        } catch (IOException ex) {
//                            ex.printStackTrace();
//                        }
//                    }
//
//                    mProgresDialogUtil.dismiss();
//                }
//
//                @Override
//                public void onFailure(Call<CriarLoginResponse> call, Throwable t) {
//                    UtilsActivity.alertIfSocketException(t, activity);
//                    mProgresDialogUtil.dismiss();
//                    t.printStackTrace();
//                }
//            });
//        }
    }


    public boolean validaFormulario(){
        if (!activity.getCheckTermosDeUso().isChecked()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage("Você precisa concordar com os termos de uso e políticas de privacidade.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            activity.getCheckTermosDeUso().requestFocus();
                        }
                    });
            builder.create().show();
            return false;
        }

        if(!ValidationsForms.isEmail(activity.getEmail().getText().toString())){
            activity.getEmail().setError(activity.getString(R.string.error_invalid_email));
            activity.getEmail().requestFocus();
            return false;
        }

        if(!ValidationsForms.isEmail(activity.getEmailprofissional().getText().toString())){
            activity.getEmail().setError(activity.getString(R.string.error_invalid_email));
            activity.getEmail().requestFocus();
            return false;
        }

        if(!ValidationsForms.senhaValida(activity.getSenha().getText().toString())){
            activity.getSenha().setError(activity.getString(R.string.error_incorrect_password_input));
            activity.getSenha().requestFocus();
            return false;
        }

        if(!activity.getSenha().getText().toString().equals(activity.getConfirmacaoSenha().getText().toString())){
            activity.getConfirmacaoSenha().setError(activity.getString(R.string.error_senhas_incompativeis));
            activity.getConfirmacaoSenha().requestFocus();
            return false;
        }

        mCadastroBaseSingleton.setmEmailPessoal(activity.getEmail().getText().toString());
        mCadastroBaseSingleton.setmEmailPessoal(activity.getEmail().getText().toString());
        mCadastroBaseSingleton.setmSenha(activity.getSenha().getText().toString());

        return true;
    }

}


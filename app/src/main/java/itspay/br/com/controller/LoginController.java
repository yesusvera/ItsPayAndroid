package itspay.br.com.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import br.com.braga.junior.aplicationlib.model.FazerLoginPortador;
import br.com.braga.junior.aplicationlib.model.FazerLoginPortadorResponse;
import br.com.braga.junior.aplicationlib.util.ItsPayConstants;
import br.com.braga.junior.aplicationlib.util.usersharepreferences.SharedPreferenceUtil;
import itspay.br.com.activity.LoginActivity;
import itspay.br.com.activity.MeusCartoesActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.UtilsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static itspay.br.com.activity.LoginActivity.IS_FINGER_PRINT_CHECKED;
import static itspay.br.com.activity.LoginActivity.IS_SECOND_LOGIN_FINGER_PRINT;

/**
 * Created by yesus on 17/12/16.
 */
public class LoginController extends BaseActivityController<LoginActivity> {

    public LoginController(LoginActivity activity) {
        super(activity);
    }

    public void login(final String cpf, final String password) {
        final FazerLoginPortador fazerLoginPortador = new FazerLoginPortador();
        fazerLoginPortador.setArchitectureInfo(Build.DEVICE);
        fazerLoginPortador.setCpf(cpf.replace(".", "").replace("-", ""));
        fazerLoginPortador.setDeviceId(Build.ID);
        fazerLoginPortador.setIdInstituicao(ItsPayConstants.ID_INSTITUICAO);
        fazerLoginPortador.setIdProcessadora(ItsPayConstants.ID_PROCESSADORA);
//        fazerLoginPortador.setLatitude(castCoordenada(activity.getLatitude()));
//        fazerLoginPortador.setLongitude(castCoordenada(activity.getLongitude()));
//
        fazerLoginPortador.setLatitude(activity.getLatitude());
        fazerLoginPortador.setLongitude(activity.getLongitude());
        fazerLoginPortador.setModel(Build.MODEL);
        fazerLoginPortador.setOrigemAcesso(1);
        fazerLoginPortador.setPlataformVersion(Build.VERSION.RELEASE);
        fazerLoginPortador.setPlatformName(Build.PRODUCT);
        fazerLoginPortador.setSenha(password);
        fazerLoginPortador.setSistemaOperacional(ItsPayConstants.SISTEMA_OPERACIONAL);
        fazerLoginPortador.setVersaoConhecida("1.0.0");
        fazerLoginPortador.setVersaoInstalada("1.0.0");

        Call<FazerLoginPortadorResponse> fazerLoginPortadorResponseCall = ConnectPortadorService.getService().login(fazerLoginPortador);

        fazerLoginPortadorResponseCall.enqueue(new Callback<FazerLoginPortadorResponse>() {
            @Override
            public void onResponse(Call<FazerLoginPortadorResponse> call, Response<FazerLoginPortadorResponse> response) {
                if (response.body() != null) {
                    Log.i("RESPOSTA SERVICO LOGIN", response.body().toString());
                    activity.showProgress(false);

                    IdentityItsPay.getInstance().setLoginPortadorResponse(response.body());
                    IdentityItsPay.getInstance().setLoginPortador(fazerLoginPortador);

                    String setCookie = response.headers().get("Set-Cookie");
//                   String JSESSIONID = extractJSESSSIONID(setCookie);

                    IdentityItsPay.getInstance().setSetCookie(setCookie);

                    SharedPreferenceUtil.setStringPreference(activity, "lastCPFLogged", cpf);
                    SharedPreferenceUtil.setStringPreference(activity, "lastPasswordLogged", password);

                    activity.getmPasswordView().setText("");

                    if (response.body().isRequisitarAtualizacao()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setCancelable(false).setMessage(response.body().getRequisicaoAtualizacaoMensagem())
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        redirecionarMeusCartoes();
                                    }
                                })
                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        redirecionarMeusCartoes();
                                    }
                                });
                        builder.create().show();
                    } else if (response.body().isRequisitarPermissaoNotificacao()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setCancelable(false).setMessage(response.body().getRequisicaoNotificacaoMensagem())
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        redirecionarMeusCartoes();
                                    }
                                })
                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        redirecionarMeusCartoes();
                                    }
                                });
                        builder.create().show();

                    } else {
                        redirecionarMeusCartoes();
                    }

                    SharedPreferenceUtil.setBooleanPreference(activity, activity.IS_SECOND_LOGIN_FINGER_PRINT, true);

                } else if (response.errorBody() != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String msg = jsonObject.getString("msg");

                        activity.mLlInputLayoutPassword.setVisibility(View.VISIBLE);
                        if(activity.mAlertDialog!=null) activity.mAlertDialog.dismiss();

                        SharedPreferenceUtil.setBooleanPreference(activity.getBaseContext(), IS_FINGER_PRINT_CHECKED, false);
                        SharedPreferenceUtil.setBooleanPreference(activity.getBaseContext(), IS_SECOND_LOGIN_FINGER_PRINT, false);


                        activity.mIsFeatureEnabled = SharedPreferenceUtil.getBooleanPreference(activity.getBaseContext(), IS_FINGER_PRINT_CHECKED, false);
                        activity.mSecondLogin = SharedPreferenceUtil.getBooleanPreference(activity.getBaseContext(), IS_SECOND_LOGIN_FINGER_PRINT, false);

                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setCancelable(false).setMessage(msg)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        activity.showProgress(false);
                                        IdentityItsPay.getInstance().clean();
                                    }
                                });
                        builder.create().show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                        activity.showProgress(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<FazerLoginPortadorResponse> call, Throwable t) {
//                activity.mLlInputLayoutPassword.setVisibility(View.VISIBLE);
                UtilsActivity.alertIfSocketException(t, activity);
                activity.showProgress(false);
            }
        });
    }

    public void redirecionarMeusCartoes() {
        //redirecionando para meus cartões
        MeusCartoesActivity.FORCE_LOGOUT = false;
        Intent intent = new Intent(activity, MeusCartoesActivity.class);
        activity.startActivity(intent);
    }

    public long castCoordenada(double coordenada) {
        String str = String.valueOf(coordenada);
        str = str.replace(".", "").replace(",", "");
        return Long.valueOf(str);
    }

    public String extractJSESSSIONID(String setCookie) {
        String jsessionid = "";
        if (setCookie != null) {
            if (setCookie.indexOf(";") > -1) {
                String[] headers = setCookie.split(";");

                for (String header : headers) {
                    if (header.indexOf("JSESSIONID") > -1) {
                        jsessionid = header;
                        jsessionid = jsessionid.replace("JSESSIONID=", "");
                    }
                }
            }
        }

        return jsessionid;
    }
}

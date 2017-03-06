package itspay.br.com.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;

import javax.crypto.KeyGenerator;

import itspay.br.com.activity.LoginActivity;
import itspay.br.com.activity.MeusCartoesActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.model.FazerLoginPortador;
import itspay.br.com.model.FazerLoginPortadorResponse;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.ItsPayConstants;
import itspay.br.com.util.UtilsActivity;
import itspay.br.com.util.usersharepreferences.SharedPreferenceUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 17/12/16.
 */
public class LoginController extends BaseActivityController<LoginActivity>{

    private static final String KEY_NAME = "example_key";
    private KeyGenerator keyGenerator;
    private KeyStore keyStore;

    public LoginController(LoginActivity activity){
        super(activity);
    }

    public void login(String cpf, String password){
        activity.showProgress(true);
        final FazerLoginPortador fazerLoginPortador = new FazerLoginPortador();
        fazerLoginPortador.setArchitectureInfo("string");
        fazerLoginPortador.setCpf(cpf.replace(".", "").replace("-", ""));
        fazerLoginPortador.setDeviceId("string");
        fazerLoginPortador.setIdInstituicao(ItsPayConstants.ID_INSTITUICAO);
        fazerLoginPortador.setIdProcessadora(ItsPayConstants.ID_PROCESSADORA);
//        fazerLoginPortador.setLatitude(castCoordenada(activity.getLatitude()));
//        fazerLoginPortador.setLongitude(castCoordenada(activity.getLongitude()));
//
        fazerLoginPortador.setLatitude(activity.getLatitude());
        fazerLoginPortador.setLongitude(activity.getLongitude());
        fazerLoginPortador.setModel("string");
        fazerLoginPortador.setOrigemAcesso(1);
        fazerLoginPortador.setPlataformVersion("string");
        fazerLoginPortador.setPlatformName("string");
        fazerLoginPortador.setSenha(password);
        fazerLoginPortador.setSistemaOperacional(2);
        fazerLoginPortador.setVersaoConhecida("1.0.0");
        fazerLoginPortador.setVersaoInstalada("1.0.0");

        Call<FazerLoginPortadorResponse> fazerLoginPortadorResponseCall =   ConnectPortadorService.getService().login(fazerLoginPortador);

        fazerLoginPortadorResponseCall.enqueue(new Callback<FazerLoginPortadorResponse>() {
            @Override
            public void onResponse(Call<FazerLoginPortadorResponse> call, Response<FazerLoginPortadorResponse> response) {
               if(response.body()!=null) {
                   Log.i("RESPOSTA SERVICO LOGIN", response.body().toString());
                   activity.showProgress(false);

                   IdentityItsPay.getInstance().setLoginPortadorResponse(response.body());
                   IdentityItsPay.getInstance().setLoginPortador(fazerLoginPortador);

                   String setCookie = response.headers().get("Set-Cookie");
//                   String JSESSIONID = extractJSESSSIONID(setCookie);

                   IdentityItsPay.getInstance().setSetCookie(setCookie);

                   SharedPreferenceUtil.setStringPreference(activity, "lastCPFLogged",activity.getmCpfView().getText().toString());
                   SharedPreferenceUtil.setStringPreference(activity, "lastPasswordLogged",activity.getmPasswordView().getText().toString());


                   if(response.body().isRequisitarAtualizacao()){
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
                   }else if(response.body().isRequisitarPermissaoNotificacao()){
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

                   }else{
                       redirecionarMeusCartoes();
                   }

               }else if(response.errorBody() != null){
                   try {
                       JSONObject jsonObject = new JSONObject(response.errorBody().string());
                       String msg = jsonObject.getString("msg");

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
                   }catch (IOException ex){
                       ex.printStackTrace();
                   }catch (JSONException ex){
                       ex.printStackTrace();
                       activity.showProgress(false);
                   }
               }
            }

            @Override
            public void onFailure(Call<FazerLoginPortadorResponse> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
                activity.showProgress(false);
            }
        });
    }

    public void redirecionarMeusCartoes(){
        //redirecionando para meus cartões
        MeusCartoesActivity.FORCE_LOGOUT = false;
        Intent intent = new Intent(activity, MeusCartoesActivity.class);
        activity.startActivity(intent);
    }

    public long castCoordenada(double coordenada){
        String str = String.valueOf(coordenada);
        str = str.replace(".", "").replace(",", "");
        return Long.valueOf(str);
    }

    public String extractJSESSSIONID(String setCookie){
        String jsessionid = "";
        if(setCookie!=null){
            if(setCookie.indexOf(";")>-1){
                String[] headers = setCookie.split(";");

                for(String header: headers){
                    if(header.indexOf("JSESSIONID") > -1){
                        jsessionid = header;
                        jsessionid = jsessionid.replace("JSESSIONID=", "");
                    }
                }
            }
        }

        return jsessionid;
    }

    public void generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            keyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES,
                    "AndroidKeyStore");
        } catch (NoSuchAlgorithmException |
                NoSuchProviderException e) {
            throw new RuntimeException(
                    "Failed to get KeyGenerator instance", e);
        }

        try {
            keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException |
                InvalidAlgorithmParameterException
                | CertificateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}

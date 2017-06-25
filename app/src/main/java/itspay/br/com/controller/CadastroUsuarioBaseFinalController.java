package itspay.br.com.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.example.aplicationlib.model.CriarLoginResponse;
import com.example.aplicationlib.model.PortadorPreEmitidoLogin.EnderecoPessoaRequestBean;
import com.example.aplicationlib.model.PortadorPreEmitidoLogin.EnderecoPessoaRequestBean.EnderecosBean;
import com.example.aplicationlib.model.PortadorLogin;
import com.example.aplicationlib.model.PortadorPreEmitidoLogin;
import com.example.aplicationlib.util.EncriptSHA512;
import com.example.aplicationlib.util.ItsPayConstants;
import com.example.aplicationlib.util.UtilsAplication;
import com.example.aplicationlib.util.validations.ValidationsForms;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import itspay.br.com.activity.CadastroUsuarioBaseFinalizaActivity;
import itspay.br.com.activity.LoginActivity;
import itspay.br.com.itspay.R;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.singleton.CadastoBaseSingleton;
import itspay.br.com.util.UtilsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by juniorbraga on 11/05/17.
 */

public class CadastroUsuarioBaseFinalController extends BaseActivityController<CadastroUsuarioBaseFinalizaActivity> {

    CadastoBaseSingleton mCadastroBaseSingleton;
    String credencial;
    String dataNascimento;

    public CadastroUsuarioBaseFinalController(CadastroUsuarioBaseFinalizaActivity activity) {
        super(activity);
        mCadastroBaseSingleton = CadastoBaseSingleton.getInstance();
    }


    //  Finalizar Cadastro
    public void criarLogin() {
        if (validaFormulario()) {

            mProgresDialogUtil.show("Cadastrandro Usuario", "Aguarde.");


            credencial = EncriptSHA512.encript(mCadastroBaseSingleton.getmNumeroCartao().toString()).toUpperCase();
            dataNascimento = UtilsAplication.parserDataService(mCadastroBaseSingleton.getmDataNascimento());


            PortadorPreEmitidoLogin mPortadorPreEmitidoLogin = new PortadorPreEmitidoLogin();


            mPortadorPreEmitidoLogin.setIdInstituicao(ItsPayConstants.ID_INSTITUICAO);
            mPortadorPreEmitidoLogin.setIdProcessadora(ItsPayConstants.ID_PROCESSADORA);
            mPortadorPreEmitidoLogin.setIdFilial(ItsPayConstants.ID_FILIAL);
            mPortadorPreEmitidoLogin.setIdRegional(ItsPayConstants.ID_REGIONAL);
            mPortadorPreEmitidoLogin.setIdPontoDeRelacionamento(ItsPayConstants.ID_PONTO_RELACIONAMENTO);


            mPortadorPreEmitidoLogin.setCredencial(credencial);
            mPortadorPreEmitidoLogin.setDocumento(mCadastroBaseSingleton.getmCpf());
            mPortadorPreEmitidoLogin.setDataNascimento(dataNascimento);
            mPortadorPreEmitidoLogin.setEmail(mCadastroBaseSingleton.getmEmailPessoal());
            mPortadorPreEmitidoLogin.setEmailProfissional(mCadastroBaseSingleton.getmEmailProfissional());
            mPortadorPreEmitidoLogin.setEstrangeiro(mCadastroBaseSingleton.ismEstrangeiro());

             if(mCadastroBaseSingleton.ismEstrangeiro())
                 mPortadorPreEmitidoLogin.setPassaporte(mCadastroBaseSingleton.getmNumeroPassaporte());

            mPortadorPreEmitidoLogin.setIdEstadoCivil(mCadastroBaseSingleton.getmEstadoCivil());
            mPortadorPreEmitidoLogin.setIdSexo(mCadastroBaseSingleton.getmGenero());
            mPortadorPreEmitidoLogin.setNacionalidade(mCadastroBaseSingleton.getmNacionalidade());
            mPortadorPreEmitidoLogin.setNaturalidade(mCadastroBaseSingleton.getmNaturalidade());
            mPortadorPreEmitidoLogin.setTipoPessoa(mCadastroBaseSingleton.getmTipoPessoa());
            mPortadorPreEmitidoLogin.setNomeMae(mCadastroBaseSingleton.getmNomeMae());
            mPortadorPreEmitidoLogin.setNomePai(mCadastroBaseSingleton.getmNomePai());
            mPortadorPreEmitidoLogin.setNomeCompleto(mCadastroBaseSingleton.getmNomeCompleto());
            mPortadorPreEmitidoLogin.setRg(mCadastroBaseSingleton.getmRg());
            mPortadorPreEmitidoLogin.setRgOrgaoEmissor(mCadastroBaseSingleton.getmOrgaoEmissor().toUpperCase());
            mPortadorPreEmitidoLogin.setRgDataEmissao(mCadastroBaseSingleton.getmDataEmissao());
            //Phone Number
            mPortadorPreEmitidoLogin.setDddTelefoneCelular(mCadastroBaseSingleton.getmDddTelefoneCelular());
            mPortadorPreEmitidoLogin.setTelefoneCelular(mCadastroBaseSingleton.getmNumerocelular());
            mPortadorPreEmitidoLogin.setDddTelefoneResidencial(mCadastroBaseSingleton.getmDddTelefoneResidencial());
            mPortadorPreEmitidoLogin.setTelefoneResidencial(mCadastroBaseSingleton.getmNumeroResidencial());

            mPortadorPreEmitidoLogin.setDddTelefoneComercial(mCadastroBaseSingleton.getmDddTelefoneComercial());
            mPortadorPreEmitidoLogin.setTelefoneComercial(mCadastroBaseSingleton.getmNumeroComercial());

            EnderecoPessoaRequestBean mEnderecosPessoaRequest = new EnderecoPessoaRequestBean();
            List<EnderecosBean> mListEnderecoPessoaRequest = new ArrayList<EnderecosBean>();
            EnderecosBean mEnderecoPessoaRequest = new EnderecosBean();

            mEnderecoPessoaRequest.setCep(mCadastroBaseSingleton.mCep);
            mEnderecoPessoaRequest.setBairro(mCadastroBaseSingleton.getmBairro());
            mEnderecoPessoaRequest.setCidade(mCadastroBaseSingleton.getmCidade());
            mEnderecoPessoaRequest.setLogradouro(mCadastroBaseSingleton.getLogradouro());
            mEnderecoPessoaRequest.setCidade(mCadastroBaseSingleton.getmCidade());
            mEnderecoPessoaRequest.setNumero(mCadastroBaseSingleton.getmNumero());
            mEnderecoPessoaRequest.setUf(mCadastroBaseSingleton.getSiglaUF());
            mEnderecoPessoaRequest.setIdUsuarioInclusao(ItsPayConstants.ID_USUARIO);

            if(mCadastroBaseSingleton.getmComplemento() !=null)
                mEnderecoPessoaRequest.setComplemento(mCadastroBaseSingleton.getmComplemento());

            mListEnderecoPessoaRequest.add(mEnderecoPessoaRequest);

            mEnderecosPessoaRequest.setEnderecos(mListEnderecoPessoaRequest);

            mPortadorPreEmitidoLogin.setEnderecoPessoaRequest(mEnderecosPessoaRequest);



            Call<CriarLoginResponse> criarLoginCall = ConnectPortadorService.getService().loginPreEmitidoPortador(mPortadorPreEmitidoLogin);
            criarLoginCall.enqueue(new Callback<CriarLoginResponse>() {
                @Override
                public void onResponse(Call<CriarLoginResponse> call, Response<CriarLoginResponse> response) {
                    if (response.body() != null) {
                        createLogin();
                    } else if (response.errorBody() != null) {
                        try {
                            String jsonStr = response.errorBody().string();

                            JSONObject reader = new JSONObject(jsonStr);
                            String msg = reader.getString("msg");
//                            String DTL = reader.getString("DTL");
//                            Boolean created = reader.getBoolean("created");

                            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                            builder.setCancelable(false).setTitle("Erro").setMessage(msg)
                                    .setPositiveButton("OK", null);
                            builder.create().show();
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        mProgresDialogUtil.dismiss();
                    }

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


    public void createLogin(){

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        PortadorLogin portadorLogin = new PortadorLogin();
            portadorLogin.setCpf(mCadastroBaseSingleton.getmCpf());
            portadorLogin.setCredencial(credencial);
            portadorLogin.setEmail(mCadastroBaseSingleton.getmEmailPessoal());
            portadorLogin.setIdInstituicao(ItsPayConstants.ID_INSTITUICAO);
            portadorLogin.setIdProcessadora(ItsPayConstants.ID_PROCESSADORA);
            portadorLogin.setOrigemCadastroLogin(ItsPayConstants.ORIGEM_ACESSO);
            portadorLogin.setSenha(mCadastroBaseSingleton.getmSenha());
            portadorLogin.setDataNascimento(dataNascimento);

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
                                        Intent intent  = new Intent(activity, LoginActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        activity.startActivity(intent);
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


    public boolean validaFormulario(){

        if(!ValidationsForms.isEmail(activity.getEmail().getText().toString())){
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
        mCadastroBaseSingleton.setmEmailProfissional(activity.getEmailprofissional().getText().toString());
        mCadastroBaseSingleton.setmSenha(activity.getSenha().getText().toString());

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

        return true;
    }
}


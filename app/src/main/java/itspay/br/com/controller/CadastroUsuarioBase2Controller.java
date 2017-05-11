package itspay.br.com.controller;

import android.content.Intent;

import com.example.aplicationlib.model.BuscarEmailResponse;
import com.example.aplicationlib.model.EnderecoResponse;
import com.example.aplicationlib.model.ItsPayResponse;
import com.example.aplicationlib.model.RequestTokenResponse;
import com.example.aplicationlib.util.ItsPayConstants;

import java.text.SimpleDateFormat;
import java.util.Date;

import itspay.br.com.activity.CadastroUsuarioBase2Activity;
import itspay.br.com.activity.TokenActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.itspay.R;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.singleton.CadastoBaseSingleton;
import itspay.br.com.singleton.CadastroSingleton;
import itspay.br.com.util.UtilsActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by juniorbraga on 10/05/17.
 */

public class CadastroUsuarioBase2Controller extends BaseActivityController<CadastroUsuarioBase2Activity> {

        CadastoBaseSingleton mCadastroBaseSingleton;
        CadastroSingleton mCadastroSingleton;
        EnderecoResponse[] mEnderecoResponse;

    public CadastroUsuarioBase2Controller(CadastroUsuarioBase2Activity activity) {
        super(activity);
        mCadastroBaseSingleton = CadastoBaseSingleton.getInstance();
        mCadastroSingleton = CadastroSingleton.getInstance();
    }

    public void nextPage(){
        if(validaFormulario()){


            CadastroSingleton.getInstance().setmNumerocelular(mCadastroBaseSingleton.getmNumerocelular());

            mCadastroSingleton.setmKey(CadastroSingleton.getInstance().getmNumerocelular() +
                    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));

            Intent intent = new Intent(activity, TokenActivity.class);
            intent.putExtra("tipoActivity",1);
            activity.startActivity(intent);
        }
    }



    public void getEnderecoCep(String cep){
        mProgresDialogUtil.show("Aguarde","Estamos buscando seu endereço");

        Call<EnderecoResponse []> call = ConnectPortadorService.getService().bucaEndereco(cep.replace("-",""));

        call.enqueue(new Callback<EnderecoResponse []>() {
            @Override
            public void onResponse(Call<EnderecoResponse []> call, Response<EnderecoResponse []> response) {

                if(response.body()!=null){
                    mProgresDialogUtil.dismiss();
                    mEnderecoResponse = response.body();


                    activity.getBairro().setText(mEnderecoResponse[0].getBairroInicio().getNome());
                    activity.getTxtestado().setText(mEnderecoResponse[0].getBairroInicio().getLocalidade().getUf().getNome());
                    activity.getTxtcidade().setText(mEnderecoResponse[0].getBairroInicio().getLocalidade().getNome());
                    activity.getEndereco().setText(mEnderecoResponse[0].getNomeAbreviado());
                }else{
                    mProgresDialogUtil.dismiss();
                    UtilsActivity.alertMsg(response.errorBody(), activity);
                }
            }

            @Override
            public void onFailure(Call<EnderecoResponse []> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
                mProgresDialogUtil.dismiss();
                t.printStackTrace();
            }
        });
    }

    public boolean validaFormulario(){


        if(activity.getCep().getText().toString().length() < 9 ){
            activity.getCep().setError(activity.getString(R.string.error_field_required));
            activity.getCep().requestFocus();
            return false;
        }

        if(activity.getBairro().getText().toString().isEmpty()){
            activity.getBairro().setError(activity.getString(R.string.error_field_required));
            activity.getBairro().requestFocus();
            return false;
        }

        if(activity.getEndereco().getText().toString().isEmpty()){
            activity.getEndereco().setError(activity.getString(R.string.error_field_required));
            activity.getEndereco().requestFocus();
            return false;
        }

//        Campo Nao Obrigatorio
        if(activity.getNumerocomplemento().getText().toString().isEmpty()){
            activity.getNumerocomplemento().setError(activity.getString(R.string.error_field_required));
            activity.getNumerocomplemento().requestFocus();
        }

        if(activity.getNumeroresidencial().getText().toString().isEmpty()){
            activity.getNumeroresidencial().setError(activity.getString(R.string.error_field_required));
            activity.getNumeroresidencial().requestFocus();
            return false;
        }

        mCadastroBaseSingleton.setmBairro(activity.getBairro().getText().toString());
        mCadastroBaseSingleton.setmCep(activity.getCep().getText().toString());
        mCadastroBaseSingleton.setmEndereco(activity.getEndereco().getText().toString());
        mCadastroBaseSingleton.setmComplemento(activity.getNumerocomplemento().getText().toString());
        mCadastroBaseSingleton.setmNumero(activity.getNumeroresidencial().getText().toString());
        mCadastroBaseSingleton.setmCidade(activity.getTxtcidade().getText().toString());
        mCadastroBaseSingleton.setmEstado(activity.getTxtestado().getText().toString());

        return true;
    }
}

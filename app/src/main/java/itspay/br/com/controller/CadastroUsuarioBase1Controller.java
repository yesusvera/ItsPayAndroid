package itspay.br.com.controller;

import android.content.Intent;

import com.example.aplicationlib.model.VerificaCredencial;
import com.example.aplicationlib.util.EncriptSHA512;
import com.example.aplicationlib.util.validations.ValidationsForms;

import itspay.br.com.activity.CadastroUsuarioBase1Activity;
import itspay.br.com.activity.CadastroUsuarioBaseActivity;
import itspay.br.com.itspay.R;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.singleton.CadastoBaseSingleton;
import itspay.br.com.util.UtilsActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.aplicationlib.util.UtilsAplication.parserDataService;

/**
 * Created by juniorbraga on 09/05/17.
 */

public class CadastroUsuarioBase1Controller extends BaseActivityController<CadastroUsuarioBase1Activity> {

    CadastoBaseSingleton mCadastroBaseSingleton;

    public CadastroUsuarioBase1Controller(CadastroUsuarioBase1Activity activity) {
        super(activity);
        mCadastroBaseSingleton = CadastoBaseSingleton.getInstance();
    }

    public void nextPage(){
        if (validaFormulario()){

            VerificaCredencial mVerificaCredencial  = new VerificaCredencial();
            String credencial = EncriptSHA512.encript(mCadastroBaseSingleton.getmNumeroCartao().toString());
            mVerificaCredencial.setHashCode(credencial);


            Call<ResponseBody> call =
                    ConnectPortadorService.getService().verificaCredencial(mVerificaCredencial);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.body()!=null){
                        Intent intent = new Intent(activity,CadastroUsuarioBaseActivity.class);
                        activity.startActivity(intent);

                    }else{
                        UtilsActivity.alertMsg(response.errorBody(), activity);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    UtilsActivity.alertIfSocketException(t, activity);
                    t.printStackTrace();
                }
            });

            Intent intent = new Intent(activity,CadastroUsuarioBaseActivity.class);
            activity.startActivity(intent);
        }
    }

    public boolean validaFormulario(){

//        if(activity.getNumeroCartao().getText().length() < 19){
//            activity.getNumeroCartao().setError(activity.getString(R.string.error_invalid_card_number));
//            activity.getNumeroCartao().requestFocus();
//            return false;
//        }
//
//        if(activity.getDataNascimento().getText().toString().length() < 10){
//            activity.getDataNascimento().setError(activity.getString(R.string.error_data_nascimento_invalida));
//            activity.getDataNascimento().requestFocus();
//            return false;
//        }
//
//        if(!ValidationsForms.isCPF(activity.getCpf().getText().toString())){
//            activity.getCpf().setError(activity.getString(R.string.error_invalid_cpf));
//            activity.getCpf().requestFocus();
//            return false;
//        }
//
//        if(activity.getDataNascimento().getText().toString().isEmpty()){
//            activity.getNumerocelular().setError("Numero de Celular Invalido");
//            activity.getNumerocelular().requestFocus();
//            return false;
//        }
//
//        if(activity.getDataNascimento().getText().toString().isEmpty()){
//            activity.getNumeroresidencial().setError("Numero Invalido");
//            activity.getNumeroresidencial().requestFocus();
//            return false;
//        }
//
////        Campo Não Obrigatório
//        if(activity.getDataNascimento().getText().toString().isEmpty()){
//            activity.getNumerocomercial().setError("Campo Vazio");
//            activity.getNumerocomercial().requestFocus();
//        }
//
//        mCadastroBaseSingleton.setmNumeroCartao(activity.getNumeroCartao().getText().toString().replace(".",""));
//        mCadastroBaseSingleton.setmDataNascimento(parserDataService(activity.getDataNascimento().getText().toString()));
//        mCadastroBaseSingleton.setmCpf(activity.getCpf().getText().toString());
        mCadastroBaseSingleton.setmNumerocelular(activity.getNumerocelular().getText().toString().
                replace("(","").
                replace(")","").
                replace("-",""));
//
//        mCadastroBaseSingleton.setmNumeroResidencial(activity.getNumeroresidencial().getText().toString().
//                replace("(","").
//                replace(")","").
//                replace("-",""));
//
//        mCadastroBaseSingleton.setmNumeroComercial(activity.getNumerocomercial().getText().toString().
//                replace("(","").
//                replace(")","").
//                replace("-",""));

        return true;
    }
}

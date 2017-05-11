package itspay.br.com.controller;

import android.content.Intent;

import itspay.br.com.activity.CadastroUsuarioBase1Activity;
import itspay.br.com.activity.CadastroUsuarioBase2Activity;
import itspay.br.com.activity.CadastroUsuarioBaseActivity;
import itspay.br.com.itspay.R;
import itspay.br.com.singleton.CadastoBaseSingleton;

import static com.example.aplicationlib.util.UtilsAplication.parserDataService;

/**
 * Created by juniorbraga on 09/05/17.
 */

public class CadastroUsuarioBaseController extends BaseActivityController<CadastroUsuarioBaseActivity> {

    CadastoBaseSingleton mCadastroBaseSingleton;

    public CadastroUsuarioBaseController(CadastroUsuarioBaseActivity activity) {
        super(activity);
        mCadastroBaseSingleton = CadastoBaseSingleton.getInstance();
    }

    public void nextPage(){
        if (validaFormulario()){
            Intent intent = new Intent(activity, CadastroUsuarioBase2Activity.class);
            activity.startActivity(intent);
        }

    }

    public boolean validaFormulario(){

        if(activity.getNomeCompleto().getText().toString().isEmpty()){
            activity.getNomeCompleto().setError(activity.getString(R.string.error_field_required));
            activity.getNomeCompleto().requestFocus();
            return false;
        }

        if(activity.getNomeMae().getText().toString().isEmpty()){
            activity.getNomeMae().setError(activity.getString(R.string.error_field_required));
            activity.getNomeMae().requestFocus();
            return false;
        }

        if(activity.getNomePai().getText().toString().isEmpty()){
            activity.getNomePai().setError(activity.getString(R.string.error_field_required));
            activity.getNomePai().requestFocus();
            return false;
        }

        if(activity.getRg().getText().toString().length() < 7){
            activity.getRg().setError(activity.getString(R.string.error_invalid_rg));
            activity.getRg().requestFocus();
            return false;
        }

        if(activity.getDataEmissao().getText().toString().length() < 10){
            activity.getDataEmissao().setError(activity.getString(R.string.error_data_invalida));
            activity.getDataEmissao().requestFocus();
            return false;
        }

        if(activity.getOrgaoEmissor().getText().toString().isEmpty()){
            activity.getOrgaoEmissor().setError(activity.getString(R.string.error_field_required));
            activity.getOrgaoEmissor().requestFocus();
            return false;
        }

        if(activity.getNacionalidade().getText().toString().isEmpty()){
            activity.getNacionalidade().setError(activity.getString(R.string.error_field_required));
            activity.getNacionalidade().requestFocus();
            return false;
        }
        if(activity.getNaturalidade().getText().toString().isEmpty()){
            activity.getNaturalidade().setError(activity.getString(R.string.error_field_required));
            activity.getNaturalidade().requestFocus();
            return false;
        }

        if(activity.isEstrageiro){
            if(activity.getNumeropassaport().getText().toString().isEmpty()){
                activity.getNumeropassaport().setError(activity.getString(R.string.error_field_required));
                activity.getNumeropassaport().requestFocus();
                return false;
            }
        }

        mCadastroBaseSingleton.setmNomeCompleto(activity.getNomeCompleto().getText().toString());
        mCadastroBaseSingleton.setmNomeMae(activity.getNomeMae().getText().toString());
        mCadastroBaseSingleton.setmNomePai(activity.getNomePai().getText().toString());
        mCadastroBaseSingleton.setmRg(activity.getRg().getText().toString());
        mCadastroBaseSingleton.setmOrgaoEmissor(activity.getOrgaoEmissor().getText().toString());
        mCadastroBaseSingleton.setmDataEmissao(parserDataService(activity.getDataEmissao().getText().toString()));
        mCadastroBaseSingleton.setmGenero(activity.getmGenero());
        mCadastroBaseSingleton.setmEstadoCivil(activity.getmStadoCivil());
        mCadastroBaseSingleton.setmEstrangeiro(activity.isEstrageiro);
        mCadastroBaseSingleton.setmNumeroPassaporte(activity.getNumeropassaport().getText().toString());
        mCadastroBaseSingleton.setmNacionalidade(activity.getNacionalidade().getText().toString());
        mCadastroBaseSingleton.setmNaturalidade(activity.getNaturalidade().getText().toString());


        return true;
    }

}

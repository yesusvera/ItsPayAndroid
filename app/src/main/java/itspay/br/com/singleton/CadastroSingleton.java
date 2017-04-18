package itspay.br.com.singleton;

/**
 * Created by juniorbraga on 24/02/17.
 */

public class CadastroSingleton {

    private static CadastroSingleton instance = new CadastroSingleton();

    public static CadastroSingleton getInstance() {
        return instance;
    }

    public String mNumeroCartao;
    public String mDataNascimento;
    public String mCpf;
    public String mNumerocelular;
    public String mSenha;
    public String mEmail;

    private CadastroSingleton(){}


    public String getmNumeroCartao() {
        return mNumeroCartao;
    }

    public void setmNumeroCartao(String mNumeroCartao) {
        this.mNumeroCartao = mNumeroCartao;
    }

    public String getmDataNascimento() {
        return mDataNascimento;
    }

    public void setmDataNascimento(String mDataNascimento) {
        this.mDataNascimento = mDataNascimento;
    }

    public String getmCpf() {
        return mCpf;
    }

    public void setmCpf(String mCpf) {
        this.mCpf = mCpf;
    }

    public String getmNumerocelular() {
        return mNumerocelular;
    }

    public void setmNumerocelular(String mNumerocelular) {
        this.mNumerocelular = mNumerocelular;
    }

    public String getmSenha() {
        return mSenha;
    }

    public void setmSenha(String mSenha) {
        this.mSenha = mSenha;
    }
    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

}

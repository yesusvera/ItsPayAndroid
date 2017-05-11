package itspay.br.com.singleton;

/**
 * Created by juniorbraga on 09/05/17.
 */

public class CadastoBaseSingleton {

    private static CadastoBaseSingleton instance = new CadastoBaseSingleton();

    public static CadastoBaseSingleton getInstance() {
        return instance;
    }

    public String mNomeCompleto;
    public String mNomeMae;
    public String mNomePai;
    public String mCpf;
    public String mRg;
    public String mEstadoCivil;
    public String mGenero;
    public String mOrgaoEmissor;

    public String mNacionalidade;
    public String mNaturalidade;

    public String mDataEmissao;
    public String mDataNascimento;

    public String mNumerocelular;
    public String mNumeroPassaporte;
    public String mNumeroResidencial;
    public String mNumeroComercial;
    public String mNumeroCartao;


    public String mSenha;
    public String mEmailPessoal;
    public String mEmailProfissional;

    public String mCep;
    public String mBairro;
    public String mEndereco;
    public String mNumero;
    public String mComplemento;
    public String mCidade;
    public String mEstado;


    public boolean mEstrangeiro;

    public String mKey;

    private CadastoBaseSingleton(){}


    public String getmKey() {
        return mKey;
    }

    public void setmKey(String mKey) {
        this.mKey = mKey;
    }

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

    public String getmGenero() {
        return mGenero;
    }

    public void setmGenero(String mGenero) {
        this.mGenero = mGenero;
    }

    public String getmNomeMae() {
        return mNomeMae;
    }

    public void setmNomeMae(String mNomeMae) {
        this.mNomeMae = mNomeMae;
    }

    public String getmNomePai() {
        return mNomePai;
    }

    public void setmNomePai(String mNomePai) {
        this.mNomePai = mNomePai;
    }

    public String getmDataEmissao() {
        return mDataEmissao;
    }

    public void setmDataEmissao(String mDataEmissao) {
        this.mDataEmissao = mDataEmissao;
    }

    public String getmNumeroResidencial() {
        return mNumeroResidencial;
    }

    public void setmNumeroResidencial(String mNumeroResidencial) {
        this.mNumeroResidencial = mNumeroResidencial;
    }

    public String getmNumeroComercial() {
        return mNumeroComercial;
    }

    public void setmNumeroComercial(String mNumeroComercial) {
        this.mNumeroComercial = mNumeroComercial;
    }

    public String getmOrgaoEmissor() {
        return mOrgaoEmissor;
    }

    public void setmOrgaoEmissor(String mOrgaoEmissor) {
        this.mOrgaoEmissor = mOrgaoEmissor;
    }

    public String getmRg() {
        return mRg;
    }

    public void setmRg(String mRg) {
        this.mRg = mRg;
    }

    public String getmNumeroPassaporte() {
        return mNumeroPassaporte;
    }

    public void setmNumeroPassaporte(String mNumeroPassaporte) {
        this.mNumeroPassaporte = mNumeroPassaporte;
    }

    public String getmNacionalidade() {
        return mNacionalidade;
    }

    public void setmNacionalidade(String mNacionalidade) {
        this.mNacionalidade = mNacionalidade;
    }

    public String getmNaturalidade() {
        return mNaturalidade;
    }

    public void setmNaturalidade(String mNaturalidade) {
        this.mNaturalidade = mNaturalidade;
    }

    public boolean getmEstrangeiro() {
        return mEstrangeiro;
    }

    public void setmEstrangeiro(boolean mEstrangeiro) {
        this.mEstrangeiro = mEstrangeiro;
    }

    public String getmEstadoCivil() {
        return mEstadoCivil;
    }

    public void setmEstadoCivil(String mEstadoCivil) {
        this.mEstadoCivil = mEstadoCivil;
    }

    public String getmNomeCompleto() {
        return mNomeCompleto;
    }

    public void setmNomeCompleto(String mNomeCompleto) {
        this.mNomeCompleto = mNomeCompleto;
    }

    public String getmEmailPessoal() {
        return mEmailPessoal;
    }

    public void setmEmailPessoal(String mEmailPessoal) {
        this.mEmailPessoal = mEmailPessoal;
    }

    public String getmEmailProfissional() {
        return mEmailProfissional;
    }

    public void setmEmailProfissional(String mEmailProfissional) {
        this.mEmailProfissional = mEmailProfissional;
    }

    public String getmCep() {
        return mCep;
    }

    public void setmCep(String mCep) {
        this.mCep = mCep;
    }

    public String getmBairro() {
        return mBairro;
    }

    public void setmBairro(String mBairro) {
        this.mBairro = mBairro;
    }

    public String getmEndereco() {
        return mEndereco;
    }

    public void setmEndereco(String mEndereco) {
        this.mEndereco = mEndereco;
    }

    public String getmNumero() {
        return mNumero;
    }

    public void setmNumero(String mNumero) {
        this.mNumero = mNumero;
    }

    public String getmComplemento() {
        return mComplemento;
    }

    public void setmComplemento(String mComplemento) {
        this.mComplemento = mComplemento;
    }

    public String getmCidade() {
        return mCidade;
    }

    public void setmCidade(String mCidade) {
        this.mCidade = mCidade;
    }

    public String getmEstado() {
        return mEstado;
    }

    public void setmEstado(String mEstado) {
        this.mEstado = mEstado;
    }
}

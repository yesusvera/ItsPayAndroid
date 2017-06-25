package itspay.br.com.singleton;

/**
 * Created by juniorbraga on 09/05/17.
 */

public class CadastoBaseSingleton {

    private static CadastoBaseSingleton instance = new CadastoBaseSingleton();

    public static CadastoBaseSingleton getInstance() {
        return instance;
    }

    public int mTipoPessoa;
    public String mNomeCompleto;
    public String mNomeMae;
    public String mNomePai;
    public String mCpf;
    public String mRg;
    public String mOrgaoEmissor;

    public String mNacionalidade;
    public String mNaturalidade;

    public String mDataEmissao;
    public String mDataNascimento;

    private int mDddTelefoneCelular;
//    private Integer telefoneCelular;

    private int mDddTelefoneResidencial;
//    private Integer telefoneResidencial;

    private int mDddTelefoneComercial;
//    private Integer telefoneComercial;

    public int mNumerocelular;
    public String mNumeroPassaporte;
    public int mNumeroResidencial;
    public int mNumeroComercial;
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

    public int mGenero;
    public int mEstadoCivil;

    public boolean mEstrangeiro;

    public String mKey;

    private String logradouro;
    private String siglaUF;

    private CadastoBaseSingleton(){}


    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getSiglaUF() {
        return siglaUF;
    }

    public void setSiglaUF(String siglaUF) {
        this.siglaUF = siglaUF;
    }

    public int getmTipoPessoa() {
        return mTipoPessoa;
    }

    public void setmTipoPessoa(int mTipoPessoa) {
        this.mTipoPessoa = mTipoPessoa;
    }

    public String getmNomeCompleto() {
        return mNomeCompleto;
    }

    public void setmNomeCompleto(String mNomeCompleto) {
        this.mNomeCompleto = mNomeCompleto;
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

    public String getmCpf() {
        return mCpf;
    }

    public void setmCpf(String mCpf) {
        this.mCpf = mCpf;
    }

    public String getmRg() {
        return mRg;
    }

    public void setmRg(String mRg) {
        this.mRg = mRg;
    }

    public String getmOrgaoEmissor() {
        return mOrgaoEmissor;
    }

    public void setmOrgaoEmissor(String mOrgaoEmissor) {
        this.mOrgaoEmissor = mOrgaoEmissor;
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

    public String getmDataEmissao() {
        return mDataEmissao;
    }

    public void setmDataEmissao(String mDataEmissao) {
        this.mDataEmissao = mDataEmissao;
    }

    public String getmDataNascimento() {
        return mDataNascimento;
    }

    public void setmDataNascimento(String mDataNascimento) {
        this.mDataNascimento = mDataNascimento;
    }

    public int getmDddTelefoneCelular() {
        return mDddTelefoneCelular;
    }

    public void setmDddTelefoneCelular(int mDddTelefoneCelular) {
        this.mDddTelefoneCelular = mDddTelefoneCelular;
    }

    public int getmDddTelefoneResidencial() {
        return mDddTelefoneResidencial;
    }

    public void setmDddTelefoneResidencial(int mDddTelefoneResidencial) {
        this.mDddTelefoneResidencial = mDddTelefoneResidencial;
    }

    public int getmDddTelefoneComercial() {
        return mDddTelefoneComercial;
    }

    public void setmDddTelefoneComercial(int mDddTelefoneComercial) {
        this.mDddTelefoneComercial = mDddTelefoneComercial;
    }

    public int getmNumerocelular() {
        return mNumerocelular;
    }

    public void setmNumerocelular(int mNumerocelular) {
        this.mNumerocelular = mNumerocelular;
    }

    public String getmNumeroPassaporte() {
        return mNumeroPassaporte;
    }

    public void setmNumeroPassaporte(String mNumeroPassaporte) {
        this.mNumeroPassaporte = mNumeroPassaporte;
    }

    public int getmNumeroResidencial() {
        return mNumeroResidencial;
    }

    public void setmNumeroResidencial(int mNumeroResidencial) {
        this.mNumeroResidencial = mNumeroResidencial;
    }

    public int getmNumeroComercial() {
        return mNumeroComercial;
    }

    public void setmNumeroComercial(int mNumeroComercial) {
        this.mNumeroComercial = mNumeroComercial;
    }

    public String getmNumeroCartao() {
        return mNumeroCartao;
    }

    public void setmNumeroCartao(String mNumeroCartao) {
        this.mNumeroCartao = mNumeroCartao;
    }

    public String getmSenha() {
        return mSenha;
    }

    public void setmSenha(String mSenha) {
        this.mSenha = mSenha;
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

    public int getmGenero() {
        return mGenero;
    }

    public void setmGenero(int mGenero) {
        this.mGenero = mGenero;
    }

    public int getmEstadoCivil() {
        return mEstadoCivil;
    }

    public void setmEstadoCivil(int mEstadoCivil) {
        this.mEstadoCivil = mEstadoCivil;
    }

    public boolean ismEstrangeiro() {
        return mEstrangeiro;
    }

    public void setmEstrangeiro(boolean mEstrangeiro) {
        this.mEstrangeiro = mEstrangeiro;
    }

    public String getmKey() {
        return mKey;
    }

    public void setmKey(String mKey) {
        this.mKey = mKey;
    }
}

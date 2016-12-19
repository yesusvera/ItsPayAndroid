package itspay.br.com.model;

/**
 * Created by yesus on 18/12/16.
 */

public  class PortadorLogin {
    private String cpf;
    private String credencial;
    private String dataNascimento;
    private String email;
    private long idInstituicao;
    private long idProcessadora;
    private long origemCadastroLogin;
    private String senha;


    public PortadorLogin(){}

    public PortadorLogin(String cpf, String credencial, String dataNascimento, String email, long idInstituicao, long idProcessadora, long origemCadastroLogin, String senha){
        this.cpf = cpf;
        this.credencial = credencial;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.idInstituicao = idInstituicao;
        this.idProcessadora = idProcessadora;
        this.origemCadastroLogin = origemCadastroLogin;
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCredencial() {
        return credencial;
    }

    public void setCredencial(String credencial) {
        this.credencial = credencial;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(long idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public long getIdProcessadora() {
        return idProcessadora;
    }

    public void setIdProcessadora(long idProcessadora) {
        this.idProcessadora = idProcessadora;
    }

    public long getOrigemCadastroLogin() {
        return origemCadastroLogin;
    }

    public void setOrigemCadastroLogin(long origemCadastroLogin) {
        this.origemCadastroLogin = origemCadastroLogin;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
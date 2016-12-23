package itspay.br.com.model;

/**
 * Created by yesus on 23/12/16.
 */

public class TrocarSenhaPortador {
    public String cpf;
    public long idInstituicao;
    public long idProcessadora;
    public String novaSenha;
    public String senha;

    public TrocarSenhaPortador(String cpf, long idInstituicao, long idProcessadora, String novaSenha, String senha){
        this.cpf = cpf;
        this.idInstituicao = idInstituicao;
        this.idProcessadora = idProcessadora;
        this.novaSenha = novaSenha;
        this.senha = senha;
    }

    public TrocarSenhaPortador() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "TrocarSenhaPortador{" +
                "cpf='" + cpf + '\'' +
                ", idInstituicao=" + idInstituicao +
                ", idProcessadora=" + idProcessadora +
                ", novaSenha='" + novaSenha + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}

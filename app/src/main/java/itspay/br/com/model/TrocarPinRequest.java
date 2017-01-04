package itspay.br.com.model;

/**
 * Created by yesus on 04/01/17.
 */
public class TrocarPinRequest {
    private long idCredencial;
    private String novaSenha;
    private String senha;

    public TrocarPinRequest(long idCredencial, String novaSenha, String senha){
        this.idCredencial = idCredencial;
        this.novaSenha = novaSenha;
        this.senha = senha;
    }

    public TrocarPinRequest() {
    }

    public long getIdCredencial() {
        return idCredencial;
    }

    public void setIdCredencial(long idCredencial) {
        this.idCredencial = idCredencial;
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
        return "TrocarPinRequest{" +
                "idCredencial=" + idCredencial +
                ", novaSenha='" + novaSenha + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
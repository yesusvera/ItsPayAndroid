package itspay.br.com.model;

/**
 * Created by yesus on 23/12/16.
 */

public class TrocarEmail {
    public String documento;
    public String email;
    public long idInstituicao;
    public long idProcessadora;

    public TrocarEmail() {
    }

    public TrocarEmail(String documento, String email, long idInstituicao, long idProcessadora){
        this.documento = documento;
        this.email = email;
        this.idInstituicao = idInstituicao;
        this.idProcessadora = idProcessadora;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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

    @Override
    public String toString() {
        return "TrocarEmail{" +
                "documento='" + documento + '\'' +
                ", email='" + email + '\'' +
                ", idInstituicao=" + idInstituicao +
                ", idProcessadora=" + idProcessadora +
                '}';
    }
}
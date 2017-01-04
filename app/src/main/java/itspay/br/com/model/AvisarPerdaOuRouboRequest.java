package itspay.br.com.model;

/**
 * Created by yesus on 04/01/17.
 */

public class AvisarPerdaOuRouboRequest {
    private long idCredencial;
    private long idUsuario;
    private long status;

    public AvisarPerdaOuRouboRequest(long idCredencial, long idUsuario, long status){
        this.idCredencial = idCredencial;
        this.idUsuario = idUsuario;
        this.status = status;
    }

    public AvisarPerdaOuRouboRequest() {
    }

    public long getIdCredencial() {
        return idCredencial;
    }

    public void setIdCredencial(long idCredencial) {
        this.idCredencial = idCredencial;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AvisarPerdaOuRouboRequest{" +
                "idCredencial=" + idCredencial +
                ", idUsuario=" + idUsuario +
                ", status=" + status +
                '}';
    }
}
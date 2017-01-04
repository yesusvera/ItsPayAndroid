package itspay.br.com.model;

/**
 * Created by yesus on 03/01/17.
 * troca estado da credencial conforme parametros.
 * Tipo estados: 1-trocar status exterior,2-trocar status ecommerce,
 * 3-trocar status saque,4-trocar status uso pessoa,
 * 5-trocar status notificacao transacao
 */

public class TrocarEstadoCredencialRequest {
    private long idCredencial;
    private long idUsuario;
    private long tipoEstado;

    public TrocarEstadoCredencialRequest(long idCredencial, long idUsuario, long tipoEstado){
        this.idCredencial = idCredencial;
        this.idUsuario = idUsuario;
        this.tipoEstado = tipoEstado;
    }

    public TrocarEstadoCredencialRequest() {
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

    public long getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(long tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    @Override
    public String toString() {
        return "TrocarEstadoCredencialRequest{" +
                "idCredencial=" + idCredencial +
                ", idUsuario=" + idUsuario +
                ", tipoEstado=" + tipoEstado +
                '}';
    }
}
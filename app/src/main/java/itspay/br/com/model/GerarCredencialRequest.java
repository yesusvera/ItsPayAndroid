package itspay.br.com.model;

/**
 * Created by yesus on 01/01/17.
 */

public class GerarCredencialRequest {
    private long idConta;
    private long idPessoa;
    private long idUsuario;
    private boolean virtual;
    private String virtualApelido;
    private long virtualMesesValidade;

    public GerarCredencialRequest(long idConta, long idPessoa, long idUsuario, boolean virtual, String virtualApelido, long virtualMesesValidade){
        this.idConta = idConta;
        this.idPessoa = idPessoa;
        this.idUsuario = idUsuario;
        this.virtual = virtual;
        this.virtualApelido = virtualApelido;
        this.virtualMesesValidade = virtualMesesValidade;
    }

    public GerarCredencialRequest() {
    }

    public long getIdConta() {
        return idConta;
    }

    public void setIdConta(long idConta) {
        this.idConta = idConta;
    }

    public long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isVirtual() {
        return virtual;
    }

    public void setVirtual(boolean virtual) {
        this.virtual = virtual;
    }

    public String getVirtualApelido() {
        return virtualApelido;
    }

    public void setVirtualApelido(String virtualApelido) {
        this.virtualApelido = virtualApelido;
    }

    public long getVirtualMesesValidade() {
        return virtualMesesValidade;
    }

    public void setVirtualMesesValidade(long virtualMesesValidade) {
        this.virtualMesesValidade = virtualMesesValidade;
    }

    @Override
    public String toString() {
        return "GerarCredencialRequest{" +
                "idConta=" + idConta +
                ", idPessoa=" + idPessoa +
                ", idUsuario=" + idUsuario +
                ", virtual=" + virtual +
                ", virtualApelido='" + virtualApelido + '\'' +
                ", virtualMesesValidade=" + virtualMesesValidade +
                '}';
    }
}
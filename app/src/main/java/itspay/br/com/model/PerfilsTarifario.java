package itspay.br.com.model;

/**
 * Created by yesus on 06/01/17.
 */

public class PerfilsTarifario {
    private String descPerfil;
    private String descTransacaoEstendida;
    private String descTransacaoReduzida;
    private long idPerfilTarifario;
    private double valorTarifa;

    public PerfilsTarifario(String descPerfil, String descTransacaoEstendida, String descTransacaoReduzida, long idPerfilTarifario, long valorTarifa){
        this.descPerfil = descPerfil;
        this.descTransacaoEstendida = descTransacaoEstendida;
        this.descTransacaoReduzida = descTransacaoReduzida;
        this.idPerfilTarifario = idPerfilTarifario;
        this.valorTarifa = valorTarifa;
    }

    public PerfilsTarifario() {
    }

    public String getDescPerfil() {
        return descPerfil;
    }

    public void setDescPerfil(String descPerfil) {
        this.descPerfil = descPerfil;
    }

    public String getDescTransacaoEstendida() {
        return descTransacaoEstendida;
    }

    public void setDescTransacaoEstendida(String descTransacaoEstendida) {
        this.descTransacaoEstendida = descTransacaoEstendida;
    }

    public String getDescTransacaoReduzida() {
        return descTransacaoReduzida;
    }

    public void setDescTransacaoReduzida(String descTransacaoReduzida) {
        this.descTransacaoReduzida = descTransacaoReduzida;
    }

    public long getIdPerfilTarifario() {
        return idPerfilTarifario;
    }

    public void setIdPerfilTarifario(long idPerfilTarifario) {
        this.idPerfilTarifario = idPerfilTarifario;
    }

    public double getValorTarifa() {
        return valorTarifa;
    }

    public void setValorTarifa(double valorTarifa) {
        this.valorTarifa = valorTarifa;
    }

    @Override
    public String toString() {
        return "PerfilsTarifario{" +
                "descPerfil='" + descPerfil + '\'' +
                ", descTransacaoEstendida='" + descTransacaoEstendida + '\'' +
                ", descTransacaoReduzida='" + descTransacaoReduzida + '\'' +
                ", idPerfilTarifario=" + idPerfilTarifario +
                ", valorTarifa=" + valorTarifa +
                '}';
    }
}
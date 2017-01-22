package itspay.br.com.model;

/**
 * Created by yesus on 21/01/17.
 */

public class Caracteristica {
    private long idCaracteristicaReferencia;
    private long idReferenciaSKU;
    private String nome;
    private String valor;

    public Caracteristica(long idCaracteristicaReferencia, long idReferenciaSKU, String nome, String valor) {
        this.idCaracteristicaReferencia = idCaracteristicaReferencia;
        this.idReferenciaSKU = idReferenciaSKU;
        this.nome = nome;
        this.valor = valor;
    }

    public Caracteristica() {
    }

    public long getIdCaracteristicaReferencia() {
        return idCaracteristicaReferencia;
    }

    public void setIdCaracteristicaReferencia(long idCaracteristicaReferencia) {
        this.idCaracteristicaReferencia = idCaracteristicaReferencia;
    }

    public long getIdReferenciaSKU() {
        return idReferenciaSKU;
    }

    public void setIdReferenciaSKU(long idReferenciaSKU) {
        this.idReferenciaSKU = idReferenciaSKU;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Caracteristica{" +
                "idCaracteristicaReferencia=" + idCaracteristicaReferencia +
                ", idReferenciaSKU=" + idReferenciaSKU +
                ", nome='" + nome + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
}
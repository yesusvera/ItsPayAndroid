package itspay.br.com.model;

import java.util.Arrays;

/**
 * Created by yesus on 24/01/17.
 */

public class Referencia {
    private Caracteristica caracteristicas[];
    private boolean disponivel;
    private double freteMedio;
    private long idParceiro;
    private String idProduto;
    private long idReferenciaSKU;
    private long idSKU;
    private double precoDe;
    private double precoPor;
    private String referencia;
    private double saldo;

    public Referencia(Caracteristica[] caracteristicas, boolean disponivel, double freteMedio, long idParceiro, String idProduto, long idReferenciaSKU, long idSKU, double precoDe, double precoPor, String referencia, double saldo) {
        this.caracteristicas = caracteristicas;
        this.disponivel = disponivel;
        this.freteMedio = freteMedio;
        this.idParceiro = idParceiro;
        this.idProduto = idProduto;
        this.idReferenciaSKU = idReferenciaSKU;
        this.idSKU = idSKU;
        this.precoDe = precoDe;
        this.precoPor = precoPor;
        this.referencia = referencia;
        this.saldo = saldo;
    }

    public Referencia() {
    }

    public Caracteristica[] getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Caracteristica[] caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public double getFreteMedio() {
        return freteMedio;
    }

    public void setFreteMedio(double freteMedio) {
        this.freteMedio = freteMedio;
    }

    public long getIdParceiro() {
        return idParceiro;
    }

    public void setIdParceiro(long idParceiro) {
        this.idParceiro = idParceiro;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public long getIdReferenciaSKU() {
        return idReferenciaSKU;
    }

    public void setIdReferenciaSKU(long idReferenciaSKU) {
        this.idReferenciaSKU = idReferenciaSKU;
    }

    public long getIdSKU() {
        return idSKU;
    }

    public void setIdSKU(long idSKU) {
        this.idSKU = idSKU;
    }

    public double getPrecoDe() {
        return precoDe;
    }

    public void setPrecoDe(double precoDe) {
        this.precoDe = precoDe;
    }

    public double getPrecoPor() {
        return precoPor;
    }

    public void setPrecoPor(double precoPor) {
        this.precoPor = precoPor;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Referencia{" +
                "caracteristicas=" + Arrays.toString(caracteristicas) +
                ", disponivel=" + disponivel +
                ", freteMedio=" + freteMedio +
                ", idParceiro=" + idParceiro +
                ", idProduto='" + idProduto + '\'' +
                ", idReferenciaSKU=" + idReferenciaSKU +
                ", idSKU=" + idSKU +
                ", precoDe=" + precoDe +
                ", precoPor=" + precoPor +
                ", referencia='" + referencia + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
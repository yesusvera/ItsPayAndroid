package itspay.br.com.model;

import java.util.Arrays;

/**
 * Created by yesus on 21/01/17.
 */

public class ItemPedido {
    private long idPedido;
    private long sequenciaItemPedido;
    private long idSKU;
    private long idParceiro;
    private long idProcessadora;
    private long idInstituicao;
    private double valorUnitario;
    private long quantidadeItem;
    private double valorTotalItem;
    private String nomeProduto;
    private String descricao;
    private long idReferenciaSKU;
    private Caracteristica caracteristicas[];

    public ItemPedido(long idPedido, long sequenciaItemPedido, long idSKU, long idParceiro, long idProcessadora, long idInstituicao, double valorUnitario, long quantidadeItem, double valorTotalItem, String nomeProduto, String descricao, long idReferenciaSKU, Caracteristica[] caracteristicas) {
        this.idPedido = idPedido;
        this.sequenciaItemPedido = sequenciaItemPedido;
        this.idSKU = idSKU;
        this.idParceiro = idParceiro;
        this.idProcessadora = idProcessadora;
        this.idInstituicao = idInstituicao;
        this.valorUnitario = valorUnitario;
        this.quantidadeItem = quantidadeItem;
        this.valorTotalItem = valorTotalItem;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.idReferenciaSKU = idReferenciaSKU;
        this.caracteristicas = caracteristicas;
    }

    public ItemPedido() {
    }

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

    public long getSequenciaItemPedido() {
        return sequenciaItemPedido;
    }

    public void setSequenciaItemPedido(long sequenciaItemPedido) {
        this.sequenciaItemPedido = sequenciaItemPedido;
    }

    public long getIdSKU() {
        return idSKU;
    }

    public void setIdSKU(long idSKU) {
        this.idSKU = idSKU;
    }

    public long getIdParceiro() {
        return idParceiro;
    }

    public void setIdParceiro(long idParceiro) {
        this.idParceiro = idParceiro;
    }

    public long getIdProcessadora() {
        return idProcessadora;
    }

    public void setIdProcessadora(long idProcessadora) {
        this.idProcessadora = idProcessadora;
    }

    public long getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(long idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public long getQuantidadeItem() {
        return quantidadeItem;
    }

    public void setQuantidadeItem(long quantidadeItem) {
        this.quantidadeItem = quantidadeItem;
    }

    public double getValorTotalItem() {
        return valorTotalItem;
    }

    public void setValorTotalItem(double valorTotalItem) {
        this.valorTotalItem = valorTotalItem;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getIdReferenciaSKU() {
        return idReferenciaSKU;
    }

    public void setIdReferenciaSKU(long idReferenciaSKU) {
        this.idReferenciaSKU = idReferenciaSKU;
    }

    public Caracteristica[] getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Caracteristica[] caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "idPedido=" + idPedido +
                ", sequenciaItemPedido=" + sequenciaItemPedido +
                ", idSKU=" + idSKU +
                ", idParceiro=" + idParceiro +
                ", idProcessadora=" + idProcessadora +
                ", idInstituicao=" + idInstituicao +
                ", valorUnitario=" + valorUnitario +
                ", quantidadeItem=" + quantidadeItem +
                ", valorTotalItem=" + valorTotalItem +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", descricao='" + descricao + '\'' +
                ", idReferenciaSKU=" + idReferenciaSKU +
                ", caracteristicas=" + Arrays.toString(caracteristicas) +
                '}';
    }
}
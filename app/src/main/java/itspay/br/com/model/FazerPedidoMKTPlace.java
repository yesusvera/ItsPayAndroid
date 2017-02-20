package itspay.br.com.model;

import java.util.Arrays;

/**
 * Created by yesus on 08/02/17.
 */

public class FazerPedidoMKTPlace {
    private long idConta;
    private long idCredencial;
    private long idEndereco;
    private long idInstituicao;
    private long idParceiro;
    private long idProcessadora;
    private ItemPedidoReduzido itens[];
    private long quantidadeParcelas;
    private String senhaCredencial;
    private long tipoEntrega;
    private double valorFrete;


    public FazerPedidoMKTPlace(long idConta, long idCredencial, long idEndereco, long idInstituicao, long idParceiro, long idProcessadora, ItemPedidoReduzido[] itens, long quantidadeParcelas, String senhaCredencial, long tipoEntrega, double valorFrete) {
        this.idConta = idConta;
        this.idCredencial = idCredencial;
        this.idEndereco = idEndereco;
        this.idInstituicao = idInstituicao;
        this.idParceiro = idParceiro;
        this.idProcessadora = idProcessadora;
        this.itens = itens;
        this.quantidadeParcelas = quantidadeParcelas;
        this.senhaCredencial = senhaCredencial;
        this.tipoEntrega = tipoEntrega;
        this.valorFrete = valorFrete;
    }

    public FazerPedidoMKTPlace() {
    }

    public long getIdConta() {
        return idConta;
    }

    public void setIdConta(long idConta) {
        this.idConta = idConta;
    }

    public long getIdCredencial() {
        return idCredencial;
    }

    public void setIdCredencial(long idCredencial) {
        this.idCredencial = idCredencial;
    }

    public long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public long getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(long idInstituicao) {
        this.idInstituicao = idInstituicao;
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

    public ItemPedidoReduzido[] getItens() {
        return itens;
    }

    public void setItens(ItemPedidoReduzido[] itens) {
        this.itens = itens;
    }

    public long getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(long quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public String getSenhaCredencial() {
        return senhaCredencial;
    }

    public void setSenhaCredencial(String senhaCredencial) {
        this.senhaCredencial = senhaCredencial;
    }

    public long getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(long tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(double valorFrete) {
        this.valorFrete = valorFrete;
    }

    @Override
    public String toString() {
        return "FazerPedidoMKTPlace{" +
                "idConta=" + idConta +
                ", idCredencial=" + idCredencial +
                ", idEndereco=" + idEndereco +
                ", idInstituicao=" + idInstituicao +
                ", idParceiro=" + idParceiro +
                ", idProcessadora=" + idProcessadora +
                ", itens=" + Arrays.toString(itens) +
                ", quantidadeParcelas=" + quantidadeParcelas +
                ", senhaCredencial='" + senhaCredencial + '\'' +
                ", tipoEntrega=" + tipoEntrega +
                ", valorFrete=" + valorFrete +
                '}';
    }
}
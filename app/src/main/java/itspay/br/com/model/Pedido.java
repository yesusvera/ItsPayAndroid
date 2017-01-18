package itspay.br.com.model;

/**
 * Created by yesus on 16/01/17.
 */

public class Pedido {
//    private DataHoraPedido dataHoraPedido;
    private String dataHoraPedido;
    private String dataHoraPedidoStr;
//    private DataHoraStatus dataHoraStatus;
    private String dataHoraStatus;
    private String dataHoraStatusStr;
    private String descStatus;
    private long idConta;
    private long idCredencial;
    private long idEndereco;
    private long idInstituicao;
    private long idParceiro;
    private long idPedido;
    private long idProcessadora;
    private long quantidadeParcelas;
    private long status;
    private long tipoEntrega;
    private double valorFrete;
    private double valorTotal;

    public Pedido(String dataHoraPedido, String dataHoraPedidoStr, String dataHoraStatus, String dataHoraStatusStr, String descStatus, long idConta, long idCredencial, long idEndereco, long idInstituicao, long idParceiro, long idPedido, long idProcessadora, long quantidadeParcelas, long status, long tipoEntrega, double valorFrete, double valorTotal) {
        this.dataHoraPedido = dataHoraPedido;
        this.dataHoraPedidoStr = dataHoraPedidoStr;
        this.dataHoraStatus = dataHoraStatus;
        this.dataHoraStatusStr = dataHoraStatusStr;
        this.descStatus = descStatus;
        this.idConta = idConta;
        this.idCredencial = idCredencial;
        this.idEndereco = idEndereco;
        this.idInstituicao = idInstituicao;
        this.idParceiro = idParceiro;
        this.idPedido = idPedido;
        this.idProcessadora = idProcessadora;
        this.quantidadeParcelas = quantidadeParcelas;
        this.status = status;
        this.tipoEntrega = tipoEntrega;
        this.valorFrete = valorFrete;
        this.valorTotal = valorTotal;
    }

    public Pedido() {
    }

    public String getDataHoraPedido() {
        return dataHoraPedido;
    }

    public void setDataHoraPedido(String dataHoraPedido) {
        this.dataHoraPedido = dataHoraPedido;
    }

    public String getDataHoraPedidoStr() {
        return dataHoraPedidoStr;
    }

    public void setDataHoraPedidoStr(String dataHoraPedidoStr) {
        this.dataHoraPedidoStr = dataHoraPedidoStr;
    }

    public String getDataHoraStatus() {
        return dataHoraStatus;
    }

    public void setDataHoraStatus(String dataHoraStatus) {
        this.dataHoraStatus = dataHoraStatus;
    }

    public String getDataHoraStatusStr() {
        return dataHoraStatusStr;
    }

    public void setDataHoraStatusStr(String dataHoraStatusStr) {
        this.dataHoraStatusStr = dataHoraStatusStr;
    }

    public String getDescStatus() {
        return descStatus;
    }

    public void setDescStatus(String descStatus) {
        this.descStatus = descStatus;
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

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

    public long getIdProcessadora() {
        return idProcessadora;
    }

    public void setIdProcessadora(long idProcessadora) {
        this.idProcessadora = idProcessadora;
    }

    public long getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(long quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
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

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "dataHoraPedido='" + dataHoraPedido + '\'' +
                ", dataHoraPedidoStr='" + dataHoraPedidoStr + '\'' +
                ", dataHoraStatus='" + dataHoraStatus + '\'' +
                ", dataHoraStatusStr='" + dataHoraStatusStr + '\'' +
                ", descStatus='" + descStatus + '\'' +
                ", idConta=" + idConta +
                ", idCredencial=" + idCredencial +
                ", idEndereco=" + idEndereco +
                ", idInstituicao=" + idInstituicao +
                ", idParceiro=" + idParceiro +
                ", idPedido=" + idPedido +
                ", idProcessadora=" + idProcessadora +
                ", quantidadeParcelas=" + quantidadeParcelas +
                ", status=" + status +
                ", tipoEntrega=" + tipoEntrega +
                ", valorFrete=" + valorFrete +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
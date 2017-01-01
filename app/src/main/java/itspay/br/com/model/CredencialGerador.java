package itspay.br.com.model;

/**
 * Created by yesus on 01/01/17.
 */

public class CredencialGerador {
    private long codTransacao;
    private String dataAgendamento;
    private String dataHoraInclusao;
    private String dataHoraManutencao;
    private String dataHoraStatus;
    private String descStatus;
    private long idFilial;
    private long idInstituicao;
    private long idLote;
    private long idPontoDeRelacionamento;
    private long idProcessadora;
    private long idProdutoInstituicao;
    private long idRegional;
    private long idUsuarioInclusao;
    private long idUsuarioManutencao;
    private ProdutoInstituicao produtoInstituicao;
    private long qtdLancamento;
    private long status;
    private double valorTotalLancamento;

    public CredencialGerador(long codTransacao, String dataAgendamento, String dataHoraInclusao,
                             String dataHoraManutencao, String dataHoraStatus, String descStatus,
                             long idFilial, long idInstituicao, long idLote, long idPontoDeRelacionamento,
                             long idProcessadora, long idProdutoInstituicao, long idRegional,
                             long idUsuarioInclusao, long idUsuarioManutencao,
                             ProdutoInstituicao produtoInstituicao, long qtdLancamento,
                             long status, double valorTotalLancamento) {
        this.codTransacao = codTransacao;
        this.dataAgendamento = dataAgendamento;
        this.dataHoraInclusao = dataHoraInclusao;
        this.dataHoraManutencao = dataHoraManutencao;
        this.dataHoraStatus = dataHoraStatus;
        this.descStatus = descStatus;
        this.idFilial = idFilial;
        this.idInstituicao = idInstituicao;
        this.idLote = idLote;
        this.idPontoDeRelacionamento = idPontoDeRelacionamento;
        this.idProcessadora = idProcessadora;
        this.idProdutoInstituicao = idProdutoInstituicao;
        this.idRegional = idRegional;
        this.idUsuarioInclusao = idUsuarioInclusao;
        this.idUsuarioManutencao = idUsuarioManutencao;
        this.produtoInstituicao = produtoInstituicao;
        this.qtdLancamento = qtdLancamento;
        this.status = status;
        this.valorTotalLancamento = valorTotalLancamento;
    }

    public CredencialGerador() {
    }

    public long getCodTransacao() {
        return codTransacao;
    }

    public void setCodTransacao(long codTransacao) {
        this.codTransacao = codTransacao;
    }

    public String getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(String dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public String getDataHoraInclusao() {
        return dataHoraInclusao;
    }

    public void setDataHoraInclusao(String dataHoraInclusao) {
        this.dataHoraInclusao = dataHoraInclusao;
    }

    public String getDataHoraManutencao() {
        return dataHoraManutencao;
    }

    public void setDataHoraManutencao(String dataHoraManutencao) {
        this.dataHoraManutencao = dataHoraManutencao;
    }

    public String getDataHoraStatus() {
        return dataHoraStatus;
    }

    public void setDataHoraStatus(String dataHoraStatus) {
        this.dataHoraStatus = dataHoraStatus;
    }

    public String getDescStatus() {
        return descStatus;
    }

    public void setDescStatus(String descStatus) {
        this.descStatus = descStatus;
    }

    public long getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(long idFilial) {
        this.idFilial = idFilial;
    }

    public long getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(long idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public long getIdLote() {
        return idLote;
    }

    public void setIdLote(long idLote) {
        this.idLote = idLote;
    }

    public long getIdPontoDeRelacionamento() {
        return idPontoDeRelacionamento;
    }

    public void setIdPontoDeRelacionamento(long idPontoDeRelacionamento) {
        this.idPontoDeRelacionamento = idPontoDeRelacionamento;
    }

    public long getIdProcessadora() {
        return idProcessadora;
    }

    public void setIdProcessadora(long idProcessadora) {
        this.idProcessadora = idProcessadora;
    }

    public long getIdProdutoInstituicao() {
        return idProdutoInstituicao;
    }

    public void setIdProdutoInstituicao(long idProdutoInstituicao) {
        this.idProdutoInstituicao = idProdutoInstituicao;
    }

    public long getIdRegional() {
        return idRegional;
    }

    public void setIdRegional(long idRegional) {
        this.idRegional = idRegional;
    }

    public long getIdUsuarioInclusao() {
        return idUsuarioInclusao;
    }

    public void setIdUsuarioInclusao(long idUsuarioInclusao) {
        this.idUsuarioInclusao = idUsuarioInclusao;
    }

    public long getIdUsuarioManutencao() {
        return idUsuarioManutencao;
    }

    public void setIdUsuarioManutencao(long idUsuarioManutencao) {
        this.idUsuarioManutencao = idUsuarioManutencao;
    }

    public ProdutoInstituicao getProdutoInstituicao() {
        return produtoInstituicao;
    }

    public void setProdutoInstituicao(ProdutoInstituicao produtoInstituicao) {
        this.produtoInstituicao = produtoInstituicao;
    }

    public long getQtdLancamento() {
        return qtdLancamento;
    }

    public void setQtdLancamento(long qtdLancamento) {
        this.qtdLancamento = qtdLancamento;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public double getValorTotalLancamento() {
        return valorTotalLancamento;
    }

    public void setValorTotalLancamento(double valorTotalLancamento) {
        this.valorTotalLancamento = valorTotalLancamento;
    }

    @Override
    public String toString() {
        return "CredencialGerador{" +
                "codTransacao=" + codTransacao +
                ", dataAgendamento='" + dataAgendamento + '\'' +
                ", dataHoraInclusao='" + dataHoraInclusao + '\'' +
                ", dataHoraManutencao='" + dataHoraManutencao + '\'' +
                ", dataHoraStatus='" + dataHoraStatus + '\'' +
                ", descStatus='" + descStatus + '\'' +
                ", idFilial=" + idFilial +
                ", idInstituicao=" + idInstituicao +
                ", idLote=" + idLote +
                ", idPontoDeRelacionamento=" + idPontoDeRelacionamento +
                ", idProcessadora=" + idProcessadora +
                ", idProdutoInstituicao=" + idProdutoInstituicao +
                ", idRegional=" + idRegional +
                ", idUsuarioInclusao=" + idUsuarioInclusao +
                ", idUsuarioManutencao=" + idUsuarioManutencao +
                ", produtoInstituicao=" + produtoInstituicao +
                ", qtdLancamento=" + qtdLancamento +
                ", status=" + status +
                ", valorTotalLancamento=" + valorTotalLancamento +
                '}';
    }
}
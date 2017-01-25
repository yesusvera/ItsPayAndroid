package itspay.br.com.model;

/**
 * Created by yesus on 21/01/17.
 */

public class PedidoDetalhe {
    private long idPedido;
    private long idParceiro;
    private long idProcessadora;
    private long idInstituicao;
    private long idConta;
    private long idCredencial;
    private double valorTotal;
    private long quantidadeParcelas;
    private long status;
    private String descStatus;
    private String dataHoraStatus;
    private String dataHoraPedido;
    private long idEndereco;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private long tipoEntrega;
    private String descTipoEntrega;
    private String dataHoraStatusStr;
    private String dataHoraPedidoStr;
    private double valorFrete;
    private String nomeImpresso;
    private String ultimos4Digitos;
    private String nomeParceiro;
    private double valorParcela;
    private String enderecoCompleto;
    private ItemPedido itensPedido[];

    public PedidoDetalhe(long idPedido, long idParceiro, long idProcessadora, long idInstituicao, long idConta, long idCredencial, double valorTotal, long quantidadeParcelas, long status, String descStatus, String dataHoraStatus, String dataHoraPedido, long idEndereco, String cep, String logradouro, String numero, String complemento, String bairro, String cidade, String uf, long tipoEntrega, String descTipoEntrega, String dataHoraStatusStr, String dataHoraPedidoStr, double valorFrete, String nomeImpresso, String ultimos4Digitos, String nomeParceiro, double valorParcela, String enderecoCompleto, ItemPedido[] itensPedido) {
        this.idPedido = idPedido;
        this.idParceiro = idParceiro;
        this.idProcessadora = idProcessadora;
        this.idInstituicao = idInstituicao;
        this.idConta = idConta;
        this.idCredencial = idCredencial;
        this.valorTotal = valorTotal;
        this.quantidadeParcelas = quantidadeParcelas;
        this.status = status;
        this.descStatus = descStatus;
        this.dataHoraStatus = dataHoraStatus;
        this.dataHoraPedido = dataHoraPedido;
        this.idEndereco = idEndereco;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.tipoEntrega = tipoEntrega;
        this.descTipoEntrega = descTipoEntrega;
        this.dataHoraStatusStr = dataHoraStatusStr;
        this.dataHoraPedidoStr = dataHoraPedidoStr;
        this.valorFrete = valorFrete;
        this.nomeImpresso = nomeImpresso;
        this.ultimos4Digitos = ultimos4Digitos;
        this.nomeParceiro = nomeParceiro;
        this.valorParcela = valorParcela;
        this.enderecoCompleto = enderecoCompleto;
        this.itensPedido = itensPedido;
    }

    public PedidoDetalhe() {
    }

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
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

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
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

    public String getDescStatus() {
        return descStatus;
    }

    public void setDescStatus(String descStatus) {
        this.descStatus = descStatus;
    }

    public String getDataHoraStatus() {
        return dataHoraStatus;
    }

    public void setDataHoraStatus(String dataHoraStatus) {
        this.dataHoraStatus = dataHoraStatus;
    }

    public String getDataHoraPedido() {
        return dataHoraPedido;
    }

    public void setDataHoraPedido(String dataHoraPedido) {
        this.dataHoraPedido = dataHoraPedido;
    }

    public long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public long getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(long tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public String getDescTipoEntrega() {
        return descTipoEntrega;
    }

    public void setDescTipoEntrega(String descTipoEntrega) {
        this.descTipoEntrega = descTipoEntrega;
    }

    public String getDataHoraStatusStr() {
        return dataHoraStatusStr;
    }

    public void setDataHoraStatusStr(String dataHoraStatusStr) {
        this.dataHoraStatusStr = dataHoraStatusStr;
    }

    public String getDataHoraPedidoStr() {
        return dataHoraPedidoStr;
    }

    public void setDataHoraPedidoStr(String dataHoraPedidoStr) {
        this.dataHoraPedidoStr = dataHoraPedidoStr;
    }

    public double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public String getNomeImpresso() {
        return nomeImpresso;
    }

    public void setNomeImpresso(String nomeImpresso) {
        this.nomeImpresso = nomeImpresso;
    }

    public String getUltimos4Digitos() {
        return ultimos4Digitos;
    }

    public void setUltimos4Digitos(String ultimos4Digitos) {
        this.ultimos4Digitos = ultimos4Digitos;
    }

    public String getNomeParceiro() {
        return nomeParceiro;
    }

    public void setNomeParceiro(String nomeParceiro) {
        this.nomeParceiro = nomeParceiro;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public String getEnderecoCompleto() {
        return enderecoCompleto;
    }

    public void setEnderecoCompleto(String enderecoCompleto) {
        this.enderecoCompleto = enderecoCompleto;
    }

    public ItemPedido[] getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(ItemPedido[] itensPedido) {
        this.itensPedido = itensPedido;
    }
}
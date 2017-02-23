package itspay.br.com.model;

import android.graphics.drawable.Drawable;

/**
 * Created by yesus on 19/12/16.
 */

public class Credencial {
    private String apelidoVirtual;
    private String codigoSeguranca;
    private String contaPagamento;
    private String credencialMascarada;
    private String credencialMascaradaReduzida;
    private String credencialUltimosDigitos;
    private String credencialVirtual;
    private String dataHoraInclusao;
    private String dataSaldo;
    private String dataValidade;
    private String dataValidadeFmt;
    private String descGrupoStatus;
    private String descStatus;
    private String email;
    private long grupoStatus;
    private long idConta;
    private long idCredencial;
    private long idPessoa;
    private long idPlastico;
    private long idProduto;
    private long idProdutoPlataforma;
    private long limiteDisponivel;
    private String nomeImpresso;
    private String nomeProduto;
    private String preparaDataSaldo;
    private double saldo;
    private long status;
    private long tipoConta;
    private String urlImagemProduto;
    transient private Drawable drawable;


    public Credencial() {
    }

    public Credencial(String apelidoVirtual, String codigoSeguranca, String contaPagamento,
                      String credencialMascarada, String credencialMascaradaReduzida, String credencialUltimosDigitos,
                      String credencialVirtual, String dataHoraInclusao, String dataSaldo,
                      String dataValidade, String dataValidadeFmt, String descGrupoStatus,
                      String descStatus, String email, long grupoStatus, long idConta,
                      long idCredencial, long idPessoa, long idPlastico, long idProduto,
                      long idProdutoPlataforma, long limiteDisponivel, String nomeImpresso, String nomeProduto,
                      String preparaDataSaldo, double saldo, long status, long tipoConta, String urlImagemProduto) {
        this.apelidoVirtual = apelidoVirtual;
        this.codigoSeguranca = codigoSeguranca;
        this.contaPagamento = contaPagamento;
        this.credencialMascarada = credencialMascarada;
        this.credencialMascaradaReduzida = credencialMascaradaReduzida;
        this.credencialUltimosDigitos = credencialUltimosDigitos;
        this.credencialVirtual = credencialVirtual;
        this.dataHoraInclusao = dataHoraInclusao;
        this.dataSaldo = dataSaldo;
        this.dataValidade = dataValidade;
        this.dataValidadeFmt = dataValidadeFmt;
        this.descGrupoStatus = descGrupoStatus;
        this.descStatus = descStatus;
        this.email = email;
        this.grupoStatus = grupoStatus;
        this.idConta = idConta;
        this.idCredencial = idCredencial;
        this.idPessoa = idPessoa;
        this.idPlastico = idPlastico;
        this.idProduto = idProduto;
        this.idProdutoPlataforma = idProdutoPlataforma;
        this.limiteDisponivel = limiteDisponivel;
        this.nomeImpresso = nomeImpresso;
        this.nomeProduto = nomeProduto;
        this.preparaDataSaldo = preparaDataSaldo;
        this.saldo = saldo;
        this.status = status;
        this.tipoConta = tipoConta;
        this.urlImagemProduto = urlImagemProduto;
    }

    public String getApelidoVirtual() {
        return apelidoVirtual;
    }

    public void setApelidoVirtual(String apelidoVirtual) {
        this.apelidoVirtual = apelidoVirtual;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(String codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }

    public String getContaPagamento() {
        return contaPagamento;
    }

    public void setContaPagamento(String contaPagamento) {
        this.contaPagamento = contaPagamento;
    }

    public String getCredencialMascarada() {
        return credencialMascarada;
    }

    public void setCredencialMascarada(String credencialMascarada) {
        this.credencialMascarada = credencialMascarada;
    }

    public String getCredencialMascaradaReduzida() {
        return credencialMascaradaReduzida;
    }

    public void setCredencialMascaradaReduzida(String credencialMascaradaReduzida) {
        this.credencialMascaradaReduzida = credencialMascaradaReduzida;
    }

    public String getCredencialUltimosDigitos() {
        return credencialUltimosDigitos;
    }

    public void setCredencialUltimosDigitos(String credencialUltimosDigitos) {
        this.credencialUltimosDigitos = credencialUltimosDigitos;
    }

    public String getCredencialVirtual() {
        return credencialVirtual;
    }

    public void setCredencialVirtual(String credencialVirtual) {
        this.credencialVirtual = credencialVirtual;
    }

    public String getDataHoraInclusao() {
        return dataHoraInclusao;
    }

    public void setDataHoraInclusao(String dataHoraInclusao) {
        this.dataHoraInclusao = dataHoraInclusao;
    }

    public String getDataSaldo() {
        return dataSaldo;
    }

    public void setDataSaldo(String dataSaldo) {
        this.dataSaldo = dataSaldo;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getDataValidadeFmt() {
        return dataValidadeFmt;
    }

    public void setDataValidadeFmt(String dataValidadeFmt) {
        this.dataValidadeFmt = dataValidadeFmt;
    }

    public String getDescGrupoStatus() {
        return descGrupoStatus;
    }

    public void setDescGrupoStatus(String descGrupoStatus) {
        this.descGrupoStatus = descGrupoStatus;
    }

    public String getDescStatus() {
        return descStatus;
    }

    public void setDescStatus(String descStatus) {
        this.descStatus = descStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getGrupoStatus() {
        return grupoStatus;
    }

    public void setGrupoStatus(long grupoStatus) {
        this.grupoStatus = grupoStatus;
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

    public long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public long getIdPlastico() {
        return idPlastico;
    }

    public void setIdPlastico(long idPlastico) {
        this.idPlastico = idPlastico;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public long getIdProdutoPlataforma() {
        return idProdutoPlataforma;
    }

    public void setIdProdutoPlataforma(long idProdutoPlataforma) {
        this.idProdutoPlataforma = idProdutoPlataforma;
    }

    public long getLimiteDisponivel() {
        return limiteDisponivel;
    }

    public void setLimiteDisponivel(long limiteDisponivel) {
        this.limiteDisponivel = limiteDisponivel;
    }

    public String getNomeImpresso() {
        return nomeImpresso;
    }

    public void setNomeImpresso(String nomeImpresso) {
        this.nomeImpresso = nomeImpresso;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getPreparaDataSaldo() {
        return preparaDataSaldo;
    }

    public void setPreparaDataSaldo(String preparaDataSaldo) {
        this.preparaDataSaldo = preparaDataSaldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public long getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(long tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getUrlImagemProduto() {
        return urlImagemProduto;
    }

    public void setUrlImagemProduto(String urlImagemProduto) {
        this.urlImagemProduto = urlImagemProduto;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    @Override
    public String toString() {
        return "Credencial{" +
                "apelidoVirtual='" + apelidoVirtual + '\'' +
                ", codigoSeguranca='" + codigoSeguranca + '\'' +
                ", contaPagamento='" + contaPagamento + '\'' +
                ", credencialMascarada='" + credencialMascarada + '\'' +
                ", credencialMascaradaReduzida='" + credencialMascaradaReduzida + '\'' +
                ", credencialUltimosDigitos='" + credencialUltimosDigitos + '\'' +
                ", credencialVirtual='" + credencialVirtual + '\'' +
                ", dataHoraInclusao='" + dataHoraInclusao + '\'' +
                ", dataSaldo='" + dataSaldo + '\'' +
                ", dataValidade='" + dataValidade + '\'' +
                ", dataValidadeFmt='" + dataValidadeFmt + '\'' +
                ", descGrupoStatus='" + descGrupoStatus + '\'' +
                ", descStatus='" + descStatus + '\'' +
                ", email='" + email + '\'' +
                ", grupoStatus=" + grupoStatus +
                ", idConta=" + idConta +
                ", idCredencial=" + idCredencial +
                ", idPessoa=" + idPessoa +
                ", idPlastico=" + idPlastico +
                ", idProduto=" + idProduto +
                ", idProdutoPlataforma=" + idProdutoPlataforma +
                ", limiteDisponivel=" + limiteDisponivel +
                ", nomeImpresso='" + nomeImpresso + '\'' +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", preparaDataSaldo='" + preparaDataSaldo + '\'' +
                ", saldo=" + saldo +
                ", status=" + status +
                ", tipoConta=" + tipoConta +
                ", urlImagemProduto='" + urlImagemProduto + '\'' +
                '}';
    }
}
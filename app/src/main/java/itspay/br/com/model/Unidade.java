package itspay.br.com.model;

/**
 * Created by yesus on 24/01/17.
 */

public class Unidade {
    private String bairro;
    private String cep;
    private String cidade;
    private String complemento;
    private long dddTelefone1;
    private long dddTelefone2;
    private long idParceiro;
    private long idUnidade;
    private double latitude;
    private String logradouro;
    private double longitude;
    private String nomeResponsavel;
    private String numero;
    private String observacoes;
    private long telefone1;
    private long telefone2;
    private String uf;

    public Unidade(String bairro, String cep, String cidade, String complemento, long dddTelefone1, long dddTelefone2, long idParceiro, long idUnidade, double latitude, String logradouro, double longitude, String nomeResponsavel, String numero, String observacoes, long telefone1, long telefone2, String uf) {
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.complemento = complemento;
        this.dddTelefone1 = dddTelefone1;
        this.dddTelefone2 = dddTelefone2;
        this.idParceiro = idParceiro;
        this.idUnidade = idUnidade;
        this.latitude = latitude;
        this.logradouro = logradouro;
        this.longitude = longitude;
        this.nomeResponsavel = nomeResponsavel;
        this.numero = numero;
        this.observacoes = observacoes;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.uf = uf;
    }

    public Unidade() {
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public long getDddTelefone1() {
        return dddTelefone1;
    }

    public void setDddTelefone1(long dddTelefone1) {
        this.dddTelefone1 = dddTelefone1;
    }

    public long getDddTelefone2() {
        return dddTelefone2;
    }

    public void setDddTelefone2(long dddTelefone2) {
        this.dddTelefone2 = dddTelefone2;
    }

    public long getIdParceiro() {
        return idParceiro;
    }

    public void setIdParceiro(long idParceiro) {
        this.idParceiro = idParceiro;
    }

    public long getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(long idUnidade) {
        this.idUnidade = idUnidade;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public long getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(long telefone1) {
        this.telefone1 = telefone1;
    }

    public long getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(long telefone2) {
        this.telefone2 = telefone2;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "Unidade{" +
                "bairro='" + bairro + '\'' +
                ", cep='" + cep + '\'' +
                ", cidade='" + cidade + '\'' +
                ", complemento='" + complemento + '\'' +
                ", dddTelefone1=" + dddTelefone1 +
                ", dddTelefone2=" + dddTelefone2 +
                ", idParceiro=" + idParceiro +
                ", idUnidade=" + idUnidade +
                ", latitude=" + latitude +
                ", logradouro='" + logradouro + '\'' +
                ", longitude=" + longitude +
                ", nomeResponsavel='" + nomeResponsavel + '\'' +
                ", numero='" + numero + '\'' +
                ", observacoes='" + observacoes + '\'' +
                ", telefone1=" + telefone1 +
                ", telefone2=" + telefone2 +
                ", uf='" + uf + '\'' +
                '}';
    }
}
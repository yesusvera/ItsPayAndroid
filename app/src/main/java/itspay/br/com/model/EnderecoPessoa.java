package itspay.br.com.model;

/**
 * Created by yesus on 08/02/17.
 */

public class EnderecoPessoa {
    private String bairro;
    private String cep;
    private String cidade;
    private String complemento;
    //    public DataHoraStatus dataHoraStatus;
    private String dataHoraStatus;
    private String descStatus;
    private long idEndereco;
    private long idPessoa;
    private long idTipoEndereco;
    private long idUsuarioInclusao;
    private long idUsuarioManutencao;
    private String logradouro;
    private String numero;
    private long status;
    private TipoEndereco tipoEndereco;
    private String uf;

    public EnderecoPessoa(String bairro, String cep, String cidade, String complemento, String dataHoraStatus, String descStatus, long idEndereco, long idPessoa, long idTipoEndereco, long idUsuarioInclusao, long idUsuarioManutencao, String logradouro, String numero, long status, TipoEndereco tipoEndereco, String uf) {
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.complemento = complemento;
        this.dataHoraStatus = dataHoraStatus;
        this.descStatus = descStatus;
        this.idEndereco = idEndereco;
        this.idPessoa = idPessoa;
        this.idTipoEndereco = idTipoEndereco;
        this.idUsuarioInclusao = idUsuarioInclusao;
        this.idUsuarioManutencao = idUsuarioManutencao;
        this.logradouro = logradouro;
        this.numero = numero;
        this.status = status;
        this.tipoEndereco = tipoEndereco;
        this.uf = uf;
    }

    public EnderecoPessoa() {
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

    public long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public long getIdTipoEndereco() {
        return idTipoEndereco;
    }

    public void setIdTipoEndereco(long idTipoEndereco) {
        this.idTipoEndereco = idTipoEndereco;
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

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public TipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "EnderecoPessoa{" +
                "bairro='" + bairro + '\'' +
                ", cep='" + cep + '\'' +
                ", cidade='" + cidade + '\'' +
                ", complemento='" + complemento + '\'' +
                ", dataHoraStatus='" + dataHoraStatus + '\'' +
                ", descStatus='" + descStatus + '\'' +
                ", idEndereco=" + idEndereco +
                ", idPessoa=" + idPessoa +
                ", idTipoEndereco=" + idTipoEndereco +
                ", idUsuarioInclusao=" + idUsuarioInclusao +
                ", idUsuarioManutencao=" + idUsuarioManutencao +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", status=" + status +
                ", tipoEndereco=" + tipoEndereco +
                ", uf='" + uf + '\'' +
                '}';
    }
}
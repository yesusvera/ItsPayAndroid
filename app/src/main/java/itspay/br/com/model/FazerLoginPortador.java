package itspay.br.com.model;

/**
 * Created by yesus on 16/12/16.
 */

public class FazerLoginPortador {
    private String architectureInfo;
    private String cpf;
    private String deviceId;
    private long idInstituicao;
    private long idProcessadora;
    private double latitude;
    private double longitude;
    private String model;
    private long origemAcesso;
    private String plataformVersion;
    private String platformName;
    private String senha;
    private long sistemaOperacional;
    private String versaoConhecida;
    private  String versaoInstalada;

    public FazerLoginPortador(){}

    public FazerLoginPortador(String architectureInfo, String cpf, String deviceId, long idInstituicao, long idProcessadora, double latitude, double longitude, String model, long origemAcesso, String plataformVersion, String platformName, String senha, long sistemaOperacional, String versaoConhecida, String versaoInstalada){
        this.architectureInfo = architectureInfo;
        this.cpf = cpf;
        this.deviceId = deviceId;
        this.idInstituicao = idInstituicao;
        this.idProcessadora = idProcessadora;
        this.latitude = latitude;
        this.longitude = longitude;
        this.model = model;
        this.origemAcesso = origemAcesso;
        this.plataformVersion = plataformVersion;
        this.platformName = platformName;
        this.senha = senha;
        this.sistemaOperacional = sistemaOperacional;
        this.versaoConhecida = versaoConhecida;
        this.versaoInstalada = versaoInstalada;
    }

    public String getArchitectureInfo() {
        return architectureInfo;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public long getIdInstituicao() {
        return idInstituicao;
    }

    public long getIdProcessadora() {
        return idProcessadora;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getModel() {
        return model;
    }

    public long getOrigemAcesso() {
        return origemAcesso;
    }

    public String getPlataformVersion() {
        return plataformVersion;
    }

    public String getPlatformName() {
        return platformName;
    }

    public String getSenha() {
        return senha;
    }

    public long getSistemaOperacional() {
        return sistemaOperacional;
    }

    public String getVersaoConhecida() {
        return versaoConhecida;
    }

    public String getVersaoInstalada() {
        return versaoInstalada;
    }

    public void setArchitectureInfo(String architectureInfo) {
        this.architectureInfo = architectureInfo;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setIdInstituicao(long idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public void setIdProcessadora(long idProcessadora) {
        this.idProcessadora = idProcessadora;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setOrigemAcesso(long origemAcesso) {
        this.origemAcesso = origemAcesso;
    }

    public void setPlataformVersion(String plataformVersion) {
        this.plataformVersion = plataformVersion;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setSistemaOperacional(long sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public void setVersaoConhecida(String versaoConhecida) {
        this.versaoConhecida = versaoConhecida;
    }

    public void setVersaoInstalada(String versaoInstalada) {
        this.versaoInstalada = versaoInstalada;
    }
}
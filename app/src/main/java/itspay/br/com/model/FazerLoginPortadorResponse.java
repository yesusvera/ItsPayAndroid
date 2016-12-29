package itspay.br.com.model;

/**
 * Created by yesus on 16/12/16.
 */

public class FazerLoginPortadorResponse{
    private String dataHoraUltimoAcessso;
    private long idLogin;
    private String requisicaoAtualizacaoMensagem;
    private String requisicaoNotificacaoMensagem;
    private boolean requisitarAtualizacao;
    private boolean requisitarPermissaoNotificacao;
    private String token;
    private String versaoMaisRecente;

    public FazerLoginPortadorResponse(){}

    public FazerLoginPortadorResponse(String dataHoraUltimoAcessso, long idLogin, String requisicaoAtualizacaoMensagem, String requisicaoNotificacaoMensagem, boolean requisitarAtualizacao, boolean requisitarPermissaoNotificacao, String token, String versaoMaisRecente){
        this.dataHoraUltimoAcessso = dataHoraUltimoAcessso;
        this.idLogin = idLogin;
        this.requisicaoAtualizacaoMensagem = requisicaoAtualizacaoMensagem;
        this.requisicaoNotificacaoMensagem = requisicaoNotificacaoMensagem;
        this.requisitarAtualizacao = requisitarAtualizacao;
        this.requisitarPermissaoNotificacao = requisitarPermissaoNotificacao;
        this.token = token;
        this.versaoMaisRecente = versaoMaisRecente;
    }

    public String getDataHoraUltimoAcessso() {
        return dataHoraUltimoAcessso;
    }

    public long getIdLogin() {
        return idLogin;
    }

    public String getRequisicaoAtualizacaoMensagem() {
        return requisicaoAtualizacaoMensagem;
    }

    public String getRequisicaoNotificacaoMensagem() {
        return requisicaoNotificacaoMensagem;
    }

    public boolean isRequisitarAtualizacao() {
        return requisitarAtualizacao;
    }

    public boolean isRequisitarPermissaoNotificacao() {
        return requisitarPermissaoNotificacao;
    }

    public String getToken() {
        return token;
    }

    public String getVersaoMaisRecente() {
        return versaoMaisRecente;
    }

    public void setDataHoraUltimoAcessso(String dataHoraUltimoAcessso) {
        this.dataHoraUltimoAcessso = dataHoraUltimoAcessso;
    }

    public void setIdLogin(long idLogin) {
        this.idLogin = idLogin;
    }

    public void setRequisicaoAtualizacaoMensagem(String requisicaoAtualizacaoMensagem) {
        this.requisicaoAtualizacaoMensagem = requisicaoAtualizacaoMensagem;
    }

    public void setRequisicaoNotificacaoMensagem(String requisicaoNotificacaoMensagem) {
        this.requisicaoNotificacaoMensagem = requisicaoNotificacaoMensagem;
    }

    public void setRequisitarAtualizacao(boolean requisitarAtualizacao) {
        this.requisitarAtualizacao = requisitarAtualizacao;
    }

    public void setRequisitarPermissaoNotificacao(boolean requisitarPermissaoNotificacao) {
        this.requisitarPermissaoNotificacao = requisitarPermissaoNotificacao;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setVersaoMaisRecente(String versaoMaisRecente) {
        this.versaoMaisRecente = versaoMaisRecente;
    }

    @Override
    public String toString() {
        return "FazerLoginPortadorResponse{" +
                "dataHoraUltimoAcessso=" + dataHoraUltimoAcessso +
                ", idLogin=" + idLogin +
                ", requisicaoAtualizacaoMensagem='" + requisicaoAtualizacaoMensagem + '\'' +
                ", requisicaoNotificacaoMensagem='" + requisicaoNotificacaoMensagem + '\'' +
                ", requisitarAtualizacao=" + requisitarAtualizacao +
                ", requisitarPermissaoNotificacao=" + requisitarPermissaoNotificacao +
                ", token='" + token + '\'' +
                ", versaoMaisRecente='" + versaoMaisRecente + '\'' +
                '}';
    }
}
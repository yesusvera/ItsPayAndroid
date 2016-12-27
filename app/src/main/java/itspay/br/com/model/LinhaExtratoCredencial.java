package itspay.br.com.model;

/**
 * Created by yesus on 24/12/16.
 */

public class LinhaExtratoCredencial {
    private String dataTransacao;
    private String dataTransacaoFmt;
    private String dataTransacaoFmtMes;
    private String descLocal;
    private String descSeguimento;
    private float valorTransacao;
    private long idTransacao;
    private long sinal;

    public LinhaExtratoCredencial() {
    }

    private LinhaExtratoCredencial(String dataTransacao, String dataTransacaoFmt, String dataTransacaoFmtMes, String descLocal, String descSeguimento, float valorTransacao, long idTransacao, long sinal){
        this.dataTransacao = dataTransacao;
        this.dataTransacaoFmt = dataTransacaoFmt;
        this.dataTransacaoFmtMes = dataTransacaoFmtMes;
        this.descLocal = descLocal;
        this.descSeguimento = descSeguimento;
        this.valorTransacao = valorTransacao;
        this.idTransacao = idTransacao;
        this.sinal = sinal;
    }

    public String getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(String dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public String getDataTransacaoFmt() {
        return dataTransacaoFmt;
    }

    public void setDataTransacaoFmt(String dataTransacaoFmt) {
        this.dataTransacaoFmt = dataTransacaoFmt;
    }

    public String getDataTransacaoFmtMes() {
        return dataTransacaoFmtMes;
    }

    public void setDataTransacaoFmtMes(String dataTransacaoFmtMes) {
        this.dataTransacaoFmtMes = dataTransacaoFmtMes;
    }

    public String getDescLocal() {
        return descLocal;
    }

    public void setDescLocal(String descLocal) {
        this.descLocal = descLocal;
    }

    public String getDescSeguimento() {
        return descSeguimento;
    }

    public void setDescSeguimento(String descSeguimento) {
        this.descSeguimento = descSeguimento;
    }

    public float getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(float valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    public long getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(long idTransacao) {
        this.idTransacao = idTransacao;
    }

    public long getSinal() {
        return sinal;
    }

    public void setSinal(long sinal) {
        this.sinal = sinal;
    }

    @Override
    public String toString() {
        return "LinhaExtratoCredencial{" +
                "dataTransacao='" + dataTransacao + '\'' +
                ", dataTransacaoFmt='" + dataTransacaoFmt + '\'' +
                ", dataTransacaoFmtMes='" + dataTransacaoFmtMes + '\'' +
                ", descLocal='" + descLocal + '\'' +
                ", descSeguimento='" + descSeguimento + '\'' +
                ", valorTransacao=" + valorTransacao +
                ", idTransacao=" + idTransacao +
                ", sinal=" + sinal +
                '}';
    }
}
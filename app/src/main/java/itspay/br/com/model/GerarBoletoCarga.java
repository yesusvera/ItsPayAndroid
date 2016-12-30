package itspay.br.com.model;

/**
 * Created by yesus on 30/12/16.
 */

public class GerarBoletoCarga {
    private String contaPagamento;
    private String documentoPortador;
    private String emailDestino;
    private long idInstituicao;
    private long idProcessadora;
    private long idProduto;
    private double valor;
    private String vencimento;

    public GerarBoletoCarga(String contaPagamento, String documentoPortador, String emailDestino, long idInstituicao, long idProcessadora, long idProduto, double valor, String vencimento){
        this.contaPagamento = contaPagamento;
        this.documentoPortador = documentoPortador;
        this.emailDestino = emailDestino;
        this.idInstituicao = idInstituicao;
        this.idProcessadora = idProcessadora;
        this.idProduto = idProduto;
        this.valor = valor;
        this.vencimento = vencimento;
    }

    public GerarBoletoCarga() {
    }

    public String getContaPagamento() {
        return contaPagamento;
    }

    public void setContaPagamento(String contaPagamento) {
        this.contaPagamento = contaPagamento;
    }

    public String getDocumentoPortador() {
        return documentoPortador;
    }

    public void setDocumentoPortador(String documentoPortador) {
        this.documentoPortador = documentoPortador;
    }

    public String getEmailDestino() {
        return emailDestino;
    }

    public void setEmailDestino(String emailDestino) {
        this.emailDestino = emailDestino;
    }

    public long getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(long idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public long getIdProcessadora() {
        return idProcessadora;
    }

    public void setIdProcessadora(long idProcessadora) {
        this.idProcessadora = idProcessadora;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }
}
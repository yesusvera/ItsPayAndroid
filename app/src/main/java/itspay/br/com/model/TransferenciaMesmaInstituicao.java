package itspay.br.com.model;

/**
 * Created by yesus on 29/12/16.
 */

public class TransferenciaMesmaInstituicao {
    private String contaOrigem;
    private String credencialDestino;
    private long idCredencialOrigem;
    private long idInstituicaoOrigem;
    private String pinCredencialOrigem;
    private double valorTransferencia;


    public TransferenciaMesmaInstituicao() {
    }

    private TransferenciaMesmaInstituicao(String contaOrigem, String credencialDestino, long idCredencialOrigem, long idInstituicaoOrigem, String pinCredencialOrigem, long valorTransferencia){
        this.contaOrigem = contaOrigem;
        this.credencialDestino = credencialDestino;
        this.idCredencialOrigem = idCredencialOrigem;
        this.idInstituicaoOrigem = idInstituicaoOrigem;
        this.pinCredencialOrigem = pinCredencialOrigem;
        this.valorTransferencia = valorTransferencia;
    }

    public String getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(String contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public String getCredencialDestino() {
        return credencialDestino;
    }

    public void setCredencialDestino(String credencialDestino) {
        this.credencialDestino = credencialDestino;
    }

    public long getIdCredencialOrigem() {
        return idCredencialOrigem;
    }

    public void setIdCredencialOrigem(long idCredencialOrigem) {
        this.idCredencialOrigem = idCredencialOrigem;
    }

    public long getIdInstituicaoOrigem() {
        return idInstituicaoOrigem;
    }

    public void setIdInstituicaoOrigem(long idInstituicaoOrigem) {
        this.idInstituicaoOrigem = idInstituicaoOrigem;
    }

    public String getPinCredencialOrigem() {
        return pinCredencialOrigem;
    }

    public void setPinCredencialOrigem(String pinCredencialOrigem) {
        this.pinCredencialOrigem = pinCredencialOrigem;
    }

    public double getValorTransferencia() {
        return valorTransferencia;
    }

    public void setValorTransferencia(double valorTransferencia) {
        this.valorTransferencia = valorTransferencia;
    }
}
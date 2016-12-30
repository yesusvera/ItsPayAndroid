package itspay.br.com.model;

/**
 * Created by yesus on 30/12/16.
 */

public class TransferenciaContaCorrente {
    private String contaCorrenteDestino;
    private long idAgenciaDestino;
    private long idBancoDestino;
    private long idCredencialOrigem;
    private long idInstituicaoOrigem;
    private String pinCredencialOrigem;
    private double valorTransferencia;

    public TransferenciaContaCorrente(String contaCorrenteDestino, long idAgenciaDestino,
                                      long idBancoDestino, long idCredencialOrigem,
                                      long idInstituicaoOrigem, String pinCredencialOrigem,
                                      double valorTransferencia) {
        this.contaCorrenteDestino = contaCorrenteDestino;
        this.idAgenciaDestino = idAgenciaDestino;
        this.idBancoDestino = idBancoDestino;
        this.idCredencialOrigem = idCredencialOrigem;
        this.idInstituicaoOrigem = idInstituicaoOrigem;
        this.pinCredencialOrigem = pinCredencialOrigem;
        this.valorTransferencia = valorTransferencia;
    }

    public TransferenciaContaCorrente() {
    }

    public String getContaCorrenteDestino() {
        return contaCorrenteDestino;
    }

    public void setContaCorrenteDestino(String contaCorrenteDestino) {
        this.contaCorrenteDestino = contaCorrenteDestino;
    }

    public long getIdAgenciaDestino() {
        return idAgenciaDestino;
    }

    public void setIdAgenciaDestino(long idAgenciaDestino) {
        this.idAgenciaDestino = idAgenciaDestino;
    }

    public long getIdBancoDestino() {
        return idBancoDestino;
    }

    public void setIdBancoDestino(long idBancoDestino) {
        this.idBancoDestino = idBancoDestino;
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
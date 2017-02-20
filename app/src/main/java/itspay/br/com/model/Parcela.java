package itspay.br.com.model;

/**
 * Created by yesus
 */

public class Parcela {
    private double valorParcela;
    private double valor;
    private long quantidadeParcelas;

    public Parcela(double valorParcela, double valor, long quantidadeParcelas){
        this.valorParcela = valorParcela;
        this.valor = valor;
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public Parcela() {
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public long getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(long quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }
}
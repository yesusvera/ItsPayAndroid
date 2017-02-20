package itspay.br.com.model;

/**
 * Created by yesus
 */

public  class ParcelasResponse {

    private Parcela parcelas[];
    private double cet;

    public ParcelasResponse(Parcela[] parcelas, double cet){
        this.parcelas = parcelas;
        this.cet = cet;
    }

    public ParcelasResponse() {
    }

    public Parcela[] getParcelas() {
        return parcelas;
    }

    public void setParcelas(Parcela[] parcelas) {
        this.parcelas = parcelas;
    }

    public double getCet() {
        return cet;
    }

    public void setCet(double cet) {
        this.cet = cet;
    }
}
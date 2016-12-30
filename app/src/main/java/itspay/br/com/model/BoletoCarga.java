package itspay.br.com.model;

/**
 * Created by yesus on 30/12/16.
 */

public class BoletoCarga {
    private String codigoDeBarras;
    private String dataVencimentoFmtMes;
    private String linhaDigitavel;

    public BoletoCarga(String codigoDeBarras, String dataVencimentoFmtMes, String linhaDigitavel){
        this.codigoDeBarras = codigoDeBarras;
        this.dataVencimentoFmtMes = dataVencimentoFmtMes;
        this.linhaDigitavel = linhaDigitavel;
    }

    public BoletoCarga() {
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public String getDataVencimentoFmtMes() {
        return dataVencimentoFmtMes;
    }

    public void setDataVencimentoFmtMes(String dataVencimentoFmtMes) {
        this.dataVencimentoFmtMes = dataVencimentoFmtMes;
    }

    public String getLinhaDigitavel() {
        return linhaDigitavel;
    }

    public void setLinhaDigitavel(String linhaDigitavel) {
        this.linhaDigitavel = linhaDigitavel;
    }
}
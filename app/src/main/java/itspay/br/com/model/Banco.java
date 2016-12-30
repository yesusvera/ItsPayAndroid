package itspay.br.com.model;

/**
 * Created by yesus on 30/12/16.
 */

public class Banco {

    private GeradorDeDigito geradorDeDigito;
    private Image image;
    private String numeroFormatado;
    private String numeroFormatadoComDigito;

    private long idBanco;
    private String descBanco;

    public Banco(GeradorDeDigito geradorDeDigito, Image image, String numeroFormatado, String numeroFormatadoComDigito, long idBanco, String descBanco) {
        this.geradorDeDigito = geradorDeDigito;
        this.image = image;
        this.numeroFormatado = numeroFormatado;
        this.numeroFormatadoComDigito = numeroFormatadoComDigito;
        this.idBanco = idBanco;
        this.descBanco = descBanco;
    }

    public Banco() {
    }

    public GeradorDeDigito getGeradorDeDigito() {
        return geradorDeDigito;
    }

    public void setGeradorDeDigito(GeradorDeDigito geradorDeDigito) {
        this.geradorDeDigito = geradorDeDigito;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getNumeroFormatado() {
        return numeroFormatado;
    }

    public void setNumeroFormatado(String numeroFormatado) {
        this.numeroFormatado = numeroFormatado;
    }

    public String getNumeroFormatadoComDigito() {
        return numeroFormatadoComDigito;
    }

    public void setNumeroFormatadoComDigito(String numeroFormatadoComDigito) {
        this.numeroFormatadoComDigito = numeroFormatadoComDigito;
    }

    public long getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(long idBanco) {
        this.idBanco = idBanco;
    }

    public String getDescBanco() {
        return descBanco;
    }

    public void setDescBanco(String descBanco) {
        this.descBanco = descBanco;
    }
}
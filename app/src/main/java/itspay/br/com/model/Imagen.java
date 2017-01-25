package itspay.br.com.model;

/**
 * Created by yesus on 24/01/17.
 * Tem que ser Imagen com 'N'no final porque está assim no serviço REST
 */

public class Imagen {
    private long idImagem;
    private String idProduto;
    private long idSKU;
    private String nomeImagem;
    private boolean principal;
    private String url;

    public Imagen(long idImagem, String idProduto, long idSKU, String nomeImagem, boolean principal, String url) {
        this.idImagem = idImagem;
        this.idProduto = idProduto;
        this.idSKU = idSKU;
        this.nomeImagem = nomeImagem;
        this.principal = principal;
        this.url = url;
    }

    public Imagen() {
    }

    public long getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(long idImagem) {
        this.idImagem = idImagem;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public long getIdSKU() {
        return idSKU;
    }

    public void setIdSKU(long idSKU) {
        this.idSKU = idSKU;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Imagen{" +
                "idImagem=" + idImagem +
                ", idProduto='" + idProduto + '\'' +
                ", idSKU=" + idSKU +
                ", nomeImagem='" + nomeImagem + '\'' +
                ", principal=" + principal +
                ", url='" + url + '\'' +
                '}';
    }
}
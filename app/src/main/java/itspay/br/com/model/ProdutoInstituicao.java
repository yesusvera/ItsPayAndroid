package itspay.br.com.model;

/**
 * Created by yesus on 01/01/17.
 */

public class ProdutoInstituicao {
    private boolean b2b;
    private String descProdInstituicao;
    private long id;
    private long idInstituicao;
    private long idProcessadora;
    private long idProdInstituicao;

    public ProdutoInstituicao(boolean b2b, String descProdInstituicao, long id,
                              long idInstituicao, long idProcessadora, long idProdInstituicao){
        this.b2b = b2b;
        this.descProdInstituicao = descProdInstituicao;
        this.id = id;
        this.idInstituicao = idInstituicao;
        this.idProcessadora = idProcessadora;
        this.idProdInstituicao = idProdInstituicao;
    }

    public ProdutoInstituicao() {
    }

    public boolean isB2b() {
        return b2b;
    }

    public void setB2b(boolean b2b) {
        this.b2b = b2b;
    }

    public String getDescProdInstituicao() {
        return descProdInstituicao;
    }

    public void setDescProdInstituicao(String descProdInstituicao) {
        this.descProdInstituicao = descProdInstituicao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getIdProdInstituicao() {
        return idProdInstituicao;
    }

    public void setIdProdInstituicao(long idProdInstituicao) {
        this.idProdInstituicao = idProdInstituicao;
    }

    @Override
    public String toString() {
        return "ProdutoInstituicao{" +
                "b2b=" + b2b +
                ", descProdInstituicao='" + descProdInstituicao + '\'' +
                ", id=" + id +
                ", idInstituicao=" + idInstituicao +
                ", idProcessadora=" + idProcessadora +
                ", idProdInstituicao=" + idProdInstituicao +
                '}';
    }
}
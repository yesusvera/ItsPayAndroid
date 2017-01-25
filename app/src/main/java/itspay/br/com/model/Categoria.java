package itspay.br.com.model;

/**
 * Created by yesus on 24/01/17.
 */
public class Categoria {
    private String descricao;
    private long idCategoria;
    private boolean vitrine;

    public Categoria(String descricao, long idCategoria, boolean vitrine) {
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.vitrine = vitrine;
    }

    public Categoria() {

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public boolean isVitrine() {
        return vitrine;
    }

    public void setVitrine(boolean vitrine) {
        this.vitrine = vitrine;
    }
}

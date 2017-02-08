package itspay.br.com.model;

/**
 * Created by yesus on 08/02/17.
 */

public class TipoEndereco {
    private long aplicabilidade;
    private String descTipoEndereco;
    private long idTipoEndereco;

    public TipoEndereco(long aplicabilidade, String descTipoEndereco, long idTipoEndereco) {
        this.aplicabilidade = aplicabilidade;
        this.descTipoEndereco = descTipoEndereco;
        this.idTipoEndereco = idTipoEndereco;
    }

    public TipoEndereco() {
    }

    public long getAplicabilidade() {
        return aplicabilidade;
    }

    public void setAplicabilidade(long aplicabilidade) {
        this.aplicabilidade = aplicabilidade;
    }

    public String getDescTipoEndereco() {
        return descTipoEndereco;
    }

    public void setDescTipoEndereco(String descTipoEndereco) {
        this.descTipoEndereco = descTipoEndereco;
    }

    public long getIdTipoEndereco() {
        return idTipoEndereco;
    }

    public void setIdTipoEndereco(long idTipoEndereco) {
        this.idTipoEndereco = idTipoEndereco;
    }

    @Override
    public String toString() {
        return "TipoEndereco{" +
                "aplicabilidade=" + aplicabilidade +
                ", descTipoEndereco='" + descTipoEndereco + '\'' +
                ", idTipoEndereco=" + idTipoEndereco +
                '}';
    }
}
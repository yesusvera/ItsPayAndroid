package itspay.br.com.model;

/**
 * Created by yesus on 08/02/17.
 */

public class ItemPedidoReduzido {
    private String idProduto;
    private long idReferenciaSKU;
    private long idSKU;
    private long quantidadeItem;

    public ItemPedidoReduzido(String idProduto, long idReferenciaSKU, long idSKU, long quantidadeItem) {
        this.idProduto = idProduto;
        this.idReferenciaSKU = idReferenciaSKU;
        this.idSKU = idSKU;
        this.quantidadeItem = quantidadeItem;
    }

    public ItemPedidoReduzido() {
    }

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public long getIdReferenciaSKU() {
        return idReferenciaSKU;
    }

    public void setIdReferenciaSKU(long idReferenciaSKU) {
        this.idReferenciaSKU = idReferenciaSKU;
    }

    public long getIdSKU() {
        return idSKU;
    }

    public void setIdSKU(long idSKU) {
        this.idSKU = idSKU;
    }

    public long getQuantidadeItem() {
        return quantidadeItem;
    }

    public void setQuantidadeItem(long quantidadeItem) {
        this.quantidadeItem = quantidadeItem;
    }

    @Override
    public String toString() {
        return "ItemPedidoReduzido{" +
                "idProduto='" + idProduto + '\'' +
                ", idReferenciaSKU=" + idReferenciaSKU +
                ", idSKU=" + idSKU +
                ", quantidadeItem=" + quantidadeItem +
                '}';
    }
}

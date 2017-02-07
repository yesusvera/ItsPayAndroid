package itspay.br.com.model;

/**
 * Created by yesus on 07/02/17.
 */

public class ProdutoCarrinho {

    private ProdutoDetalhe produtoDetalhe;
    private Referencia referencia;
    private int quantidade;

    public ProdutoCarrinho(ProdutoDetalhe produtoDetalhe, Referencia referencia, int quantidade) {
        this.produtoDetalhe = produtoDetalhe;
        this.referencia = referencia;
        this.quantidade = quantidade;
    }

    public ProdutoCarrinho() {
    }

    public ProdutoDetalhe getProdutoDetalhe() {
        return produtoDetalhe;
    }

    public void setProdutoDetalhe(ProdutoDetalhe produtoDetalhe) {
        this.produtoDetalhe = produtoDetalhe;
    }

    public Referencia getReferencia() {
        return referencia;
    }

    public void setReferencia(Referencia referencia) {
        this.referencia = referencia;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "ProdutoCarrinho{" +
                "produtoDetalhe=" + produtoDetalhe +
                ", referencia=" + referencia +
                ", quantidade=" + quantidade +
                '}';
    }
}

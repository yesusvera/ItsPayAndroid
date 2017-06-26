package br.com.braga.junior.aplicationlib.model;

/**
 * Created by yesus on 07/02/17.
 */

public class ProdutoCarrinho {

    private ProdutoDetalhe produtoDetalhe;
    private MarketPlaceResponse.ProdutoBean.ReferenciasBean referencia;
    private int quantidade;

    public ProdutoCarrinho(ProdutoDetalhe produtoDetalhe, MarketPlaceResponse.ProdutoBean.ReferenciasBean referencia, int quantidade) {
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

    public MarketPlaceResponse.ProdutoBean.ReferenciasBean getReferencia() {
        return referencia;
    }

    public void setReferencia(MarketPlaceResponse.ProdutoBean.ReferenciasBean referencia) {
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

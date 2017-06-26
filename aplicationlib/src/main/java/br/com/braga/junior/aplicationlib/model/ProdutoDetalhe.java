package br.com.braga.junior.aplicationlib.model;

/**
 * Created by yesus on 01/02/17.
 */

public class ProdutoDetalhe {
    private MarketPlaceResponse parceiroResponse;
    private MarketPlaceResponse.ProdutoBean produto;

    public ProdutoDetalhe(MarketPlaceResponse parceiroResponse, MarketPlaceResponse.ProdutoBean produto) {
        this.parceiroResponse = parceiroResponse;
        this.produto = produto;
    }

    public MarketPlaceResponse getParceiroResponse() {
        return parceiroResponse;
    }

    public void setParceiroResponse(MarketPlaceResponse parceiroResponse) {
        this.parceiroResponse = parceiroResponse;
    }

    public MarketPlaceResponse.ProdutoBean getProduto() {
        return produto;
    }

    public void setProduto(MarketPlaceResponse.ProdutoBean produto) {
        this.produto = produto;
    }
}

package itspay.br.com.model;

/**
 * Created by yesus on 01/02/17.
 */

public class ProdutoDetalhe {
    private ParceiroResponse parceiroResponse;
    private Produto produto;

    public ProdutoDetalhe(ParceiroResponse parceiroResponse, Produto produto) {
        this.parceiroResponse = parceiroResponse;
        this.produto = produto;
    }

    public ParceiroResponse getParceiroResponse() {
        return parceiroResponse;
    }

    public void setParceiroResponse(ParceiroResponse parceiroResponse) {
        this.parceiroResponse = parceiroResponse;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}

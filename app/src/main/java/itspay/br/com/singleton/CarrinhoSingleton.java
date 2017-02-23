package itspay.br.com.singleton;

import java.util.ArrayList;
import java.util.List;

import itspay.br.com.model.Credencial;
import itspay.br.com.model.EnderecoPessoa;
import itspay.br.com.model.FazerPedidoMKTPlace;
import itspay.br.com.model.GetFormasEnvioResponse;
import itspay.br.com.model.Parcela;
import itspay.br.com.model.Produto;
import itspay.br.com.model.ProdutoCarrinho;

/**
 * Created by yesus on 07/02/17.
 */

public class CarrinhoSingleton {

    private static CarrinhoSingleton carrinho = new CarrinhoSingleton();


    private Credencial credencial;
    private FazerPedidoMKTPlace requestMKT = new FazerPedidoMKTPlace();
    private Parcela parcela;
    private EnderecoPessoa enderecoPessoa;
    private GetFormasEnvioResponse formaEnvio;


    private List<ProdutoCarrinho> listaProdutosCarrinho = new ArrayList<>();


    private CarrinhoSingleton(){}

    public Credencial getCredencial() {
        return credencial;
    }

    public void setCredencial(Credencial credencial) {
        this.credencial = credencial;
    }

    public Parcela getParcela() {
        return parcela;
    }

    public void setParcela(Parcela parcela) {
        this.parcela = parcela;
    }

    public EnderecoPessoa getEnderecoPessoa() {
        return enderecoPessoa;
    }

    public void setEnderecoPessoa(EnderecoPessoa enderecoPessoa) {
        this.enderecoPessoa = enderecoPessoa;
    }

    public GetFormasEnvioResponse getFormaEnvio() {
        return formaEnvio;
    }

    public void setFormaEnvio(GetFormasEnvioResponse formaEnvio) {
        this.formaEnvio = formaEnvio;
    }

    public void removerProduto(ProdutoCarrinho produtoCarrinho){
        listaProdutosCarrinho.remove(produtoCarrinho);
    }

    public void adicionarProduto(ProdutoCarrinho produtoCarrinho){
        listaProdutosCarrinho.add(produtoCarrinho);
    }

    public boolean temProduto(){
        return listaProdutosCarrinho.size() > 0;
    }

    public void esvaziarCarrinho(){
        listaProdutosCarrinho.clear();
        limparPedido();
    }

    public void limparPedido(){
        requestMKT = new FazerPedidoMKTPlace();
        credencial = null;
        enderecoPessoa = null;
        formaEnvio = null;
    }

    public List<ProdutoCarrinho> getListaProdutosCarrinho() {
        return listaProdutosCarrinho;
    }

    public static CarrinhoSingleton getInstance(){
        if(carrinho==null){
            carrinho = new CarrinhoSingleton();
        }
        return carrinho;
    }

    public double getValorTotal(){
        double valorTotal = 0;
        for (ProdutoCarrinho produtoCarrinho : getListaProdutosCarrinho()) {
            Produto produto = produtoCarrinho.getProdutoDetalhe().getProduto();
            double subtotal = produtoCarrinho.getQuantidade() * produto.getReferencias()[0].getPrecoPor();
            valorTotal += subtotal;
        }
        return valorTotal;
    }

    public FazerPedidoMKTPlace getRequestPedido() {
        return requestMKT;
    }
}

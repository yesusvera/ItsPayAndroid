package itspay.br.com.singleton;

import java.util.ArrayList;
import java.util.List;

import itspay.br.com.model.FazerPedidoMKTPlace;
import itspay.br.com.model.Produto;
import itspay.br.com.model.ProdutoCarrinho;

/**
 * Created by yesus on 07/02/17.
 */

public class CarrinhoSingleton {

    private static CarrinhoSingleton carrinho = new CarrinhoSingleton();

    private FazerPedidoMKTPlace requestMKT = new FazerPedidoMKTPlace();

    private List<ProdutoCarrinho> listaProdutosCarrinho = new ArrayList<>();


    private CarrinhoSingleton(){}


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
        requestMKT = new FazerPedidoMKTPlace();
    }

    public void limparPedido(){
        requestMKT = new FazerPedidoMKTPlace();
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

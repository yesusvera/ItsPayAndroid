package itspay.br.com.singleton;

import java.util.ArrayList;
import java.util.List;

import itspay.br.com.model.ProdutoCarrinho;

/**
 * Created by yesus on 07/02/17.
 */

public class CarrinhoSingleton {

    private static CarrinhoSingleton carrinho = new CarrinhoSingleton();

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

    public void limparLista(){
        listaProdutosCarrinho.clear();
    }

    public static CarrinhoSingleton getInstance(){
        if(carrinho==null){
            carrinho = new CarrinhoSingleton();
        }
        return carrinho;
    }


}

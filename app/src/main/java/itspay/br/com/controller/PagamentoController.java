package itspay.br.com.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;

import java.util.ArrayList;

import itspay.br.com.activity.PagamentoActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.model.FazerPedidoMKTPlace;
import itspay.br.com.model.ItemPedidoReduzido;
import itspay.br.com.model.ProdutoCarrinho;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.singleton.CarrinhoSingleton;
import itspay.br.com.util.EncriptSHA512;
import itspay.br.com.util.ItsPayConstants;
import itspay.br.com.util.UtilsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yesus on 22/02/17.
 */

public class PagamentoController extends BaseActivityController<PagamentoActivity> {

    public PagamentoController(PagamentoActivity activity) {
        super(activity);
    }

    public void efetuarPedido() {
        activity.setLoading(true);
        CarrinhoSingleton carrinho = CarrinhoSingleton.getInstance();

        FazerPedidoMKTPlace requestPedido = carrinho.getRequestPedido();
        requestPedido.setQuantidadeParcelas(carrinho.getParcela().getQuantidadeParcelas());
        requestPedido.setIdCredencial(carrinho.getCredencial().getIdCredencial());

        if (carrinho.getEnderecoPessoa() != null) {
            requestPedido.setIdEndereco(carrinho.getEnderecoPessoa().getIdEndereco());
        }
        requestPedido.setIdConta(carrinho.getCredencial().getIdConta());
        requestPedido.setIdInstituicao(ItsPayConstants.ID_INSTITUICAO);
        requestPedido.setIdParceiro(carrinho.getListaProdutosCarrinho().get(0).getProdutoDetalhe().getParceiroResponse().getIdParceiro());
        requestPedido.setIdProcessadora(ItsPayConstants.ID_PROCESSADORA);

        //ADICIONANDO ITENS
        ArrayList<ItemPedidoReduzido> itens = new ArrayList<>();

        for (ProdutoCarrinho produtoCarrinho : carrinho.getListaProdutosCarrinho()) {
            //CONVERTENDO ITEM.
            ItemPedidoReduzido item = new ItemPedidoReduzido();
            item.setIdProduto(produtoCarrinho.getProdutoDetalhe().getProduto().getIdProduto());
            item.setIdReferenciaSKU(produtoCarrinho.getReferencia().getIdReferenciaSKU());
            item.setIdSKU(produtoCarrinho.getReferencia().getIdSKU());
            item.setQuantidadeItem(produtoCarrinho.getQuantidade());
            itens.add(item);
        }

        ItemPedidoReduzido[] itensPedidoReduzido = new ItemPedidoReduzido[itens.size()];
        itens.toArray(itensPedidoReduzido);

        requestPedido.setItens(itensPedidoReduzido);
        requestPedido.setTipoEntrega(0);

        if (carrinho.getFormaEnvio() != null) {
            requestPedido.setValorFrete(carrinho.getFormaEnvio().getValor());
            if (carrinho.getFormaEnvio().getTitulo().toLowerCase().contains("normal")) {
                requestPedido.setTipoEntrega(1);
            } else {
                requestPedido.setTipoEntrega(2);
            }
        }

        String senhaCredencial = EncriptSHA512.encript(activity.txtSenha.getText().toString() +
                IdentityItsPay.getInstance().getToken()
        );
        requestPedido.setSenhaCredencial(senhaCredencial);

        Call<Integer> call =
                ConnectPortadorService.getService().efetuarPedido(requestPedido,
                        IdentityItsPay.getInstance().getToken());

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.body() != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle("Sucesso")
                            .setMessage("Compra realizada com sucesso.\n" +
                                    "Número do pedido gerado: " + response.body())
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            CarrinhoSingleton.getInstance().esvaziarCarrinho();
                                            activity.finish();
                                        }
                                    }
                            );
                    builder.create().show();

                } else {
                    UtilsActivity.alertMsg(response.errorBody(), activity);
                }

                activity.setLoading(false);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, activity);
                activity.setLoading(false);
            }
        });
    }
}

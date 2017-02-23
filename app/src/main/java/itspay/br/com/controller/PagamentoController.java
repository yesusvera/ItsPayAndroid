package itspay.br.com.controller;

import itspay.br.com.activity.PagamentoActivity;
import itspay.br.com.model.FazerPedidoMKTPlace;
import itspay.br.com.singleton.CarrinhoSingleton;

/**
 * Created by yesus on 22/02/17.
 */

public class PagamentoController extends BaseActivityController<PagamentoActivity> {

    public PagamentoController(PagamentoActivity activity) {
        super(activity);
    }

    public void efetuarPedido(){
        activity.setLoading(true);

        FazerPedidoMKTPlace requestPedido = CarrinhoSingleton.getInstance().getRequestPedido();

//        ConnectPortadorService.getService().efetuarPedido();
    }
}

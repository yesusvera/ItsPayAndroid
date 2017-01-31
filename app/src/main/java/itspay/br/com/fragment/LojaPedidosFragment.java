package itspay.br.com.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.Arrays;

import itspay.br.com.adapter.FoldingCellPedidosAdapter;
import itspay.br.com.controller.LojaPedidoController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Pedido;
import itspay.br.com.model.PedidoDetalhe;
import itspay.br.com.util.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LojaPedidosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LojaPedidosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LojaPedidosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SwipeRefreshLayout swipeRefreshLayout;

    private LojaPedidoController controller = new LojaPedidoController();

    private View rootView;

    public ArrayList<Integer> pedidosCarregadosIndex = new ArrayList<>();

    private ListView theListView;

    public LojaPedidosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LojaPedidosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LojaPedidosFragment newInstance(String param1, String param2) {
        LojaPedidosFragment fragment = new LojaPedidosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_loja_pedidos, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
//
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                controller.listarPedidos(LojaPedidosFragment.this);
            }
        });

        controller.listarPedidos(LojaPedidosFragment.this);

        return rootView;
    }

    public void configurarPedidos(final Pedido[] listaPedidos) {

        theListView = (ListView) rootView.findViewById(R.id.mainListView);

        final ArrayList<Pedido> pedidos = new ArrayList<>(Arrays.asList(listaPedidos));

        final FoldingCellPedidosAdapter adapter = new FoldingCellPedidosAdapter(getActivity(), pedidos);

        theListView.setAdapter(adapter);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                if (!((FoldingCell) view).isUnfolded()) {
                    //Somente busca no serviço se ele já não foi carregado.
                    boolean jaCarregado = false;
                    for (Integer index : pedidosCarregadosIndex) {
                        if (index.intValue() == pos) {
                            jaCarregado = true;
                            break;
                        }
                    }

                    if (!jaCarregado) {
                        controller.buscarPedidoDetalhe(LojaPedidosFragment.this, pedidos.get(pos), view, adapter, pos);
                    }
                }
                // toggle clicked celula_pedido state
                ((FoldingCell) view).toggle(false);
                // register in adapter that state for selected celula_pedido is toggled
                adapter.registerToggle(pos);
            }
        });
    }

    public void clickPedido(final View view, FoldingCellPedidosAdapter adapter, final int pos, PedidoDetalhe pedidoDetalhe) {

        TextView nomeParceiro = (TextView) view.findViewById(R.id.text_nome_parceiro);
        TextView valorTotal = (TextView) view.findViewById(R.id.text_valor_total);
        TextView endereco1 = (TextView) view.findViewById(R.id.text_endereco_entrega);
        TextView endereco2 = (TextView) view.findViewById(R.id.text_endereco_entrega2);
        TextView quantidadeParcelas = (TextView) view.findViewById(R.id.text_qtde_parcelas);
        TextView valorParcela = (TextView) view.findViewById(R.id.text_valor_parcela);
        TextView valorTotalInferior = (TextView) view.findViewById(R.id.text_inferior_valor_total);
        TextView ultimos4Digitos = (TextView) view.findViewById(R.id.text_ultimos_4_digitos);
        TextView nomeImpresso = (TextView) view.findViewById(R.id.text_nome_impresso);
        TextView button_status_pedido = (TextView) view.findViewById(R.id.btn_status_pedido);

        nomeParceiro.setText(pedidoDetalhe.getNomeParceiro());
        valorTotal.setText("R$ " + Utils.formataMoeda(pedidoDetalhe.getValorTotal()));
        endereco1.setText("Frete: R$ " + Utils.formataMoeda(pedidoDetalhe.getValorFrete()));
        endereco2.setText(pedidoDetalhe.getEnderecoCompleto());
        quantidadeParcelas.setText(pedidoDetalhe.getQuantidadeParcelas() + "x");
        valorParcela.setText("R$ " + Utils.formataMoeda(pedidoDetalhe.getValorParcela()));
        valorTotalInferior.setText("R$ " + Utils.formataMoeda(pedidoDetalhe.getValorTotal()));
        ultimos4Digitos.setText(pedidoDetalhe.getUltimos4Digitos());
        nomeImpresso.setText(pedidoDetalhe.getNomeImpresso());
        button_status_pedido.setText(pedidoDetalhe.getDescStatus());

//        if (pedidoDetalhe.getItensPedido() != null) {
//            MaterialListView materialListViewProdutos = (MaterialListView) view.findViewById(R.id.material_listview_produtos);
//
//            materialListViewProdutos.setItemAnimator(new FlipInBottomXAnimator());
//            materialListViewProdutos.getItemAnimator().setAddDuration(300);
//            materialListViewProdutos.getItemAnimator().setRemoveDuration(300);
//
//            materialListViewProdutos.addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener() {
//                @Override
//                public void onItemClick(@NonNull Card card, int position) {
//                    theListView.getOnItemClickListener().onItemClick(null,view, pos, 0 );
//                }
//
//                @Override
//                public void onItemLongClick(@NonNull Card card, int position) {
//
//                }
//            });
//
//            materialListViewProdutos.getAdapter().clearAll();
//
//            List<Card> cards = new ArrayList<>();
//
//
//            for (ItemPedido produto : pedidoDetalhe.getItensPedido()) {
//
//                final Card card = new Card.Builder(LojaPedidosFragment.this.getContext())
//                        .setTag("Produto Pedido")
//                        .withProvider(new CardProvider())
//                        .setLayout(R.layout.item_produto_pedido)
//                        .setTitle(produto.getNomeProduto())
//                        .setTitleColor(Color.DKGRAY)
//                        .setSubtitle(produto.getQuantidadeItem() + "")
//                        .setSubtitle2("R$" + Utils.formataMoeda(produto.getValorTotalItem()))
//                        .setSubtitleColor(Color.DKGRAY)
//                        .setKeepLayoutXml(true)
//                        .endConfig()
//                        .build();
//
//                Call<ResponseBody> call = ConnectPortadorService.getService().abrirImagemProduto(produto.getIdSKU(),
//                        IdentityItsPay.getInstance().getToken());
//
//                call.enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        if (response.body() != null && response.body().byteStream() != null) {
//                            card.getProvider().setDrawable(new BitmapDrawable(response.body().byteStream()));
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        UtilsActivity.alertIfSocketException(t, LojaPedidosFragment.this.getContext());
//                    }
//                });
//
//                cards.add(card);
//            }
//
//            materialListViewProdutos.getAdapter().addAll(cards);
//        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

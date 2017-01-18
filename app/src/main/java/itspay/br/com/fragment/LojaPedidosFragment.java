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

import com.dexafree.materialList.view.MaterialListView;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.Arrays;

import itspay.br.com.adapter.FoldingCellPedidosAdapter;
import itspay.br.com.controller.LojaPedidoController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Pedido;

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

    public MaterialListView materialListView;
    public SwipeRefreshLayout swipeRefreshLayout;

    private LojaPedidoController controller = new LojaPedidoController();

    private View rootView;

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
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_loja_pedidos, container, false);
//        materialListView = (MaterialListView) rootView.findViewById(R.id.material_listview);
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

    public void configurarPedidos(Pedido[] listaPedidos){

        ListView theListView = (ListView) rootView.findViewById(R.id.mainListView);



        /*****
        // prepare elements to display
        final ArrayList<Item> items = Item.getTestingList();

        // add custom btn handler to first list item
        items.get(0).setRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "CUSTOM HANDLER FOR FIRST BUTTON", Toast.LENGTH_SHORT).show();
            }
        });

        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        final FoldingCellListAdapter adapter = new FoldingCellListAdapter(getContext(), items);


        // add default btn handler for each request btn on each item if custom handler not found
        adapter.setDefaultRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "DEFAULT HANDLER FOR ALL BUTTONS", Toast.LENGTH_SHORT).show();
            }
        });
         ****/

        ArrayList<Pedido> pedidos = new ArrayList<>(Arrays.asList(listaPedidos));

        final FoldingCellPedidosAdapter adapter = new FoldingCellPedidosAdapter(getActivity(), pedidos);

        theListView.setAdapter(adapter);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                // toggle clicked celula_pedido state
                ((FoldingCell) view).toggle(false);
                // register in adapter that state for selected celula_pedido is toggled
                adapter.registerToggle(pos);
            }
        });
    }

//
//    public void configurarPedidos(Pedido[] listaPedidos) {
//        materialListView.setItemAnimator(new FadeInLeftAnimator());
//        materialListView.getItemAnimator().setAddDuration(300);
//        materialListView.getItemAnimator().setRemoveDuration(300);
//
//        materialListView.getAdapter().clearAll();
//
//        List<Card> cards = new ArrayList<>();
//        for (Pedido pedido : listaPedidos) {
//            cards.add(novaLinhaPedido(pedido));
//        }
//        materialListView.getAdapter().addAll(cards);
//
//        swipeRefreshLayout.setRefreshing(false);
//    }
//
//    private Card novaLinhaPedido(final Pedido pedido) {
//        DecimalFormat formatoDois = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
//        formatoDois.setMinimumFractionDigits(2);
//        formatoDois.setParseBigDecimal(true);
//        String valorPedido = formatoDois.format(pedido.getValorTotal());
//
//        return new Card.Builder(rootView.getContext())
//                .setTag("pedido")
//                .withProvider(new CardProvider())
//                .setLayout(R.layout.material_itspay_pedido)
//                .setTitle("Pedido Nº"+ pedido.getIdPedido())
//                .setTitleColor(Color.BLACK)
//                .setSubtitle(pedido.getDataHoraPedidoStr())
//                .setSubtitleColor(Color.GRAY)
//                .setDescription(" R$ " + valorPedido + "")
//                .setDescriptionGravity(Gravity.CENTER_VERTICAL + Gravity.RIGHT)
//                .setDescriptionColor(getActivity().getResources().getColor(R.color.green_bahamas))
//                .endConfig()
//                .build();
//    }

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

package itspay.br.com.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import itspay.br.com.itspay.R;
import itspay.br.com.singleton.CarrinhoSingleton;

public class LojaCarrinhoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View rootView;
    private LinearLayout linearLayoutSemProduto;
    private LinearLayout linearLayoutCarrinho;

    private OnFragmentInteractionListener mListener;

    public LojaCarrinhoFragment() {
        // Required empty public constructor
    }

    public static LojaCarrinhoFragment newInstance(String param1, String param2) {
        LojaCarrinhoFragment fragment = new LojaCarrinhoFragment();
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
    public void onResume() {
        super.onResume();
        listarProdutos();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_loja_carrinho, container, false);

        linearLayoutCarrinho = (LinearLayout) rootView.findViewById(R.id.linear_conteudo_carrinho);
        linearLayoutSemProduto = (LinearLayout) rootView.findViewById(R.id.linear_sem_produtos);

        return rootView;
    }

    public void listarProdutos(){
        if(CarrinhoSingleton.getInstance().temProduto()){
            linearLayoutSemProduto.setVisibility(View.GONE);
            linearLayoutCarrinho.setVisibility(View.VISIBLE);
        }else{
            linearLayoutSemProduto.setVisibility(View.VISIBLE);
            linearLayoutCarrinho.setVisibility(View.GONE);
        }
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

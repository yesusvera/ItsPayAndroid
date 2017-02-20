package itspay.br.com.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.card.OnActionClickListener;
import com.dexafree.materialList.card.action.TextViewAction;
import com.dexafree.materialList.view.MaterialListView;

import java.util.ArrayList;
import java.util.List;

import itspay.br.com.activity.CartoesLojaActivity;
import itspay.br.com.activity.EnderecoActivity;
import itspay.br.com.activity.MarketPlaceActivity;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Produto;
import itspay.br.com.model.ProdutoCarrinho;
import itspay.br.com.singleton.CarrinhoSingleton;
import itspay.br.com.util.Utils;
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;

public class LojaCarrinhoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private View rootView;
    private LinearLayout linearLayoutSemProduto;
    private LinearLayout linearLayoutCarrinho;
    private Button btnLimparLista;
    private Button btnContinuar;
    private TextView textGrupo;
    private TextView textValorTotal;
    public MaterialListView materialListView;

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

        CarrinhoSingleton.getInstance().limparPedido();

        Activity activity = getActivity();
        if (activity instanceof MarketPlaceActivity) {
            MarketPlaceActivity marketPlaceActivity = (MarketPlaceActivity) activity;
            marketPlaceActivity.configuraBadgedCarrinho();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_loja_carrinho, container, false);

        linearLayoutCarrinho = (LinearLayout) rootView.findViewById(R.id.linear_conteudo_carrinho);
        linearLayoutSemProduto = (LinearLayout) rootView.findViewById(R.id.linear_sem_produtos);
        btnLimparLista = (Button) rootView.findViewById(R.id.btn_limpar_lista);
        btnContinuar = (Button) rootView.findViewById(R.id.btn_continuar);
        textGrupo = (TextView) rootView.findViewById(R.id.text_grupo);
        textValorTotal = (TextView) rootView.findViewById(R.id.text_valor_total);

        materialListView = (MaterialListView) rootView.findViewById(R.id.material_listView_produtos);

        btnLimparLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(getActivity().getString(R.string.prompt_limpar_carrinho))
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                CarrinhoSingleton.getInstance().esvaziarCarrinho();
                                onResume();
                            }
                        })
                        .setNegativeButton("Não", null);
                builder.create().show();
            }
        });

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escolherFormasEntrega();
            }
        });

        return rootView;
    }


    public void escolherFormasEntrega() {
        CharSequence[] items = {getString(R.string.label_entrega_endereco_vendedor),
                getString(R.string.label_entrega_meu_endereco)};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Escolha a forma de Entrega").setCancelable(true).setNegativeButton("Cancelar", null)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0: {
                                Intent intent = new Intent(LojaCarrinhoFragment.this.getActivity(), CartoesLojaActivity.class);
                                startActivity(intent);
                                break;
                            }
                            case 1: {
                                Intent intent = new Intent(LojaCarrinhoFragment.this.getActivity(), EnderecoActivity.class);
                                startActivity(intent);
                                break;
                            }
                        }
                    }
                });
        builder.create().show();
    }

    public void listarProdutos() {
        if (CarrinhoSingleton.getInstance().temProduto()) {
            linearLayoutSemProduto.setVisibility(View.GONE);
            linearLayoutCarrinho.setVisibility(View.VISIBLE);
        } else {
            linearLayoutSemProduto.setVisibility(View.VISIBLE);
            linearLayoutCarrinho.setVisibility(View.GONE);
        }

        materialListView.getAdapter().clearAll();
        materialListView.setItemAnimator(new FlipInTopXAnimator());
        materialListView.getItemAnimator().setAddDuration(500);
        materialListView.getItemAnimator().setRemoveDuration(300);

        List<Card> cards = new ArrayList<>();

        double valorTotal = 0;

        for (ProdutoCarrinho produtoCarrinho : CarrinhoSingleton.getInstance().getListaProdutosCarrinho()) {

            textGrupo.setText(produtoCarrinho.getProdutoDetalhe().getParceiroResponse().getNomeParceiro());

            Produto produto = produtoCarrinho.getProdutoDetalhe().getProduto();

            String precoPor = "R$" + Utils.formataMoeda(produto.getReferencias()[0].getPrecoPor());

            double subtotal = produtoCarrinho.getQuantidade() * produto.getReferencias()[0].getPrecoPor();

            valorTotal += subtotal;

            String strSubtotal = "R$ " + Utils.formataMoeda(subtotal);

            final Card card = new Card.Builder(this.getContext())
                    .setTag(produtoCarrinho)
                    .withProvider(new CardProvider())
                    .addAction(R.id.text_excluir, new TextViewAction(getActivity()).setListener(
                            new OnActionClickListener() {
                                @Override
                                public void onActionClicked(View view, final Card card) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    builder.setMessage(getString(R.string.prompt_excluir_produto_carrinho))
                                            .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    CarrinhoSingleton.getInstance().getListaProdutosCarrinho().remove(card.getTag());
                                                    onResume();
                                                }
                                            })
                                            .setNegativeButton("Não", null);
                                    builder.create().show();
                                }
                            }
                    ))
                    .setLayout(R.layout.item_produto_carrinho)
                    .setTitle(produto.getNomeProduto())
                    .setTitleColor(Color.DKGRAY)
                    .setSubtitle("Quantidade: " + produtoCarrinho.getQuantidade())
                    .setSubtitle2(precoPor)
                    .setSubtitle3(strSubtotal)
                    .setSubtitleColor(Color.BLACK)
                    .setDescription("Subtotal:")
                    .setKeepLayoutXml(true)
                    .endConfig()
                    .build();

            cards.add(card);

//            if (produto.getImagens() != null && produto.getImagens().length > 0) {
//                Call<ResponseBody> call = ConnectPortadorService.getService().abrirImagemProduto(produto.getImagens()[0].getIdImagem(),
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
//                        UtilsActivity.alertIfSocketException(t, LojaCarrinhoFragment.this.getContext());
//                    }
//                });
//            }
        }

        textValorTotal.setText("R$ " + Utils.formataMoeda(valorTotal));
        materialListView.getAdapter().addAll(cards);
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}

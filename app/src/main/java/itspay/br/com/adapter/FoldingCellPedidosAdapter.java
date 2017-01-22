package itspay.br.com.adapter;

/**
 * Created by yesus on 18/01/17.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;

import java.util.HashSet;
import java.util.List;

import itspay.br.com.itspay.R;
import itspay.br.com.model.Pedido;
import itspay.br.com.util.Utils;

/**
 * Simple example of ListAdapter for using with Folding Cell
 * Adapter holds indexes of unfolded elements for correct work with default reusable views behavior
 */
public class FoldingCellPedidosAdapter extends ArrayAdapter<Pedido> {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;


    public FoldingCellPedidosAdapter(Context context, List<Pedido> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get item for selected view
        Pedido pedido = getItem(position);
        // if celula_pedido is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;
        ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.celula_pedido, parent, false);
            // binding view parts to view holder
            viewHolder.preco = (TextView) cell.findViewById(R.id.text_preco);
            viewHolder.data = (TextView) cell.findViewById(R.id.text_data);
            viewHolder.pedido = (TextView) cell.findViewById(R.id.text_numero_pedido);
            viewHolder.endereco = (TextView) cell.findViewById(R.id.text_endereco);

            cell.setTag(viewHolder);
        } else {
            // for existing celula_pedido set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }

        // bind data from selected element to view through view holder
        viewHolder.preco.setText("R$ "+ Utils.formataMoeda(pedido.getValorTotal()));
        viewHolder.data.setText(pedido.getDataHoraPedidoStr());
        viewHolder.pedido.setText("Pedido Nº"+ pedido.getIdPedido());
        viewHolder.endereco.setText("Frete: R$" + Utils.formataMoeda(pedido.getValorFrete()));

        return cell;
    }

    // simple methods for register celula_pedido state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return defaultRequestBtnClickListener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView preco;
        TextView data;
        TextView pedido;
        TextView endereco;
    }
}

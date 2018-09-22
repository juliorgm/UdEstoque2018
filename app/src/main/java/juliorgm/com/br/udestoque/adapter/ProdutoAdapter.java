package juliorgm.com.br.udestoque.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import juliorgm.com.br.udestoque.Produto;
import juliorgm.com.br.udestoque.R;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ViewHolder> {

    private final Context mContext;
    private final List<Produto> mListaProdutos;

    public ProdutoAdapter(Context mContext, List<Produto> mListaProdutos) {
        this.mContext = mContext;
        this.mListaProdutos = mListaProdutos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_produto,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Produto produto = mListaProdutos.get(position);

        holder.mItemNomeProduto.setText(produto.getmNome());
        holder.mItemPrecoProduto.setText(produto.getmPreco());
    }

    @Override
    public int getItemCount() {
        return mListaProdutos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mItemNomeProduto;
        private final TextView mItemPrecoProduto;

        private ViewHolder(View itemView) {
            super(itemView);

            mItemNomeProduto = itemView.findViewById(R.id.txtItemNomeProduto );
            mItemPrecoProduto = itemView.findViewById(R.id.txtItemPreco );
        }
    }
}

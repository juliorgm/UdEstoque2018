package juliorgm.com.br.udestoque.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import juliorgm.com.br.udestoque.AddProdutoActivity;
import juliorgm.com.br.udestoque.ContextMenuItem;
import juliorgm.com.br.udestoque.MainActivity;
import juliorgm.com.br.udestoque.Produto;
import juliorgm.com.br.udestoque.R;
import juliorgm.com.br.udestoque.dao.ProdutoDB;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ViewHolder> {

    private final Context mContext;
    private final List<Produto> mListaProdutos;

    private static final int sLarguraPopup = 200;
    private static final int sPosicaoPopup = -160;

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
        holder.mItemMenu.setImageResource(R.mipmap.ic_item_menu_opcoes);
    }

    @Override
    public int getItemCount() {
        return mListaProdutos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener{

        private final TextView mItemNomeProduto;
        private final TextView mItemPrecoProduto;
        private final ImageView mItemMenu;

        private ViewHolder(View itemView) {
            super(itemView);

            mItemNomeProduto = itemView.findViewById(R.id.txtItemNomeProduto );
            mItemPrecoProduto = itemView.findViewById(R.id.txtItemPreco );
            mItemMenu = itemView.findViewById(R.id.imgItemMenu );

            if (mItemMenu!=null){
                mItemMenu.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {

            List<ContextMenuItem> itens = new ArrayList<>();
            itens.add(new ContextMenuItem(R.drawable.pencil, mContext.getString(R.string.menu_editar)));
            itens.add(new ContextMenuItem(R.drawable.detalhe, mContext.getString(R.string.menu_detalhes)));
            itens.add(new ContextMenuItem(R.drawable.delete, mContext.getString(R.string.menu_delete)));

            ContextMenuAdapter adapter = new ContextMenuAdapter(mContext,itens);

            ListPopupWindow popupWindow = new ListPopupWindow(mContext);
            popupWindow.setAdapter(adapter);
            popupWindow.setAnchorView(mItemMenu);
            popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position){
                        case 0:
                            redirecionarParaAddActivity(MainActivity.sEditar,mListaProdutos.get(getAdapterPosition()));
                            break;
                        case 1:
                            redirecionarParaAddActivity(MainActivity.sDetalhar,mListaProdutos.get(getAdapterPosition()));
                            break;
                        case 2:
                            ProdutoDB produtoDB = new ProdutoDB(mContext);
                            Produto produto = mListaProdutos.get(getAdapterPosition());
                            produtoDB.deletar(produto);
                            remove(produto);
                            break;
                    }
                }
            });
            popupWindow.setHorizontalOffset( sPosicaoPopup );
            popupWindow.setWidth( sLarguraPopup );
            popupWindow.setModal(true);
            popupWindow.show();
        }
    }

    private void redirecionarParaAddActivity(String key,Produto produto){
        Intent intent = new Intent(mContext, AddProdutoActivity.class);
        intent.putExtra(key,produto);
        mContext.startActivity(intent);
    }

    private void remove(Produto item) {
        int position = mListaProdutos.indexOf(item);
        mListaProdutos.remove(position);
        notifyItemRemoved(position);
    }
}

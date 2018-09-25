package juliorgm.com.br.udestoque.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import juliorgm.com.br.udestoque.AddProdutoActivity;
import juliorgm.com.br.udestoque.ContextMenuItem;
import juliorgm.com.br.udestoque.DetalhesActivity;
import juliorgm.com.br.udestoque.Produto;
import juliorgm.com.br.udestoque.R;
import juliorgm.com.br.udestoque.Utils;
import juliorgm.com.br.udestoque.dao.ProdutoDB;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ViewHolder> {

    private final Context mContext;
    private final List<Produto> mListaProdutos;
    private float mDensidade;
    private Produto mProduto;

    public ProdutoAdapter(Context mContext, List<Produto> mListaProdutos) {
        this.mContext = mContext;
        this.mListaProdutos = mListaProdutos;
        mDensidade = mContext.getResources().getDisplayMetrics().density;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_produto,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        mProduto = mListaProdutos.get(position);

        holder.mItemNomeProduto.setText(mProduto.getmNome());
        holder.mItemPrecoProduto.setText(mContext.getString(R.string.tag_preco) + mProduto.getmPreco());
        holder.mItemQuantidaderoduto.setText(mContext.getString(R.string.tag_quantidade) + mProduto.getmQuantidade());
        holder.mItemMenu.setImageResource(R.mipmap.ic_item_menu_opcoes);

        holder.mBtnVender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int decrementaQuatidade = Integer.parseInt(mProduto.getmQuantidade());
                if(decrementaQuatidade>0){
                    decrementaQuatidade--;
                    mProduto.setmQuantidade(String.valueOf(decrementaQuatidade));
                    ProdutoDB produtoDB = new ProdutoDB(mContext);
                    if (produtoDB.alterar(mProduto)==1){
                        notifyItemChanged(position);
                    }else{
                        Toast.makeText(mContext,"Teste",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(mContext,R.string.remove_quantidade_erro,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListaProdutos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener{

        private final TextView mItemNomeProduto;
        private final TextView mItemPrecoProduto;
        private final TextView mItemQuantidaderoduto;
        private final Button mBtnVender;
        private final ImageView mItemMenu;

        private ViewHolder(View itemView) {
            super(itemView);

            mItemNomeProduto = itemView.findViewById(R.id.txtItemNomeProduto );
            mItemPrecoProduto = itemView.findViewById(R.id.txtItemPreco );
            mItemQuantidaderoduto = itemView.findViewById(R.id.txtItemQuantidade );
            mBtnVender = itemView.findViewById(R.id.btnItemVender );
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

            final ContextMenuAdapter adapter = new ContextMenuAdapter(mContext,itens);

            final ListPopupWindow popupWindow = new ListPopupWindow(mContext);
            popupWindow.setAdapter(adapter);
            popupWindow.setAnchorView(mItemMenu);
            popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position){
                        case 0:
                            redirecionarParaAddActivity(AddProdutoActivity.class, Utils.sEditar,mListaProdutos.get(getAdapterPosition()));
                            break;
                        case 1:
                            redirecionarParaAddActivity(DetalhesActivity.class,Utils.sDetalhar,mListaProdutos.get(getAdapterPosition()));
                            break;
                    }
                    popupWindow.dismiss();
                }
            });
            popupWindow.setHorizontalOffset( Utils.sPosicaoPopup );
            popupWindow.setWidth( (int)(Utils.sLarguraPopup * mDensidade * 0.5f) );
            popupWindow.setModal(true);
            popupWindow.show();
        }
    }

    private void redirecionarParaAddActivity(Class destino,String key,Produto produto){
        Intent intent = new Intent(mContext, destino);
        intent.putExtra(key,produto);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
}

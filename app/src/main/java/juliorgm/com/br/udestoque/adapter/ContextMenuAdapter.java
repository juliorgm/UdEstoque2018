package juliorgm.com.br.udestoque.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import juliorgm.com.br.udestoque.ContextMenuItem;
import juliorgm.com.br.udestoque.R;

public class ContextMenuAdapter extends BaseAdapter {

    private List<ContextMenuItem> mListaMenuItem;
    private LayoutInflater mInflater;
    private ViewHolder mHolder;

    public ContextMenuAdapter(Context mContext, List<ContextMenuItem> mListaMenuItem) {
        this.mListaMenuItem = mListaMenuItem;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mListaMenuItem.size();
    }

    @Override
    public Object getItem(int position) {
        return mListaMenuItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = mInflater.inflate(R.layout.context_menu,parent,false);
            convertView.setTag(mHolder);

            mHolder = new ViewHolder();

            mHolder.mIconeImagem = convertView.findViewById(R.id.imageContextMenu);
            mHolder.mTxtTextoMenu = convertView.findViewById(R.id.txtContextMenu);
        }

        ContextMenuItem item = mListaMenuItem.get(position);

        convertView.setTag(convertView.getId(),item.getmNome());
        mHolder.mIconeImagem.setImageResource(item.getmIcone());
        mHolder.mTxtTextoMenu.setText(item.getmNome());
        return convertView;
    }

    public static class ViewHolder{
        private ImageView mIconeImagem;
        private TextView mTxtTextoMenu;
    }
}

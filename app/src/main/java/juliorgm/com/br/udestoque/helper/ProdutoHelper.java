package juliorgm.com.br.udestoque.helper;

import android.content.Context;
import android.widget.EditText;
import juliorgm.com.br.udestoque.AddProdutoActivity;
import juliorgm.com.br.udestoque.Produto;
import juliorgm.com.br.udestoque.R;

public class ProdutoHelper {
    private final EditText mCampoNome;
    private final EditText mCampoPreco;
    private final EditText mCampoQuantidade;
    private final EditText mCampoFornecedor;
    private final EditText mCampoFornecedorTelefone;
    private final Context mContext;

    public ProdutoHelper(AddProdutoActivity activity) {
        this.mCampoNome = activity.findViewById(R.id.editNomeProduto);
        this.mCampoPreco = activity.findViewById(R.id.editPrecoProduto);
        this.mCampoQuantidade = activity.findViewById(R.id.editQuantidadeProduto);
        this.mCampoFornecedor = activity.findViewById(R.id.editFornecedor);
        this.mCampoFornecedorTelefone = activity.findViewById(R.id.editTelefoneFornecedor);

        mContext = activity;
    }

    public Produto pegaProduto(Produto produto) {
        if(produto==null) produto = new Produto();

        produto.setmNome(mCampoNome.getText().toString());
        produto.setmPreco(mCampoPreco.getText().toString());
        produto.setmQuantidade(mCampoQuantidade.getText().toString());
        produto.setmFonecedor(mCampoFornecedor.getText().toString());
        produto.setmFornecedorTelefone(mCampoFornecedorTelefone.getText().toString());

        return produto;
    }

    public boolean validaCampos() {

        if (mCampoNome.getText().toString().isEmpty()){
            mCampoNome.setError(mContext.getText(R.string.valida_campo_nome));
            return false;
        }
        if (mCampoPreco.getText().toString().isEmpty()){
            mCampoPreco.setError(mContext.getText(R.string.valida_campo_preco));
            return false;
        }
        if (mCampoQuantidade.getText().toString().isEmpty()){
            mCampoQuantidade.setError(mContext.getText(R.string.valida_campo_quantidade));
            return false;
        }
        if (mCampoFornecedor.getText().toString().isEmpty()){
            mCampoFornecedor.setError(mContext.getText(R.string.valida_campo_fornecedor));
            return false;
        }
        if (mCampoFornecedorTelefone.getText().toString().isEmpty()){
            mCampoFornecedorTelefone.setError(mContext.getText(R.string.valida_campo_fornecedor_telefone));
            return false;
        }
        return true;
    }

    public void carregarCampos(Produto produto) {
        mCampoNome.setText(produto.getmNome());
        mCampoPreco.setText(produto.getmPreco());
        mCampoQuantidade.setText(produto.getmQuantidade());
        mCampoFornecedor.setText(produto.getmFonecedor());
        mCampoFornecedorTelefone.setText(produto.getmFornecedorTelefone());
    }
}

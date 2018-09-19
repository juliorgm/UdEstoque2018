package juliorgm.com.br.udestoque;

public class Produto {

    private String mIdProduto;
    private String mNome;
    private String mPreço;
    private String mQuantidade;
    private String mFonecedor;
    private String mFornecedorTelefone;

    public Produto(String mNome, String mPreço, String mQuantidade, String mFonecedor, String mFornecedorTelefone) {
        this.mNome = mNome;
        this.mPreço = mPreço;
        this.mQuantidade = mQuantidade;
        this.mFonecedor = mFonecedor;
        this.mFornecedorTelefone = mFornecedorTelefone;
    }

    public String getmNome() {
        return mNome;
    }

    public String getmPreço() {
        return mPreço;
    }

    public String getmQuantidade() {
        return mQuantidade;
    }

    public String getmFonecedor() {
        return mFonecedor;
    }

    public String getmFornecedorTelefone() {
        return mFornecedorTelefone;
    }

    public String getId() {
        return mIdProduto;
    }
}

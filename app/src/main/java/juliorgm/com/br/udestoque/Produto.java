package juliorgm.com.br.udestoque;

import java.io.Serializable;

public class Produto implements Serializable {

    private int mIdProduto;

    public int getmIdProduto() {
        return mIdProduto;
    }

    private String mNome;
    private String mPreco;
    private String mQuantidade;
    private String mFonecedor;
    private String mFornecedorTelefone;

    public Produto(int idProduto, String mNome, String mPreco, String mQuantidade, String mFonecedor, String mFornecedorTelefone) {
        this.mIdProduto = idProduto;
        this.mNome = mNome;
        this.mPreco = mPreco;
        this.mQuantidade = mQuantidade;
        this.mFonecedor = mFonecedor;
        this.mFornecedorTelefone = mFornecedorTelefone;
    }

    public Produto() { }

    public String getmNome() {
        return mNome;
    }

    public String getmPreco() {
        return mPreco;
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

    public void setmNome(String mNome) {
        this.mNome = mNome;
    }

    public void setmPreco(String mPreco) {
        this.mPreco = mPreco;
    }

    public void setmQuantidade(String mQuantidade) {
        this.mQuantidade = mQuantidade;
    }

    public void setmFonecedor(String mFonecedor) {
        this.mFonecedor = mFonecedor;
    }

    public void setmFornecedorTelefone(String mFornecedorTelefone) {
        this.mFornecedorTelefone = mFornecedorTelefone;
    }
}

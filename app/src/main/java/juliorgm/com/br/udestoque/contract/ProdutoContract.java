package juliorgm.com.br.udestoque.contract;

import android.provider.BaseColumns;

public final class ProdutoContract  {

    public static final class ProdutoEntry implements BaseColumns{
        public final static String TABLE_NAME = "PRODUTOS";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUNA_NOME_PRODUTO ="nome";
        public final static String COLUNA_PRECO_PRODUTO ="preco";
        public final static String COLUNA_QUANTIDADE_PRODUTO ="quantidade";
        public final static String COLUNA_NOME_FORNECEDOR ="fornecedor";
        public final static String COLUNA_TELEFONE_FORNECEDOR ="telefone_fornecedor";
    }
}

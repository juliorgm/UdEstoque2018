package juliorgm.com.br.udestoque.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import juliorgm.com.br.udestoque.Produto;
import juliorgm.com.br.udestoque.contract.ProdutoContract.ProdutoEntry;

public class ProdutoDB extends SQLiteOpenHelper {

    private static final String LOG_TAG = ProdutoDB.class.getName();
    private static final String DATABASE_NAME = "dbestoque.db";
    private static final int DATABASE_VERSION = 1;

    public ProdutoDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_PRODUTO = "CREATE TABLE "+ ProdutoEntry.TABLE_NAME +
                "(" + ProdutoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ProdutoEntry.COLUNA_NOME_PRODUTO + " TEXT NOT NULL, " +
                ProdutoEntry.COLUNA_PRECO_PRODUTO + " TEXT NOT NULL, " +
                ProdutoEntry.COLUNA_QUANTIDADE_PRODUTO  + " TEXT NOT NULL, " +
                ProdutoEntry.COLUNA_NOME_FORNECEDOR + " TEXT NOT NULL, " +
                ProdutoEntry.COLUNA_TELEFONE_FORNECEDOR + " TEXT )";

        db.execSQL(CREATE_TABLE_PRODUTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ ProdutoEntry.TABLE_NAME);
    }

    public long insere(Produto produto) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaContentValue(produto);
        long resultado = db.insert(ProdutoEntry.TABLE_NAME,null,dados);
        db.close();

        return resultado;
    }

    private ContentValues pegaContentValue(Produto produto){
        ContentValues dados = new ContentValues();
        dados.put(ProdutoEntry.COLUNA_NOME_PRODUTO,produto.getmNome());
        dados.put(ProdutoEntry.COLUNA_PRECO_PRODUTO,produto.getmPreco());
        dados.put(ProdutoEntry.COLUNA_QUANTIDADE_PRODUTO,produto.getmQuantidade());
        dados.put(ProdutoEntry.COLUNA_NOME_FORNECEDOR,produto.getmFonecedor());
        dados.put(ProdutoEntry.COLUNA_TELEFONE_FORNECEDOR,produto.getmFornecedorTelefone());

        return dados;
    }

    public List<Produto> buscaProdutos() {
        SQLiteDatabase db = getReadableDatabase();//Criando objeto de leitura de banco
        Cursor cursor = db.rawQuery("select * from " + ProdutoEntry.TABLE_NAME,null);
        List<Produto> listaDeProdutos = new ArrayList<>();

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(ProdutoEntry._ID));
            String nome = cursor.getString(cursor.getColumnIndex(ProdutoEntry.COLUNA_NOME_PRODUTO));
            String preco = cursor.getString(cursor.getColumnIndex(ProdutoEntry.COLUNA_PRECO_PRODUTO));
            String qtde = cursor.getString(cursor.getColumnIndex(ProdutoEntry.COLUNA_QUANTIDADE_PRODUTO));
            String fornecedor = cursor.getString(cursor.getColumnIndex(ProdutoEntry.COLUNA_NOME_FORNECEDOR));
            String telefone_forncedor = cursor.getString(cursor.getColumnIndex(ProdutoEntry.COLUNA_TELEFONE_FORNECEDOR));

            listaDeProdutos.add(new Produto(id,nome,preco,qtde,fornecedor,telefone_forncedor));
        }
        cursor.close();
        return listaDeProdutos;
    }

    public int numeroDeRegistros(){
        SQLiteDatabase db = getReadableDatabase();
        try{
            Cursor cursor = db.rawQuery("SELECT * FROM " + ProdutoEntry.TABLE_NAME,null);
            int registros = cursor.getCount();
            cursor.close();
            db.close();
            return  registros;
        }catch (Exception e){
            Log.e("numeroDeRegistro",e.getMessage());
        }
        db.close();
        return 0;
    }

    public int alterar(Produto produto){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaContentValue(produto);
        String[] parametro = {String.valueOf(produto.getmIdProduto())};
        int resultado = db.update(ProdutoEntry.TABLE_NAME,dados,ProdutoEntry._ID + "=?",parametro);
        db.close();
        Log.v(LOG_TAG,"Registros alterados "+ resultado);
        return resultado;
    }

    public Produto pegaItemProduto(int id){
        SQLiteDatabase db = getReadableDatabase();
        Produto produto = null;
        try{
            String[] parametro = {String.valueOf(id)};
            Cursor cursor = db.rawQuery("SELECT * FROM " +ProdutoEntry.TABLE_NAME +" where "+ProdutoEntry._ID+" = ?",parametro);

            if (cursor.moveToNext()) {
                int _id = cursor.getInt(cursor.getColumnIndex(ProdutoEntry._ID));
                String nome = cursor.getString(cursor.getColumnIndex(ProdutoEntry.COLUNA_NOME_PRODUTO));
                String preco = cursor.getString(cursor.getColumnIndex(ProdutoEntry.COLUNA_PRECO_PRODUTO));
                String qtde = cursor.getString(cursor.getColumnIndex(ProdutoEntry.COLUNA_QUANTIDADE_PRODUTO));
                String fornecedor = cursor.getString(cursor.getColumnIndex(ProdutoEntry.COLUNA_NOME_FORNECEDOR));
                String telefone_forncedor = cursor.getString(cursor.getColumnIndex(ProdutoEntry.COLUNA_TELEFONE_FORNECEDOR));

                produto = new Produto(_id,nome,preco,qtde,fornecedor,telefone_forncedor);
            }
            cursor.close();
            db.close();

        }catch (Exception e){
            Log.e("numeroDeRegistro",e.getMessage());
        }
        db.close();
        return produto;
    }

    public int deletar(Produto produto) {
        SQLiteDatabase db = getWritableDatabase();
        String[] parametro = {String.valueOf(produto.getmIdProduto())};
        return db.delete(ProdutoEntry.TABLE_NAME,ProdutoEntry._ID + "=?",parametro);
    }
}

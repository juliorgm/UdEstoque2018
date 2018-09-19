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

public class ProdutoDB extends SQLiteOpenHelper {
    public ProdutoDB(Context context, String name) {
        super(context, "PRODUTOS", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE PRODUTOS(idProdutos INTEGER PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "preco TEXT," +
                "quantidade TEXT," +
                "fornecedor TEXT," +
                "telefone_forncedor TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE PRODUTOS");
    }

    public void insere(Produto produto) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaContentValue(produto);
        db.insert("ALUNO",null,dados);
    }

    public ContentValues pegaContentValue(Produto produto){
        ContentValues dados = new ContentValues();
        dados.put("nome",produto.getmNome());
        dados.put("preço",produto.getmPreço());
        dados.put("quantidade",produto.getmQuantidade());
        dados.put("fornecedor",produto.getmFonecedor());
        dados.put("telefone_forncedor",produto.getmFornecedorTelefone());

        return dados;
    }

    public void altera(Produto produto){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaContentValue(produto);
        String[] parametro = {String.valueOf(produto.getId())};
        int resultado = db.update("ALUNO",dados,"idProduto=?",parametro);

        Log.v("ALTERA_ALUNO","Registros alterados "+ resultado);

    }


    public List<Produto> buscaProdutos() {
        SQLiteDatabase db = getReadableDatabase();//Criando objeto de leitura de banco
        Cursor cursor = db.rawQuery("select * from ALUNO",null);
        List<Produto> listaDeProdutos = new ArrayList<>();

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("idProduto"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String preco = cursor.getString(cursor.getColumnIndex("preco"));
            String qtde = cursor.getString(cursor.getColumnIndex("quantidade"));
            String fornecedor = cursor.getString(cursor.getColumnIndex("fornecedor"));
            String telefone_forncedor = cursor.getString(cursor.getColumnIndex("telefone_forncedor"));

            listaDeProdutos.add(new Produto(nome,preco,qtde,fornecedor,telefone_forncedor));
        }

        return listaDeProdutos;
    }

    public void deleta(Produto produto) {
        SQLiteDatabase db = getWritableDatabase();
        String[] parametro = {String.valueOf(produto.getId())};
        db.delete("ALUNO","idProduto=?",parametro);
    }
}

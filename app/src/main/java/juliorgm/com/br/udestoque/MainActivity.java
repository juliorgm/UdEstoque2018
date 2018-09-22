package juliorgm.com.br.udestoque;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import juliorgm.com.br.udestoque.adapter.ProdutoAdapter;
import juliorgm.com.br.udestoque.dao.ProdutoDB;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton mBtbNovo;
    private RecyclerView mRecyclerProduto;
    private ProdutoDB mProdutoDB;
    private TextView mTextMensagem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void carregaViews(){
        mBtbNovo = findViewById(R.id.btnNovo);
        mRecyclerProduto = findViewById(R.id.recyclerProduto);
        mTextMensagem = findViewById(R.id.txtMensagem);
    }

    private void cliqueBotoes(){
        mBtbNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddProdutoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void carregaDados(){
        mProdutoDB = new ProdutoDB(this);

        if (mProdutoDB.numeroDeRegistros()==0){
            mTextMensagem.setEnabled(true);
            mRecyclerProduto.setEnabled(false);
            mTextMensagem.setText(R.string.produto_0);
        }else {
            mTextMensagem.setEnabled(false);
            mRecyclerProduto.setEnabled(true);
            carregaRecyclerProdutos();
        }
    }

    private void carregaRecyclerProdutos(){
        mRecyclerProduto.setHasFixedSize(true);
        mRecyclerProduto.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ProdutoAdapter adapter = new ProdutoAdapter(getApplicationContext(), mProdutoDB.buscaProdutos());
        mRecyclerProduto.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaViews();
        cliqueBotoes();
        carregaDados();
    }
}

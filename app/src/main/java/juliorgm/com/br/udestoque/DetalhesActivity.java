package juliorgm.com.br.udestoque;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import juliorgm.com.br.udestoque.dao.ProdutoDB;

public class DetalhesActivity extends AppCompatActivity {

    private Produto mProduto;
    private TextView mNomeProduto;
    private TextView mPrecoProduto;
    private TextView mQuatidadeProduto;
    private TextView mFornecedor;
    private TextView mNumeroFornecedor;
    private Button mBtnAddDetalhes;
    private Button mBtnRemoveDetalhes;
    private Button mBtnExcluir;
    private ProdutoDB mProdutoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Intent intent = getIntent();

        if (intent!=null){
            mProduto = (Produto) intent.getSerializableExtra(Utils.sDetalhar);
            if (mProduto!=null)
            {
                carregarCampos();
                carregarProduto(mProduto);
                clicarBotoes();
            }
        }
    }

    private void clicarBotoes() {
        mBtnAddDetalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantidade = Integer.parseInt(mProduto.getmQuantidade())+1;
                alterarQuantidade(String.valueOf(quantidade));
            }
        });

        mBtnRemoveDetalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantidade = Integer.parseInt(mProduto.getmQuantidade())-1;

                if (quantidade >= 0){
                    alterarQuantidade(String.valueOf(quantidade));
                }else {
                    Toast.makeText(getApplicationContext(),"Não é possivel ter quantidades negativas",Toast.LENGTH_SHORT).show();
                }
            }
        });

        mBtnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarExclusao();
            }
        });

        mNumeroFornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:"+mProduto.getmFornecedorTelefone());
                Intent intent = new Intent(Intent.ACTION_DIAL,uri);

                startActivity(intent);
            }
        });
    }

    private void alterarQuantidade(String quantidade){
        mProduto.setmQuantidade(quantidade);

        mProdutoDB = new ProdutoDB(getApplicationContext());
        mProdutoDB.alterar(mProduto);
        mProduto = mProdutoDB.pegaItemProduto(mProduto.getmIdProduto());
        if (mProduto!=null){
            carregarProduto(mProduto);
        }else{
            Toast.makeText(getApplicationContext(),"Houve um erro ao carregar os detalhes do produto",Toast.LENGTH_SHORT).show();
        }
    }

    private void carregarCampos() {
        mNomeProduto = findViewById(R.id.txtDetalheNome);
        mPrecoProduto = findViewById(R.id.txtDetalhePreco);
        mQuatidadeProduto = findViewById(R.id.txtDetalheQuantidade);
        mFornecedor = findViewById(R.id.txtDetalheFornecedor);
        mNumeroFornecedor = findViewById(R.id.txtDetalheTefoneFornecedor);
        mBtnAddDetalhes = findViewById(R.id.btnAddDetalhes);
        mBtnRemoveDetalhes = findViewById(R.id.btnRemoveDetalhes);
        mBtnExcluir = findViewById(R.id.btnExcluir);
    }

    private void carregarProduto(Produto produto) {
        mNomeProduto.setText(produto.getmNome());
        mPrecoProduto.setText(produto.getmPreco());
        mQuatidadeProduto.setText(produto.getmQuantidade());
        mFornecedor.setText(produto.getmFonecedor());
        mNumeroFornecedor.setText(produto.getmFornecedorTelefone());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_voltar:
                valtarParaHome();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void valtarParaHome(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    private void confirmarExclusao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_mensagem_confirmacao);
        builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                excluirProduto();
            }
        });
        builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void excluirProduto() {
        mProdutoDB = new ProdutoDB(this);
        if(mProdutoDB.deletar(mProduto)==1){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,R.string.excluir_erro,Toast.LENGTH_LONG).show();
        }
    }
}

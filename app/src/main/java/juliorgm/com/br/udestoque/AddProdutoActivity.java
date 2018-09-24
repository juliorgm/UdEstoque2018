package juliorgm.com.br.udestoque;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.azimolabs.maskformatter.MaskFormatter;
import juliorgm.com.br.udestoque.dao.ProdutoDB;
import juliorgm.com.br.udestoque.helper.ProdutoHelper;

public class AddProdutoActivity extends AppCompatActivity {

    private Button mBtnCadastrarProduto;
    private ProdutoDB mProdutoDB;
    private ProdutoHelper mProdutoHelper;
    private Produto mProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produto);

        mBtnCadastrarProduto = findViewById(R.id.btnCadastrarProduto);
        mProdutoDB = new ProdutoDB(this);

        mProdutoHelper = new ProdutoHelper(this);

        cliqueBotoes();
        formataCampoTelefone();

        Intent intent = getIntent();

        if (intent!=null){
            mProduto = (Produto) intent.getSerializableExtra(MainActivity.sEditar);
            if(mProduto != null){
                mProdutoHelper.carregarCampos(mProduto);
            }else {
                mProduto = (Produto) intent.getSerializableExtra(MainActivity.sDetalhar);
                if (mProduto!=null)
                    {
                        mProdutoHelper.carregarCampos(mProduto);
                        mProdutoHelper.desativarEditTexts();
                        findViewById(R.id.btnCadastrarProduto).setVisibility(View.INVISIBLE);
                    }
            }
        }
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

    private void cliqueBotoes(){
        mBtnCadastrarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insereProduto();
            }
        });
    }

    private void insereProduto() {
        if( mProdutoHelper.validaCampos()){
            if (mProduto == null){
                if (mProdutoDB.insere(mProdutoHelper.pegaProduto(null))==-1){
                    Toast.makeText(this,R.string.insere_erro,Toast.LENGTH_LONG).show();
                }else{
                    RetornarParaMain();
                }
            }else {
                if (mProdutoDB.alterar(mProdutoHelper.pegaProduto(mProduto))==1){
                    RetornarParaMain();
                }else{
                    Toast.makeText(this,R.string.altera_erro,Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void RetornarParaMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void formataCampoTelefone(){
        EditText telefone = findViewById(R.id.editTelefoneFornecedor);
        MaskFormatter ibanMaskFormatter = new MaskFormatter("99 99999 9999", telefone);
        telefone.addTextChangedListener(ibanMaskFormatter);
    }
}

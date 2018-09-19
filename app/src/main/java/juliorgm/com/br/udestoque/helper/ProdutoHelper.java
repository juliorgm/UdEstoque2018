package juliorgm.com.br.udestoque.helper;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import juliorgm.com.br.udestoque.Produto;
import juliorgm.com.br.udestoque.R;

public class ProdutoHelper {
    EditText campoNome;
    EditText campoTelefone;
    EditText campoEndereco;
    EditText campoEmail;
    RadioGroup campoSexo;
    RadioButton campoSexoMasculino;
    RadioButton campoSexoFeminino;
    Produto produto;

    public ProdutoHelper(AddProdutoActivity activity, Produto produto) {
        this.campoNome = activity.findViewById(R.id.cadProdutoEditNome);
        this.campoTelefone = activity.findViewById(R.id.cadProdutoEditTelefone);
        this.campoEndereco = activity.findViewById(R.id.cadProdutoEditEndereco);
        this.campoEmail = activity.findViewById(R.id.cadProdutoEditEmail);
        this.campoSexo = activity.findViewById(R.id.cadProdutoRDGSexo);
        this.campoSexoMasculino = activity.findViewById(R.id.cadProdutoRBSexoMasculino);
        this.campoSexoFeminino = activity.findViewById(R.id.cadProdutoRBSexoFeminino);
        this.produto = produto;
    }

    public Produto pegaProduto() {
        if(produto==null) produto = new Produto();

        produto.setNome(campoNome.getText().toString());
        produto.setTelefone(campoTelefone.getText().toString());
        produto.setEmail(campoEmail.getText().toString());
        produto.setEndereco(campoEndereco.getText().toString());
        switch (campoSexo.getCheckedRadioButtonId()){
            case R.id.cadProdutoRBSexoFeminino:
                produto.setSexo("F");
                break;
            case R.id.cadProdutoRBSexoMasculino:
                produto.setSexo("M");
                break;
        }

        return produto;
    }

    public void carregaCampos(Produto produto) {
        campoNome.setText(produto.getNome());
        campoEmail.setText(produto.getEmail());
        campoEndereco.setText(produto.getEndereco());
        campoTelefone.setText(produto.getTelefone());

        if (produto.getSexo().equals("M")) {
            campoSexoMasculino.setChecked(true);
        }
        else if (produto.getSexo().equals("F")) {
            campoSexoFeminino.setChecked(true);
        }
    }
}

package juliorgm.com.br.udestoque;

public class ContextMenuItem {
    private int mIcone;
    private String mNome;

    public ContextMenuItem(int mIcone, String mNome) {
        this.mIcone = mIcone;
        this.mNome = mNome;
    }

    public int getmIcone() {
        return mIcone;
    }

    public String getmNome() {
        return mNome;
    }
}

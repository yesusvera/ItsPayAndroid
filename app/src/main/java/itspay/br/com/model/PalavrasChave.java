package itspay.br.com.model;

/**
 * Created by yesus on 24/01/17.
 */


public class PalavrasChave {
    private long idPalavraChave;
    private String nome;

    public PalavrasChave(long idPalavraChave, String nome) {
        this.idPalavraChave = idPalavraChave;
        this.nome = nome;
    }

    public PalavrasChave() {
    }

    public long getIdPalavraChave() {
        return idPalavraChave;
    }

    public void setIdPalavraChave(long idPalavraChave) {
        this.idPalavraChave = idPalavraChave;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "PalavrasChave{" +
                "idPalavraChave=" + idPalavraChave +
                ", nome='" + nome + '\'' +
                '}';
    }
}

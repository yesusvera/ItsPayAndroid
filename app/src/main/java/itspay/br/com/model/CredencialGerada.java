package itspay.br.com.model;

/**
 * Created by yesus on 01/01/17.
 */
public class CredencialGerada {
    private CredencialGerador credencial;

    public CredencialGerada(CredencialGerador credencial){
        this.credencial = credencial;
    }

    public CredencialGerada() {
    }

    public CredencialGerador getCredencial() {
        return credencial;
    }


    public void setCredencial(CredencialGerador credencial) {
        this.credencial = credencial;
    }

    @Override
    public String toString() {
        return "CredencialGerada{" +
                "credencial=" + credencial +
                '}';
    }
}
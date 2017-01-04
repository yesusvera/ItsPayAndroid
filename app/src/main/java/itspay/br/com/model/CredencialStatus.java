package itspay.br.com.model;

/**
 * Created by yesus on 03/01/17.
 */

public class CredencialStatus {
    private boolean habilitaEcommerce;
    private boolean habilitaExterior;
    private boolean habilitaNotificacaoTransacao;
    private boolean habilitaSaque;
    private boolean habilitaUsoPessoa;

    public CredencialStatus(boolean habilitaEcommerce, boolean habilitaExterior, boolean habilitaNotificacaoTransacao, boolean habilitaSaque, boolean habilitaUsoPessoa){
        this.habilitaEcommerce = habilitaEcommerce;
        this.habilitaExterior = habilitaExterior;
        this.habilitaNotificacaoTransacao = habilitaNotificacaoTransacao;
        this.habilitaSaque = habilitaSaque;
        this.habilitaUsoPessoa = habilitaUsoPessoa;
    }

    public CredencialStatus() {
    }

    public boolean isHabilitaEcommerce() {
        return habilitaEcommerce;
    }

    public void setHabilitaEcommerce(boolean habilitaEcommerce) {
        this.habilitaEcommerce = habilitaEcommerce;
    }

    public boolean isHabilitaExterior() {
        return habilitaExterior;
    }

    public void setHabilitaExterior(boolean habilitaExterior) {
        this.habilitaExterior = habilitaExterior;
    }

    public boolean isHabilitaNotificacaoTransacao() {
        return habilitaNotificacaoTransacao;
    }

    public void setHabilitaNotificacaoTransacao(boolean habilitaNotificacaoTransacao) {
        this.habilitaNotificacaoTransacao = habilitaNotificacaoTransacao;
    }

    public boolean isHabilitaSaque() {
        return habilitaSaque;
    }

    public void setHabilitaSaque(boolean habilitaSaque) {
        this.habilitaSaque = habilitaSaque;
    }

    public boolean isHabilitaUsoPessoa() {
        return habilitaUsoPessoa;
    }

    public void setHabilitaUsoPessoa(boolean habilitaUsoPessoa) {
        this.habilitaUsoPessoa = habilitaUsoPessoa;
    }

    @Override
    public String toString() {
        return "CredencialStatus{" +
                "habilitaEcommerce=" + habilitaEcommerce +
                ", habilitaExterior=" + habilitaExterior +
                ", habilitaNotificacaoTransacao=" + habilitaNotificacaoTransacao +
                ", habilitaSaque=" + habilitaSaque +
                ", habilitaUsoPessoa=" + habilitaUsoPessoa +
                '}';
    }
}
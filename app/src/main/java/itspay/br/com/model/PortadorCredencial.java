package itspay.br.com.model;

/**
 * Created by yesus on 29/12/16.
 */
public class PortadorCredencial {
    private String documento;
    private String email;
    private String nome;
    private String nomeImpresso;
    private double saldoDisponivel;

    public PortadorCredencial() {
    }

    private PortadorCredencial(String documento, String email, String nome, String nomeImpresso, double saldoDisponivel){
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.nomeImpresso = nomeImpresso;
        this.saldoDisponivel = saldoDisponivel;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeImpresso() {
        return nomeImpresso;
    }

    public void setNomeImpresso(String nomeImpresso) {
        this.nomeImpresso = nomeImpresso;
    }

    public double getSaldoDisponivel() {
        return saldoDisponivel;
    }

    public void setSaldoDisponivel(long saldoDisponivel) {
        this.saldoDisponivel = saldoDisponivel;
    }
}
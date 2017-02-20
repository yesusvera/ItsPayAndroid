package itspay.br.com.model;

/**
 * Created by yesus on 08/02/17.
 */

public class GetFormasEnvioResponse {
    private String descricao;
    private String titulo;
    private double valor;

    public GetFormasEnvioResponse(String descricao, String titulo, double valor) {
        this.descricao = descricao;
        this.titulo = titulo;
        this.valor = valor;
    }

    public GetFormasEnvioResponse() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "GetFormasEnvioResponse{" +
                "descricao='" + descricao + '\'' +
                ", titulo='" + titulo + '\'' +
                ", valor=" + valor +
                '}';
    }
}

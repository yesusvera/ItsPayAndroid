package itspay.br.com.model;

/**
 * Created by yesus on 08/02/17.
 */

public class GetFormasEnvioResponse {
    private String descricao;
    private String titulo;
    private long valor;

    public GetFormasEnvioResponse(String descricao, String titulo, long valor) {
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

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
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

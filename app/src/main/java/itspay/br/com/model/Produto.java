package itspay.br.com.model;

import java.util.Arrays;

/**
 * Created by yesus on 24/01/17.
 */

public class Produto {
    private boolean ativo;
    private Categoria categorias[];
    private String descricao;
    private long destaque;
    private long idInstituicao;
    private long idParceiro;
    private long idProcessadora;
    private String idProduto;
    private long idSKU;
    private Imagen imagens[];
    private String nomeProduto;
    private PalavrasChave palavrasChaves[];
    private Referencia referencias[];
    private String tipoProduto;
    private String url;
    private boolean vitrine;

    public Produto(boolean ativo, Categoria[] categorias, String descricao, long destaque, long idInstituicao, long idParceiro, long idProcessadora, String idProduto, long idSKU, Imagen[] imagens, String nomeProduto, PalavrasChave[] palavrasChaves, Referencia[] referencias, String tipoProduto, String url, boolean vitrine) {
        this.ativo = ativo;
        this.categorias = categorias;
        this.descricao = descricao;
        this.destaque = destaque;
        this.idInstituicao = idInstituicao;
        this.idParceiro = idParceiro;
        this.idProcessadora = idProcessadora;
        this.idProduto = idProduto;
        this.idSKU = idSKU;
        this.imagens = imagens;
        this.nomeProduto = nomeProduto;
        this.palavrasChaves = palavrasChaves;
        this.referencias = referencias;
        this.tipoProduto = tipoProduto;
        this.url = url;
        this.vitrine = vitrine;
    }

    public Produto() {
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Categoria[] getCategorias() {
        return categorias;
    }

    public void setCategorias(Categoria[] categorias) {
        this.categorias = categorias;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getDestaque() {
        return destaque;
    }

    public void setDestaque(long destaque) {
        this.destaque = destaque;
    }

    public long getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(long idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public long getIdParceiro() {
        return idParceiro;
    }

    public void setIdParceiro(long idParceiro) {
        this.idParceiro = idParceiro;
    }

    public long getIdProcessadora() {
        return idProcessadora;
    }

    public void setIdProcessadora(long idProcessadora) {
        this.idProcessadora = idProcessadora;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public long getIdSKU() {
        return idSKU;
    }

    public void setIdSKU(long idSKU) {
        this.idSKU = idSKU;
    }

    public Imagen[] getImagens() {
        return imagens;
    }

    public void setImagens(Imagen[] imagens) {
        this.imagens = imagens;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public PalavrasChave[] getPalavrasChaves() {
        return palavrasChaves;
    }

    public void setPalavrasChaves(PalavrasChave[] palavrasChaves) {
        this.palavrasChaves = palavrasChaves;
    }

    public Referencia[] getReferencias() {
        return referencias;
    }

    public void setReferencias(Referencia[] referencias) {
        this.referencias = referencias;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isVitrine() {
        return vitrine;
    }

    public void setVitrine(boolean vitrine) {
        this.vitrine = vitrine;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "ativo=" + ativo +
                ", categorias=" + Arrays.toString(categorias) +
                ", descricao='" + descricao + '\'' +
                ", destaque=" + destaque +
                ", idInstituicao=" + idInstituicao +
                ", idParceiro=" + idParceiro +
                ", idProcessadora=" + idProcessadora +
                ", idProduto='" + idProduto + '\'' +
                ", idSKU=" + idSKU +
                ", imagens=" + Arrays.toString(imagens) +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", palavrasChaves=" + Arrays.toString(palavrasChaves) +
                ", referencias=" + Arrays.toString(referencias) +
                ", tipoProduto='" + tipoProduto + '\'' +
                ", url='" + url + '\'' +
                ", vitrine=" + vitrine +
                '}';
    }
}
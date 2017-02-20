package itspay.br.com.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by yesus on 24/01/17.
 */

public class ParceiroResponse  implements  Cloneable{
    private String cnpj;
    private String dataHoraStatus;
    private long entrega;
    private long idParceiro;
    private long idUsuarioInclusao;
    private long idUsuarioManutencao;
    private double jurosAoMes;
    private String nomeImagem;
    private String nomeParceiro;
    private ArrayList<Produto> produtos;
    private long quantMaxParcelaComJuros;
    private long quantMaxParcelaSemJuros;
    private long status;
    private Unidade unidades[];

    public ParceiroResponse(String cnpj, String dataHoraStatus, long entrega, long idParceiro, long idUsuarioInclusao, long idUsuarioManutencao, double jurosAoMes, String nomeImagem, String nomeParceiro, ArrayList<Produto>  produtos, long quantMaxParcelaComJuros, long quantMaxParcelaSemJuros, long status, Unidade[] unidades) {
        this.cnpj = cnpj;
        this.dataHoraStatus = dataHoraStatus;
        this.entrega = entrega;
        this.idParceiro = idParceiro;
        this.idUsuarioInclusao = idUsuarioInclusao;
        this.idUsuarioManutencao = idUsuarioManutencao;
        this.jurosAoMes = jurosAoMes;
        this.nomeImagem = nomeImagem;
        this.nomeParceiro = nomeParceiro;
        this.produtos = produtos;
        this.quantMaxParcelaComJuros = quantMaxParcelaComJuros;
        this.quantMaxParcelaSemJuros = quantMaxParcelaSemJuros;
        this.status = status;
        this.unidades = unidades;
    }

    public ParceiroResponse() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDataHoraStatus() {
        return dataHoraStatus;
    }

    public void setDataHoraStatus(String dataHoraStatus) {
        this.dataHoraStatus = dataHoraStatus;
    }

    public long getEntrega() {
        return entrega;
    }

    public void setEntrega(long entrega) {
        this.entrega = entrega;
    }

    public long getIdParceiro() {
        return idParceiro;
    }

    public void setIdParceiro(long idParceiro) {
        this.idParceiro = idParceiro;
    }

    public long getIdUsuarioInclusao() {
        return idUsuarioInclusao;
    }

    public void setIdUsuarioInclusao(long idUsuarioInclusao) {
        this.idUsuarioInclusao = idUsuarioInclusao;
    }

    public long getIdUsuarioManutencao() {
        return idUsuarioManutencao;
    }

    public void setIdUsuarioManutencao(long idUsuarioManutencao) {
        this.idUsuarioManutencao = idUsuarioManutencao;
    }

    public double getJurosAoMes() {
        return jurosAoMes;
    }

    public void setJurosAoMes(double jurosAoMes) {
        this.jurosAoMes = jurosAoMes;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public String getNomeParceiro() {
        return nomeParceiro;
    }

    public void setNomeParceiro(String nomeParceiro) {
        this.nomeParceiro = nomeParceiro;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto>  produtos) {
        this.produtos = produtos;
    }

    public long getQuantMaxParcelaComJuros() {
        return quantMaxParcelaComJuros;
    }

    public void setQuantMaxParcelaComJuros(long quantMaxParcelaComJuros) {
        this.quantMaxParcelaComJuros = quantMaxParcelaComJuros;
    }

    public long     getQuantMaxParcelaSemJuros() {
        return quantMaxParcelaSemJuros;
    }

    public void setQuantMaxParcelaSemJuros(long quantMaxParcelaSemJuros) {
        this.quantMaxParcelaSemJuros = quantMaxParcelaSemJuros;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public Unidade[] getUnidades() {
        return unidades;
    }

    public void setUnidades(Unidade[] unidades) {
        this.unidades = unidades;
    }

    @Override
    public String toString() {
        return "ParceiroResponse{" +
                "cnpj='" + cnpj + '\'' +
                ", dataHoraStatus='" + dataHoraStatus + '\'' +
                ", entrega=" + entrega +
                ", idParceiro=" + idParceiro +
                ", idUsuarioInclusao=" + idUsuarioInclusao +
                ", idUsuarioManutencao=" + idUsuarioManutencao +
                ", jurosAoMes=" + jurosAoMes +
                ", nomeImagem='" + nomeImagem + '\'' +
                ", nomeParceiro='" + nomeParceiro + '\'' +
                ", produtos=" + produtos +
                ", quantMaxParcelaComJuros=" + quantMaxParcelaComJuros +
                ", quantMaxParcelaSemJuros=" + quantMaxParcelaSemJuros +
                ", status=" + status +
                ", unidades=" + Arrays.toString(unidades) +
                '}';
    }

    @Override
    public ParceiroResponse clone(){
        try {
            return (ParceiroResponse)super.clone();
        }catch (CloneNotSupportedException ex){
            ex.printStackTrace();
        }

        return null;
    }
}
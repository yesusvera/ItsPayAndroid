package br.com.braga.junior.aplicationlib.model;

/**
 * Created by juniorbraga on 26/06/17.
 */

import java.util.List;


public class MarketPlaceResponse implements Cloneable {


    /**
     * produto : {"idSKU":1,"idProduto":"10001","nomeProduto":"MP3 Player","descricao":"O MP3 Player mais avançado do mercado","tipoProduto":"Eletrônico","destaque":1,"vitrine":true,"referencias":[{"idReferenciaSKU":1,"referencia":"10002","idSKU":1,"idProduto":"10001","idParceiro":1,"precoDe":82.42,"precoPor":65.92,"freteMedio":10,"disponivel":true,"saldo":100,"caracteristicas":[{"idCaracteristicaReferencia":1,"idReferenciaSKU":1,"nome":"Marca","valor":"Sony"},{"idCaracteristicaReferencia":2,"idReferenciaSKU":1,"nome":"Cor","valor":"Preta"}]},{"idReferenciaSKU":2,"referencia":"10003","idSKU":1,"idProduto":"10001","idParceiro":1,"precoDe":89.97,"precoPor":70.97,"freteMedio":10,"disponivel":true,"saldo":100,"caracteristicas":[{"idCaracteristicaReferencia":3,"idReferenciaSKU":2,"nome":"Marca","valor":"Sony"},{"idCaracteristicaReferencia":4,"idReferenciaSKU":2,"nome":"Cor","valor":"Branca"}]}],"imagens":[{"idImagem":1,"idProduto":"10001","idSKU":1,"principal":true,"nomeImagem":"sku_1_img_1.jpg"},{"idImagem":6,"idProduto":"10001","idSKU":1,"principal":false,"nomeImagem":"sku_1_img_6.jpg"}],"categorias":[{"idCategoria":1,"descricao":"ELETRÔNICOS","vitrine":true}],"palavrasChaves":[{"idPalavraChave":1,"nome":"mp3"},{"idPalavraChave":2,"nome":"som"},{"idPalavraChave":3,"nome":"sony"}]}
     * parceiro : {"idParceiro":1,"nomeParceiro":"GRUPO ABC","nomeImagem":"logo_parceiro_1.png","status":1,"dataHoraStatus":"2016-12-28 00:00:00.0","idUsuarioInclusao":1,"cnpj":"36112778000130","quantMaxParcelaSemJuros":6,"quantMaxParcelaComJuros":12,"jurosAoMes":2}
     */

    private ProdutoBean produto;
    private ParceiroBean parceiro;

    public ProdutoBean getProduto() {
        return produto;
    }

    public void setProduto(ProdutoBean produto) {
        this.produto = produto;
    }

    public ParceiroBean getParceiro() {
        return parceiro;
    }

    public void setParceiro(ParceiroBean parceiro) {
        this.parceiro = parceiro;
    }

    public static class ProdutoBean {
        /**
         * idSKU : 1
         * idProduto : 10001
         * nomeProduto : MP3 Player
         * descricao : O MP3 Player mais avançado do mercado
         * tipoProduto : Eletrônico
         * destaque : 1
         * vitrine : true
         * referencias : [{"idReferenciaSKU":1,"referencia":"10002","idSKU":1,"idProduto":"10001","idParceiro":1,"precoDe":82.42,"precoPor":65.92,"freteMedio":10,"disponivel":true,"saldo":100,"caracteristicas":[{"idCaracteristicaReferencia":1,"idReferenciaSKU":1,"nome":"Marca","valor":"Sony"},{"idCaracteristicaReferencia":2,"idReferenciaSKU":1,"nome":"Cor","valor":"Preta"}]},{"idReferenciaSKU":2,"referencia":"10003","idSKU":1,"idProduto":"10001","idParceiro":1,"precoDe":89.97,"precoPor":70.97,"freteMedio":10,"disponivel":true,"saldo":100,"caracteristicas":[{"idCaracteristicaReferencia":3,"idReferenciaSKU":2,"nome":"Marca","valor":"Sony"},{"idCaracteristicaReferencia":4,"idReferenciaSKU":2,"nome":"Cor","valor":"Branca"}]}]
         * imagens : [{"idImagem":1,"idProduto":"10001","idSKU":1,"principal":true,"nomeImagem":"sku_1_img_1.jpg"},{"idImagem":6,"idProduto":"10001","idSKU":1,"principal":false,"nomeImagem":"sku_1_img_6.jpg"}]
         * categorias : [{"idCategoria":1,"descricao":"ELETRÔNICOS","vitrine":true}]
         * palavrasChaves : [{"idPalavraChave":1,"nome":"mp3"},{"idPalavraChave":2,"nome":"som"},{"idPalavraChave":3,"nome":"sony"}]
         */

        private int idSKU;
        private String idProduto;
        private String nomeProduto;
        private String descricao;
        private String tipoProduto;
        private int destaque;
        private boolean vitrine;
        private List<ReferenciasBean> referencias;
        private List<ImagensBean> imagens;
        private List<CategoriasBean> categorias;
        private List<PalavrasChavesBean> palavrasChaves;

        public int getIdSKU() {
            return idSKU;
        }

        public void setIdSKU(int idSKU) {
            this.idSKU = idSKU;
        }

        public String getIdProduto() {
            return idProduto;
        }

        public void setIdProduto(String idProduto) {
            this.idProduto = idProduto;
        }

        public String getNomeProduto() {
            return nomeProduto;
        }

        public void setNomeProduto(String nomeProduto) {
            this.nomeProduto = nomeProduto;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public String getTipoProduto() {
            return tipoProduto;
        }

        public void setTipoProduto(String tipoProduto) {
            this.tipoProduto = tipoProduto;
        }

        public int getDestaque() {
            return destaque;
        }

        public void setDestaque(int destaque) {
            this.destaque = destaque;
        }

        public boolean isVitrine() {
            return vitrine;
        }

        public void setVitrine(boolean vitrine) {
            this.vitrine = vitrine;
        }

        public List<ReferenciasBean> getReferencias() {
            return referencias;
        }

        public void setReferencias(List<ReferenciasBean> referencias) {
            this.referencias = referencias;
        }

        public List<ImagensBean> getImagens() {
            return imagens;
        }

        public void setImagens(List<ImagensBean> imagens) {
            this.imagens = imagens;
        }

        public List<CategoriasBean> getCategorias() {
            return categorias;
        }

        public void setCategorias(List<CategoriasBean> categorias) {
            this.categorias = categorias;
        }

        public List<PalavrasChavesBean> getPalavrasChaves() {
            return palavrasChaves;
        }

        public void setPalavrasChaves(List<PalavrasChavesBean> palavrasChaves) {
            this.palavrasChaves = palavrasChaves;
        }

        public static class ReferenciasBean {
            /**
             * idReferenciaSKU : 1
             * referencia : 10002
             * idSKU : 1
             * idProduto : 10001
             * idParceiro : 1
             * precoDe : 82.42
             * precoPor : 65.92
             * freteMedio : 10
             * disponivel : true
             * saldo : 100
             * caracteristicas : [{"idCaracteristicaReferencia":1,"idReferenciaSKU":1,"nome":"Marca","valor":"Sony"},{"idCaracteristicaReferencia":2,"idReferenciaSKU":1,"nome":"Cor","valor":"Preta"}]
             */

            private long idReferenciaSKU;
            private String referencia;
            private long idSKU;
            private String idProduto;
            private long idParceiro;
            private double precoDe;
            private double precoPor;
            private double freteMedio;
            private boolean disponivel;
            private double saldo;
            private List<CaracteristicasBean> caracteristicas;

            public long getIdReferenciaSKU() {
                return idReferenciaSKU;
            }

            public void setIdReferenciaSKU(long idReferenciaSKU) {
                this.idReferenciaSKU = idReferenciaSKU;
            }

            public String getReferencia() {
                return referencia;
            }

            public void setReferencia(String referencia) {
                this.referencia = referencia;
            }

            public long getIdSKU() {
                return idSKU;
            }

            public void setIdSKU(long idSKU) {
                this.idSKU = idSKU;
            }

            public String getIdProduto() {
                return idProduto;
            }

            public void setIdProduto(String idProduto) {
                this.idProduto = idProduto;
            }

            public long getIdParceiro() {
                return idParceiro;
            }

            public void setIdParceiro(long idParceiro) {
                this.idParceiro = idParceiro;
            }

            public double getPrecoDe() {
                return precoDe;
            }

            public void setPrecoDe(double precoDe) {
                this.precoDe = precoDe;
            }

            public double getPrecoPor() {
                return precoPor;
            }

            public void setPrecoPor(double precoPor) {
                this.precoPor = precoPor;
            }

            public double getFreteMedio() {
                return freteMedio;
            }

            public void setFreteMedio(double freteMedio) {
                this.freteMedio = freteMedio;
            }

            public boolean isDisponivel() {
                return disponivel;
            }

            public void setDisponivel(boolean disponivel) {
                this.disponivel = disponivel;
            }

            public double getSaldo() {
                return saldo;
            }

            public void setSaldo(double saldo) {
                this.saldo = saldo;
            }

            public List<CaracteristicasBean> getCaracteristicas() {
                return caracteristicas;
            }

            public void setCaracteristicas(List<CaracteristicasBean> caracteristicas) {
                this.caracteristicas = caracteristicas;
            }

            public static class CaracteristicasBean {
                /**
                 * idCaracteristicaReferencia : 1
                 * idReferenciaSKU : 1
                 * nome : Marca
                 * valor : Sony
                 */

                private int idCaracteristicaReferencia;
                private int idReferenciaSKU;
                private String nome;
                private String valor;

                public int getIdCaracteristicaReferencia() {
                    return idCaracteristicaReferencia;
                }

                public void setIdCaracteristicaReferencia(int idCaracteristicaReferencia) {
                    this.idCaracteristicaReferencia = idCaracteristicaReferencia;
                }

                public int getIdReferenciaSKU() {
                    return idReferenciaSKU;
                }

                public void setIdReferenciaSKU(int idReferenciaSKU) {
                    this.idReferenciaSKU = idReferenciaSKU;
                }

                public String getNome() {
                    return nome;
                }

                public void setNome(String nome) {
                    this.nome = nome;
                }

                public String getValor() {
                    return valor;
                }

                public void setValor(String valor) {
                    this.valor = valor;
                }
            }
        }

        public static class ImagensBean {
            /**
             * idImagem : 1
             * idProduto : 10001
             * idSKU : 1
             * principal : true
             * nomeImagem : sku_1_img_1.jpg
             */

            private long idImagem;
            private String idProduto;
            private long idSKU;
            private boolean principal;
            private String  nomeImagem;

            public long getIdImagem() {
                return idImagem;
            }

            public void setIdImagem(long idImagem) {
                this.idImagem = idImagem;
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

            public boolean isPrincipal() {
                return principal;
            }

            public void setPrincipal(boolean principal) {
                this.principal = principal;
            }

            public String getNomeImagem() {
                return nomeImagem;
            }

            public void setNomeImagem(String nomeImagem) {
                this.nomeImagem = nomeImagem;
            }
        }

        public static class CategoriasBean {
            /**
             * idCategoria : 1
             * descricao : ELETRÔNICOS
             * vitrine : true
             */

            private int idCategoria;
            private String descricao;
            private boolean vitrine;

            public int getIdCategoria() {
                return idCategoria;
            }

            public void setIdCategoria(int idCategoria) {
                this.idCategoria = idCategoria;
            }

            public String getDescricao() {
                return descricao;
            }

            public void setDescricao(String descricao) {
                this.descricao = descricao;
            }

            public boolean isVitrine() {
                return vitrine;
            }

            public void setVitrine(boolean vitrine) {
                this.vitrine = vitrine;
            }
        }

        public static class PalavrasChavesBean {
            /**
             * idPalavraChave : 1
             * nome : mp3
             */

            private int idPalavraChave;
            private String nome;

            public int getIdPalavraChave() {
                return idPalavraChave;
            }

            public void setIdPalavraChave(int idPalavraChave) {
                this.idPalavraChave = idPalavraChave;
            }

            public String getNome() {
                return nome;
            }

            public void setNome(String nome) {
                this.nome = nome;
            }
        }
    }

    public static class ParceiroBean {
        /**
         * idParceiro : 1
         * nomeParceiro : GRUPO ABC
         * nomeImagem : logo_parceiro_1.png
         * status : 1
         * dataHoraStatus : 2016-12-28 00:00:00.0
         * idUsuarioInclusao : 1
         * cnpj : 36112778000130
         * quantMaxParcelaSemJuros : 6
         * quantMaxParcelaComJuros : 12
         * jurosAoMes : 2
         */

        private long idParceiro;
        private String nomeParceiro;
        private String nomeImagem;
        private int status;
        private String dataHoraStatus;
        private int idUsuarioInclusao;
        private String cnpj;
        private int quantMaxParcelaSemJuros;
        private int quantMaxParcelaComJuros;
        private double jurosAoMes;

        public long getIdParceiro() {
            return idParceiro;
        }

        public void setIdParceiro(long idParceiro) {
            this.idParceiro = idParceiro;
        }

        public String getNomeParceiro() {
            return nomeParceiro;
        }

        public void setNomeParceiro(String nomeParceiro) {
            this.nomeParceiro = nomeParceiro;
        }

        public String getNomeImagem() {
            return nomeImagem;
        }

        public void setNomeImagem(String nomeImagem) {
            this.nomeImagem = nomeImagem;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getDataHoraStatus() {
            return dataHoraStatus;
        }

        public void setDataHoraStatus(String dataHoraStatus) {
            this.dataHoraStatus = dataHoraStatus;
        }

        public int getIdUsuarioInclusao() {
            return idUsuarioInclusao;
        }

        public void setIdUsuarioInclusao(int idUsuarioInclusao) {
            this.idUsuarioInclusao = idUsuarioInclusao;
        }

        public String getCnpj() {
            return cnpj;
        }

        public void setCnpj(String cnpj) {
            this.cnpj = cnpj;
        }

        public int getQuantMaxParcelaSemJuros() {
            return quantMaxParcelaSemJuros;
        }

        public void setQuantMaxParcelaSemJuros(int quantMaxParcelaSemJuros) {
            this.quantMaxParcelaSemJuros = quantMaxParcelaSemJuros;
        }

        public int getQuantMaxParcelaComJuros() {
            return quantMaxParcelaComJuros;
        }

        public void setQuantMaxParcelaComJuros(int quantMaxParcelaComJuros) {
            this.quantMaxParcelaComJuros = quantMaxParcelaComJuros;
        }

        public double getJurosAoMes() {
            return jurosAoMes;
        }

        public void setJurosAoMes(double jurosAoMes) {
            this.jurosAoMes = jurosAoMes;
        }
    }

    @Override
    public MarketPlaceResponse clone(){
        try {
            return (MarketPlaceResponse)super.clone();
        }catch (CloneNotSupportedException ex){
            ex.printStackTrace();
        }

        return null;
    }
}

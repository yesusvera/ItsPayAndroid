package com.example.aplicationlib.model;

/**
 * Created by juniorbraga on 11/05/17.
 */

public class EnderecoResponse {


    /**
     * codLogradouro : 46949
     * nome : SHIN QI
     * nomeAbreviado : SHIN QI 3 CJ 7
     * bairroInicio : {"codBairro":1240,"nome":"SETOR DE HABITACOES INDIVIDUAIS NORTE","localidade":{"codLocalidade":1778,"nome":"BRASILIA","cep":"0       ","codIbge":5300108,"codIbgeReduzido":108,"uf":{"codUf":7,"sigla":"DF","nome":"DISTRITO FEDERAL"}}}
     * cep : 71505270
     */

    private int codLogradouro;
    private String nome;
    private String nomeAbreviado;
    private BairroInicioBean bairroInicio;
    private String cep;

    public int getCodLogradouro() {
        return codLogradouro;
    }

    public void setCodLogradouro(int codLogradouro) {
        this.codLogradouro = codLogradouro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeAbreviado() {
        return nomeAbreviado;
    }

    public void setNomeAbreviado(String nomeAbreviado) {
        this.nomeAbreviado = nomeAbreviado;
    }

    public BairroInicioBean getBairroInicio() {
        return bairroInicio;
    }

    public void setBairroInicio(BairroInicioBean bairroInicio) {
        this.bairroInicio = bairroInicio;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public static class BairroInicioBean {
        /**
         * codBairro : 1240
         * nome : SETOR DE HABITACOES INDIVIDUAIS NORTE
         * localidade : {"codLocalidade":1778,"nome":"BRASILIA","cep":"0       ","codIbge":5300108,"codIbgeReduzido":108,"uf":{"codUf":7,"sigla":"DF","nome":"DISTRITO FEDERAL"}}
         */

        private int codBairro;
        private String nome;
        private LocalidadeBean localidade;

        public int getCodBairro() {
            return codBairro;
        }

        public void setCodBairro(int codBairro) {
            this.codBairro = codBairro;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public LocalidadeBean getLocalidade() {
            return localidade;
        }

        public void setLocalidade(LocalidadeBean localidade) {
            this.localidade = localidade;
        }

        public static class LocalidadeBean {
            /**
             * codLocalidade : 1778
             * nome : BRASILIA
             * cep : 0
             * codIbge : 5300108
             * codIbgeReduzido : 108
             * uf : {"codUf":7,"sigla":"DF","nome":"DISTRITO FEDERAL"}
             */

            private int codLocalidade;
            private String nome;
            private String cep;
            private int codIbge;
            private int codIbgeReduzido;
            private UfBean uf;

            public int getCodLocalidade() {
                return codLocalidade;
            }

            public void setCodLocalidade(int codLocalidade) {
                this.codLocalidade = codLocalidade;
            }

            public String getNome() {
                return nome;
            }

            public void setNome(String nome) {
                this.nome = nome;
            }

            public String getCep() {
                return cep;
            }

            public void setCep(String cep) {
                this.cep = cep;
            }

            public int getCodIbge() {
                return codIbge;
            }

            public void setCodIbge(int codIbge) {
                this.codIbge = codIbge;
            }

            public int getCodIbgeReduzido() {
                return codIbgeReduzido;
            }

            public void setCodIbgeReduzido(int codIbgeReduzido) {
                this.codIbgeReduzido = codIbgeReduzido;
            }

            public UfBean getUf() {
                return uf;
            }

            public void setUf(UfBean uf) {
                this.uf = uf;
            }

            public static class UfBean {
                /**
                 * codUf : 7
                 * sigla : DF
                 * nome : DISTRITO FEDERAL
                 */

                private int codUf;
                private String sigla;
                private String nome;


                public int getCodUf() {
                    return codUf;
                }

                public void setCodUf(int codUf) {
                    this.codUf = codUf;
                }

                public String getSigla() {
                    return sigla;
                }

                public void setSigla(String sigla) {
                    this.sigla = sigla;
                }

                public String getNome() {
                    return nome;
                }

                public void setNome(String nome) {
                    this.nome = nome;
                }
            }
        }
    }
}
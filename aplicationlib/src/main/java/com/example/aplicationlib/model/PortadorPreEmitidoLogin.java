package com.example.aplicationlib.model;

import java.util.List;

/**
 * Created by juniorbraga on 12/05/17.
 */

public class PortadorPreEmitidoLogin {

    /**
     * tipoPessoa : 1
     * idInstituicao : 300
     * idProcessadora : 10
     * documento : 35156447333
     * idUsuarioInclusao : null
     * idRegional : 3001
     * idFilial : 3002
     * idPontoDeRelacionamento : 320
     * nomeCompleto : DENNIS
     * naturalidade : Brasília
     * rg : 2664114
     * rgOrgaoEmissor : null
     * rgDataEmissao : 1993-10-14T03:00:00.000Z
     * estrangeiro : false
     * passaporte : null
     * dataNascimento : 1993-10-14T03:00:00.000Z
     * nomePai : DENNIS OLIVEIRA CREMASCO
     * nomeMae : CREMASCO
     * email : dennnisdocwe@gmail.com
     * emailProfissional : dennnisdocwe@gmail.com
     * dddTelefoneResidencial : 61
     * telefoneResidencial : 981551079
     * dddTelefoneComercial : 61
     * telefoneComercial : 981551079
     * dddTelefoneCelular : 61
     * telefoneCelular : 981551079
     * razaoSocial : null
     * credencial : 0B0B0D6DDBF61CBF6E1204AB61E1D7139052946CB4C53D08FB2BA3CE2E147915A2E6CB775FDAD08E3503EDD856915A0A799FEDED543F72537305AB574652147D
     * enderecoPessoaRequest : {"enderecos":[{"idUsuarioInclusao":999999,"cep":"71505280","logradouro":"SHIN QI 3 CJ 7","numero":"8","complemento":"","bairro":"SETOR DE HABITACOES INDIVIDUAIS NORTE","cidade":"BRASILIA","uf":"DF"}]}
     */

    private int tipoPessoa;
    private long idInstituicao;
    private long idProcessadora;
    private String documento;
    private long idUsuarioInclusao;
    private long idRegional;
    private long idFilial;
    private long idPontoDeRelacionamento;
    private String nomeCompleto;
    private String naturalidade;
    private String rg;
    private String rgOrgaoEmissor;
    private String rgDataEmissao;
    private boolean estrangeiro;
    private String passaporte;
    private String dataNascimento;
    private String nomePai;
    private String nomeMae;
    private String email;
    private String emailProfissional;
    private int dddTelefoneResidencial;
    private int telefoneResidencial;
    private int dddTelefoneComercial;
    private int telefoneComercial;
    private int dddTelefoneCelular;
    private int telefoneCelular;
    private Object razaoSocial;
    private String credencial;
    private EnderecoPessoaRequestBean enderecoPessoaRequest;
    private int idSexo;
    private int idEstadoCivil;
    private String nacionalidade;

    public int getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(int idSexo) {
        this.idSexo = idSexo;
    }

    public int getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(int idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public int getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(int tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public long getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(long idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public long getIdProcessadora() {
        return idProcessadora;
    }

    public void setIdProcessadora(long idProcessadora) {
        this.idProcessadora = idProcessadora;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public long getIdUsuarioInclusao() {
        return idUsuarioInclusao;
    }

    public void setIdUsuarioInclusao(long idUsuarioInclusao) {
        this.idUsuarioInclusao = idUsuarioInclusao;
    }

    public long getIdRegional() {
        return idRegional;
    }

    public void setIdRegional(long idRegional) {
        this.idRegional = idRegional;
    }

    public long getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(long idFilial) {
        this.idFilial = idFilial;
    }

    public long getIdPontoDeRelacionamento() {
        return idPontoDeRelacionamento;
    }

    public void setIdPontoDeRelacionamento(long idPontoDeRelacionamento) {
        this.idPontoDeRelacionamento = idPontoDeRelacionamento;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRgOrgaoEmissor() {
        return rgOrgaoEmissor;
    }

    public void setRgOrgaoEmissor(String rgOrgaoEmissor) {
        this.rgOrgaoEmissor = rgOrgaoEmissor;
    }

    public String getRgDataEmissao() {
        return rgDataEmissao;
    }

    public void setRgDataEmissao(String rgDataEmissao) {
        this.rgDataEmissao = rgDataEmissao;
    }

    public boolean isEstrangeiro() {
        return estrangeiro;
    }

    public void setEstrangeiro(boolean estrangeiro) {
        this.estrangeiro = estrangeiro;
    }

    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailProfissional() {
        return emailProfissional;
    }

    public void setEmailProfissional(String emailProfissional) {
        this.emailProfissional = emailProfissional;
    }

    public int getDddTelefoneResidencial() {
        return dddTelefoneResidencial;
    }

    public void setDddTelefoneResidencial(int dddTelefoneResidencial) {
        this.dddTelefoneResidencial = dddTelefoneResidencial;
    }

    public int getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(int telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public int getDddTelefoneComercial() {
        return dddTelefoneComercial;
    }

    public void setDddTelefoneComercial(int dddTelefoneComercial) {
        this.dddTelefoneComercial = dddTelefoneComercial;
    }

    public int getTelefoneComercial() {
        return telefoneComercial;
    }

    public void setTelefoneComercial(int telefoneComercial) {
        this.telefoneComercial = telefoneComercial;
    }

    public int getDddTelefoneCelular() {
        return dddTelefoneCelular;
    }

    public void setDddTelefoneCelular(int dddTelefoneCelular) {
        this.dddTelefoneCelular = dddTelefoneCelular;
    }

    public int getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(int telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public Object getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(Object razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCredencial() {
        return credencial;
    }

    public void setCredencial(String credencial) {
        this.credencial = credencial;
    }

    public EnderecoPessoaRequestBean getEnderecoPessoaRequest() {
        return enderecoPessoaRequest;
    }

    public void setEnderecoPessoaRequest(EnderecoPessoaRequestBean enderecoPessoaRequest) {
        this.enderecoPessoaRequest = enderecoPessoaRequest;
    }

    public static class EnderecoPessoaRequestBean {
        private List<EnderecosBean> enderecos;

        public List<EnderecosBean> getEnderecos() {
            return enderecos;
        }

        public void setEnderecos(List<EnderecosBean> enderecos) {
            this.enderecos = enderecos;
        }

        public static class EnderecosBean {
            /**
             * idUsuarioInclusao : 999999
             * cep : 71505270
             * logradouro : SHIN QI 3 CJ 7
             * numero : 8
             * complemento :
             * bairro : SETOR DE HABITACOES INDIVIDUAIS NORTE
             * cidade : BRASILIA
             * uf : DF
             */

            private long idUsuarioInclusao;
            private String cep;
            private String logradouro;
            private String numero;
            private String complemento;
            private String bairro;
            private String cidade;
            private String uf;

            public long getIdUsuarioInclusao() {
                return idUsuarioInclusao;
            }

            public void setIdUsuarioInclusao(long idUsuarioInclusao) {
                this.idUsuarioInclusao = idUsuarioInclusao;
            }

            public String getCep() {
                return cep;
            }

            public void setCep(String cep) {
                this.cep = cep;
            }

            public String getLogradouro() {
                return logradouro;
            }

            public void setLogradouro(String logradouro) {
                this.logradouro = logradouro;
            }

            public String getNumero() {
                return numero;
            }

            public void setNumero(String numero) {
                this.numero = numero;
            }

            public String getComplemento() {
                return complemento;
            }

            public void setComplemento(String complemento) {
                this.complemento = complemento;
            }

            public String getBairro() {
                return bairro;
            }

            public void setBairro(String bairro) {
                this.bairro = bairro;
            }

            public String getCidade() {
                return cidade;
            }

            public void setCidade(String cidade) {
                this.cidade = cidade;
            }

            public String getUf() {
                return uf;
            }

            public void setUf(String uf) {
                this.uf = uf;
            }
        }
    }
}

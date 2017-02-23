package itspay.br.com.services;

import java.util.ArrayList;

import itspay.br.com.model.AvisarPerdaOuRouboRequest;
import itspay.br.com.model.Banco;
import itspay.br.com.model.BoletoCarga;
import itspay.br.com.model.BuscarEmailResponse;
import itspay.br.com.model.Credencial;
import itspay.br.com.model.CredencialGerada;
import itspay.br.com.model.CredencialStatus;
import itspay.br.com.model.CriarLoginResponse;
import itspay.br.com.model.EnderecoPessoa;
import itspay.br.com.model.FazerLoginPortador;
import itspay.br.com.model.FazerLoginPortadorResponse;
import itspay.br.com.model.FazerPedidoMKTPlace;
import itspay.br.com.model.GerarBoletoCarga;
import itspay.br.com.model.GerarCredencialRequest;
import itspay.br.com.model.GetCredenciaisResponse;
import itspay.br.com.model.GetFormasEnvioResponse;
import itspay.br.com.model.GetInfoPortadorCredencialRequest;
import itspay.br.com.model.GetPerfilTarifarioResponse;
import itspay.br.com.model.ItsPayResponse;
import itspay.br.com.model.LinhaExtratoCredencial;
import itspay.br.com.model.ParceiroResponse;
import itspay.br.com.model.ParcelasResponse;
import itspay.br.com.model.Pedido;
import itspay.br.com.model.PedidoDetalhe;
import itspay.br.com.model.PortadorCredencial;
import itspay.br.com.model.PortadorLogin;
import itspay.br.com.model.TransferenciaContaCorrente;
import itspay.br.com.model.TransferenciaMesmaInstituicao;
import itspay.br.com.model.TrocarEmail;
import itspay.br.com.model.TrocarEstadoCredencialRequest;
import itspay.br.com.model.TrocarPinRequest;
import itspay.br.com.model.TrocarSenhaPortador;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by yesus on 16/12/16.
 */

public interface PortadorService {

    @POST("api/portador/login/auth")
    Call<FazerLoginPortadorResponse> login(@Body FazerLoginPortador fazerLoginPortador);

    @POST("api/portador/login")
    Call<CriarLoginResponse> criarLogin(@Body PortadorLogin portadorLogin);

    @GET("api/portador/credencial/{documento}/pessoa/{tipoPessoa}/processadora/{idProcessadora}/instituicao/{idInstituicao}")
    Call<GetCredenciaisResponse> listaCredenciais(
            @Path("documento") String documento,
            @Path("tipoPessoa") long tipoPessoa,
            @Path("idProcessadora") long idProcessadora,
            @Path("idInstituicao") long idInstituicao,
            @Header("AuthorizationPortador") String token
    );

    @GET("api/portador/credencial/{idCredencial}/detalhes")
    Call<Credencial> credencialDetalhes(
            @Path("idCredencial") long idCredencial,
            @Header("AuthorizationPortador") String token
    );

    @GET("api/portador/login/logout")
    Call<ResponseBody> logout();

    @PUT("api/portador/login/trocar-email")
    Call<ItsPayResponse> trocarEmail(@Body TrocarEmail trocarEmail, @Header("AuthorizationPortador") String token);

    @PUT("api/portador/login/trocar-senha")
    Call<ItsPayResponse> trocarSenha(@Body TrocarSenhaPortador trocarSenhaPortador, @Header("AuthorizationPortador") String token);

    @GET("api/portador/login/{idProcessadora}/{idInstituicao}/buscar-email/{documento}")
    Call<BuscarEmailResponse> buscarEmail(@Path("idProcessadora") long idProcessadora,
                                          @Path("idInstituicao") long idInstituicao,
                                          @Path("documento") String documento,
                                          @Header("AuthorizationPortador") String token);

    //{periodo} - Valores aceitos 15, 30 ou 45.
    @GET("api/portador/credencial/{idCredencial}/extrato/data_inicial/{dataInicial}/data_final/{dataFinal}")
    Call<LinhaExtratoCredencial[]> extratoPeriodo(@Path("idCredencial") long idCredencial,
                                                  @Path("dataInicial") String dataInicial,
                                                  @Path("dataFinal") String dataFinal,
                                                  @Header("AuthorizationPortador") String token);

    //{periodo} - Valores aceitos 15, 30 ou 45.
    @GET("api/portador/credencial/{idCredencial}/extrato/periodo/{periodo}")
    Call<LinhaExtratoCredencial[]> extratoCredencial(@Path("idCredencial") long idCredencial,
                                                     @Path("periodo") String periodo,
                                                     @Header("AuthorizationPortador") String token);

    @GET("api/plastico/abrir/mobile/{idPlastico}")
    Call<ResponseBody> abrirPlastico(@Path("idPlastico") long idPlastico,
                                     @Header("AuthorizationPortador") String token);

    /**
     * @param portadorCredencialRequest a credencial deve ser criptografada com SHA512
     * @param token
     * @return
     */
    @POST("api/portador/credencial/info-portador")
    Call<PortadorCredencial> getPortadorCredencial(@Body GetInfoPortadorCredencialRequest portadorCredencialRequest,
                                                   @Header("AuthorizationPortador") String token);

    @POST("api/portador/conta/transferencia")
    Call<ResponseBody> transferenciaOutroCartao(@Body TransferenciaMesmaInstituicao request,
                                                @Header("AuthorizationPortador") String token);

    @POST("api/portador/conta/transferencia/conta/corrente")
    Call<ResponseBody> transferenciaContaCorrente(@Body TransferenciaContaCorrente request,
                                                  @Header("AuthorizationPortador") String token);

    @GET("api/banco")
    Call<Banco[]> listaBancos(@Header("AuthorizationPortador") String token);

    @POST("api/boleto/carga/gerar-linha-digitavel")
    Call<BoletoCarga> gerarLinhaDigitavel(@Body GerarBoletoCarga request,
                                          @Header("AuthorizationPortador") String token);

    @POST("api/boleto/carga/enviar-boleto-email")
    Call<ResponseBody> enviarBoletoEmail(@Body GerarBoletoCarga request,
                                         @Header("AuthorizationPortador") String token);

    @GET("api/portador/credencial/virtual/conta/{idConta}")
    Call<GetCredenciaisResponse> listaCartoesVirtuais(@Path("idConta") long idConta,
                                                      @Header("AuthorizationPortador") String token);

    @POST("api/gerador/credencial")
    Call<CredencialGerada> novoCartaoVirtual(@Body GerarCredencialRequest request,
                                             @Header("AuthorizationPortador") String token);

    @GET("api/portador/credencial/status-habilitacao/{idCredencial}")
    Call<CredencialStatus> listaStatusHabilitacao(@Path("idCredencial") long idCredencial,
                                                  @Header("AuthorizationPortador") String token);

    @POST("api/portador/credencial/trocar-estado")
    Call<ResponseBody> trocarEstado(@Body TrocarEstadoCredencialRequest request,
                                    @Header("AuthorizationPortador") String token);


    @POST("api/portador/credencial/avisar-perda")
    Call<ResponseBody> avisarPerda(@Body AvisarPerdaOuRouboRequest request,
                                   @Header("AuthorizationPortador") String token);

    @POST("api/portador/credencial/avisar-roubo")
    Call<ResponseBody> avisarRoubo(@Body AvisarPerdaOuRouboRequest request,
                                   @Header("AuthorizationPortador") String token);

    @POST("api/portador/credencial/trocar-pin")
    Call<Boolean> trocarSenhaCartao(@Body TrocarPinRequest request,
                                         @Header("AuthorizationPortador") String token);

    @GET("api/portador/conta/buscar-tarifas/conta/{idConta}")
    Call<GetPerfilTarifarioResponse> listaTarifas(@Path("idConta") long idConta,
                                                  @Header("AuthorizationPortador") String token);

    @GET("api/portador/credencial/{documento}/pessoa/{tipoPessoa}/processadora/{idProc}/instituicao/{idInst}/desbloqueadas")
    Call<GetCredenciaisResponse> listaCredenciaisLoja(
            @Path("documento") String documento,
            @Path("tipoPessoa") long tipoPessoa,
            @Path("idProc") long idProcessadora,
            @Path("idInst") long idInstituicao,
            @Header("AuthorizationPortador") String token
    );

    @GET("api/mktplace/portador/pedido/pessoa/{documento}/processadora/{idProcessadora}/instituicao/{idInstituicao}")
    Call<Pedido[]> buscarPedidos(@Path("documento") String documento,
                                 @Path("idProcessadora") long idProcessadora,
                                 @Path("idInstituicao") long idInstituicao,
                                 @Header("AuthorizationPortador") String token);

    @GET("api/mktplace/portador/pedido/{idPedido}")
    Call<PedidoDetalhe> buscarPedidoDetalhe(
                                 @Path("idPedido") long idPedido,
                                 @Header("AuthorizationPortador") String token);

    @GET("api/mktplace/portador/parceiro-produto/{idProcessadora}/{idInstituicao}/")
    Call<ArrayList<ParceiroResponse>> getParceiros(@Path("idProcessadora") long idProcessadora,
                                                   @Path("idInstituicao") long idInstituicao,
                                                   @Header("AuthorizationPortador") String token);

    @GET("api/mktplace/administrativo/imagem/sku/{idImagem}")
    Call<ResponseBody> abrirImagemProduto(@Path("idImagem") long idImagem,
                                          @Header("AuthorizationPortador") String token);

    @GET("api/mktplace/portador/formas-envio/{idParceiro}/endereco/{idEndereco}")
    Call<GetFormasEnvioResponse[]> getFormasEnvio(@Path("idParceiro") long idParceiro,
                                                  @Path("idEndereco") long idEndereco,
                                                  @Header("AuthorizationPortador") String token);

    @GET("api/endereco/{documento}/pessoa/{tipoPessoa}/processadora/{idProc}/instituicao/{idInst}/status/{status}/")
    Call<EnderecoPessoa[]> getEnderecoPortador(@Path("documento") String documento,
                                               @Path("tipoPessoa") long tipoPessoa,
                                               @Path("idProc") long idProc,
                                               @Path("idInst") long idInst,
                                               @Path("status") long status,
                                               @Header("AuthorizationPortador") String token);

    @POST("api/mktplace/portador/pedido")
    Call<Integer> efetuarPedido(@Body FazerPedidoMKTPlace request,
                                        @Header("AuthorizationPortador") String token);

    @GET("api/mktplace/portador/parcelas/{idParceiro}/valor/{valorCarrinho}")
    Call<ParcelasResponse> getParcelamento(@Path("idParceiro") long idParceiro,
                                           @Path("valorCarrinho") double valorCarrinho,
                                           @Header("AuthorizationPortador") String token);
}
package itspay.br.com.services;

import itspay.br.com.model.BuscarEmailResponse;
import itspay.br.com.model.Credencial;
import itspay.br.com.model.CriarLoginResponse;
import itspay.br.com.model.FazerLoginPortador;
import itspay.br.com.model.FazerLoginPortadorResponse;
import itspay.br.com.model.GetCredenciaisResponse;
import itspay.br.com.model.GetInfoPortadorCredencialRequest;
import itspay.br.com.model.ItsPayResponse;
import itspay.br.com.model.LinhaExtratoCredencial;
import itspay.br.com.model.PortadorCredencial;
import itspay.br.com.model.PortadorLogin;
import itspay.br.com.model.TransferenciaMesmaInstituicao;
import itspay.br.com.model.TrocarEmail;
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
}

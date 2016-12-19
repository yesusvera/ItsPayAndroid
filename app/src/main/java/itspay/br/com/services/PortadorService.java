package itspay.br.com.services;

import java.util.List;

import itspay.br.com.model.Credencial;
import itspay.br.com.model.CriarLoginResponse;
import itspay.br.com.model.FazerLoginPortador;
import itspay.br.com.model.FazerLoginPortadorResponse;
import itspay.br.com.model.PortadorLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by yesus on 16/12/16.
 */

public interface PortadorService {

    @POST("api/portador/login/auth")
    Call<FazerLoginPortadorResponse> login(@Body FazerLoginPortador fazerLoginPortador);

    @POST("api/portador/login")
    Call<CriarLoginResponse> criarLogin(@Body PortadorLogin portadorLogin);


    @GET("api/portador/credencial/{documento}/pessoa/{tipoPessoa}/processadora/{idProc}/instituicao/{idInst}")
    Call<List<Credencial>> listaCredenciais(
                                     @Path("documento") String documento,
                                     @Path("tipoPessoa") long tipoPessoa,
                                     @Path("idProc") long idProcessadora,
                                     @Path("idInst") long idInstituicao,
                                     @Header("AuthorizationPortador") String token,
                                     @Header("Set-Cookie") String setCookie
                                     );

}

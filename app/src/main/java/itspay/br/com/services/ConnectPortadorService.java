package itspay.br.com.services;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yesus on 17/12/16.
 */

public abstract class ConnectPortadorService {

    private static PortadorService service;

    public static PortadorService getService(){
        if(service==null){

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            // init cookie manager
            CookieHandler cookieHandler = new CookieManager();

            OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor)
                    .cookieJar(new JavaNetCookieJar(cookieHandler))
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();


            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl("http://ppp.sinergico.com.br:8080/issuer/")
                    .baseUrl("http://tst.api.itspay.com.br/api/")
//                    .baseUrl("http://tst.issuer.itspay.com.br/issuer/")
//                    .client(client)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(PortadorService.class);
        }
        return service;
    }
}

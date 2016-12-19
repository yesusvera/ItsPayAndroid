package itspay.br.com.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yesus on 17/12/16.
 */

public abstract class ConnectPortadorService {

    private static PortadorService service;

    public static PortadorService getService(){
        if(service==null){



            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://ppp.sinergico.com.br:8080/issuer/")
//                    .baseUrl("http://tst.api.itspay.com.br/issuer/")
//                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(PortadorService.class);
        }
        return service;
    }
}

package com.jtl.duanzile_lib;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class HttpHelper {
    Retrofit retrofit;
    private OkHttpClient okHttpClient;
    public HttpHelper(String url){
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .build();

        retrofit.create()



    }




}

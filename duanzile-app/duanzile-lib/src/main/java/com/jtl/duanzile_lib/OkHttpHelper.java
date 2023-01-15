package com.jtl.duanzile_lib;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHelper {
    OkHttpClient okHttpClient;
    private OkHttpHelper(){
        okHttpClient = new OkHttpClient();
        okHttpClient.readTimeoutMillis();
    }
    public static final MediaType JSON = MediaType.get("application/json");


    public String postRequest(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String getRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }
    public static OkHttpBuilder builder(){
        return new OkHttpBuilder();
    }

    private static class OkHttpBuilder{
        private String url;

        public OkHttpBuilder setUrl(String url){
            this.url = url;
            return this;
        }

        public OkHttpHelper build(){
            return new OkHttpHelper();
        }
    }

}

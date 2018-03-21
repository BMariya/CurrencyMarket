package com.present.market.core.store;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RemoteClient {

    private OkHttpClient mClient;
    private Retrofit mRetrofit;
    private final String mUrl;

    public RemoteClient (String url) {
        this.mClient = new OkHttpClient();
        this.mUrl = url;
        this.mRetrofit = new Retrofit
                .Builder()
                .baseUrl(this.mUrl)
                .client(mClient)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return this.mRetrofit;
    }

}

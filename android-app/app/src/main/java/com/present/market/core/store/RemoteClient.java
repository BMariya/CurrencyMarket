package com.present.market.core.store;

import com.present.market.ValuteService;
import com.present.market.core.base.AbsObj;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RemoteClient extends AbsObj {

    private OkHttpClient mClient;
    private Retrofit mRetrofit;
    private final String mUrl;

    public RemoteClient (String url) {
        log().debug("url=%s", url);
        this.mClient = new OkHttpClient();
        this.mUrl = url;
        this.mRetrofit = new Retrofit
                .Builder()
                .baseUrl(this.mUrl)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        ValuteService valuteService = this.mRetrofit.create(ValuteService.class);
        valuteService.listValutes();
    }

    public Retrofit getRetrofit() {
        return this.mRetrofit;
    }

}

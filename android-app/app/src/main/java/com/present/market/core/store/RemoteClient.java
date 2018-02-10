package com.present.market.core.store;

import com.present.market.core.base.AbsObj;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

public class RemoteClient extends AbsObj {

    private OkHttpClient mClient;
    private final String mUrl;

    public RemoteClient (String url) {
        log().debug("url=%s", url);
        this.mClient = new OkHttpClient();
        this.mUrl = url;
    }

    public Call getRequest() {
        return this.mClient.newCall(new Request.Builder().url(this.mUrl).build());
    }

}

package com.present.market;

import com.present.market.core.AbsAppStore;
import com.present.market.core.base.AppCallback;
import com.present.market.core.base.AppEx;
import com.present.market.core.base.AppType;
import com.present.market.core.store.LocalSource;
import com.present.market.core.store.RemoteSource;
import com.present.market.obj.Valute;
import com.present.market.obj.Valutes;

import java.util.ArrayList;
import java.util.List;

public final class AppStore extends AbsAppStore<AppStore, Valutes> {
    private final LocalSource<Valutes> mLocalSource;
    private final RemoteSource<Valutes> mRemoteSource;
    private Valute mRefMarketValute;
    private Valute mRefValute;
    private AppType.AppAmount mRefAmount;
    private String mTitle;
    private AppType.AppDate mDate;
    private List<Valute> mValuteList;
    public AppStore(LocalSource<Valutes> localSource, RemoteSource<Valutes> remoteSource,
                    Valute refMarketValute) {
        super();
        this.mLocalSource = localSource;
        this.mRemoteSource = remoteSource;
        this.mRefMarketValute = refMarketValute;
        this.mRefValute = refMarketValute;
        this.mRefAmount = new AppType.AppAmount(1);
        this.mTitle = "-";
        this.mDate = new AppType.AppDate();
        this.mValuteList = new ArrayList<>();
        this.sync();
    }

    @Override
    protected AppStore store() {
        return this;
    }

    private void sync() {
        log().debug("sync");
        this.mLocalSource.load(new AppCallback<Valutes>() {
            @Override
            public void onError(AppEx appEx) {
                AppStore.this.onError(appEx);
            }

            @Override
            public void onResult(Valutes valutes) {
                AppStore.this.onResult(valutes);
            }
        });
        this.mRemoteSource.load(new AppCallback<Valutes>() {
            @Override
            public void onError(AppEx appEx) {
                AppStore.this.onError(appEx);
            }

            @Override
            public void onResult(Valutes valutes) {
                AppStore.this.onResult(valutes);
                AppStore.this.mLocalSource.save(valutes, new AppCallback<Valutes>() {
                    @Override
                    public void onError(AppEx appEx) {}
                    @Override
                    public void onResult(Valutes valutes) {}
                });
            }
        });
    }

    @Override
    public void onResult(Valutes valutes) {
        log().debug("onResult");
        log().todo("onResult. clear ex?");
        this.mTitle = valutes.getName();
        this.mDate = valutes.getDate();
        this.mValuteList = valutes.getValuteList();
        updateAppCallbacks();
    }

    public String getTitle() {
        return this.mTitle;
    }

    public AppType.AppDate getDate() {
        return this.mDate;
    }

    public AppType.AppAmount getRefAmount() {
        return this.mRefAmount;
    }

    public Valute getRefValute() {
        return this.mRefValute;
    }

    public List<Valute> getValuteList() {
        ArrayList<Valute> result = new ArrayList<>(this.mValuteList);
        result.add(0, this.mRefMarketValute);
        return result;
    }

    public void setRefAmount(AppType.AppAmount refAmount) {
        log().debug("setRefAmount.refAmount=%s", refAmount);
        if (!this.mRefAmount.equals(refAmount)) {
            this.mRefAmount = refAmount;
            updateAppCallbacks();
        }
    }

    public void setRefValute(Valute refValute) {
        log().debug("setRefValute");
        if (!this.mRefValute.equals(refValute)) {
            this.mRefValute = refValute;
            updateAppCallbacks();
        }
    }
}

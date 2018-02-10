package com.present.market;

import com.present.market.obj.Valutes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ValuteService {
    @GET("scripts/XML_daily.asp")
    Call<Valutes> listValutes();
}

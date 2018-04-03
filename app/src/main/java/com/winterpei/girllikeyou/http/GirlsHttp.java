package com.winterpei.girllikeyou.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author xingyang.pei
 * @date 2017/9/4.
 */

public final class GirlsHttp extends RetrofitClient {

    private static ApiService mApiService;

    public static ApiService api() {
        return null == mApiService ? getRetrofit().create(ApiService.class) : mApiService;
    }

    private static Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://gank.io/api/")
                .build();
        return retrofit;
    }

}

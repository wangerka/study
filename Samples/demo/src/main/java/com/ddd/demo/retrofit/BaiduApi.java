package com.ddd.demo.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface BaiduApi {
    @GET
    Call<String> getHtml(@Url String url);
}

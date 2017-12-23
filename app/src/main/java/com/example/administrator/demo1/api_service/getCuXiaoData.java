package com.example.administrator.demo1.api_service;

import com.example.administrator.demo1.bean.CuXiaoBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/10/27.
 */
public interface getCuXiaoData {

    @GET(value = "app/promotion_activity?")
    Call<CuXiaoBean> getCuXiaoList(@Query(value = "pg") String pg);
}

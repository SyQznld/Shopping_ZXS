package com.example.administrator.demo1.api_service;

import com.example.administrator.demo1.bean.ZbYouHuiBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/10/31.
 */
public interface GetYouHuiData {


    @GET(value = "interface/quan?")
    Call<ZbYouHuiBean> getYouHuiList(@Query(value = "pg") String pg);
}

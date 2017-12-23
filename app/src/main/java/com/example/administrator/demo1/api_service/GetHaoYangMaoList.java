package com.example.administrator.demo1.api_service;

import com.example.administrator.demo1.bean.SyHaoYangMaoBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2016/10/28.
 */

//"http://app.gwdang.com/app/price_zhi?ps=20&is_haitao=2&format=json&img_width=517&current_time=1477112529&version=2"

public interface GetHaoYangMaoList {
    @GET(value = "app/price_zhi?ps=20&is_haitao=2&format=json&img_width=517&current_time=1477112529&version=2")
    Call<SyHaoYangMaoBean> getHaoYangMaoData();
}

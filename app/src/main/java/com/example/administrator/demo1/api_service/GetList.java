package com.example.administrator.demo1.api_service;

import com.example.administrator.demo1.bean.XPopTotalBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2016/10/24.
 */

public interface GetList {
    @GET(value = "app/price_zhi?ps=20&is_haitao=1&format=json&img_width=375&current_time=1477111800&version=2")
    Call<XPopTotalBean> getListData();
}

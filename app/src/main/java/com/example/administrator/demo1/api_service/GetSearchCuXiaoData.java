package com.example.administrator.demo1.api_service;

import com.example.administrator.demo1.bean.CuXiaoBean;
import com.example.administrator.demo1.bean.Search_CuXiaoBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/11/1.
 */
public interface GetSearchCuXiaoData {
    @GET(value = "app/promotion_activity?pg=1&ps=20&order_by=0&format=json&img_width=1052&")
   Call<Search_CuXiaoBean> getSearchCuXiaoList(@Query(value = "keyword") String keyword);
}

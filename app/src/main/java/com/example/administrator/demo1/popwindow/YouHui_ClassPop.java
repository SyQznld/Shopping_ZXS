package com.example.administrator.demo1.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.PopupWindow;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.adapter.CuXiao_ClassAdapter;
import com.example.administrator.demo1.adapter.YouHui_ClassAdapter;
import com.example.administrator.demo1.api_service.GetYouHuiData;
import com.example.administrator.demo1.api_service.getCuXiaoData;
import com.example.administrator.demo1.bean.CuXiaoBean;
import com.example.administrator.demo1.bean.ZbYouHuiBean;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2016/10/31.
 */
public class YouHui_ClassPop extends PopupWindow {

    private View contentView;
    private GridView gridView_shop_pop;
    private YouHui_ClassAdapter sgAdapter;
    int pg = 1;
    private List<ZbYouHuiBean.ClassListBean> sitesBeen = new ArrayList<>();
    public YouHui_ClassPop(final Activity context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.layout_shop_pop,null);
        gridView_shop_pop = (GridView) contentView.findViewById(R.id.gridView_shop_pop);
        sgAdapter = new YouHui_ClassAdapter(context,sitesBeen);
        gridView_shop_pop.setAdapter(sgAdapter);
        initPopWindow(context);
        initShopGrid();


    }

   private void initShopGrid() {
       String baseUrl = "http://app.gwdang.com/";
       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(baseUrl)
               .addConverterFactory(GsonConverterFactory.create())
               .build();
       GetYouHuiData data = retrofit.create(GetYouHuiData.class);
       Call<ZbYouHuiBean> call = data.getYouHuiList(pg + "&ps=20&format=json&current_time=1477113443");
       call.enqueue(new Callback<ZbYouHuiBean>() {
           @Override
           public void onResponse(Call<ZbYouHuiBean> call, Response<ZbYouHuiBean> response) {
               List<ZbYouHuiBean.ClassListBean> sites = response.body().getClass_list();
               if (sites != null && sites.size() > 0) {
                   sitesBeen.clear();
                   sitesBeen.addAll(sites);
                   sgAdapter.notifyDataSetChanged();
               }
           }

           @Override
           public void onFailure(Call<ZbYouHuiBean> call, Throwable t) {
               Log.e(TAG, "onFailure:=============="+t.getMessage() );
           }
       });
    }

    private void initPopWindow(final Activity context) {
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        this.setContentView(contentView);
        this.setWidth(w);
        this.setHeight(h/3);
        ColorDrawable dw = new ColorDrawable(Color.argb(255,255,255,255));
        this.setBackgroundDrawable(dw);
        this.setFocusable(false);
        this.setOutsideTouchable(true);
        this.update();
    }
    public void showPricePopup(View parent) {
        if (!this.isShowing()) {
            this.showAsDropDown(parent);
        } else {
            this.dismiss();
        }
    }
}

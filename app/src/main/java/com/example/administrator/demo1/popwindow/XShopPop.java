package com.example.administrator.demo1.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.PopupWindow;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.adapter.XShopGridAdapter;
import com.example.administrator.demo1.api_service.GetList;
import com.example.administrator.demo1.bean.XPopTotalBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2016/10/26.
 */

public class XShopPop extends PopupWindow {
    private View contentView;
    private GridView gridView_shop_pop;
    private XShopGridAdapter sgAdapter;
    private List<XPopTotalBean.SitesBean> sitesBeen = new ArrayList<>();
    public XShopPop(final Activity context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.layout_shop_pop,null);
        gridView_shop_pop = (GridView) contentView.findViewById(R.id.gridView_shop_pop);
        sgAdapter = new XShopGridAdapter(context,sitesBeen);
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
        GetList data = retrofit.create(GetList.class);
        Call<XPopTotalBean> call = data.getListData();
        call.enqueue(new Callback<XPopTotalBean>() {
            @Override
            public void onResponse(Call<XPopTotalBean> call, Response<XPopTotalBean> response) {
                List<XPopTotalBean.SitesBean> sites = response.body().getSites();
                if (sites != null && sites.size() > 0) {
                        sitesBeen.clear();
                    sitesBeen.addAll(sites);
                    sgAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<XPopTotalBean> call, Throwable t) {
                Log.e(TAG, "onFailure:=============="+t.getMessage() );
            }
        });
    }
    private void initPopWindow(final Activity context) {
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        this.setContentView(contentView);
        this.setWidth(w);
        this.setHeight(h/2);
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

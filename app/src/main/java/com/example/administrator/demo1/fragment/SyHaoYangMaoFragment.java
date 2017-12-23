package com.example.administrator.demo1.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.activity.SyHaoYangMaoDetailActivity;
import com.example.administrator.demo1.activity.SyLiuShiClickActivity;
import com.example.administrator.demo1.adapter.SyHaoYangMaoGridAdapter;
import com.example.administrator.demo1.bean.SyHaoYangMaoBean;
import com.example.administrator.demo1.popwindow.XBrandsPop;
import com.example.administrator.demo1.popwindow.XPricePop;
import com.example.administrator.demo1.popwindow.XShopPop;
import com.example.administrator.demo1.popwindow.XclassesPop;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class SyHaoYangMaoFragment extends Fragment {

    //分类
    private LinearLayout ranking_common;
    //商城
    private LinearLayout ranking_sale_volume;
    //品牌
    private LinearLayout ranking_brand;
    //价格
    private LinearLayout ranking_price;

    List<SyHaoYangMaoBean.ProductBean> totalList = new ArrayList<>();
    PullToRefreshGridView haoyangmao_gridView;
    SyHaoYangMaoGridAdapter adapter;
    String path = "http://app.gwdang.com/app/price_zhi?ps=20&is_haitao=2&format=json&img_width=517&current_time=1477112529&version=2";

    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sy_hao_yang_mao, container, false);

        //查找控件
        initView(view);

        //adapter填充数据
        initAdapter();
        //Okhttp请求数据
        initOkHttp();
        //PopWindow点击事件
        initOnCLick();

        haoyangmao_gridView.setMode(PullToRefreshBase.Mode.BOTH);
        haoyangmao_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(context,"========"+position,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context, SyHaoYangMaoDetailActivity.class);

                String url = totalList.get(position).getUrl();
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });


        return view;
    }

    private void initAdapter() {

        adapter = new SyHaoYangMaoGridAdapter(totalList, context);
        haoyangmao_gridView.setAdapter(adapter);
    }

    private void initOkHttp() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(path)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();
                if (!TextUtils.isEmpty(string)) {
                    Gson gson = new Gson();
                    SyHaoYangMaoBean syHaoYangMaoBean = gson.fromJson(string, SyHaoYangMaoBean.class);
                    List<SyHaoYangMaoBean.ProductBean> list = syHaoYangMaoBean.getProduct();
                    totalList.addAll(list);
                    haoyangmao_gridView.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                }

            }
        });
    }

    private void initView(View view) {
        haoyangmao_gridView = (PullToRefreshGridView) view.findViewById(R.id.haoyangmao_gridView);

        ranking_common = (LinearLayout) view.findViewById(R.id.ranking_common);
        ranking_brand = (LinearLayout) view.findViewById(R.id.ranking_brand);
        ranking_price = (LinearLayout) view.findViewById(R.id.ranking_price);
        ranking_sale_volume = (LinearLayout) view.findViewById(R.id.ranking_sale_volume);

    }

    private void initOnCLick() {
        ranking_common.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XclassesPop popup = new XclassesPop(getActivity());
                popup.showPricePopup(v);
            }
        });
        ranking_sale_volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XShopPop pop = new XShopPop(getActivity());
                pop.showPricePopup(v);
            }
        });
        ranking_brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XBrandsPop pop = new XBrandsPop(getActivity());
                pop.showPricePopup(v);
            }
        });
        ranking_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XPricePop pop = new XPricePop(getActivity());
                pop.showPricePopup(v);
            }
        });
    }

}

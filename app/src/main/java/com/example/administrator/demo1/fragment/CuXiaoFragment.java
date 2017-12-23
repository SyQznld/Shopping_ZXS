package com.example.administrator.demo1.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.activity.SyHaoYangMaoDetailActivity;
import com.example.administrator.demo1.adapter.CuXiaoAdapter;
import com.example.administrator.demo1.api_service.getCuXiaoData;
import com.example.administrator.demo1.bean.CuXiaoBean;
import com.example.administrator.demo1.popwindow.CuXiao_ClassPop;
import com.example.administrator.demo1.popwindow.CuXiao_SitesPop;
import com.example.administrator.demo1.popwindow.CuXiao_TypePop;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class CuXiaoFragment extends Fragment {


    //http://app.gwdang.com/app/promotion_activity?pg=1&ps=20&order_by=0&format=json&img_width=1050&current_time=1477112991

    //分类
    private LinearLayout ranking_common;
    //商城
    private LinearLayout ranking_sale_volume;

    private LinearLayout ranking_price;


    PullToRefreshListView cuxiao_refreshListView;
    List<CuXiaoBean.PromotionBean> list = new ArrayList<>();
    int pg = 1;
    CuXiaoAdapter cuXiaoAdapter;
    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cu_xiao, container, false);
        cuxiao_refreshListView = (PullToRefreshListView) view.findViewById(R.id.cuxiao_refreshListView);


        cuXiaoAdapter = new CuXiaoAdapter(list, context);
        cuxiao_refreshListView.setAdapter(cuXiaoAdapter);

        //Retrofit
        initRetrofit();
        //cuxiao_refreshListView 处理事件  刷新  点击跳转

        initPullToRefreshListView();

        initPopClick(view);


        return view;
    }

    private void initPopClick(View view) {

        ranking_common = (LinearLayout) view.findViewById(R.id.ranking_common);
        ranking_sale_volume = (LinearLayout) view.findViewById(R.id.ranking_sale_volume);
        ranking_price = (LinearLayout) view.findViewById(R.id.ranking_price);

        ranking_common.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CuXiao_ClassPop pop = new CuXiao_ClassPop(getActivity());
                pop.showPricePopup(v);

            }
        });
        ranking_sale_volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CuXiao_SitesPop pop = new CuXiao_SitesPop(getActivity());
                pop.showPricePopup(v);
            }
        });

        ranking_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CuXiao_TypePop pop = new CuXiao_TypePop(getActivity());
                pop.showPricePopup(v);
            }
        });
    }

    private void initPullToRefreshListView() {
        cuxiao_refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        cuxiao_refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                pg = 1;
                initRetrofit();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pg++;
                initRetrofit();
            }
        });

        cuxiao_refreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = list.get(position).getUrl();
                Intent intent = new Intent(getActivity(), SyHaoYangMaoDetailActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
    }

    private void initRetrofit() {

        String baseUrl = "http://app.gwdang.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getCuXiaoData getCuXiaoData = retrofit.create(getCuXiaoData.class);
        Call<CuXiaoBean> cuXiaoList = getCuXiaoData.getCuXiaoList(pg + "&ps=20&order_by=0&format=json&img_width=1050&current_time=1477112991");
        cuXiaoList.enqueue(new Callback<CuXiaoBean>() {
            @Override
            public void onResponse(Call<CuXiaoBean> call, Response<CuXiaoBean> response) {
                List<CuXiaoBean.PromotionBean> promotionBean = response.body().getPromotion();
                list.clear();
                list.addAll(promotionBean);
                cuXiaoAdapter.notifyDataSetChanged();
                cuxiao_refreshListView.onRefreshComplete();
            }

            @Override
            public void onFailure(Call<CuXiaoBean> call, Throwable t) {

            }
        });
    }

}

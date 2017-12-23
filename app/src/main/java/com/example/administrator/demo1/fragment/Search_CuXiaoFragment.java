package com.example.administrator.demo1.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.activity.SyHaoYangMaoDetailActivity;
import com.example.administrator.demo1.adapter.CuXiaoAdapter;
import com.example.administrator.demo1.adapter.Search_CuXiaoAdapter;
import com.example.administrator.demo1.api_service.GetSearchCuXiaoData;
import com.example.administrator.demo1.api_service.getCuXiaoData;
import com.example.administrator.demo1.bean.CuXiaoBean;
import com.example.administrator.demo1.bean.Search_CuXiaoBean;
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
public class Search_CuXiaoFragment extends Fragment {

    PullToRefreshListView search_cuxiaoListView;
    List<Search_CuXiaoBean.PromotionBean> list = new ArrayList<>();
    int pg = 1;
    Search_CuXiaoAdapter cuXiaoAdapter;
    Context context;
    String keyword;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search__cu_xiao, container, false);
        search_cuxiaoListView = (PullToRefreshListView) view.findViewById(R.id.search_cuxiaoListView);


        Bundle bundle = getArguments();
        if (bundle != null) {
            keyword = bundle.getString("keyword");
        }

        cuXiaoAdapter = new Search_CuXiaoAdapter(list, context);
        search_cuxiaoListView.setAdapter(cuXiaoAdapter);

        //Retrofit
        initRetrofit();
        //cuxiao_refreshListView 处理事件  刷新  点击跳转

        initPullToRefreshListView();

        return view;
    }

    private void initPullToRefreshListView() {
        search_cuxiaoListView.setMode(PullToRefreshBase.Mode.BOTH);
        search_cuxiaoListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
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

        search_cuxiaoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        GetSearchCuXiaoData getCuXiaoData = retrofit.create(GetSearchCuXiaoData.class);
        Call<Search_CuXiaoBean> cuXiaoList = getCuXiaoData.getSearchCuXiaoList(keyword);
        cuXiaoList.enqueue(new Callback<Search_CuXiaoBean>() {
            @Override
            public void onResponse(Call<Search_CuXiaoBean> call, Response<Search_CuXiaoBean> response) {
                List<Search_CuXiaoBean.PromotionBean> promotionBean = response.body().getPromotion();
                list.clear();
                list.addAll(promotionBean);
                cuXiaoAdapter.notifyDataSetChanged();
                search_cuxiaoListView.onRefreshComplete();
            }

            @Override
            public void onFailure(Call<Search_CuXiaoBean> call, Throwable t) {

            }
        });
    }

}

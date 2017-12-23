package com.example.administrator.demo1.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.activity.ZbYouHuiDetailsActivity;
import com.example.administrator.demo1.adapter.ZbYouHuiAdapter;
import com.example.administrator.demo1.bean.ZbYouHuiBean;
import com.example.administrator.demo1.popwindow.YouHui_ClassPop;
import com.example.administrator.demo1.popwindow.YouHui_SitesPop;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZbYouHuiFragment extends Fragment {

    //分类
    private LinearLayout ranking_common;
    //商城
    private LinearLayout ranking_sale_volume;

    private List<ZbYouHuiBean.ListBean> totalList = new ArrayList<>();
    String url;
    PullToRefreshListView zb_youhui_PullToRefreshListView;
    ZbYouHuiAdapter adapter;
    int pg = 1;
    Context context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
    public ZbYouHuiFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zb_you_hui, container, false);
        zb_youhui_PullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.zb_youhui_ListView);
        ranking_common = (LinearLayout) view.findViewById(R.id.ranking_common);
        ranking_sale_volume = (LinearLayout) view.findViewById(R.id.ranking_sale_volume);


        adapter = new ZbYouHuiAdapter(totalList, context);
        zb_youhui_PullToRefreshListView.setAdapter(adapter);
        initOkHttp(pg);
        zb_youhui_PullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        zb_youhui_PullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ZbYouHuiBean.ListBean listBean = totalList.get(position - 1);
                String quan_url = listBean.getQuan_url();
                String img_url = listBean.getImg_url();
                String site_url = listBean.getSite_url();

                String discount_info = listBean.getDiscount_info();
                String site_name = listBean.getSite_name();
                String taken_num = listBean.getTaken_num();
                String left_num = listBean.getLeft_num();
                String end_time = listBean.getEnd_time();
                String discount_arg = listBean.getDiscount_arg();
                String discount_range = listBean.getDiscount_range();
                String id1 = listBean.getId();
                Intent intent = new Intent(getActivity(), ZbYouHuiDetailsActivity.class);

                intent.putExtra("id1",id1);
                intent.putExtra("discount_info", discount_info);
                intent.putExtra("site_name", site_name);
                intent.putExtra("taken_num", taken_num);
                intent.putExtra("end_time", end_time);
                intent.putExtra("left_num", left_num);
                intent.putExtra("discount_arg", discount_arg);
                intent.putExtra("discount_range", discount_range);

                intent.putExtra("site_url", site_url);
                intent.putExtra("img_url", img_url);
                intent.putExtra("quan_url", quan_url);
                startActivity(intent);
            }
        });
        zb_youhui_PullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pg = 1;
                initOkHttp(pg);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pg += 1;
                initOkHttp(pg);
            }
        });
        
        
        
        initPopClick();
        return view;
    }

    private void initPopClick() {
        ranking_common.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YouHui_ClassPop pop = new YouHui_ClassPop(getActivity());
                pop.showPricePopup(v);
            }
        });

        ranking_sale_volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YouHui_SitesPop pop = new YouHui_SitesPop(getActivity());
                pop.showPricePopup(v);
            }
        });
    }

    private void initOkHttp(int pg) {
        OkHttpClient okhhttp = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://app.gwdang.com/interface/quan?pg=" + pg + "&ps=20&format=json&current_time=1477113443")
                .build();
        Call call = okhhttp.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(Response response) throws IOException {
                totalList.clear();
                String string = response.body().string();
                if (!TextUtils.isEmpty(string)) {
                    Gson gson = new Gson();
                    ZbYouHuiBean zbChuXiaoBean = gson.fromJson(string, ZbYouHuiBean.class);
                    List<ZbYouHuiBean.ListBean> list = zbChuXiaoBean.getList();
                    totalList.addAll(list);
                    zb_youhui_PullToRefreshListView.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                            zb_youhui_PullToRefreshListView.onRefreshComplete();
                        }
                    });
                }
            }
        });
    }

}

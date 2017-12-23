package com.example.administrator.demo1.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.adapter.Search_BiJiaAdapter;
import com.example.administrator.demo1.adapter.SyHaoYangMaoGridAdapter;
import com.example.administrator.demo1.adapter.XListAdapter;
import com.example.administrator.demo1.bean.Search_BiJiaBean;
import com.example.administrator.demo1.bean.SyHaoYangMaoBean;
import com.example.administrator.demo1.bean.XGoods;
import com.example.administrator.demo1.popwindow.XBrandsPop;
import com.example.administrator.demo1.popwindow.XPricePop;
import com.example.administrator.demo1.popwindow.XShopPop;
import com.example.administrator.demo1.popwindow.XclassesPop;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
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
public class Search_BiJiaFragment extends Fragment {

    PullToRefreshListView bijia_listView;
    List<Search_BiJiaBean.ProductBean> totalList = new ArrayList<>();
    private Search_BiJiaAdapter adapter;
    String keyword;
    Context context;
    String path;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search__bi_jia, container, false);
        bijia_listView = (PullToRefreshListView) view.findViewById(R.id.bijia_listView);

        Bundle bundle = getArguments();
        if (bundle != null) {
            keyword = bundle.getString("keyword");
        }
        path = "http://app.gwdang.com/app/search?pg=3&s_product=" + keyword + "&ps=20&format=json&img_width=344&version=2";


        //adapter填充数据
        initAdapter();
        //Okhttp请求数据
        initOkHttp();


        bijia_listView.setMode(PullToRefreshBase.Mode.BOTH);
        return view;
    }

    private void initAdapter() {

        adapter = new Search_BiJiaAdapter(context,totalList);
        bijia_listView.setAdapter(adapter);
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
                    Search_BiJiaBean search_biJiaBean = gson.fromJson(string, Search_BiJiaBean.class);
                    List<Search_BiJiaBean.ProductBean> list = search_biJiaBean.getProduct();
                    totalList.addAll(list);
                    bijia_listView.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                }

            }
        });
    }


}

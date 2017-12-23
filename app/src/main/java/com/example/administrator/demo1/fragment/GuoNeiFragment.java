package com.example.administrator.demo1.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.activity.GuoNeiDetailActivity;
import com.example.administrator.demo1.adapter.XListAdapter;
import com.example.administrator.demo1.bean.XGoods;
import com.example.administrator.demo1.cans.Cans;
import com.example.administrator.demo1.popwindow.XBrandsPop;
import com.example.administrator.demo1.popwindow.XPricePop;
import com.example.administrator.demo1.popwindow.XShopPop;
import com.example.administrator.demo1.popwindow.XclassesPop;
import com.example.administrator.demo1.task.JsonTask;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuoNeiFragment extends Fragment implements JsonTask.JsonCallBack{
    //分类
    private LinearLayout ranking_common;
    //商城
    private LinearLayout ranking_sale_volume;
    //品牌
    private LinearLayout ranking_brand;
    //价格
    private LinearLayout ranking_price;
    private List<XGoods> beanList = new ArrayList<>();
    private XListAdapter adapter ;
    private int pager = 0;
    private boolean isLoading = false;
    Context context;
    PullToRefreshListView listView;

    public GuoNeiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_guo_nei, container, false);
        initView(view);
        initListView(view);
        initOnCLick();
        ListViewClick();
        return view;
    }
    private void initView(final View view) {
        ranking_common = (LinearLayout) view.findViewById(R.id.ranking_common);
        ranking_brand = (LinearLayout) view.findViewById(R.id.ranking_brand);
        ranking_price = (LinearLayout) view.findViewById(R.id.ranking_price);
        ranking_sale_volume = (LinearLayout) view.findViewById(R.id.ranking_sale_volume);
    }
    private void initListView(View view) {
        listView = (PullToRefreshListView) view.findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                isLoading = false;
                pager = 0;

                initDoadlow();
                isLoading = true;
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (isLoading) {
                    initDoadLowTwo();
                    isLoading = false;
                } else {
                    initDoadLowThree();
                    isLoading = true;
                }

            }
        });
        adapter = new XListAdapter(context, beanList);
        listView.setAdapter(adapter);
        initDoadlow();
    }
    private void initDoadlow() {
        new JsonTask(this).execute(Cans.getGuoNeiPath(pager));
    }
    public void initDoadLowTwo(){
        new JsonTask(this).execute(Cans.GuoNeiSecondPage());
    }
    public void initDoadLowThree(){
        new JsonTask(this).execute(Cans.GuoNeiThridPage());
    }
    @Override
    public void jsonCallBack(List<XGoods> list) {
        beanList.clear();
        beanList.addAll(list);
        adapter.notifyDataSetChanged();
        listView.onRefreshComplete();
    }

    private void initOnCLick() {
        ranking_common.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XclassesPop popup = new XclassesPop(getActivity());
                popup.showPricePopup(v);
                Log.e(TAG, "onClick: ==============hhahhahahahhahahh==" );
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
    public void ListViewClick(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, GuoNeiDetailActivity.class);
                String pic_url = beanList.get(position-1).getPic_url();
                String title = beanList.get(position-1).getTitle();
                String price_info = beanList.get(position-1).getPrice_info();
                String brand = beanList.get(position-1).getBrand();
                String site_name = beanList.get(position-1).getSite_name();
                String class_name = beanList.get(position-1).getClass_name();
                String update_time = beanList.get(position-1).getUpdate_time();
                String keyword = beanList.get(position-1).getKeyword();
                String site_id = beanList.get(position - 1).getSite_id();
                String class_id = beanList.get(position-1).getClass_id();
                String url = beanList.get(position - 1).getUrl();

                Bundle bundle = new Bundle();
                bundle.putString("pic_url", pic_url);
                bundle.putString("title", title);
                bundle.putString("price_info", price_info);
                bundle.putString("brand",brand);
                bundle.putString("site_name", site_name);
                bundle.putString("class_name",class_name);
                bundle.putString("update_time",update_time);
                bundle.putString("keyword",keyword);
                bundle.putString("site_id",site_id);
                bundle.putString("class_id",class_id);
                bundle.putString("url",url);

               intent.putExtra("info",bundle);
                startActivity(intent);
                Log.e(TAG, "onItemClick: ================jjjjjjjj===" );
            }
        });
    }
}

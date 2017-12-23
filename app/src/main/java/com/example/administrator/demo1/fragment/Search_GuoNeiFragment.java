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

import android.widget.ListView;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.activity.GuoNeiDetailActivity;

import com.example.administrator.demo1.adapter.XListAdapter;

import com.example.administrator.demo1.bean.XGoods;

import com.example.administrator.demo1.task.JsonTask;


import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search_GuoNeiFragment extends Fragment implements JsonTask.JsonCallBack {


    private String keyword;
    private String path;

    private List<XGoods> beanList = new ArrayList<>();
    private XListAdapter adapter;
    private int pager = 0;
    private boolean isLoading = false;
    Context context;
    PullToRefreshListView search_listView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search__guo_nei, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            keyword = bundle.getString("keyword");
        }
        path = "http://app.gwdang.com/app/price_zhi?ps=20&is_haitao=0&format=json&img_width=344&keyword=" + keyword + "&version=2";

        initListView(view);

        ListViewClick();
        return view;
    }

    private void initListView(View view) {
        search_listView = (PullToRefreshListView) view.findViewById(R.id.search_listView);
        search_listView.setMode(PullToRefreshBase.Mode.BOTH);
        search_listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                isLoading = false;
                pager = 0;

                initDoadlow();
                isLoading = true;
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
               /* if (isLoading) {
                    initDoadLowTwo();
                    isLoading = false;
                } else {
                    initDoadLowThree();
                    isLoading = true;
                }
*/
            }
        });
        adapter = new XListAdapter(context, beanList);
        search_listView.setAdapter(adapter);
        initDoadlow();
    }

    private void initDoadlow() {
        new JsonTask(this).execute(path);
    }

    //    public void initDoadLowTwo(){
//        new JsonTask(this).execute(Cans.GuoNeiSecondPage());
//    }
//    public void initDoadLowThree(){
//        new JsonTask(this).execute(Cans.GuoNeiThridPage());
//    }
    @Override
    public void jsonCallBack(List<XGoods> list) {
        beanList.clear();
        beanList.addAll(list);
        adapter.notifyDataSetChanged();
        search_listView.onRefreshComplete();
    }


    public void ListViewClick() {
        search_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, GuoNeiDetailActivity.class);
                String pic_url = beanList.get(position - 1).getPic_url();
                String title = beanList.get(position - 1).getTitle();
                String price_info = beanList.get(position - 1).getPrice_info();
                String brand = beanList.get(position - 1).getBrand();
                String site_name = beanList.get(position - 1).getSite_name();
                String class_name = beanList.get(position - 1).getClass_name();
                String update_time = beanList.get(position - 1).getUpdate_time();
                String keyword = beanList.get(position - 1).getKeyword();
                String site_id = beanList.get(position - 1).getSite_id();
                String class_id = beanList.get(position - 1).getClass_id();
                String url = beanList.get(position - 1).getUrl();

                Bundle bundle = new Bundle();
                bundle.putString("pic_url", pic_url);
                bundle.putString("title", title);
                bundle.putString("price_info", price_info);
                bundle.putString("brand", brand);
                bundle.putString("site_name", site_name);
                bundle.putString("class_name", class_name);
                bundle.putString("update_time", update_time);
                bundle.putString("keyword", keyword);
                bundle.putString("site_id", site_id);
                bundle.putString("class_id", class_id);
                bundle.putString("url", url);

                intent.putExtra("info", bundle);
                startActivity(intent);
                Log.e(TAG, "onItemClick: ================jjjjjjjj===");
            }
        });
    }
}

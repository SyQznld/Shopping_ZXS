package com.example.administrator.demo1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.adapter.XListAdapter;
import com.example.administrator.demo1.bean.XGoods;
import com.example.administrator.demo1.cans.Cans;
import com.example.administrator.demo1.myinterface.MyInterface;
import com.example.administrator.demo1.task.PopJsonTask;
import com.example.administrator.demo1.utils.ParseUtils;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class BiaoQianActivity extends AppCompatActivity {
     PullToRefreshListView listView_biaoqian;
    private XListAdapter xListAdapter;
    String key;
    private List<XGoods> goodsList = new ArrayList<>();
    private TextView biaoqian_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biao_qian);
        biaoqian_tv = (TextView) findViewById(R.id.biaoqian_tv);
        listView_biaoqian = (PullToRefreshListView) findViewById(R.id.listView_biaoqian);
        key = getIntent().getStringExtra("key");
        biaoqian_tv.setText("标签:"+key);
        Log.e("haha", "onCreate: ================================================" + key);

        ListViewClick();
        shopClick();
        ClassClick();
        BrandClick();
        KeyWordClick();
        initListView();
    }

    private void initListView() {
        xListAdapter = new XListAdapter(this,goodsList);
        listView_biaoqian.setAdapter(xListAdapter);
        new PopJsonTask(new MyInterface.JsonCallBack() {
            @Override
            public void jsonCallBack(String json) {
                if (!TextUtils.isEmpty(json)) {
                    List<XGoods> list = ParseUtils.getJson(json);
                    if (list != null && list.size() > 0) {
                        goodsList.clear();
                        goodsList.addAll(list);
                        xListAdapter.notifyDataSetChanged();
                    }
                }
            }
        }).execute(Cans.DetailPage(key));
    }
    public void ListViewClick(){
        listView_biaoqian.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BiaoQianActivity.this, GuoNeiDetailActivity.class);
                String pic_url = goodsList.get(position-1).getPic_url();
                String title = goodsList.get(position-1).getTitle();
                String price_info = goodsList.get(position-1).getPrice_info();
                String brand = goodsList.get(position-1).getBrand();
                String site_name = goodsList.get(position-1).getSite_name();
                String class_name = goodsList.get(position-1).getClass_name();
                String update_time = goodsList.get(position-1).getUpdate_time();
                String keyword = goodsList.get(position-1).getKeyword();

                Bundle bundle = new Bundle();
                bundle.putString("pic_url", pic_url);
                bundle.putString("title", title);
                bundle.putString("price_info", price_info);
                bundle.putString("brand",brand);
                bundle.putString("site_name", site_name);
                bundle.putString("class_name",class_name);
                bundle.putString("update_time",update_time);
                bundle.putString("keyword",keyword);
                intent.putExtra("info",bundle);
                startActivity(intent);
                Log.e(TAG, "onItemClick: ================llllllllllllllllllll===" );
            }
        });
    }
    public void shopClick(){
        xListAdapter = new XListAdapter(this,goodsList);
        listView_biaoqian.setAdapter(xListAdapter);
        new PopJsonTask(new MyInterface.JsonCallBack() {
            @Override
            public void jsonCallBack(String json) {
                if (!TextUtils.isEmpty(json)) {
                    List<XGoods> list = ParseUtils.getJson(json);
                    if (list != null && list.size() > 0) {
                        goodsList.clear();
                        goodsList.addAll(list);
                        xListAdapter.notifyDataSetChanged();
                    }
                }
            }
        }).execute(Cans.DetailShop(key));
    }
    public void ClassClick(){
        xListAdapter = new XListAdapter(this,goodsList);
        listView_biaoqian.setAdapter(xListAdapter);
        new PopJsonTask(new MyInterface.JsonCallBack() {
            @Override
            public void jsonCallBack(String json) {
                if (!TextUtils.isEmpty(json)) {
                    List<XGoods> list = ParseUtils.getJson(json);
                    if (list != null && list.size() > 0) {
                        goodsList.clear();
                        goodsList.addAll(list);
                        xListAdapter.notifyDataSetChanged();
                    }
                }
            }
        }).execute(Cans.DetailClass(key));
    }
    public void BrandClick(){
        xListAdapter = new XListAdapter(this,goodsList);
        listView_biaoqian.setAdapter(xListAdapter);
        new PopJsonTask(new MyInterface.JsonCallBack() {
            @Override
            public void jsonCallBack(String json) {
                if (!TextUtils.isEmpty(json)) {
                    List<XGoods> list = ParseUtils.getJson(json);
                    if (list != null && list.size() > 0) {
                        goodsList.clear();
                        goodsList.addAll(list);
                        xListAdapter.notifyDataSetChanged();
                    }
                }
            }
        }).execute(Cans.DetailBrand(key));
    }
    public void KeyWordClick(){
        xListAdapter = new XListAdapter(this,goodsList);
        listView_biaoqian.setAdapter(xListAdapter);
        new PopJsonTask(new MyInterface.JsonCallBack() {
            @Override
            public void jsonCallBack(String json) {
                if (!TextUtils.isEmpty(json)) {
                    List<XGoods> list = ParseUtils.getJson(json);
                    if (list != null && list.size() > 0) {
                        goodsList.clear();
                        goodsList.addAll(list);
                        xListAdapter.notifyDataSetChanged();
                    }
                }
            }
        }).execute(Cans.DetailKeyWord(key));
    }
}

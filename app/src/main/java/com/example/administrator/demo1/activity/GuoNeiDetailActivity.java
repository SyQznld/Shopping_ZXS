package com.example.administrator.demo1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.demo1.OnKeyShare;
import com.example.administrator.demo1.R;
import com.example.administrator.demo1.adapter.GuoNeiDetailAdapter;
import com.example.administrator.demo1.bean.XGoods;
import com.example.administrator.demo1.cans.Cans;
import com.example.administrator.demo1.myinterface.MyInterface;
import com.example.administrator.demo1.popwindow.GuoNeiDetailPop;
import com.example.administrator.demo1.popwindow.HaoYangMaoPop;
import com.example.administrator.demo1.task.PopJsonTask;
import com.example.administrator.demo1.utils.ParseUtils;

import java.util.ArrayList;
import java.util.List;

public class GuoNeiDetailActivity extends AppCompatActivity {
    ImageView guonei_detail_back;
    ImageView guonei_detail_more;
    ImageView guonei_detail_pic;
    ImageView guonei_detail_buy;
    TextView guonei_detail_siteName;
    TextView guonei_detail_time;
    TextView guonei_detail_title;
    TextView guonei_detail_price;
    TextView label_siteName;
    TextView label_classify;
    TextView label_brand;
    TextView label_kayword;
    ListView listView_more_goods;
    String keyword;
    String site_id;
    String class_id;
    String brand;
    private String url;
    RelativeLayout relativelayout_more;
    LinearLayout guonei_detail_share, guonei_detail_collect;

    boolean isLogin = true;

    private List<XGoods> goodsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guo_nei_detail);
        initView();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("info");
        String title = bundle.getString("title");
        String pic_url = bundle.getString("pic_url");
        String price_info = bundle.getString("price_info");
        brand = bundle.getString("brand");
        String site_name = bundle.getString("site_name");
        String class_name = bundle.getString("class_name");
        String update_time = bundle.getString("update_time");
        site_id = bundle.getString("site_id");
        class_id = bundle.getString("class_id");
        keyword = bundle.getString("keyword");
        Log.e("tag", "onCreate:===================================== " + pic_url);
        guonei_detail_title.setText(title);
        Glide.with(this).load(pic_url).placeholder(R.mipmap.ic_launcher).into(guonei_detail_pic);
        guonei_detail_price.setText(price_info);
        label_brand.setText("品牌：" + brand);
        label_siteName.setText("商城：" + site_name);
        label_classify.setText("分类：" + class_name);
        guonei_detail_time.setText(update_time);
        label_kayword.setText("关键词：" + keyword);
        initListView();
        LabelClick();
        RelativeLayoutClick();
        listView_more_goods.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String keyword1 = goodsList.get(position).getKeyword();
                String brand1 = goodsList.get(position).getBrand();
                String title1 = goodsList.get(position).getTitle();
                String pic_url1 = goodsList.get(position).getPic_url();
                url = goodsList.get(position).getUrl();
                String price_info1 = goodsList.get(position).getPrice_info();
                String site_name1 = goodsList.get(position).getSite_name();
                String class_name1 = goodsList.get(position).getClass_name();
                String update_time1 = goodsList.get(position).getUpdate_time();
                guonei_detail_title.setText(title1);
                Glide.with(GuoNeiDetailActivity.this).load(pic_url1).placeholder(R.mipmap.ic_launcher).into(guonei_detail_pic);
                guonei_detail_price.setText(price_info1);
                label_brand.setText("品牌：" + brand1);
                label_siteName.setText("商城：" + site_name1);
                label_classify.setText("分类：" + class_name1);
                guonei_detail_time.setText(update_time1);
                label_kayword.setText("关键词：" + keyword1);


            }
        });
        //头尾布局点击事件
        initClick();
    }

    private void initClick() {
        guonei_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        guonei_detail_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuoNeiDetailPop addPopWindow = new GuoNeiDetailPop(GuoNeiDetailActivity.this);
                addPopWindow.showPopupWindow(guonei_detail_more);


                addPopWindow.getContentView().findViewById(R.id.haoyangmao_pop_home).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

                addPopWindow.getContentView().findViewById(R.id.haoyangmao_pop_share).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        OnKeyShare.showShare(GuoNeiDetailActivity.this);
                    }
                });

                addPopWindow.getContentView().findViewById(R.id.haoyangmao_pop_shoucang).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(GuoNeiDetailActivity.this, SyLoginActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });


        guonei_detail_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnKeyShare.showShare(GuoNeiDetailActivity.this);
            }
        });

        guonei_detail_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLogin) {
                    Intent intent = new Intent(GuoNeiDetailActivity.this, SyLoginActivity.class);
                    startActivity(intent);
                    isLogin = true;
                } else if (isLogin) {
                    Intent intent = new Intent(GuoNeiDetailActivity.this, ZbMyCollectActivity.class);
                    startActivity(intent);
                }
            }
        });

        guonei_detail_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuoNeiDetailActivity.this, SyHaoYangMaoDetailActivity.class);
                intent.putExtra("url", url);
                startActivity(intent );

            }
        });
    }

    private void initListView() {
        final GuoNeiDetailAdapter gndAdapter = new GuoNeiDetailAdapter(goodsList, this);
        listView_more_goods.setAdapter(gndAdapter);
        new PopJsonTask(new MyInterface.JsonCallBack() {
            @Override
            public void jsonCallBack(String json) {
                if (!TextUtils.isEmpty(json)) {
                    List<XGoods> list = ParseUtils.getJson(json);
                    if (list != null && list.size() > 0) {
                        goodsList.addAll(list.subList(0, 3));
                        gndAdapter.notifyDataSetChanged();
                    }
                }
            }
        }).execute(Cans.DetailPage(keyword));
    }

    private void initView() {
        guonei_detail_back = (ImageView) findViewById(R.id.guonei_detail_back);
        guonei_detail_more = (ImageView) findViewById(R.id.guonei_detail_more);
        guonei_detail_pic = (ImageView) findViewById(R.id.guonei_detail_pic);
        guonei_detail_siteName = (TextView) findViewById(R.id.guonei_detail_siteName);
        guonei_detail_time = (TextView) findViewById(R.id.guonei_detail_time);
        guonei_detail_title = (TextView) findViewById(R.id.guonei_detail_title);
        guonei_detail_price = (TextView) findViewById(R.id.guonei_detail_price);
        label_siteName = (TextView) findViewById(R.id.label_siteName);
        label_classify = (TextView) findViewById(R.id.label_classify);
        label_brand = (TextView) findViewById(R.id.label_brand);
        guonei_detail_buy = (ImageView) findViewById(R.id.guonei_detail_buy);
        label_kayword = (TextView) findViewById(R.id.label_kayword);
        listView_more_goods = (ListView) findViewById(R.id.listView_more_goods);
        relativelayout_more = (RelativeLayout) findViewById(R.id.relativelayout_more);

        guonei_detail_share = (LinearLayout) findViewById(R.id.guonei_detail_share);
        guonei_detail_collect = (LinearLayout) findViewById(R.id.guonei_detail_collect);

    }

    public void RelativeLayoutClick() {
        relativelayout_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuoNeiDetailActivity.this, BiaoQianActivity.class);
                intent.putExtra("key", keyword);
                startActivity(intent);
            }
        });
    }

    public void LabelClick() {
        label_siteName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuoNeiDetailActivity.this, BiaoQianActivity.class);
                intent.putExtra("key", site_id);
                startActivity(intent);
            }
        });
        label_classify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuoNeiDetailActivity.this, BiaoQianActivity.class);
                intent.putExtra("key", class_id);
                startActivity(intent);
            }
        });
        label_kayword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuoNeiDetailActivity.this, BiaoQianActivity.class);
                intent.putExtra("key", keyword);
                startActivity(intent);
            }
        });
        label_brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuoNeiDetailActivity.this, BiaoQianActivity.class);
                intent.putExtra("key", brand);
                startActivity(intent);
            }
        });
    }
}

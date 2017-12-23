package com.example.administrator.demo1.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.adapter.ZbMyYouHuiQuanAdapter;
import com.example.administrator.demo1.bean.DaoMaster;
import com.example.administrator.demo1.bean.DaoSession;
import com.example.administrator.demo1.bean.ZbYouHuiJuanBean;
import com.example.administrator.demo1.bean.ZbYouHuiJuanBeanDao;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZbMyCollectActivity extends AppCompatActivity {
private  List<ZbYouHuiJuanBean> totalList = new ArrayList<>();
    ZbMyYouHuiQuanAdapter adapter;
    QueryBuilder<ZbYouHuiJuanBean> zbYouHuiJuanBeanQueryBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zb_my_collect);
        SharedPreferences denglu = getSharedPreferences("Denglu", MODE_PRIVATE);
        String registerName = denglu.getString("registerName", null);
        DaoSession youhuiquan = DaoMaster.newDevSession(this,"youhuiquan");
        initView();
        if (!TextUtils.isEmpty(registerName)){
        ZbYouHuiJuanBeanDao zbYouHuiJuanBeanDao = youhuiquan.getZbYouHuiJuanBeanDao();
         zbYouHuiJuanBeanQueryBuilder = zbYouHuiJuanBeanDao.queryBuilder();
        Query<ZbYouHuiJuanBean> build = zbYouHuiJuanBeanQueryBuilder.where(ZbYouHuiJuanBeanDao.Properties.RegisterName.eq(registerName)).build();
            List<ZbYouHuiJuanBean> list = build.list();
            if (list.size()==0){
            Toast.makeText(ZbMyCollectActivity.this, "暂时没有收藏", Toast.LENGTH_SHORT).show();
        }else {
                totalList.addAll(list);
                adapter.notifyDataSetChanged();
        }
    }else {
            Intent intent = new Intent(this,SyLoginActivity.class);
            startActivity(intent);
        }
}
     public  void  initView(){
         com.handmark.pulltorefresh.library.PullToRefreshListView pullToRefreshListView= (com.handmark.pulltorefresh.library.PullToRefreshListView) findViewById(R.id.zbmyyouhuiquanRefreshListView);
         adapter= new ZbMyYouHuiQuanAdapter(totalList,this);
         pullToRefreshListView.setAdapter(adapter);
         pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                 ZbYouHuiJuanBean zbYouHuiJuanBean = totalList.get(position - 1);
                 if (zbYouHuiJuanBean!=null){
                     final  String site_url = zbYouHuiJuanBean.getSite_url();
                     final   String img_url = zbYouHuiJuanBean.getImg_url();
                     final String discount_info = zbYouHuiJuanBean.getDiscount_info();
                     final String site_name = zbYouHuiJuanBean.getSite_name();
                     final String taken_num = zbYouHuiJuanBean.getTaken_num();
                     final String left_num = zbYouHuiJuanBean.getLeft_num();
                     final  String end_time = zbYouHuiJuanBean.getEnd_time();
                     final String discount_arg = zbYouHuiJuanBean.getDiscount_arg();
                     final String discount_range = zbYouHuiJuanBean.getDiscount_range();
                     final long id1 = zbYouHuiJuanBean.getId();


                     AlertDialog.Builder builder = new AlertDialog.Builder(ZbMyCollectActivity.this);
                     builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             Intent intent = new Intent(ZbMyCollectActivity.this,ZbWebViewActivity.class);
                             intent.putExtra("url",site_url);
                             startActivity(intent);
                         }
                     });
                     builder.setNegativeButton("查看详情", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             Intent intent1 = new Intent(ZbMyCollectActivity.this,ZbYouHuiDetailsActivity.class);
                             intent1.putExtra("id1",id1+"");
                             intent1.putExtra("discount_info", discount_info);
                             intent1.putExtra("site_name", site_name);
                             intent1.putExtra("taken_num", taken_num);
                             intent1.putExtra("end_time", end_time);
                             intent1.putExtra("left_num", left_num);
                             intent1.putExtra("discount_arg", discount_arg);
                             intent1.putExtra("discount_range", discount_range);

                             intent1.putExtra("site_url", site_url);
                             intent1.putExtra("img_url", img_url);
                           intent1.putExtra("quan_url","wu");
                             startActivity(intent1);
                         }
                     });
                     builder.show();
                 }
             }
         });
         pullToRefreshListView.setOnLongClickListener(new View.OnLongClickListener() {
             @Override
             public boolean onLongClick(View v) {
                 return true;
             }
         });
     }
}

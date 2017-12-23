package com.example.administrator.demo1.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.bean.DaoMaster;
import com.example.administrator.demo1.bean.DaoSession;
import com.example.administrator.demo1.bean.ZbYouHuiJuanBean;
import com.example.administrator.demo1.bean.ZbYouHuiJuanBeanDao;
import com.squareup.picasso.Picasso;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class ZbYouHuiDetailsActivity extends AppCompatActivity {
    ImageView zb_youdetailImg;
    Button immediatelytoreceive;
    TextView zb_youhuidetailTitle, zb_shoping, zb_youhuidetail_youxiaoqi, zb_youhuidetail_shengyu,
            zb_youhuidetail_discount_arg, zb_youhuidetail_discount_discount_range;
    String site_url, img_url, quan_url, taken_num, left_num;
    ZbYouHuiJuanBeanDao zbYouHuiJuanBeanDao;
    ZbYouHuiJuanBean zjb;
    SharedPreferences denglu;
    private List<ZbYouHuiJuanBean> list = new ArrayList<>();
    private int tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zb_you_hui_details);
        Intent intent = getIntent();
        DaoSession youhuiquan = DaoMaster.newDevSession(this, "youhuiquan");
        zbYouHuiJuanBeanDao = youhuiquan.getZbYouHuiJuanBeanDao();
        denglu = getSharedPreferences("Denglu", MODE_PRIVATE);
        initView();
        initDate(intent);
    }

    String discount_info;
    String site_name;
    String end_time;
    String discount_arg;
    String discount_range;
    String id1;
    QueryBuilder<ZbYouHuiJuanBean> where1;

    private void initDate(Intent intent) {
        //
        discount_info = intent.getStringExtra("discount_info");
        site_name = intent.getStringExtra("site_name");
        taken_num = intent.getStringExtra("taken_num");
        end_time = intent.getStringExtra("end_time");
        left_num = intent.getStringExtra("left_num");
        discount_arg = intent.getStringExtra("discount_arg");
        discount_range = intent.getStringExtra("discount_range");
        id1 = intent.getStringExtra("id1");
//地址
        site_url = intent.getStringExtra("site_url");
        img_url = intent.getStringExtra("img_url");
        quan_url = intent.getStringExtra("quan_url");
        String registerName = denglu.getString("registerName", "");
        tag = denglu.getInt("tag", 0);
        //更新Ui
        reUI();
        zjb = new ZbYouHuiJuanBean(Integer.parseInt(id1), img_url, discount_info, discount_arg,
                end_time, site_url, registerName, site_url, site_name, taken_num, left_num, discount_range);
        //数据库查找
        if (!TextUtils.isEmpty(registerName)) {
            QueryBuilder<ZbYouHuiJuanBean> zbYouHuiJuanBeanQueryBuilder = zbYouHuiJuanBeanDao.queryBuilder();
            QueryBuilder<ZbYouHuiJuanBean> where = zbYouHuiJuanBeanQueryBuilder.where(ZbYouHuiJuanBeanDao.Properties.RegisterName.in(registerName));
            QueryBuilder<ZbYouHuiJuanBean> where2 = where.where(ZbYouHuiJuanBeanDao.Properties.Id.eq(Integer.parseInt(id1)));
            List<ZbYouHuiJuanBean> list = where2.build().list();
            if (list.size() > 0) {
                immediatelytoreceive.setText("已经领取");
                immediatelytoreceive.setClickable(false);
            } else {
                immediatelytoreceive.setText("点击领取");
                //判断是否  有自己的领券中心
                if (TextUtils.isEmpty(quan_url)) {
                    zbYouHuiJuanBeanDao.insertOrReplaceInTx(zjb);
                    immediatelytoreceive.setClickable(false);
                }else {
                    Intent intent1 = new Intent(ZbYouHuiDetailsActivity.this, ZbWebViewActivity.class);
                    intent1.putExtra("url", site_url);
                    ZbYouHuiDetailsActivity.this.startActivity(intent1);
                }
            }
        }
    }

    private void initView() {
        immediatelytoreceive = (Button) findViewById(R.id.immediatelytoreceive);
        zb_youdetailImg = (ImageView) findViewById(R.id.zb_youdetailImg);
        zb_youhuidetailTitle = (TextView) findViewById(R.id.zb_youhuidetailTitle);
        zb_shoping = (TextView) findViewById(R.id.zb_shoping);
        zb_youhuidetail_youxiaoqi = (TextView) findViewById(R.id.zb_youhuidetail_youxiaoqi);
        zb_youhuidetail_shengyu = (TextView) findViewById(R.id.zb_youhuidetail_shengyu);
        zb_youhuidetail_discount_arg = (TextView) findViewById(R.id.zb_youhuidetail_discount_arg);
        zb_youhuidetail_discount_discount_range = (TextView) findViewById(R.id.zb_youhuidetail_discount_discount_range);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zb_youhui_goShopping:
                Intent intent = new Intent(ZbYouHuiDetailsActivity.this, ZbWebViewActivity.class);
                intent.putExtra("url", site_url);
                ZbYouHuiDetailsActivity.this.startActivity(intent);
                break;
            case R.id.immediatelytoreceive:
                if (!TextUtils.isEmpty(quan_url)) {
                    Intent intent1 = new Intent(ZbYouHuiDetailsActivity.this, ZbWebViewActivity.class);
                    intent1.putExtra("url", quan_url);
                    ZbYouHuiDetailsActivity.this.startActivity(intent1);
                } else {
                    if (tag == 1) {
                        immediatelytoreceive.setText("已经领取");
                        zb_youhuidetail_shengyu.setText("剩余张数: " + (Integer.parseInt(left_num) - 1) + "(已有" + (Integer.parseInt(taken_num) + 1) + "人领取)");
                        Toast.makeText(ZbYouHuiDetailsActivity.this, "领取成功", Toast.LENGTH_SHORT).show();
                        //数据库添加;
                        zbYouHuiJuanBeanDao.insertOrReplaceInTx(zjb);
                    } else {
                        Intent intent1 = new Intent(ZbYouHuiDetailsActivity.this, SyLoginActivity.class);
                        startActivityForResult(intent1, 100);
                    }
                }
                break;
        }
    }

    public void reUI() {
        Picasso.with(this).load(img_url).into(zb_youdetailImg);
        zb_youhuidetailTitle.setText(discount_info);
        zb_shoping.setText(site_name);
        int i = end_time.indexOf(" ");
        zb_youhuidetail_youxiaoqi.setText("有效期至: " + end_time.substring(0, i));
        zb_youhuidetail_shengyu.setText("剩余张数: " + left_num + "(已有" + taken_num + "人领取)");
        zb_youhuidetail_discount_arg.setText("优惠规格: " + discount_arg);
        zb_youhuidetail_discount_discount_range.setText("使用范围: " + discount_range);
    }
}

package com.example.administrator.demo1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.demo1.activity.SySettingActivity;
import com.example.administrator.demo1.activity.ZbSearchActivity;
import com.example.administrator.demo1.adapter.MainAdapter;
import com.example.administrator.demo1.fragment.CuXiaoFragment;
import com.example.administrator.demo1.fragment.GuoNeiFragment;
import com.example.administrator.demo1.fragment.HaiTaoFragment;
import com.example.administrator.demo1.fragment.SyHaoYangMaoFragment;
import com.example.administrator.demo1.fragment.ZbYouHuiFragment;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    MainAdapter adapter;
    RelativeLayout gou;
    TextView tv1;
    List<View> list = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();
    String[] titles = {"国内", "海淘", "薅羊毛", "促销活动", "优惠券"};
    int[] image = {R.drawable.zbguonei, R.drawable.zbhaitao, R.drawable.zbhaoyangmao,
            R.drawable.zbchuxiaohuodong, R.drawable.zbyouhuijuan};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setSelectedTabIndicatorHeight(0);
        gou = (RelativeLayout) findViewById(R.id.gou_rl);

        fragmentList.add(new GuoNeiFragment());
        fragmentList.add(new HaiTaoFragment());
        fragmentList.add(new SyHaoYangMaoFragment());
        fragmentList.add(new CuXiaoFragment());
        fragmentList.add(new ZbYouHuiFragment());


        viewPager.setAdapter(new MainAdapter(getSupportFragmentManager(), fragmentList, titles));
        //将TabLayout与ViewPager关联    实现联动效果
        tabLayout.setupWithViewPager(viewPager);
        resetLayout();
        initmonitor();
    }
    //监听
    private void initmonitor() {
        gou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ZbSearchActivity.class);
                startActivity(intent);
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                View view = list.get(position);
                TextView textView = (TextView) view.findViewById(R.id.viewpger_textView);
                textView.setTextColor(getResources().getColor(R.color.qingse));
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                View view = list.get(position);
                TextView textView = (TextView) view.findViewById(R.id.viewpger_textView);
                textView.setTextColor(Color.GRAY);
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void resetLayout() {
        for (int i = 0; i < titles.length; i++) {
            tabLayout.getTabAt(i).setCustomView(getTabView(i));
        }
    }
    //tab
    public View getTabView(int position) {
        View v = LayoutInflater.from(this).inflate(R.layout.tab, null);
        TextView tv = (TextView) v.findViewById(R.id.viewpger_textView);
        tv.setText(titles[position]);
        if (position == 0) {
            tv.setTextColor(getResources().getColor(R.color.qingse));
        }
        ImageView iv = (ImageView) v.findViewById(R.id.viewpger_imageView);
        iv.setImageResource(image[position]);
        list.add(v);
        return v;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //  记录时间 判断退出
    private long exitTime;
    private void exit() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(MainActivity.this, "再按一次返回键，退出购物党", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, SySettingActivity.class);
        startActivity(intent);
    }
}

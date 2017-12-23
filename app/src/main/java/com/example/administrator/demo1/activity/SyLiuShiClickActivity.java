package com.example.administrator.demo1.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.adapter.SearchFragmentAdapter;
import com.example.administrator.demo1.fragment.Search_BiJiaFragment;
import com.example.administrator.demo1.fragment.Search_CuXiaoFragment;
import com.example.administrator.demo1.fragment.Search_GuoNeiFragment;
import com.example.administrator.demo1.fragment.Search_HaiTaoFragment;
import com.example.administrator.demo1.fragment.Search_YouHuiQuanFragment;

import java.util.ArrayList;
import java.util.List;

public class SyLiuShiClickActivity extends AppCompatActivity {
    //  http://app.gwdang.com/app/price_zhi?ps=20&is_haitao=0&format=json&img_width=344&keyword=跑鞋&version=2

    ImageView liushiClick_back;
    private String keyword;
    EditText liushiClick_editText;

    ViewPager search_viewPager;
    TabLayout search_tabLayout;
    String[] titles = {"国内", "海淘", "促销", "优惠券", "全网比价"};
    Search_GuoNeiFragment guoNeiFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sy_liu_shi_click);

        Intent intent = getIntent();
        keyword = intent.getStringExtra("keyword");

        //查找控件
        initView();

        tabLayoutAndViewPager();

        Bundle bundle = new Bundle();
        bundle.putString("keyword", keyword);
        guoNeiFragment.setArguments(bundle);
        liushiClick_editText.setText(keyword);

        //控件点击事件
        viewClick();

    }

    private void tabLayoutAndViewPager() {
        List<Fragment> fragmentList = new ArrayList<>();

        guoNeiFragment = new Search_GuoNeiFragment();
        fragmentList.add(guoNeiFragment);
        fragmentList.add(new Search_HaiTaoFragment());
        fragmentList.add(new Search_CuXiaoFragment());
        fragmentList.add(new Search_YouHuiQuanFragment());
        fragmentList.add(new Search_BiJiaFragment());

        search_tabLayout.setSelectedTabIndicatorHeight(0);
        search_tabLayout.setTabTextColors(Color.GRAY, R.color.qingse);

        search_viewPager.setAdapter(new SearchFragmentAdapter(getSupportFragmentManager(), fragmentList, titles));
        search_tabLayout.setupWithViewPager(search_viewPager);


    }

    private void viewClick() {
        //点击返回键  回到上一页面
        liushiClick_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        liushiClick_back = (ImageView) findViewById(R.id.liushiClick_back);
        search_tabLayout = (TabLayout) findViewById(R.id.search_tablayout);
        search_viewPager = (ViewPager) findViewById(R.id.search_viewPager);
        liushiClick_editText = (EditText) findViewById(R.id.liushiClick_editText);
    }
}
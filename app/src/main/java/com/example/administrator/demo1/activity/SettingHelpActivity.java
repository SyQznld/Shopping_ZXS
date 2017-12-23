package com.example.administrator.demo1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.activity.setting_help.Help_BiJiaActivity;
import com.example.administrator.demo1.activity.setting_help.Help_BuyActivity;
import com.example.administrator.demo1.activity.setting_help.Help_DaoHuoActivity;
import com.example.administrator.demo1.activity.setting_help.Help_FindActivity;
import com.example.administrator.demo1.activity.setting_help.Help_JiangJiaActivity;
import com.example.administrator.demo1.activity.setting_help.Help_ShouCangActivity;
import com.example.administrator.demo1.activity.setting_help.Help_XiaDanActivity;
import com.example.administrator.demo1.activity.setting_help.Help_YouHuiJuanActivity;
import com.example.administrator.demo1.activity.setting_help.Help_ZhuCeActivity;

public class SettingHelpActivity extends AppCompatActivity {

    ImageView help_back;
    RelativeLayout help_zhuce, help_find, help_xiadan, help_youhuijuan, help_buy, help_bijia,
            help_shoucang, help_jiangjia, help_daohuo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_help);

        //查找控件
        initView();
        //控件点击事件
        viewClick();
    }

    private void viewClick() {
        help_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        help_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingHelpActivity.this, Help_ZhuCeActivity.class);
                startActivity(intent);
            }
        });
        help_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingHelpActivity.this, Help_FindActivity.class);
                startActivity(intent);
            }
        });
        help_xiadan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingHelpActivity.this, Help_XiaDanActivity.class);
                startActivity(intent);
            }
        });
        help_youhuijuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingHelpActivity.this, Help_YouHuiJuanActivity.class);
                startActivity(intent);
            }
        });
        help_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingHelpActivity.this, Help_BuyActivity.class);
                startActivity(intent);
            }
        });
        help_bijia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingHelpActivity.this, Help_BiJiaActivity.class);
                startActivity(intent);
            }
        });
        help_shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingHelpActivity.this, Help_ShouCangActivity.class);
                startActivity(intent);
            }
        });
        help_jiangjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingHelpActivity.this, Help_JiangJiaActivity.class);
                startActivity(intent);
            }
        });
        help_daohuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingHelpActivity.this, Help_DaoHuoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {

        help_back = (ImageView) findViewById(R.id.help_back);
        help_zhuce = (RelativeLayout) findViewById(R.id.help_zhuce);
        help_find = (RelativeLayout) findViewById(R.id.help_find);
        help_xiadan = (RelativeLayout) findViewById(R.id.help_xiadan);
        help_youhuijuan = (RelativeLayout) findViewById(R.id.help_youhuijuan);
        help_buy = (RelativeLayout) findViewById(R.id.help_buy);
        help_bijia = (RelativeLayout) findViewById(R.id.help_bijia);
        help_shoucang = (RelativeLayout) findViewById(R.id.help_shoucang);
        help_jiangjia = (RelativeLayout) findViewById(R.id.help_jiangjia);
        help_daohuo = (RelativeLayout) findViewById(R.id.help_daohuo);

    }
}

package com.example.administrator.demo1.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.demo1.MyDialog;
import com.example.administrator.demo1.OnKeyShare;
import com.example.administrator.demo1.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class SySettingActivity extends AppCompatActivity implements View.OnClickListener {

    CircleImageView setting_head;
    ImageView setting_back;
    TextView setting_login;
    RelativeLayout setting_shoucang, setting_youhuijuan;
    private RelativeLayout tuichudenglu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sy_setting);
        initView();
    }

    SharedPreferences denglu;

    private void initView() {
        setting_login = (TextView) findViewById(R.id.setting_login);
        setting_youhuijuan = (RelativeLayout) findViewById(R.id.setting_youhuijuan);
        setting_shoucang = (RelativeLayout) findViewById(R.id.setting_shoucang);
        setting_head = (CircleImageView) findViewById(R.id.setting_head);
        setting_back = (ImageView) findViewById(R.id.login_back);
        tuichudenglu = ((RelativeLayout) findViewById(R.id.tuichudenglu));
        denglu = getSharedPreferences("Denglu", MODE_PRIVATE);
        RelativeLayout viewById = (RelativeLayout) findViewById(R.id.setting_tuijian);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnKeyShare.showShare(SySettingActivity.this);
            }
        });
        tuichudenglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MyDialog dialog = new MyDialog(SySettingActivity.this);
                dialog.setMyTitle("");
                dialog.setMyMessage("确定退出?");
                dialog.setYesClick(new MyDialog.YesOnClickListener() {
                    @Override
                    public void yesOnClickListener() {
                        tuichudenglu.setClickable(false);
                        setting_login.setClickable(true);
                        setting_head.setClickable(true);
                        tuichudenglu.setVisibility(View.INVISIBLE);
                        initTuiChu();
                        dialog.dismiss();
                    }
                });
                dialog.setNoClick(new MyDialog.NoOnClickListener() {
                    @Override
                    public void noOnClickListener() {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        String icon = denglu.getString("icon", null);
        String registerName = denglu.getString("registerName", null);
        if (!TextUtils.isEmpty(icon)) {
            Glide.with(this).load(icon).into(setting_head);
            setting_login.setText(registerName);
            setting_login.setClickable(false);
            setting_head.setClickable(false);
            tuichudenglu.setClickable(true);
            tuichudenglu.setVisibility(View.VISIBLE);
        } else {

            setting_login.setClickable(true);
            setting_head.setClickable(true);
            tuichudenglu.setClickable(false);
            tuichudenglu.setVisibility(View.INVISIBLE);
            Picasso.with(this).load(R.mipmap.personal_center_header_default).into(setting_head);
            setting_login.setText("未登录");
        }
    }
    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.setting_login:
                intent = new Intent(SySettingActivity.this, SyLoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.setting_head:
                intent = new Intent(SySettingActivity.this, SyLoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.setting_haoping:
                intent = new Intent(SySettingActivity.this,MyHaoPinActivity.class);
                startActivity(intent);
                break;
            case R.id.setting_shoucang:
                intent= new Intent(SySettingActivity.this,MyShouCangActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.setting_tucao:
                intent = new Intent(SySettingActivity.this,MyTuCaoActivity.class);
                startActivity(intent);
                break;
            case R.id.setting_jiaru:
               intent = new Intent(SySettingActivity.this,JiaRuActivity.class);
                startActivity(intent);
                break;
            case R.id.setting_bangzhu:
                intent = new Intent(SySettingActivity.this, SettingHelpActivity.class);
                startActivity(intent);
                break;
            case R.id.setting_huancun:

                break;

            case R.id.setting_youhuijuan:
                intent = new Intent(SySettingActivity.this, ZbMyCollectActivity.class);
                startActivity(intent);
                break;
        }


    }

    private void initTuiChu() {
        SharedPreferences.Editor edit = denglu.edit();
        edit.putInt("tag", 0);
        edit.putString("icon", null);
        edit.putString("registerName", null);
        edit.commit();
        Picasso.with(this).load(R.mipmap.personal_center_header_default).into(setting_head);
        setting_login.setText("登录/注册");
    }
}

package com.example.administrator.demo1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.demo1.adapter.ZbAdapter;
import com.example.administrator.demo1.fragment.ZbWelcomeSeconFragment;
import com.example.administrator.demo1.fragment.ZbWelocmeFirstFragment;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity implements ZbWelocmeFirstFragment.MyCallBack {
    private SharedPreferences share;
    private  int tag =0;
    ViewPager viewPager;
    Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
                    Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
            switch (msg.what){
                case 100:
                    startActivity(intent);
                    finish();
                    break;
                case 200:
                    startActivity(intent);
                   finish();
                    break;
            }
              }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        share = getSharedPreferences("welcome",MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        int tag = share.getInt("tag",0);
        if (tag==0) {
            ZbWelocmeFirstFragment zwff = new ZbWelocmeFirstFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container, zwff).commit();
            tag += 1;
            edit.putInt("tag", tag);
            edit.commit();
            handler.sendEmptyMessageDelayed(200,100000);

        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new ZbWelcomeSeconFragment()).commit();
            handler.sendEmptyMessageDelayed(100,4000);
        }
    }


    //接口回调，但无法特定时间
    @Override
    public void myCallBack(int position) {
        if (position==2){
            handler.sendEmptyMessageDelayed(200,30000);
    }
    }
}

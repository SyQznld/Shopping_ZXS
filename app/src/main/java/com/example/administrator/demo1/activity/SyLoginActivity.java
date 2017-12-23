package com.example.administrator.demo1.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Looper;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.demo1.R;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;


public class SyLoginActivity extends AppCompatActivity {
    @BindView(R.id.youxiangadress)
    TextInputEditText youxiangadress;
    @BindView(R.id.login_back)
    ImageView login_back;
    @BindView(R.id.keyPassword)
    TextInputEditText keyPassword;
    @BindView(R.id.login_button)
    Button login_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sy_login);
        ButterKnife.bind(this);
        ShareSDK.initSDK(this);
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.login_back:
                intent = new Intent(SyLoginActivity.this,SySettingActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.login_button:
                initLogin();
                break;
            case R.id.login_forget:
    Intent intentForget = new Intent(SyLoginActivity.this,ZbForgetKeyActivity.class);
                startActivity(intentForget);
                break;
            case R.id.login_register:
            intent = new Intent(SyLoginActivity.this,ZbRegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.qq:
                thirdLogin(QQ.NAME);
                break;
            case R.id.xinLang:
                thirdLogin(SinaWeibo.NAME);
                break;
            case R.id.weixin:
                thirdLogin(Wechat.NAME);
                break;
        }
    }

    private void initLogin() {
        String s = youxiangadress.getText().toString();
        String s1 = keyPassword.getText().toString();
        SQLiteDatabase uerdb = openOrCreateDatabase("uerdb", MODE_PRIVATE, null);
        uerdb.execSQL("create table if not exists usertb(_id integer primary key autoincrement,userAdresses text,userName text,userKey text)");
        SharedPreferences.Editor denglu = getSharedPreferences("Denglu", MODE_PRIVATE).edit();
        Cursor cursor = uerdb.rawQuery("select * from usertb where userAdresses=?", new String[]{s});
        if (cursor!=null){
            if (cursor.moveToNext()){
                String userKey = cursor.getString(cursor.getColumnIndex("userKey"));
                String userName = cursor.getString(cursor.getColumnIndex("userName"));
                if (s1.equals(userKey)){
                    Intent intent = new Intent(SyLoginActivity.this,SySettingActivity.class);
                    denglu.putString("registerName",userName);
                    denglu.putInt("tag", 1);
                    denglu.putString("icon","https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=422186556481800a71e58e0e813433d6/34fae6cd7b899e519cca45ce4aa7d933c9950da7.jpg");
                    denglu.commit();
                    startActivity(intent);
                    finish();
                }else {
                    keyPassword.setText("");
                    Toast.makeText(SyLoginActivity.this, "用户名或密码输入有误", Toast.LENGTH_SHORT).show();
                }
            }else {
                keyPassword.setText("");
                Toast.makeText(SyLoginActivity.this, "用户名或密码输入有误", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void thirdLogin(String name) {
        Platform platform = ShareSDK.getPlatform(this, name);
        platform.SSOSetting(false);
        platform.removeAccount();
        platform.showUser(null);
        //登录结果的监听
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Looper.prepare();
                Toast.makeText(SyLoginActivity.this, "登录成功,正在跳转", Toast.LENGTH_LONG).show();
                String name = platform.getDb().getUserName();
                String icon = platform.getDb().getUserIcon();
                String id = platform.getDb().getUserId();
                SharedPreferences denglu = getSharedPreferences("Denglu", MODE_PRIVATE);
                SharedPreferences.Editor edit = denglu.edit();
                edit.putInt("tag", 1);
                edit.putString("registerName", name);
                edit.putString("icon", icon);
                edit.commit();
                Intent intent = new Intent(SyLoginActivity.this, SySettingActivity.class);
                startActivity(intent);
                finish();
                //无跳转效果
                SyLoginActivity.this.overridePendingTransition(0, 0);
                Looper.loop();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
            }

            @Override
            public void onCancel(Platform platform, int i) {
            }
        });
    }
}

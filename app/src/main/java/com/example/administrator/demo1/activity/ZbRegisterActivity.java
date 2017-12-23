package com.example.administrator.demo1.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.demo1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZbRegisterActivity extends AppCompatActivity {
    @BindView(R.id.register_adress)
    EditText register_adress;
    @BindView(R.id.register_userWord)
    EditText register_userWord;
    @BindView(R.id.register_passWord)
    EditText register_passWord;
    @BindView(R.id.imageShow)
    ImageView imageShow;
    int tag = 0;
    @BindView(R.id.text_register)
    TextView text_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zb_register);
        ButterKnife.bind(this);
        SQLiteDatabase uerdb = openOrCreateDatabase("uerdb", MODE_PRIVATE, null);
        uerdb.execSQL("create table if not exists usertb(_id integer primary key autoincrement,userName text,userKey text)");
        SharedPreferences.Editor denglu = getSharedPreferences("Denglu", MODE_PRIVATE).edit();
        String s = text_register.getText().toString();
        SpannableStringBuilder ssbuilder = new SpannableStringBuilder(s);
        ForegroundColorSpan yellowSpan = new ForegroundColorSpan(getResources().getColor(R.color.orange));
        ssbuilder.setSpan(yellowSpan,9,s.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_register.setText(ssbuilder);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_button:
                initDate();
                break;
            case R.id.navibar_back:
                finish();
                break;
            case R.id.imageShow:
                if (tag == 1) {
                    imageShow.setSelected(false);
                    //密文
                    ZbRegisterActivity.this.register_passWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    tag = 0;
                } else {
                    //明文
                    ZbRegisterActivity.this.register_passWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageShow.setSelected(true);
                    tag += 1;
                }
                break;
        }
    }
    private void initDate() {
        String s = register_adress.getText().toString();
        //验证邮箱
        boolean matches = s.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
        if (matches){
        String s1 = register_userWord.getText().toString();
        String s2 = register_passWord.getText().toString();
        SQLiteDatabase uerdb = openOrCreateDatabase("uerdb", MODE_PRIVATE, null);
        uerdb.execSQL("create table if not exists usertb(_id integer primary key autoincrement,userAdresses text,userName text,userKey text)");
        Cursor cursor2 = uerdb.rawQuery("select * from usertb", new String[]{});
        if (cursor2!=null){
            if (cursor2.moveToNext()){
                String userAdresses = cursor2.getString(cursor2.getColumnIndex("userAdresses"));
                String userName = cursor2.getString(cursor2.getColumnIndex("userName"));
                String userKey = cursor2.getString(cursor2.getColumnIndex("userKey"));
            }
        }
        Cursor cursor = uerdb.rawQuery("select * from usertb where userAdresses=?", new String[]{s});
        SharedPreferences denglu = getSharedPreferences("Denglu", MODE_PRIVATE);
        Cursor cursor1 = uerdb.rawQuery("select * from usertb where userName=?", new String[]{s1});;
            if (cursor.moveToNext()) {
                register_passWord.setText("");
                register_userWord.setText("");
                register_adress.setText("");
                Toast.makeText(ZbRegisterActivity.this, "已经注册过", Toast.LENGTH_SHORT).show();
            }else if (cursor1.moveToNext()) {
                    Toast.makeText(ZbRegisterActivity.this, "用户名重复", Toast.LENGTH_SHORT).show();
            } else {
                ContentValues values = new ContentValues();
                values.put("userAdresses", s);
                values.put("userName", s1);
                values.put("userKey", s2);
                long usertb = uerdb.insert("usertb",null, values);

                SharedPreferences.Editor edit = denglu.edit();
                edit.putString("registerName", s1);
                edit.putString("icon", "https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=422186556481800a71e58e0e813433d6/34fae6cd7b899e519cca45ce4aa7d933c9950da7.jpg");
                edit.putInt("tag", 1);
                edit.commit();
                Intent intent = new Intent(ZbRegisterActivity.this,SySettingActivity.class);
                startActivity(intent);
                finish();
                values.clear();
            }
        }else {
            Toast.makeText(ZbRegisterActivity.this, "输入有误", Toast.LENGTH_SHORT).show();
        }
    }
    }

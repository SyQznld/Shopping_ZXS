package com.example.administrator.demo1.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.view.SearchLiuShiText;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZbSearchActivity extends AppCompatActivity {


    ImageView zb_search_back;
    SearchLiuShiText searchLiuShiView;
    String[] arr = {"跑鞋", "饼干糕点", "面膜", "T恤", "休闲运动鞋", "腕表", "休闲零食", "休闲鞋", "阿迪达斯", "卡尔文.克莱恩", "飞利浦", "新百伦", "亚瑟士",
            "耐克", "爱步", "苹果"};
    private String searchEdit;
    @BindView(R.id.zb_search_searchEdit)
    EditText zb_search_searchEdit;
    @BindView(R.id.zb_search_searchText)
    TextView zb_search_searchText;
    @BindView(R.id.search_remove)
    TextView search_remove;
    private SQLiteDatabase search_add;
    private Cursor cursor;
    private int count;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zb_search);
        ButterKnife.bind(this);
        search_add = openOrCreateDatabase("search", MODE_PRIVATE, null);
        search_add.execSQL("create table if not exists searchtb(_id integer primary key autoincrement,searchName text)");
        cursor = search_add.rawQuery("select * from searchtb", new String[]{});
        count = cursor.getCount();
        if (count > 0) {
            search_remove.setVisibility(View.VISIBLE);
        } else {
            search_remove.setVisibility(View.INVISIBLE);
        }
        search_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_add.execSQL("drop table if exists searchtb");
                ll.setVisibility(View.INVISIBLE);
                backIntent();
            }
        });
        zb_search_searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.length();
                if (length > 0) {
                    searchEdit = s.toString();
                    zb_search_searchText.setClickable(true);
                } else {
                    zb_search_searchText.setClickable(false);
                }
            }
        });

        //查找控件
        initView();
        //填充流式布局 里 TextView
        initaddView();
        initLiuShiTextView();
        //各个控件点击事件
        viewClick();
    }

    private void initaddView() {
        ll = (LinearLayout) findViewById(R.id.search_ll);
        ll.setOrientation(LinearLayout.VERTICAL);
        if (count > 3) {
            Cursor cursor1 = search_add.rawQuery("select * from searchtb limit ?,?", new String[]{String.valueOf(count - 3), String.valueOf(count)});
            int count1 = cursor1.getCount();
            for (int i = 0; i < count1; i++) {
                RelativeLayout ll1 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.addsearch_view, null);
                TextView viewById = (TextView) ll1.findViewById(R.id.search_search_textView);
                if (cursor1.moveToNext()) {
                    String searchName = cursor1.getString(this.cursor.getColumnIndex("searchName"));
                    viewById.setText(searchName);
                    ll.addView(ll1);
                }
            }
        } else {
            for (int i = 0; i < count; i++) {
                RelativeLayout ll1 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.addsearch_view, null);
                TextView viewById = (TextView) ll1.findViewById(R.id.search_search_textView);
                if (cursor.moveToNext()) {
                    String searchName = cursor.getString(cursor.getColumnIndex("searchName"));
                    viewById.setText(searchName);
                    ll.addView(ll1);
                }
            }
        }


    }

    private void viewClick() {

        final ContentValues values = new ContentValues();
        zb_search_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        zb_search_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        zb_search_searchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                values.put("searchName", searchEdit);
                long searchtb = search_add.insert("searchtb", null, values);
                values.clear();
                backIntent();
            }
        });
    }

    private void initView() {
        zb_search_back = (ImageView) findViewById(R.id.zb_search_back);
        searchLiuShiView = (SearchLiuShiText) findViewById(R.id.search_liushiView);
    }

    private void initLiuShiTextView() {
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < arr.length; i++) {
            TextView view = (TextView) inflater.inflate(R.layout.search_liushi_item, searchLiuShiView, false);
            view.setText(arr[i]);
            searchLiuShiView.addView(view);
            //流式布局 TextView点击事件
            textClick(view);
        }

    }

    private void textClick(final TextView view) {

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = view.getText().toString();
                ContentValues values = new ContentValues();
                values.put("searchName", s);
                long searchtb = search_add.insert("searchtb", null, values);
                values.clear();
                Intent intent = new Intent(ZbSearchActivity.this, SyLiuShiClickActivity.class);
                intent.putExtra("keyword", s);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zb_search_searchText:
                ContentValues values = new ContentValues();
                values.put("searchName", searchEdit);
                backIntent();
                break;
        }
    }

    //数据库操作，返回刷新
    public void backIntent() {
        Intent intent = new Intent(ZbSearchActivity.this, SyLiuShiClickActivity.class);

        intent.putExtra("keyword", zb_search_searchEdit.getText().toString());
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }
}

package com.example.administrator.demo1.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.administrator.demo1.OnKeyShare;
import com.example.administrator.demo1.R;
import com.example.administrator.demo1.popwindow.HaoYangMaoPop;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SyHaoYangMaoDetailActivity extends AppCompatActivity {

    String url;
    @BindView(R.id.web_over_webview)
    WebView web_over_webview;
    @BindView(R.id.web_over_more)
    ImageView web_over_more;
    @BindView(R.id.web_over_back)
    ImageView web_over_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sy_hao_yang_mao_detail);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        //控件点击事件
        viewClick();
        //webview加载网页数据
        initWebView();

    }

    private void viewClick() {
        web_over_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        web_over_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HaoYangMaoPop addPopWindow = new HaoYangMaoPop(SyHaoYangMaoDetailActivity.this);
                addPopWindow.showPopupWindow(web_over_more);


                addPopWindow.getContentView().findViewById(R.id.haoyangmao_pop_home).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                addPopWindow.getContentView().findViewById(R.id.haoyangmao_pop_search).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       Intent intent = new Intent(SyHaoYangMaoDetailActivity.this,ZbSearchActivity.class);
                        startActivity(intent);
                    }
                });

                addPopWindow.getContentView().findViewById(R.id.haoyangmao_pop_share).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       Toast.makeText(SyHaoYangMaoDetailActivity.this,"分享",Toast.LENGTH_LONG).show();
                        OnKeyShare.showShare(SyHaoYangMaoDetailActivity.this);
                    }
                });

                addPopWindow.getContentView().findViewById(R.id.haoyangmao_pop_shoucang).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SyHaoYangMaoDetailActivity.this,SyLoginActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });

    }


    private void initWebView() {

/*
        web_over_webview.getSettings().setJavaScriptEnabled(true);
        web_over_webview.getSettings().setSupportZoom(true);
        web_over_webview.getSettings().setBuiltInZoomControls(true);*/
        web_over_webview.loadUrl(url);

        //设置加载网络图片
        web_over_webview.getSettings().setBlockNetworkImage(false);


        //设置可加载js代码
        web_over_webview.getSettings().setJavaScriptEnabled(true);

        //设置页面放大缩小
        web_over_webview.getSettings().setSupportZoom(true);
        web_over_webview.getSettings().setDisplayZoomControls(true);
        web_over_webview.getSettings().setBuiltInZoomControls(true);


        web_over_webview.getSettings().setUseWideViewPort(true);

        //禁止使用第三方浏览器，使用自己程序打开
        web_over_webview.setWebViewClient(new WebViewClient());


    }


}

package com.example.administrator.demo1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.demo1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyTuCaoActivity extends AppCompatActivity {

    @BindView(R.id.mytucao_back)
    ImageView tucao_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tu_chao);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.mytucao_back)
    public  void  back(View view){
        finish();
    }

    public void onClick(View view) {
        Toast.makeText(MyTuCaoActivity.this,"感谢您的反馈",Toast.LENGTH_LONG).show();

        finish();
    }
}

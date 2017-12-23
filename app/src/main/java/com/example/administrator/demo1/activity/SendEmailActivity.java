package com.example.administrator.demo1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.demo1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SendEmailActivity extends AppCompatActivity {
@BindView(R.id.yanzheng_tv)
    TextView textView;
@BindView(R.id.name_tv)
    TextView name_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zb_send_email);
        ButterKnife.bind(this);
        String email = getIntent().getStringExtra("email");
        textView.setText("验证邮件已经发送至"+email+"的邮箱,请前往验证");
        name_tv.setText("昵称:12313241224124");
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.goBack_button:
                Intent intent = new Intent(SendEmailActivity.this,SyLoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}

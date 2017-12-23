package com.example.administrator.demo1.activity.setting_help;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.demo1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Help_DaoHuoActivity extends AppCompatActivity {

    @BindView(R.id.help_daohuo_back)
    ImageView help_daohuo_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help__dao_huo);

        ButterKnife.bind(this);
    }
    @OnClick(R.id.help_daohuo_back)
    public  void  back(View view){
        finish();
    }
}

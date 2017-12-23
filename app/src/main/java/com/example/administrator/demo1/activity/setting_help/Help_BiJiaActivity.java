package com.example.administrator.demo1.activity.setting_help;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.demo1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Help_BiJiaActivity extends AppCompatActivity {

    @BindView(R.id.help_bijia_back)
    ImageView help_bijia_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help__bi_jia);

        ButterKnife.bind(this);
    }
    @OnClick(R.id.help_bijia_back)
    public  void  back(View view){
        finish();
    }
}

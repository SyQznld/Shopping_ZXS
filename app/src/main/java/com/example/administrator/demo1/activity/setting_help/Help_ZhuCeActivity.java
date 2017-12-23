package com.example.administrator.demo1.activity.setting_help;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.demo1.R;

public class Help_ZhuCeActivity extends AppCompatActivity {

    ImageView help_zhuce_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help__zhu_ce);


        help_zhuce_back = (ImageView) findViewById(R.id.help_zhuce_back);
        help_zhuce_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

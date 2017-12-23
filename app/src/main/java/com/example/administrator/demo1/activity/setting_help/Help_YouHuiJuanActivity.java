package com.example.administrator.demo1.activity.setting_help;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.demo1.R;

public class Help_YouHuiJuanActivity extends AppCompatActivity {

    ImageView help_youhuijuan_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help__you_hui_juan);

        help_youhuijuan_back = (ImageView) findViewById(R.id.help_youhuijuan_back);
        help_youhuijuan_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

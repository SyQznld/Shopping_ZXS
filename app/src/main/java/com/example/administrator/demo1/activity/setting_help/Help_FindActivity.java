package com.example.administrator.demo1.activity.setting_help;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.demo1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Help_FindActivity extends AppCompatActivity {

    @BindView(R.id.help_find_back)
    ImageView help_find_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help__find);

        ButterKnife.bind(this);

        help_find_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}

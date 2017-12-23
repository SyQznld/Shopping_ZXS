package com.example.administrator.demo1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.demo1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZbForgetKeyActivity extends AppCompatActivity {
@BindView(R.id.find_userWord)
    EditText find_userWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zb_forget_key);
        ButterKnife.bind(this);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navibar_back:
                finish();
                break;
            case R.id.find_button:
                initNext();
                break;
        }
    }

    private void initNext() {
        String s = find_userWord.getText().toString();
        boolean matches = s.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
        if (matches){
            Intent intent = new Intent(ZbForgetKeyActivity.this,SendEmailActivity.class);
            intent.putExtra("email",s);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(ZbForgetKeyActivity.this, "输入邮箱有误", Toast.LENGTH_SHORT).show();
            find_userWord.setText("");
        }
    }
}

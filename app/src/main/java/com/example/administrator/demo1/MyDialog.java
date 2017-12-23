package com.example.administrator.demo1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/10/28.
 */
public class MyDialog extends Dialog {
    private TextView title;
    private TextView message;
    private Button no;
    private Button yes;

    private String myTitle;
    private  String myMessage;

    //监听点击接口
     public    interface  NoOnClickListener{
            public  void  noOnClickListener();
        }
    public    interface  YesOnClickListener{
            public  void  yesOnClickListener();
        }
    NoOnClickListener noClick;
    YesOnClickListener yesClick;
    public void setNoClick(NoOnClickListener noClick) {
        this.noClick = noClick;
    }

    public void setYesClick(YesOnClickListener yesClick) {
        this.yesClick = yesClick;
    }

    public String getMyTitle() {
        return myTitle;
    }

    public void setMyTitle(String myTitle) {
        this.myTitle = myTitle;
    }

    public String getMyMessage() {
        return myMessage;
    }

    public void setMyMessage(String myMessage) {
        this.myMessage = myMessage;
    }

    public MyDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public MyDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydialog_item);
        //按空白页不消失
        setCanceledOnTouchOutside(false);
        initView();
        initDate();
        initOnClcick();
    }
//点击监听
    private void initOnClcick() {
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noClick!=null){
                    noClick.noOnClickListener();
                }
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesClick!=null){
                    yesClick.yesOnClickListener();
                }
            }
        });
    }

    private void initDate() {
        if (myTitle!=null){
            title.setText(myTitle);
        }
        if (myMessage!=null){
            message.setText(myMessage);
        }
    }

    private void initView() {
        title = ((TextView) findViewById(R.id.title));
        message = ((TextView) findViewById(R.id.message));
        no = ((Button) findViewById(R.id.no));
        yes = ((Button) findViewById(R.id.yes));
    }
}

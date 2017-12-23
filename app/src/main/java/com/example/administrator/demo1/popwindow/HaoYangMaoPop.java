package com.example.administrator.demo1.popwindow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.activity.ZbSearchActivity;

/**
 * Created by Administrator on 2016/10/28.
 */
public  class HaoYangMaoPop extends PopupWindow {

    private View conentView;

    public  HaoYangMaoPop(final Activity context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.haoyangmao_web_popwindow, null);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        //  this.setAnimationStyle(R.style.AnimationPreview);
        LinearLayout home = (LinearLayout) conentView
                .findViewById(R.id.haoyangmao_pop_home);

        LinearLayout search = (LinearLayout) conentView
                .findViewById(R.id.haoyangmao_pop_search);
        LinearLayout share = (LinearLayout) conentView
                .findViewById(R.id.haoyangmao_pop_share);
        LinearLayout shoucang = (LinearLayout) conentView
                .findViewById(R.id.haoyangmao_pop_shoucang);


        home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                HaoYangMaoPop.this.dismiss();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                HaoYangMaoPop.this.dismiss();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                HaoYangMaoPop.this.dismiss();
            }
        });
        shoucang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                HaoYangMaoPop.this.dismiss();
            }
        });
    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 18);
        } else {
            this.dismiss();
        }
    }
}

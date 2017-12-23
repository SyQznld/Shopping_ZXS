package com.example.administrator.demo1.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.example.administrator.demo1.R;

/**
 * Created by Administrator on 2016/10/27.
 */

public class XPricePop extends PopupWindow {
    private View contentView;
    public XPricePop (final Activity context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.item_pop_list,null);
        initListViewRight(context);
        initPopWindow(context);
    }

    private void initListViewRight(Activity context) {
    }

    private void initPopWindow(final Activity context) {
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        this.setContentView(contentView);
        this.setWidth(w);
        this.setHeight(h/7);
        ColorDrawable dw = new ColorDrawable(Color.argb(255,255,255,255));
        this.setBackgroundDrawable(dw);
        this.setFocusable(false);
        this.setOutsideTouchable(true);
        this.update();
    }
    public void showPricePopup(View parent) {
        if (!this.isShowing()) {
            this.showAsDropDown(parent);
        } else {
            this.dismiss();
        }
    }
}

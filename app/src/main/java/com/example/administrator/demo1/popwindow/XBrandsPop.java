package com.example.administrator.demo1.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.cans.Cans;
import com.example.administrator.demo1.myinterface.MyInterface;
import com.example.administrator.demo1.task.PopJsonTask;
import com.example.administrator.demo1.utils.ParseUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/27.
 */

public class XBrandsPop extends PopupWindow{
    private View contentView;
    private LinearLayout headView;
    private TextView shop_pop_tv;
    private ListView listView_brands_pop;
    private GridView gridView_brands_pop;
    private int pre;
     List<String> gridString = new ArrayList<>();
    public XBrandsPop(final Activity context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.layout_brands_pop, null);
        headView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.item_shop_pop_layout,null);
        listView_brands_pop = (ListView) contentView.findViewById(R.id.listView_brands_pop);
        gridView_brands_pop = (GridView) contentView.findViewById(R.id.gridView_brands_pop);
        initHeaderView(headView);
        listView_brands_pop.addHeaderView(headView);
        initListView(context);
        initPopWindow(context);
        OnClickList(context);
    }
    private void initListView(final Context context) {
        initGridView(0, context);
        String brands[] = {"A-D", "I-L", "Y-Z", "U-X", "Q-T", "M-P", "E-H"};
        listView_brands_pop.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, brands));
        listView_brands_pop.post(new Runnable() {
            @Override
            public void run() {
                listView_brands_pop.getChildAt(0).setBackgroundColor(Color.GRAY);
            }
        });
    }
    public void OnClickList(final Context context){
        listView_brands_pop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView_brands_pop.getChildAt(pre).setBackgroundColor(Color.WHITE);
                listView_brands_pop.getChildAt(position).setBackgroundColor(Color.GRAY);
                pre = position;
                initGridView(position, context);
            }
        });
    }

    private void initGridView(final int position,Context context) {
        gridView_brands_pop.setAdapter(new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,gridString));
        new PopJsonTask(new MyInterface.JsonCallBack() {
            @Override
            public void jsonCallBack(String json) {
                if (json != null) {
                    List<String> list = ParseUtils.getStringList(json);
                    if (list != null && list.size() > 0) {
                        gridString.addAll(list);
                    }
                }
            }
        }).execute(Cans.getPath(1));
    }
    private void initHeaderView(LinearLayout headView) {
        shop_pop_tv  = (TextView) headView.findViewById(R.id.shop_pop_tv);
        shop_pop_tv.setText("全部品牌");
    }
    private void initPopWindow(Activity context) {
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        this.setContentView(contentView);
        this.setWidth(w);
        this.setHeight(h/2);
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

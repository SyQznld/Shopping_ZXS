package com.example.administrator.demo1.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.example.administrator.demo1.R;
import com.example.administrator.demo1.adapter.ClassesAdapter;
import com.example.administrator.demo1.adapter.HaoYangMaoNextAdapter;
import com.example.administrator.demo1.adapter.XPopNextAdapter;
import com.example.administrator.demo1.api_service.GetHaoYangMaoList;
import com.example.administrator.demo1.api_service.GetList;
import com.example.administrator.demo1.bean.SyHaoYangMaoBean;
import com.example.administrator.demo1.bean.XClassesBean;
import com.example.administrator.demo1.bean.XPopTotalBean;
import com.example.administrator.demo1.cans.Cans;
import com.example.administrator.demo1.myinterface.MyInterface;
import com.example.administrator.demo1.task.PopJsonTask;
import com.example.administrator.demo1.utils.ParseUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2016/10/28.
 */
public class HaoYangMaoClassesPop extends PopupWindow {


    private View contentView;
    private List<XClassesBean> classesBeen = new ArrayList<>();
    private List<SyHaoYangMaoBean.ClassesBean.NextBean> popBeen = new ArrayList<>();
    private HaoYangMaoNextAdapter xnAdapter;
    private ListView listView;
    private ListView listView_right;
    private ClassesAdapter classesAdapter;
    public HaoYangMaoClassesPop(final Activity context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.item_classes_pop, null);
        //左边listView
        classesAdapter = new ClassesAdapter(context,classesBeen);
        listView = (ListView) contentView.findViewById(R.id.listView_classes);
        listView.setAdapter(classesAdapter);
        //右边listView
        listView_right = (ListView) contentView.findViewById(R.id.listView_right);
        xnAdapter = new HaoYangMaoNextAdapter(context,popBeen);
        listView_right.setAdapter(xnAdapter);
//        listView_right = contentView.findViewById(R.id.listView_right);
        initClassesListLeft();
        initPopWindow(context);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                initClassesListRight(position,context);
            }
        });

    }

    private void initClassesListRight(final int position,Context context) {
        //http://app.gwdang.com/app/price_zhi?ps=20&is_haitao=1&format=json&img_width=375&current_time=1477111800&version=2
        String baseUrl = "http://app.gwdang.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetHaoYangMaoList getHaoYangMaoList = retrofit.create(GetHaoYangMaoList.class);
        Call<SyHaoYangMaoBean> call = getHaoYangMaoList.getHaoYangMaoData();
        call.enqueue(new Callback<SyHaoYangMaoBean>() {
            @Override
            public void onResponse(Call<SyHaoYangMaoBean> call, Response<SyHaoYangMaoBean> response) {
                List<SyHaoYangMaoBean.ClassesBean.NextBean> next = response.body().getClasses().get(position).getNext();
                Log.e(TAG, "onResponse: ======================="+next );
                if (next != null && next.size() > 0) {
                    popBeen.clear();
                    popBeen.addAll(next);
                    xnAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<SyHaoYangMaoBean> call, Throwable t) {
                Log.e(TAG, "onFailure:=============="+t.getMessage() );
            }
        });
    }

    private void initPopWindow(final Activity context) {
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

    private void initClassesListLeft() {
        new PopJsonTask(new MyInterface.JsonCallBack() {
            @Override
            public void jsonCallBack(String json) {
                if (!TextUtils.isEmpty(json)) {
                    List<XClassesBean> list = ParseUtils.getClassesList(json);
                    if (list != null && list.size() > 0) {
                        classesBeen.clear();
                        classesBeen.addAll(list);
                        classesAdapter.notifyDataSetChanged();
                    }
                }
            }
        }).execute(Cans.getYangMao());
    }

    public void showPricePopup(View parent) {
        if (!this.isShowing()) {
            this.showAsDropDown(parent);
        } else {
            this.dismiss();
        }
    }
}

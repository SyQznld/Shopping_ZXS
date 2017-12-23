package com.example.administrator.demo1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.bean.ZbYouHuiJuanBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/26.
 */
public class ZbMyYouHuiQuanAdapter extends BaseAdapter {
    List<ZbYouHuiJuanBean> list;
    Context context;
    LayoutInflater inflater;

    public ZbMyYouHuiQuanAdapter(List<ZbYouHuiJuanBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = inflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.zbmyyouhuiquanadapter_item, parent, false);
            holder.zb_myyouhui_icon = ((ImageView) convertView.findViewById(R.id.zb_myyouhui_icon));
            holder.zb_myyouhui_title = ((TextView) convertView.findViewById(R.id.zb_myyouhui_title));
            holder.zb_myyouhui_hcoupon = ((TextView) convertView.findViewById(R.id.zb_myyouhui_hcoupon));
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ZbYouHuiJuanBean zbYouHuiJuanBean = list.get(position);
        if (zbYouHuiJuanBean != null) {
            String img_url = zbYouHuiJuanBean.getImg_url();
            String discount_arg = zbYouHuiJuanBean.getDiscount_arg();
            String end_time = zbYouHuiJuanBean.getEnd_time();
            holder.zb_myyouhui_title.setText(discount_arg);
            holder.zb_myyouhui_hcoupon.setText("有效期至: " + end_time);
            Picasso.with(context).load(img_url).placeholder(R.mipmap.ic_launcher).into(holder.zb_myyouhui_icon);
        }
        return convertView;
    }

    static class ViewHolder {
        ImageView zb_myyouhui_icon;
        TextView zb_myyouhui_title;
        TextView zb_myyouhui_hcoupon;


    }
}

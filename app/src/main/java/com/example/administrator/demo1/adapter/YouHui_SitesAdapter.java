package com.example.administrator.demo1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.bean.ZbYouHuiBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */
public class YouHui_SitesAdapter extends BaseAdapter {

    Context mContext;
    List<ZbYouHuiBean.SiteListBean> list;
    LayoutInflater inflater;

    public YouHui_SitesAdapter(Context mContext, List<ZbYouHuiBean.SiteListBean> list) {
        this.mContext = mContext;
        this.list = list;
        inflater = LayoutInflater.from(mContext);
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
            convertView = inflater.inflate(R.layout.item_shop_pop_layout, parent, false);
            holder = new ViewHolder();
            holder.shop_pop_tv = (TextView) convertView.findViewById(R.id.shop_pop_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ZbYouHuiBean.SiteListBean bean = list.get(position);
        if (bean != null) {
            holder.shop_pop_tv.setText(bean.getName());
        }
        return convertView;
    }
    static class ViewHolder{
        TextView shop_pop_tv;
    }
}

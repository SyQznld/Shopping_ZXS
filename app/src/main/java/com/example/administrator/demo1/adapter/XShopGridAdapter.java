package com.example.administrator.demo1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.bean.XPopTotalBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */

public class XShopGridAdapter extends BaseAdapter {
    Context mContext;
    List<XPopTotalBean.SitesBean> list;
    LayoutInflater inflater;

    public XShopGridAdapter(Context mContext, List<XPopTotalBean.SitesBean> list) {
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
        XPopTotalBean.SitesBean bean = list.get(position);
        if (bean != null) {
            holder.shop_pop_tv.setText(bean.getSite_name());
        }
        return convertView;
    }
    static class ViewHolder{
        TextView shop_pop_tv;
    }
}

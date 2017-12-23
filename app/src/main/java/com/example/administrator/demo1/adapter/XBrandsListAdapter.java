package com.example.administrator.demo1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.demo1.R;

import java.util.List;

/**
 * Created by Administrator on 2016/10/27.
 */

public class XBrandsListAdapter extends BaseAdapter {
    Context mContext;
    List<String> stringList;
    LayoutInflater inflater;
    public XBrandsListAdapter(Context mContext, List<String> stringList) {
        this.mContext = mContext;
        this.stringList = stringList;
        inflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return stringList.size();
    }
    @Override
    public Object getItem(int position) {
        return stringList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_shop_pop_layout, parent);
            holder = new ViewHolder();
            holder.shop_pop_tv = (TextView) convertView.findViewById(R.id.shop_pop_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.shop_pop_tv.setText(stringList.get(position));
        return convertView;
    }
    static class  ViewHolder{
        TextView shop_pop_tv;
    }
}

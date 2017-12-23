package com.example.administrator.demo1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.bean.XClassesBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */

public class ClassesAdapter extends BaseAdapter {
    Context mContext;
    List<XClassesBean> list;
    LayoutInflater inflater;

    public ClassesAdapter(Context mContext, List<XClassesBean> list) {
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
            convertView = inflater.inflate(R.layout.item_classes, parent, false);
            holder = new ViewHolder();
            holder.name_tv = (TextView) convertView.findViewById(R.id.name_tv);
            holder.num_tv = (TextView) convertView.findViewById(R.id.num_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        XClassesBean bean = list.get(position);
        if (bean != null) {
            holder.name_tv.setText(bean.getName());
            holder.num_tv.setText(bean.getSum());
        }
        return convertView;
    }
    static class ViewHolder{
        TextView name_tv;
        TextView num_tv;
    }
}

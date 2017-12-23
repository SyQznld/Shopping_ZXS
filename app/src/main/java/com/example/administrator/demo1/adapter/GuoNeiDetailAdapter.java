package com.example.administrator.demo1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.demo1.R;
import com.example.administrator.demo1.bean.XGoods;

import java.util.List;

import static com.example.administrator.demo1.R.id.image_sitename;

/**
 * Created by Administrator on 2016/10/28.
 */
public class GuoNeiDetailAdapter extends BaseAdapter {

    List<XGoods> list;
    Context context;
    LayoutInflater inflater;

    public GuoNeiDetailAdapter(List<XGoods> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
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
            convertView = inflater.inflate(R.layout.item_listview,parent,false);
            holder = new ViewHolder();
            holder.image_detail = (ImageView) convertView.findViewById(R.id.image_detail);
            holder.image_sitename = (TextView) convertView.findViewById(image_sitename);
            holder.price_detail = (TextView) convertView.findViewById(R.id.price_detail);
            holder.title_detail = (TextView) convertView.findViewById(R.id.title_detail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        XGoods bean = list.get(position);
            if (bean != null) {
                holder.title_detail.setText(bean.getTitle());
                holder.price_detail.setText(bean.getPrice_info());
                holder.image_sitename.setText(bean.getSite_name());
                Glide.with(context).load(bean.getPic_url()).into(holder.image_detail);
        }
        return convertView;
    }
    static class ViewHolder{
        ImageView image_detail;
       TextView title_detail;
        TextView price_detail;
        TextView  image_sitename;
    }
}

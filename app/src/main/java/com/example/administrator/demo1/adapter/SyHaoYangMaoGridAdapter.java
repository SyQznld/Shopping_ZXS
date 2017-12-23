package com.example.administrator.demo1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.bean.SyHaoYangMaoBean;
import com.example.administrator.demo1.bean.SyHaoYangMaoBean.ProductBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */
public class SyHaoYangMaoGridAdapter extends BaseAdapter {

    List<SyHaoYangMaoBean.ProductBean> list;
    Context context;
    LayoutInflater inflater;

    public SyHaoYangMaoGridAdapter(List<SyHaoYangMaoBean.ProductBean> list, Context context) {
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
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.haoyangmao_grid_item, parent, false);

            holder.haoyangmao_icon = (ImageView) convertView.findViewById(R.id.haoyangmao_icon);
            holder.haoyangmao_imageview = (ImageView) convertView.findViewById(R.id.haoyangmao_imageview);

            holder.haoyangmao_name = (TextView) convertView.findViewById(R.id.haoyangmao_name);
            holder.haoyangmao_price = (TextView) convertView.findViewById(R.id.haoyangmao_price);
            holder.haoyangmao_time = (TextView) convertView.findViewById(R.id.haoyangmao_time);
            holder.haoyangmao_title = (TextView) convertView.findViewById(R.id.haoyangmao_title);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SyHaoYangMaoBean.ProductBean productBean = list.get(position);

        if (productBean != null) {
            holder.haoyangmao_title.setText(productBean.getTitle());
            holder.haoyangmao_price.setText(productBean.getPrice_info());
            holder.haoyangmao_name.setText(productBean.getSite_name());

            int time = productBean.getUpdate_time().indexOf(" ");
            //   int length = productBean.getUpdate_time().length();
            int lastIndexOf = productBean.getUpdate_time().lastIndexOf(":");
            holder.haoyangmao_time.setText(productBean.getUpdate_time().substring(time + 1, lastIndexOf));

            Picasso.with(context).load(productBean.getSite_icon()).placeholder(R.mipmap.product_image_default).into(holder.haoyangmao_icon);
            Picasso.with(context).load(productBean.getPic_url()).placeholder(R.mipmap.product_image_default).into(holder.haoyangmao_imageview);

        }
        return convertView;
    }

    class ViewHolder {
        TextView haoyangmao_title, haoyangmao_price, haoyangmao_name, haoyangmao_time;
        ImageView haoyangmao_imageview, haoyangmao_icon;
    }
}

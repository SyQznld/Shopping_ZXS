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
import com.example.administrator.demo1.bean.Search_BiJiaBean;
import com.example.administrator.demo1.bean.XGoods;

import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */
public class Search_BiJiaAdapter extends BaseAdapter {

    Context context;
    List<Search_BiJiaBean.ProductBean> list;
    LayoutInflater inflater;

    public Search_BiJiaAdapter(Context context, List<Search_BiJiaBean.ProductBean> list) {
        this.context = context;
        this.list = list;
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
            convertView = inflater.inflate(R.layout.search_bijia_item_layout, parent, false);
            holder = new ViewHolder();
            holder.bijia_title = (TextView) convertView.findViewById(R.id.bijia_title);
            holder.bijia_count = (TextView) convertView.findViewById(R.id.bijia_count);
            holder.bijia_pic = (ImageView) convertView.findViewById(R.id.bijia_pic);
            holder.bijia_onsale = (TextView) convertView.findViewById(R.id.bijia_onsale);


            holder.price_tv = (TextView) convertView.findViewById(R.id.price_tv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Search_BiJiaBean.ProductBean bean = list.get(position);

        if (bean != null) {
            Glide.with(context).load(bean.getImg_url())
                    .placeholder(R.mipmap.ic_launcher)
                    .override(400, 400)
                    .centerCrop()
                    .into(holder.bijia_pic);


            int length = bean.getMin_price().length();
            holder.price_tv.setText(bean.getMin_price().substring(0,length-2)+"元");
            holder.bijia_title.setText(bean.getTitle());
            holder.bijia_onsale.setText(""+bean.getSite_cnt_onsale()+"家有售");

            holder.bijia_count.setText(bean.getReview_cnt());
        }
        return convertView;
    }

    static class ViewHolder {
        ImageView bijia_pic;
        TextView bijia_title;
        TextView price_tv;
        TextView bijia_count;
        TextView bijia_onsale;
    }
}

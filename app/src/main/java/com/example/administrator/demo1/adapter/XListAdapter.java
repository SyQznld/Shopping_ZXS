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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/10/24.
 */

public class XListAdapter extends BaseAdapter {
    Context mContext;
    List<XGoods> list;
    LayoutInflater inflater;

    public XListAdapter(Context mContext, List<XGoods> list) {
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
            convertView = inflater.inflate(R.layout.item_list_haitao, parent, false);
            holder = new ViewHolder();
            holder.time_tv = (TextView) convertView.findViewById(R.id.time_tv);
            holder.title_tv = (TextView) convertView.findViewById(R.id.title_tv);
            holder.image_haitao = (ImageView) convertView.findViewById(R.id.image_haitao);
            holder.shop_tv = (TextView) convertView.findViewById(R.id.shop_tv);
            holder.price_tv = (TextView) convertView.findViewById(R.id.price_tv);
            holder.siteIcon_iv = (ImageView) convertView.findViewById(R.id.siteIcon_iv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        XGoods bean = list.get(position);
        if (bean != null) {
            Glide.with(mContext).load(bean.getPic_url())
                    .placeholder(R.mipmap.ic_launcher)
                    .override(400,400)
                    .centerCrop()
                    .into(holder.image_haitao);
            Glide.with(mContext).load(bean.getSite_icon()).into(holder.siteIcon_iv);
            holder.price_tv.setText(bean.getPrice_info());
            holder.shop_tv.setText(bean.getSite_name());
            holder.title_tv.setText(bean.getTitle());
            //update_time":"2016-10-25 08:50:18"
           /* String time = bean.getUpdate_time();
            SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            try {
                Date dt2 = sdf.parse(time);
                long lTime = dt2.getTime() / 1000;
                String distanceTime = TimeUtils.getDistanceTime(lTime, System.currentTimeMillis());
                holder.time_tv.setText(distanceTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }*/


            int time = bean.getUpdate_time().indexOf(" ");
            //   int length = productBean.getUpdate_time().length();
            int lastIndexOf = bean.getUpdate_time().lastIndexOf(":");
            holder.time_tv.setText(bean.getUpdate_time().substring(time + 1, lastIndexOf));
        }
        return convertView;
    }
    static class ViewHolder{
        ImageView image_haitao;
        ImageView siteIcon_iv;
        TextView title_tv;
        TextView price_tv;
        TextView time_tv;
        TextView shop_tv;
    }
    static class TimeUtils {
        /*
        *计算time2减去time1的差值 差值只设置 几天 几个小时 或 几分钟
        * 根据差值返回多长之间前或多长时间后
        * */
         static String getDistanceTime(long  time1,long time2 ) {
            long day = 0;
            long hour = 0;
            long min = 0;
            long sec = 0;
            long diff ;
            String flag;
            if(time1<time2) {
                diff = time2 - time1;
                flag="前";
            } else {
                diff = time1 - time2;
                flag="后";
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
            if(day!=0)return day+"天"+flag;
            if(hour!=0)return hour+"小时"+flag;
            if(min!=0)return min+"分钟"+flag;
            return "刚刚";
        }
    }

}

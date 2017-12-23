package com.example.administrator.demo1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.bean.ZbYouHuiBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */
public class ZbYouHuiAdapter extends BaseAdapter {
    List<ZbYouHuiBean.ListBean> list ;
    Context mContext;
 LayoutInflater inflater;
    public ZbYouHuiAdapter(List<ZbYouHuiBean.ListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        inflater =inflater.from(mContext);
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
        ViewHolder viewHolder ;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.zbyouhuiadapter_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.zb_youhui_date = (TextView) convertView.findViewById(R.id.zb_youhui_date);
            viewHolder.zb_youhui_title = (TextView) convertView.findViewById(R.id.zb_youhui_title);
            viewHolder.zb_youhui_hcoupon = (TextView) convertView.findViewById(R.id.zb_youhui_hcoupon);
            viewHolder.zb_youhui_icon = (ImageView) convertView.findViewById(R.id.zb_youhui_icon);
            convertView.setTag(viewHolder);
        }else {
        viewHolder= (ViewHolder) convertView.getTag();
        }
        ZbYouHuiBean.ListBean listBean = list.get(position);
        if (listBean!=null){
            String discount_info = listBean.getDiscount_info();
            String left_num = listBean.getLeft_num();
            String taken_num = listBean.getTaken_num();
            String end_time = listBean.getEnd_time();
            String img_url = listBean.getImg_url();
            int i2 = end_time.indexOf("-");
            int i1 = end_time.indexOf(" ");
            String substring = end_time.substring(i2 + 1, i1);
            String date = substring.replace("-", "月");
            viewHolder.zb_youhui_date.setText("有效期至:"+date+"日");
            int length = discount_info.length();
            viewHolder.zb_youhui_title.setText(discount_info);
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(viewHolder.zb_youhui_title.getText().toString());
            //ForegroundColorSpan--文字前景色，BackgroundColorSpan--文字背景色
            ForegroundColorSpan yellowSpan = new ForegroundColorSpan(Color.RED);
            ForegroundColorSpan yellowSpan1 = new ForegroundColorSpan(Color.RED);
            ForegroundColorSpan yellowSpan2 = new ForegroundColorSpan(Color.RED);
            ForegroundColorSpan yellowSpan3 = new ForegroundColorSpan(Color.RED);
            int tag=0;
            int tag2=0;
            int tag1=0;
            int tag3=0;
            int tag4=0;
            int tag5=0;
            int tag6=0;
            int tag7=0;
            int tag8=0;
            for (int i = 0; i < length; i++) {
                char c = discount_info.charAt(i);
                boolean digit = Character.isDigit(c);
                if (digit&&tag==0){
                    tag1=i;
                    tag=1;
                }else if (!digit&&tag==1){
                    tag2=i;
                    tag=2;
                }else if (tag==2&&i>tag2&&digit){
                    tag=3;
                    tag3=i;
                }else if (!digit&&tag==3){
                    tag4=i;
                    tag=4;
                }else if (digit&&i== length -1){
                    tag4=i+1;
                }
                else if (digit&&tag==4){
                    tag5=i;
                    tag=5;
                }else if (!digit&&tag==5){
                    tag6=i;
                    tag=6;
                }else if (digit&&tag==6){
                    tag7=i;
                    tag=7;
                }else if (!digit&&tag==7){
                    tag8=i;
                    tag=8;
                }else if (digit&&i== length -1){
                    tag8=i+1;
                }
            }
            if (tag1>0&&tag2>tag1) {
                ssbuilder.setSpan(yellowSpan, tag1, tag2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (tag3>0&&tag4>tag3) {
                ssbuilder.setSpan(yellowSpan1, tag3, tag4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (tag5>0&&tag6>tag5) {
                ssbuilder.setSpan(yellowSpan2, tag5, tag6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (tag7>0&&tag8>tag7) {
                ssbuilder.setSpan(yellowSpan3, tag7, tag8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            viewHolder.zb_youhui_title.setText(ssbuilder);
            viewHolder.zb_youhui_hcoupon.setText("已领"+taken_num+"张,剩" +left_num+"张");
           Picasso.with(mContext).load(img_url).into(viewHolder.zb_youhui_icon);
        }
        return convertView;
    }
    static class ViewHolder{
    TextView zb_youhui_date,zb_youhui_title,zb_youhui_hcoupon;
        ImageView zb_youhui_icon;
    }
}

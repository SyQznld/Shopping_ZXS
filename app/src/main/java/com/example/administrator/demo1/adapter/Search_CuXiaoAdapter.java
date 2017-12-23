package com.example.administrator.demo1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.demo1.R;
import com.example.administrator.demo1.bean.CuXiaoBean;
import com.example.administrator.demo1.bean.Search_CuXiaoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/1.
 */
public class Search_CuXiaoAdapter extends BaseAdapter{

    List<Search_CuXiaoBean.PromotionBean> list;
    Context context;
    LayoutInflater inflater;

    public Search_CuXiaoAdapter(List<Search_CuXiaoBean.PromotionBean> list, Context context) {
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
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.cuxiao_item_layout, parent, false);

            ButterKnife.bind(viewHolder, convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Search_CuXiaoBean.PromotionBean promotionBean = list.get(position);
        if (promotionBean != null) {
            String title = promotionBean.getTitle();
            viewHolder.cuxiao_title.setText(title);
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(viewHolder.cuxiao_title.getText().toString());
            //ForegroundColorSpan--文字前景色，BackgroundColorSpan--文字背景色
            ForegroundColorSpan yellowSpan = new ForegroundColorSpan(Color.RED);
            ForegroundColorSpan yellowSpan1 = new ForegroundColorSpan(Color.RED);
            ForegroundColorSpan yellowSpan2 = new ForegroundColorSpan(Color.RED);
            ForegroundColorSpan yellowSpan3 = new ForegroundColorSpan(Color.RED);
            int tag = 0;
            int tag2 = 0;
            int tag1 = 0;
            int tag3 = 0;
            int tag4 = 0;
            int tag5 = 0;
            int tag6 = 0;
            int tag7 = 0;
            int tag8 = 0;
            int length = title.length();
            for (int i = 0; i < length; i++) {
                char c = title.charAt(i);
                boolean digit = Character.isDigit(c);
                if (digit && tag == 0) {
                    tag1 = i;
                    tag = 1;
                } else if (!digit && tag == 1) {
                    tag2 = i;
                    tag = 2;
                } else if (tag == 2 && i > tag2 && digit) {
                    tag = 3;
                    tag3 = i;
                } else if (!digit && tag == 3) {
                    tag4 = i;
                    tag = 4;
                }else if(digit&&tag == 3&&i== length -1){
                    tag4 = i+1;
                    tag = 4;
                } else if (digit&&tag == 4) {
                    tag5 = i;
                    tag = 5;
                } else if (!digit && tag == 5) {
                    tag6 = i;
                    tag = 6;
                } else if (digit && tag == 6) {
                    tag7 = i;
                    tag = 7;
                } else if (!digit&& tag == 7) {
                    tag8 = i;
                    tag = 8;
                }else if (digit&&tag == 7&&i== length -1){
                    tag8=i+1;
                }
            }
            if (tag1 > 0 && tag2 > tag1) {
                ssbuilder.setSpan(yellowSpan, tag1, tag2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (tag3 > 0 && tag4 > tag3) {
                ssbuilder.setSpan(yellowSpan1, tag3, tag4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (tag5 > 0 && tag6 > tag5) {
                ssbuilder.setSpan(yellowSpan2, tag5, tag6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (tag7 > 0 && tag8 > tag7) {
                ssbuilder.setSpan(yellowSpan3, tag7, tag8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            viewHolder.cuxiao_title.setText(ssbuilder);

            viewHolder.cuxiao_name.setText(promotionBean.getSite_name());
            viewHolder.cuxiao_time.setText("截止时间："+promotionBean.getEnd_time());

            Glide.with(context).load(promotionBean.getPic_url()).dontAnimate().placeholder(R.mipmap.product_image_default).into(viewHolder.cuxiao_pic);
            Glide.with(context).load(promotionBean.getSite_icon()).dontAnimate().placeholder(R.mipmap.product_image_default).into(viewHolder.cuxiao_icon);
        }
        return convertView;
    }

    class ViewHolder {

        @BindView(R.id.cuxiao_pic)
        ImageView cuxiao_pic;
        @BindView(R.id.cuxiao_icon)
        ImageView cuxiao_icon;
        @BindView(R.id.cuxiao_title)
        TextView cuxiao_title;
        @BindView(R.id.cuxiao_name)
        TextView cuxiao_name;
        @BindView(R.id.cuxiao_time)
        TextView cuxiao_time;
    }
}

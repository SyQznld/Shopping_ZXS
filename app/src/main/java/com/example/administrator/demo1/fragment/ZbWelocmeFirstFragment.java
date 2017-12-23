package com.example.administrator.demo1.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.demo1.MainActivity;
import com.example.administrator.demo1.R;
import com.example.administrator.demo1.adapter.ZbAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZbWelocmeFirstFragment extends Fragment {


    private ImageView feature_open;
    private Button tiaoguo;

    public ZbWelocmeFirstFragment() {
        // Required empty public constructor
    }

    private ViewPager viewPager;
    private ZbAdapter adapter;
    private  int imageview [] = {R.mipmap.new_feature_1,R.mipmap.new_feature_2,R.mipmap.new_feature_3};
    List<View> list = new ArrayList<>();
   MainActivity receiver;
    Context context;
    public interface MyCallBack{
        public  void  myCallBack(int position);
    }
    MyCallBack myCallBack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_zb_welocme_first, container, false);
        viewPager= (ViewPager) inflate.findViewById(R.id.welcome_viewPage);
        feature_open = ((ImageView) inflate.findViewById(R.id.feature_open));
        tiaoguo = ((Button) inflate.findViewById(R.id.tiaoguo));
        adapter = new ZbAdapter(list);
        viewPager.setAdapter(adapter);
        for (int i = 0; i < imageview.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(imageview[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            list.add(imageView);
            adapter.notifyDataSetChanged();
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
               /* Log.e("tag", "onPageScrolled: " );
                Log.e("tag", "positionOffset: "+positionOffset );
                Log.e("tag", "positionOffsetPixels: "+positionOffsetPixels );
                if (position==2&&positionOffsetPixels>10&&positionOffset>10){
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }*/

            }
            @Override
            public void onPageSelected(int position) {
                if (position==2){
                    feature_open.setVisibility(View.VISIBLE);
                    feature_open.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e("tag", "onPageScrollStateChanged: " );
            }
        });
        tiaoguo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return inflate;
    }



}

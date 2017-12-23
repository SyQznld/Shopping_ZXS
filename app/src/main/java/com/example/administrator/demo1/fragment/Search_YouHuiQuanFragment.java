package com.example.administrator.demo1.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.demo1.R;
import com.example.administrator.demo1.view.SearchLiuShiText;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search_YouHuiQuanFragment extends Fragment {

    SearchLiuShiText search_youhui_text;
    String[] arr = {"199减150", "199减100", "99减10", "200减110", "188减15", "400减50", "200减10", "199减20"};
    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search__you_hui_quan, container, false);
        search_youhui_text = (SearchLiuShiText) view.findViewById(R.id.search_youhui_text);
        initLiuShiTextView();

        return view;
    }

    private void initLiuShiTextView() {
        LayoutInflater inflater = LayoutInflater.from(context);
        for (int i = 0; i < arr.length; i++) {
            TextView view = (TextView) inflater.inflate(R.layout.search_liushi_item, search_youhui_text, false);
            view.setText(arr[i]);
            search_youhui_text.addView(view);
            //流式布局 TextView点击事件
            textClick(view);
        }

    }

    private void textClick(TextView view) {
    }
}

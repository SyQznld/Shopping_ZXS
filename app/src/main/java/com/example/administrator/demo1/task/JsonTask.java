package com.example.administrator.demo1.task;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.example.administrator.demo1.bean.XGoods;
import com.example.administrator.demo1.utils.HttpUtils;
import com.example.administrator.demo1.utils.ParseUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */

public class JsonTask extends AsyncTask<String,Void,String>{
    public interface   JsonCallBack{
        void  jsonCallBack(List<XGoods> list);
    }
    JsonCallBack  callBack;
    public JsonTask(JsonCallBack callBack) {
        this.callBack = callBack;
    }
    @Override
    protected String doInBackground(String... params) {
        String json = HttpUtils.getJsonFromNet(params[0]);
        return json;
    }

    @Override
    protected void onPostExecute(String s) {
        if (!TextUtils.isEmpty(s)) {
            List<XGoods> list = ParseUtils.getJson(s);
            callBack.jsonCallBack(list);
        }
    }
}

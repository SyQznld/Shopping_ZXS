package com.example.administrator.demo1.task;

import android.os.AsyncTask;

import com.example.administrator.demo1.myinterface.MyInterface;
import com.example.administrator.demo1.utils.HttpUtils;


/**
 * Created by Administrator on 2016/10/25.
 */

public class PopJsonTask extends AsyncTask<String,Void,String>{
    MyInterface.JsonCallBack callBack;

    public PopJsonTask(MyInterface.JsonCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected String doInBackground(String... params) {
        String json = HttpUtils.getJsonFromNet(params[0]);
        return json;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            callBack.jsonCallBack(s);
        }
    }
}

package com.example.administrator.demo1.utils;

import com.example.administrator.demo1.bean.XClassesBean;
import com.example.administrator.demo1.bean.XGoods;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */
//private String id;
//private String site_name;
//private String brand;
//private String title;
//private String pic_url;
//private String price_info;
//private String update_time;
public class ParseUtils {
    public static List<XGoods> getJson(String json) {
        List<XGoods> list = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray array = obj.getJSONArray("product");
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj2 = array.getJSONObject(i);
                String id = obj2.getString("id");
                String site_name = obj2.getString("site_name");
                String brand = obj2.getString("brand");
                String title = obj2.getString("title");
                String pic_url = obj2.getString("pic_url");
                String price_info = obj2.getString("price_info");
                String update_time = obj2.getString("update_time");
                String site_icon = obj2.getString("site_icon");
                String keyword = obj2.getString("keyword");
                String class_name = obj2.getString("class_name");
                String class_id = obj2.getString("class_id");
                String site_id = obj2.getString("site_id");
                String url = obj2.getString("url");
                XGoods bean = new XGoods(id, site_name, brand, title, pic_url, price_info, update_time,site_icon,keyword,class_name,site_id,class_id,url);
                list.add(bean);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<XClassesBean> getClassesList(String json) {
        List<XClassesBean> list = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray array = obj.getJSONArray("classes");
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj2 = array.getJSONObject(i);
                String class_id = obj2.getString("class_id");
                String name = obj2.getString("name");
                String sum = obj2.getString("sum");
                XClassesBean bean = new XClassesBean(class_id,name,sum);
                list.add(bean);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<XClassesBean> getClassesListRight(String json,int position) {
        List<XClassesBean> list = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray classes = obj.getJSONArray("classes");
            for (int i = 0; i < classes.length(); i++) {
                JSONObject obj2 = classes.getJSONObject(i);
                JSONArray next = obj2.getJSONArray("next");
                for (int j = 0; j < next.length(); j++) {
                    JSONObject obj3 = next.getJSONObject(j);
                    String class_id = obj3.getString("class_id");
                    String name = obj3.getString("name");
                    String sum = obj3.getString("sum");
                    XClassesBean bean = new XClassesBean(class_id,name,sum);
                    list.add(bean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> getStringList(String json) {
        List<String> list = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(json);
            JSONObject obj2 = obj.getJSONObject("letter_brands");
            JSONArray all = obj2.getJSONArray("all");
            for (int i = 0; i < all.length(); i++) {
                JSONObject objAll = all.getJSONObject(i);
                String abbr7 = objAll.getString("abbr");
                list.add(abbr7);
            }
            JSONArray arr1 = obj2.getJSONArray("A-D");
            for (int i = 0; i < arr1.length(); i++) {
                JSONObject obj3 = arr1.getJSONObject(i);
                String abbr = obj3.getString("abbr");
                list.add(abbr);
            }
            JSONArray arr2 = obj2.getJSONArray("I-L");
            for (int i = 0; i < arr2.length(); i++) {
                JSONObject objI = arr2.getJSONObject(i);
                String abbr1 = objI.getString("abbr");
                list.add(abbr1);
            }
            JSONArray arr3 = obj2.getJSONArray("Y-Z");
            for (int i = 0; i < arr3.length(); i++) {
                JSONObject objYZ = arr3.getJSONObject(i);
                String abbr2 = objYZ.getString("abbr");
                list.add(abbr2);
            }
            JSONArray arr4 = obj2.getJSONArray("U-X");
            for (int i = 0; i < arr4.length(); i++) {
                JSONObject objUX = arr4.getJSONObject(i);
                String abbr3 = objUX.getString("abbr");
                list.add(abbr3);
            }
            JSONArray arr5 = obj2.getJSONArray("Q-T");
            for (int i = 0; i < arr5.length(); i++) {
                JSONObject objQT = arr5.getJSONObject(i);
                String abbr4 = objQT.getString("abbr");
                list.add(abbr4);
            }
            JSONArray arr6 = obj2.getJSONArray("M-P");
            for (int i = 0; i < arr6.length(); i++) {
                JSONObject objMP = arr6.getJSONObject(i);
                String abbr5 = objMP.getString("abbr");
                list.add(abbr5);
            }
            JSONArray arr7 = obj2.getJSONArray("E-H");
            for (int i = 0; i < arr7.length(); i++) {
                JSONObject objEH = arr7.getJSONObject(i);
                String abbr6 = objEH.getString("abbr");
                list.add(abbr6);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}

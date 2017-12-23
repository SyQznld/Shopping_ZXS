package com.example.administrator.demo1.bean;

/**
 * Created by Administrator on 2016/10/25.
 */

public class XGoods {
    private String id;
    private String site_name;
    private String brand;
    private String title;
    private String pic_url;
    private String price_info;
    private String update_time;
    private String site_icon;
    private String keyword;
    private String class_name;
    private String site_id;
    private String class_id;
    private String url;

    public XGoods(String id, String site_name, String brand, String title, String pic_url, String price_info, String update_time, String site_icon, String keyword, String class_name, String site_id, String class_id, String url) {
        this.id = id;
        this.site_name = site_name;
        this.brand = brand;
        this.title = title;
        this.pic_url = pic_url;
        this.price_info = price_info;
        this.update_time = update_time;
        this.site_icon = site_icon;
        this.keyword = keyword;
        this.class_name = class_name;
        this.site_id = site_id;
        this.class_id = class_id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getPrice_info() {
        return price_info;
    }

    public void setPrice_info(String price_info) {
        this.price_info = price_info;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getSite_icon() {
        return site_icon;
    }

    public void setSite_icon(String site_icon) {
        this.site_icon = site_icon;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

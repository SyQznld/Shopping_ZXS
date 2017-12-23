package com.example.administrator.demo1.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2016/10/26.
 */
@Entity
public class ZbYouHuiJuanBean {
    @Id(autoincrement = true)
    long id;
    String img_url;
    String discount_info;
    String discount_arg;
    String end_time;
    String site_url;
    String registerName;
    String url_site;
    String site_name;
    String taken_num;
    String left_num;
    String discount_range;
    @Generated(hash = 580909316)
    public ZbYouHuiJuanBean(long id, String img_url, String discount_info,
            String discount_arg, String end_time, String site_url,
            String registerName, String url_site, String site_name,
            String taken_num, String left_num, String discount_range) {
        this.id = id;
        this.img_url = img_url;
        this.discount_info = discount_info;
        this.discount_arg = discount_arg;
        this.end_time = end_time;
        this.site_url = site_url;
        this.registerName = registerName;
        this.url_site = url_site;
        this.site_name = site_name;
        this.taken_num = taken_num;
        this.left_num = left_num;
        this.discount_range = discount_range;
    }
    @Generated(hash = 1507632806)
    public ZbYouHuiJuanBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getImg_url() {
        return this.img_url;
    }
    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
    public String getDiscount_info() {
        return this.discount_info;
    }
    public void setDiscount_info(String discount_info) {
        this.discount_info = discount_info;
    }
    public String getDiscount_arg() {
        return this.discount_arg;
    }
    public void setDiscount_arg(String discount_arg) {
        this.discount_arg = discount_arg;
    }
    public String getEnd_time() {
        return this.end_time;
    }
    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
    public String getSite_url() {
        return this.site_url;
    }
    public void setSite_url(String site_url) {
        this.site_url = site_url;
    }
    public String getRegisterName() {
        return this.registerName;
    }
    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }
    public String getUrl_site() {
        return this.url_site;
    }
    public void setUrl_site(String url_site) {
        this.url_site = url_site;
    }
    public String getSite_name() {
        return this.site_name;
    }
    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }
    public String getTaken_num() {
        return this.taken_num;
    }
    public void setTaken_num(String taken_num) {
        this.taken_num = taken_num;
    }
    public String getLeft_num() {
        return this.left_num;
    }
    public void setLeft_num(String left_num) {
        this.left_num = left_num;
    }
    public String getDiscount_range() {
        return this.discount_range;
    }
    public void setDiscount_range(String discount_range) {
        this.discount_range = discount_range;
    }
}

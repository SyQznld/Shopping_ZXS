package com.example.administrator.demo1.bean;

/**
 * Created by Administrator on 2016/10/25.
 */

public class XClassesBean {
    private String class_id;
    private String name;
    private String sum;

    public XClassesBean(String class_id, String name, String sum) {
        this.class_id = class_id;
        this.name = name;
        this.sum = sum;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}

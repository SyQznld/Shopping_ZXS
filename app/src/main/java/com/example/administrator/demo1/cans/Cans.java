package com.example.administrator.demo1.cans;

/**
 * Created by Administrator on 2016/10/25.
 */

public class Cans {
    //国内第一页
    public static String getGuoNeiPath(int index) {
        //index从0开始
        String path = "http://app.gwdang.com/app/price_zhi?ps=20&is_haitao=" + index + "&format=json&img_width=375&current_time=1477113152&version=2";
        return path;
    }

    //国内第二页
    public static String GuoNeiSecondPage() {
        String path = "http://app.gwdang.com/app/price_zhi?update_time=1477490237&ps=20&is_haitao=0&format=json&img_width=344&version=2";
        return path;
    }

    //国内第三夜
    public static String GuoNeiThridPage() {
        String path = "http://app.gwdang.com/app/price_zhi?update_time=1477488836&ps=20&is_haitao=0&format=json&img_width=344&version=2";
        return path;
    }

    //海淘 第一页
    public static String getPath(int index) {
        String path = "http://app.gwdang.com/app/price_zhi?ps=20&is_haitao=" + index + "&format=json&img_width=375&current_time=1477111800&version=2";
        return path;
    }

    //海淘 第二页
    public static String SecondPage() {
        String path = "http://app.gwdang.com/app/price_zhi?update_time=1477487745&ps=20&is_haitao=1&format=json&img_width=344&version=2";
        return path;
    }

    //海淘 第三页
    public static String ThirdPage() {
        String path = "http://app.gwdang.com/app/price_zhi?update_time=1477478280&ps=20&is_haitao=1&format=json&img_width=344&version=2";
        return path;
    }

    //详情页
    public static String DetailPage(String key) {
        String path = "http://app.gwdang.com/app/price_zhi?ps=20&is_haitao=0&format=json&img_width=375&keyword=" + key + "&version=2";
        return path;
    }

    public static String getYangMao() {
        String path = "http://app.gwdang.com/app/price_zhi?ps=20&is_haitao=2&format=json&img_width=517&current_time=1477112529&version=2\n";
        return path;
    }
    //商城
//    http://app.gwdang.com/app/price_zhi?site_id=83&ps=20&is_haitao=0&format=json&img_width=375&version=2
    public static String DetailShop(String site_id){
        String path = "http://app.gwdang.com/app/price_zhi?site_id="+site_id+"&ps=20&is_haitao=0&format=json&img_width=375&version=2";
        return path;
    }
    //分类
    public static String DetailClass(String key){
        String path = "http://app.gwdang.com/app/price_zhi?class_id="+key+"&ps=20&is_haitao=0&format=json&img_width=375&version=2";
        return path;
    }
    //品牌
    public static String DetailBrand(String key){
        String path = "http://app.gwdang.com/app/price_zhi?brand="+key+"&ps=20&is_haitao=0&format=json&img_width=375&current_time=1477796422&version=2";
        return path;
    }
    //关键词
    public static String DetailKeyWord(String key){
        String path = "http://app.gwdang.com/app/price_zhi?ps=20&is_haitao=0&format=json&img_width=375&keyword="+key+"&version=2";
        return path;
    }
}

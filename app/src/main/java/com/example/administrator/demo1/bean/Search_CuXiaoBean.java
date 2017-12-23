package com.example.administrator.demo1.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */
public class Search_CuXiaoBean {


    /**
     * promotion : [{"promo_id":"4796846","status_id":"13260081","site_id":"83","promo_type":"1","zdm_tag":"1","promo_class_id":"4","class_id_1":"00000000","class_id_2":"00000000","class_id_3":"00000000","class_id_4":"00000000","class_id_1_crc64":"24090400","class_id_2_crc64":"00000000","class_id_3_crc64":"00000000","class_id_4_crc64":"00000000","title":"圣康尼跑鞋低至3折 领券满800减80","keyword":"圣康尼跑鞋低至3折 领券满800减80","sub_title":"","start_time":"2016-11-11 00:00:00","end_time":"2016-11-11 23:59:00","url":"https://saucony.tmall.com/campaign-3745-28.htm","pic_url":"http://img.gwdang.com/c_zoom,w_1052/p_4796846.png","sub_url_list":"","download":"0","downloaded":"1","random_sort":"2340","create_time":"2016-11-01 02:16:33","status":"1","selected":"0","is_read":"0","why_not":"","editor":"48","edit_time":"2016-11-01 11:17:48","up_count":"10","price":"0","show":"0","click":"0","order":"0","sales":"0","commission":"0","brand":"SAUCONY/索康尼","brand_id":"72938","brand_id_1":"0","brand_id_2":"0","brand_id_3":"0","brand_id_4":"0","promo_tag":"0","product_cnt":"145","product_img_cnt":"0","isOffline":"0","discount_keyword":"","cate_keyword":"","brand_keyword":"","expect_order":"0","expect_sales":"0","expect_commission":"0","promo_status":"1","self_support":"1","update_time":"2016-11-01 13:18:05","site_name":"天猫","site_icon":"http://s1.gwdang.com/images/favicon/83.png","go_url":"http://www.gwdang.com/union/go/?site_id=83&target_url=https%3A%2F%2Fsaucony.tmall.com%2Fcampaign-3745-28.htm&union=app&column=activity_48_4796846&crc64=1","highlight_title":"圣康尼跑鞋低至3<\/em>折 领券满800<\/em>减80<\/em>"}]
     * types : [{"p_type":"1","sum":"1","name":"特价"}]
     * sites : [{"site_id":"83","sum":"1","name":"天猫"}]
     * classes : [{"class_id":"4","sum":"1","name":"服饰箱包"}]
     * total_count : 1
     * new_count : 0
     */

    private String total_count;
    private String new_count;
    /**
     * promo_id : 4796846
     * status_id : 13260081
     * site_id : 83
     * promo_type : 1
     * zdm_tag : 1
     * promo_class_id : 4
     * class_id_1 : 00000000
     * class_id_2 : 00000000
     * class_id_3 : 00000000
     * class_id_4 : 00000000
     * class_id_1_crc64 : 24090400
     * class_id_2_crc64 : 00000000
     * class_id_3_crc64 : 00000000
     * class_id_4_crc64 : 00000000
     * title : 圣康尼跑鞋低至3折 领券满800减80
     * keyword : 圣康尼跑鞋低至3折 领券满800减80
     * sub_title :
     * start_time : 2016-11-11 00:00:00
     * end_time : 2016-11-11 23:59:00
     * url : https://saucony.tmall.com/campaign-3745-28.htm
     * pic_url : http://img.gwdang.com/c_zoom,w_1052/p_4796846.png
     * sub_url_list :
     * download : 0
     * downloaded : 1
     * random_sort : 2340
     * create_time : 2016-11-01 02:16:33
     * status : 1
     * selected : 0
     * is_read : 0
     * why_not :
     * editor : 48
     * edit_time : 2016-11-01 11:17:48
     * up_count : 10
     * price : 0
     * show : 0
     * click : 0
     * order : 0
     * sales : 0
     * commission : 0
     * brand : SAUCONY/索康尼
     * brand_id : 72938
     * brand_id_1 : 0
     * brand_id_2 : 0
     * brand_id_3 : 0
     * brand_id_4 : 0
     * promo_tag : 0
     * product_cnt : 145
     * product_img_cnt : 0
     * isOffline : 0
     * discount_keyword :
     * cate_keyword :
     * brand_keyword :
     * expect_order : 0
     * expect_sales : 0
     * expect_commission : 0
     * promo_status : 1
     * self_support : 1
     * update_time : 2016-11-01 13:18:05
     * site_name : 天猫
     * site_icon : http://s1.gwdang.com/images/favicon/83.png
     * go_url : http://www.gwdang.com/union/go/?site_id=83&target_url=https%3A%2F%2Fsaucony.tmall.com%2Fcampaign-3745-28.htm&union=app&column=activity_48_4796846&crc64=1
     * highlight_title : 圣康尼跑鞋低至3</em>折 领券满800</em>减80</em>
     */

    private List<PromotionBean> promotion;
    /**
     * p_type : 1
     * sum : 1
     * name : 特价
     */

    private List<TypesBean> types;
    /**
     * site_id : 83
     * sum : 1
     * name : 天猫
     */

    private List<SitesBean> sites;
    /**
     * class_id : 4
     * sum : 1
     * name : 服饰箱包
     */

    private List<ClassesBean> classes;

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }

    public String getNew_count() {
        return new_count;
    }

    public void setNew_count(String new_count) {
        this.new_count = new_count;
    }

    public List<PromotionBean> getPromotion() {
        return promotion;
    }

    public void setPromotion(List<PromotionBean> promotion) {
        this.promotion = promotion;
    }

    public List<TypesBean> getTypes() {
        return types;
    }

    public void setTypes(List<TypesBean> types) {
        this.types = types;
    }

    public List<SitesBean> getSites() {
        return sites;
    }

    public void setSites(List<SitesBean> sites) {
        this.sites = sites;
    }

    public List<ClassesBean> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassesBean> classes) {
        this.classes = classes;
    }

    public static class PromotionBean {
        private String promo_id;
        private String status_id;
        private String site_id;
        private String promo_type;
        private String zdm_tag;
        private String promo_class_id;
        private String class_id_1;
        private String class_id_2;
        private String class_id_3;
        private String class_id_4;
        private String class_id_1_crc64;
        private String class_id_2_crc64;
        private String class_id_3_crc64;
        private String class_id_4_crc64;
        private String title;
        private String keyword;
        private String sub_title;
        private String start_time;
        private String end_time;
        private String url;
        private String pic_url;
        private String sub_url_list;
        private String download;
        private String downloaded;
        private String random_sort;
        private String create_time;
        private String status;
        private String selected;
        private String is_read;
        private String why_not;
        private String editor;
        private String edit_time;
        private String up_count;
        private String price;
        private String show;
        private String click;
        private String order;
        private String sales;
        private String commission;
        private String brand;
        private String brand_id;
        private String brand_id_1;
        private String brand_id_2;
        private String brand_id_3;
        private String brand_id_4;
        private String promo_tag;
        private String product_cnt;
        private String product_img_cnt;
        private String isOffline;
        private String discount_keyword;
        private String cate_keyword;
        private String brand_keyword;
        private String expect_order;
        private String expect_sales;
        private String expect_commission;
        private String promo_status;
        private String self_support;
        private String update_time;
        private String site_name;
        private String site_icon;
        private String go_url;
        private String highlight_title;

        public String getPromo_id() {
            return promo_id;
        }

        public void setPromo_id(String promo_id) {
            this.promo_id = promo_id;
        }

        public String getStatus_id() {
            return status_id;
        }

        public void setStatus_id(String status_id) {
            this.status_id = status_id;
        }

        public String getSite_id() {
            return site_id;
        }

        public void setSite_id(String site_id) {
            this.site_id = site_id;
        }

        public String getPromo_type() {
            return promo_type;
        }

        public void setPromo_type(String promo_type) {
            this.promo_type = promo_type;
        }

        public String getZdm_tag() {
            return zdm_tag;
        }

        public void setZdm_tag(String zdm_tag) {
            this.zdm_tag = zdm_tag;
        }

        public String getPromo_class_id() {
            return promo_class_id;
        }

        public void setPromo_class_id(String promo_class_id) {
            this.promo_class_id = promo_class_id;
        }

        public String getClass_id_1() {
            return class_id_1;
        }

        public void setClass_id_1(String class_id_1) {
            this.class_id_1 = class_id_1;
        }

        public String getClass_id_2() {
            return class_id_2;
        }

        public void setClass_id_2(String class_id_2) {
            this.class_id_2 = class_id_2;
        }

        public String getClass_id_3() {
            return class_id_3;
        }

        public void setClass_id_3(String class_id_3) {
            this.class_id_3 = class_id_3;
        }

        public String getClass_id_4() {
            return class_id_4;
        }

        public void setClass_id_4(String class_id_4) {
            this.class_id_4 = class_id_4;
        }

        public String getClass_id_1_crc64() {
            return class_id_1_crc64;
        }

        public void setClass_id_1_crc64(String class_id_1_crc64) {
            this.class_id_1_crc64 = class_id_1_crc64;
        }

        public String getClass_id_2_crc64() {
            return class_id_2_crc64;
        }

        public void setClass_id_2_crc64(String class_id_2_crc64) {
            this.class_id_2_crc64 = class_id_2_crc64;
        }

        public String getClass_id_3_crc64() {
            return class_id_3_crc64;
        }

        public void setClass_id_3_crc64(String class_id_3_crc64) {
            this.class_id_3_crc64 = class_id_3_crc64;
        }

        public String getClass_id_4_crc64() {
            return class_id_4_crc64;
        }

        public void setClass_id_4_crc64(String class_id_4_crc64) {
            this.class_id_4_crc64 = class_id_4_crc64;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getSub_title() {
            return sub_title;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getSub_url_list() {
            return sub_url_list;
        }

        public void setSub_url_list(String sub_url_list) {
            this.sub_url_list = sub_url_list;
        }

        public String getDownload() {
            return download;
        }

        public void setDownload(String download) {
            this.download = download;
        }

        public String getDownloaded() {
            return downloaded;
        }

        public void setDownloaded(String downloaded) {
            this.downloaded = downloaded;
        }

        public String getRandom_sort() {
            return random_sort;
        }

        public void setRandom_sort(String random_sort) {
            this.random_sort = random_sort;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSelected() {
            return selected;
        }

        public void setSelected(String selected) {
            this.selected = selected;
        }

        public String getIs_read() {
            return is_read;
        }

        public void setIs_read(String is_read) {
            this.is_read = is_read;
        }

        public String getWhy_not() {
            return why_not;
        }

        public void setWhy_not(String why_not) {
            this.why_not = why_not;
        }

        public String getEditor() {
            return editor;
        }

        public void setEditor(String editor) {
            this.editor = editor;
        }

        public String getEdit_time() {
            return edit_time;
        }

        public void setEdit_time(String edit_time) {
            this.edit_time = edit_time;
        }

        public String getUp_count() {
            return up_count;
        }

        public void setUp_count(String up_count) {
            this.up_count = up_count;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getShow() {
            return show;
        }

        public void setShow(String show) {
            this.show = show;
        }

        public String getClick() {
            return click;
        }

        public void setClick(String click) {
            this.click = click;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public String getBrand_id_1() {
            return brand_id_1;
        }

        public void setBrand_id_1(String brand_id_1) {
            this.brand_id_1 = brand_id_1;
        }

        public String getBrand_id_2() {
            return brand_id_2;
        }

        public void setBrand_id_2(String brand_id_2) {
            this.brand_id_2 = brand_id_2;
        }

        public String getBrand_id_3() {
            return brand_id_3;
        }

        public void setBrand_id_3(String brand_id_3) {
            this.brand_id_3 = brand_id_3;
        }

        public String getBrand_id_4() {
            return brand_id_4;
        }

        public void setBrand_id_4(String brand_id_4) {
            this.brand_id_4 = brand_id_4;
        }

        public String getPromo_tag() {
            return promo_tag;
        }

        public void setPromo_tag(String promo_tag) {
            this.promo_tag = promo_tag;
        }

        public String getProduct_cnt() {
            return product_cnt;
        }

        public void setProduct_cnt(String product_cnt) {
            this.product_cnt = product_cnt;
        }

        public String getProduct_img_cnt() {
            return product_img_cnt;
        }

        public void setProduct_img_cnt(String product_img_cnt) {
            this.product_img_cnt = product_img_cnt;
        }

        public String getIsOffline() {
            return isOffline;
        }

        public void setIsOffline(String isOffline) {
            this.isOffline = isOffline;
        }

        public String getDiscount_keyword() {
            return discount_keyword;
        }

        public void setDiscount_keyword(String discount_keyword) {
            this.discount_keyword = discount_keyword;
        }

        public String getCate_keyword() {
            return cate_keyword;
        }

        public void setCate_keyword(String cate_keyword) {
            this.cate_keyword = cate_keyword;
        }

        public String getBrand_keyword() {
            return brand_keyword;
        }

        public void setBrand_keyword(String brand_keyword) {
            this.brand_keyword = brand_keyword;
        }

        public String getExpect_order() {
            return expect_order;
        }

        public void setExpect_order(String expect_order) {
            this.expect_order = expect_order;
        }

        public String getExpect_sales() {
            return expect_sales;
        }

        public void setExpect_sales(String expect_sales) {
            this.expect_sales = expect_sales;
        }

        public String getExpect_commission() {
            return expect_commission;
        }

        public void setExpect_commission(String expect_commission) {
            this.expect_commission = expect_commission;
        }

        public String getPromo_status() {
            return promo_status;
        }

        public void setPromo_status(String promo_status) {
            this.promo_status = promo_status;
        }

        public String getSelf_support() {
            return self_support;
        }

        public void setSelf_support(String self_support) {
            this.self_support = self_support;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getSite_name() {
            return site_name;
        }

        public void setSite_name(String site_name) {
            this.site_name = site_name;
        }

        public String getSite_icon() {
            return site_icon;
        }

        public void setSite_icon(String site_icon) {
            this.site_icon = site_icon;
        }

        public String getGo_url() {
            return go_url;
        }

        public void setGo_url(String go_url) {
            this.go_url = go_url;
        }

        public String getHighlight_title() {
            return highlight_title;
        }

        public void setHighlight_title(String highlight_title) {
            this.highlight_title = highlight_title;
        }
    }

    public static class TypesBean {
        private String p_type;
        private String sum;
        private String name;

        public String getP_type() {
            return p_type;
        }

        public void setP_type(String p_type) {
            this.p_type = p_type;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class SitesBean {
        private String site_id;
        private String sum;
        private String name;

        public String getSite_id() {
            return site_id;
        }

        public void setSite_id(String site_id) {
            this.site_id = site_id;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ClassesBean {
        private String class_id;
        private String sum;
        private String name;

        public String getClass_id() {
            return class_id;
        }

        public void setClass_id(String class_id) {
            this.class_id = class_id;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

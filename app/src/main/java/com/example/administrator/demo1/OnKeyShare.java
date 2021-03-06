package com.example.administrator.demo1;

import android.content.Context;
import android.view.View;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Administrator on 2016/10/28.
 */
public class OnKeyShare {

    //指定平台分享，自己完成分享界面
    public  static void showShare1(Context context,String name) {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        //设置指定平台分享
        oks.setPlatform(name);
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");


        //true:直接分享
        //false:先展示分享的编辑界面再分享，默认false
        oks.setSilent(false);

        //分享的地址经纬度
//        oks.setLatitude();
//        oks.setLongitude();

        //只在短信和邮箱有用
        // oks.setAddress("");

        //分享多张图片，最多9个
        // oks.setImageArray();

        //设置分享音乐
        // oks.setMusicUrl();

        //设置分享视频
        //  oks.setVideoUrl();

        //设置编辑页面的显示模式为Dialog模式，目前版本生效
        oks.setDialogMode();
        // 启动分享GUI
        oks.show(context);
    }



    //sharesdk的一键分享功能
     public  static  void showShare(Context context) {
        ShareSDK.initSDK(context);

        OnekeyShare oks = new OnekeyShare();

        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");


        //true:直接分享
        //false:先展示分享的编辑界面再分享，默认false
        oks.setSilent(false);

        //分享的地址经纬度
//        oks.setLatitude();
//        oks.setLongitude();

        //只在短信和邮箱有用
        // oks.setAddress("");

        //分享多张图片，最多9个
        // oks.setImageArray();

        //设置分享音乐
        // oks.setMusicUrl();

        //设置分享视频
        //  oks.setVideoUrl();

        //设置编辑页面的显示模式为Dialog模式，目前版本生效
        oks.setDialogMode();
        // 启动分享GUI
        oks.show(context);
    }
}

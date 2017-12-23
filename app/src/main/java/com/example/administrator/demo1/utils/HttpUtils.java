package com.example.administrator.demo1.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {
	// 网络下载Json数据
	public static String getJsonFromNet(String path) {
		String result = "";

		HttpURLConnection conn;
		try {
			URL url = new URL(path);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5 * 1000);
			conn.setReadTimeout(5 * 1000);
			conn.connect();
			if (conn.getResponseCode() == 200) {
				InputStream inputStream = conn.getInputStream();
				byte[] bytes = new byte[1024];
				int num;
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				while ((num = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, num);
				}
				byte[] array = outputStream.toByteArray();
				result = new String(array);
				return result;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 下载网络图片
	public static Bitmap getBitmapFromNet(String path) {
		HttpURLConnection conn;
		try {
			URL url = new URL(path);
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(5 * 1000);
			conn.setConnectTimeout(5 * 1000);
			conn.connect();
			if (conn.getResponseCode() == 200) {

				Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream());
				return bitmap;
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

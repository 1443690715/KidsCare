package com.lanbiao.youxiaoyunteacher.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

import com.lanbiao.youxiaoyunteacher.entity.WebSite;
import com.lanbiao.youxiaoyunteacher.http.HttpUtils;

public class MyClassService {
	private static final String TAG = "MyClassService";
	private static WebSite url;
	private static String path;

	public static String myClassGetId(String tid) {
		// �ύ���ݵ�������
		url = new WebSite();
		int type = 2;
		try {
			// �ӿ�
			path = url.getPath() + url.getStudent() + url.getTeacherid() + tid
					+ url.getType() + type;
			Log.v(TAG, "~~~~~~~~" + path);
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestMethod("GET");
			int code = conn.getResponseCode();
			if (code == 200) {
				// ����ɹ�
				InputStream is = conn.getInputStream();
				String text = HttpUtils.readInputStream(is);
				return text;
			} else {
				// ����ʧ��
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

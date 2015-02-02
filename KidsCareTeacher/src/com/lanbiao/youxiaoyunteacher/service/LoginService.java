package com.lanbiao.youxiaoyunteacher.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.widget.Toast;

import com.lanbiao.youxiaoyunteacher.entity.WebSite;
import com.lanbiao.youxiaoyunteacher.http.HttpUtils;

public class LoginService {
	private static final String tag = "MainActivity";
	private static WebSite url;
	private static String path;
	private static Context context;

	public static Context getContext() {
		return context;
	}

	/**
	 * ����
	 * 
	 * @param context
	 * @param username
	 * @param password
	 */
	public static void saveUser(Context context, String username,
			String password) {
		SharedPreferences sp = context.getSharedPreferences("config",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("username", username);
		editor.putString("password", password);
		editor.commit();
	}

	/**
	 * ֻ�����û�
	 * 
	 * @param context
	 * @param username
	 */
	public static void saveUserName(Context context, String username,
			String password) {
		SharedPreferences sp = context.getSharedPreferences("config",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("username", username);
		editor.putString("password", password);
		editor.commit();
	}

	/**
	 * ����nullΪ��½ʧ��
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public static String login(String username, String password) {
		// �ύ���ݵ�������
		url = new WebSite();
		try {
			// �ӿ�
			path = url.getPath() + url.getLogin() + url.getUsername()
					+ username + url.getPassword() + password;
			// Log.v(tag, path);
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestMethod("GET");
			int code = conn.getResponseCode();
			if (code == 200) {
				// ����ɹ�
				InputStream is = conn.getInputStream();
				String text = HttpUtils.readInputStream(is);
				// Log.v(tag, "text-=-----" + text);
				return text;
			} else {
				// ����ʧ��
				Log.v(tag, "����ʧ��");
				return "����ʧ��";
			}
		} catch (Exception e) {
			// Toast.makeText(Context, "�������ѶϿ�����...", 0).show();
			Log.v(tag, "�������ѶϿ�����...");
			// e.printStackTrace();
		}
		return "����ʧ��";
	}
}

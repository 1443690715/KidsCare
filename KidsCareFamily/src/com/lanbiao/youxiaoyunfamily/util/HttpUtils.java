package com.lanbiao.youxiaoyunfamily.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class HttpUtils {
	private static final String TAG = "HttpUtils";

	/**
	 * ��������������ת�����ַ���
	 * 
	 * @param is
	 * @return
	 */
	public static String readInputStream(InputStream is) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int len = 0;
			byte[] buffer = new byte[1024];
			while ((len = is.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			is.close();
			baos.close();
			byte[] result = baos.toByteArray();
			// ���Ž���result������ַ���
			String temp = new String(result);
			return temp;
		} catch (Exception e) {
			return "��ȡʧ��";
		}
	}

	/**
	 * ����url,���������ݸ��ͻ���
	 * 
	 * @param url_path
	 * @return
	 */
	public static String getJsonContent(String url_path) {
		try {
			Log.v(TAG, "---" + url_path);
			URL url = new URL(url_path);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(5 * 1000);
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			int code = connection.getResponseCode();
			if (code == 200) {
				// ��ʾ���ӳɹ�
				return changeInputStream(connection.getInputStream());
			}
		} catch (Exception e) {
			Log.v(TAG, "���ӳ�ʱ...");

			e.printStackTrace();
		}
		return url_path;
	}

	private static String changeInputStream(InputStream inputStream) {
		String jsonString = "";
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		int len = 0;
		byte[] data = new byte[1024];
		try {
			while ((len = inputStream.read(data)) != -1) {
				outputStream.write(data, 0, len);// ��0��ʼ
			}
			jsonString = new String(outputStream.toByteArray());// ����һ���ַ���
			inputStream.close();
			outputStream.close();
		} catch (Exception e) {
			Log.v(TAG, "��ȡʧ��...");
			// e.printStackTrace();
		}
		return jsonString;
	}
}

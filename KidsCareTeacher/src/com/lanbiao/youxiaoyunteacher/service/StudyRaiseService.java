package com.lanbiao.youxiaoyunteacher.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

import com.lanbiao.youxiaoyunteacher.entity.WebSite;
import com.lanbiao.youxiaoyunteacher.http.HttpUtils;

public class StudyRaiseService {
	private static final String TAG = "StudyRaiseService";
	private static WebSite webSite;
	private static String path;

	/**
	 * һ���˵�
	 * 
	 * @return
	 */
	public static String getOneMeu() {
		try {
			webSite = new WebSite();
			path = webSite.getPath() + webSite.getSturaiseonemeu();
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			int code = conn.getResponseCode();
			if (code == 200) {
				// ����ɹ�
				InputStream is = conn.getInputStream();
				String text = HttpUtils.readInputStream(is);
				return text;
			} else {
				return "����ʧ��";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "����ʧ��";
	}

	/**
	 * �����˵�
	 * 
	 * @return
	 */
	public static String getSecondByIdMeu(String secondId) {
		try {
			webSite = new WebSite();
			path = webSite.getPath() + webSite.getSturaisetwomeu()
					+ webSite.getStrsecondid() + secondId;
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			int code = conn.getResponseCode();
			if (code == 200) {
				// ����ɹ�
				InputStream is = conn.getInputStream();
				String text = HttpUtils.readInputStream(is);
				return text;
			} else {
				return "����ʧ��";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "����ʧ��";
	}

	public static String getDetailData(String detailid) {
		try {
			webSite = new WebSite();
			path = webSite.getPath() + webSite.getSturaisedetail()
					+ webSite.getStrdetailid() + detailid;
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			int code = conn.getResponseCode();
			if (code == 200) {
				// ����ɹ�
				InputStream is = conn.getInputStream();
				String text = HttpUtils.readInputStream(is);
				return text;
			} else {
				return "����ʧ��";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "����ʧ��";
	}

	/**
	 * ��������һ���˵�
	 * 
	 * @param strSchoolid
	 * @return
	 */
	public static String getChildCareMenu(String strSchoolid) {
		try {
			webSite = new WebSite();
			path = webSite.getPath() + webSite.getQueryshoppingmenu()
					+ webSite.getStrSchoolid() + strSchoolid;
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(5 * 1000);
			int code = conn.getResponseCode();
			if (code == 200) {
				InputStream is = conn.getInputStream();
				String text = HttpUtils.readInputStream(is);
				return text;
			} else {
				return "����ʧ��!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Ո��ʧ��";
	}

	/**
	 * ��������һ���˵��µ��б���ʾ
	 * 
	 * @param secondId
	 * @return
	 */
	public static String getChildCareDetail(String secondId) {
		try {
			webSite = new WebSite();
			path = webSite.getPath() + webSite.getShoppingdetail()
					+ webSite.getSecondmunuid() + secondId;
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(5 * 1000);
			int code = conn.getResponseCode();
			if (code == 200) {
				InputStream is = conn.getInputStream();
				String text = HttpUtils.readInputStream(is);
				return text;
			} else {
				return "����ʧ��!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Ո��ʧ��";
	}

	/**
	 * �������ĵ�����
	 * 
	 * @param strshoppingid
	 * @return
	 */
	public static String getChildCareByIdDetail(String strshoppingid) {
		try {
			webSite = new WebSite();
			path = webSite.getPath() + webSite.getShoppingbyid()
					+ webSite.getShoppingid() + strshoppingid;
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(5 * 1000);
			int code = conn.getResponseCode();
			if (code == 200) {
				InputStream is = conn.getInputStream();
				String text = HttpUtils.readInputStream(is);
				return text;
			} else {
				return "����ʧ��!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Ո��ʧ��";
	}

}

package com.lanbiao.youxiaoyunfamily.cache;

import java.io.File;
import java.io.IOException;

import android.util.Log;

import com.lanbiao.youxiaoyunfamily.util.CheckCacheUtils;
import com.lanbiao.youxiaoyunfamily.util.FileUtils;
import com.lanbiao.youxiaoyunfamily.util.NetworkUtils;

public class ConfigCache {
	private static final String TAG = ConfigCache.class.getName();

	public static final int CONFIG_CACHE_MOBILE_TIMEOUT = 3600000; // 1 hour
	public static final int CONFIG_CACHE_WIFI_TIMEOUT = 300000; // 5 minute

	public static String getUrlCache(String url) {
		if (url == null) {
			return null;
		}
		String result = null;
		File file = new File(CheckCacheUtils.mSdcardDataDir + "/"
				+ getCacheDecodeString(url));
		if (file.exists() && file.isFile()) {
			long expiredTime = System.currentTimeMillis() - file.lastModified();

			Log.d(TAG, file.getAbsolutePath() + " expiredTime:" + expiredTime
					/ 60000 + "min");

			if (CheckCacheUtils.mNetWorkState != NetworkUtils.NETWORN_NONE
					&& expiredTime < 0) {
				return null;
			}
			if (CheckCacheUtils.mNetWorkState == NetworkUtils.NETWORN_WIFI
					&& expiredTime > CONFIG_CACHE_WIFI_TIMEOUT) {
				return null;
			} else if (CheckCacheUtils.mNetWorkState == NetworkUtils.NETWORN_MOBILE
					&& expiredTime > CONFIG_CACHE_MOBILE_TIMEOUT) {
				return null;
			}
			try {
				// ��ȡ���ص�����
				result = FileUtils.readTextFile(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static void setUrlCache(String data, String url) {
		File file = new File(CheckCacheUtils.mSdcardDataDir + "/"
				+ getCacheDecodeString(url));
		try {
			// �����������ݵ����̣����Ǵ����ļ�
			FileUtils.writeTextFile(file, data);
		} catch (IOException e) {
			// Log.d(TAG, "write " + file.getAbsolutePath() + " data failed!");
			e.printStackTrace();
		}
	}

	public static String getCacheDecodeString(String url) {
		// 1. ���������ַ�
		// 2. ȥ����׺���������ļ����������ͼ����(�ر���ͼƬ����Ҫ������ƴ��������е��ֻ���ͼ�⣬ȫ�����ǵĻ���ͼƬ)
		if (url != null) {
			return url.replaceAll("[.:/,%?&=]", "+").replaceAll("[+]+", "+");
		}
		return null;
	}
}

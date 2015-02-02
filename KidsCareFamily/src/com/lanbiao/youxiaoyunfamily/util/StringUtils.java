package com.lanbiao.youxiaoyunfamily.util;

public class StringUtils {
	public static String replaceUrlWithPlus(String url) {
		// 1. ���������ַ�
		// 2. ȥ����׺���������ļ����������ͼ����(�ر���ͼƬ����Ҫ������ƴ��������е��ֻ���ͼ�⣬ȫ�����ǵĻ���ͼƬ)
		if (url != null) {
			return url.replaceAll("http://(.)*?/", "")
					.replaceAll("[.:/,%?&=]", "+").replaceAll("[+]+", "+");
		}
		return null;
	}
}

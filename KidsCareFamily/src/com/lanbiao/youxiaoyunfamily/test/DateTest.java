package com.lanbiao.youxiaoyunfamily.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import junit.framework.TestCase;
import android.net.ParseException;
import android.util.Log;

public class DateTest extends TestCase {
	private static final String TAG = "DateTest";

	private String getWeek(String pTime) throws Exception {

		String Week = "";

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {

			c.setTime(format.parse(pTime));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {

			Log.v(TAG, Week += "������");
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 2) {
			Log.v(TAG, Week += "����һ");
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 3) {
			Log.v(TAG, Week += "���ڶ�");
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 4) {
			Log.v(TAG, Week += "������");
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 5) {
			Log.v(TAG, Week += "������");
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 6) {
			Log.v(TAG, Week += "������");
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 7) {
			Log.v(TAG, Week += "������");
		}

		return Week;
	}

	public void gettime() {
		try {
			getWeek("2014-03-17 16:55:29");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

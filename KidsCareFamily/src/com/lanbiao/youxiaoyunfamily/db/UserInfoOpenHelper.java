package com.lanbiao.youxiaoyunfamily.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * �������ݱ�
 * 
 * @author zhangmingxun
 * 
 */
public class UserInfoOpenHelper extends SQLiteOpenHelper {

	public UserInfoOpenHelper(Context context) {
		super(context, "userInfo.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// ������
		db.execSQL("create table userInfo (id integer primary key autoincrement,"
				+ "username varchar(20),password integer(16))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}

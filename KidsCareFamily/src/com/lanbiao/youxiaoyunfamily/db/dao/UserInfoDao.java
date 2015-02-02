package com.lanbiao.youxiaoyunfamily.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lanbiao.youxiaoyunfamily.db.UserInfoOpenHelper;
import com.lanbiao.youxiaoyunfamily.entity.UserInfo;

/**
 * userInfo�������ɾ���ġ���
 * 
 * @author zhangmingxun
 * 
 */
public class UserInfoDao {
	private static final String TAG = "UserInfoDao";
	private UserInfoOpenHelper helper;

	public UserInfoDao(Context context) {
		helper = new UserInfoOpenHelper(context);
	}

	/**
	 * 
	 * 
	 * @param name
	 * @param phone
	 */
	public void addContact(String username, String userpwd) {
		List<UserInfo> familyinfos = findAll();
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		String names = "";
		// ���ֲ�ѯ��ʽ1
		// for (UserInfo u : userinfo) {
		// Log.v(TAG, "----" + u.getuName());
		// }
		// ���ֲ�ѯ��ʽ2

		try {
			if (familyinfos.size() == 0) {
				values.put("username", username);
				values.put("password", userpwd);
				db.insert("userInfo", null, values);
				Log.v(TAG, "�������1");
				db.close();
			} else {
				for (int i = 0; i < familyinfos.size(); i++) {
					names += familyinfos.get(i).getUsername() + ",";
				}
				if (names.contains(username)) {
					Log.v(TAG, "�������-------------" + names);
				} else {
					Log.v(TAG, "�������================");
					values.put("username", username);
					values.put("password", userpwd);
					db.insert("userInfo", null, values);
					db.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.v(TAG, "�������");
		} finally {
			db.close();
		}

	}

	/**
	 * ��ѯ����
	 * 
	 * @return
	 */
	public List<UserInfo> findAll() {
		SQLiteDatabase db = helper.getReadableDatabase();
		List<UserInfo> users = new ArrayList<UserInfo>();
		Cursor cursor = db.query("userInfo", new String[] { "id", "username",
				"password" }, null, null, null, null, null);
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String uName = cursor.getString(cursor.getColumnIndex("username"));
			String uPwd = cursor.getString(cursor.getColumnIndex("password"));
			UserInfo u = new UserInfo(uName, uPwd, id);
			users.add(u);
		}
		cursor.close();
		db.close();
		return users;

	}

	/**
	 * ɾ������
	 * 
	 * @return
	 */
	public int delete() {
		SQLiteDatabase db = helper.getWritableDatabase();
		int number = db.delete("userInfo", null, null);
		db.close();
		return number;
	}

	/**
	 * ͨ��idɾ��ָ������
	 * 
	 * @param id
	 * @return
	 */
	public int delbyid(String id) {
		SQLiteDatabase db = helper.getWritableDatabase();
		int byId = db.delete("userInfo", "id=?", new String[] { id });
		db.close();
		return byId;
	}

	public void update(String name, String phone, int id) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db = helper.getReadableDatabase();// ��д�����ݿ�
		db.execSQL("update userInfo set password=?, username=?  where id=?",
				new Object[] { phone, name, id });
		db.close();
	}
}

package com.lanbiao.youxiaoyunfamily.hint;

import android.content.Context;
import android.widget.Toast;

public class ShowToast {

	/**
	 * ��ʾ��Ϣ
	 * 
	 * @param msg
	 * @param context
	 * @return
	 */
	public static String getToastInfo(String msg, Context context) {
		// ����д��˵���Խ���ص���ʾʱ�ӳ�
		Toast toast = null;
		if (toast == null) {
			toast = Toast.makeText(context.getApplicationContext(), msg,
					Toast.LENGTH_SHORT);
		}
		toast.show();
		return msg;
	}

}

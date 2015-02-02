package com.lanbiao.youxiaoyunfamily.prompt;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;

public class ShowDialogInfo extends Activity {
	private static Context context;

	public static Context getContext() {
		return context;
	}

	/**
	 * ����������ʾ��
	 * 
	 * @param msg
	 */
	public void showDialogInfo(String msg) {
		Builder dialog = new AlertDialog.Builder(getContext());
		dialog.setTitle("��ʾ");
		dialog.setMessage(msg);
		dialog.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// ���ٵ�ǰ��activity
				finish();
			}
		});
		dialog.setCancelable(false);
		dialog.show();
	}

	/**
	 * ��ʾ��������
	 */
	public DialogInterface.OnClickListener noDataListener = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// ���ٵ�ǰ��activity
			finish();
		}
	};
}

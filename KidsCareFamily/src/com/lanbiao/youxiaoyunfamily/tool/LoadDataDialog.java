package com.lanbiao.youxiaoyunfamily.tool;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;

public class LoadDataDialog {
	public static Handler loadDataDialog(Context context){
		final ProgressDialog dialog = new ProgressDialog(context);
		dialog.setTitle("����������������...");
		dialog.setMessage("���Ժ�...");
		dialog.show();
		
		final Handler handler = new Handler() {
				public void handleMessage(android.os.Message msg) {
					dialog.cancel();
				}
		};
			return handler;
	}
}

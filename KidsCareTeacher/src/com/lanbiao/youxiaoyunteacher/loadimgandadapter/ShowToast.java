package com.lanbiao.youxiaoyunteacher.loadimgandadapter;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.lanbiao.youxinteacher.R;

public class ShowToast extends Activity {
	/*
	 * �Ӳ����ļ��м��ز��ֲ����Զ�����ʾToast
	 */
	public  void showCustomToast() {
		// ��ȡLayoutInflater���󣬸ö�����Խ������ļ�ת������֮һ�µ�view����
		LayoutInflater inflater = getLayoutInflater();
		// �������ļ�ת������Ӧ��View����
		View layout = inflater.inflate(R.layout.custome_toast_layout,
				(ViewGroup) findViewById(R.id.toast_layout_root));
		// ��layout�а���id����imageView����
		ImageView imageView = (ImageView) layout.findViewById(R.id.ivForToast);
		// ����ImageView��ͼƬ
		imageView.setBackgroundResource(R.drawable.ic_ok);
		// ʵ����һ��Toast����
		Toast toast = new Toast(getApplicationContext());
		toast.setDuration(5);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setView(layout);
		toast.show();
	}
}

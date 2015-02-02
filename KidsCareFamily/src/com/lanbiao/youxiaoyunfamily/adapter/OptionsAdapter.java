package com.lanbiao.youxiaoyunfamily.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.lanbiao.youxiaoyunfamily.R;
import com.lanbiao.youxiaoyunfamily.entity.SelectPhone;

public class OptionsAdapter extends ArrayAdapter<SelectPhone> {

	@SuppressWarnings("unused")
	private List<SelectPhone> list = new ArrayList<SelectPhone>();
	private Activity activity = null;
	private Handler handler;

	/**
	 * �Զ��幹�췽��
	 * 
	 * @param activity
	 * @param handler
	 * @param list
	 */
	public OptionsAdapter(Activity activity, Handler handler,
			List<SelectPhone> list, ListView lv) {
		super(activity, 0, list);
		this.activity = activity;
		this.handler = handler;
		this.list = list;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		SelectPhone selectPhone = getItem(position);
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			// �������
			convertView = LayoutInflater.from(activity).inflate(
					R.layout.option_item, null);
			holder.textView = (TextView) convertView
					.findViewById(R.id.textView1);
			holder.btn = (ImageButton) convertView.findViewById(R.id.button1);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		String phone = selectPhone.getName();
		holder.textView.setText(phone);

		// Ϊ������ѡ�����ֲ��������¼�������Ч���ǵ������������䵽�ı���
		holder.textView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Message msg = new Message();
				Bundle data = new Bundle();
				// ����ѡ������
				data.putInt("selIndex", position);
				msg.setData(data);
				msg.what = 1;
				// ������Ϣ
				handler.sendMessage(msg);
			}
		});

		// Ϊ������ѡ��ɾ��ͼ�겿�������¼�������Ч���ǵ������ѡ��ɾ��
		holder.btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Message msg = new Message();
				Bundle data = new Bundle();
				// ����ɾ������
				data.putInt("delIndex", position);
				msg.setData(data);
				msg.what = 2;
				// ������Ϣ
				handler.sendMessage(msg);
			}
		});

		return convertView;
	}

}

class ViewHolder {
	TextView textView;
	ImageButton btn;
	// ImageView imageView;
}
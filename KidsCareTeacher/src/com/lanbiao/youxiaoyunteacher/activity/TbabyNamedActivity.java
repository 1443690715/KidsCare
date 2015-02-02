package com.lanbiao.youxiaoyunteacher.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lanbiao.youxiaoyunteacher.entity.BabyNamed;
import com.lanbiao.youxiaoyunteacher.entity.Classinfo;
import com.lanbiao.youxiaoyunteacher.entity.Studentinfo;
import com.lanbiao.youxiaoyunteacher.json.JsonTools;
import com.lanbiao.youxiaoyunteacher.loadimgandadapter.TbabyNamedAdapter;
import com.lanbiao.youxiaoyunteacher.service.BabyNamedService;
import com.lanbiao.youxiaoyunteacher.service.LoginService;
import com.lanbiao.youxinteacher.R;

public class TbabyNamedActivity extends Activity implements OnClickListener {
	private static final String TAG = "TbabyNamedActivity";
	private Button btn_save;
	private ListView lv_left, lv_right;
	private TextView tv_count, tv_nosignin;
	private TbabyNamedAdapter leftadapter;
	private TbabyNamedAdapter rightAdapter;
	private List<BabyNamed> right_list = new ArrayList<BabyNamed>();
	private List<BabyNamed> list = new ArrayList<BabyNamed>();
	private String userName, userPwd, result;
	private JSONObject jo;
	private Classinfo myClass;
	private Button btn_all;
	private Studentinfo studentinfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.t_baby_named_layout);
		initView();
		initData();
	}

	public void initView() {
		tv_nosignin = (TextView) this.findViewById(R.id.txtv_tbnd_left_id);
		btn_all = (Button) findViewById(R.id.btn_tbnd_select_all_id);
		btn_save = (Button) this.findViewById(R.id.btn_tbnd_save_id);
		lv_left = (ListView) this.findViewById(R.id.listv_tbnd_left_id);
		lv_right = (ListView) findViewById(R.id.listv_tbnd_right_id);
		tv_count = (TextView) findViewById(R.id.txtv_count_right_id);
		btn_save.setOnClickListener(this);
		btn_all.setOnClickListener(this);
	}

	public void initData() {
		try {
			Intent intent = getIntent();
			userName = intent.getStringExtra("username");
			userPwd = intent.getStringExtra("pwd");
			result = LoginService.login(userName, userPwd);
			// jo = new JSONObject(result);// ��ʦ������Ϣ
			myClass = JsonTools.getClassId("results", result);
			String strTeacherid = BabyNamedService.getStus(myClass.getId());
			jo = new JSONObject(strTeacherid);// ͨ����ʦ��ID�õ���ʦ������ѧ����������Ϣ
			studentinfo = JsonTools.getTeahcerStus("results", strTeacherid);

			String id = studentinfo.getSid();
			String name = studentinfo.getName();
			String head = studentinfo.getHead();
			String[] strId = id.split(",");
			String[] strName = name.split(",");
			String[] strHead = head.split(",");

			for (int i = 0; i < strName.length; i++) {
				list.add(new BabyNamed(strId[i], i + 1 + "", strHead[i],
						strName[i]));
			}
			rightAdapter = new TbabyNamedAdapter(this, right_list, lv_right);
			leftadapter = new TbabyNamedAdapter(this, list, lv_left);

			lv_right.setAdapter(rightAdapter);
			lv_left.setAdapter(leftadapter);
			tv_nosignin.setText("δǩ��" + lv_left.getCount() + "��");
			lv_left.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					right_list.add(list.get(position));
					list.remove(position);
					tv_nosignin.setText("δǩ��" + (lv_left.getCount() - 1) + "��");
					tv_count.setText("��ǩ��" + (lv_right.getCount() + 1) + "��");
					rightAdapter.notifyDataSetChanged();
					leftadapter.notifyDataSetChanged();
				}
			});
			lv_right.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					list.add(right_list.get(position));
					right_list.remove(position);
					tv_nosignin.setText("δǩ��" + (lv_left.getCount() + 1) + "��");
					tv_count.setText("��ǩ��" + (lv_right.getCount() - 1) + "��");
					leftadapter.notifyDataSetChanged();
					rightAdapter.notifyDataSetChanged();
				}
			});
			leftadapter.notifyDataSetChanged();
			rightAdapter.notifyDataSetChanged();
		} catch (Exception e) {
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_tbnd_save_id:

			new AlertDialog.Builder(TbabyNamedActivity.this)
					.setMessage("ȷ�����汦������")
					.setPositiveButton("ȷ��",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									String sid = "";
									try {
										int count = lv_right.getCount();
										for (int i = 0; i < count; i++) {
											String id = right_list.get(i)
													.getSid();
											sid += id + ",";
										}
										String substringSid = sid.substring(0,
												sid.length() - 1);
										String results = BabyNamedService
												.stuSignin(myClass.getId(),
														substringSid);
										try {
											JSONObject object = new JSONObject(
													results);
											int code = Integer.parseInt(object
													.getString("responseCode"));
											if (code == 0) {
												showCustomToast();
												finish();
											} else {
												Toast.makeText(
														TbabyNamedActivity.this,
														"����ʧ��...", 0).show();
											}
										} catch (Exception e) {
											e.printStackTrace();
										}
									} catch (Exception e) {
										e.printStackTrace();
									}

								}
							}).setNegativeButton("ȡ��", null).show();
			break;

		case R.id.btn_tbnd_select_all_id:
			right_list.addAll(list);
			tv_count.setText("��ǩ��" + list.size() + "��");
			list.removeAll(list);
			tv_nosignin.setText("δǩ��" + list.size() + "��");
			rightAdapter.notifyDataSetChanged();
			leftadapter.notifyDataSetChanged();
			break;
		}
	}

	/*
	 * �Ӳ����ļ��м��ز��ֲ����Զ�����ʾToast
	 */
	private void showCustomToast() {
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

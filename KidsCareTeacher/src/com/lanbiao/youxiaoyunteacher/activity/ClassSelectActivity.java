package com.lanbiao.youxiaoyunteacher.activity;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lanbiao.youxiaoyunteacher.entity.Classinfo;
import com.lanbiao.youxiaoyunteacher.entity.Studentinfo;
import com.lanbiao.youxiaoyunteacher.json.JsonTools;
import com.lanbiao.youxiaoyunteacher.service.LoginService;
import com.lanbiao.youxiaoyunteacher.service.MyClassService;
import com.lanbiao.youxinteacher.R;

public class ClassSelectActivity extends Activity implements
		OnItemClickListener {
	private static final String TAG = "MyClassActivity";
	private ListView listView;
	private String userName;
	private String userPwd;
	private String re;
	private JSONObject jo;
	private Classinfo myClass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build());
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.t_baby_class_layout);

		listView = (ListView) findViewById(R.id.lv_t_class_bottom_id);

		getMyclass();
		listView.setOnItemClickListener(this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_CALL) {
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}

	public void btn_back_id(View view) {
		finish();
	}

	/**
	 * ��ʾ��ʦ�İ༶
	 */

	public void getMyclass() {
		try {
			addData();
			ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();// �������д������
			String classname = myClass.getClassname();
			String classid = myClass.getClassid();
			String[] myCId = classid.split(",");
			String[] myCName = classname.split(",");
			for (int i = 0; i < myCName.length; i++) {// ��ʾ�༶����
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("ItemText", myCName[i]);
				map.put("ItemId", myCId[i]);
				listItem.add(map);
			}

			SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, listItem,
					R.layout.t_myclass_no_item, new String[] { "ItemText",
							"ItemId" },
					new int[] { R.id.ItemText, R.id.ItemId });
			listView.setAdapter(mSimpleAdapter);
		} catch (Exception e) {
			Toast.makeText(ClassSelectActivity.this, "��ȡ�༶ʧ�ܣ�", 0).show();
		}

	}

	public void addData() {
		Intent intent = getIntent();
		userName = intent.getStringExtra("username");
		userPwd = intent.getStringExtra("pwd");
		re = LoginService.login(userName, userPwd);
		try {
			jo = new JSONObject(re);// ��ʦ������Ϣ
			myClass = JsonTools.getClassId("results", re);
			String myclassId = MyClassService.myClassGetId(myClass.getId());
			jo = new JSONObject(myclassId);// ͨ����ʦ��ID�õ���ʦ������ѧ����������Ϣ
			myClass = JsonTools.getClasslistKeyMaps("results", myclassId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * OnItem
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		TextView tv_classId = (TextView) view.findViewById(R.id.ItemId);
		TextView tv_schoolname = (TextView) view.findViewById(R.id.ItemText);
		Intent intent = new Intent(ClassSelectActivity.this,
				TbabyMyStudentsActivity.class);

		Bundle bundle = new Bundle();
		// ������classid putInt ("id")
		bundle.putString("classId", (String) tv_classId.getText());
		bundle.putString("schoolname", (String) tv_schoolname.getText());
		bundle.putString("username", userName);
		bundle.putString("pwd", userPwd);
		intent.putExtras(bundle);
		startActivity(intent);
	}
}

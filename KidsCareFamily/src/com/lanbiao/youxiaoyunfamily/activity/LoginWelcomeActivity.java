package com.lanbiao.youxiaoyunfamily.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.lanbiao.youxiaoyunfamily.AppAppliction;
import com.lanbiao.youxiaoyunfamily.R;
import com.lanbiao.youxiaoyunfamily.adapter.OptionsAdapter;
import com.lanbiao.youxiaoyunfamily.data.GsonRequest;
import com.lanbiao.youxiaoyunfamily.db.UserInfoOpenHelper;
import com.lanbiao.youxiaoyunfamily.db.dao.UserInfoDao;
import com.lanbiao.youxiaoyunfamily.entity.Famliy;
import com.lanbiao.youxiaoyunfamily.entity.FamliyInfo;
import com.lanbiao.youxiaoyunfamily.entity.SelectPhone;
import com.lanbiao.youxiaoyunfamily.entity.UserInfo;
import com.lanbiao.youxiaoyunfamily.entity.Website;
import com.lanbiao.youxiaoyunfamily.hint.ShowToast;
import com.lanbiao.youxiaoyunfamily.service.LoginService;
import com.lanbiao.youxiaoyunfamily.util.NetworkUtils;

public class LoginWelcomeActivity extends Activity implements Callback {
	protected static final String TAG = "MainActivity";
	private EditText et_username, et_password;
	private CheckBox cb;

	// PopupWindow����
	private PopupWindow selectPopupWindow = null;
	// �Զ���Adapter
	private OptionsAdapter optionsAdapter = null;
	// ������ѡ������Դ
	private ArrayList<SelectPhone> phoneDatas = new ArrayList<SelectPhone>();
	// �������������
	private LinearLayout parent;
	// ���������������ȣ�Ҳ����Ϊ������Ŀ��
	private int pwidth;
	// ������ͷͼƬ���
	private ImageButton image;
	// �ָ�����Դ��ť
	// չʾ��������ѡ���ListView
	private ListView listView = null;
	// ��������ѡ�л���ɾ����������Ϣ
	private Handler handlerData;
	// �Ƿ��ʼ����ɱ�־
	private boolean flag = false;
	private String path = "";
	private String userName;
	private String userPwd;
	private static ProgressDialog dialog = null;// ���ؽ��ȿ�
	private Intent intent = new Intent();
	private Website url = new Website();
	private List<FamliyInfo> infos;
	private final int SHOWDIALOG = 0;
	private final int CLOSEDIALOG = 1;
	private int code;
	private int type;
	private AppAppliction appliction;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (!NetworkUtils.isConnectingToInternet(LoginWelcomeActivity.this)) {
				ShowToast.getToastInfo("���������Ƿ�����", LoginWelcomeActivity.this);
			} else {
				switch (msg.what) {
				case 0:
					// showDialogInfo("���Ժ�...", "���ڵ�½");
					dialog.setTitle("���ڵ�½");
					dialog.setMessage("���Ժ�...");
					dialog.setCancelable(false);
					dialog.show();

					break;
				case 1:
					// ��¼ʧ��
					dialog.cancel();
					break;
				}
			}
		}
	};
	public static Handler handler2 = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				dialog.dismiss();
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ò���ʾ����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		et_username = (EditText) findViewById(R.id.editv_login_uanem_id);
		et_password = (EditText) findViewById(R.id.editv_login_upwd_id);
		setContentView(R.layout.login_layout);
		dialog = new ProgressDialog(this);
		appliction = (AppAppliction) getApplication();
		appliction.mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(appliction.mReceiver, appliction.mFilter);

		cb = (CheckBox) findViewById(R.id.cb_login_ischeck_id);
		initPwdAndUser();
		createDataBase();

	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(appliction.mReceiver);
		super.onDestroy();
	}

	/**
	 * ��½
	 * 
	 * @param view
	 */
	public void login(View view) {
		userName = et_username.getText().toString().trim();
		userPwd = et_password.getText().toString().trim();
		intent.putExtra("username", userName);
		intent.putExtra("pwd", userPwd);
		if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPwd)) {
			ShowToast.getToastInfo("�û��������벻��Ϊ�գ�", LoginWelcomeActivity.this);
			return;
		} else {
			handler.sendEmptyMessage(SHOWDIALOG);
			LoginResultInfo();
		}

	}

	/**
	 * <p>
	 * �����½ʱ�õ���������Ϣ
	 * </p>
	 * <p>
	 * �����ж�
	 * </p>
	 */
	private void LoginResultInfo() {

		// �ӿ�
		path = url.getPath() + url.getLogin() + url.getUsername() + userName
				+ url.getPassword() + userPwd;
		System.out.println(path);
		try {

			RequestQueue mQueue = Volley.newRequestQueue(this);
			GsonRequest<Famliy> gsonRequest = new GsonRequest<Famliy>(path,
					Famliy.class, new Listener<Famliy>() {

						@Override
						public void onResponse(Famliy famliy) {
							infos = famliy.getResults();

							code = famliy.getResponseCode();
							if (code == -3) {// �������-3 �����û������������
								ShowToast.getToastInfo("�û������������",
										LoginWelcomeActivity.this);
								handler.sendEmptyMessage(CLOSEDIALOG);
							} else if (code == 0) {// ��½�ɹ�
								for (FamliyInfo info : infos) {
									type = info.getType();
								}
								if (type == 2) {// �ж��Ƿ��Ǽҳ���½
									startIntent();
								}
							} else
								ShowToast.getToastInfo("��ʹ�üҳ��˺ŵ�½��",
										LoginWelcomeActivity.this);
							handler.sendEmptyMessage(CLOSEDIALOG);
						}
					}, new Response.ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError error) {
							Log.e(TAG, error.getMessage(), error);
						}
					});
			mQueue.add(gsonRequest);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * ��½�ɹ�֮���ҳ����ת����ס�������
	 */
	public void startIntent() {
		appliction.infos = infos;
		UserInfoDao dao = new UserInfoDao(this);
		// �Ƿ񱣴��û�������
		if (cb.isChecked()) {
			LoginService.saveUser(LoginWelcomeActivity.this, userName, userPwd);
			dao.addContact(userName, userPwd);

		} else {
			String userpwd = "";
			LoginService.saveUserName(LoginWelcomeActivity.this, userName,
					userpwd);
		}
		// ����Ҫ��ת����Ŀ��ҳ��
		intent.setClass(LoginWelcomeActivity.this, FamilyActivity.class);
		startActivity(intent);
		finish();
		handler.sendEmptyMessage(CLOSEDIALOG);

		return;
	}

	/**
	 * ��ʼ��֮ǰ����õ��û���������
	 */
	public void initPwdAndUser() {
		SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
		String name = sp.getString("username", "");
		et_username.setText(name);
		String pwd = sp.getString("password", "");
		et_password.setText(pwd);
		if (TextUtils.isEmpty(name) && TextUtils.isEmpty(pwd)) {
			cb.setChecked(false);
		} else {
			cb.setChecked(true);
		}
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		while (!flag) {
			initView();
			flag = true;
		}
	}

	public void initView() {
		// ��ʼ��Handler,����������Ϣ
		handlerData = new Handler(LoginWelcomeActivity.this);

		// ��ʼ���������
		parent = (LinearLayout) findViewById(R.id.parent);
		et_username = (EditText) findViewById(R.id.editv_login_uanem_id);
		et_password = (EditText) findViewById(R.id.editv_login_upwd_id);
		image = (ImageButton) findViewById(R.id.btn_select);

		// ��ȡ������������������
		int width = parent.getWidth();
		pwidth = width;

		// ���õ��������ͷͼƬ�¼����������PopupWindow����������
		image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (flag) {
					// ��ʾPopupWindow����
					popupWindwShowing();
				}
			}
		});
		// ��ʼ��PopupWindow
		initPopuWindow();
	}

	public void initDatas() {
		try {
			UserInfoDao dao = new UserInfoDao(this);
			List<UserInfo> info = dao.findAll();
			for (UserInfo uinfo : info) {
				// phoneDatas.add(uinfo.getPhone());
				phoneDatas.add(new SelectPhone(uinfo.getUsername(), uinfo
						.getUserpwd()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * ��ʼ��PopupWindow
	 */
	private void initPopuWindow() {
		initDatas();
		// PopupWindow���������򲼾�
		View loginwindow = (View) this.getLayoutInflater().inflate(
				R.layout.options, null);
		listView = (ListView) loginwindow.findViewById(R.id.list);
		// �����Զ���Adapter
		optionsAdapter = new OptionsAdapter(this, handlerData, phoneDatas,
				listView);
		listView.setAdapter(optionsAdapter);

		selectPopupWindow = new PopupWindow(loginwindow, pwidth,
				LayoutParams.WRAP_CONTENT, true);

		selectPopupWindow.setOutsideTouchable(true);

		// ��һ����Ϊ��ʵ�ֵ���PopupWindow�󣬵������Ļ�������ּ�Back��ʱPopupWindow����ʧ��
		selectPopupWindow.setBackgroundDrawable(new BitmapDrawable());
	}

	/**
	 * ��ʾPopupWindow����
	 * 
	 * @param popupwindow
	 */
	public void popupWindwShowing() {
		// ��selectPopupWindow��Ϊparent����������ʾ����ָ��selectPopupWindow��Y����������ƫ��3pix��
		// ����Ϊ�˷�ֹ���������ı���֮�������϶��Ӱ���������
		// ���Ƿ�������϶����������϶�Ĵ�С�����ܻ���ݻ��͡�Androidϵͳ�汾��ͬ����ɣ���̫�����
		selectPopupWindow.showAsDropDown(parent, 0, -3);
	}

	@Override
	public boolean handleMessage(Message msg) {
		Bundle data = msg.getData();
		UserInfoDao dao = new UserInfoDao(this);
		switch (msg.what) {
		case 1:
			// ѡ���������������ʧ
			int selIndex = data.getInt("selIndex");
			et_username.setText(phoneDatas.get(selIndex).getName());
			et_password.setText(phoneDatas.get(selIndex).getPwd());
			dismiss();
			break;
		case 2:
			// �Ƴ�����������
			int delIndex = data.getInt("delIndex");
			phoneDatas.remove(delIndex);
			dao.delbyid(Integer.toString(delIndex + 1));
			// ˢ�������б�
			optionsAdapter.notifyDataSetChanged();
			break;
		}
		return false;
	}

	/**
	 * PopupWindow��ʧ
	 */
	public void dismiss() {
		selectPopupWindow.dismiss();
	}

	public void createDataBase() {
		UserInfoOpenHelper helper = new UserInfoOpenHelper(this);
		SQLiteDatabase db = helper.getWritableDatabase();
		db.close();
	}

}

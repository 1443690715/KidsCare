package com.lanbiao.youxiaoyunfamily.activity;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.lanbiao.youxiaoyunfamily.AppAppliction;
import com.lanbiao.youxiaoyunfamily.R;
import com.lanbiao.youxiaoyunfamily.adapter.ChatMsgViewAdapter;
import com.lanbiao.youxiaoyunfamily.entity.ChatMsgEntity;
import com.lanbiao.youxiaoyunfamily.entity.FamliyInfo;
import com.lanbiao.youxiaoyunfamily.entity.LeaveInfo;
import com.lanbiao.youxiaoyunfamily.json.JsonTools;
import com.lanbiao.youxiaoyunfamily.service.FamilyofBabyDynamicService;

public class MessageActivity extends Activity implements OnClickListener {
	private Button btn_back;// ����btn
	private Button btn_send;// ����btn
	private EditText mEditTextContent;
	private ListView mListView;
	private ChatMsgViewAdapter mAdapter;// ��Ϣ��ͼ��Adapter
	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();// ��Ϣ��������
	private LeaveInfo leaveInfo;
	@SuppressWarnings("unused")
	private static final String TAG = "MessageActivity";
	private AppAppliction appliction;
	private List<FamliyInfo> infos;
	private String strFamliy_Id;
	private String strTeacher_Id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.liaotian);
		appliction = (AppAppliction) getApplication();
		initView();
		initData();
		if (mAdapter != null && mAdapter.getCount() > 0) {
			mListView.setSelection(mAdapter.getCount() - 1);
		}

	}

	public void initView() {
		btn_back = (Button) findViewById(R.id.btn_p_message_back_id);
		mListView = (ListView) findViewById(R.id.listview);
		btn_send = (Button) findViewById(R.id.btn_send);
		btn_send.setOnClickListener(this);
		mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
		btn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	public void initData() {
		try {
			initLogin();
			String leaveinfo = FamilyofBabyDynamicService.getleaveInfo(
					strFamliy_Id, strTeacher_Id);
			JSONObject jsonObject = new JSONObject(leaveinfo);
			int code = jsonObject.getInt("responseCode");
			// ��ʾ����Ϣ
			// String strerrormsg = jsonObject.getString("msg");
			if (code == -2) {
				// Toast.makeText(getApplicationContext(), strerrormsg,
				// 0).show();
				return;
			} else {
				leaveInfo = JsonTools.getLeaveAllInfo("results", leaveinfo);
				String msg = leaveInfo.getMsg();
				String[] msgArray = msg.split("##");
				for (int i = 0; i < msgArray.length; i++) {
					ChatMsgEntity entity = new ChatMsgEntity();
					String[] currentMsgArray = msgArray[i].split("=");
					String strTime = currentMsgArray[0];// ʱ��
					String strName = currentMsgArray[1];// ����
					String strLogo = currentMsgArray[2];// ͷ��
					String strType = currentMsgArray[3];// ����
					String strContent = currentMsgArray[4];// ����
					if (strType.equals("1")) // �ж�type 1���յ� 2�Ƿ���
						entity.setMsgType(false);// �յ�����Ϣ
					else
						entity.setMsgType(true);// �Լ����͵���Ϣ
					entity.setMessage(strContent);
					entity.setName(strName);
					entity.setDate(strTime);
					entity.setUserheadurl(strLogo);
					mDataArrays.add(entity);
				}
			}

			/***************** ���Դ���Start ********************/
			// for (int i = 0; i < COUNT; i++) {
			// ChatMsgEntity entity = new ChatMsgEntity();
			// entity.setDate(dataArray[i]);// ʱ��
			// if (i % 2 == 0) {
			// entity.setName("ФB");// ����
			// entity.setMsgType(true);// �յ�����Ϣ
			// } else {
			// entity.setName("�ذ�");
			// entity.setMsgType(false);// �Լ����͵���Ϣ
			// }
			// entity.setMessage(msgArray[i]);
			// mDataArrays.add(entity);
			// }
			/***************** ���Դ���End ********************/

			mAdapter = new ChatMsgViewAdapter(this, mDataArrays);
			mListView.setAdapter(mAdapter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initLogin() {
		try {
			infos = appliction.infos;
			for (FamliyInfo info : infos) {
				strFamliy_Id = info.getFamilyId();
				strTeacher_Id = info.getTeacher_id();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_send:// ���Ͱ�ť����¼�
			send();
			break;
		case R.id.btn_p_message_back_id:// ���ذ�ť����¼�
			finish();// ����,ʵ�ʿ����У����Է���������
			break;
		}
	}

	/**
	 * ������Ϣ
	 */
	@SuppressWarnings("deprecation")
	private void send() {
		String strcontent = mEditTextContent.getText().toString();

		if (strcontent.length() > 0) {
			try {
				ChatMsgEntity entity = new ChatMsgEntity();
				initLogin();
				String content = strcontent;
				content = URLEncoder.encode(content);
				String result = FamilyofBabyDynamicService.sendMsg(
						strFamliy_Id, strTeacher_Id, content);
				JSONObject jsonObject = new JSONObject(result);
				int code = jsonObject.getInt("responseCode");
				if (code == 0) {
					entity.setDate(getDate());
					entity.setMessage(strcontent);
					// entity.setName(leaveInfo.getStudent_region());
					entity.setMsgType(false);
					mDataArrays.add(entity);
					if (mAdapter != null) {
						mAdapter.notifyDataSetChanged();// ֪ͨListView�������ѷ����ı�
					} else if (mAdapter == null) {
						initData();
						mAdapter.notifyDataSetChanged();// ֪ͨListView�������ѷ����ı�
					}
					mEditTextContent.setText("");// ��ձ༭������
					// ����һ����Ϣʱ��ListView��ʾѡ�����һ��
					mListView.setSelection(mListView.getCount() - 1);

				}
			} catch (Exception e) {
				// Toast.makeText(getApplicationContext(), "����ʧ��...", 0).show();
			}

		}
	}

	/**
	 * ������Ϣʱ����ȡ��ǰ�¼�
	 * 
	 * @return ��ǰʱ��
	 */
	private String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(new Date());
	}
}

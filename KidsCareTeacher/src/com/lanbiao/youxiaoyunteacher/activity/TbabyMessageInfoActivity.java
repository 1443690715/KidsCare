package com.lanbiao.youxiaoyunteacher.activity;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.lanbiao.youxiaoyunteacher.entity.ChatMsgEntity;
import com.lanbiao.youxiaoyunteacher.entity.MessageDetail;
import com.lanbiao.youxiaoyunteacher.json.JsonTools;
import com.lanbiao.youxiaoyunteacher.loadimgandadapter.ChatMsgViewAdapter;
import com.lanbiao.youxiaoyunteacher.service.MsgServcie;
import com.lanbiao.youxinteacher.R;

public class TbabyMessageInfoActivity extends Activity implements OnClickListener {
	private Button btn_back;// ����btn
	private Button btn_send;// ����btn
	private EditText mEditTextContent;
	private ListView mListView;
	private MessageDetail detail;
	private ChatMsgViewAdapter mAdapter;// ��Ϣ��ͼ��Adapter
	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();// ��Ϣ��������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.t_family_message_layout);
		initView();
		initData();
		if (mAdapter.getCount() > 0) {
			mListView.setSelection(mAdapter.getCount() - 1);
		}
	}

	public void initView() {
		btn_back = (Button) findViewById(R.id.btn_intentback);
		mListView = (ListView) findViewById(R.id.listview);
		btn_send = (Button) findViewById(R.id.btn_send);
		btn_send.setOnClickListener(this);
		mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);

		btn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = getIntent();
				String userName = intent.getStringExtra("username");
				String userPwd = intent.getStringExtra("userpwd");
				intent.putExtra("username", userName);
				intent.putExtra("pwd", userPwd);
				intent.setClass(TbabyMessageInfoActivity.this,
						TbabyQueryListMsgActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	public void initData() {
		try {
			Intent intent = getIntent();
			String fid = intent.getStringExtra("fid");
			String tid = intent.getStringExtra("tid");
			String strFamilyMsginfo = MsgServcie.getFamilyMsgInfo(fid, tid);
			detail = JsonTools.getFamilyInfo("results", strFamilyMsginfo);
			String MsgArray = detail.getTimeandregionandlogoandcontentandtype();

			String[] strMsgArray = MsgArray.split("##");
			for (int i = 0; i < strMsgArray.length; i++) {
				ChatMsgEntity entity = new ChatMsgEntity();
				String[] currentMsgArray = strMsgArray[i].split("=");
				String strTime = currentMsgArray[0];
				String strRegion = currentMsgArray[1];
				String strLogo = currentMsgArray[2];
				String strType = currentMsgArray[3];
				String strContent = currentMsgArray[4];
				if (strType.equals("1")) // �ж�type 1���յ� 2�Ƿ���
					entity.setComMeg(true);// �յ�����Ϣ
				else
					entity.setComMeg(false);// �Լ����͵���Ϣ
				entity.setContent(strContent);
				entity.setName(strRegion);
				entity.setDate(strTime);
				entity.setHead(strLogo);
				mDataArrays.add(entity);
			}
			mAdapter = new ChatMsgViewAdapter(this, mDataArrays);
			mListView.setAdapter(mAdapter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			Intent intent = getIntent();
			String userName = intent.getStringExtra("username");
			String userPwd = intent.getStringExtra("userpwd");
			intent.putExtra("username", userName);
			intent.putExtra("pwd", userPwd);
			intent.setClass(TbabyMessageInfoActivity.this,
					TbabyQueryListMsgActivity.class);
			startActivity(intent);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_send:// ���Ͱ�ť����¼�
			send();
			break;
		}
	}

	/**
	 * ������Ϣ
	 */
	private void send() {
		String strcontent = mEditTextContent.getText().toString();

		if (strcontent.length() > 0) {
			try {
				ChatMsgEntity entity = new ChatMsgEntity();
				Intent intent = getIntent();
				String tid = intent.getStringExtra("tid");
				String converid = intent.getStringExtra("converid");
				String name = intent.getStringExtra("tname");

				String content = strcontent;
				content = URLEncoder.encode(content);
				String result = MsgServcie.sendMsg(converid, tid, content);
				JSONObject jsonObject = new JSONObject(result);
				int code = jsonObject.getInt("responseCode");
				if (code == 0) {
					entity.setDate(getDate());
					entity.setContent(strcontent);
					entity.setName(name);
					entity.setComMeg(false);
					mDataArrays.add(entity);
					mAdapter.notifyDataSetChanged();// ֪ͨListView�������ѷ����ı�
					mEditTextContent.setText("");// ��ձ༭������
					mListView.setSelection(mListView.getCount() - 1);
					// ����һ����Ϣʱ��ListView��ʾѡ�����һ��
				}
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), "����ʧ��...", 0).show();
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

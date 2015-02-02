package com.lanbiao.youxiaoyunteacher.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lanbiao.youxiaoyunteacher.entity.Classinfo;
import com.lanbiao.youxiaoyunteacher.entity.ImageAndText;
import com.lanbiao.youxiaoyunteacher.entity.MessageInfo;
import com.lanbiao.youxiaoyunteacher.entity.MessageInfoImgAndTxt;
import com.lanbiao.youxiaoyunteacher.json.JsonTools;
import com.lanbiao.youxiaoyunteacher.loadimgandadapter.QueryMsgAdapter;
import com.lanbiao.youxiaoyunteacher.service.LoginService;
import com.lanbiao.youxiaoyunteacher.service.MsgServcie;
import com.lanbiao.youxinteacher.R;

public class TbabyQueryListMsgActivity extends Activity {
	protected static final String TAG = "TbabyQueryListMsgActivity";
	private Button btn_back, btn_replyall;
	private ListView lv_list;
	private QueryMsgAdapter adapter;
	private Classinfo classinfo;
	private MessageInfo info;
	private List<MessageInfoImgAndTxt> list = new ArrayList<MessageInfoImgAndTxt>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.t_baby_message_layout);
		initView();
		initData();
	}

	public void initView() {
		btn_back = (Button) findViewById(R.id.btn_t_message_back_id);
		btn_replyall = (Button) findViewById(R.id.btn_t_main_quit_id);
		lv_list = (ListView) findViewById(R.id.lv_familymsg);

		btn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		btn_replyall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if ((info.getMsgs()).equals("")) {

					Toast.makeText(getApplicationContext(), "���޻ظ���Ϣ��", 0)
							.show();
				} else {
					try {
						Intent intent = getIntent();
						String userName = intent.getStringExtra("username");
						String userPwd = intent.getStringExtra("pwd");
						intent.putExtra("username", userName);
						intent.putExtra("pwd", userPwd);
						intent.setClass(getApplicationContext(),
								TbabyReplyAllActivity.class);
						startActivity(intent);
						finish();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

	}

	public void initData() {
		try {
			Intent intent = getIntent();
			String userName = intent.getStringExtra("username");
			String userPwd = intent.getStringExtra("pwd");
			String strteacherinfo = LoginService.login(userName, userPwd);
			classinfo = JsonTools.getClassId("results", strteacherinfo);// �õ���ʦ�ĸ�����Ϣ
			String StrReplylist = MsgServcie.getReplyList(classinfo.getId());// �õ���ʦ��id
			info = JsonTools.getMsgList("results", StrReplylist);// �õ���ʦ����Ϣ�б�
			String contentArray = info.getContent();// ����
			String regionArray = info.getSturegion();// ������ϵ
			String logoArray = info.getStulogo();// ͷ��
			String statusArray = info.getStatus();// ״̬ �ѻظ� ��δ�ظ�
			String converstationidArray = info.getConversation_id();// ��ǰ�ػ�id,��ʦor�ҳ�
			String timeArray = info.getMsgtime();// ʱ��
			String familyArray = info.getFamilyid();// �ҳ�id

			String[] strContent = contentArray.split("=");
			String[] strRegion = regionArray.split("=");
			String[] strLogo = logoArray.split("=");
			String[] strStatus = statusArray.split("=");
			String[] strConverId = converstationidArray.split("=");
			String[] strTime = timeArray.split("=");
			String[] strFamily = familyArray.split("=");

			for (int i = 0; i < strContent.length; i++) {
				list.add(new MessageInfoImgAndTxt(strRegion[i], strContent[i],
						strLogo[i], strStatus[i], strConverId[i], strTime[i],
						strFamily[i], classinfo.getId(), classinfo
								.getTeachername(), userName, userPwd));
			}
			adapter = new QueryMsgAdapter(this, list, lv_list);
			lv_list.setAdapter(adapter);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

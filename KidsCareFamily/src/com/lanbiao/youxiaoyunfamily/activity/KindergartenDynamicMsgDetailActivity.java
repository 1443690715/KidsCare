package com.lanbiao.youxiaoyunfamily.activity;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lanbiao.youxiaoyunfamily.R;
import com.lanbiao.youxiaoyunfamily.entity.Notice;
import com.lanbiao.youxiaoyunfamily.json.JsonTools;
import com.lanbiao.youxiaoyunfamily.service.FamilyofBabyDynamicService;

public class KindergartenDynamicMsgDetailActivity extends Activity {
	private Button btn_join;
	private TextView tv_title, tv_begintime, tv_endtime, tv_contactphone,
			tv_named, tv_content;
	private Notice notice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.p_school_notice_detail);
		initView();
		initData();
	}

	/**
	 * ��ʼ����ͼ
	 */
	public void initView() {
		tv_named = (TextView) findViewById(R.id.tv_named_id);
		btn_join = (Button) findViewById(R.id.btn_t_main_join_id);
		tv_title = (TextView) findViewById(R.id.tv_msg_title);
		tv_begintime = (TextView) findViewById(R.id.tv_begin_time);
		tv_endtime = (TextView) findViewById(R.id.tv_end_time);
		tv_contactphone = (TextView) findViewById(R.id.tv_acitivityphone);
		tv_content = (TextView) findViewById(R.id.tv_activity_content);
		btn_join.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle bundle = getIntent().getExtras();
				String bname = bundle.getString("name");
				LayoutInflater layoutInflater = LayoutInflater
						.from(KindergartenDynamicMsgDetailActivity.this);
				final View vfamilyinfo = layoutInflater.inflate(
						R.layout.activity_dialog, null);
				TextView tv_name = (TextView) vfamilyinfo
						.findViewById(R.id.et_name);
				tv_name.setText(bname);
				showDialogInfo(vfamilyinfo);
			}
		});
	}

	/**
	 * �������ʱ �����Ի���
	 * 
	 * @param view
	 */
	public void showDialogInfo(View view) {
		Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("�����");
		dialog.setView(view);
		dialog.setPositiveButton("ȷ��", positiveListener);
		dialog.setNegativeButton("ȡ��", negativeListener);
		dialog.show();
	}

	/**
	 * ���ȷ������ʱ��������
	 */
	public DialogInterface.OnClickListener positiveListener = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			getBackData();
		}
	};
	/**
	 * ���ȡ��ʱ��������˾
	 */
	public DialogInterface.OnClickListener negativeListener = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			showToast("ȡ��������");
		}
	};

	/**
	 * �õ�����ʱ���ص����� �����ɹ�----����ʧ��
	 */
	public void getBackData() {
		Bundle bundle = getIntent().getExtras();
		String strfamilyid = bundle.getString("familyid");
		String strstuid = bundle.getString("stuid");
		String re = FamilyofBabyDynamicService.activityJoin(// �����ӿ�
				notice.getActivityid(), strfamilyid, strstuid);
		try {
			JSONObject j = new JSONObject(re);
			int code;
			code = Integer.parseInt(j.getString("responseCode"));// ����Json
			if (code == 1) {
				showToast("����ʧ�ܣ�");
			} else {
				showToast("�����ɹ���");
				finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * ��ʾ��ʾ��Ϣ ---��˾
	 * 
	 * @param msg
	 */
	public void showToast(String msg) {
		Toast.makeText(this, msg, 0).show();
	}

	/**
	 * ��ʼ������
	 */
	public void initData() {
		try {
			Bundle bundle = this.getIntent().getExtras();
			String stractivityid = bundle.getString("itemid");// �õ���һ��activity�������Ļid
			String strfamilyid = bundle.getString("familyid");// �õ���һ��activity�������ļҳ�id
			String strActivityDetail = FamilyofBabyDynamicService// ֪ͨ�ӿڷ�����������
					.getMsgInfoDetail(stractivityid, strfamilyid);
			notice = JsonTools.getMsgInfoDataDetail("results",// ʹ��Json����֪ͨ�ӿڷ�����������
					strActivityDetail);
			String contactphone = notice.getContactphone();// ��ϵ�绰
			String title = notice.getTitle();// ����
			String content = notice.getContent();// ����
			String begintime = notice.getBegintime();// ���ʼʱ��
			String endtime = notice.getEndtime();// �����ʱ��
			String siginstatus = notice.getSiginstatus();// �μ�״̬ �ѱ���|δ����
			String type = notice.getType();// �֪ͨ����
			if (type.equals("2")) {// ���type=2������֪ͨ������ʾ������ť
				tv_named.setVisibility(View.GONE);// ���ء��ѱ���������
				btn_join.setVisibility(View.GONE);// ���ء���������ť
				tv_title.setText(title);
			} else if (type.equals("1")) {// ��ʾ������ť
				if (siginstatus.equals("1")) {// ����״̬ 1���ѱ��� 0��δ����
					tv_named.setVisibility(View.VISIBLE);// ��ʾΪ���ѱ�����
					btn_join.setVisibility(View.GONE);// ���ء���������ť
					tv_named.setText("�ѱ���");// ��ʾ��������ȥ
				} else {
					tv_named.setVisibility(View.GONE);// ���ء��ѱ���������
					btn_join.setVisibility(View.VISIBLE);// ��ʾ��������ť��
				}

			}
			tv_title.setText(title);
			tv_begintime.setText(begintime);
			tv_endtime.setText(endtime);
			tv_contactphone.setText(contactphone);
			tv_content.setText(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

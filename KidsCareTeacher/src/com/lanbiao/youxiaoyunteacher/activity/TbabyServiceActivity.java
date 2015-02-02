package com.lanbiao.youxiaoyunteacher.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanbiao.youxiaoyunteacher.entity.Classinfo;
import com.lanbiao.youxiaoyunteacher.entity.Menu;
import com.lanbiao.youxiaoyunteacher.entity.StudyRaise;
import com.lanbiao.youxiaoyunteacher.entity.TitleAndId;
import com.lanbiao.youxiaoyunteacher.json.JsonTools;
import com.lanbiao.youxiaoyunteacher.service.LoginService;
import com.lanbiao.youxiaoyunteacher.service.StudyRaiseService;
import com.lanbiao.youxinteacher.R;

public class TbabyServiceActivity extends Activity {
	private ExpandableListView elistview;
	private List<String> groupData;
	private List<List<TitleAndId>> childrenData;
	StudyRaise studyRaise;
	private String userName, userPwd, result;
	private Classinfo myClass;
	private Menu menu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.t_baby_service_layout);
		elistview = (ExpandableListView) findViewById(R.id.expandlv_t_service_type_id);
		loadData();
		// ϵͳ�Դ���ͼ��ȥ��
		elistview.setGroupIndicator(null);
		elistview.setAdapter(new ExpandableAdapter());
	}

	private void loadData() {
		try {
			Intent intent = getIntent();
			userName = intent.getStringExtra("username");
			userPwd = intent.getStringExtra("pwd");
			result = LoginService.login(userName, userPwd);
			myClass = JsonTools.getClassId("results", result);

			// �õ��˵���
			String results = StudyRaiseService.getChildCareMenu(myClass
					.getShoolId());
			// �����˵���
			menu = JsonTools.getMenu("results", results);
			// �õ������˵������ƺ�id
			String fNameAndId = menu.getFirstNameAndId();
			// �õ�һ���˵�id�Ӽ��˵�name
			String sNameAndfId = menu.getSnameanfdid();
			// �����˵������ƺ�����Ӧ��id ���м���=2efd154e-098e-4ddb-b808-d9ba4071d504
			String twoNameAndId = menu.getSecondNameAndId();

			// �õ� �����˵�name��id
			String[] strTwoNameAndId = twoNameAndId.split(",");
			// �õ�һ���˵�name/id
			String[] strFNameAndId = fNameAndId.split(",");
			// �����˵�name��һ���˵�id
			String[] strSNameAndfId = sNameAndfId.split(",");

			// ����(һ��)�˵�
			groupData = new ArrayList<String>();
			// ��(����)�˵�����
			// childrenData = new ArrayList<List<String>>();
			childrenData = new ArrayList<List<TitleAndId>>();
			// �����˵�
			for (int i = 0; i < strFNameAndId.length; i++) {
				String[] strFNameAndIds = strFNameAndId[i].split("=");
				// �����ǵõ������˵���id ���Ϊ0ʱ�Ǹ����˵�������
				String fid = strFNameAndIds[0];
				// ����һ�������˵�����
				String sname = "";
				String strtwoid = "";
				// ����õ��Ӳ˵��ĳ���
				for (int j = 0; j < strSNameAndfId.length; j++) {
					// ��ȡ�Ӳ˵�id
					String[] strSNameAndIds = strSNameAndfId[j].split("=");
					String[] strTwoNameAndIds = strTwoNameAndId[j].split("=");

					// STRing twoId =
					// �Ӳ˵��������˵���id��
					String sid = strSNameAndIds[1];
					// ������id =����idʱ��������ӵ�����˵���
					if (sid.equals(fid)) {
						// �����˵������ƣ����⣩
						sname += strSNameAndIds[0] + ",";
						strtwoid += strTwoNameAndIds[1] + ",";
					}
				}
				// ���ɸ���(һ��)�˵�
				groupData.add(strFNameAndIds[1]);
				// ��������˵��ĳ��ȴ���-1�����ȡ�ַ������һλ��
				if (sname.length() > -1) {
					sname = sname.substring(0, sname.length() - 1);
				}
				String[] strtwoids = strtwoid.split(",");
				// ��ȡ�Ӳ˵����ַ��� [���м���, �ճ�����, ����֪ʶ]
				String[] strnames = sname.split(",");
				// ��������˵�
				List<TitleAndId> childTemp = new ArrayList<TitleAndId>();
				for (int j = 0; j < strnames.length; j++) {
					TitleAndId titleAndId = new TitleAndId();
					titleAndId.setStitle(strnames[j]);
					titleAndId.setSid(strtwoids[j]);
					childTemp.add(titleAndId);
				}
				childrenData.add(childTemp);
			}

		} catch (Exception e) {
		}

	}

	private class ExpandableAdapter extends BaseExpandableListAdapter {

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return childrenData.get(groupPosition).get(childPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return 0;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {

			// ʵ���������ļ�
			LinearLayout clayout = (LinearLayout) LayoutInflater.from(
					TbabyServiceActivity.this).inflate(
					R.layout.t_raise_child_layout, null);

			final TextView tv = (TextView) clayout.findViewById(R.id.ctv);
			final TextView tv_cid = (TextView) clayout
					.findViewById(R.id.tv_cid);
			tv.setText(((TitleAndId) getChild(groupPosition, childPosition))
					.getStitle());
			tv_cid.setText(((TitleAndId) getChild(groupPosition, childPosition))
					.getSid());
			tv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(TbabyServiceActivity.this,
							TbabyServiceListItemActivity.class);

					Bundle bundle = new Bundle();
					bundle.putString("secondid", (String) tv_cid.getText());
					bundle.putString("title", (String) tv.getText());
					intent.putExtras(bundle);
					startActivity(intent);
				}
			});

			return clayout;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return childrenData.get(groupPosition).size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return groupData.get(groupPosition);
		}

		@Override
		public int getGroupCount() {
			return groupData.size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			return 0;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {

			// ʵ���������ļ�
			RelativeLayout glayout = (RelativeLayout) LayoutInflater.from(
					TbabyServiceActivity.this).inflate(
					R.layout.t_raise_group_layout, null);
			ImageView iv = (ImageView) glayout.findViewById(R.id.giv);
			// �жϷ����Ƿ�չ�����ֱ��벻ͬ��ͼƬ��Դ
			if (isExpanded) {
				iv.setImageResource(R.drawable.jian);
			} else {
				iv.setImageResource(R.drawable.jia);
			}
			TextView tv = (TextView) glayout.findViewById(R.id.gtv);
			tv.setText(this.getGroup(groupPosition).toString());
			return glayout;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return false;
		}

	}
}

package com.lanbiao.youxiaoyunfamily.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
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

import com.lanbiao.youxiaoyunfamily.AppAppliction;
import com.lanbiao.youxiaoyunfamily.R;
import com.lanbiao.youxiaoyunfamily.entity.FamliyInfo;
import com.lanbiao.youxiaoyunfamily.entity.Menu;
import com.lanbiao.youxiaoyunfamily.entity.TitleAndId;
import com.lanbiao.youxiaoyunfamily.entity.Website;
import com.lanbiao.youxiaoyunfamily.json.JsonTools;
import com.lanbiao.youxiaoyunfamily.util.HttpUtils;

public class ChildCareCentersActivity extends Activity {
	private ExpandableListView elistview;
	private List<String> groupData;
	private Menu menu;
	private List<List<TitleAndId>> childrenData;
	private ExpandableAdapter adapter = new ExpandableAdapter();
	private Website website = new Website();
	private String path = "";
	private AppAppliction appliction;
	private List<FamliyInfo> infos;
	private String strSchool_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		appliction = (AppAppliction) getApplication();
		setContentView(R.layout.p_baby_shop_layout);
		elistview = (ExpandableListView) findViewById(R.id.expandlv_p_shop_type_id);

		loadData();
		if (menu.getFirstNameAndId() != null) {
			// ϵͳ�Դ���ͼ��ȥ��
			elistview.setGroupIndicator(null);
			elistview.setAdapter(adapter);
		}
	}

	private void loadData() {
		try {
			infos = appliction.infos;
			for (FamliyInfo info : infos) {
				strSchool_id = info.getSchoolId();
			}

			path = website.getPath() + website.getQueryshoppingmenu()
					+ website.getStrSchoolid() + strSchool_id;
			// �õ��˵���
			String results = HttpUtils.getJsonContent(path);
			// �����˵���
			menu = JsonTools.getMenu("results", results);
			// �õ������˵������ƺ�id
			String fNameAndId = menu.getFirstNameAndId();
			// �õ�һ���˵�id�Ӽ��˵�name
			String sNameAndfId = menu.getSnameanfdid();
			// �����˵������ƺ�����Ӧ��id ���м���=2efd154e-098e-4ddb-b808-d9ba4071d504
			String twoNameAndId = menu.getSecondNameAndId();
			if (fNameAndId == null) {
				showDialogInfo("����������Ϣ");
				return;
			} else {

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
						String[] strTwoNameAndIds = strTwoNameAndId[j]
								.split("=");

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
					ChildCareCentersActivity.this).inflate(
					R.layout.p_baby_shop_child_layout, null);

			TextView tv = (TextView) clayout.findViewById(R.id.ctv);
			final TextView tv_cid = (TextView) clayout
					.findViewById(R.id.tv_cid);
			tv.setText(((TitleAndId) getChild(groupPosition, childPosition))
					.getStitle());
			tv_cid.setText(((TitleAndId) getChild(groupPosition, childPosition))
					.getSid());
			tv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(ChildCareCentersActivity.this,
							ChildCareCentersListItemActivity.class);

					Bundle bundle = new Bundle();
					bundle.putString("secondid", (String) tv_cid.getText());
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
					ChildCareCentersActivity.this).inflate(
					R.layout.p_baby_shop_group_layout, null);
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

	/**
	 * ����������ʾ��
	 * 
	 * @param msg
	 */
	private void showDialogInfo(String msg) {
		Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("��ʾ");
		dialog.setMessage(msg);
		dialog.setPositiveButton("ȷ��", noDataListener);
		dialog.setCancelable(false);
		dialog.show();
	}

	/**
	 * ��ʾ��������
	 */
	public DialogInterface.OnClickListener noDataListener = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// ���ٵ�ǰ��activity
			finish();
		}
	};
}

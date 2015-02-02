package com.lanbiao.youxiaoyunfamily.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanbiao.youxiaoyunfamily.AppAppliction;
import com.lanbiao.youxiaoyunfamily.R;
import com.lanbiao.youxiaoyunfamily.adapter.DynamicAdapter;
import com.lanbiao.youxiaoyunfamily.entity.FamliyInfo;
import com.lanbiao.youxiaoyunfamily.entity.ImageAndText;
import com.lanbiao.youxiaoyunfamily.entity.MyBabyDynamic;
import com.lanbiao.youxiaoyunfamily.entity.Website;
import com.lanbiao.youxiaoyunfamily.json.JsonTools;
import com.lanbiao.youxiaoyunfamily.refresh.PullDownListView;
import com.lanbiao.youxiaoyunfamily.refresh.PullDownListView.OnRefreshListioner;
import com.lanbiao.youxiaoyunfamily.util.HttpUtils;

public class BabyDynamicActivity extends Activity implements OnRefreshListioner {
	@SuppressWarnings("unused")
	private static final String TAG = "BabyDynamic";
	private ListView lv;
	private RelativeLayout rl_nodata, rl_showdata;
	private TextView tv_nodata;
	private MyBabyDynamic dynamic;
	private DynamicAdapter adapter;
	private PullDownListView mPullDownView;
	private Handler mHandler = new Handler();
	List<ImageAndText> list = new ArrayList<ImageAndText>();
	private Website website = new Website();
	private String path = "";
	private AppAppliction appliction;
	private List<FamliyInfo> infos;
	private String str_fid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build());
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.p_baby_dynamic_layout);
		appliction = (AppAppliction) getApplication();
		InitView();
		InitData(list.size());

	}

	public void btn_back(View view) {
		finish();
	}

	public void InitView() {
		tv_nodata = (TextView) findViewById(R.id.tv_nodata);
		rl_nodata = (RelativeLayout) findViewById(R.id.rl_nodata);
		rl_showdata = (RelativeLayout) findViewById(R.id.rl_showdata);
		mPullDownView = (PullDownListView) findViewById(R.id.sreach_list);
		lv = (ListView) this.findViewById(R.id.lv_dynamic);
		mPullDownView.setRefreshListioner(this);
	}

	public void InitData(int n) {
		n += list.size();
		try {
			infos = appliction.infos;
			for (FamliyInfo info : infos) {
				str_fid = info.getFamilyId();
			}
			path = website.getPath() + website.getQuerytrends()
					+ website.getFamilyid() + str_fid;
			String strFamilyid = HttpUtils.getJsonContent(path);
			dynamic = JsonTools.getBabyDynamic("results", strFamilyid);

			String evaluate = dynamic.getEvaluate();// ��Ϣ����
			String sendtime = dynamic.getSendTime();// ����ʱ��
			String contentAndimg = dynamic.getContentAndimg();// ���ݺ���Ƭ
			String contentAndDynamic = dynamic.getContentAnddynamic();// ���ݺͱ�����̬

			/************* --NOUPDATA--START- *************/
			String head = dynamic.getStuhead();// ͷ��
			// String stuImage = dynamic.getStuImage();// ѧϰ״̬��Ƭ
			/************* --NOUPDATA--END- *************/
			if (evaluate == null) {
				rl_showdata.setVisibility(View.GONE);
				rl_nodata.setVisibility(View.VISIBLE);
				tv_nodata.setText("���޶�̬��Ϣ");
			} else {
				rl_nodata.setVisibility(View.GONE);
				rl_showdata.setVisibility(View.VISIBLE);
				// String[] strEvaluate = evaluate.split("/");
				String[] strSendtime = sendtime.split("=");
				// ����==������##����==������
				String[] contentAndimg_Arrays = contentAndimg.split("##");
				// ����==��̬##����==��̬
				String[] contentAndDynamic_Arrays = contentAndDynamic
						.split("##");
				for (int i = 0; i < strSendtime.length; i++) {
					// ����==������
					String contentAndimg_Array = contentAndimg_Arrays[i];
					// ����==��̬
					String contentAndDynamic_Array = contentAndDynamic_Arrays[i];
					String[] oneContentAndImg = contentAndimg_Array.split("==");
					String[] oneContentAndDynamic = contentAndDynamic_Array
							.split("==");
					// ����
					String content = oneContentAndImg[0];
					// ��Ƭ
					String imgs = oneContentAndImg[1];
					String dynamic = oneContentAndDynamic[1];
					// Log.v(TAG, "img---" + contentAndimg_Array);
					// list.add(new ImageAndText(head, content, strSendtime[i],
					// imgs, strTwo[i]));
					list.add(new ImageAndText(head, content, strSendtime[i],
							imgs, dynamic));
				}
				adapter = new DynamicAdapter(this, lv, list);
				lv = mPullDownView.mListView;
				mPullDownView.setMore(true);//
				// ��������true��ʾ���и�����أ�����Ϊfalse�ײ�������ʾ����
				lv.setAdapter(adapter);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����ˢ��
	 */
	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {

			public void run() {
				list.clear();
				// addLists(10);// ����ʱ���ص����� ����Ϊ10��
				InitData(5);
				mPullDownView.onRefreshComplete();// �����ʾˢ�´�����ɺ������ļ���ˢ�½�������
				mPullDownView.setMore(true);// ��������true��ʾ���и�����أ�����Ϊfalse�ײ�������ʾ����
				adapter.notifyDataSetChanged();

			}
		}, 1500);
	}

	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {
			public void run() {
				// addLists(5);// ÿ�μ�������������
				// �����ʾ���ظ��ദ����ɺ������ļ��ظ�����棨���ػ��������������ࣩ
				mPullDownView.onLoadMoreComplete();
				if (list.size() < list.size())
					// �жϵ�ǰlist������ӵ������Ƿ�С�����ֵmaxAount������ô����ʾ���������ʾ
					mPullDownView.setMore(true);//
				// ��������true��ʾ���и�����أ�����Ϊfalse�ײ�������ʾ����
				else
					mPullDownView.setMore(false);
				adapter.notifyDataSetChanged();

			}
		}, 1500);
	}
}

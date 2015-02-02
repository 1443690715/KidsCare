package com.lanbiao.youxiaoyunfamily.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.lanbiao.youxiaoyunfamily.R;

public class UpdateManager {

	private Context mContext;

	// ��ʾ��
	// private String updateMsg = "�����µ������Ŷ���׿����ذ�~";
	private String updateMsg = "";
	// ���صİ�װ��url
	// private String apkUrl =
	// "http://softfile.3g.qq.com:8080/msoft/179/24659/43549/qq_hd_mini_1.4.apk";
	private Dialog noticeDialog;

	private Dialog downloadDialog;
	/* ���ذ���װ·�� */
	// private static final String savePath = "/sdcard/updatedemo/";
	//
	// private static final String saveFileName = savePath
	// + "UpdateDemoRelease.apk";

	// private static final String savePath = "/sdcard/updatedemo/";

	// private static final String saveFileName = savePath
	// + "UpdateDemoRelease.apk";

	/* ��������֪ͨuiˢ�µ�handler��msg���� */
	private ProgressBar mProgress;

	private static final int DOWN_UPDATE = 1;

	private static final int DOWN_OVER = 2;

	private int progress;
	private PackageManager manager;
	private PackageInfo info;
	private int oldVersion;// �ɰ汾��

	private Thread downLoadThread;
	private boolean interceptFlag = false;

	/**
	 * ��ʾ������
	 */
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWN_UPDATE:
				mProgress.setProgress(progress);
				break;
			case DOWN_OVER:
				installApk();
				break;
			default:
				break;
			}
		};
	};

	public UpdateManager(Context context) {
		this.mContext = context;
		init();
	}

	public void init() {
		manager = mContext.getPackageManager();
		try {
			info = manager.getPackageInfo(mContext.getPackageName(),
					PackageManager.GET_ACTIVITIES);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String contents;
	private String upDateTime;
	private int newVersionCode;
	private String apkUrl;

	public UpdateManager(Context context, String content, String strTime,
			int nVCode, String urlApk) {
		this.mContext = context;
		this.contents = content;
		this.upDateTime = strTime;
		this.newVersionCode = nVCode;
		this.apkUrl = urlApk;
		init();
	}

	/**
	 * �õ��汾��
	 * 
	 * @return
	 */
	public int getVersionCode() {
		return info.versionCode;
	}

	// �ⲿ�ӿ�����Activity����
	public void checkUpdateInfo() {
		try {
			oldVersion = info.versionCode;
			if (newVersionCode > oldVersion) {
				showNoticeDialog();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void showNoticeDialog() {
		updateMsg = contents + "\n" + "����ʱ��:" + upDateTime + "\n" + "�汾�ţ�"
				+ newVersionCode;
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setTitle("����汾����");
		builder.setMessage(updateMsg);
		builder.setPositiveButton("����", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				showDownloadDialog();
			}
		});
		builder.setNegativeButton("�Ժ���˵", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		noticeDialog = builder.create();
		noticeDialog.show();
	}

	private void showDownloadDialog() {
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setTitle("����汾����");

		final LayoutInflater inflater = LayoutInflater.from(mContext);
		View v = inflater.inflate(R.layout.progress, null);
		mProgress = (ProgressBar) v.findViewById(R.id.progress);

		builder.setView(v);
		builder.setNegativeButton("ȡ��", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				interceptFlag = true;
			}
		});
		downloadApk();
		downloadDialog = builder.create();
		downloadDialog.show();

	}

	private Runnable mdownApkRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				FileOutputStream fos = null;
				URL url = new URL(apkUrl);

				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.connect();
				int length = conn.getContentLength();
				InputStream is = conn.getInputStream();

				// File file = new File(savePath);
				// if (!file.exists()) {
				// file.mkdir();
				// }
				// String apkFile = saveFileName;
				// File ApkFile = new File(apkFile);
				// FileOutputStream fos = new FileOutputStream(ApkFile);

				File file = new File(Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/KidsCares/updateApkFile/");
				if (!file.exists()) {
					// ����ļ��в�����,�򴴽�
					file.mkdir();
				}
				// ���ط��������°汾�����д�ļ���
				String apkFile = Environment.getExternalStorageDirectory()
						.getAbsolutePath()
						+ "/KidsCares/updateApkFile/"
						+ "KidsCareFamily.apk";
				File ApkFile = new File(apkFile);
				fos = new FileOutputStream(ApkFile);
				int count = 0;
				byte buf[] = new byte[1024];

				do {
					int numread = is.read(buf);
					count += numread;
					progress = (int) (((float) count / length) * 100);
					// ���½���
					mHandler.sendEmptyMessage(DOWN_UPDATE);
					if (numread <= 0) {
						// �������֪ͨ��װ
						mHandler.sendEmptyMessage(DOWN_OVER);
						break;
					}
					fos.write(buf, 0, numread);
				} while (!interceptFlag);// ���ȡ����ֹͣ����.

				fos.close();
				is.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	};

	/**
	 * ����apk
	 * 
	 * @param url
	 */

	private void downloadApk() {
		downLoadThread = new Thread(mdownApkRunnable);
		downLoadThread.start();
	}

	/**
	 * ��װapk
	 * 
	 * @param url
	 */
	// private void installApk() {
	// File apkfile = new File(saveFileName);
	// if (!apkfile.exists()) {
	// return;
	// }
	// Intent i = new Intent(Intent.ACTION_VIEW);
	// i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
	// "application/vnd.android.package-archive");
	// mContext.startActivity(i);
	//
	// }

	/**
	 * ת��ʱ���ʽ
	 * 
	 * @param time
	 * @return
	 */
	public String toTime(String time) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date date = format.parse(time);
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			return format1.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	private void installApk() {
		// ��ȡ��ǰsdcard�洢·��
		File apkfile = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath()
				+ "/KidsCares/updateApkFile/"
				+ "KidsCareFamily.apk");
		if (!apkfile.exists()) {
			return;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		// ��װ�����ǩ����һ�£����ܳ��ֳ���δ��װ��ʾ
		i.setDataAndType(Uri.fromFile(new File(apkfile.getAbsolutePath())),
				"application/vnd.android.package-archive");
		mContext.startActivity(i);
	}
}

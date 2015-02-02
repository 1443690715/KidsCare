package com.lanbiao.youxiaoyunfamily.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanbiao.youxiaoyunfamily.AppAppliction;
import com.lanbiao.youxiaoyunfamily.R;
import com.lanbiao.youxiaoyunfamily.entity.FamliyInfo;
import com.lanbiao.youxiaoyunfamily.entity.Food;
import com.lanbiao.youxiaoyunfamily.entity.Website;
import com.lanbiao.youxiaoyunfamily.json.JsonTools;
import com.lanbiao.youxiaoyunfamily.loadimg.ImageDownloadTask;
import com.lanbiao.youxiaoyunfamily.util.HttpUtils;

public class FoodTuesdayActivity extends Activity {
	private Website website = new Website();
	private String path = "";
	private Food foods;
	private AppAppliction appliction;
	private List<FamliyInfo> infos;
	private String strStu_id;
	private String afternoon_adds, afternoonlogo_adds, breakfasts,
			breakfastlogos, dinners, dinnerlogos, lunchs, lunchlogos,
			morning_adds, morninglogo_adds;// �����ַ���

	private String[] strBreakfasts, strBreakfastsLogos, strDinners,
			strDinnerLogos, strLunchs, strLunchLogos, strMorning_adds,
			strMorningLogo_adds, strAfternoon_adds, strAfternoonLogo_adds;// ��ȡ��=���ַ���

	private TextView tv_breakfast, tv_morningadd, tv_lunch, tv_afternoon,
			tv_dinner;
	private ImageView iv_breakfast, iv_morningadd, iv_lunch, iv_afternoon,
			iv_dinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.p_baby_foods_item_layout);
		appliction = (AppAppliction) getApplication();
		initView();
		initData();

	}

	public void initView() {
		tv_breakfast = (TextView) findViewById(R.id.txtv_breakfast_id);
		tv_morningadd = (TextView) findViewById(R.id.txtv_morningadd_id);
		tv_lunch = (TextView) findViewById(R.id.txtv_lunch_id);
		tv_afternoon = (TextView) findViewById(R.id.txtv_afternoonadd_id);
		tv_dinner = (TextView) findViewById(R.id.txtv_dinner_id);

		iv_breakfast = (ImageView) findViewById(R.id.iv_breakfast_img);
		iv_morningadd = (ImageView) findViewById(R.id.iv_morningadd_img);
		iv_lunch = (ImageView) findViewById(R.id.iv_lunch_img);
		iv_afternoon = (ImageView) findViewById(R.id.iv_afternoonadd_img);
		iv_dinner = (ImageView) findViewById(R.id.iv_dinner_img);
	}

	public void initData() {
		try {
			infos = appliction.infos;
			for (FamliyInfo info : infos) {
				strStu_id = info.getStudentId();
			}
			path = website.getPath() + website.getQueryfood()
					+ website.getChildid() + strStu_id;
			String strFoods = HttpUtils.getJsonContent(path);
			foods = JsonTools.getFoodInfo("results", strFoods);

			breakfasts = foods.getBreakfast();// ���
			breakfastlogos = foods.getBreakfastlogo();

			morning_adds = foods.getMorning_add();// ����Ӳ�
			morninglogo_adds = foods.getMorning_addlogo();

			lunchs = foods.getLunch();// ���
			lunchlogos = foods.getLunchLogo();

			afternoon_adds = foods.getAfternoon_add();// ����Ӳ�
			afternoonlogo_adds = foods.getAfternoon_addlogo();

			dinners = foods.getDinner();// ���
			dinnerlogos = foods.getDinnerlogo();

			strBreakfasts = breakfasts.split("=");// ���
			strBreakfastsLogos = breakfastlogos.split("=");

			strMorning_adds = morning_adds.split("=");// ����Ӳ�
			strMorningLogo_adds = morninglogo_adds.split("=");

			strLunchs = lunchs.split("=");// ���
			strLunchLogos = lunchlogos.split("=");

			strAfternoon_adds = afternoon_adds.split("=");// ����Ӳ�
			strAfternoonLogo_adds = afternoonlogo_adds.split("=");

			strDinners = dinners.split("=");
			strDinnerLogos = dinnerlogos.split("=");
			for (int i = 0; i < strBreakfasts.length; i++) {
				/***************************** ����Start ************************************/
				String[] strBreakfastsAndDays = strBreakfasts[i].split("##");// ���
				String[] strMorningAndDays = strMorning_adds[i].split("##");// ����Ӳ�
				String[] strLunchAndDays = strLunchs[i].split("##");// ���
				String[] strAfterAndDays = strAfternoon_adds[i].split("##");// ����Ӳ�
				String[] strDinnerAndDays = strDinners[i].split("##");// ���
				String strBreakfast = strBreakfastsAndDays[0];
				String strMorningadd = strMorningAndDays[0];
				String strLunch = strLunchAndDays[0];
				String strAfter = strAfterAndDays[0];
				String strDinner = strDinnerAndDays[0];
				String strDay = strBreakfastsAndDays[1];
				/***************************** ����End ************************************/

				/***************************** ͼƬStart ************************************/
				String[] strBreakfastsLogoAndDays = strBreakfastsLogos[i]
						.split("##");
				String[] strMorningLogoAndDays = strMorningLogo_adds[i]
						.split("##");
				String[] strLunchLogoAndDays = strLunchLogos[i].split("##");
				String[] strAfternoonLogoAndDays = strAfternoonLogo_adds[i]
						.split("##");
				String[] strDinnerLogoAndDays = strDinnerLogos[i].split("##");
				String strBLogo = strBreakfastsLogoAndDays[0];
				String strMLogo = strMorningLogoAndDays[0];
				String strLLogo = strLunchLogoAndDays[0];
				String strALogo = strAfternoonLogoAndDays[0];
				String strDLogo = strDinnerLogoAndDays[0];

				/***************************** ����End ************************************/
				if (strDay.equals("2")) {
					tv_breakfast.setText(strBreakfast);
					tv_morningadd.setText(strMorningadd);
					tv_lunch.setText(strLunch);
					tv_afternoon.setText(strAfter);
					tv_dinner.setText(strDinner);

					ImageDownloadTask imgtask1 = new ImageDownloadTask();
					/** �����ǻ�ȡ�ֻ���Ļ�ķֱ����������� ͼƬ �������ġ�begin */
					DisplayMetrics dm1 = new DisplayMetrics();
					getWindowManager().getDefaultDisplay().getMetrics(dm1);
					imgtask1.setDisplayWidth(dm1.widthPixels);
					imgtask1.setDisplayHeight(dm1.heightPixels);
					imgtask1.execute(strBLogo, iv_breakfast);

					ImageDownloadTask imgtask2 = new ImageDownloadTask();
					DisplayMetrics dm2 = new DisplayMetrics();
					getWindowManager().getDefaultDisplay().getMetrics(dm2);
					imgtask2.setDisplayWidth(dm2.widthPixels);
					imgtask2.setDisplayHeight(dm2.heightPixels);
					imgtask2.execute(strMLogo, iv_morningadd);

					ImageDownloadTask imgtask3 = new ImageDownloadTask();
					DisplayMetrics dm3 = new DisplayMetrics();
					getWindowManager().getDefaultDisplay().getMetrics(dm3);
					imgtask3.setDisplayWidth(dm3.widthPixels);
					imgtask3.setDisplayHeight(dm3.heightPixels);
					imgtask3.execute(strLLogo, iv_lunch);

					ImageDownloadTask imgtask4 = new ImageDownloadTask();
					DisplayMetrics dm4 = new DisplayMetrics();
					getWindowManager().getDefaultDisplay().getMetrics(dm4);
					imgtask4.setDisplayWidth(dm4.widthPixels);
					imgtask4.setDisplayHeight(dm4.heightPixels);
					imgtask4.execute(strALogo, iv_afternoon);

					ImageDownloadTask imgtask5 = new ImageDownloadTask();
					DisplayMetrics dm5 = new DisplayMetrics();
					getWindowManager().getDefaultDisplay().getMetrics(dm5);
					imgtask5.setDisplayWidth(dm5.widthPixels);
					imgtask5.setDisplayHeight(dm5.heightPixels);
					imgtask5.execute(strDLogo, iv_dinner);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

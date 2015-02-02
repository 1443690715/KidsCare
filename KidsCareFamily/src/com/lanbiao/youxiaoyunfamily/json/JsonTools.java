package com.lanbiao.youxiaoyunfamily.json;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;

import android.net.ParseException;

import com.lanbiao.youxiaoyunfamily.entity.AboutUs;
import com.lanbiao.youxiaoyunfamily.entity.ActivityContent;
import com.lanbiao.youxiaoyunfamily.entity.Company;
import com.lanbiao.youxiaoyunfamily.entity.Course;
import com.lanbiao.youxiaoyunfamily.entity.Familyboyinfo;
import com.lanbiao.youxiaoyunfamily.entity.Food;
import com.lanbiao.youxiaoyunfamily.entity.InstallInfo;
import com.lanbiao.youxiaoyunfamily.entity.LeaveInfo;
import com.lanbiao.youxiaoyunfamily.entity.Menu;
import com.lanbiao.youxiaoyunfamily.entity.Messages;
import com.lanbiao.youxiaoyunfamily.entity.MyBabyDynamic;
import com.lanbiao.youxiaoyunfamily.entity.News;
import com.lanbiao.youxiaoyunfamily.entity.Notice;

/**
 * ��json���ݵĽ���
 * 
 * @author zhangmingxun
 * 
 */
public class JsonTools {
	@SuppressWarnings("unused")
	private static final String TAG = "JsonTools";
	String strName = "";
	String strUrl = "";
	String strStuId = "";
	String strCIdandphone = "";

	/**
	 * �ҳ�
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static Familyboyinfo getMybaby(String key, String jsonString) {
		Familyboyinfo familyboyinfo = new Familyboyinfo();
		try {
			JSONObject jObject = new JSONObject(jsonString);
			JSONArray jsonArray = jObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jo = jsonArray.getJSONObject(i);
				familyboyinfo.setBabyname(jo.getString("studentName"));
				familyboyinfo.setSchoolname(jo.getString("schoolName"));
				familyboyinfo.setFamilyboyLogo(jo.getString("studentHead"));
				familyboyinfo.setBirthday(jo.getString("birth"));
				familyboyinfo.setSchoolId(jo.getString("schoolId"));
				familyboyinfo.setTeacherId(jo.getString("teacher_id"));
				familyboyinfo.setStudentId(jo.getString("studentId"));
				familyboyinfo.setFamilyid(jo.getString("familyId"));
				familyboyinfo.setType(jo.getString("type"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return familyboyinfo;
	}

	/**
	 * ������̬
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static MyBabyDynamic getBabyDynamic(String key, String jsonString) {
		MyBabyDynamic dynamic = new MyBabyDynamic();
		String strSendtime = "";
		String strEvaluate = "";
		String strTwo = "";
		String strStuImage = "";
		String strcontentAndimg = "";
		String strcontentAnddynamic = "";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jObject = jsonArray.getJSONObject(i);
				String head = jObject.getString("studentHead");
				String sendtime = jObject.getString("trends_time");
				String evaluate = jObject.getString("remark");
				String stuImage = "";
				if (!jObject.getString("trends_logo").equals("")) {
					stuImage = jObject.getString("trends_logo");
				} else {
					stuImage = "NODATA";
				}

				String content = jObject.getString("trends_content");
				JSONArray jArray = new JSONArray(content);
				String two = "";
				for (int j = 0; j < jArray.length(); j++) {
					JSONObject object = jArray.getJSONObject(j);

					try {
						two = object.getString("trendstwo");
						strTwo += two + "=";
					} catch (Exception e) {
					}

				}
				strcontentAndimg += evaluate + "==" + stuImage + "##";
				strcontentAnddynamic += evaluate + "==" + two + "##";
				dynamic.setStuhead(head);
				strStuImage += stuImage + "##";
				strEvaluate += evaluate + "/";
				strSendtime += sendtime + "=";

			}
			dynamic.setContentAnddynamic(strcontentAnddynamic);
			dynamic.setContentAndimg(strcontentAndimg);
			dynamic.setContentTwo(strTwo);
			dynamic.setStuImage(strStuImage);
			dynamic.setEvaluate(strEvaluate);
			dynamic.setSendTime(strSendtime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dynamic;
	}

	public static Food getFoodInfo(String key, String jsonString) {
		Food food = new Food();
		String strTime = "";
		String strBreakfast = "";
		String strBreakLogo = "";
		String strLunch = "";
		String strLunchLogo = "";
		String strAfternoon = "";
		String strAfternoonLogo = "";
		String strDinner = "";
		String strDinnerLogo = "";
		String strMorning = "";
		String strMorningLogo = "";

		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String cookbooks = object.getString("Cookbook");
				String cooktimes = object.getString("Cooktime");
				strTime += cooktimes + "=";
				String coursetimetype = "";
				coursetimetype = getWeek(cooktimes);
				JSONArray array = new JSONArray(cookbooks);
				for (int j = 0; j < array.length(); j++) {
					JSONObject jObject = array.getJSONObject(j);

					// 1 breakfast
					// if (jObject.getString("breakfast") != null) {
					if (!jObject.isNull("breakfast")) {

						String breakfast = jObject.getString("breakfast");
						String breakfastlog = jObject
								.getString("breakfast_logo");
						strBreakfast += breakfast + "##" + coursetimetype + "=";
						strBreakLogo += breakfastlog + "##" + coursetimetype
								+ "=";

					}
					// 2 morning_add
					// if (jObject.getString("morning_add") != null) {
					if (!jObject.isNull("morning_add")) {
						String morning_add = jObject.getString("morning_add");
						String morning_addlogo = jObject
								.getString("morning_addlogo");
						strMorning += morning_add + "##" + coursetimetype + "=";
						strMorningLogo += morning_addlogo + "##"
								+ coursetimetype + "=";
					}
					// 3 lunch
					// if (jObject.getString("lunch") != null) {
					if (!jObject.isNull("lunch")) {
						String lunch = jObject.getString("lunch");
						String lunch_logo = jObject.getString("lunch_logo");
						strLunch += lunch + "##" + coursetimetype + "=";
						strLunchLogo += lunch_logo + "##" + coursetimetype
								+ "=";
					}
					// 4 afternoon_add
					// if (jObject.getString("afternoon_add") != null) {
					if (!jObject.isNull("afternoon_add")) {
						String afternoon_add = jObject
								.getString("afternoon_add");
						String afternoon_logo = jObject
								.getString("afternoon_addlogo");
						strAfternoon += afternoon_add + "##" + coursetimetype
								+ "=";
						strAfternoonLogo += afternoon_logo + "##"
								+ coursetimetype + "=";
					}
					// 5 dinner
					// if (jObject.getString("dinner") != null) {
					if (!jObject.isNull("dinner")) {
						String dinner = jObject.getString("dinner");
						String dinner_logo = jObject.getString("dinner_logo");
						strDinner += dinner + "##" + coursetimetype + "=";
						strDinnerLogo += dinner_logo + "##" + coursetimetype
								+ "=";
					}

				}
			}

			food.setAfternoon_add(strAfternoon);// ����Ӳ�
			food.setAfternoon_addlogo(strAfternoonLogo);

			food.setDinner(strDinner);// ���
			food.setDinnerlogo(strDinnerLogo);

			food.setLunch(strLunch);// ���
			food.setLunchLogo(strLunchLogo);

			food.setMorning_add(strMorning);// ����Ӳ�
			food.setMorning_addlogo(strMorningLogo);

			food.setBreakfast(strBreakfast);// ���
			food.setBreakfastlogo(strBreakLogo);
			food.setCooktime(strTime);// ʱ��
		} catch (Exception e) {
			e.printStackTrace();
		}

		return food;
	}

	/**
	 * �õ������γ���������������
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static Course getCourseInfo(String key, String jsonString) {
		Course course = new Course();
		String strcoursetime = "";
		String strpm = "";
		String stram = "";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String coursetime = object.getString("coursetime");
				strcoursetime += coursetime + "=";
				String coursetimetype = "";
				coursetimetype = getWeek(coursetime); // �������1,2,3,4,5 �������ڼ�
				String classcourse = object.getString("classcourse");
				JSONObject classcourseobject = new JSONObject(classcourse);
				String pm = classcourseobject.getString("content_pm");
				strpm += pm + "##" + coursetimetype + "=";
				String am = classcourseobject.getString("content_am");
				stram += am + "##" + coursetimetype + "=";
			}
			course.setCoursetime(strcoursetime);
			course.setPm(strpm);
			course.setAm(stram);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return course;
	}

	public static Company getConpanyInfo(String key, String jsonString) {
		Company company = new Company();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				company.setCompany_name(object.getString("company_name"));
				company.setCompany_synopsis(object
						.getString("RegistrationMark"));
				company.setRegistrationMark(object
						.getString("company_synopsis"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return company;
	}

	/**
	 * �������ĵĲ˵�
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static Menu getMenu(String key, String jsonString) {
		Menu menu = new Menu();
		String firstnameandid = "";// һ���˵���name��id
		String strfnameandid = "";// һ���˵�name��id
		String snameAndfid = "";// �����˵���name��һ���˵���id
		String strsnameandfid = "";// �����˵���name��һ���˵���id
		String sNameAndId = "";// �����˵���name��id
		String strSNameAndId = "";// �����˵���name��id

		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String second = object.getString("second_meu");
				JSONArray array = new JSONArray(second);
				for (int j = 0; j < array.length(); j++) {
					JSONObject jObject = array.getJSONObject(j);
					String name = jObject.getString("name");
					String stusecondid = jObject.getString("secondmenuId");
					String sfid = jObject.getString("menuId");
					snameAndfid += name + "=" + sfid + ",";
					sNameAndId += name + "=" + stusecondid + ",";
				}
				String firstname = object.getString("menu_id");
				String firstid = object.getString("menu_name");
				firstnameandid += firstname + "=" + firstid + ",";

			}
			strsnameandfid += snameAndfid;
			strfnameandid += firstnameandid;
			strSNameAndId += sNameAndId;
			menu.setFirstNameAndId(strfnameandid);
			menu.setSecondNameAndId(strSNameAndId);
			menu.setSnameanfdid(strsnameandfid);

		} catch (Exception e) {
		}
		return menu;
	}

	/**
	 * �������Ĳ˵��µ��б�
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static Menu getListData(String key, String jsonString) {
		Menu menu = new Menu();
		String strRemark = "", strtime = "", strlogo = "", strid = "", strtitle = "", strage = "";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String remark = object.getString("shopping_remark");
				String time = object.getString("create_time");
				String logo = object.getString("shopping_logo");
				String id = object.getString("shopping_id");
				String title = object.getString("shopping_name");
				String age = object.getString("shopping_age");

				strRemark += remark + "=";
				strtime += time + "=";
				strlogo += logo + "=";
				strid += id + "=";
				strtitle += title + "=";
				strage += age + "=";
			}
			menu.setName(strtitle);// ����
			menu.setRemark(strRemark);// ����
			menu.setTime(strtime);// ����ʱ��
			menu.setLogo(strlogo);// ͼƬ
			menu.setShoppingid(strid);// ����id
			menu.setAge(strage);// ����
		} catch (Exception e) {
		}
		return menu;
	}

	/**
	 * ���׷�������˵������µ��б�
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static Menu getQueryServiceDatas(String key, String jsonString) {
		Menu menu = new Menu();
		String strRemark = "", strtime = "", strlogo = "", strid = "", strtitle = "", strage = "";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String remark = object.getString("shopping_remark");
				String time = object.getString("create_time");
				String logo = object.getString("shopping_logo");
				String id = object.getString("shopping_id");
				String title = object.getString("shopping_name");
				String age = object.getString("shopping_age");

				strRemark += remark + "=";
				strtime += time + "=";
				strlogo += logo + "=";
				strid += id + "=";
				strtitle += title + "=";
				strage += age + "=";
			}
			menu.setName(strtitle);// ����
			menu.setRemark(strRemark);// ����
			menu.setTime(strtime);// ����ʱ��
			menu.setLogo(strlogo);// ͼƬ
			menu.setShoppingid(strid);// ����id
			menu.setAge(strage);// ����
		} catch (Exception e) {
		}
		return menu;
	}

	/**
	 * ���������б��µ�����
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static Menu getListDatadetail(String key, String jsonString) {
		Menu menu = new Menu();
		String strRemark = "", strlogo = "", strtitle = "", strage = "";
		String strprice = "";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String remark = object.getString("shopping_remark");
				// String time = object.getString("create_time");
				String logo = object.getString("shopping_logo");
				String title = object.getString("shopping_name");
				String age = object.getString("shopping_age");
				String price = object.getString("shopping_price");
				strprice += price;
				strRemark += remark;
				// strtime += time;
				strlogo += logo;
				strtitle += title;
				strage += age;
			}

			menu.setName(strtitle);// ����
			menu.setRemark(strRemark);// ����
			menu.setPrice(strprice);
			menu.setLogo(strlogo);// ͼƬ
			menu.setAge(strage);// ����
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menu;
	}

	/**
	 * �����б�
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static News getNewsData(String key, String jsonString) {
		News news = new News();
		String strtitle = "";
		String strcontent = "";
		String strnewsid = "";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String title = object.getString("news_title");
				String content = object.getString("news_content");
				String newsid = object.getString("news_id");
				strtitle += title + "=";
				strcontent += content + "=";
				strnewsid += newsid + "=";
			}
			news.setNewsid(strnewsid);
			news.setTitle(strtitle);
			news.setContent(strcontent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return news;
	}

	/**
	 * ��������
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static News getNewsDataDetail(String key, String jsonString) {
		News news = new News();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String title = object.getString("news_title");
				String content = object.getString("news_content");
				String logo = object.getString("news_logo");
				news.setTitle(title);
				news.setContent(content);
				news.setImgurl(logo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return news;
	}

	/**
	 * �õ�֪ͨ�б�
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static Notice getMsgInfoData(String key, String jsonString) {
		Notice notice = new Notice();
		String strname = "";
		String strcontent = "";
		String stractivityid = "";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String name = object.getString("activity_name");
				String content = object.getString("activity_content");
				String activityid = object.getString("activity_id");
				strname += name + "=";
				strcontent += content + "=";
				stractivityid += activityid + "=";
			}
			notice.setName(strname);
			notice.setContent(strcontent);
			notice.setActivityid(stractivityid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notice;
	}

	/**
	 * �õ�֪ͨ����
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static Notice getMsgInfoDataDetail(String key, String jsonString) {
		Notice notice = new Notice();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String contact = object.getString("activity_phonepeople");
				String begintime = object.getString("activity_begntime");
				String acitivtiyid = object.getString("activity_id");
				String contactphone = object.getString("activity_phone");
				String endtime = object.getString("activity_endtime");
				String title = object.getString("activity_title");
				String content = object.getString("activity_content");
				String siginstatus = object.getString("activity_siginstatus");
				String type = object.getString("type");
				notice.setType(type);
				notice.setSiginstatus(siginstatus);
				notice.setContactname(contact);
				notice.setBegintime(begintime);
				notice.setEndtime(endtime);
				notice.setActivityid(acitivtiyid);
				notice.setContactphone(contactphone);
				notice.setTitle(title);
				notice.setContent(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notice;
	}

	/**
	 * ��������
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static AboutUs getAboutus(String key, String jsonString) {
		AboutUs aboutUs = new AboutUs();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String title = object.getString("school_name");
				String logo = object.getString("logoaddress");
				String content = object.getString("remark");
				String address = object.getString("address");
				String website = object.getString("schoolwebsite");
				String phone = object.getString("phone");

				aboutUs.setTitle(title);
				aboutUs.setLogo(logo);
				aboutUs.setRemark(content);
				aboutUs.setAddress(address);
				aboutUs.setSchoolwebsite(website);
				aboutUs.setPhone(phone);
			}
		} catch (Exception e) {
		}
		return aboutUs;
	}

	/**
	 * ��б�
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static ActivityContent getActivityList(String key, String jsonString) {
		ActivityContent activityContent = new ActivityContent();
		String strtitles = "", strcontents = "", strtimes = "", strlogos = "", strids = "";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String titles = object.getString("title");
				String contents = object.getString("content");
				String times = object.getString("time");
				String logos = object.getString("logo");
				String ids = object.getString("id");

				strtitles += titles + "=";
				strcontents += contents + "=";
				strtimes += times + "=";
				strlogos += logos + "=";
				strids += ids + "=";
			}
			activityContent.setActivitytitle(strtitles);
			activityContent.setActivitycontents(strcontents);
			activityContent.setActivityimg(strlogos);
			activityContent.setActivitycreatetime(strtimes);
			activityContent.setActivityid(strids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return activityContent;
	}

	/**
	 * �����
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static ActivityContent getActivityDetail(String key,
			String jsonString) {
		ActivityContent activityContent = new ActivityContent();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String content = object.getString("content");
				String time = object.getString("time");
				String title = object.getString("title");
				String logo = object.getString("logon");
				String classname = object.getString("class_name");
				String teacherName = object.getString("teacherName");

				activityContent.setActivitytitle(title);
				activityContent.setActivitycontents(content);
				activityContent.setActivitycreatetime(time);
				activityContent.setActivityimg(logo);
				activityContent.setActivityapplytoclass(classname);
				activityContent.setActivitysendteacher(teacherName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return activityContent;
	}

	/**
	 * ������Ϣ����
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static LeaveInfo getLeaveAllInfo(String key, String jsonString) {
		LeaveInfo leaveInfo = new LeaveInfo();
		String strftimeandfnameandflogoandftypeandfcontent = "";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				/**
				 * String a = "";// �ڶ��з������Ȱ�object��Ϊһ���ַ��� int s =
				 * a.indexOf("{"family_content":"Fghfghdghdfghdgggghdfghdgfdf
				 * ghdfhgdfghdfghdhhfghdhdfhg","student_region":"���صĸ���",
				 * "family_time":"2014-03-28
				 * 11:14:48","type":1,"student_logo":"http
				 * :\/\/img28.nipic.com\/20111007
				 * \/150769_130358469144_1.jpg"}");// if(s>-1){ ���￪ʼдjson���������� }
				 */
				if (!object.isNull("family_time")) {
					String ftime = object.getString("family_time");
					String fregion = object.getString("student_region");
					String flogo = object.getString("student_logo");
					String fcontent = object.getString("family_content");
					String ftype = object.getString("type");
					strftimeandfnameandflogoandftypeandfcontent += ftime + "="
							+ fregion + "=" + flogo + "=" + ftype + "="
							+ fcontent + "##";
					leaveInfo.setStudent_region(fregion);// ���ӹ�ϵ��
				} else if (!object.isNull("teacher_time")) {
					String ttime = object.getString("teacher_time");
					String tcontent = object.getString("teacher_content");
					String tlogo = object.getString("teacher_logo");
					String tname = object.getString("teacher_name");
					String type = object.getString("type");
					strftimeandfnameandflogoandftypeandfcontent += ttime + "="
							+ tname + "=" + tlogo + "=" + type + "=" + tcontent
							+ "##";

				}
			}
			leaveInfo.setMsg(strftimeandfnameandflogoandftypeandfcontent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return leaveInfo;
	}

	/**
	 * �õ�����ÿ������������ǩ��ʱ��
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static Familyboyinfo getSignin(String key, String jsonString) {
		Familyboyinfo familyboyinfo = new Familyboyinfo();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				if (!object.isNull("moring_signin")) {
					String msgnin = object.getString("moring_signin");
					familyboyinfo.setMoring_signin(msgnin);
				} else if (!object.isNull("afternoon_signin")) {
					String aftergnin = object.getString("afternoon_signin");
					familyboyinfo.setAfter_signin(aftergnin);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return familyboyinfo;
	}

	/**
	 * TODO
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static Messages getTSendbyStuMsg(String key, String jsonString) {
		Messages message = new Messages();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			int code = jsonObject.getInt("responseCode");
			if (code != -2) {

				JSONArray jsonArray = jsonObject.getJSONArray(key);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject object = jsonArray.getJSONObject(i);
					String content = object.getString("remark");
					String bid = object.getString("birth_id");
					message.setContent(content);
					message.setBirthid(bid);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	/**
	 * �ж�ʱ�������ڼ�
	 * 
	 * @param pTime
	 * @return
	 * @throws Exception
	 */
	private static String getWeek(String pTime) throws Exception {

		String Week = "";

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();

		try {

			c.setTime(format.parse(pTime));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {

			Week = "7";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 2) {
			Week = "1";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 3) {
			Week = "2";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 4) {
			Week = "3";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 5) {
			Week = "4";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 6) {
			Week = "5";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 7) {
			Week = "6";
		}

		return Week;
	}

	/**
	 * �õ������µ�����
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static InstallInfo checkUpdateDatas(String key, String jsonString) {
		InstallInfo installInfo = new InstallInfo();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String content = object.getString("endition_content");
				String showVersion = object.getString("endition_remark");
				String showTime = object.getString("endition_time");
				// String newVcode = object.getString("endition_num");
				int newVcode = object.getInt("endition_num");
				String apkUrl = object.getString("url");

				// ����˵��
				installInfo.setNewContent(content);
				// ����ʱ��
				installInfo.setUpdateTime(showTime);
				// ���µİ汾��
				installInfo.setNewVersionCode(newVcode);
				// ����apk��·��
				installInfo.setApkUrl(apkUrl);
				// �汾��Ϣ
				installInfo.setShowVersionCode(showVersion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return installInfo;
	}
}

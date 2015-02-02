package com.lanbiao.youxiaoyunteacher.json;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.lanbiao.youxiaoyunteacher.entity.BabyDynimic;
import com.lanbiao.youxiaoyunteacher.entity.Classinfo;
import com.lanbiao.youxiaoyunteacher.entity.Company;
import com.lanbiao.youxiaoyunteacher.entity.Contact;
import com.lanbiao.youxiaoyunteacher.entity.Familyinfo;
import com.lanbiao.youxiaoyunteacher.entity.Menu;
import com.lanbiao.youxiaoyunteacher.entity.MessageDetail;
import com.lanbiao.youxiaoyunteacher.entity.MessageInfo;
import com.lanbiao.youxiaoyunteacher.entity.Studentinfo;
import com.lanbiao.youxiaoyunteacher.entity.StudyRaise;

/**
 * ��ɶ�json���ݵĽ��� �������Ҫ�޸�
 * 
 * @author zhangmingxun
 * 
 */
public class JsonTools {
	private static final String TAG = "JsonTools";
	static Classinfo classinfo = new Classinfo();

	/**
	 * �༶
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static Classinfo getClassId(String key, String jsonString) {
		Classinfo myClass = new Classinfo();
		try {
			JSONObject jObject = new JSONObject(jsonString);
			JSONArray jsonArray = jObject.getJSONArray(key);// �õ���ʦ�ĸ�����Ϣ
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jo = jsonArray.getJSONObject(i);
				myClass.setId(jo.getString("id"));// ��ʦID
				myClass.setShoolId(jo.getString("schoolId"));
				myClass.setTeachername(jo.getString("teacherName"));
				myClass.setSchoolname(jo.getString("schoolName"));
				myClass.setType(jo.getString("type"));
				myClass.setPhone(jo.getString("phone"));
				myClass.setPassword(jo.getString("password"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myClass;
	}

	/**
	 * ClassInfo
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static Classinfo getClasslistKeyMaps(String key, String jsonString) {
		Classinfo myClass = new Classinfo();
		String strClassName = "";
		String strClassId = "";
		try {
			JSONObject jObject = new JSONObject(jsonString);
			JSONArray jsonArray = jObject.getJSONArray(key);// ��ʦ������ѧ��
			Log.v(TAG, "onetwo-----" + jsonArray);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jo = jsonArray.getJSONObject(i);
				String str = jo.getString("classname");
				String classid = jo.getString("classid");
				if (strClassName.indexOf(str) < 0) {
					strClassName += str + ",";
				}
				if (strClassId.indexOf(classid) < 0) {
					strClassId += classid + ",";
				}

			}
			myClass.setClassname(strClassName);// �õ��༶������
			myClass.setClassid(strClassId);// �õ��༶ID
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myClass;
	}

	public static Studentinfo getStudentlistKeyMaps(String key,
			String jsonString) {
		Studentinfo stu = new Studentinfo();
		String strUrl = "";
		String strStuId = "";
		String strName = "";
		try {
			JSONObject jObject = new JSONObject(jsonString);
			JSONArray jsonArray = jObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jo = jsonArray.getJSONObject(i);
				String name = jo.getString("student_name");
				String url = jo.getString("student_logo");
				String studentid = jo.getString("student_id");
				if (strStuId.indexOf(studentid) < 0) {
					strStuId += studentid + ",";
				}
				if (strUrl.indexOf(url) < 0) {
					strUrl += url + ",";
				}
				if (strName.indexOf(name) < 0) {
					strName += name + ",";
				}
			}
			stu.setSid(strStuId); // �õ�ѧ��ID
			stu.setName(strName);// �õ��༶������
			stu.setHead(strUrl);// �õ�ѧ��ͷ��
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stu;
	}

	public static Familyinfo getFamilylistKeyMaps(String key, String jsonString) {
		Familyinfo familyinfo = new Familyinfo();
		try {
			JSONObject jObject = new JSONObject(jsonString);
			JSONArray jsonArray = jObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jo = jsonArray.getJSONObject(i);
				try {
					String family = jo.getString("family_info");
					JSONArray jArray = new JSONArray(family);
					for (int j = 0; j < jArray.length(); j++) {
						JSONObject jsonObject = jArray.getJSONObject(j);// family����
						String childId = jsonObject.getString("childId");
						String phone = jsonObject.getString("phone");
						// if (strCId.indexOf(childId) < 0) {
						// strCId += childId + ",";
						// }
						// if (strPhone.indexOf(phone) < 0) {
						// strPhone += phone + ",";
						// }

					}

				} catch (Exception e) {
				}
			}
			// familyinfo.setPhone(strPhone);// �ҳ�����
			// familyinfo.setChildId(strCId);// ѧ��ID

		} catch (Exception e) {
			e.printStackTrace();
		}
		return familyinfo;
	}

	public static BabyDynimic getBabyDynimicselect(String key, String jsonString) {
		BabyDynimic babyDynimic = new BabyDynimic();
		String strTrendsname = "";
		String strNames = "";
		String strId = "";
		String strtwo = "";
		try {
			JSONObject jObject = new JSONObject(jsonString);
			JSONArray jsonArray = jObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String name = jsonObject.getString("name");
				String id = jsonObject.getString("id");
				strId += id + ",";
				strNames += name + ",";
				String trends_twoInfo = jsonObject.getString("trends_twoInfo");
				JSONArray array = new JSONArray(trends_twoInfo);
				for (int j = 0; j < array.length(); j++) {
					JSONObject object = array.getJSONObject(j);
					String trendnames = object.getString("name");
					String two = object.getString("trendssecondId");
					strtwo += two + ",";
					strTrendsname += trendnames + ",";
				}
			}
			babyDynimic.setLifestatetwo(strtwo);
			babyDynimic.setLifestateIds(strId);
			babyDynimic.setLifestatename(strNames);
			babyDynimic.setLifestate(strTrendsname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return babyDynimic;
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

	public static Studentinfo getTeahcerStus(String key, String jsonString) {
		Studentinfo studentinfo = new Studentinfo();
		String strStuid = "", strStuname = "", strStuhead = "";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String Stuid = object.getString("id");
				String Stuname = object.getString("name");
				String Stuhead = object.getString("head");
				if (strStuid.indexOf(Stuid) < 0) {
					strStuid += Stuid + ",";
				}
				if (strStuname.indexOf(Stuname) < 0) {
					strStuname += Stuname + ",";
				}
				if (strStuhead.indexOf(Stuhead) < 0) {
					strStuhead += Stuhead + ",";
				}
			}
			studentinfo.setSid(strStuid);
			studentinfo.setName(strStuname);
			studentinfo.setHead(strStuhead);
		} catch (Exception e) {
		}
		return studentinfo;
	}

	public static StudyRaise getoneMenu(String key, String jsonString) {
		StudyRaise studyRaise = new StudyRaise();
		String firstnameandid = "";
		String snameAndfid = "";
		String secondNameAndId = "";
		String strSecondNameAndId = "";
		String strFirstnameandid = "";
		String strsnameandfid = "";
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
					String stusecondid = jObject.getString("studyssecondId");
					String sfid = jObject.getString("sfId");
					snameAndfid += name + "=" + sfid + ",";
					secondNameAndId += name + "=" + stusecondid + ",";
				}
				String firstname = object.getString("first_name");
				String firstid = object.getString("first_id");
				firstnameandid += firstname + "=" + firstid + ",";

			}
			strsnameandfid += snameAndfid;
			strFirstnameandid += firstnameandid;
			strSecondNameAndId += secondNameAndId;
			studyRaise.setFirstnameandid(strFirstnameandid);
			studyRaise.setNameandid(strsnameandfid);
			studyRaise.setSecondnameandid(strSecondNameAndId);

		} catch (Exception e) {
		}
		return studyRaise;

	}

	public static StudyRaise getListData(String key, String jsonString) {
		StudyRaise studyRaise = new StudyRaise();
		String strcontent = "", strtime = "", strlogo = "", strid = "", strtitle = "", strapplyToclass = "";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String content = object.getString("insrease_content");
				String time = object.getString("insrease_time");
				String logo = object.getString("insrease_logo");
				String id = object.getString("insrease_id");
				String title = object.getString("insrease_title");
				String applyToclass = object.getString("insrease_class");

				strcontent += content + "=";
				strtime += time + "=";
				strlogo += logo + "=";
				strid += id + "=";
				strtitle += title + "=";
				strapplyToclass += applyToclass + "=";
			}
			studyRaise.setTitle(strtitle);// ����
			studyRaise.setContent(strcontent);// ����
			studyRaise.setTimes(strtime);// ����ʱ��
			studyRaise.setImgurl(strlogo);// ͼƬ
			studyRaise.setContentid(strid);// ����id
			studyRaise.setApplytoclass(strapplyToclass);// ���÷�Χ
		} catch (Exception e) {
		}
		return studyRaise;
	}

	public static StudyRaise getListdataDetail(String key, String jsonString) {
		StudyRaise studyRaise = new StudyRaise();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String content = object.getString("insrease_content");
				String time = object.getString("insrease_time");
				String logo = object.getString("insrease_logo");
				String id = object.getString("insrease_id");
				String title = object.getString("insrease_title");
				String applyToclass = object.getString("insrease_class");
				studyRaise.setTitle(title);// ����
				studyRaise.setContent(content);// ����
				studyRaise.setTimes(time);// ����ʱ��
				studyRaise.setImgurl(logo);// ͼƬ
				studyRaise.setContentid(id);// ����id
				studyRaise.setApplytoclass(applyToclass);// ���÷�Χ
			}
		} catch (Exception e) {
		}
		return studyRaise;
	}

	/**
	 * ѧ����Ϣ
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static Contact getStuinfo(String key, String jsonString) {
		Contact contact = new Contact();
		String strname = "", strhead = "", strid = "";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String name = object.getString("name");
				String head = object.getString("head");
				String id = object.getString("id");
				strname += name + "=";
				strhead += head + "=";
				strid += id + "=";
			}
			contact.setName(strname);
			contact.setHead(strhead);
			contact.setId(strid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contact;
	}

	/**
	 * �õ��W�����L��Ϣ
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static Contact getStufamilinfo(String key, String jsonString) {
		Contact contact = new Contact();
		String strphoneandregionandid = "";
		String stridandname = "";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String name = object.getString("name");
				String head = object.getString("head");
				String id = object.getString("id");
				String fphone = object.getString("family_phone");
				JSONArray array = new JSONArray(fphone);
				for (int j = 0; j < array.length(); j++) {
					JSONObject jobject = array.getJSONObject(j);
					String phone = jobject.getString("phone");
					String childRegion = jobject.getString("childRegion");
					strphoneandregionandid += phone + "=" + childRegion + ","
							+ id + "��";

				}
				stridandname += id + "=" + name + ",";

			}
			contact.setIdandname(stridandname);
			contact.setIdandphoneandchildRegion(strphoneandregionandid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contact;
	}

	/**
	 * �õ�ѧ���ҳ���������Ϣ�б�
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static MessageInfo getMsgList(String key, String jsonString) {
		MessageInfo messageInfo = new MessageInfo();
		String strcontent = "";
		String strregion = "";
		String strlogo = "";
		String strconverstationid = "";
		String strtime = "";
		String strfid = "";
		String strstatus = "";
		String strMsgs = "";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String content = object.getString("content");// ����
				String region = object.getString("student_region");// ��ϵ
				String logo = object.getString("student_logo");// ͷ��
				String converstationid = object.getString("conversation_id");// ��ϵid
				String time = object.getString("message_time");// ʱ��
				String fid = object.getString("family_id");// �ҳ�id
				String status = object.getString("status");// ״̬ �Ѷ� /δ��

				if (status.equals("0")) {
					strMsgs += region + "=" + converstationid + "=" + fid + "="
							+ status + "##";
				}
				strcontent += content + "=";
				strregion += region + "=";
				strlogo += logo + "=";
				strconverstationid += converstationid + "=";
				strtime += time + "=";
				strfid += fid + "=";
				strstatus += status + "=";
			}
			messageInfo.setMsgs(strMsgs);
			messageInfo.setContent(strcontent);
			messageInfo.setSturegion(strregion);
			messageInfo.setStulogo(strlogo);
			messageInfo.setConversation_id(strconverstationid);
			messageInfo.setMsgtime(strtime);
			messageInfo.setFamilyid(strfid);
			messageInfo.setStatus(strstatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return messageInfo;
	}

	/**
	 * �鿴�ҳ���������
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static MessageDetail getFamilyInfo(String key, String jsonString) {
		MessageDetail detail = new MessageDetail();
		String strftimeandfnameandflogoandftypeandfcontent = "";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				if (!object.isNull("family_time")) {
					String ftime = object.getString("family_time");
					String fregion = object.getString("student_region");
					String flogo = object.getString("student_logo");
					String fcontent = object.getString("family_content");
					String ftype = object.getString("type");
					strftimeandfnameandflogoandftypeandfcontent += ftime + "="
							+ fregion + "=" + flogo + "=" + ftype + "="
							+ fcontent + "##";
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
			detail.setTimeandregionandlogoandcontentandtype(strftimeandfnameandflogoandftypeandfcontent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detail;
	}

	/**
	 * ������Բ�Ҫ��
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static MessageInfo getNoReply(String key, String jsonString) {
		MessageInfo messageInfo = new MessageInfo();
		String strMsgs = "";
		String strregion = "";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				if (object.getString("status").equals("0")) {
					String region = object.getString("student_region");// ��ϵ
					String converstationid = object
							.getString("conversation_id");// ��ϵid
					String fid = object.getString("family_id");// �ҳ�id
					String status = object.getString("status");// ״̬ �Ѷ� /δ��
					if (status.equals("0")) {
						strMsgs += region + "=" + converstationid + "=" + fid
								+ "=" + status + "##";
						strregion += region + ",";
					}
				}
			}
			messageInfo.setSturegion(strregion);
			messageInfo.setMsgs(strMsgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return messageInfo;
	}

	/**
	 * һ���˵�
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
	 * �����˵��µ��б�
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static Menu getRaiseListData(String key, String jsonString) {
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
	 * ����
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static Menu getRaiseListDatadetail(String key, String jsonString) {
		Menu menu = new Menu();
		String strRemark = "", strtime = "", strlogo = "", strid = "", strtitle = "", strage = "";
		String strprice = "";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String remark = object.getString("shopping_remark");
				String time = object.getString("create_time");
				String logo = object.getString("shopping_logo");
				String title = object.getString("shopping_name");
				String age = object.getString("shopping_age");
				String price = object.getString("shopping_price");
				strprice += price;
				strRemark += remark;
				strtime += time;
				strlogo += logo;
				strtitle += title;
				strage += age;
			}

			menu.setName(strtitle);// ����
			menu.setRemark(strRemark);// ����
			// menu.setTime(strtime);// ����ʱ��
			menu.setPrice(strprice);
			menu.setLogo(strlogo);// ͼƬ
			// menu.setShoppingid(strid);// ����id
			menu.setAge(strage);// ����
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menu;
	}

}

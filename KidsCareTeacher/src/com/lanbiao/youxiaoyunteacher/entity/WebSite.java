package com.lanbiao.youxiaoyunteacher.entity;

public class WebSite {
	private String path = "http://211.149.187.10:8080/KidsYun/dataExchange";
	// private String path = "http://192.168.1.101:8080/KidsYun/dataExchange";
	private String username = "&userName=";
	private String password = "&userPwd=";
	// ��ʦ��Ϣ
	private String student = "/student.action?requestCode=103";
	// ��½
	private String login = "/applogin.action?requestCode=101";
	// ��ʦ��Ӧ�İ༶
	private String mystudent = "/querystudent.action?requestCode=131";
	// ������̬����
	private String addtrends = "/addtrends.action?requestCode=105";
	// �������� ��˾��Ϣ
	private String querycompany = "/querycompany.action?requestCode=133";
	// ��̬ѡ��
	private String trendsmenu = "/trendsmenu.action?requestCode=106";
	// �ύ������Ϣ
	private String addsuggestion = "/addsuggestion.action?requestCode=132";
	// ��������
	private String addsignin = "/addsignin.action?requestCode=104";
	// ѧϰ���һ���˵�
	private String sturaiseonemeu = "/querystudy.action?requestCode=107";
	// ѧϰ��߶����˵�
	private String sturaisetwomeu = "/querystudylist.action?requestCode=108";
	// ѧϰ�����ϸҳ��
	private String sturaisedetail = "/querstudyid.action?requestCode=109";
	// ��ϵ�ҳ�
	private String contactfamily = "/queryallstu.action?requestCode=134";
	// �ҳ��������б�
	private String replylist = "/messageteacher.action?requestCode=111";
	// ͨ��id-->�ҳ�����Ϣ��ʾ
	private String msg = "/messagetbyt.action?requestCode=112";
	// �ظ�����
	private String reply = "/messageaddteacher.action?requestCode=113";

	// ���׷���
	private String queryshoppingmenu = "/queryshoppingmenu.action?requestCode=124";
	private String strSchoolid = "&schoolid=";
	// ���׷�����������б�
	private String shoppingdetail = "/queryshopping.action?requestCode=125";
	private String secondmunuid = "&secondmenuid=";
	// ���׷��������ϸҳ��
	private String shoppingbyid = "/queryshoppingbyid.action?requestCode=126";
	private String shoppingid = "&shoppingid=";
	// �޸�����
	private String updateuser = "/updateuser.action?requestCode=141";
	private String strusername = "&userName=";
	private String struserpwd = "&userPwd=";
	private String strusernew = "&newuserpwd=";
	private String strrepetition = "&newonespwd=";
	private String strold = "&oldpwd=";

	private String teacherid = "&teacherid=";
	private String type = "&type=";
	private String classbyid = "&class_byid=";
	private String childids = "&childids=";
	private String base64_logo = "&base64_logo=";
	private String logo_name = "&logo_name=";
	private String Trendsfirstid = "&Trendsfirstid=";
	private String Trendstwoid = "&Trendstwoid=";
	private String Teachremark = "&Teachremark=";
	private String schoolid = "&schoolid=";
	private String strphone = "&phone=";
	private String strremark = "&remark=";
	private String strtype = "&type=";
	private String strsignintype = "&Signintype=";
	private String strstuid = "&Studentid=";
	private String strsecondid = "&second_id=";
	private String strdetailid = "&id=";
	private String strtid = "&teachid=";
	private String strfamilyid = "&familyid=";
	private String strcontent = "&content=";
	private String strconverid = "&conversationid=";

	public String getUpdateuser() {
		return updateuser;
	}

	public String getStrusername() {
		return strusername;
	}

	public String getStruserpwd() {
		return struserpwd;
	}

	public String getStrusernew() {
		return strusernew;
	}

	public String getStrrepetition() {
		return strrepetition;
	}

	public String getStrold() {
		return strold;
	}

	public String getQueryshoppingmenu() {
		return queryshoppingmenu;
	}

	public String getStrSchoolid() {
		return strSchoolid;
	}

	public String getShoppingdetail() {
		return shoppingdetail;
	}

	public String getSecondmunuid() {
		return secondmunuid;
	}

	public String getShoppingbyid() {
		return shoppingbyid;
	}

	public String getShoppingid() {
		return shoppingid;
	}

	public String getStrconverid() {
		return strconverid;
	}

	public String getReply() {
		return reply;
	}

	public String getStrcontent() {
		return strcontent;
	}

	public String getReplylist() {
		return replylist;
	}

	public String getMsg() {
		return msg;
	}

	public String getStrtid() {
		return strtid;
	}

	public String getStrfamilyid() {
		return strfamilyid;
	}

	public String getContactfamily() {
		return contactfamily;
	}

	public String getSturaisedetail() {
		return sturaisedetail;
	}

	public String getStrdetailid() {
		return strdetailid;
	}

	public String getStrsecondid() {
		return strsecondid;
	}

	public String getSturaisetwomeu() {
		return sturaisetwomeu;
	}

	public String getSturaiseonemeu() {
		return sturaiseonemeu;
	}

	public String getAddsignin() {
		return addsignin;
	}

	public String getStrsignintype() {
		return strsignintype;
	}

	public String getStrstuid() {
		return strstuid;
	}

	public String getQuerycompany() {
		return querycompany;
	}

	public String getAddsuggestion() {
		return addsuggestion;
	}

	public String getStrphone() {
		return strphone;
	}

	public String getStrremark() {
		return strremark;
	}

	public String getStrtype() {
		return strtype;
	}

	private String str;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getTrendsmenu() {
		return trendsmenu;
	}

	public String getSchoolid() {
		return schoolid;
	}

	/**
	 * querystudent.action?requestCode=131
	 * 
	 * @return
	 */
	public String getMystudent() {
		return mystudent;
	}

	/**
	 * class_byid=
	 * 
	 * @return
	 */
	public String getClassbyid() {
		return classbyid;
	}

	public String getStudent() {
		return student;
	}

	public String getTeacherid() {
		return teacherid;
	}

	public String getType() {
		return type;
	}

	/**
	 * http://211.149.187.10:8080/KidsYun/dataExchange
	 * 
	 * @return
	 */
	public String getPath() {
		return path;
	}

	public String getLogin() {
		return login;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getAddtrends() {
		return addtrends;
	}

	public String getChildids() {
		return childids;
	}

	public String getBase64_logo() {
		return base64_logo;
	}

	public String getLogo_name() {
		return logo_name;
	}

	public String getTrendsfirstid() {
		return Trendsfirstid;
	}

	public String getTrendstwoid() {
		return Trendstwoid;
	}

	public String getTeachremark() {
		return Teachremark;
	}

}

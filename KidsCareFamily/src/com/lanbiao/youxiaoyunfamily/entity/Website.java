package com.lanbiao.youxiaoyunfamily.entity;

public class Website {
	// private String path = "http://211.149.187.10:8080/KidsYun/dataExchange";
	private String localPath = "http://192.168.1.100/KidsYun/dataExchange";
	private String path = "http://kidyun.com/dataExchange";
	// ��½
	private String login = "/applogin.action?requestCode=101";
	private String username = "&userName=";
	private String password = "&userPwd=";

	// ������̬
	private String querytrends = "/querytrends.action?requestCode=114";
	private String familyid = "&familyid=";

	// �����γ�
	private String querycourse = "/querycourse.action?requestCode=120";
	private String childid = "&childid=";

	// ����ʳ��
	private String queryfood = "/querycook.action?requestCode=121";

	// �������� ��˾��Ϣ
	private String querycompany = "/querycompany.action?requestCode=133";

	// �ύ������Ϣ
	private String addsuggestion = "/addsuggestion.action?requestCode=132";
	private String strphone = "&phone=";
	private String strremark = "&remark=";
	private String strtype = "&type=";

	// �������ķ���
	private String queryshoppingmenu = "/queryshoppingmenu.action?requestCode=124";
	private String strSchoolid = "&schoolid=";

	// ���������б�
	private String shoppingdetail = "/queryshopping.action?requestCode=125";
	private String secondmunuid = "&secondmenuid=";

	// ������ϸҳ��
	private String shoppingbyid = "/queryshoppingbyid.action?requestCode=126";
	private String shoppingid = "&shoppingid=";

	// ����+֪ͨ
	private String querynews = "/queryinforlist.action?requestCode=116";
	private String typeno = "&typeNum=";
	private String strcurrentschool = "&schoolId=";

	// ����+֪ͨ����
	private String querynewsdetail = "/queryinforbyid.action?requestCode=117";
	private String strnewsid = "&newsId=";
	private String stractivityid = "&activityId=";
	private String strfamilyid = "&family=";

	// �����
	private String joinactivity = "/addsignactivity.action?requestCode=118";
	private String strfamilyids = "&familyids=";
	private String stuid = "&stuId=";

	// �������� ͨ�����ӵ�id
	private String aboutbyid = "/queryschool.action?requestCode=119";

	// ���ӻ
	private String activityfamily = "/familyboylist.action?requestCode=122";
	private String activitystuid = "&studentid=";

	// �����
	private String activityfamilydetail = "/queryfamilyboy.action?requestCode=123";
	private String activitystuiddetail = "&familyboyid=";
	// ��ѯ����
	private String querymsg = "/messagquerybyf.action?requestCode=127";
	private String strteachid = "&teachid=";

	// �ظ�����
	private String sendmsg = "/messageaddfamily.action?requestCode=128";
	private String strtid = "&teacherid=";
	private String strcontent = "&content=";
	// �鿴����ǩ������
	private String signin = "/appmodle.action?requestCode=102";
	private String strchildid = "&childid=";
	// �޸�����
	private String updateuser = "/updateuser.action?requestCode=141";
	private String strusername = "&userName=";
	private String struserpwd = "&userPwd=";
	private String strusernew = "&newuserpwd=";
	private String strrepetition = "&newonespwd=";
	private String strold = "&oldpwd=";
	// �鿴��ʦ�Ա�����ף����
	private String queryTMsg = "/queryFbirth.action?requestCode=136";
	private String strStuid = "&stuid=";
	// �޸ı�������ף���鿴״̬ ��ֻ��ʾΪһ�Σ�
	private String msgstatus = "/updatebirth.action?requestCode=137";
	private String strbirthid = "&birthid=";
	// ���汾����
	private String checkUpdate = "/updateAPK.action?requestCode=142&name=KidsCareFamily";
	private String num = "&num=";
	private String strType = "&type=0";
	// ���׷�������
	private String shopName = "&shoppingname=";

	public String getShopName() {
		return shopName;
	}

	public String getLocalPath() {
		return localPath;
	}

	public String getCheckUpdate() {
		return checkUpdate;
	}

	public String getNum() {
		return num;
	}

	public String getStrType() {
		return strType;
	}

	public String getStrbirthid() {
		return strbirthid;
	}

	public String getMsgstatus() {
		return msgstatus;
	}

	public String getQueryTMsg() {
		return queryTMsg;
	}

	public String getStrStuid() {
		return strStuid;
	}

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

	public String getSignin() {
		return signin;
	}

	public String getStrchildid() {
		return strchildid;
	}

	public String getSendmsg() {
		return sendmsg;
	}

	public String getStrtid() {
		return strtid;
	}

	public String getStrcontent() {
		return strcontent;
	}

	public String getQuerymsg() {
		return querymsg;
	}

	public String getStrteachid() {
		return strteachid;
	}

	public String getActivityfamilydetail() {
		return activityfamilydetail;
	}

	public String getActivitystuiddetail() {
		return activitystuiddetail;
	}

	public String getActivityfamily() {
		return activityfamily;
	}

	public String getActivitystuid() {
		return activitystuid;
	}

	public String getAboutbyid() {
		return aboutbyid;
	}

	public String getJoinactivity() {
		return joinactivity;
	}

	public String getStrfamilyids() {
		return strfamilyids;
	}

	public String getStuid() {
		return stuid;
	}

	public String getStrfamilyid() {
		return strfamilyid;
	}

	public String getStractivityid() {
		return stractivityid;
	}

	public String getStrnewsid() {
		return strnewsid;
	}

	public String getQuerynewsdetail() {
		return querynewsdetail;
	}

	public String getQuerynews() {
		return querynews;
	}

	public String getTypeno() {
		return typeno;
	}

	public String getStrcurrentschool() {
		return strcurrentschool;
	}

	public String getShoppingbyid() {
		return shoppingbyid;
	}

	public String getShoppingid() {
		return shoppingid;
	}

	public String getShoppingdetail() {
		return shoppingdetail;
	}

	public String getSecondmunuid() {
		return secondmunuid;
	}

	public String getQueryshoppingmenu() {
		return queryshoppingmenu;
	}

	public String getStrSchoolid() {
		return strSchoolid;
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

	public String getQuerycompany() {
		return querycompany;
	}

	public String getQueryfood() {
		return queryfood;
	}

	public String getQuerycourse() {
		return querycourse;
	}

	public String getChildid() {
		return childid;
	}

	public String getQuerytrends() {
		return querytrends;
	}

	public String getFamilyid() {
		return familyid;
	}

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

}

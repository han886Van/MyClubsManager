package com.gpnu.association.util;

public class CommonUtil {
	public static final String USER_ROLE_ADMIN = "1";   	//系统管理员角色 
	public static final String USER_ROLE_TEACHER = "2";		//老师
	public static final String USER_ROLE_STUDENT = "3";		//学生
	
	public static final String ASSOCIATION_ROLE_PERSIDENT = "1";  //社长角色
	public static final String ASSOCIATION_ROLE_VICE_PERSIDENT = "2";  //副社长角色
	public static final String ASSOCIATION_ROLE_MEMBER = "3";  //社员角色
	
	public static final String MALE = "1";	//男性
	public static final String FEMALE = "0";	//女性
	
	public static final String APPLY_STATE = "0";	//待审核状态
	public static final String ALLOW_STATE = "1";	//通过状态
	public static final String REFUSE_STATE = "2";	//拒绝状态
	public static final String APPLY_QUIT_STATE = "3";	//申请退出、申请解散状态
	
	
	public static final String PROFESSIONAL_ACADEMIC = "1";	//专业学术类
	public static final String SCIENCE_TECHNOLOGY = "2";	//科技创新类
	public static final String INTEREST_ART = "3";			//艺术兴趣类
	public static final String SPORTS_FITNESS = "4";		//体育健身类
	public static final String PUBLIC_SERVICE = "5";		//公益服务类
	
	 //**上传附件类型**//
	public static final String ATTACHMENT_TYPE_HEADIMG = "HEADPIC";  //头像
	public static final String ATTACHMENT_TYPE_COMMENT = "COMMENTPIC";  //评论图片
	public static final String ATTACHMENT_TYPE_EXCEL = "EXCEL";  //excel文件
	
	/*上传附件路径*/
	public static String ASSOCIATION_ATTACHMENT_PATH = "association.attachment.path";
	public static String ASSOCIATION_ATTACHMENT_PREVIEW = "association.attachment.preview";
}

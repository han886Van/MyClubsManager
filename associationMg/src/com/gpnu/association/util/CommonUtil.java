package com.gpnu.association.util;

public class CommonUtil {
	public static final String USER_ROLE_ADMIN = "1";   	//系统管理员角色 
	public static final String USER_ROLE_TEACHER = "2";		//老师
	public static final String USER_ROLE_STUDENT = "3";		//学生
	
	public static final String ASSOCIATION_ROLE_PERSIDENT = "1";  //社长角色
	public static final String ASSOCIATION_ROLE_MEMBER = "2";  //社员角色
	
	public static final String MALE = "1";	//男性
	public static final String FEMALE = "0";	//女性
	
	public static final String APPLY_STATE = "0";	//待审核状态
	public static final String ALLOW_STATE = "1";	//通过状态
	public static final String REFUSE_STATE = "2";	//拒绝状态
	
	
	
	 //**上传附件类型**//
	public static final String ATTACHMENT_TYPE_HEADIMG = "1";  //头像
	public static final String ATTACHMENT_TYPE_COMMENT = "2";  //评论图片
	
	/*上传附件路径*/
	public static String ASSOCIATION_ATTACHMENT_PATH = "association.attachment.path";
	public static String ASSOCIATION_ATTACHMENT_PREVIEW = "association.attachment.preview";
}

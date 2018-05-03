package com.gpnu.association.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gpnu.association.mapper.AssociationAndUserMapper;
import com.gpnu.association.mapper.AssociationMapper;
import com.gpnu.association.mapper.TypeMapper;
import com.gpnu.association.mapper.UserMapper;
import com.gpnu.association.util.CommonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MybatisTest {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private TypeMapper typeMapper;
	
	@Autowired
	private AssociationMapper associationMapper;
	
	@Autowired
	private AssociationAndUserMapper anuMapper;
	
	@Test
	public void testUser() {
		Map user = new HashMap();
	/*	user.put("userId", 13);
		user.put("userName", "小群软糖");
		user = userMapper.get(user);
		System.out.println(user);*/
		
		/*user.put("userName", "大猪蹄子");
		user.put("account", "dazhutizi");
		user.put("password", "123");
		user.put("phone", "13278412442");
		user.put("email", "dazhutiz@qq.com");
		user.put("sex", CommonUtil.MALE);
		user.put("userType", CommonUtil.USER_ROLE_STUDENT);
		user.put("college", "音乐学院");
		user.put("createTime", new Date());
		userMapper.add(user);*/
		
	//	userMapper.delete(6);
	/*	
		user.put("userId", "5");
		user.put("userName", "哼大猪蹄子");
		user.put("password", "123");
		user.put("phone", "13278412442");
		user.put("email", "dazhutiz@qq.com");
		user.put("sex", CommonUtil.MALE);
		user.put("userType", CommonUtil.USER_ROLE_STUDENT);
		userMapper.update(user);*/
		
		
		/*List<Map> resultMap = userMapper.list();
		System.out.println(resultMap);*/
		
		user.put("account", "dazhutizi");
		user.put("password", "123");
		user = userMapper.findPasswordByAccount(user);
		System.out.println(user);
	}

	
	@Test
	public void testType(){
		Map type = new HashMap();
		type.put("type", "二级学院");
		List<Map> res = typeMapper.get(type);
		System.out.println(res);
	}
	
	@Test
	public void testAssociation(){
		Map paraMap = new HashMap();
		/*paraMap.put("name", "计科女篮");
		paraMap.put("briefIntroduction", "无兄弟，不篮球！");
		paraMap.put("applyComments", "锻炼身体，休闲娱乐");
		associationMapper.add(paraMap);*/
		
	/*	paraMap.put("associationId", "1");
		paraMap.put("state", CommonUtil.ALLOW_STATE);
		paraMap.put("place", "新旧篮球场");
		associationMapper.update(paraMap);*/
		
	/*	paraMap.put("associationId", "1");
		paraMap.put("name", "计科女篮");
		paraMap.put("state", CommonUtil.REFUSE_STATE);
		List<Map> res = associationMapper.get(paraMap);
		System.out.println(res);*/
		
		paraMap.put("associationId", "1");
		associationMapper.delete(paraMap);
	}
	
	@Test
	public void testAssociationAndUser(){
		Map paraMap = new HashMap();
		paraMap.put("id", "1");
		paraMap.put("checkPersonId", "1");
		paraMap.put("userState", CommonUtil.ALLOW_STATE);
		anuMapper.delete(1);
	}

}

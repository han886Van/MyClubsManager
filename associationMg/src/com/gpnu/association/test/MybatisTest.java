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

import com.gpnu.association.mapper.UserMapper;
import com.gpnu.association.util.CommonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MybatisTest {

	@Autowired
	private UserMapper userMapper;

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


}

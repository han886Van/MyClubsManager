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
import com.gpnu.association.mapper.AttachmentMapper;
import com.gpnu.association.mapper.EventMapper;
import com.gpnu.association.mapper.LikeCommentsMapper;
import com.gpnu.association.mapper.MaterielMapper;
import com.gpnu.association.mapper.NewsMapper;
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
	
	@Autowired
	private EventMapper eventMapper;
	
	@Autowired
	private MaterielMapper materielMapper;
	
	@Autowired
	private NewsMapper newsMapper;
	
	@Autowired
	private AttachmentMapper attachmentMapper;
	
	@Autowired
	private LikeCommentsMapper likeComtMapper;
	
	@Test
	public void testUser() {
		Map paraMap = new HashMap();
		int pagesize = 2; //每页的数量
		
		//3是从第几页开始
		paraMap.put("start", (9-1)*pagesize);
		paraMap.put("pagesize", pagesize);
		List<Map> user = userMapper.get(paraMap);
		System.out.println(user);
		
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
		
	/*	user.put("account", "dazhutizi");
		user.put("password", "123");
		user = userMapper.findPasswordByAccount(user);*/
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

	@Test
	public void testEvent(){
		Map paraMap = new HashMap();
		/*paraMap.put("title", "计科篮球赛活动申请");
		paraMap.put("content", "计科院各班级比赛，决出名次并可领取相应奖励");
		paraMap.put("associationId", "1");
		paraMap.put("userId", "4");
		paraMap.put("state", CommonUtil.ALLOW_STATE);
		eventMapper.add(paraMap);
		
		paraMap.put("id", "1");
		eventMapper.delete(1);
		
		paraMap.put("title", "计科篮球赛活动申请111");
		paraMap.put("state", CommonUtil.ALLOW_STATE);
		eventMapper.update(paraMap);*/
		paraMap.put("id", "2");
		List<Map> res = eventMapper.get(paraMap);
		System.out.println(res);
	}
	
	@Test
	public void testMateriel(){
		Map paraMap = new HashMap();
		/*paraMap.put("title", "计科球队经费申请");
		paraMap.put("content", "计科球队经费用于篮球、队服、矿泉水购买");
		paraMap.put("associationId", "1");
		paraMap.put("userId", "4");
		paraMap.put("state", CommonUtil.ALLOW_STATE);
		materielMapper.add(paraMap);*/
		paraMap.put("id", 1);
		paraMap.put("applyComments", "穷死了，补贴一下好吧");
		paraMap.put("checkComments", "通过");
		paraMap.put("state", CommonUtil.REFUSE_STATE);
		materielMapper.update(paraMap);
		
		List<Map> res = materielMapper.get(paraMap);
		System.out.println(res);
		
		materielMapper.delete(1);
	}
	
	@Test
	public void testNews(){
		Map paraMap = new HashMap();
		paraMap.put("title", "计科院");
		paraMap.put("id", "1");
		List<Map> res = newsMapper.get(paraMap);
		System.out.println(res);
		
	}
	
	@Test
	public void testAttachment(){
		Map paraMap = new HashMap();
		paraMap.put("id", 1);
		paraMap.put("state", CommonUtil.ALLOW_STATE);
		paraMap.put("name", "做我的猫");
		attachmentMapper.update(paraMap);
		List<Map> res = attachmentMapper.get(paraMap);
		attachmentMapper.deleteFile(paraMap);
		System.out.println(res);
	}
	
	@Test
	public void testLikeCommt(){
		Map paraMap = new HashMap();
		paraMap.put("commentsId", 1);
		paraMap.put("userId", 6);
		likeComtMapper.add(paraMap);
		List<Map> res = likeComtMapper.get(paraMap);
		
		System.out.println(res);
		res = likeComtMapper.getCount(paraMap);
		System.out.println(res);
		likeComtMapper.cancelLike(paraMap);
	}
}

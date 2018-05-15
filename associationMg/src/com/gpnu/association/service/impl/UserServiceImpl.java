package com.gpnu.association.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.gpnu.association.mapper.AttachmentMapper;
import com.gpnu.association.mapper.UserMapper;
import com.gpnu.association.service.UserService;
import com.gpnu.association.util.CommonUtil;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	UserMapper userMapper;
	
	@Resource
	AttachmentMapper attachmentMapper;
	
	/*@Autowired
	CommonMapper commonMapper;*/
	

	@Override
	public JSONObject saveOrUpdate(Map paraMap) {
		JSONObject json = new JSONObject();
		Map user = new HashMap();
		if(!paraMap.containsKey("userId") || paraMap.get("userId").equals("")){
			paraMap.put("createTime", new Date());
			userMapper.add(paraMap);
			json.put("msg", "666");
			json.put("comment", "添加账号成功");
			
			user = userMapper.findPasswordByAccount(paraMap);
			json.put("addUser", user);
		}
		else{
			userMapper.update(paraMap);
			json.put("msg", "666");
			json.put("comment", "修改个人信息成功");
			user = userMapper.findPasswordByAccount(paraMap);
			json.put("updateUser", user);
		}
		return json;
	}

	@Override
	public JSONObject login(Map paraMap) { 
		JSONObject json = new JSONObject();
		Map user = new HashMap();
		Map loginUser = userMapper.findPasswordByAccount(paraMap);
		if(loginUser != null){
			paraMap.put("attachmentType", CommonUtil.ATTACHMENT_TYPE_HEADIMG);
		//	Map headPic = 
			json.put("msg", "666");
			json.put("comment", "登陆成功");
			json.put("loginUser", loginUser);
		}else{
			json.put("msg", "555");
			json.put("comment", "账号或密码错误！");
		}
		return json;
	}
	
	@Override
	public List<Map> getList(Map paraMap) {
		JSONObject json = new JSONObject();
		List<Map> loginUser = userMapper.get(paraMap);
		return loginUser;
	}

	@Override
	public JSONObject modifyPassword(Map paraMap) {
		JSONObject json = new JSONObject();
		List<Map> getUser = userMapper.get(paraMap);
		if(getUser.size() == 0 || getUser.isEmpty()){
			json.put("msg", "555");
			json.put("comment", "服务器登录超时，请重新登录！");
		}else{
			if(getUser.get(0).get("passowrd").equals("originalPassword")){
				userMapper.update(paraMap);
				json.put("msg", "666");
				json.put("comment", "修改密码成功！");
			}else{
				json.put("msg", "555");
				json.put("comment", "原始密码错误！");
			}
		}
		return json;
	}

}

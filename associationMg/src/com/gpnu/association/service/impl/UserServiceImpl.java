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
import com.gpnu.association.mapper.UserMapper;
import com.gpnu.association.pojo.User;
import com.gpnu.association.service.UserService;
import com.gpnu.association.util.CommonUtil;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	UserMapper userMapper;
	
	/*@Autowired
	CommonMapper commonMapper;*/
	
	@Override
	public List<Map> list() {
		return userMapper.list();
	}

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
			json.put("msg", "666");
			json.put("comment", "登陆成功");
			json.put("loginUser", loginUser);
		}else{
			json.put("msg", "555");
			json.put("comment", "账号或密码错误！");
		}
		return json;
	}

}

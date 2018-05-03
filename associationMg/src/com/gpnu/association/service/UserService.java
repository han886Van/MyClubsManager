package com.gpnu.association.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.gpnu.association.pojo.User;

public interface UserService {
	public JSONObject login(Map paraMap);
	public List<Map> list();
	public JSONObject saveOrUpdate(Map paraMap);
}

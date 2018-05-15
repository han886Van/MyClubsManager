package com.gpnu.association.service;

import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSONObject;

public interface UserService {
	public JSONObject login(Map paraMap);
	public JSONObject saveOrUpdate(Map paraMap);
	public List<Map> getList(Map paraMap);
	public JSONObject modifyPassword(Map paraMap);
}

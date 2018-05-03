package com.gpnu.association.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.gpnu.association.mapper.UserMapper;
import com.gpnu.association.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/login")
	@ResponseBody
	public JSONObject login(@RequestParam Map paraMap, HttpServletRequest request,
			HttpServletResponse response){
		JSONObject json = new JSONObject();
		try {
			json = userService.login(paraMap);
		} catch (Exception e) {
			json.put("msg", "发生错误！");
			logger.error(e);
		}
		return json;
	}
	
	@RequestMapping("/saveOrUpdateUser")
	@ResponseBody
	public JSONObject saveOrUpdateUser(@RequestParam Map paraMap, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		try {
			json = userService.saveOrUpdate(paraMap);
		} catch (Exception e) {
			json.put("msg", "发生错误，操作失败！");
			logger.error(e);
		}
		return json;
	}
	
	
	@RequestMapping("/homepage")
	public String homepage(Map paraMap){
		return "jsp/index";
	}
}

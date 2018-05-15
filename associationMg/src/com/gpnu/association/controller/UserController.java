package com.gpnu.association.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.gpnu.association.mapper.UserMapper;
import com.gpnu.association.service.AttachmentService;
import com.gpnu.association.service.UserService;
import com.gpnu.association.util.CommonUtil;


@Controller
@RequestMapping("/user")
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	AttachmentService attachmentService;
	
	@RequestMapping("/login")
	@ResponseBody
	public JSONObject login(@RequestBody Map paraMap, HttpServletRequest request,
			HttpServletResponse response){
		JSONObject json = new JSONObject();
		try {
			json = userService.login(paraMap);
			if(json.get("msg").equals("666")){
				//登录成功，将用户信息存入session中。
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", json.get("loginUser"));
			}
		} catch (Exception e) {
			json.put("msg", "发生错误！");
			logger.error(e);
		}
		return json;
	}
	
	@RequestMapping("/loginOut")
	@ResponseBody
	public JSONObject loginOut(@RequestBody Map paraMap, HttpServletRequest request,
			HttpServletResponse response){
		JSONObject json = new JSONObject();
		try {
			//退出账号
			HttpSession session = request.getSession();
			session.removeAttribute("loginUser");
		} catch (Exception e) {
			json.put("msg", "发生错误！");
			logger.error(e);
		}
		return json;
	}
	
	@RequestMapping("/modifyPassword")
	@ResponseBody
	public JSONObject modifyPassword(@RequestBody Map paraMap, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		try {
			json = userService.modifyPassword(paraMap);
		} catch (Exception e) {
			json.put("msg", "发生错误，操作失败！");
			logger.error(e);
		}
		return json;
	}
	
	@RequestMapping("/saveOrUpdateUser")
	@ResponseBody
	public JSONObject saveOrUpdateUser(@RequestBody Map paraMap, HttpServletRequest request,
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
	
	
	@RequestMapping("/personalCenter")
	@ResponseBody
	public JSONObject personalCenter(@RequestBody Map paraMap, HttpServletRequest request,
			HttpServletResponse response){
		JSONObject json = new JSONObject();
		try {
			Map searchMap = (Map) request.getSession().getAttribute("loginUser");
			List<Map> personalCenter = userService.getList(searchMap);
			searchMap.put("attachmentType", CommonUtil.ATTACHMENT_TYPE_HEADIMG);
			List<Map> headImg = attachmentService.getFile(searchMap);
			if(headImg.size() != 0 && !headImg.isEmpty()){
				json.put("headImg", headImg);
			}
			json.put("msg", "666");
			json.put("comment", "请求成功");
			json.put("getUser", personalCenter.get(0));
		} catch (Exception e) {
			json.put("msg", "发生错误，操作失败！");
			logger.error(e);
		}
		return json;
	}
}

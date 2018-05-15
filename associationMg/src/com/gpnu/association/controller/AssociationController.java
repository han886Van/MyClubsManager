package com.gpnu.association.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.gpnu.association.service.AssociationService;

@Controller
@RequestMapping("/association")
public class AssociationController {
	private static Logger logger = Logger.getLogger(AssociationController.class);
	
	@Autowired
	private AssociationService associationService;
	
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public JSONObject saveOrUpdate(@RequestBody Map paraMap, HttpServletRequest request,
			HttpServletResponse response){
		JSONObject json = new JSONObject();
		try {
		    associationService.saveOrUpdate(paraMap);
		    json.put("msg", "666");
		    json.put("comment", "请求成功");
		} catch (Exception e) {
			json.put("msg", "555");
			json.put("comment", "发送错误");
			logger.error(e);
		}
		return json;
	}
	
	@RequestMapping("/dissolveAssociation")
	@ResponseBody
	public JSONObject dissolveAssociation(@RequestBody Map paraMap, HttpServletRequest request,
			HttpServletResponse response){
		JSONObject json = new JSONObject();
		try {
		    associationService.dissolveAssociation(paraMap);
		    json.put("msg", "666");
		    json.put("comment", "请求成功");
		} catch (Exception e) {
			json.put("msg", "555");
			json.put("comment", "发送错误");
			logger.error(e);
		}
		return json;
	}
}

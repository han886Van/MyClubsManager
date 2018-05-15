package com.gpnu.association.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.gpnu.association.service.AssociationAndUserService;
import com.gpnu.association.service.AssociationService;

@Controller
@RequestMapping("/associationAndUser")
public class AssociationAndUserController {
private static Logger logger = Logger.getLogger(AssociationAndUserController.class);
	
	@Autowired
	private AssociationAndUserService assoAndUserService;
	
	@RequestMapping("/applyAssociation")
	@ResponseBody
	public JSONObject applyAssociation(@RequestBody Map paraMap, HttpServletRequest request,
			HttpServletResponse response){
		JSONObject json = new JSONObject();
		try {
		    assoAndUserService.applyAssociation(paraMap);
		    json.put("msg", "666");
		    json.put("comment", "请求成功");
		} catch (Exception e) {
			json.put("msg", "发生错误！");
			logger.error(e);
		}
		return json;
	}
	
	
	@RequestMapping("/modifyApplyAssociationStatus")
	@ResponseBody
	public JSONObject modifyApplyAssociationStatus(@RequestBody Map paraMap, HttpServletRequest request,
			HttpServletResponse response){
		JSONObject json = new JSONObject();
		try {
		    assoAndUserService.modifyApplyAssociationStatus(paraMap);
		    json.put("msg", "666");
		    json.put("comment", "请求成功");
		} catch (Exception e) {
			json.put("msg", "发生错误！");
			logger.error(e);
		}
		return json;
	}
}

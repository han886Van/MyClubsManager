package com.gpnu.association.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gpnu.association.service.AttachmentService;
import com.gpnu.association.util.CommonUtil;
import com.gpnu.association.util.PropertiesUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/attachment")
public class AttachmentController {
	private static Logger logger = Logger.getLogger(AttachmentController.class);

	@Autowired
	private AttachmentService attachmentService;

	@RequestMapping("/uploadFile")
	@ResponseBody
	public JSONObject uploadFile(@RequestBody Map paraMap, @RequestParam(value = "uploadExcel", required = false) MultipartFile file, HttpServletRequest request,
			HttpServletResponse response){
		JSONObject json = new JSONObject();
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = null;
		try {
			paraMap.put("name", file.getOriginalFilename());
			paraMap.put("attachmentType", CommonUtil.ATTACHMENT_TYPE_HEADIMG);
			attachmentService.uploadFile(paraMap, file, request, response);
			json.put("msg", "success");
		} catch (Exception e) {
			json.put("msg", "发生错误！");
			logger.error(e);
		}
		return json;
	}

}

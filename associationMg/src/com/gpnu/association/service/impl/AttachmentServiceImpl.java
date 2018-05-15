package com.gpnu.association.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gpnu.association.mapper.AttachmentMapper;
import com.gpnu.association.service.AttachmentService;
import com.gpnu.association.util.CommonUtil;
import com.gpnu.association.util.PropertiesUtils;


@Service
@SuppressWarnings("unchecked")
public class AttachmentServiceImpl implements AttachmentService {

	@Resource
	private AttachmentMapper attachmentMapper;
	
	public Map uploadFile(Map paraMap, MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		String path = paraMap.get("attachmentId").toString();
		String type = paraMap.get("attachmentType").toString();
		// 从属性文件取出文件的本地存放路径
		String baseDir = PropertiesUtils.readProperties().getString(CommonUtil.ASSOCIATION_ATTACHMENT_PATH);
		String fileDir = baseDir + File.separator + path.toString() + File.separator + type;
		String previewPath = PropertiesUtils.readProperties().getString(CommonUtil.ASSOCIATION_ATTACHMENT_PREVIEW);

		File tempFile = new File(fileDir);

		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		File uploadedFile = new File(fileDir+ File.separator + file.getOriginalFilename());
		try {
			FileCopyUtils.copy(file.getBytes(), uploadedFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map map = new HashMap();
		map.put("attachmentType", paraMap.get("attachmentType"));
		map.put("attachmentId", paraMap.get("attachmentId"));
		map.put("name", file.getOriginalFilename());
		map.put("path", File.separator + path + File.separator+type+File.separator);
		map.put("attachmentUrl", previewPath+"/" + path + "/"+type+"/"+file.getOriginalFilename());
		map.put("associationId", paraMap.containsKey("associationId")==true?paraMap.get("associationId"):"");
		map.put("userId", paraMap.get("userId"));
		map.put("createTime", new Date());
		map.put("state", paraMap.get("state"));
		attachmentMapper.add(map);

		return map;
	}
	

	@Override
	public void deleteFile(Map paraMap) {
		attachmentMapper.deleteFile(paraMap);
		
	}


	@Override
	public List<Map<?, ?>> getHeade(Object param) {
		return null;//获取头像
	}


}

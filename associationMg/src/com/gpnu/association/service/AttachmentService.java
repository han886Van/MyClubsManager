package com.gpnu.association.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;


@SuppressWarnings("unchecked")
public interface AttachmentService {

	Map uploadFile(Map paraMap, MultipartFile file,HttpServletRequest request, HttpServletResponse response);
	public void deleteFile(Map paraMap);
	public List<Map> getFile(Map param);
}

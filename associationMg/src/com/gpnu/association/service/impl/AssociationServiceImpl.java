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
import com.gpnu.association.mapper.AssociationMapper;
import com.gpnu.association.service.AssociationService;
import com.gpnu.association.service.UserService;
import com.gpnu.association.util.CommonUtil;

@Service
public class AssociationServiceImpl implements AssociationService {

	@Resource
	private AssociationMapper associationMapper;
	

	@Override
	public void saveOrUpdate(Map paraMap) {
		if(!paraMap.containsKey("associationId")){
			paraMap.put("state", CommonUtil.APPLY_STATE);
			paraMap.put("createTime", new Date());
			associationMapper.add(paraMap);
		}else{
			associationMapper.update(paraMap);
		}
	}


	@Override
	public void dissolveAssociation(Map paraMap) {
		associationMapper.delete(paraMap);
		
	}

}

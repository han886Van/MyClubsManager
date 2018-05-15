package com.gpnu.association.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.gpnu.association.mapper.AssociationAndUserMapper;
import com.gpnu.association.service.AssociationAndUserService;
import com.gpnu.association.util.CommonUtil;

@Service
public class AssociationAndUserServiceImpl implements AssociationAndUserService {

	@Resource
	private AssociationAndUserMapper assoAndUserMapper;

	@Override
	public void applyAssociation(Map paraMap) {
		paraMap.put("userState", CommonUtil.APPLY_STATE);
		paraMap.put("applyTime", new Date());
		paraMap.put("userType", CommonUtil.ASSOCIATION_ROLE_MEMBER);
		assoAndUserMapper.add(paraMap);
	}

	@Override
	public void modifyApplyAssociationStatus(Map paraMap) {
		JSONObject json = new JSONObject();
		if(!paraMap.containsKey("userState")){
			Map assoAndUserMap = assoAndUserMapper.getSingle(paraMap);
			paraMap.put("applyTime", new Date());
			assoAndUserMapper.update(paraMap);
		}else{
			assoAndUserMapper.delete(Integer.parseInt(paraMap.get("id").toString()));
		}
	}


	

	
}

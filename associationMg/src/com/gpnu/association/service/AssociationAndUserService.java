package com.gpnu.association.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface AssociationAndUserService {
	public void modifyApplyAssociationStatus(Map paraMap);
	public void applyAssociation(Map paraMap);
}

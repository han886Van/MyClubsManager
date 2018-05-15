package com.gpnu.association.service;

import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSONObject;

public interface AssociationService {
	public void saveOrUpdate(Map paraMap);
	public void dissolveAssociation(Map paraMap);
	
}

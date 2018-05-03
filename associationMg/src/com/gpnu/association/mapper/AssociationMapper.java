package com.gpnu.association.mapper;

import java.util.List;
import java.util.Map;



public interface AssociationMapper {
	public void add(Map paraMap);
	public void delete(Map paraMap);
	public List<Map> get(Map paraMap);
	public void update(Map paraMap);
}

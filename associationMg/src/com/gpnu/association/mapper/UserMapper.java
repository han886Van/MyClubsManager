package com.gpnu.association.mapper;

import java.util.List;
import java.util.Map;


public interface UserMapper {
	public void add(Map paraMap);
	public void delete(int id);
	public Map get(Map paraMap);
	public void update(Map paraMap);
	public List<Map> list();
	public Map findPasswordByAccount(Map paraMap);
}

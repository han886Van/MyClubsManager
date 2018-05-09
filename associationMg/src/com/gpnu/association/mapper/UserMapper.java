package com.gpnu.association.mapper;

import java.util.List;
import java.util.Map;


public interface UserMapper {
	public void add(Map paraMap);
	public void delete(int id);
	public List<Map> get(Map paraMap);
	public void update(Map paraMap);
	public Map findPasswordByAccount(Object paraMap);
}

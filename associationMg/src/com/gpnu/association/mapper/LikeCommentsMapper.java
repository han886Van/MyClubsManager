package com.gpnu.association.mapper;

import java.util.List;
import java.util.Map;


public interface LikeCommentsMapper {
	public void add(Map paraMap);
	public void cancelLike(Map paraMap);
	public List<Map> get(Map paraMap);
	public List<Map> getCount(Map paraMap);
}

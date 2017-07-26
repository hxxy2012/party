package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gw.base.exception.OtherException;

/**
 * 资讯分类
 * @author hanxu
 *	2015-12-20
 */
public interface NewsTypeService {
	
	/**
	 * 获取下级分类列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	List<Map<String, Object>> getNewsClassList(HashMap<String,String> requestMap) throws OtherException;
	
}

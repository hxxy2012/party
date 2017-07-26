package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;
import com.gw.zhsq.web.call.NewsTypeClientCall;
import com.gw.zhsq.web.service.NewsTypeService;

/**
 * 资讯分类
 * @author fuyun
 *	2015-10-12
 */
@Service("newsTypeService")
public class NewsTypeServiceImpl implements NewsTypeService {
	
	//获取下级分类列表
	@Override
	public List<Map<String, Object>> getNewsClassList(HashMap<String, String> requestMap) throws OtherException {
		String classId = requestMap.get("classId");//资讯类信息父ID
		String shequId = requestMap.get("shequId");
		if(classId==null || "".equals(classId)){
			classId = "0";//一级资讯类信息父ID
		}
		HashMap<String, String> searchMap = new HashMap<String, String>();
		searchMap.put("supClassId", classId);//资讯类信息父ID
		searchMap.put("shequId", shequId);//社区ID
		List<Map<String, Object>> classList = NewsTypeClientCall.getNewsClassList(searchMap);
		return classList;
	}
	
}




package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;
import com.gw.zhsq.web.call.TXLClientCall;
import com.gw.zhsq.web.service.TXLService;

/**
 * 通讯录
 * @author hanxu
 *	2015-11-02
 */
@Service("txlService")
public class TXLServiceImpl implements TXLService {
	
	//获取通讯录列表
	@Override
	public Map<String, Object> getTXLListMap(HashMap<String, String> requestMap)throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String pageno = "1",pageSize = "10";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		String shequId = requestMap.get("shequId");//社区ID
		paramMap.put("shequId", shequId);
		paramMap.put("page", pageno);//当前页数
		paramMap.put("pageSize", pageSize);//每页条数
		Map<String, Object> txlListMap = TXLClientCall.getTXLListMap(paramMap);
		return txlListMap;
	}

}

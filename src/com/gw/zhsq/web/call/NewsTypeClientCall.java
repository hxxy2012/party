package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.NewsTypeClient;

/**
 * 获取资讯分类
 * @author hanxu
 *	2015-04-02
 */
public class NewsTypeClientCall extends BaseCall{

	/**
	 * 获取下级资讯分类列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static List<Map<String, Object>> getNewsClassList(HashMap<String, String> requestMap) throws OtherException {
		List<Map<String, Object>> newsClassList = null;
		ApiResponse apiResponse = null;
		apiResponse = NewsTypeClient.getNewsClassList(requestMap);
		if (apiResponse.isSuccess()) {
			newsClassList = apiResponse.getListValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return newsClassList;
	}
		
}

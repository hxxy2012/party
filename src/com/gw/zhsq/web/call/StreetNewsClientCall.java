package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.StreetNewsClient;

/**
 * 街道资讯
 * @author hanxu
 *	2015-04-02
 */
public class StreetNewsClientCall extends BaseCall{

	/**
	 * 获取下级资讯分类列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static List<Map<String, Object>> getStreetInfoClassList(HashMap<String, String> requestMap) throws OtherException {
		List<Map<String, Object>> newsClassList = null;
		ApiResponse apiResponse = null;
		apiResponse = StreetNewsClient.getStreetInfoClassList(requestMap);
		if (apiResponse.isSuccess()) {
			newsClassList = apiResponse.getListValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return newsClassList;
	}
		
	
	/**
	 * 获取街道资讯列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getStreetNewsListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> getGoodsMap = null;
		ApiResponse apiResponse = null;
		apiResponse = StreetNewsClient.getStreetNewsListMap(requestMap);
		if (apiResponse.isSuccess()) {
			getGoodsMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return getGoodsMap;
	}
	
	/**
	 * 获取政务详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getStreetNewsMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> newsMap = null;
		ApiResponse apiResponse = null;
		apiResponse = StreetNewsClient.getStreetNewsMap(requestMap);
		if (apiResponse.isSuccess()) {
			newsMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return newsMap;
	}
	
}

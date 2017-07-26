package com.gw.zhsq.web.client;

import java.util.HashMap;
import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 通讯录
 * @author hanxu
 *	2015-11-02
 */
public class TXLClient extends BaseClient{

	/**
	 * 获取通讯录列表
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getTXLListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getZhsqTXLList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取通讯录列表失败！");
		}
		return apiResponse;
	}
	
}

package com.gw.zhsq.web.client;

import java.util.HashMap;

import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 获取预约提醒人员列表
 * @author lihui
 *
 * 2016年6月1日
 */
public class XishanYuyueAdminClient extends BaseClient {
	public static ApiResponse getXishanYuyueAdminListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getXishanYuyueAdminListMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl, requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取预约提醒人员列表失败！");
		}
		return apiResponse;
	}
}

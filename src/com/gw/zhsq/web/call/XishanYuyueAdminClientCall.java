package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.Map;

import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.XishanYuyueAdminClient;

/**
 * 获取预约提醒人员列表
 * @author lihui
 *
 * @Time 2016年6月2日
 */
public class XishanYuyueAdminClientCall extends BaseCall {
	public static Map<String, Object> getXishanYuyueAdminListMap(HashMap<String, String> requestMap)
			throws OtherException {
		Map<String, Object> yuyueAdminListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = XishanYuyueAdminClient.getXishanYuyueAdminListMap(requestMap);
		if (apiResponse.isSuccess()) {
			yuyueAdminListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(), apiResponse.getErrormsg());
		}
		return yuyueAdminListMap;
	}
}

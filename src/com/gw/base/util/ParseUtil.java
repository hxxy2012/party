package com.gw.base.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.LinkedCaseInsensitiveMap;

public class ParseUtil {
	
	static final String ERRORCODE = "success";
	static final String ERRORMSG = "data";
	static final String DATA = "data";
	static final String PAGE = "page";
	static final String RESULT = "result";
	static final String SUCCESS = "success";
	static final String LIST = "list";
	static final String TOTALCOUNT = "totalcount";

	@SuppressWarnings("unchecked")
	public static ApiResponse parse(String responseText) throws JsonParseException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> map = mapper.readValue(responseText, LinkedCaseInsensitiveMap.class);
		
		System.out.println("map===>"+map);
		
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setErrorcode(StringUtil.nvl(map.get("success")));
		apiResponse.setErrormsg(StringUtil.nvl(map.get("errorMsg")));
		
		// 如果接口调用结果正确
		if (StringUtil.nvl(map.get("success")).equals("0")) {
			apiResponse.setSuccess(true);

			// 根据Date的对象类型封装Apiresponse
			if (map.get(DATA) instanceof String || map.get(DATA) instanceof Integer) {
				String string = map.get(DATA).toString();
				apiResponse.setStringValue(string);
			} else if (map.get(DATA) instanceof List) {
				List<Map<String,Object>> list = (List<Map<String,Object>>)map.get(DATA);
				apiResponse.setListValue(list);
			} else if (map.get(DATA) instanceof Map) {
				HashMap<String, Object> data = (HashMap<String, Object>) map.get(DATA);
				if (data.get(LIST) != null && data.get(LIST) instanceof List) {
					List<Map<String,Object>> list = (List<Map<String,Object>>)map.get(LIST);
					apiResponse.setListValue(list);
					apiResponse.setTotalcount(Integer.valueOf(data.get(TOTALCOUNT).toString()));
				} else {
					filterMap(data);
					apiResponse.setHashMapValue(data);
				}
			}
		}else if(StringUtil.nvl(map.get("success")).equals("2")){
			apiResponse.setSuccess(true);
			apiResponse.setStringValue(StringUtil.nvl(map.get("success")));
		}
		return apiResponse;
	}

	@SuppressWarnings("unchecked")
	public static ApiResponse parsePageResult(String responseText) throws JsonParseException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> map = mapper.readValue(responseText, LinkedCaseInsensitiveMap.class);
		
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setErrorcode(StringUtil.nvl(map.get("success")));
		apiResponse.setErrormsg("");
		
		// 如果接口调用结果正确
		if (StringUtil.nvl(map.get("success")).equals("0")) {
			apiResponse.setSuccess(true);
			if (map.get(PAGE) instanceof Map) {
				HashMap<String, Object> hashMap = (HashMap<String, Object>) map.get(PAGE);
				if (map.get(RESULT) instanceof List) {
					hashMap.put(RESULT, map.get(RESULT));
				}
				filterMap(hashMap);
				apiResponse.setHashMapValue(hashMap);
			}else if (map.get("headImage") instanceof String) {
				String string = map.get("headImage").toString();
				apiResponse.setStringValue(string);
			}else if (map.get("DATA") instanceof List) {
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				if (map.get(DATA) instanceof List) {
					hashMap.put(DATA, map.get(DATA));
				}
				filterMap(hashMap);
				apiResponse.setHashMapValue(hashMap);
			}
		}
		return apiResponse;
	}
//	@SuppressWarnings("unchecked")
//	public static ApiResponse parseMap(String responseText) throws JsonParseException,
//			JsonMappingException, IOException {
//		ObjectMapper mapper = new ObjectMapper();
//		HashMap<String, Object> map = mapper
//				.readValue(responseText, LinkedCaseInsensitiveMap.class);
//		ApiResponse apiResponse = new ApiResponse();
//
//		apiResponse.setErrorcode((String) map.get(ERRORCODE));
//		apiResponse.setErrormsg((String) map.get(ERRORMSG));
//
//		// 如果接口调用结果正确
//		if (map.get("errorcode").equals("0000")) {
//			apiResponse.setSuccess(true);
//
//			// 根据Date的对象类型封装Apiresponse
//			if (map.get(DATA) instanceof String || map.get(DATA) instanceof Integer) {
//				String string = map.get(DATA).toString();
//				apiResponse.setStringValue(string);
//			} else if (map.get(DATA) instanceof List) {
//				List<Map<String,Object>> list = (List<Map<String,Object>>)map.get(DATA);
//				apiResponse.setListValue(list);
//			} else if (map.get(DATA) instanceof Map) {
//				HashMap<String, Object> data = (HashMap<String, Object>) map.get(DATA);
//				filterMap(data);
//				apiResponse.setHashMapValue(data);
//			}
//		}
//		return apiResponse;
//	}
	
	/**
	 * 过滤一下Map，如果value是null，那么转为空字符串，为了Freemarker解析null值方便
	 * @param map
	 */
	private static void filterMap(HashMap<String, Object> map) {
		for (String key : map.keySet()) {
			if (map.get(key) == null) {
				map.put(key, "");
			}
		}
	}
	
}

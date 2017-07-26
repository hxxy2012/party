package com.gw.zhsq.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.service.XishanService;

/**
 * 【锡山经济技术开发区建设城管】首页
 * 
 * @author fuyun 
 * 2016-04-12 
 * 请求地址：http://localhost:8080/Weixin/xishan/init.htm
 */
@Controller
@RequestMapping("/xishan")
public class XishanIndexController extends BaseController {

	@Resource
	private XishanService xishanService;
	/**
	 * 初始化首页
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String init(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		return "xishan/xishan_index";
	}

	/**
	 * 下载
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getFileListMap")
	public String getFileListMap(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		Map<String, Object> fileListMap = null;
		try {
			fileListMap = xishanService.getFileListMap(requestMap);
			List<Map<String, Object>> fileList = (List<Map<String, Object>>) fileListMap.get("result");
			request.setAttribute("fileListMap", fileListMap);
			request.setAttribute("fileList", fileList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "xishan/xishan_download";
	}

	/**
	 * 获取资讯列表
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getNewsListMap")
	public String getNewsListMap(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String classId = requestMap.get("classId");// 政务父ID
		request.setAttribute("classId", classId);
		String title = requestMap.get("title");// 政务标题
		request.setAttribute("title", title);
		
		Map<String, Object> newsMap = null;
		try {
			newsMap = xishanService.getNewsListMap(requestMap);
			List<Map<String, Object>> newsList = (List<Map<String, Object>>) newsMap.get("result");
			if (newsList != null && newsList.size() > 0) {
				for (Map<String, Object> map : newsList) {
					if (map.get("clientContent") != null) {
						String clientContent = (String) map.get("clientContent");
						if (clientContent.length() > 30) {
							clientContent = clientContent.substring(0, 30) + "...";
						}
						map.put("clientContent", clientContent);
					} else {
						map.put("clientContent", "暂无内容");
					}

					if (map.get("paramMap") != null) {
						Map<String, Object> map2 = (Map<String, Object>) map.get("paramMap");
						if (map2.get("imagePaths") != null) {
							String imagePaths = StringUtil.nvl(map2.get("imagePaths"));
							if (imagePaths.indexOf("/base/") > -1) {
								imagePaths = imagePaths.replaceAll("/base/", "/mobile/");
								map2.put("imagePaths", imagePaths);
							}
						}
					}
				}
			}
			requestMap.put("title", title);
			requestMap.put("classId", classId);
			request.setAttribute("newsList", newsList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "xishan/xishan_news_list";
	}

	/**
	 * 获取资讯详情
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getNewsMap")
	public String getNewsMap(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String className = requestMap.get("className");
		try {
			HashMap<String, String> paramMaps = new HashMap<String, String>();
			paramMaps.put("newsId", requestMap.get("newsId"));
			Map<String, Object> newsInfoMap = xishanService.getNewsMap(paramMaps);
			request.setAttribute("newsInfoMap", newsInfoMap);
			request.setAttribute("className", className);
			//ios端预览文件信息
			String iosClick = requestMap.get("iosClick");
			if("1".equals(iosClick)){
				System.out.println("ios 点击=============》"+iosClick);
				return "";
			}
			
			Map<String, Object> paramMap = (Map<String, Object>) newsInfoMap.get("paramMap");// 图片集合
			// 获取资讯幻灯片图片
			List<Map<String, Object>> indexADList = (List<Map<String, Object>>) paramMap.get("imagePaths");
			List<Map<String, Object>> indexADListNew = new ArrayList<Map<String, Object>>();
			Map<String, Object> indexADMapNew = null;
			if (indexADList != null && indexADList.size() > 0) {
				for (int i = 0; i < indexADList.size(); i++) {
					indexADMapNew = new HashMap<String, Object>();
					Map<String, Object> indexMap = indexADList.get(i);
					String imagePaths = StringUtil.nvl(indexMap.get("largetPath"));
					indexADMapNew.put("url", "");
					indexADMapNew.put("title", "");
					if (StringUtils.isNotBlank(imagePaths)) {
						if (imagePaths.indexOf("/base/") > -1) {
							imagePaths = imagePaths.replaceAll("/base/", "/small/");
							indexADMapNew.put("img", imagePaths);
						}
					}
					indexADListNew.add(indexADMapNew);
				}
			}

			if (indexADList != null && indexADList.size() == 1) {
				Map<String, Object> temp = indexADList.get(0);
				String imagePaths = StringUtil.nvl(temp.get("largetPath"));
				if (StringUtils.isNotBlank(imagePaths)) {
					if (imagePaths.indexOf("/base/") > -1) {
						imagePaths = imagePaths.replaceAll("/base/", "/small/");
						request.setAttribute("imageUrl", imagePaths);
					}
				}
			}
			
			JSONArray indexADJsonArray = JSONArray.fromObject(indexADListNew);
			request.setAttribute("indexADJsonArray", indexADJsonArray);
			request.setAttribute("indexADListNew", indexADListNew);
			request.setAttribute("indexADSize", indexADListNew.size());
			request.setAttribute("fileUrl", newsInfoMap.get("fileUrl"));
			
			List<Map<String, Object>> pageNumList = new ArrayList<Map<String, Object>>();
			for(int i = 1; i < 6; i++){
				Map<String, Object> pageNumMap = new HashMap<String, Object>();
				pageNumMap.put("num", i);
				pageNumList.add(pageNumMap);
			}
			request.setAttribute("pageNumList", pageNumList);
			request.setAttribute("pageNum", 6);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "xishan/xishan_news_detail";
	}

	/**
	 * 获取项目列表
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getProjectListMap")
	public String getProjectListMap(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		Map<String, Object> projectListMap = null;
		try {
			String projectType = requestMap.get("projectType");
			if (StringUtils.isBlank(projectType)) {
				projectType = "1";
			}
			String projectName = requestMap.get("projectName");
			projectListMap = xishanService.getProjectListMap(requestMap);
			List<Map<String, Object>> projectList = (List<Map<String, Object>>) projectListMap.get("result");
			
			requestMap.put("projectName", projectName);
			requestMap.put("projectType", projectType);
			request.setAttribute("projectListMap", projectListMap);
			request.setAttribute("projectList", projectList);
			request.setAttribute("projectType", projectType);
			request.setAttribute("projectName", projectName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "xishan/xishan_project_list";
	}

	/**
	 * 获取项目详情
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getProjectMap")
	public String getProjectMap(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		try {
			HashMap<String, String> paramMaps = new HashMap<String, String>();
			paramMaps.put("projectId", requestMap.get("projectId"));
			Map<String, Object> newsInfoMap = xishanService.getProjectMap(paramMaps);
			request.setAttribute("newsInfoMap", newsInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "xishan/xishan_project_detail";
	}

	/**
	 * 初始化联系我们
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/contactUs")
	public String contactUs(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		return "xishan/xishan_contact_us";
	}

	// 异步取得信息列表
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getNewsListAsync")
	@ResponseBody
	public void getNewsListAsync(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();
		try {
			System.out.println("requestMap=====getNewsListAsync=====>>>" + requestMap);
			Map<String, Object> newsMap = xishanService.getNewsListMap(requestMap);
			newsList = (List<Map<String, Object>>) newsMap.get("result");

			if (newsList != null && newsList.size() > 0) {
				for (Map<String, Object> map : newsList) {
					if (map.get("clientContent") != null) {
						String clientContent = (String) map.get("clientContent");
						if (clientContent.length() > 30) {
							clientContent = clientContent.substring(0, 30) + "...";
						}
						map.put("clientContent", clientContent);
					} else {
						map.put("clientContent", "暂无内容");
					}

					if (map.get("paramMap") != null) {
						Map<String, Object> map2 = (Map<String, Object>) map.get("paramMap");
						if (map2.get("imagePaths") != null) {
							String imagePaths = StringUtil.nvl(map2.get("imagePaths"));
							if (imagePaths.indexOf("/base/") > -1) {
								imagePaths = imagePaths.replaceAll("/base/", "/mobile/");
								map2.put("imagePaths", imagePaths);
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out;
		try {
			response.setContentType("text/json;charset=UTF-8");
			Map map = new HashMap();
			map.put("dataList", newsList);
			map.put("size", (newsList == null) ? 0 : newsList.size());
			JSONObject obj = JSONObject.fromObject(map);
			out = response.getWriter();
			out.write(obj.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 异步取得工程项目列表
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getProjectListAsync")
	@ResponseBody
	public void getProjectListAsync(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();
		try {

			Map<String, Object> newsMap = xishanService.getProjectListMap(requestMap);
			newsList = (List<Map<String, Object>>) newsMap.get("result");
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out;
		try {
			response.setContentType("text/json;charset=UTF-8");
			Map map = new HashMap();
			map.put("dataList", newsList);
			map.put("size", (newsList == null) ? 0 : newsList.size());
			JSONObject obj = JSONObject.fromObject(map);
			out = response.getWriter();
			out.write(obj.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 异步取得下载文件列表
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getFileListAsync")
	@ResponseBody
	public void getFileListAsync(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();
		try {

			Map<String, Object> newsMap = xishanService.getFileListMap(requestMap);
			newsList = (List<Map<String, Object>>) newsMap.get("result");
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out;
		try {
			response.setContentType("text/json;charset=UTF-8");
			Map map = new HashMap();
			map.put("dataList", newsList);
			map.put("size", (newsList == null) ? 0 : newsList.size());
			JSONObject obj = JSONObject.fromObject(map);
			out = response.getWriter();
			out.write(obj.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

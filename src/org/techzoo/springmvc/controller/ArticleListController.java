package org.techzoo.springmvc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.utils.TimeUtils;
import org.techzoo.springmvc.service.WebGroupService;

import com.alibaba.fastjson.JSONObject;

@Controller
public class ArticleListController
{
	@Autowired(required = false)
	private WebGroupService webGroupService;

	@RequestMapping(value = "/getMoreNotice", method = RequestMethod.GET)
	public String notice(Model model)
	{
		int totalCount = webGroupService.queryNoticeCount();
		List<Map<String, Object>> listnotice = webGroupService.NoticeList();
		for (Map<String, Object> map : listnotice)
		{
			String title = (String) map.get("title");
			if (title.length() > 19)
			{
				title = title.substring(0, 18) + "..";
			}
			map.put("title", title);
		}
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("listnotice", listnotice);

		return "noticeList";
	}

	@RequestMapping(value = "/getMoreNotice", method = RequestMethod.POST)
	@ResponseBody
	public void getNotice(HttpServletRequest request, HttpServletResponse response, Integer pageNum, Integer pageSize) throws IOException
	{
		response.setContentType("application/json;charset=utf-8");

		if (pageSize == null || pageSize == 0)
		{
			pageSize = 20;
		}
		if (pageNum == null || pageNum == 0)
		{
			pageNum = 0;
		}
		else
		{
			pageNum = pageSize * (pageNum - 1);
		}
		List<Map<String, Object>> list = webGroupService.queryNoticeList(pageNum, pageSize);

		if (list != null && list.size() > 0)
		{
			for (Map<String, Object> item : list)
			{
				item.put("update_time", TimeUtils.formatDate(1000L * (Long) item.get("update_time"), "yyyy-MM-dd HH:mm"));
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("list", list);
		response.getWriter().print(JSONObject.toJSONString(map));
	}

	@RequestMapping(value = "/getMoreDynamic", method = RequestMethod.GET)
	public String dynamic(Model model)
	{
		int totalCount = webGroupService.queryDynamicCount();

		List<Map<String, Object>> listdynamic = webGroupService.DynamicList();
		for (Map<String, Object> map : listdynamic)
		{
			String title = (String) map.get("title");
			if (title.length() > 19)
			{
				title = title.substring(0, 18) + "..";
			}
			map.put("title", title);
		}

		model.addAttribute("listdynamic", listdynamic);
		model.addAttribute("totalCount", totalCount);

		return "dynamicList";
	}

	@RequestMapping(value = "/getMoreDynamic", method = RequestMethod.POST)
	@ResponseBody
	public void getDynamic(HttpServletRequest request, HttpServletResponse response, Integer pageNum, Integer pageSize) throws IOException
	{
		response.setContentType("application/json;charset=utf-8");

		if (pageSize == null || pageSize == 0)
		{
			pageSize = 20;
		}
		if (pageNum == null || pageNum == 0)
		{
			pageNum = 0;
		}
		else
		{
			pageNum = pageSize * (pageNum - 1);
		}
		List<Map<String, Object>> list = webGroupService.queryDynamicList(pageNum, pageSize);

		if (list != null && list.size() > 0)
		{
			for (Map<String, Object> item : list)
			{
				item.put("update_time", TimeUtils.formatDate(1000L * (Long) item.get("update_time"), "yyyy-MM-dd HH:mm"));
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("list", list);
		response.getWriter().print(JSONObject.toJSONString(map));
	}

	@RequestMapping(value = "/getMoreNews", method = RequestMethod.GET)
	public String news(Model model)
	{
		int totalCount = webGroupService.querySchoolNewsCount();

		List<Map<String, Object>> listnew = webGroupService.NewList();
		for (Map<String, Object> map : listnew)
		{
			String title = (String) map.get("title");
			if (title.length() > 19)
			{
				title = title.substring(0, 18) + "..";
			}
			map.put("title", title);
		}

		model.addAttribute("listnew", listnew);
		model.addAttribute("totalCount", totalCount);

		return "newsList";
	}

	@RequestMapping(value = "/getMoreNews", method = RequestMethod.POST)
	@ResponseBody
	public void getNews(HttpServletRequest request, HttpServletResponse response, Integer pageNum, Integer pageSize) throws IOException
	{
		response.setContentType("application/json;charset=utf-8");

		if (pageSize == null || pageSize == 0)
		{
			pageSize = 20;
		}
		if (pageNum == null || pageNum == 0)
		{
			pageNum = 0;
		}
		else
		{
			pageNum = pageSize * (pageNum - 1);
		}
		List<Map<String, Object>> list = webGroupService.querySchoolNewsList(pageNum, pageSize);

		if (list != null && list.size() > 0)
		{
			for (Map<String, Object> item : list)
			{
				item.put("update_time", TimeUtils.formatDate(1000L * (Long) item.get("update_time"), "yyyy-MM-dd HH:mm"));
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("list", list);
		response.getWriter().print(JSONObject.toJSONString(map));
	}
}

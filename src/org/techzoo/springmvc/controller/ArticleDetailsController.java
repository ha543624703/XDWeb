package org.techzoo.springmvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.techzoo.springmvc.service.WebGroupService;

@Controller
public class ArticleDetailsController
{
	@Autowired(required = false)
	private WebGroupService webGroupService;

	@RequestMapping("/getNotice")
	public String notice(String articleId, Model model)
	{
		Map<String, Object> map = webGroupService.getNotice(Long.valueOf(articleId));

		if (map != null && map.size() > 0)
		{
			long update_time = 0;
			if (map.get("update_time") != null)
			{
				update_time = (Long) map.get("update_time");
			}

			Date date = new Date(1000L * update_time);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			map.put("updateTimeStr", sdf.format(date));
		}

		List<Map<String, Object>> listnotice = webGroupService.NoticeList();
		for (Map<String, Object> map2 : listnotice)
		{
			String title = (String) map2.get("title");
			if (title.length() > 19)
			{
				title = title.substring(0, 18) + "..";
			}
			map2.put("title", title);
		}

		model.addAttribute("listnotice", listnotice);
		model.addAttribute("notice", map);

		return "noticeDetail";
	}

	@RequestMapping("/getDynamic")
	public String Dynamic(String articleId, Model model)
	{
		Map<String, Object> map = webGroupService.getDynamic(Long.valueOf(articleId));

		if (map != null && map.size() > 0)
		{
			long update_time = 0;
			if (map.get("update_time") != null)
			{
				update_time = (Long) map.get("update_time");
			}

			Date date = new Date(1000L * update_time);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			map.put("updateTimeStr", sdf.format(date));
		}

		List<Map<String, Object>> listdynamic = webGroupService.DynamicList();
		for (Map<String, Object> map2 : listdynamic)
		{
			String title = (String) map2.get("title");
			if (title.length() > 19)
			{
				title = title.substring(0, 18) + "..";
			}
			map2.put("title", title);
		}

		model.addAttribute("listdynamic", listdynamic);
		model.addAttribute("dynamic", map);

		return "dynamicDetail";
	}

	@RequestMapping("/getNew")
	public String New(String articleId, Model model)
	{

		Map<String, Object> map = webGroupService.getSchoolNews(Long.valueOf(articleId));

		if (map != null && map.size() > 0)
		{
			long update_time = 0;
			if (map.get("update_time") != null)
			{
				update_time = (Long) map.get("update_time");
			}

			Date date = new Date(1000L * update_time);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			map.put("updateTimeStr", sdf.format(date));
		}

		List<Map<String, Object>> listnew = webGroupService.NewList();
		for (Map<String, Object> map2 : listnew)
		{
			String title = (String) map2.get("title");
			if (title.length() > 19)
			{
				title = title.substring(0, 18) + "..";
			}
			map2.put("title", title);
		}

		model.addAttribute("listnew", listnew);
		model.addAttribute("neww", map);

		return "newDetail";
	}
}

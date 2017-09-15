package org.techzoo.springmvc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.techzoo.springmvc.form.RepairPresentation;
import org.techzoo.springmvc.service.RepairService;

/**
 * @description:报修
 * @projectName:jxhlwx
 * @className:RepairController.java
 * @author:duzl
 * @createTime:2017年6月8日 上午9:36:12
 * @version 1.0
 */
@Controller
public class RepairPresentationController
{
	@Autowired(required = false)
	public RepairService repairService;

	@RequestMapping(value = "/goRepair")
	public String goRepair(HttpServletRequest request)
	{
		return "repair";
	}

	@RequestMapping(value = "/setRepair")
	public String setRepair(HttpServletRequest request, RepairPresentation repairPresentation)
	{
		String userId = getUserId(request);
		if (repairPresentation != null)
		{
			repairPresentation.setUserid(userId);
			repairPresentation.setStatus(0);
		}
		System.out.println(repairPresentation.getPlace());
		int t = repairService.insertRepairPresentation(repairPresentation);

		if (t <= 0)
		{
			return "repairFail";
		}

		return "repairSuccess";
	}

	@RequestMapping(value = "/repairList")
	public String queryRepairs(HttpServletRequest request, Model model)
	{
		List<Map<String, Object>> wait = repairService.queryRepairList(0);
		List<Map<String, Object>> audited = repairService.queryRepairList(1);
		List<Map<String, Object>> failed = repairService.queryRepairList(2);

		if (wait != null && wait.size() > 0)
		{
			for (Map<String, Object> repair : wait)
			{
				String repairDetail = "" + repair.get("place") + repair.get("buildingdescribe") + repair.get("hydropower") + repair.get("furniture")
						+ repair.get("other");
				if (repairDetail.length() > 30)
				{
					repairDetail = repairDetail.substring(0, 28) + "...";
				}
				repair.put("repairDetail", repairDetail);
			}
		}
		if (audited != null && audited.size() > 0)
		{
			for (Map<String, Object> repair : audited)
			{
				String repairDetail = "" + repair.get("place") + repair.get("buildingdescribe") + repair.get("hydropower") + repair.get("furniture")
						+ repair.get("other");
				if (repairDetail.length() > 30)
				{
					repairDetail = repairDetail.substring(0, 28) + "...";
				}
				repair.put("repairDetail", repairDetail);
			}
		}
		if (failed != null && failed.size() > 0)
		{
			for (Map<String, Object> repair : failed)
			{
				String repairDetail = "" + repair.get("place") + repair.get("buildingdescribe") + repair.get("hydropower") + repair.get("furniture")
						+ repair.get("other");
				if (repairDetail.length() > 30)
				{
					repairDetail = repairDetail.substring(0, 28) + "...";
				}
				repair.put("repairDetail", repairDetail);
			}
		}
		model.addAttribute("wait", wait);
		model.addAttribute("audited", audited);
		model.addAttribute("failed", failed);

		return "repairList";
	}

	@RequestMapping(value = "/repairDetail")
	public String getRepair(HttpServletRequest request, Integer id, Model model)
	{
		String userId = getUserId(request);
		if (id != null)
		{
			Map<String, Object> map = repairService.getRepair(id);
			model.addAttribute("map", map);
			model.addAttribute("userId", userId);
		}
		return "repairDetail";
	}

	@RequestMapping(value = "/repairAudited")
	public String setRepairAudited(Integer id)
	{
		repairService.setRepairAudited(id, 1);

		return "redirect:/repairList";
	}

	@RequestMapping(value = "/repairFailed")
	public String setRepairFailed(Integer id)
	{
		repairService.setRepairAudited(id, 2);

		return "redirect:/repairList";
	}

	private String getUserId(HttpServletRequest request)
	{
		HttpSession session = ((HttpServletRequest) request).getSession();
		if (session != null)
		{
			return session.getAttribute("username").toString();
		}
		return null;
	}
}
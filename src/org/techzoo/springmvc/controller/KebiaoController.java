package org.techzoo.springmvc.controller;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.techzoo.springmvc.jdbcDAO.EducaJDBC;

@Controller
public class KebiaoController
{
	@RequestMapping({ "/goTeacherKB" })
	public String goTeacherKB(HttpServletRequest request, Model model)
	{
		HttpSession session = ((HttpServletRequest) request).getSession();
		if (session != null)
		{
			String username = session.getAttribute("username").toString();
			model.addAttribute("userId", username);
		}

		return "teacherKB";
	}

	@RequestMapping(params = { "teacherKB" })
	@ResponseBody
	public void teacherKB(HttpServletResponse response, Model model, String xnxqh, String userId) throws IOException
	{
		response.setContentType("application/json;charset=utf-8");
		String sql = "SELECT CASE WHEN SUBSTRING(a.KCSJBH,2,2)='AB' then '1-2节' WHEN SUBSTRING(a.KCSJBH,2,2)='CD' THEN '3-4节' WHEN SUBSTRING(a.KCSJBH,2,2)='EF' THEN '5-6节' WHEN SUBSTRING(a.KCSJBH,2,2)='GH' then '7-8节' end as js,Max(CASE SUBSTRING(a.KCSJBH,1,1) WHEN '1' THEN KCMC+'['+JXBMC+KCSJ+KKZC+']('+JIAOSMC+')' ELSE '' END) AS 'z1',Max(CASE SUBSTRING(a.KCSJBH,1,1) WHEN '2' THEN KCMC+'['+JXBMC+KCSJ+KKZC++']('+JIAOSMC+')' ELSE '' END) AS 'z2',Max(CASE SUBSTRING(a.KCSJBH,1,1) WHEN '3' THEN KCMC+'['+JXBMC+KCSJ+KKZC++']('+JIAOSMC+')' ELSE '' END) AS 'z3',Max(CASE SUBSTRING(a.KCSJBH,1,1) WHEN '4' THEN KCMC+'['+JXBMC+KCSJ+KKZC++']('+JIAOSMC+')' ELSE '' END) AS 'z4',Max(CASE SUBSTRING(a.KCSJBH,1,1) WHEN '5' THEN KCMC+'['+JXBMC+KCSJ+KKZC++']('+JIAOSMC+')' ELSE '' END) AS 'z5' from V_JX_PK_PKKKTZD_KB_JIAOS a where XNXQH = '"
				+ xnxqh + "' AND a.JSBH = '" + userId + "' GROUP BY SUBSTRING(a.KCSJBH,2,2)";

		EducaJDBC dBjdbcCont = new EducaJDBC();
		dBjdbcCont.setSql(sql);

		Result rst = null;
		try
		{
			rst = dBjdbcCont.executeQuery();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (rst != null)
		{
			List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < rst.getRowCount(); i++)
			{
				Map<String, Object> hmap = new HashMap<String, Object>(6);

				@SuppressWarnings("rawtypes")
				SortedMap[] map = rst.getRows();

				String js = map[i].get("js") + "";
				String z1 = map[i].get("z1") + "";
				String z2 = map[i].get("z2") + "";
				String z3 = map[i].get("z3") + "";
				String z4 = map[i].get("z4") + "";
				String z5 = map[i].get("z5") + "";

				hmap.put("js", js);
				hmap.put("z1", z1);
				hmap.put("z2", z2);
				hmap.put("z3", z3);
				hmap.put("z4", z4);
				hmap.put("z5", z5);
				data.add(hmap);
			}
			returnMap.put("status", "success");
			returnMap.put("data", data);
		}
		else
		{
			returnMap.put("status", "error");
			returnMap.put("data", "未查询到课表信息");
		}
		response.getWriter().print(JSONObject.toJSONString(returnMap));
	}

	@RequestMapping({ "/goStudentKB" })
	public String goStudentKB(HttpServletRequest request, Model model)
	{
		HttpSession session = ((HttpServletRequest) request).getSession();
		if (session != null)
		{
			String username = session.getAttribute("username").toString();
			model.addAttribute("userId", username);
		}

		return "StudentKB";
	}

	@RequestMapping(params = { "studentKB" })
	@ResponseBody
	public void studentKB(HttpServletResponse response, Model model, String xnxqh, String userId) throws IOException
	{
		response.setContentType("application/json;charset=utf-8");

		String zzname = "JX_KKTZD_XS" + xnxqh.substring(0, 4) + xnxqh.substring(10);

		String sql = "SELECT CASE WHEN SUBSTRING(ccc.KCSJBH,2,len(ccc.KCSJBH)-1)='AB' THEN '1-2节' "
				+ "WHEN SUBSTRING(ccc.KCSJBH,2,len(ccc.KCSJBH)-1)='CD' THEN '3-4节' "
				+ "WHEN SUBSTRING(ccc.KCSJBH,2,len(ccc.KCSJBH)-1)='EF' then '5-6节' "
				+ "WHEN SUBSTRING(ccc.KCSJBH,2,len(ccc.KCSJBH)-1)='EFG' then '5-7节' "
				+ "WHEN SUBSTRING(ccc.KCSJBH,2,len(ccc.KCSJBH)-1)='EFGH' then '5-8节' "
				+ "WHEN SUBSTRING(ccc.KCSJBH,2,len(ccc.KCSJBH)-1)='GH' then '7-8节' "
				+ "WHEN SUBSTRING(ccc.KCSJBH,2,len(ccc.KCSJBH)-1)='IJ' then '9-10节' end as js,"
				+ "Max(CASE SUBSTRING(ccc.KCSJBH,1,1) WHEN '1' THEN KCMC+'['+JXBMC+KCSJ+KKZC+']('+JIAOSMC+')' ELSE '' END) AS 'z1',"
				+ "Max(CASE SUBSTRING(ccc.KCSJBH,1,1) WHEN '2' THEN KCMC+'['+JXBMC+KCSJ+KKZC++']('+JIAOSMC+')' ELSE '' END) AS 'z2',"
				+ "Max(CASE SUBSTRING(ccc.KCSJBH,1,1) WHEN '3' THEN KCMC+'['+JXBMC+KCSJ+KKZC++']('+JIAOSMC+')' ELSE '' END) AS 'z3',"
				+ "Max(CASE SUBSTRING(ccc.KCSJBH,1,1) WHEN '4' THEN KCMC+'['+JXBMC+KCSJ+KKZC++']('+JIAOSMC+')' ELSE '' END) AS 'z4',"
				+ "Max(CASE SUBSTRING(ccc.KCSJBH,1,1) WHEN '5' THEN KCMC+'['+JXBMC+KCSJ+KKZC++']('+JIAOSMC+')' ELSE '' END) AS 'z5' "
				+ "FROM V_JX_PK_PKKKTZD_KB_JIAOS ccc INNER JOIN (SELECT aaa.* FROM " + zzname + " aaa INNER JOIN XS_XSJBXX ddd ON ddd.XSDM=aaa.XSDM "
				+ "WHERE ddd.XH= '" + userId + "' AND aaa.xnxqh= '" + xnxqh + "' AND aaa.sfxz='1') bbb ON bbb.tzdid=ccc.tzdid WHERE ccc.xnxqh= '"
				+ xnxqh + "' GROUP BY SUBSTRING(ccc.KCSJBH,2,len(ccc.KCSJBH)-1)";

		EducaJDBC dBjdbcCont = new EducaJDBC();
		dBjdbcCont.setSql(sql);

		Result rst = null;
		try
		{
			rst = dBjdbcCont.executeQuery();
		}
		catch (Exception e)
		{
			rst = null;
			e.printStackTrace();
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if ((rst != null) && (rst.getRowCount() > 0))
		{
			List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < rst.getRowCount(); i++)
			{
				Map<String, Object> hmap = new HashMap<String, Object>(6);

				@SuppressWarnings("rawtypes")
				SortedMap[] map = rst.getRows();
				String js = map[i].get("js") + "";
				String z1 = map[i].get("z1") + "";
				String z2 = map[i].get("z2") + "";
				String z3 = map[i].get("z3") + "";
				String z4 = map[i].get("z4") + "";
				String z5 = map[i].get("z5") + "";

				hmap.put("js", js);
				hmap.put("z1", z1);
				hmap.put("z2", z2);
				hmap.put("z3", z3);
				hmap.put("z4", z4);
				hmap.put("z5", z5);
				data.add(hmap);
			}
			returnMap.put("status", "success");
			returnMap.put("data", data);
		}
		else
		{
			returnMap.put("status", "error");
			returnMap.put("data", "未查询到课表信息");
		}
		response.getWriter().print(JSONObject.toJSONString(returnMap));
	}

	@RequestMapping({ "/goStudentCJ" })
	public String goStudentCJ(HttpServletRequest request, Model model)
	{
		HttpSession session = ((HttpServletRequest) request).getSession();
		if (session != null)
		{
			String username = session.getAttribute("username").toString();
			model.addAttribute("userId", username);
		}

		return "cj";
	}

	@RequestMapping(params = { "studentCJ" })
	@ResponseBody
	public void studentCJ(HttpServletResponse response, Model model, String xnxq, String userId) throws IOException
	{
		response.setContentType("application/json;charset=utf-8");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String sql = "select XNXQH,DWMC,ZYMC,BJMC,XH,XM,JSXM,KCJC,KCXZMC,XF,ZCJ from V_CJ_XSCJZB_LIST b where XH='" + userId + "' AND XNXQH='" + xnxq
				+ "' order by XNXQH";

		EducaJDBC dBjdbcCont = new EducaJDBC();

		dBjdbcCont.setSql(sql);

		Result rst = null;
		try
		{
			rst = dBjdbcCont.executeQuery();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		if ((rst != null) && (rst.getRowCount() > 0))
		{
			List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < rst.getRowCount(); i++)
			{
				Map<String, Object> hmap = new HashMap<String, Object>(11);

				@SuppressWarnings("rawtypes")
				SortedMap[] map = rst.getRows();

				hmap.put("XNXQH", map[i].get("XNXQH"));
				hmap.put("DWMC", map[i].get("DWMC"));
				hmap.put("ZYMC", map[i].get("ZYMC"));
				hmap.put("BJMC", map[i].get("BJMC"));
				hmap.put("XH", map[i].get("XH"));
				hmap.put("XM", map[i].get("XM"));
				hmap.put("JSXM", map[i].get("JSXM"));
				hmap.put("KCJC", map[i].get("KCJC"));
				hmap.put("KCXZMC", map[i].get("KCXZMC"));
				hmap.put("XF", map[i].get("XF"));
				hmap.put("ZCJ", map[i].get("ZCJ"));

				data.add(hmap);
			}
			returnMap.put("status", "success");
			returnMap.put("data", data);
		}
		else
		{
			returnMap.put("status", "error");
			returnMap.put("data", "未查询到成绩信息");
		}
		response.getWriter().print(JSONObject.toJSONString(returnMap));
	}
}

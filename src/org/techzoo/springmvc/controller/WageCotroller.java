package org.techzoo.springmvc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springside.modules.utils.ExcelHelper;
import org.techzoo.springmvc.form.Importlog;
import org.techzoo.springmvc.form.Jbgz;
import org.techzoo.springmvc.form.Jtgz;
import org.techzoo.springmvc.form.Jxgz;
import org.techzoo.springmvc.form.Qtgz;
import org.techzoo.springmvc.service.ImportlogService;
import org.techzoo.springmvc.service.JbgzService;
import org.techzoo.springmvc.service.JtgzService;
import org.techzoo.springmvc.service.JxgzService;
import org.techzoo.springmvc.service.QtgzService;

import java.util.UUID;

@Controller
public class WageCotroller
{
	@Autowired(required = false)
	private ImportlogService importlogService;
	@Autowired(required = false)
	private JbgzService jbgzService;
	@Autowired(required = false)
	private JxgzService jxgzService;
	@Autowired(required = false)
	private JtgzService jtgzService;
	@Autowired(required = false)
	private QtgzService qtgzService;

	@RequestMapping("/goWages")
	public String goWages(HttpServletRequest request, Model model)
	{
		return "wages";
	}

	@RequestMapping("/getWages")
	@ResponseBody
	public Map<String, Object> getWages(HttpServletRequest request, Integer year)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = ((HttpServletRequest) request).getSession();
		if (session != null)
		{
			String username = session.getAttribute("username").toString();

			List<Jbgz> list = jbgzService.listJbgzsByYear(username, year);
			map.put("list", list);
			return map;
		}

		return map;
	}

	@RequestMapping("/ImportWage")
	public String ImportWage(HttpServletRequest request)
	{
		HttpSession session = ((HttpServletRequest) request).getSession();
		if (session != null)
		{
			String username = session.getAttribute("username").toString();

			if (!"1998250002".equals(username) && !"admin".equals(username))
			{
				return "error2";
			}
		}

		Calendar cal = Calendar.getInstance();
		ModelAndView mav = new ModelAndView("QueryWage");
		mav.addObject("month", cal.getTime().getMonth());
		mav.addObject("year", cal.getTime().getYear());
		return "ImportWage";
	}

	@RequestMapping("/ImportLog")
	public String ImportLog(HttpSession session)
	{
		if (session != null)
		{
			String username = session.getAttribute("username").toString();

			if (!"1998250002".equals(username) && !"admin".equals(username))
			{
				return "error2";
			}
		}

		return "ImportLog";
	}

	@RequestMapping("/inputUserId")
	@ResponseBody
	public void inputUserId(String userId, String gzId)
	{
		jbgzService.inputWagesUserid(userId, gzId);
	}

	@RequestMapping("/QueryWage")
	public ModelAndView QueryWage(@ModelAttribute("year") String year, @ModelAttribute("month") String month, Map<String, Object> map,
			HttpServletRequest request)
	{
		HttpSession session = ((HttpServletRequest) request).getSession();
		Calendar cal = Calendar.getInstance();
		ModelAndView mav = new ModelAndView("QueryWage");
		mav.addObject("month", cal.getTime().getMonth());
		// String username = session.getAttribute("username").toString();
		String username = "1998250002";
		mav.addObject("username", username);
		return mav;
	}

	@RequestMapping(value = "/getJbgzList.json")
	@ResponseBody
	public Map<String, Object> getJbgzList(HttpSession session, @RequestParam int year, @RequestParam int month)
	{

		String username = session.getAttribute("username").toString();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jbgzlist", jbgzService.listJbgzs(username, year, month));
		map.put("jxgzlist", jxgzService.listJxgzs(username, year, month));
		map.put("jtgzlist", jtgzService.listJtgzs(username, year, month));
		map.put("qtgzlist", qtgzService.listQtgzs(username, year, month));

		return map;
	}

	@RequestMapping("/deleteImport/{logId}")
	public String deleteImport(@PathVariable("logId") String logId, @RequestParam("tableName") String tableName, @RequestParam("year") String year,
			@RequestParam("month") String month)
	{
		importlogService.removeImport(tableName, logId);
		return "redirect:/ImportLog?year=" + year + "&month=" + month;
	}

	@RequestMapping("/deleteImportLog/{logId}")
	public String deleteImportLog(@PathVariable("logId") String logId, @RequestParam("year") String year, @RequestParam("month") String month)
	{
		importlogService.removeImportlog(logId);
		return "redirect:/ImportLog?year=" + year + "&month=" + month;
	}

	@RequestMapping(value = "/ImportWage/Add", method = RequestMethod.GET)
	public String goImportWageAdd()
	{
		return "redirect:/ImportWage";
	}

	@RequestMapping(value = "/ImportWage/Add", method = RequestMethod.POST)
	public ModelAndView ImportWageAdd(@RequestParam("year") int year, @RequestParam("month") int month,
			@RequestParam("clientFile") MultipartFile clientFile, @RequestParam("clientFileJX") MultipartFile clientFileJX,
			@RequestParam("clientFileJT") MultipartFile clientFileJT, @RequestParam("clientFileQT") MultipartFile clientFileQT,
			HttpServletRequest request)
	{
		if (!clientFile.isEmpty())
		{
			// 在这里就可以对file进行处理了
			System.out.println("=================" + clientFile.getSize());
		}
		try
		{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			String myappPath = multipartRequest.getRealPath("/") + "upload/";
			// does not work, oh my god!!
			Date now = new Date();
			Calendar cal = Calendar.getInstance();
			if (clientFile.getSize() > 0)
			{
				long size = clientFile.getSize();
				byte[] data = new byte[(int) size];
				InputStream input = clientFile.getInputStream();
				input.read(data);

				File outFile = new File(myappPath + File.separator + cal.getTime().getTime() + clientFile.getOriginalFilename());
				if (!outFile.exists())
				{
					outFile.createNewFile();
					System.out.println("full path = " + outFile.getAbsolutePath());
				}
				else
				{
					System.out.println("full path = " + outFile.getAbsolutePath());
				}
				FileOutputStream outStream = new FileOutputStream(outFile);

				outStream.write(data);
				outStream.close();
				input.close();
				// 基本工资
				List<String> list = ExcelHelper.exportListFromExcel(outFile, 0);
				if (list.size() > 0)
				{
					if (list.get(1).indexOf("序号|工资编号|所属部门|姓名|年|月|岗位工资|薪级工资") == -1)
					{
						ModelAndView view = new ModelAndView("ImportWage");
						view.addObject("year", year);
						view.addObject("month", month);
						view.addObject("msg", "导入失败工资表格不正确！");
						return view;
					}
				}
				Importlog log = new Importlog();
				UUID uuid = UUID.randomUUID();
				log.setId(uuid.toString());
				log.setTableName("jxjs_jbgz");
				now = cal.getTime();
				log.setdRSJ(now);
				log.setYear(year);
				log.setMonth(month);
				log.setsM("导入" + year + "年" + month + "月工资信息！");
				importlogService.addImportlog(log);
				int i = 0;
				for (String row : list)
				{
					System.out.println(i + "---" + row);
					String[] array = row.split("\\|");

					if (i > 1 && array.length > 15)
					{
						Jbgz jbgz = new Jbgz();
						uuid = UUID.randomUUID();
						jbgz.setId(uuid.toString());
						jbgz.setImportId(log.getId());

						jbgz.setXH(array[1]);
						jbgz.setBH(double2String(array[2]));
						jbgz.setGH(double2String(array[2]));
						jbgz.setBM(array[3]);
						jbgz.setXM(array[4]);
						jbgz.setS1(double2String(array[5]));
						jbgz.setS2(double2String(array[6]));
						jbgz.setS3(array[7]);
						jbgz.setS4(array[8]);
						jbgz.setS5(array[9]);
						jbgz.setS6(array[10]);
						jbgz.setS7(array[11]);
						jbgz.setS8(array[12]);
						jbgz.setS9(array[13]);
						jbgz.setS10(array[14]);
						jbgz.setS11(array[15]);
						jbgz.setS12(array[16]);
						jbgz.setS13(array[17]);
						jbgz.setS14(array[18]);
						jbgz.setS15(array[19]);
						jbgz.setS16(array[20]);
						jbgz.setS17(array[21]);
						jbgz.setS18(array[22]);
						jbgz.setS19(array[23]);
						jbgz.setS20(array[24]);
						jbgz.setS21(array[25]);
						jbgz.setS22(array[26]);
						jbgz.setS23(array[27]);
						jbgz.setS24(array[28]);
						jbgz.setS25(array[29]);
						jbgz.setS26(array[30]);
						jbgz.setS27(array[31]);
						jbgz.setS28(array[32]);
						jbgz.setS29(array[33]);
						jbgz.setS30(array[34]);
						jbgz.setS31(array[35]);
						jbgz.setS32(array[36]);
						jbgz.setS33(array[37]);
						jbgz.setS34(array[38]);
						jbgz.setS35(array[39]);
						jbgz.setS36(array[40]);
						jbgz.setS37(array[41]);
						jbgz.setS38(array[42]);
						jbgz.setS39(array[43]);
						jbgz.setS40(array[44]);
						jbgz.setS41(array[45]);
						jbgz.setS42(array[46]);
						jbgz.setS43(array[47]);
						jbgz.setS44(array[48]);
						jbgz.setS45(array[49]);
						jbgz.setS46(array[50]);
						jbgz.setS47(array[51]);
						jbgz.setS48(array[52]);
						jbgz.setS49(array[53]);
						jbgz.setS50(array[54]);
						jbgz.setS51(array[55]);
						jbgz.setS52(array[56]);
						jbgz.setS53(array[57]);
						jbgz.setS54(array[58]);
						jbgz.setS55(array[59]);
						jbgz.setS56(array[60]);
						jbgz.setS57(array[61]);
						jbgz.setS58(array[62]);
						jbgz.setS59(array[63]);
						jbgz.setS60(array[64]);
						jbgz.setS61(double2String(array[65]));

						jbgzService.addJbgz(jbgz);
					}

					i++;
				}
			}

			// 绩效工资
			if (clientFileJX.getSize() > 0)
			{
				long size1 = clientFileJX.getSize();
				byte[] data1 = new byte[(int) size1];
				InputStream input1 = clientFileJX.getInputStream();
				input1.read(data1);

				// create file, if no app context path, will throws access
				// denied.
				// seems like you could not create any file at tomcat/bin
				// directory!!!
				File outFile1 = new File(myappPath + File.separator + cal.getTime().getTime() + clientFileJX.getOriginalFilename());
				if (!outFile1.exists())
				{
					outFile1.createNewFile();
					System.out.println("full path = " + outFile1.getAbsolutePath());
				}
				else
				{
					System.out.println("full path = " + outFile1.getAbsolutePath());
				}
				FileOutputStream outStream1 = new FileOutputStream(outFile1);

				outStream1.write(data1);
				outStream1.close();
				input1.close();

				List<String> list = ExcelHelper.exportListFromExcel(outFile1, 0);
				int i = 0;
				if (list.size() > 0)
				{
					if (list.get(1).indexOf("序号|工号|所属部门|姓名") == -1)
					{
						ModelAndView view = new ModelAndView("ImportWage");
						view.addObject("year", year);
						view.addObject("month", month);
						view.addObject("msg", "导入失败基本绩效工资表格不正确！");
						return view;
					}
				}
				Importlog log = new Importlog();
				UUID uuid = UUID.randomUUID();
				log.setId(uuid.toString());
				log.setTableName("jxjs_jxgz");
				now = cal.getTime();
				log.setdRSJ(now);
				log.setYear(year);
				log.setMonth(month);
				log.setsM("导入" + year + "年" + month + "月基本绩效工资信息！");
				importlogService.addImportlog(log);
				for (String row : list)
				{
					System.out.println(i + "---" + row);
					String[] array = row.split("\\|");
					if (i > 1 && array.length > 5)
					{
						Jxgz jxgz = new Jxgz();
						uuid = UUID.randomUUID();
						jxgz.setId(uuid.toString());
						jxgz.setImportId(log.getId());

						jxgz.setXH(array[1]);
						jxgz.setGH(array[2]);
						jxgz.setBM(array[3]);
						jxgz.setXM(array[4]);
						jxgz.setS1(array[5]);
						jxgz.setS2(array[6]);
						jxgz.setS3(array[7]);
						jxgz.setS4(array[8]);

						jxgzService.addJxgz(jxgz);
					}
					i++;
				}

			}

			// 当月岗位带班津贴、课贴
			if (clientFileJT.getSize() > 0)
			{
				long size2 = clientFileJT.getSize();
				byte[] data2 = new byte[(int) size2];
				InputStream input2 = clientFileJT.getInputStream();
				input2.read(data2);

				// create file, if no app context path, will throws access
				// denied.
				// seems like you could not create any file at tomcat/bin
				// directory!!!
				File outFile2 = new File(myappPath + File.separator + cal.getTime().getTime() + clientFileJT.getOriginalFilename());
				if (!outFile2.exists())
				{
					outFile2.createNewFile();
					System.out.println("full path = " + outFile2.getAbsolutePath());
				}
				else
				{
					System.out.println("full path = " + outFile2.getAbsolutePath());
				}
				FileOutputStream outStream2 = new FileOutputStream(outFile2);

				outStream2.write(data2);
				outStream2.close();
				input2.close();

				List<String> list = ExcelHelper.exportListFromExcel(outFile2, 0);
				int i = 0;
				if (list.size() > 0)
				{
					if (list.get(0).indexOf("序号|工号|部门|姓名") == -1)
					{
						ModelAndView view = new ModelAndView("ImportWage");
						view.addObject("year", year);
						view.addObject("month", month);
						view.addObject("msg", "导入失败岗位带班津贴课贴工资表格不正确！");
						return view;
					}
				}
				Importlog log = new Importlog();
				UUID uuid = UUID.randomUUID();
				log.setId(uuid.toString());
				log.setTableName("jxjs_jtgz");
				now = cal.getTime();
				log.setdRSJ(now);
				log.setYear(year);
				log.setMonth(month);
				log.setsM("导入" + year + "年" + month + "月岗位带班津贴课贴工资信息！");
				importlogService.addImportlog(log);
				for (String row : list)
				{
					System.out.println(i + "---" + row);
					String[] array = row.split("\\|");
					if (i > 0 && array.length > 18)
					{
						Jtgz jtgz = new Jtgz();
						uuid = UUID.randomUUID();
						jtgz.setId(uuid.toString());
						jtgz.setImportId(log.getId());

						jtgz.setXH(array[1]);
						jtgz.setGH(array[2]);
						jtgz.setBM(array[3]);
						jtgz.setXM(array[4]);
						jtgz.setS1(array[5]);
						jtgz.setS2(array[6]);
						jtgz.setS3(array[7]);
						jtgz.setS4(array[8]);
						jtgz.setS5(array[9]);
						jtgz.setS6(array[10]);
						jtgz.setS7(array[11]);
						jtgz.setS8(array[12]);
						jtgz.setS9(array[13]);
						jtgz.setS10(array[14]);
						jtgz.setS11(array[15]);
						jtgz.setS12(array[16]);
						jtgz.setS13(array[17]);
						jtgz.setS14(array[18]);
						jtgz.setS15(array[19]);
						jtgz.setS16(array[20]);
						jtgz.setS17(array[21]);
						jtgz.setS18(array[22]);
						jtgz.setS19(array[23]);
						jtgz.setS20(array[24]);
						jtgzService.addJtgz(jtgz);
					}
					i++;
				}

			}

			// 其它工资
			if (clientFileQT.getSize() > 0)
			{
				long size3 = clientFileQT.getSize();
				byte[] data3 = new byte[(int) size3];
				InputStream input3 = clientFileQT.getInputStream();
				input3.read(data3);

				// create file, if no app context path, will throws access
				// denied.
				// seems like you could not create any file at tomcat/bin
				// directory!!!
				File outFile3 = new File(myappPath + File.separator + cal.getTime().getTime() + clientFileQT.getOriginalFilename());
				if (!outFile3.exists())
				{
					outFile3.createNewFile();
					System.out.println("full path = " + outFile3.getAbsolutePath());
				}
				else
				{
					System.out.println("full path = " + outFile3.getAbsolutePath());
				}
				FileOutputStream outStream3 = new FileOutputStream(outFile3);

				outStream3.write(data3);
				outStream3.close();
				input3.close();

				List<String> list = ExcelHelper.exportListFromExcel(outFile3, 0);
				int i = 0;
				if (list.size() > 0)
				{
					if (list.get(0).indexOf("序号|工号|部门|姓名|工资类别|应发金额|个人所得税") == -1)
					{
						ModelAndView view = new ModelAndView("ImportWage");
						view.addObject("year", year);
						view.addObject("month", month);
						view.addObject("msg", "导入其它工资表格不正确！");
						return view;
					}
				}
				Importlog log = new Importlog();
				UUID uuid = UUID.randomUUID();
				log.setId(uuid.toString());
				log.setTableName("jxjs_qtgz");
				now = cal.getTime();
				log.setdRSJ(now);
				log.setYear(year);
				log.setMonth(month);
				log.setsM("导入" + year + "年" + month + "月其它工资信息！");
				importlogService.addImportlog(log);
				for (String row : list)
				{
					System.out.println(i + "---" + row);
					String[] array = row.split("\\|");
					if (i > 0 && array.length > 9)
					{
						Qtgz qtgz = new Qtgz();
						uuid = UUID.randomUUID();
						qtgz.setId(uuid.toString());
						qtgz.setImportId(log.getId());

						qtgz.setXH(array[1]);
						qtgz.setGH(array[2]);
						qtgz.setBM(array[3]);
						qtgz.setXM(array[4]);
						qtgz.setS1(array[5]);
						qtgz.setS2(array[6]);
						qtgz.setS3(array[7]);
						qtgz.setS4(array[8]);
						qtgz.setS5(array[9]);
						qtgz.setS6(array[10]);

						qtgzService.addQtgz(qtgz);
					}
					i++;
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
			ModelAndView view = new ModelAndView("ImportWage");
			view.addObject("year", year);
			view.addObject("month", month);
			view.addObject("msg", "导入失败！");
			return view;
		}
		ModelAndView view = new ModelAndView("ImportWage");
		view.addObject("year", year);
		view.addObject("month", month);
		view.addObject("msg", "导入成功！");
		return view;
	}

	private String double2String(String s)
	{
		if (s != null && s.endsWith(".0"))
		{
			s = s.substring(0, s.length() - 2);
		}
		return s;
	}
}

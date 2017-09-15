package org.techzoo.springmvc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springside.modules.utils.ExcelHelper;
import org.springside.modules.utils.JsonUtil;
import org.techzoo.springmvc.form.ImportDetail;
import org.techzoo.springmvc.form.ImportDetailCode;
import org.techzoo.springmvc.form.ImportMain;
import org.techzoo.springmvc.service.ImportDetailCodeService;
import org.techzoo.springmvc.service.ImportDetailService;
import org.techzoo.springmvc.service.ImportMainService;

@Controller
public class WageListCotroller
{
	@Autowired(required = false)
	private ImportMainService importMainService;
	@Autowired(required = false)
	private ImportDetailService importDetailService;
	@Autowired(required = false)
	private ImportDetailCodeService importDetailCodeService;

	@RequestMapping("/WageList")
	public String WageList(Map<String, Object> map, HttpServletRequest request)
	{
		Calendar cal = Calendar.getInstance();
		map.put("month", cal.get(Calendar.MONTH) + 1);
		map.put("year", cal.get(Calendar.YEAR));
//			map.put("selyear", cal.get(Calendar.YEAR));
//			map.put("selYearTerm", "1");
//			map.put("selNDMonth", cal.get(Calendar.MONTH) + 1);
//			map.put("selWeekStart", "1");
//			map.put("selWeekEnd", "1");
		HttpSession session = ((HttpServletRequest) request).getSession();
		if (session.getAttribute("username") == null)
		{
			return "error";
		}
		else
		{
			return "WageList";
		}
	}

	@RequestMapping("/MyWage")
	public String MyWage(Map<String, Object> map)
	{
		Calendar cal = Calendar.getInstance();
		map.put("month", cal.get(Calendar.MONTH) + 1);
		map.put("year", cal.get(Calendar.YEAR));
		return "MyWage";
	}

	@RequestMapping("SelMyWage")
	public String SelMyWage(@RequestParam("year") int year,// 年
			@RequestParam("month") int month, @RequestParam("selwageType") int wageType, Map<String, Object> map, HttpServletRequest request)
	{
		HttpSession session = ((HttpServletRequest) request).getSession();
		String username = session.getAttribute("username").toString();
		if (username.equals(""))
		{
			username = "!@#$%%";
		}
		map.put("username", username);
		List list = importMainService.SelectWageList(username, year + "", month + "", wageType + "");
		String json = JsonUtil.listTojsonByTD(list);
		map.put("list", json);// 默认显示数据
		List<ImportMain> lists = importMainService.listImportMain(year + "", month + "", wageType + "");
		String mainID = "";
		if (lists != null && lists.size() > 0)
		{

			mainID = lists.get(0).getId();

		}

		boolean iskl = importMainService.isKL(mainID);// 判断是否为跨列数据
		int klCount = 0;
		String json2 = "1";
		if (iskl)
		{
			klCount = 1;
			List listTD = importMainService.SelectWageLitKL(mainID);// 垮了数据
			json2 = JsonUtil.listTojsonByTD(listTD);
		}
		map.put("iskl", klCount);
		map.put("json2", json2);

		Calendar cal = Calendar.getInstance();
		map.put("month", month);
		map.put("year", year);
		map.put("selwageType", wageType);
		return "MyWage";
	}

	@RequestMapping("/WageSelect/{logId}")
	public String WageSelect(@PathVariable("logId") String logId, Map<String, Object> map)
	{

		List list = importMainService.SelectWageList(logId);
		String json = JsonUtil.listTojsonByTD(list);
		map.put("list", json);// 默认显示数据
		boolean iskl = importMainService.isKL(logId);// 判断是否为跨列数据
		int klCount = 0;
		String json2 = "1";
		if (iskl)
		{
			klCount = 1;
			List listTD = importMainService.SelectWageLitKL(logId);// 垮了数据
			json2 = JsonUtil.listTojsonByTD(listTD);
		}
		map.put("iskl", klCount);
		map.put("json2", json2);
		return "WageSelect";
	}

	@RequestMapping("/WageListLog")
	public String WageListLog(@ModelAttribute("year") String year, @ModelAttribute("month") String month,
			@ModelAttribute("selwageType") String selwageType, Map<String, Object> map, HttpServletRequest request)
	{
		if (!year.isEmpty() && !month.isEmpty() && !selwageType.isEmpty())
		{
			map.put("logListWage", importMainService.listImportMain(year, month, selwageType));
			map.put("year", year);
			map.put("month", month);
			map.put("selwageType", selwageType);
		}
		else
		{
			Calendar cal = Calendar.getInstance();
			map.put("year", cal.get(Calendar.YEAR));// 当前年
			map.put("month", (cal.get(Calendar.MONTH) + 1));// 当前月
			map.put("selwageType", "1");
		}
		HttpSession session = ((HttpServletRequest) request).getSession();
		if (session.getAttribute("username") == null)
		{
			return "error";
		}
		else
		{
			return "WageListLog";
		}
	}

	@RequestMapping("/deleteWageList/{logId}")
	public String deleteWageList(@PathVariable("logId") String logId, @RequestParam("year") String year, @RequestParam("month") String month,
			@RequestParam("selwageType") String selwageType)
	{
		importMainService.removeImportMain(logId);
		return "redirect:/WageListLog?year=" + year + "&month=" + month + "&selwageType=" + selwageType;
	}

	@RequestMapping("/enableWageList/{logId}")
	public String enableWageList(@PathVariable("logId") String logId, @RequestParam("year") String year, @RequestParam("month") String month,
			@RequestParam("selwageType") String selwageType)
	{
		importMainService.ImportMianEnable(logId);
		return "redirect:/WageListLog?year=" + year + "&month=" + month + "&selwageType=" + selwageType;
	}

	@RequestMapping(value = "/WageList/Add", method = RequestMethod.POST)
	public ModelAndView WageListAdd(@RequestParam("year") int year,// 年
			@RequestParam("month") int month,// 月
			@RequestParam("filedagz") MultipartFile filedagz,// 档案工资
			@RequestParam("filebzrgz") MultipartFile filebzrgz,// 班主任工资
			@RequestParam("filexzrygz") MultipartFile filexzrygz,// 行政人员岗位工资
			@RequestParam("fileyjxgz") MultipartFile fileyjxgz,// 行政人员岗位工资
//			@RequestParam("fileqtgz") MultipartFile fileqtgz,
//			@RequestParam("selyear") int selyear,
//			@RequestParam("filendjxgz") MultipartFile filendjxgz,
//			@RequestParam("selYearTerm") String selYearTerm,
//			@RequestParam("selNDMonth") int selMonth,
//			@RequestParam("selWeekStart") int selWeekStart,
//			@RequestParam("selWeekEnd") int selWeekEnd,
//			@RequestParam("filexqjxgz") MultipartFile filexqjxgz,
			HttpServletRequest request)
	{
		try
		{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			String myappPath = multipartRequest.getRealPath("/") + "upload/";
			// does not work, oh my god!!
			Date now = new Date();
			Calendar cal = Calendar.getInstance();
			// 档案工资资导入*********************************************************TYPE=1
			if (filedagz.getSize() > 0)
			{
				long size = filedagz.getSize();
				byte[] data = new byte[(int) size];
				InputStream input = filedagz.getInputStream();
				input.read(data);

				File outFile = new File(myappPath + File.separator + cal.getTime().getTime() + filedagz.getOriginalFilename());
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
				// 档案工资资
				List<String> list = ExcelHelper.exportListFromExcel(outFile, 0);
				if (list.size() > 0)
				{
					if (list.get(1).indexOf("序号|工号|部门|姓名") == -1)
					{
						ModelAndView view = new ModelAndView("WageList");
						view.addObject("year", year);
						view.addObject("month", month);
						view.addObject("msg", "导入失败导入内容不存在工号！");
						return view;
					}
				}
				ImportMain importMainMDL = new ImportMain();
				UUID uuid = UUID.randomUUID();
				importMainMDL.setId(uuid.toString());
				importMainMDL.setCreateDate(cal.getTime());
				importMainMDL.setCreateMan("admin");// 暂时用admin
				importMainMDL.setImportCount(list.size() - 2);
				importMainMDL.setIsDelete(1);// 未删除
				importMainMDL.setIsEnable(0);// 是否显示给用户(不发布)
				importMainMDL.setWageType(1);// 档案工资资
				importMainMDL.setWYear(year);// 年
				importMainMDL.setWMonth(month); // 月
				importMainMDL.setRemark("导入" + year + "年" + month + "月岗位带班津贴课贴绩效工资!");
				importMainService.addImportMain(importMainMDL);
				int i = 0;
				if (list.get(2).substring(0, 5).equals("| | |")) // 当跨表头时
				{
					for (int t = 0; t < list.size(); t++)
					{
						i = i + 1;
						System.out.println(i + "---" + list.get(t));
						String[] array = list.get(t).split("\\|");
						if (t > 2)
						{
							ImportDetail importDetail = new ImportDetail();
							uuid = UUID.randomUUID();
							importDetail.setId(uuid.toString());
							importDetail.setSnumber(array[1].toString());// 序号
							importDetail.setUserCode(array[2]);// 工号
							importDetail.setDepanrment(array[3]);// 部门
							importDetail.setUserName(array[4]);// 姓名
							importDetail.setCreateDate(cal.getTime());// 创建日期
							importDetail.setCreateMan("admin");// 创建人
							importDetail.setMainDetailID(importMainMDL.getId());// 数据父级ID

							String thisTitle = "";
							for (int w = 3; w < list.get(1).split("\\|").length - 1; w++)
							{
								ImportDetailCode importDetailCode = new ImportDetailCode();
								String titleName = list.get(1).split("\\|")[w];
								if (titleName.equals(" "))
								{
									titleName = list.get(2).split("\\|")[w];
									if (thisTitle.equals(" ") || thisTitle.equals(""))
									{
										thisTitle = list.get(1).split("\\|")[w - 1];
										importDetailCode.setPid(thisTitle);
									}
									else
									{
										if (!list.get(1).split("\\|")[w - 1].equals(" ") && !list.get(1).split("\\|")[w - 1].equals(thisTitle))
										{
											thisTitle = list.get(1).split("\\|")[w - 1];
											importDetailCode.setPid(thisTitle);
										}
										else
										{
											importDetailCode.setPid(thisTitle);
										}
									}

								}
								else
								{
									if (list.get(1).split("\\|")[w + 1].equals(" ") && (list.get(1).split("\\|").length - 2) > w)
									{
										titleName = list.get(2).split("\\|")[w];
										importDetailCode.setPid(list.get(1).split("\\|")[w]);
									}
								}
								if (!(titleName.trim()).isEmpty())
								{
									String uuidCode = UUID.randomUUID().toString();
									importDetailCode.setId(uuidCode);
									importDetailCode.setImportDetailID(importDetail.getId());
									importDetailCode.setTitleName(titleName);
									importDetailCode.setTitleValue(array[w]);
									importDetailCode.setwOrderby(w);
									importDetailCodeService.addImportDetailCode(importDetailCode);
								}
							}
							importDetailService.addImportDetail(importDetail);

						}
					}
				}
				else
				{// 不带多表头处理
					for (int t = 0; t < list.size(); t++)
					{
						i = i + 1;
						System.out.println(i + "---" + list.get(t));
						String[] array = list.get(t).split("\\|");
						if (t >= 2)
						{
							ImportDetail importDetail = new ImportDetail();
							uuid = UUID.randomUUID();
							importDetail.setId(uuid.toString());
							importDetail.setSnumber(array[1].toString());// 序号
							importDetail.setUserCode(array[2]);// 工号
							importDetail.setDepanrment(array[3]);// 部门
							importDetail.setUserName(array[4]);// 姓名
							importDetail.setCreateDate(cal.getTime());// 创建日期
							importDetail.setCreateMan("admin");// 创建人
							importDetail.setMainDetailID(importMainMDL.getId());
							ImportDetailCode importDetailCode = new ImportDetailCode();
							for (int w = 3; w < list.get(1).split("\\|").length - 1; w++)
							{
								String titleName = list.get(1).split("\\|")[w];
								String uuidCode = UUID.randomUUID().toString();
								importDetailCode.setId(uuidCode);
								importDetailCode.setImportDetailID(importDetail.getId());
								importDetailCode.setTitleName(titleName);
								importDetailCode.setTitleValue(array[w]);
								importDetailCode.setwOrderby(w);
								if (!(titleName.trim()).isEmpty())
								{
									importDetailCodeService.addImportDetailCode(importDetailCode);
								}
							}
							importDetailService.addImportDetail(importDetail);

						}
					}
				}
			}
			// end 档案工资资******************************************************

			// 班主任工资**********************************************************TYPE=2
			if (filebzrgz.getSize() > 0)
			{
				long size = filebzrgz.getSize();
				byte[] data = new byte[(int) size];
				InputStream input = filebzrgz.getInputStream();
				input.read(data);

				File outFile = new File(myappPath + File.separator + cal.getTime().getTime() + filebzrgz.getOriginalFilename());
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
				// 班主任工资
				List<String> list = ExcelHelper.exportListFromExcel(outFile, 0);
				if (list.size() > 0)
				{
					if (list.get(1).indexOf("序号|工号|部门|姓名") == -1)
					{
						ModelAndView view = new ModelAndView("WageList");
						view.addObject("year", year);
						view.addObject("month", month);
						view.addObject("msg", "导入失败导入内容不存在工号！");
						return view;
					}
				}
				ImportMain importMainMDL = new ImportMain();
				UUID uuid = UUID.randomUUID();
				importMainMDL.setId(uuid.toString());
				importMainMDL.setCreateDate(cal.getTime());
				importMainMDL.setCreateMan("admin");// 暂时用admin
				importMainMDL.setImportCount(list.size() - 2);
				importMainMDL.setIsDelete(1);// 未删除
				importMainMDL.setIsEnable(0);// 是否显示给用户(不发布)
				importMainMDL.setWageType(2);// 班主任工资
				importMainMDL.setWYear(year);// 年
				importMainMDL.setWMonth(month); // 月
				importMainMDL.setRemark("导入" + year + "年" + month + "月基本工资!");
				importMainService.addImportMain(importMainMDL);
				int i = 0;
				if (list.get(2).substring(0, 5).equals("| | |")) // 当跨表头时
				{
					for (int t = 0; t < list.size(); t++)
					{
						i = i + 1;
						System.out.println(i + "---" + list.get(t));
						String[] array = list.get(t).split("\\|");
						if (t > 2)
						{
							ImportDetail importDetail = new ImportDetail();
							uuid = UUID.randomUUID();
							importDetail.setId(uuid.toString());
							importDetail.setSnumber(array[1]);// 序号
							importDetail.setUserCode(array[2]);// 工号
							importDetail.setDepanrment(array[3]);// 部门
							importDetail.setUserName(array[4]);// 姓名
							importDetail.setCreateDate(cal.getTime());// 创建日期
							importDetail.setCreateMan("admin");// 创建人
							importDetail.setMainDetailID(importMainMDL.getId());// 数据父级ID

							String thisTitle = "";
							for (int w = 3; w < list.get(1).split("\\|").length - 1; w++)
							{
								ImportDetailCode importDetailCode = new ImportDetailCode();
								String titleName = list.get(1).split("\\|")[w];
								if (titleName.equals(" "))
								{
									titleName = list.get(2).split("\\|")[w];
									if (thisTitle.equals(" ") || thisTitle.equals(""))
									{
										thisTitle = list.get(1).split("\\|")[w - 1];
										importDetailCode.setPid(thisTitle);
									}
									else
									{
										if (!list.get(1).split("\\|")[w - 1].equals(" ") && !list.get(1).split("\\|")[w - 1].equals(thisTitle))
										{
											thisTitle = list.get(1).split("\\|")[w - 1];
											importDetailCode.setPid(thisTitle);
										}
										else
										{
											importDetailCode.setPid(thisTitle);
										}
									}

								}
								else
								{
									if (list.get(1).split("\\|")[w + 1].equals(" ") && (list.get(1).split("\\|").length - 2) > w)
									{
										titleName = list.get(2).split("\\|")[w];
										importDetailCode.setPid(list.get(1).split("\\|")[w]);
									}
								}
								String uuidCode = UUID.randomUUID().toString();
								importDetailCode.setId(uuidCode);
								importDetailCode.setImportDetailID(importDetail.getId());
								importDetailCode.setTitleName(titleName);
								importDetailCode.setTitleValue(array[w]);
								importDetailCode.setwOrderby(w);
								importDetailCodeService.addImportDetailCode(importDetailCode);
							}
							importDetailService.addImportDetail(importDetail);

						}
					}
				}
				else
				{// 不带多表头处理
					for (int t = 0; t < list.size(); t++)
					{
						i = i + 1;
						System.out.println(i + "---" + list.get(t));
						String[] array = list.get(t).split("\\|");
						if (t >= 2)
						{
							ImportDetail importDetail = new ImportDetail();
							uuid = UUID.randomUUID();
							importDetail.setId(uuid.toString());
							importDetail.setSnumber(array[1]);// 序号
							importDetail.setUserCode(array[2]);// 工号
							importDetail.setDepanrment(array[3]);// 部门
							importDetail.setUserName(array[4]);// 姓名
							importDetail.setCreateDate(cal.getTime());// 创建日期
							importDetail.setCreateMan("admin");// 创建人
							importDetail.setMainDetailID(importMainMDL.getId());
							ImportDetailCode importDetailCode = new ImportDetailCode();
							for (int w = 3; w < list.get(1).split("\\|").length - 1; w++)
							{
								String titleName = list.get(1).split("\\|")[w];
								String uuidCode = UUID.randomUUID().toString();
								importDetailCode.setId(uuidCode);
								importDetailCode.setImportDetailID(importDetail.getId());
								importDetailCode.setTitleName(titleName);
								importDetailCode.setTitleValue(array[w]);
								importDetailCode.setwOrderby(w);
								importDetailCodeService.addImportDetailCode(importDetailCode);
							}
							importDetailService.addImportDetail(importDetail);

						}
					}
				}
			}
			// end
			// 班主任工资*******************************************************************
			// 行政人员岗位工资*****************************************************************Type=3
			if (filexzrygz.getSize() > 0)
			{
				long size = filexzrygz.getSize();
				byte[] data = new byte[(int) size];
				InputStream input = filexzrygz.getInputStream();
				input.read(data);

				File outFile = new File(myappPath + File.separator + cal.getTime().getTime() + filexzrygz.getOriginalFilename());
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
				// 行政人员岗位工资
				List<String> list = ExcelHelper.exportListFromExcel(outFile, 0);
				if (list.size() > 0)
				{
					if (list.get(1).indexOf("序号|工号|部门|姓名") == -1)
					{
						ModelAndView view = new ModelAndView("WageList");
						view.addObject("year", year);
						view.addObject("month", month);
						view.addObject("msg", "导入失败导入内容不存在工号！");
						return view;
					}
				}
				ImportMain importMainMDL = new ImportMain();
				UUID uuid = UUID.randomUUID();
				importMainMDL.setId(uuid.toString());
				importMainMDL.setCreateDate(cal.getTime());
				importMainMDL.setCreateMan("admin");// 暂时用admin
				importMainMDL.setImportCount(list.size() - 2);
				importMainMDL.setIsDelete(1);// 未删除
				importMainMDL.setIsEnable(0);// 是否显示给用户(不发布)
				importMainMDL.setWageType(3);// 行政人员岗位工资
				importMainMDL.setWYear(year);// 年
				importMainMDL.setWMonth(month); // 月
				importMainMDL.setRemark("导入" + year + "年" + month + "月基本绩效工资!");
				importMainService.addImportMain(importMainMDL);
				int i = 0;
				if (list.get(2).substring(0, 5).equals("| | |")) // 当跨表头时
				{
					for (int t = 0; t < list.size(); t++)
					{
						i = i + 1;
						System.out.println(i + "---" + list.get(t));
						String[] array = list.get(t).split("\\|");
						if (t > 2)
						{
							ImportDetail importDetail = new ImportDetail();
							uuid = UUID.randomUUID();
							importDetail.setId(uuid.toString());
							importDetail.setSnumber(array[1]);// 序号
							importDetail.setUserCode(array[2]);// 工号
							importDetail.setDepanrment(array[3]);// 部门
							importDetail.setUserName(array[4]);// 姓名
							importDetail.setCreateDate(cal.getTime());// 创建日期
							importDetail.setCreateMan("admin");// 创建人
							importDetail.setMainDetailID(importMainMDL.getId());// 数据父级ID

							String thisTitle = "";
							for (int w = 3; w < list.get(1).split("\\|").length - 1; w++)
							{
								ImportDetailCode importDetailCode = new ImportDetailCode();
								String titleName = list.get(1).split("\\|")[w];
								if (titleName.equals(" "))
								{
									titleName = list.get(2).split("\\|")[w];
									if (thisTitle.equals(" ") || thisTitle.equals(""))
									{
										thisTitle = list.get(1).split("\\|")[w - 1];
										importDetailCode.setPid(thisTitle);
									}
									else
									{
										if (!list.get(1).split("\\|")[w - 1].equals(" ") && !list.get(1).split("\\|")[w - 1].equals(thisTitle))
										{
											thisTitle = list.get(1).split("\\|")[w - 1];
											importDetailCode.setPid(thisTitle);
										}
										else
										{
											importDetailCode.setPid(thisTitle);
										}
									}

								}
								else
								{
									if (list.get(1).split("\\|")[w + 1].equals(" ") && (list.get(1).split("\\|").length - 2) > w)
									{
										titleName = list.get(2).split("\\|")[w];
										importDetailCode.setPid(list.get(1).split("\\|")[w]);
									}
								}
								String uuidCode = UUID.randomUUID().toString();
								importDetailCode.setId(uuidCode);
								importDetailCode.setImportDetailID(importDetail.getId());
								importDetailCode.setTitleName(titleName);
								importDetailCode.setTitleValue(array[w]);
								importDetailCode.setwOrderby(w);
								importDetailCodeService.addImportDetailCode(importDetailCode);
							}
							importDetailService.addImportDetail(importDetail);

						}
					}
				}
				else
				{// 不带多表头处理
					for (int t = 0; t < list.size(); t++)
					{
						i = i + 1;
						System.out.println(i + "---" + list.get(t));
						String[] array = list.get(t).split("\\|");
						if (t >= 2)
						{
							ImportDetail importDetail = new ImportDetail();
							uuid = UUID.randomUUID();
							importDetail.setId(uuid.toString());
							importDetail.setSnumber(array[1]);// 序号
							importDetail.setUserCode(array[2]);// 工号
							importDetail.setDepanrment(array[3]);// 部门
							importDetail.setUserName(array[4]);// 姓名
							importDetail.setCreateDate(cal.getTime());// 创建日期
							importDetail.setCreateMan("admin");// 创建人
							importDetail.setMainDetailID(importMainMDL.getId());
							ImportDetailCode importDetailCode = new ImportDetailCode();
							for (int w = 3; w < list.get(1).split("\\|").length - 1; w++)
							{
								String titleName = list.get(1).split("\\|")[w];
								String uuidCode = UUID.randomUUID().toString();
								importDetailCode.setId(uuidCode);
								importDetailCode.setImportDetailID(importDetail.getId());
								importDetailCode.setTitleName(titleName);
								importDetailCode.setTitleValue(array[w]);
								importDetailCode.setwOrderby(w);
								importDetailCodeService.addImportDetailCode(importDetailCode);
							}
							importDetailService.addImportDetail(importDetail);

						}
					}
				}
			}
			// end
			// 行政岗位工资******************************************************************
			// 月绩效位工资*****************************************************************Type=4
			if (fileyjxgz.getSize() > 0)
			{
				long size = fileyjxgz.getSize();
				byte[] data = new byte[(int) size];
				InputStream input = fileyjxgz.getInputStream();
				input.read(data);

				File outFile = new File(myappPath + File.separator + cal.getTime().getTime() + fileyjxgz.getOriginalFilename());
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
				// 月绩效位工资
				List<String> list = ExcelHelper.exportListFromExcel(outFile, 0);
				if (list.size() > 0)
				{
					if (list.get(1).indexOf("序号|工号|部门|姓名") == -1)
					{
						ModelAndView view = new ModelAndView("WageList");
						view.addObject("year", year);
						view.addObject("month", month);
						view.addObject("msg", "导入失败导入内容不存在工号！");
						return view;
					}
				}
				ImportMain importMainMDL = new ImportMain();
				UUID uuid = UUID.randomUUID();
				importMainMDL.setId(uuid.toString());
				importMainMDL.setCreateDate(cal.getTime());
				importMainMDL.setCreateMan("admin");// 暂时用admin
				importMainMDL.setImportCount(list.size() - 2);
				importMainMDL.setIsDelete(1);// 未删除
				importMainMDL.setIsEnable(0);// 是否显示给用户(不发布)
				importMainMDL.setWageType(4);// 月绩效位工资
				importMainMDL.setWYear(year);// 年
				importMainMDL.setWMonth(month); // 月
				importMainMDL.setRemark("导入" + year + "年" + month + "月其它工资!");
				importMainService.addImportMain(importMainMDL);
				int i = 0;
				if (list.get(2).substring(0, 5).equals("| | |")) // 当跨表头时
				{
					for (int t = 0; t < list.size(); t++)
					{
						i = i + 1;
						System.out.println(i + "---" + list.get(t));
						String[] array = list.get(t).split("\\|");
						if (t > 2)
						{
							ImportDetail importDetail = new ImportDetail();
							uuid = UUID.randomUUID();
							importDetail.setId(uuid.toString());
							importDetail.setSnumber(array[1]);// 序号
							importDetail.setUserCode(array[2]);// 工号
							importDetail.setDepanrment(array[3]);// 部门
							importDetail.setUserName(array[4]);// 姓名
							importDetail.setCreateDate(cal.getTime());// 创建日期
							importDetail.setCreateMan("admin");// 创建人
							importDetail.setMainDetailID(importMainMDL.getId());// 数据父级ID

							String thisTitle = "";
							for (int w = 3; w < list.get(1).split("\\|").length - 1; w++)
							{
								ImportDetailCode importDetailCode = new ImportDetailCode();
								String titleName = list.get(1).split("\\|")[w];
								if (titleName.equals(" "))
								{
									titleName = list.get(2).split("\\|")[w];
									if (thisTitle.equals(" ") || thisTitle.equals(""))
									{
										thisTitle = list.get(1).split("\\|")[w - 1];
										importDetailCode.setPid(thisTitle);
									}
									else
									{
										if (!list.get(1).split("\\|")[w - 1].equals(" ") && !list.get(1).split("\\|")[w - 1].equals(thisTitle))
										{
											thisTitle = list.get(1).split("\\|")[w - 1];
											importDetailCode.setPid(thisTitle);
										}
										else
										{
											importDetailCode.setPid(thisTitle);
										}
									}

								}
								else
								{
									if (list.get(1).split("\\|")[w + 1].equals(" ") && (list.get(1).split("\\|").length - 2) > w)
									{
										titleName = list.get(2).split("\\|")[w];
										importDetailCode.setPid(list.get(1).split("\\|")[w]);
									}
								}
								String uuidCode = UUID.randomUUID().toString();
								importDetailCode.setId(uuidCode);
								importDetailCode.setImportDetailID(importDetail.getId());
								importDetailCode.setTitleName(titleName);
								importDetailCode.setTitleValue(array[w]);
								importDetailCode.setwOrderby(w);
								importDetailCodeService.addImportDetailCode(importDetailCode);
							}
							importDetailService.addImportDetail(importDetail);

						}
					}
				}
				else
				{// 不带多表头处理
					for (int t = 0; t < list.size(); t++)
					{
						i = i + 1;
						System.out.println(i + "---" + list.get(t));
						String[] array = list.get(t).split("\\|");
						if (t >= 2)
						{
							ImportDetail importDetail = new ImportDetail();
							uuid = UUID.randomUUID();
							importDetail.setId(uuid.toString());
							importDetail.setSnumber(array[1]);// 序号
							importDetail.setUserCode(array[2]);// 工号
							importDetail.setDepanrment(array[3]);// 部门
							importDetail.setUserName(array[4]);// 姓名
							importDetail.setCreateDate(cal.getTime());// 创建日期
							importDetail.setCreateMan("admin");// 创建人
							importDetail.setMainDetailID(importMainMDL.getId());
							ImportDetailCode importDetailCode = new ImportDetailCode();
							for (int w = 3; w < list.get(1).split("\\|").length - 1; w++)
							{
								String titleName = list.get(1).split("\\|")[w];
								String uuidCode = UUID.randomUUID().toString();
								importDetailCode.setId(uuidCode);
								importDetailCode.setImportDetailID(importDetail.getId());
								importDetailCode.setTitleName(titleName);
								importDetailCode.setTitleValue(array[w]);
								importDetailCode.setwOrderby(w);
								importDetailCodeService.addImportDetailCode(importDetailCode);
							}
							importDetailService.addImportDetail(importDetail);

						}
					}
				}
			}
			// end
			// 月绩效位工资*****************************************************************
			// 其它工资*****************************************************************Type=5
//			if (fileqtgz.getSize() > 0) {
//				long size = fileqtgz.getSize();
//				byte[] data = new byte[(int) size];
//				InputStream input = fileqtgz.getInputStream();
//				input.read(data);
//
//				File outFile = new File(myappPath + File.separator
//						+ cal.getTime().getTime()
//						+ fileqtgz.getOriginalFilename());
//				if (!outFile.exists()) {
//					outFile.createNewFile();
//					System.out.println("full path = "
//							+ outFile.getAbsolutePath());
//				} else {
//					System.out.println("full path = "
//							+ outFile.getAbsolutePath());
//				}
//				FileOutputStream outStream = new FileOutputStream(outFile);
//				outStream.write(data);
//				outStream.close();
//				input.close();
//				// 其它工资
//				List<String> list = ExcelHelper.exportListFromExcel(outFile, 0);
//				if (list.size() > 0) {
//					if (list.get(1).indexOf("序号|工号|部门|姓名") == -1) {
//						ModelAndView view = new ModelAndView("WageList");
//						view.addObject("year", year);
//						view.addObject("month", month);
//						view.addObject("msg", "导入失败导入内容不存在工号！");
//						return view;
//					}
//				}
//				ImportMain importMainMDL = new ImportMain();
//				UUID uuid = UUID.randomUUID();
//				importMainMDL.setId(uuid.toString());
//				importMainMDL.setCreateDate(cal.getTime());
//				importMainMDL.setCreateMan("admin");// 暂时用admin
//				importMainMDL.setImportCount(list.size() - 2);
//				importMainMDL.setIsDelete(1);// 未删除
//				importMainMDL.setIsEnable(0);// 是否显示给用户(不发布)
//				importMainMDL.setWageType(5);// 其它工资
//				importMainMDL.setWYear(year);// 年
//				importMainMDL.setWMonth(month); // 月
//				importMainMDL.setRemark("导入" + year + "年" + month + "月其它工资!");
//				importMainService.addImportMain(importMainMDL);
//				int i = 0;
//				if (list.get(2).substring(0, 5).equals("| | |")) // 当跨表头时
//				{
//					for (int t = 0; t < list.size(); t++) {
//						i = i + 1;
//						System.out.println(i + "---" + list.get(t));
//						String[] array = list.get(t).split("\\|");
//						if (t > 2) {
//							ImportDetail importDetail = new ImportDetail();
//							uuid = UUID.randomUUID();
//							importDetail.setId(uuid.toString());
//							importDetail.setSnumber(array[1]);// 序号
//							importDetail.setUserCode(array[2]);// 工号
//							importDetail.setDepanrment(array[3]);// 部门
//							importDetail.setUserName(array[4]);// 姓名
//							importDetail.setCreateDate(cal.getTime());// 创建日期
//							importDetail.setCreateMan("admin");// 创建人
//							importDetail.setMainDetailID(importMainMDL.getId());// 数据父级ID
//
//							String thisTitle = "";
//							for (int w = 3; w < list.get(1).split("\\|").length - 1; w++) {
//								ImportDetailCode importDetailCode = new ImportDetailCode();
//								String titleName = list.get(1).split("\\|")[w];
//								if (titleName.equals(" ")) {
//									titleName = list.get(2).split("\\|")[w];
//									if (thisTitle.equals(" ")
//											|| thisTitle.equals("")) {
//										thisTitle = list.get(1).split("\\|")[w - 1];
//										importDetailCode.setPid(thisTitle);
//									} else {
//										if (!list.get(1).split("\\|")[w - 1]
//												.equals(" ")
//												&& !list.get(1).split("\\|")[w - 1]
//														.equals(thisTitle)) {
//											thisTitle = list.get(1)
//													.split("\\|")[w - 1];
//											importDetailCode.setPid(thisTitle);
//										} else {
//											importDetailCode.setPid(thisTitle);
//										}
//									}
//
//								} else {
//									if (list.get(1).split("\\|")[w + 1]
//											.equals(" ")
//											&& (list.get(1).split("\\|").length - 2) > w) {
//										titleName = list.get(2).split("\\|")[w];
//										importDetailCode.setPid(list.get(1)
//												.split("\\|")[w]);
//									}
//								}
//								String uuidCode = UUID.randomUUID().toString();
//								importDetailCode.setId(uuidCode);
//								importDetailCode.setImportDetailID(importDetail
//										.getId());
//								importDetailCode.setTitleName(titleName);
//								importDetailCode.setTitleValue(array[w]);
//								importDetailCode.setwOrderby(w);
//								importDetailCodeService
//										.addImportDetailCode(importDetailCode);
//							}
//							importDetailService.addImportDetail(importDetail);
//
//						}
//					}
//				} else {// 不带多表头处理
//					for (int t = 0; t < list.size(); t++) {
//						i = i + 1;
//						System.out.println(i + "---" + list.get(t));
//						String[] array = list.get(t).split("\\|");
//						if (t >= 2) {
//							ImportDetail importDetail = new ImportDetail();
//							uuid = UUID.randomUUID();
//							importDetail.setId(uuid.toString());
//							importDetail.setSnumber(array[1]);// 序号
//							importDetail.setUserCode(array[2]);// 工号
//							importDetail.setDepanrment(array[3]);// 部门
//							importDetail.setUserName(array[4]);// 姓名
//							importDetail.setCreateDate(cal.getTime());// 创建日期
//							importDetail.setCreateMan("admin");// 创建人
//							importDetail.setMainDetailID(importMainMDL.getId());
//							ImportDetailCode importDetailCode = new ImportDetailCode();
//							for (int w = 3; w < list.get(1).split("\\|").length - 1; w++) {
//								String titleName = list.get(1).split("\\|")[w];
//								String uuidCode = UUID.randomUUID().toString();
//								importDetailCode.setId(uuidCode);
//								importDetailCode.setImportDetailID(importDetail
//										.getId());
//								importDetailCode.setTitleName(titleName);
//								importDetailCode.setTitleValue(array[w]);
//								importDetailCode.setwOrderby(w);
//								importDetailCodeService
//										.addImportDetailCode(importDetailCode);
//							}
//							importDetailService.addImportDetail(importDetail);
//
//						}
//					}
//				}
//			}
//			// end
//			// 其它工资*****************************************************************
//			// 年度绩效工资*****************************************************************Type=6
//			if (filendjxgz.getSize() > 0) {
//				long size = filendjxgz.getSize();
//				byte[] data = new byte[(int) size];
//				InputStream input = filendjxgz.getInputStream();
//				input.read(data);
//
//				File outFile = new File(myappPath + File.separator
//						+ cal.getTime().getTime()
//						+ filendjxgz.getOriginalFilename());
//				if (!outFile.exists()) {
//					outFile.createNewFile();
//					System.out.println("full path = "
//							+ outFile.getAbsolutePath());
//				} else {
//					System.out.println("full path = "
//							+ outFile.getAbsolutePath());
//				}
//				FileOutputStream outStream = new FileOutputStream(outFile);
//				outStream.write(data);
//				outStream.close();
//				input.close();
//				// 年度绩效工资
//				List<String> list = ExcelHelper.exportListFromExcel(outFile, 0);
//				if (list.size() > 0) {
//					if (list.get(1).indexOf("序号|工号|部门|姓名") == -1) {
//						ModelAndView view = new ModelAndView("WageList");
//						view.addObject("year", year);
//						view.addObject("month", month);
//						view.addObject("msg", "导入失败导入内容不存在工号！");
//						return view;
//					}
//				}
//				ImportMain importMainMDL = new ImportMain();
//				UUID uuid = UUID.randomUUID();
//				importMainMDL.setId(uuid.toString());
//				importMainMDL.setCreateDate(cal.getTime());
//				importMainMDL.setCreateMan("admin");// 暂时用admin
//				importMainMDL.setImportCount(list.size() - 2);
//				importMainMDL.setIsDelete(1);// 未删除
//				importMainMDL.setIsEnable(0);// 是否显示给用户(不发布)
//				importMainMDL.setWageType(6);// 年度绩效工资傲
//				importMainMDL.setWYear(selyear);// 年
//				importMainMDL.setRemark("导入" + year + "年年度绩效工资");
//				importMainService.addImportMain(importMainMDL);
//				int i = 0;
//				if (list.get(2).substring(0, 5).equals("| | |")) // 当跨表头时
//				{
//					for (int t = 0; t < list.size(); t++) {
//						i = i + 1;
//						System.out.println(i + "---" + list.get(t));
//						String[] array = list.get(t).split("\\|");
//						if (t > 2) {
//							ImportDetail importDetail = new ImportDetail();
//							uuid = UUID.randomUUID();
//							importDetail.setId(uuid.toString());
//							importDetail.setSnumber(array[1]);// 序号
//							importDetail.setUserCode(array[2]);// 工号
//							importDetail.setDepanrment(array[3]);// 部门
//							importDetail.setUserName(array[4]);// 姓名
//							importDetail.setCreateDate(cal.getTime());// 创建日期
//							importDetail.setCreateMan("admin");// 创建人
//							importDetail.setMainDetailID(importMainMDL.getId());// 数据父级ID
//
//							String thisTitle = "";
//							for (int w = 3; w < list.get(1).split("\\|").length - 1; w++) {
//								ImportDetailCode importDetailCode = new ImportDetailCode();
//								String titleName = list.get(1).split("\\|")[w];
//								if (titleName.equals(" ")) {
//									titleName = list.get(2).split("\\|")[w];
//									if (thisTitle.equals(" ")
//											|| thisTitle.equals("")) {
//										thisTitle = list.get(1).split("\\|")[w - 1];
//										importDetailCode.setPid(thisTitle);
//									} else {
//										if (!list.get(1).split("\\|")[w - 1]
//												.equals(" ")
//												&& !list.get(1).split("\\|")[w - 1]
//														.equals(thisTitle)) {
//											thisTitle = list.get(1)
//													.split("\\|")[w - 1];
//											importDetailCode.setPid(thisTitle);
//										} else {
//											importDetailCode.setPid(thisTitle);
//										}
//									}
//
//								} else {
//									if (list.get(1).split("\\|")[w + 1]
//											.equals(" ")
//											&& (list.get(1).split("\\|").length - 2) > w) {
//										titleName = list.get(2).split("\\|")[w];
//										importDetailCode.setPid(list.get(1)
//												.split("\\|")[w]);
//									}
//								}
//								String uuidCode = UUID.randomUUID().toString();
//								importDetailCode.setId(uuidCode);
//								importDetailCode.setImportDetailID(importDetail
//										.getId());
//								importDetailCode.setTitleName(titleName);
//								importDetailCode.setTitleValue(array[w]);
//								importDetailCode.setwOrderby(w);
//								importDetailCodeService
//										.addImportDetailCode(importDetailCode);
//							}
//							importDetailService.addImportDetail(importDetail);
//
//						}
//					}
//				} else {// 不带多表头处理
//					for (int t = 0; t < list.size(); t++) {
//						i = i + 1;
//						System.out.println(i + "---" + list.get(t));
//						String[] array = list.get(t).split("\\|");
//						if (t >= 2) {
//							ImportDetail importDetail = new ImportDetail();
//							uuid = UUID.randomUUID();
//							importDetail.setId(uuid.toString());
//							importDetail.setSnumber(array[1]);// 序号
//							importDetail.setUserCode(array[2]);// 工号
//							importDetail.setDepanrment(array[3]);// 部门
//							importDetail.setUserName(array[4]);// 姓名
//							importDetail.setCreateDate(cal.getTime());// 创建日期
//							importDetail.setCreateMan("admin");// 创建人
//							importDetail.setMainDetailID(importMainMDL.getId());
//							ImportDetailCode importDetailCode = new ImportDetailCode();
//							for (int w = 3; w < list.get(1).split("\\|").length - 1; w++) {
//								String titleName = list.get(1).split("\\|")[w];
//								String uuidCode = UUID.randomUUID().toString();
//								importDetailCode.setId(uuidCode);
//								importDetailCode.setImportDetailID(importDetail
//										.getId());
//								importDetailCode.setTitleName(titleName);
//								importDetailCode.setTitleValue(array[w]);
//								importDetailCode.setwOrderby(w);
//								importDetailCodeService
//										.addImportDetailCode(importDetailCode);
//							}
//							importDetailService.addImportDetail(importDetail);
//
//						}
//					}
//				}
//			}
//			// end
//			// 年度绩效工资*****************************************************************
//			// 学期绩效工资*****************************************************************Type=6
//			if (filexqjxgz.getSize() > 0) {
//				long size = filexqjxgz.getSize();
//				byte[] data = new byte[(int) size];
//				InputStream input = filexqjxgz.getInputStream();
//				input.read(data);
//
//				File outFile = new File(myappPath + File.separator
//						+ cal.getTime().getTime()
//						+ filexqjxgz.getOriginalFilename());
//				if (!outFile.exists()) {
//					outFile.createNewFile();
//					System.out.println("full path = "
//							+ outFile.getAbsolutePath());
//				} else {
//					System.out.println("full path = "
//							+ outFile.getAbsolutePath());
//				}
//				FileOutputStream outStream = new FileOutputStream(outFile);
//				outStream.write(data);
//				outStream.close();
//				input.close();
//				// 学期绩效工资
//				List<String> list = ExcelHelper.exportListFromExcel(outFile, 0);
//				if (list.size() > 0) {
//					if (list.get(1).indexOf("序号|工号|部门|姓名") == -1) {
//						ModelAndView view = new ModelAndView("WageList");
//						view.addObject("year", year);
//						view.addObject("month", month);
//						view.addObject("msg", "导入失败导入内容不存在工号！");
//						return view;
//					}
//				}
//				ImportMain importMainMDL = new ImportMain();
//				UUID uuid = UUID.randomUUID();
//				importMainMDL.setId(uuid.toString());
//				importMainMDL.setCreateDate(cal.getTime());
//				importMainMDL.setCreateMan("admin");// 暂时用admin
//				importMainMDL.setImportCount(list.size() - 2);
//				importMainMDL.setIsDelete(1);// 未删除
//				importMainMDL.setIsEnable(0);// 是否显示给用户(不发布)
//				importMainMDL.setWageType(7);// 学期绩效工资
//				importMainMDL.setYearTerm(selYearTerm);
//				importMainMDL.setWeekStart(selWeekStart);
//				importMainMDL.setWeekEnd(selWeekEnd);
//				importMainMDL.setRemark("导入" + selYearTerm + "年学期绩效工资");
//				importMainMDL.setWMonth(selMonth);
//				importMainService.addImportMain(importMainMDL);
//				int i = 0;
//				if (list.get(2).substring(0, 5).equals("| | |")) // 当跨表头时
//				{
//					for (int t = 0; t < list.size(); t++) {
//						i = i + 1;
//						System.out.println(i + "---" + list.get(t));
//						String[] array = list.get(t).split("\\|");
//						if (t > 2) {
//							ImportDetail importDetail = new ImportDetail();
//							uuid = UUID.randomUUID();
//							importDetail.setId(uuid.toString());
//							importDetail.setSnumber(array[1]);// 序号
//							importDetail.setUserCode(array[2]);// 工号
//							importDetail.setDepanrment(array[3]);// 部门
//							importDetail.setUserName(array[4]);// 姓名
//							importDetail.setCreateDate(cal.getTime());// 创建日期
//							importDetail.setCreateMan("admin");// 创建人
//							importDetail.setMainDetailID(importMainMDL.getId());// 数据父级ID
//
//							String thisTitle = "";
//							for (int w = 3; w < list.get(1).split("\\|").length - 1; w++) {
//								ImportDetailCode importDetailCode = new ImportDetailCode();
//								String titleName = list.get(1).split("\\|")[w];
//								if (titleName.equals(" ")) {
//									titleName = list.get(2).split("\\|")[w];
//									if (thisTitle.equals(" ")
//											|| thisTitle.equals("")) {
//										thisTitle = list.get(1).split("\\|")[w - 1];
//										importDetailCode.setPid(thisTitle);
//									} else {
//										if (!list.get(1).split("\\|")[w - 1]
//												.equals(" ")
//												&& !list.get(1).split("\\|")[w - 1]
//														.equals(thisTitle)) {
//											thisTitle = list.get(1)
//													.split("\\|")[w - 1];
//											importDetailCode.setPid(thisTitle);
//										} else {
//											importDetailCode.setPid(thisTitle);
//										}
//									}
//
//								} else {
//									if (list.get(1).split("\\|")[w + 1]
//											.equals(" ")
//											&& (list.get(1).split("\\|").length - 2) > w) {
//										titleName = list.get(2).split("\\|")[w];
//										importDetailCode.setPid(list.get(1)
//												.split("\\|")[w]);
//									}
//								}
//								String uuidCode = UUID.randomUUID().toString();
//								importDetailCode.setId(uuidCode);
//								importDetailCode.setImportDetailID(importDetail
//										.getId());
//								importDetailCode.setTitleName(titleName);
//								importDetailCode.setTitleValue(array[w]);
//								importDetailCode.setwOrderby(w);
//								importDetailCodeService
//										.addImportDetailCode(importDetailCode);
//							}
//							importDetailService.addImportDetail(importDetail);
//
//						}
//					}
//				} else {// 不带多表头处理
//					for (int t = 0; t < list.size(); t++) {
//						i = i + 1;
//						System.out.println(i + "---" + list.get(t));
//						String[] array = list.get(t).split("\\|");
//						if (t >= 2) {
//							ImportDetail importDetail = new ImportDetail();
//							uuid = UUID.randomUUID();
//							importDetail.setId(uuid.toString());
//							importDetail.setSnumber(array[1]);// 序号
//							importDetail.setUserCode(array[2]);// 工号
//							importDetail.setDepanrment(array[3]);// 部门
//							importDetail.setUserName(array[4]);// 姓名
//							importDetail.setCreateDate(cal.getTime());// 创建日期
//							importDetail.setCreateMan("admin");// 创建人
//							importDetail.setMainDetailID(importMainMDL.getId());
//							ImportDetailCode importDetailCode = new ImportDetailCode();
//							for (int w = 3; w < list.get(1).split("\\|").length - 1; w++) {
//								String titleName = list.get(1).split("\\|")[w];
//								String uuidCode = UUID.randomUUID().toString();
//								importDetailCode.setId(uuidCode);
//								importDetailCode.setImportDetailID(importDetail
//										.getId());
//								importDetailCode.setTitleName(titleName);
//								importDetailCode.setTitleValue(array[w]);
//								importDetailCode.setwOrderby(w);
//								importDetailCodeService
//										.addImportDetailCode(importDetailCode);
//							}
//							importDetailService.addImportDetail(importDetail);
//
//						}
//					}
//				}
//			}
			// end
			// 学期绩效工资*****************************************************************

		}
		catch (Exception e)
		{
			ModelAndView view = new ModelAndView("WageList");

			view.addObject("year", year);
			view.addObject("month", month);
			// view.addObject("selyear", selyear);
			// view.addObject("selYearTerm", selYearTerm);
			// view.addObject("selNDMonth", selMonth);
			// view.addObject("selWeekStart", selWeekStart);
			// view.addObject("selWeekEnd",selWeekEnd);
			view.addObject("msg", "导入失败！");
			return view;
		}
		ModelAndView view = new ModelAndView("WageList");
		view.addObject("year", year);
		view.addObject("month", month);
		// view.addObject("selyear", selyear);
		// view.addObject("selYearTerm", selYearTerm);
		// view.addObject("selNDMonth", selMonth);
		// view.addObject("selWeekStart", selWeekStart);
		// view.addObject("selWeekEnd",selWeekEnd);
		view.addObject("msg", "导入成功！");
		return view;
	}
}

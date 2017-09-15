<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
		<title>江西卫生学院-工资查询</title>
		<link href="<c:url value='/resources/css/cssWage.css' />"
			rel="stylesheet" type="text/css">


		<script type="text/javascript"
			src="<c:url value='/resources/js/jquery.js' />"></script>
		<script type="text/javascript"
			src="<c:url value='/resources/js/select-ui.min.js' />"></script>
		<script type="text/javascript"
			src="<c:url value='/resources/js/sel.js' />"></script>
		<script type="text/javascript">
		
		$(document).ready(function(e) {
		$("#month").val("${month}");
		$("#month").find("option[text='${month}']").attr("selected",true);
		$("#year").val("${year}");
		$("#year").find("option[text='${year}']").attr("selected",true);
		
		$("#selyear").val("${selyear}");
		$("#selyear").find("option[text='${selyear}']").attr("selected",true);
		
		$("#selYearTerm").val("${selYearTerm}");
		$("#selYearTerm").find("option[text='${selYearTerm}']").attr("selected",true);
		
		$("#selNDMonth").val("${selNDMonth}");
		$("#selNDMonth").find("option[text='${selNDMonth}']").attr("selected",true);
		
		$("#selWeekStart").val("${selWeekStart}");
		$("#selWeekStart").find("option[text='${selWeekStart}']").attr("selected",true);
		
		$("#selWeekEnd").val("${selWeekEnd}");
		$("#selWeekEnd").find("option[text='${selWeekEnd}']").attr("selected",true);
		
	});
		
		
		
function  onSubmit(){

	var filedagz=document.getElementById("filedagz").value;//档案工资资
	var filebzrgz=document.getElementById("filebzrgz").value;//班主任工资
	var filexzrygz=document.getElementById("filexzrygz").value;//行政人员岗位工资
	var fileyjxgz=document.getElementById("fileyjxgz").value;//月绩效工资
	//var fileqtgz=document.getElementById("fileqtgz").value;//其他工资
	//var filendjxgz=document.getElementById("filendjxgz").value;//年度绩效工资
	//var filexqjxgz=document.getElementById("filexqjxgz").value;//学期绩效工资
	
	var year=$('#year option:selected').val();//年份 
	var month=$('#month option:selected').val();//月份
	//var selyear=$('#selyear option:selected').val();//年度绩效中的年度	
	
	//var selYearTerm=$('#selYearTerm option:selected').val();//学期
	//var selNDMonth=$('#selNDMonth option:selected').val();//月份
	//var selWeekStart=$('#selWeekStart option:selected').val();//周次开始
	//var selWeekEnd=$('#selWeekEnd option:selected').val();//周次结束
	
	
	if(filedagz==""&&filebzrgz==""&&filexzrygz==""&&fileyjxgz==""){
		alert("请至少选择一个要上传的文件");
		return;
	}
	if(filedagz!=""){
		if(filedagz.substring(filedagz.length-3,filedagz.length)!="xls"){
			alert("请上传2003格式EXCEL文件！");
			return;
		}
	}
	if(filexzrygz!=""){
		if(filexzrygz.substring(filexzrygz.length-3,filexzrygz.length)!="xls"){
			alert("请上传2003格式EXCEL文件！");
			return;
		}
	}
	if(fileyjxgz!=""){
		if(fileyjxgz.substring(fileyjxgz.length-3,fileyjxgz.length)!="xls"){
			alert("请上传2003格式EXCEL文件！");
			return;
		}
	}
	//if(fileqtgz!=""){
	//	if(fileqtgz.substring(fileqtgz.length-3,fileqtgz.length)!="xls"&&fileqtgz.substring(fileqtgz.length-4,fileqtgz.length)!="xlsx"){
	//		alert("请上传2003或2007格式EXCEL文件！");
	//		return;
	//	}
	//}
	//if(filendjxgz!="")//年度绩效工资
	//{
	//	if(filendjxgz.substring(filendjxgz.length-3,filendjxgz.length)!="xls"&&filendjxgz.substring(filendjxgz.length-3,filendjxgz.length)!="xlsx"){
	//		alert("请上传2003或2007格式EXCEL文件！");
	//		return;
	//	}
	//	else if(selyear=="")
	//	{
		//	alert("请选择年度！");
		//	return;
	//	}
	//}
	//if(filexqjxgz!="")//学期绩效工资
	//{
	//	if(filexqjxgz.substring(filexqjxgz.length-3,filexqjxgz.length)!="xls"&&filexqjxgz.substring(filexqjxgz.length-4,filexqjxgz.length)!="xlsx"){
	//		alert("请上传2003或2007格式EXCEL文件！");
	//		return;
		//}

	//}
	document.getElementById("uploadmsg").innerHTML="正在导入数据，请稍等……";
	
	document.getElementById("fm").submit();
	if(document.getElementById("uploadmsg").innerHTML=="正在导入数据，请稍等……")
	{
		
		$("#btnImport").attr({"disabled":"disabled"});
	}
	else
	{
	 	$("#btnImport").removeAttr("disabled");	
	}
}
</script>

	</head>

	<body>
		<c:url var="action" value="/WageList/Add.html"></c:url>
		<form method="post" ENCTYPE="multipart/form-data" id="fm"
			action="${action}">
			<div class="header">
				<div class="logo"></div>
				<div class="xxun"></div>
			</div>
			<div class="main">
				<div class="left">
					<div class="nav1">
						<h1>
							工资管理
						</h1>
					</div>
					<div class="nav2">
						<ul>
							<li class="dr">
								<a href="<c:url value='/WageList' />" class="current">工资导入</a>
							</li>
							<li class="rz">
								<a href="<c:url value='/WageListLog' />">导入日志</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="right">
					<div class="menu">
						<strong>位置:工资管理 > 工资导入</strong>
					</div>
					<div class="list">
						<table cellpadding="0" cellspacing="0" border="0" class="tabView">
							<tr>
								<td width="160" align="right">
									选择年份：
								</td>
								<td colspan="2" align="left">
									<select name="year" id="year">
										<option>
											2015
										</option>
										<option>
											2016
										</option>
										<option>
											2017
										</option>
										<option>
											2018
										</option>
										<option>
											2019
										</option>
										<option>
											2020
										</option>
										<option>
											2021
										</option>
										<option>
											2022
										</option>
									</select>
									选择月份：
									<select name="month" id="month">
										<option>
											1
										</option>
										<option>
											2
										</option>
										<option>
											3
										</option>
										<option>
											4
										</option>
										<option>
											5
										</option>
										<option>
											6
										</option>
										<option>
											7
										</option>
										<option>
											8
										</option>
										<option>
											9
										</option>
										<option>
											10
										</option>
										<option>
											11
										</option>
										<option>
											12
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<td width="160" align="right">
									岗位带班津贴课贴绩效工资：
								</td>
								<td width="300" align="left">
									<input type="file" name="filedagz" id="filedagz"
										class="fileStyle" />
								</td>
								<td width="280" align="left">
									<!-- <div class="download">
										<a href="#">下载模板</a> 
									</div>-->
								</td>
							</tr>
							<tr>
								<td width="160" align="right">
									基本工资表：
								</td>
								<td width="300" align="left">
									<input type="file" name="filebzrgz" id="filebzrgz"
										class="fileStyle" />
								</td>
								<td width="280" align="left">
									<!--<div class="download">
											<a href="#">下载模板</a>
									</div>-->
								</td>
							</tr>
							<tr>
								<td width="160" align="right">
									基本绩效工资表：
								</td>
								<td width="300" align="left">
									<input type="file" name="filexzrygz" id="filexzrygz"
										class="fileStyle" />
								</td>
								<td width="280" align="left">
									<!--<div class="download">
											<a href="#">下载模板</a>
									</div>-->
								</td>
							</tr>
							<tr>
								<td width="160" align="right">
									其它工资：
								</td>
								<td width="300" align="left">
									<input type="file" name="fileyjxgz" id="fileyjxgz"
										class="fileStyle" />
								</td>
								<td width="280" align="left">
									<!--<div class="download">
											<a href="#">下载模板</a>
									</div>-->
								</td>
							</tr>
						</table>
					</div>
			
					<div class="list">
						<table cellpadding="0" cellspacing="0" border="0" class="tabView">
							<tr>
								<td width="160" align="right"></td>
								<td width="300" align="left">
									<div id="uploadmsg" style="color: red;">
										${msg}
									</div>
									<input type="button" class="btn" id="btnImport"
										name="btnImport" onClick="onSubmit();" value="导 入" />
								</td>
								<td width="280" align="left"></td>
							</tr>
						</table>
					</div>
					<div class="list nostyle">
						<table cellpadding="0" cellspacing="0" border="0" class="tabView">
							<tr>
								<td width="160" align="right">
									<strong>模板说明：</strong>
								</td>
								
								<td align="left">
								<!-- 
									<div class="czuo">
										<a href="#">展开>></a>
									</div>
								-->
								</td>
								
							</tr>
							<tr>
								<td width="160" align="right"></td>
								<td align="left">
									<div class="sm">
										<p>
											程序将从excel的第二行起读取数据，<b>第一行为表头</b>。
										</p>
										<p>
											各个Execl必须前四列为 <b>“序号、工号、部门、姓名”</b>
											后面可自由定义。
										</p>
										<p>正常格式模版下载<a href="<c:url value='/resources/xls/gzmb.xls' />">模版下载</a></p>
										<p>
											如导入的Execl需要做到多表头情况下 请下载模版 按模版格式<a href="<c:url value='/resources/xls/kbt.xls' />">模版下载</a>
										</p>
										
									</div>
								</td>
							</tr>
							<tr>
								<td width="160" align="right">
								</td>
								
								<td align="left">
								<!-- 
									<div class="czuo">
										<a href="#">收起>></a>
									</div>
									!-->
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="footer">
				<p>
					江西省南昌市小蓝经济开发区汇仁大道689号（汇仁大道与金沙大道交汇处） 邮编：330052
					<br />
					联系电话：0791-85772111(传真)、0791-85772112 Copyright@http://www.jxhlxy.com.cn all rights reserved 赣ICP备0971111号
				</p>
			</div>
		</form>
	</body>
</html>
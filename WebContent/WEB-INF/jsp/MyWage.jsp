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
	$(document)
			.ready(
					function(e) {

						if ($("#year").val("${year}") == 0) {
							$("#month").val("1");
							$("#month").find("option[text='1']").attr(
									"selected", true);
							$("#year").val("2016");
							$("#year").find("option[text='2016']").attr(
									"selected", true);

						} else {
							$("#month").val("${month}");
							$("#month").find("option[text='${month}']").attr(
									"selected", true);
							$("#year").val("${year}");
							$("#year").find("option[text='${year}']").attr(
									"selected", true);
						}

						$("#selwageType").val("${selwageType}");
						$("#selwageType")
								.find("option[value='${selwageType}']").attr(
										"selected", true);

					});

</script>
	</head>

	<body>
		<c:url var="action" value="/SelMyWage"></c:url>
		<form method="post" id="fm" action="${action}">
			<div class="header">
				<div class="logo"></div>
				<div class="xxun"></div>
			</div>
			<div class="main1">
					<div class="menu">
						<strong>位置:工资查询</strong>
					</div>
					<div class="list nostyle">
						<div class="zhushi">
							注：选择工资类型，查询工资
						</div>
						<table cellpadding="0" cellspacing="0" border="0" class="tabs">
							<tr>
								<td>
									查询年月：
									<select id="year" name="year">
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
									</select>
									年
									<select id="month" name="month">
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
									月 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 工资类型
									<select id="selwageType" name="selwageType">
										<option value="1">
											岗位带班津贴课贴绩效工资
										</option>
										<option value="2">
											基本工资表
										</option>
										<option value="3">
											基本绩效工资表
										</option>
										<option value="4">
											其它工资
										</option>
									</select>
									&nbsp;&nbsp;
									<input type="submit"   class="btn" value="检 索" />
								</td>
							</tr>
						</table>
						<div class="dataView">
								<div style="width:100%;margin-left: auto;margin-right: auto; overflow:scroll;">
								<div class="dataView" id="divID">
								</div>
								</div>
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
			
<script type="text/javascript">
	window.onload=function(){
		var iskl="${iskl}";
		var json2=${json2};
		if(json2.length<3)
		{
			packJsonTitle="";
		}
		else
		{
			packJsonTitle=${json2};
		}
		 packJson =${list};
		 //头部内容
		 if(packJson.length==0)
		 {
		 	var c="暂无数据！";
		 }
		 else
		 {
	     var c="";
		 c+='<table cellpadding="0" cellspacing="0" border="0" class="tabData">';
		for(var i=0;i<packJson.length;i++){
			
			if(i==0)//表头定义
			{
				var ts=0;//列数
				 if(iskl=="0")//不垮列
				 {
				 	c+="<tr>";
						for(var key2 in packJson[i]){
						if(key2!="说明"&&key2!="备注")
						{
							c+="<th>"+key2+"</th>";
							ts=ts+1;
						}
						}
					c+="</tr>";
				 
				 }
				 else//跨列处理
				 {
				 		var wh="<tr><td rowspan='2'>序号</td><td rowspan='2'>工号</td>";
				 		var rh="<tr>";
				 		var tw=0;
				 		var ts=0;
					 	for(var w=0;w<packJsonTitle.length;w++)
					 	{
					 	  
					 		if(packJsonTitle[w]["Pid"]=="null"&&packJsonTitle[w]["TitleName"]!="备注")
					 		{
					 		
					 			wh+="<td rowspan='2'>"+packJsonTitle[w]["TitleName"]+"</td>";
					 			tw=0;
					 		}
					 		else 
					 		{
					 		if(tw==0)
					 		{
					 		    wh+="<td colspan='"+SCount(packJsonTitle,packJsonTitle[w]["Pid"])+"'>"+packJsonTitle[w]["Pid"]+"</td>";
					 			tw=1;
					 		}
					 		if(packJsonTitle[w]["TitleName"]!="备注")
					 		{
					 		rh+="<td>"+packJsonTitle[w]["TitleName"]+"</td>";
					 		
					 		}
					 		
					 		}
					 		ts=ts+1;
					 	}
					 	wh+="</tr>";
					 	rh+="</tr>";
					 	c+=wh;
					 	c+=rh;
				 }
			
			}
			
			c+="<tr>";
			var bz="";
			for(var key in packJson[i]){
				if(key!="说明"&&key!="备注")
				{
				c+="<td>"+packJson[i][key]+"</td>";
				}
				if(key=="备注")
				{
				bz="<tr><td colspan='3'>备注信息：</td><td colspan='"+(ts-1)+"'>"+packJson[i][key]+"</td></tr>";
				
				}
			}
			c+="</tr>";
			c+=bz;
		}
		c+="</table>";
	}
		document.getElementById("divID").innerHTML=c;
		};
		//跨列数据处理
		function SCount(packJsonTitle,title)
		{
			var c=0;
			for(var w=0;w<packJsonTitle.length;w++)
			{
				if(packJsonTitle[w]["Pid"]==title)
				{
					c=c+1;
				}
			}
			return c;
		}
</script>
		</form>
	</body>
</html>
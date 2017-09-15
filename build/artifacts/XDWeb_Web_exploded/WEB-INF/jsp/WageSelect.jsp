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
			src="<c:url value='/resources/js/sel.js' />"></script>
		<script type="text/javascript"
			src="<c:url value='/resources/js/select-ui.min.js' />"></script>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	</head>

	<body style="TEXT-ALIGN:center;">
	
	<div style="width:1000px;margin-left: auto;margin-right: auto; ">
			<div class="dataView" id="divID">
			</div>
			</div>
	

	</body>
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
	     var c="<div style='font-size:16px'><b>"+packJson[0]["说明"]+"</b>  合计："+packJson.length+"人</div>";
		 c+='<table cellpadding="0"  cellspacing="0" border="0" class="tabData">';
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
</html>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>部门发稿统计查询</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<title>江西卫生学院-发稿统计</title>
	<link href="<c:url value='/resources/css/cssWage.css' />" rel="stylesheet" type="text/css">
	<script type="text/javascript"	src="<c:url value='/resources/js/jquery.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/select-ui.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/sel.js' />"></script>
	<script src="<c:url value='/resources/js/My97DatePicker/WdatePicker.js' />" type="text/javascript"></script>
</head>
<body>
<c:url var="action" value="/QueryArticle"></c:url>
		<form method="post" id="fm" action="${action}">
			<div class="header">
				<div class="logo"></div>
				<div class="xxun"></div>
			</div>
			<div class="main1">
					<div class="menu">
						<strong>位置:发稿统计</strong>
					</div>
					<div class="list nostyle">
						<div class="zhushi">
							注：选择发稿账号，查询统计
						</div>
		
						<table cellpadding="0" cellspacing="0" border="0" class="tabs">
							<tr>
								<td>
									开始时间：
									<input id="d5221" name="begDate" class="Wdate" value="${begDate}" type="text" onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},maxDate:'#F{$dp.$D(\'d5222\')}'})"/>
									结束时间：
									<input id="d5222" name="endDate" class="Wdate" value="${endDate}" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5221\')}'})"/>
									 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 发稿账号
									<select id="selwageType" name="selDept">

										<c:forEach items="${newsaccount}" var="acc">
											<option value="${acc.UserName}">
												${acc.DisplayName}
											</option>
										</c:forEach>
									</select>
			
									
									&nbsp;&nbsp;
									<input type="submit"   class="btn" value="检 索" />
								</td>
							</tr>
						</table>
						<div class="dataView">
								<div style="width:100%;margin-left: auto;margin-right: auto; overflow:scroll;">
								<div class="dataView" id="divID">
								<table cellpadding="0" cellspacing="0" border="0" class="tabData">
								<tr>
								<th>栏目</th>
								<th>投稿总数</th>
								</tr>
								<c:forEach items="${listArticle}" var="art">
								<tr>
								<td>${art.nodename}</td>
								<td>${art.rowsum}</td>
								</tr>
								</c:forEach>
								</table>
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
	</form>
</body>
</html>
<script type="text/javascript">
$(document).ready(function(e) {
	$("#selwageType").find("option[value='${selDept}']").attr("selected",true);
	
});
</script>
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
		<c:url var="action" value="/WageListLog"></c:url>
		<form method="post" id="fm" action="${action}">
			<div class="header">
				<div class="logo"></div>
				<div class="xxun"></div>
			</div>
			<div class="main">
				<div class="right">
					<div class="menu">
						<strong>位置:工资查询</strong>
					</div>
					<div class="list nostyle">
						<div class="zhushi">
							注：请选择年月查询工资
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
									月 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 导入对象名称
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
										<!-- <option value="5">
											其它工资
										</option>
										<option value="6">
											年度绩效工资
										</option>
										<option value="7">
											周次绩效工资
										</option> -->
									</select>
									&nbsp;&nbsp;
									<input type="submit" class="btn" value="检 索" />
								</td>
							</tr>
						</table>
						<div class="dataView">
							<table cellpadding="0" cellspacing="0" border="0" class="tabData">
								<tr>
									<th>
										导入时间
									</th>
									<th>
										导入对象名称
									</th>
									<th>
										状态
									</th>
									<th>
										说明
									</th>
									<th>
										操作
									</th>
								</tr>
								<c:forEach items="${logListWage}" var="log">

									<tr>
										<td>
											${log.createDate}
										</td>


										<c:choose>
											<c:when test='${log.wageType == "1"}'>
												<td>
													岗位带班津贴课贴绩效工资
												</td>
											</c:when>
											<c:when test='${log.wageType == "2"}'>
												<td>
													基本工资表
												</td>
											</c:when>
											<c:when test='${log.wageType == "3"}'>
												<td>
													基本绩效工资表
												</td>
											</c:when>
											<c:when test='${log.wageType == "4"}'>
												<td>
													其它工资
												</td>
											</c:when>
											
											<c:otherwise>
												<td>
													其他工资
												</td>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test='${log.isEnable =="1"}'>
												<td>
													正常
												</td>
											</c:when>
											<c:otherwise>
												<td>
													禁用
												</td>
											</c:otherwise>
										</c:choose>
										<td>
											${log.remark}
										</td>
										<td>
											<span><a href="#" class="current"
												onclick="javascript:deleteWageList('${log.id}')">删除</a> </span>
											<span><a href="WageSelect/${log.id}" class="current"
												target="_blank">查看</a> </span>

											<span><a href="#" class="current"
												onclick="javascript:enableWageList('${log.id}')"> <c:choose>
														<c:when test='${log.isEnable =="1"}'>
														禁用
														</c:when>
														<c:otherwise>
														正常
														</c:otherwise>
													</c:choose> </a> </span>
										</td>
									</tr>
								</c:forEach>

							</table>
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
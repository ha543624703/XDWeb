<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML>	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
<title>江西卫生学院-工资查询</title>
<link href="<c:url value='/resources/css/css.css' />" rel="stylesheet" type="text/css">

<script type="text/javascript" src="<c:url value='/resources/js/jquery.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/select-ui.min.js' />"></script>  
<script type="text/javascript" src="<c:url value='/resources/js/sel.js' />"></script>
<script type="text/javascript">
function selectXQ(){
	var userId = $("#userId").val();
	var gzId = $("#gzId").val();
	if (userId=="" || gzId=="")
	{
		alert("请输入教师工号和工资编号");
		return false;
	}
	$.ajax({
		url : "/XDWeb/inputUserId",
		type : "post",
		data : {"userId":userId,"gzId":gzId},
		dataType : "json",
		success : function(data) {
			alert("录入成功");
			$("#userId").val("");
			$("#gzId").val("");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {alert("录入失败");}
	});
}
</script>
</head>

<body>
<c:url var="action" value="/ImportLog"  ></c:url>
<form method="post" id="fm"  action="${action}"   >
<div class="banner"></div>
<div class="nav1"><h1>为管理员提供的服务</h1></div>
<div class="nav2"><h1>教职工工资编号录入</h1></div>
<div class="main">
    <div class="navgate">
        <ul>
            <li><a href="<c:url value='/ImportWage' />" >导入工资</a></li>
            <li><a href="<c:url value='/ImportLog' />" class="current" >录入工号</a></li>
        </ul>
    </div>
    <div class="main2">
        <div class="zhushi">注：填写教师工号和工资编号点击录入按钮录入，点击录入已存在的教师编号或工资编号会重新录入</div>
        <div class="search">
            <table cellpadding="0" cellspacing="0" border="0" class="tabView">
                <tr>
                    <td align="center">
            			<span class="inp">教师工号：<input id="userId" type="text" placeholder="请输入教师工号" class="txt" /></span>
            			<span class="inp">工资编号：<input id="gzId" type="text" placeholder="请输入工资编号" class="txt" /></span>
                        <span class="bk"><input type="button" onclick="selectXQ();" class="sear" value="录入" /></span>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<div class="footer">
    <p>江西省南昌市小蓝经济开发区汇仁大道689号（汇仁大道与金沙大道交汇处） 邮编：330052</p>
    <p>联系电话：0791-85772111(传真)、0791-85772112 Copyright@http://www.jxhlxy.com.cn all rights reserved 赣ICP备0971111号</p>
    <p>推荐分辨率：1024*768</p>
</div>
</form>
</body>
</html>
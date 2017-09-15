<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>报修申请</title>
<style>
*{ padding:0px; margin:0px;}
.message{ width:500px; height:120px; line-height:30px; margin:100px auto; border:1px solid #4cb6dd; text-align:center;}
.message h1{ height:30px; background:#4cb6dd; margin:1px; font-size:14px; color:#fff;}
.message h2{ font-size:12px; padding-top:15px; color:#000;}
.message p{ font-size:12px; color:#000;}
.message p a{ color:#00f;}
</style>
</head>

<body>
<div class="message">
    <h1>信息提示</h1>
    <h2>报修申请提交成功！</h2>
    <p>如果您的浏览器没有自动跳转，<a href="/XDWeb/goRepair">请点击这里!</a></p>
</div>
</body>
</html>
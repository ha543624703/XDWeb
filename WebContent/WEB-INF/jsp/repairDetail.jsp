<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="format-detection" content="telephone=no">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>江西卫生职业学院-后勤产业处维修单</title>
<link href="<c:url value='/resources/repair/css/wxDetail.css' />" rel="stylesheet" type="text/css">
</head>

<body>
<div class="banner"><h1>后勤产业处维修单</h1></div>
<div class="global">
    <h1>报修详情</h1>
    <div class="main">
        <div class="line line1">
            <span class="zuo"><p>报修人班级<br/>(教职工部门)</p></span>
            <span class="you">${map.classno}</span>
        </div>
        <div class="line">
            <span class="zuo">报修人姓名</span>
            <span class="you">${map.name}</span>
        </div>
        <div class="line">
            <span class="zuo">报修人电话</span>
            <span class="you">${map.phone}</span>
        </div>
        <div class="line">
            <span class="zuo">报修日期</span>
            <span class="you">${map.repairtime}</span>
        </div>
        <div class="line">
            <span class="zuo">报修场所</span>
            <span class="you">${map.place}</span>
        </div>
        <div class="line">
            <span class="zuo">楼栋信息</span>
            <span class="you">${map.buildingno} ${map.buildingdescribe}</span>
        </div>
    </div>
    <h1>维修项目</h1>
    <div class="main">
        <div class="line">
            <span class="zuo">水电类</span>
            <span class="you">${map.hydropower}</span>
        </div>
        <div class="line">
            <span class="zuo">家具类</span>
            <span class="you">${map.furniture}</span>
        </div>
        <div class="line">
            <span class="zuo">其他类</span>
            <span class="you">${map.other}</span>
        </div>
    </div>  
    <h1>故障说明</h1>
    <div class="main">
        <div class="cont">${map.explaindetail}</div>
    </div>      
    <h1>签名验收</h1>
    <div class="main">
        <div class="line">
            <span class="zuo">报修人签名：</span>
            <span class="you">${map.repairacceptance}</span>
        </div>
        <div class="line">
            <span class="zuo">维修人签名：</span>
            <span class="you">${map.repairedacceptance}</span>
        </div>
    </div>
    <c:if test="${map.status == '0' && (userId == 'admin' || userId == '1998250002')}">
    <div class="btn">
        <ul>
            <li><div class="ysh"><input type="button" onclick="window.location.href='/XDWeb/repairAudited?id=${map.id}'" class="inp" value="审核通过" /></div></li>
            <li><div class="nsh"><input type="button" onclick="window.location.href='/XDWeb/repairFailed?id=${map.id}'" class="inp" value="审核不通过" /></div></li>
        </ul>
    </div>
    </c:if>
</div>
</body>
</html>

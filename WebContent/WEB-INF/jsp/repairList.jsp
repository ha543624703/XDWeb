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
<link href="<c:url value='/resources/repair/css/wxList.css' />" rel="stylesheet" type="text/css">
<script>
function g(o){return document.getElementById(o);}
function HoverLi(n)
{
for(var i=1;i<=3;i++)
{
	g('hdm_'+i).className='normal';
	g('tab_'+i).className='undis';
}
g('tab_'+n).className='dis';
g('hdm_'+n).className='current';
}
</script>
</head>

<body>
<div class="banner"><h1>后勤产业处维修单</h1></div>
<div class="nav">
    <div class="hdm">
        <ul>
            <li id="hdm_1" class="current" onclick="HoverLi(1);">报修待审核</li>
            <li id="hdm_2" onclick="HoverLi(2);">报修已审核</li>
            <li id="hdm_3" onclick="HoverLi(3);">报修未通过</li>
        </ul>
    </div>
</div>
<div class="tabView">
    <div class="dis" id="tab_1">
        <div class="line">
            <ul>
                <c:forEach var="item" items="${wait}">
                <li>
                    <a href="/XDWeb/repairDetail?id=${item.id}"><p><span>${item.repairtime}</span><strong>报修人：${item.name}</strong></p>
                    <p><i class="dsh">待审核</i><em>${item.repairDetail}</em></p></a>
                </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="undis" id="tab_2">
        <div class="line">
            <ul>
                <c:forEach var="item" items="${audited}">
                <li>
                    <a href="/XDWeb/repairDetail?id=${item.id}"><p><span>${item.repairtime}</span><strong>报修人：${item.name}</strong></p>
                    <p><i class="ysh">审核通过</i><em>${item.repairDetail}</em></p></a>
                </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="undis" id="tab_3">
        <div class="line">
            <ul>
                <c:forEach var="item" items="${failed}">
                <li>
                    <a href="/XDWeb/repairDetail?id=${item.id}"><p><span>${item.repairtime}</span><strong>报修人：${item.name}</strong></p>
                    <p><i class="wtg">审核未通过</i><em>${item.repairDetail}</em></p></a>
                </li>
                </c:forEach>
            </ul>
        </div>  
    </div>
</div>
</body>
</html>

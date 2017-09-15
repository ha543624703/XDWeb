<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset=utf-8>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息门户主界面</title>
<link href="<c:url value='/resources/css/cssArticle.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/resources/css/page.css' />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.banner-min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/page.js' />"></script>
<body>
<div class="header">
    <div class="head">
        <div class="logo"></div>
        <div class="exit">
            <ul>
                <li class="zx"><a href="http://218.87.30.159:8066/c/portal/logout">注销</a></li>
              
            </ul>
        </div>
    </div>
</div>
<div class="banner">
	<ul class="slides">
		<li><a href="#"><img src="<c:url value='/resources/images/banner01.jpg' />" /></a></li>
        <li><a href="#"><img src="<c:url value='/resources/images/banner02.jpg' />" /></a></li>
	</ul>
</div>
<div class="main">

<div class="navMenu">
        <div class="navBox">
            <div class="navTitles"><strong>部门动态</strong></div>
            <div class="navData">
                <ul>
                    <li><a href="http://218.87.30.159:8080/XDWeb/getMoreNews" class="navCurrent">学院新闻</a></li>
                    <li><a href="http://218.87.30.159:8080/XDWeb/getMoreDynamic" class="navCurrent">部门动态</a></li>  
                    <li><a href="http://218.87.30.159:8080/XDWeb/getMoreNotice" class="navCurrent">通知公告</a></li> 
                </ul>
            </div>
        </div>
        <div class="navBox h20">
            <div class="navTitles"><strong>通知公告</strong></div>
            <div class="navList">
                <ul>
                    <c:forEach var="item" items="${listdynamic}" varStatus="status" begin="0"> 
                          <li><a href="http://218.87.30.159:8080/XDWeb/getNotice?articleId=${item.id}" target="_blank">${item.title}</a></li>     
                    </c:forEach>                
                </ul>
            </div>
        </div>
    </div>



    <div class="conList">
        <div class="listNav"><strong>部门动态</strong></div>
        <div class="listNews" style="min-height:300px;">
            <ul>
            </ul>
        </div>
        <div style="height:60px; float:right;"><ul class="page" maxshowpageitem="5" pagelistcount="20"  id="page"></ul></div>
    </div>
</div>
<div class="footer">
    <p>江西省南昌市小蓝经济开发区汇仁大道689号（汇仁大道与金沙大道交汇处） 邮编：330052<br/>
    联系电话：0791-85772111(传真)、0791-85772112 Copyright@http://www.jxhlxy.com.cn all rights reserved 赣ICP备0971111号</p>
</div>
</body>
</html>
<script type="text/javascript">
    function tt(dd){
   	 $.ajax({
 		url : "/XDWeb/getMoreDynamic",
 		type : "post",
 		data : {"pageNum":dd},
 		dataType : "json",
 		success : function(data) {
 			$(".listNews > ul").empty();
			var list = data.list;	    				
			for (var i=0; i<list.length; i++)
			{
				var item = list[i];
				var _detail = '<li><span>['+item.update_time+']</span><a href="http://218.87.30.159:8080/XDWeb/getDynamic?articleId='+item.id+'" target="_blank">'+item.title+'</a></li>';

				$(".listNews > ul").append(_detail);	    					
			} 	    				
 		},
 		error : function(XMLHttpRequest, textStatus, errorThrown) {}
 	});
    }
    var GG = {
        "kk":function(mm){
            //alert(mm);
        }
    }

    $("#page").initPage(parseInt('${totalCount}'),1,tt);
</script>
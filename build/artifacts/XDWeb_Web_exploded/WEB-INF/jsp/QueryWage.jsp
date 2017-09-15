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
<title>江西现代技师学院-工资查询</title>
<link href="<c:url value='/resources/css/css.css' />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/resources/js/jquery.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/sel.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/select-ui.min.js' />"></script>


</head>

<body>
<c:url var="action" value="/QueryWage"  ></c:url>
<form method="post" id="fm"  action="${action}"   >

<div class="banner"></div>
<div class="nav1"><h1>教职工工资费用查询</h1></div>
<div class="tycx">
    <div class="cx01">
    选择年份:
        <select name="year" id="year" >
            <option>2015</option>
            <option>2016</option>
            <option>2017</option>
            <option>2018</option>
            <option>2019</option>
            <option>2020</option>
            <option>2021</option>
        </select>  
    选择月份:
        <select name="month" id="month" >
            <option value="1" >1</option>
            <option value="2" >2</option>
            <option value="3" >3</option>
            <option value="4" >4</option>
            <option value="5" >5</option>
            <option value="6" >6</option>
            <option value="7" >7</option>
            <option value="8" >8</option>
            <option value="9" >9</option>
            <option value="10" >10</option>
            <option value="11"  >11</option>
            <option value="12" >12</option>
        </select>
    <input type="button" class="sear" value="查询" onclick="bindData();" />
    </div>
    <c:if test="${username == 'admin'}">
    <div class="cx02"><a target="_blank" href="<c:url value='/ImportWage' />">工资导入</a></div>
    </c:if>
    <c:if test="${username == '910008'}">
    <div class="cx02"><a target="_blank" href="<c:url value='/ImportWage' />">工资导入</a></div>
    </c:if>
</div>
<div class="main3">
    <div id="con">
        <div id="tab_" class="con">
            <ul>
                <li id="tab_1" class="hovertab" onmouseover="sel(1);">基本工资表</li>
                <li id="tab_2" class="normaltab" onmouseover="sel(2);">基本绩效工资表</li>
                <li id="tab_3" class="normaltab" onmouseover="sel(3);">当月岗位带班津贴、科贴</li>
                <li id="tab_4" class="normaltab" onmouseover="sel(4);">其它工资</li>
            </ul>
        </div>
        <div class="ctt">
            <div class="dis" id="box_1">
                <div class="tabBox">
                <table border="0" cellspacing="0" cellpadding="0" class="tabsData">
                <tr>
                    <th>工号</th>
                    <th>所属部门</th>
                    <th>姓名</th>
                    <th>岗位工资</th>
                    <th>薪级工资</th>
                    <th>独子费</th>
                    <th>津贴</th>
                    <th>补发/其它</th>
                    <th>应发金额</th>
                    <th>工会会费</th>
                    <th>公积金</th>
                    <th>社保/医保</th>
                    <th>预扣养老保险</th>
                    <th>省直医保</th>
                    <th>个税</th>
                    <th>实发工资（元）</th>
                </tr>
   
                <tr id=jbgztr>

                </tr>
     
                </table>

                </div>
            </div>
            <div class="undis" id="box_2">
                <div class="tabBox">
                <table border="0" cellspacing="0" cellpadding="0" class="tabsData">
                <tr>
                    <th>工号</th>
                    <th>所属部门</th>
                    <th>姓名</th>
                    <th>基本绩效工资发放标准</th>
                    <th>应发金额</th>
                    <th>个税</th>
                    <th>实发工资（元）</th>
                </tr>
                    
                <tr id=jxgztr>

                </tr>
                </table>
                </div>
            </div>
            <div class="undis" id="box_3">
                <div class="tabBox">
                <table border="0" cellspacing="0" cellpadding="0" class="tabsData">
                <tr>
                    <th>工号</th>
                    <th>所属部门</th>
                    <th>姓名</th>
                    <th>聘用职工基础工资</th>
                    <th>岗位津贴标准</th>
                    <th>当月岗位津贴</th>
                    <th>当月课时津贴</th>
                    <th>当月班主任带班津贴</th>
                    <th>坐班/值班/综治津贴</th>
                    <th>加班津贴</th>
                    <th>第二课堂/兴趣班/社团课时津贴</th>
                    <th>补发/培训费/下企业锻炼津贴/奖励</th>
                    <th>竞赛津贴</th>
                    <th>出卷阅卷改卷监考</th>
                    <th>职务津贴</th>
                    <th>新校工资</th>
                    <th>应发金额</th>
                    <th>社保/医保</th>
                    <th>扣公积金</th>
                    <th>省直医保</th>
                    <th>扣房租水电</th>
                    <th>个人所得税</th>
                    <th>实发工资（元）</th>
                </tr>
                <tr id=jtgztr>

                </tr>
                </table>
                </div>
            </div>
            
            <div class="undis" id="box_4">
                <div class="tabBox">
                <table border="0" cellspacing="0" cellpadding="0" class="tabsData">
                <tr>
                    <th>工号</th>
                    <th>部门</th>
                    <th>姓名</th>
                    <th>工资类别</th>
                    <th>应发金额</th>
                    <th>个人所得税</th>
                    <th>扣发</th>
                    <th>实发金额</th>
                    <th>备注</th>
                </tr>
                <tr id=qtgztr>

                </tr>
                </table>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <p>Copyright @ 2011 Inc. All rights reserved.江西现代技师学院 版权所有</p>
    <p>地址：中国江西省南昌市青山湖区顺外路388号 电话:0791-88270680 0791-88270992</p>
    <p>赣ICP备14006885号 推荐分辨率：1024*768</p>
</div>
</form>
<script type="text/javascript">
$(document).ready(function(e) {
    $(".sel1").uedSelect({
		width : 74			  
	});
    $(".sel2").uedSelect({
		width : 60			  
	});
    $(".sel3").uedSelect({
		width : 200			  
	});
    $(".sel4").uedSelect({
		width : 275			  
	});
	$("#month").val("${month}");
	$("#month").find("option[text='${month}']").attr("selected",true);
	bindData();
});
  function bindData() {
	  var year = $("#year").val();
	  var month = $("#month").val();
      $.ajax({
            url: "<c:url value='/getJbgzList.json' />?year="+year+"&month="+month,
            type: "POST",
            dataType: "json",
            success: function (req) {
                jQuery(".myclass").remove();
                var html = "<tr id=jbgztr ></tr>";
                for (var i = 0; i < req.jbgzlist.length; i++) {
                    html = html + "<tr class='myclass' ><td >" + req.jbgzlist[i].gh + "</td><td >" + req.jbgzlist[i].bm + "</td><td >" + req.jbgzlist[i].xm + "</td><td >" + req.jbgzlist[i].s1 +"</td><td >" + req.jbgzlist[i].s2 +"</td><td >" + req.jbgzlist[i].s3 +"</td><td >"+ req.jbgzlist[i].s4 +"</td><td >" + req.jbgzlist[i].s5 +"</td><td >" + req.jbgzlist[i].s6 +"</td><td >" + req.jbgzlist[i].s7 +"</td><td >" + req.jbgzlist[i].s8 +"</td><td >" + req.jbgzlist[i].s9 +"</td><td >" + req.jbgzlist[i].s10 +"</td><td >" + req.jbgzlist[i].s11 +"</td><td >" + req.jbgzlist[i].s12 +"</td><td >" + req.jbgzlist[i].s13 +"</td></tr>";  
                }
                $("#jbgztr").replaceWith(html);

                html = "<tr id=jxgztr ></tr>";
                for (var i = 0; i < req.jxgzlist.length; i++) {
                    html = html + "<tr class='myclass' ><td >" + req.jxgzlist[i].gh + "</td><td >" + req.jxgzlist[i].bm + "</td><td >" + req.jxgzlist[i].xm + "</td><td >" + req.jxgzlist[i].s1 +"</td><td >" + req.jxgzlist[i].s2 +"</td><td >" + req.jxgzlist[i].s3 +"</td><td >"+ req.jxgzlist[i].s4 +"</td></tr>";  
                }
                $("#jxgztr").replaceWith(html);

                html = "<tr id=jtgztr ></tr>";
                for (var i = 0; i < req.jtgzlist.length; i++) {
                    html = html + "<tr class='myclass' ><td >" + req.jtgzlist[i].gh + "</td><td >" + req.jtgzlist[i].bm + "</td><td >" + req.jtgzlist[i].xm + "</td><td >" + req.jtgzlist[i].s1 +"</td><td >" + req.jtgzlist[i].s2 +"</td><td >" + req.jtgzlist[i].s3 +"</td><td >"+ req.jtgzlist[i].s4 +"</td><td >" + req.jtgzlist[i].s5 +"</td><td >" + req.jtgzlist[i].s6 +"</td><td >" + req.jtgzlist[i].s7 +"</td><td >" + req.jtgzlist[i].s8 +"</td><td >" + req.jtgzlist[i].s9 +"</td><td >" + req.jtgzlist[i].s10 +"</td><td >" + req.jtgzlist[i].s11 +"</td><td >" + req.jtgzlist[i].s12 +"</td><td >" + req.jtgzlist[i].s13 +"</td><td >" + req.jtgzlist[i].s14 +"</td><td >" + req.jtgzlist[i].s15 +"</td><td >" + req.jtgzlist[i].s16 +"</td><td >" + req.jtgzlist[i].s17 +"</td><td >" + req.jtgzlist[i].s18 +"</td><td >" + req.jtgzlist[i].s19 +"</td><td >" + req.jtgzlist[i].s20 +"</td></tr>";  
                }
                $("#jtgztr").replaceWith(html);

                html = "<tr id=qtgztr ></tr>";
                for (var i = 0; i < req.qtgzlist.length; i++) {
                    html = html + "<tr class='myclass' ><td >" + req.qtgzlist[i].gh + "</td><td >" + req.qtgzlist[i].bm + "</td><td >" + req.qtgzlist[i].xm + "</td><td >" + req.qtgzlist[i].s1 +"</td><td >" + req.qtgzlist[i].s2 +"</td><td >" + req.qtgzlist[i].s3 +"</td><td >"+ req.qtgzlist[i].s4 +"</td><td >" + req.qtgzlist[i].s5 +"</td><td >" + req.qtgzlist[i].s6 +"</td></tr>";  
                }
                $("#qtgztr").replaceWith(html);
           }
       });
  }

</script>
</body>
</html>
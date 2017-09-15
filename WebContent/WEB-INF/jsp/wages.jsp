<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />
<title>江西卫生职业学院-工资查询</title>
<link href="<c:url value='/resources/wages/css/wage.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/resources/css/css.css' />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/resources/js/jquery.js' />"></script>
<script type="text/javascript">
$(function(){
	var date = new Date;
	var year = date.getFullYear();
	for (var i = year; i >= year - 3; i--)
	{
		$("select[name='select']").append('<option value="'+i+'">'+i+'</option>');
	}
	selectXQ();
})
function selectXQ(){
	$("#queryError").hide();
	$("#queryIng").show();
	$("#wagesHead").siblings("tr").remove();
	var year = $("select[name='select']").val();
	$.ajax({
		url : "/XDWeb/getWages",
		type : "post",
		data : {"year":year},
		dataType : "json",
		success : function(data) {
			if (data.list != null && data.list.length > 0)
			{
				$("#queryError").hide();
				$("#queryIng").hide();
				for (var i=0; i<data.list.length; i++)
				{
					var item = data.list[i];
					var _wages = '<tr>';
					_wages+='<td>'+item.s59+'</td>';
					_wages+='<td>'+item.bh+'</td>';
					_wages+='<td>'+item.bm+'</td>';
					_wages+='<td>'+item.xm+'</td>';
					_wages+='<td>'+item.s1+'</td>';
					_wages+='<td>'+item.s2+'</td>';
					_wages+='<td>'+item.s3+'</td>';
					_wages+='<td>'+item.s4+'</td>';
					_wages+='<td>'+item.s5+'</td>';
					_wages+='<td>'+item.s6+'</td>';
					_wages+='<td>'+item.s7+'</td>';
					_wages+='<td>'+item.s8+'</td>';
					_wages+='<td>'+item.s9+'</td>';
					_wages+='<td>'+item.s10+'</td>';
					_wages+='<td>'+item.s11+'</td>';
					_wages+='<td>'+item.s12+'</td>';
					_wages+='<td>'+item.s13+'</td>';
					_wages+='<td>'+item.s14+'</td>';
					_wages+='<td>'+item.s15+'</td>';
					_wages+='<td>'+item.s16+'</td>';
					_wages+='<td>'+item.s17+'</td>';
					_wages+='<td>'+item.s18+'</td>';
					_wages+='<td>'+item.s19+'</td>';
					_wages+='<td>'+item.s20+'</td>';
					_wages+='<td>'+item.s21+'</td>';
					_wages+='<td>'+item.s22+'</td>';
					_wages+='<td>'+item.s23+'</td>';
					_wages+='<td>'+item.s24+'</td>';
					_wages+='<td>'+item.s25+'</td>';
					_wages+='<td>'+item.s26+'</td>';
					_wages+='<td>'+item.s27+'</td>';
					_wages+='<td>'+item.s28+'</td>';
					_wages+='<td>'+item.s29+'</td>';
					_wages+='<td>'+item.s30+'</td>';
					_wages+='<td>'+item.s31+'</td>';
					_wages+='<td>'+item.s32+'</td>';
					_wages+='<td>'+item.s33+'</td>';
					_wages+='<td>'+item.s34+'</td>';
					_wages+='<td>'+item.s35+'</td>';
					_wages+='<td>'+item.s36+'</td>';
					_wages+='<td>'+item.s37+'</td>';
					_wages+='<td>'+item.s38+'</td>';
					_wages+='<td>'+item.s39+'</td>';
					_wages+='<td>'+item.s40+'</td>';
					_wages+='<td>'+item.s41+'</td>';
					_wages+='<td>'+item.s42+'</td>';
					_wages+='<td>'+item.s43+'</td>';
					_wages+='<td>'+item.s44+'</td>';
					_wages+='<td>'+item.s45+'</td>';
					_wages+='<td>'+item.s46+'</td>';
					_wages+='<td>'+item.s47+'</td>';
					_wages+='<td>'+item.s48+'</td>';
					_wages+='<td>'+item.s49+'</td>';
					_wages+='<td>'+item.s50+'</td>';
					_wages+='<td>'+item.s51+'</td>';
					_wages+='<td>'+item.s52+'</td>';
					_wages+='<td>'+item.s53+'</td>';
					_wages+='<td>'+item.s54+'</td>';
					_wages+='<td>'+item.s55+'</td>';
					_wages+='<td>'+item.s56+'</td>';
					_wages+='<td>'+item.s57+'</td>';
					_wages+='<td>'+item.s58+'</td>';
					
					_wages+='<td>'+item.s60+'</td>';
					_wages+='<td>'+item.s61+'</td>';
					_wages+='</tr>';
					$("table").append(_wages);
				}
			}
			else
			{
				$("#queryError").show();
				$("#queryIng").hide();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {}
	});
}
</script>
</head>

<body>
<div class="banner"></div>
<div class="nav">
    <div class="menu">
        <div class="top2"><h1>工资查询</h1></div>
        <div class="top3"></div>
    </div>
</div>
<div class="global">
    <div class="selBox">
	    <span class="dlgz"><a href="ImportWage">导入工资</a></span>
        <div class="sel">请选择年份查询：
        <select name="select" onchange="selectXQ();">

        </select>
        </div>
    </div>
    <div class="tabBox">
        <table border="0" cellspacing="0" cellpadding="0" class="tabsData">
            <tr id="wagesHead">
            	<th>实发工资</th>
                <th>工资编号</th>
                <th>所属部门</th>
                <th>姓名</th>
                <th>年</th>
                <th>月</th>
                <th>岗位工资</th>
                <th>薪级工资</th>
                <th>职务十(中系)</th>
                <th>教护龄</th>
                <th>基本工资合计</th>
                <th>职务工资</th>
                <th>保留津贴</th>
                <th>职务10(其他)</th>
                <th>菜蓝子</th>
                <th>物价补贴</th>
                <th>卫生贴</th>
                <th>通讯费</th>
                <th>月奖</th>
                <th>特贴</th>
                <th>基础性绩效合计</th>
                <th>考勤</th>
                <th>其它收入</th>
                <th>班主任津贴</th>
                <th>实习班主任</th>
                <th>成教班主任津贴、监考</th>
                <th>课时费</th>
                <th>监考费</th>
                <th>军训补助</th>
                <th>运动会补贴</th>
                <th>值班费</th>
                <th>成教课时费</th>
                <th>误餐补贴</th>
                <th>中餐</th>
                <th>教研室负责人补贴</th>
                <th>奖励性绩效合计</th>
                <th>精神文明奖</th>
                <th>综治奖</th>
                <th>节能奖</th>
                <th>降温、取暖费</th>
                <th>住房补贴</th>
                <th>科研奖</th>
                <th>政府奖励合计</th>
                <th>失业金</th>
                <th>失业险固定</th>
                <th>公积金</th>
                <th>工会费</th>
                <th>保险</th>
                <th>税款</th>
                <th>电费</th>
                <th>水费</th>
                <th>医药费</th>
                <th>规范津补贴减少额</th>
                <th>养老保险预缴</th>
                <th>扣款</th>
                <th>病事假扣款</th>
                <th>职业年金</th>
                <th>扣款合计</th>
                <th>停发工资</th>
                <th>公积金差额</th>
                <th>应税工资</th>
                <th>计税工资</th>
                
                <th>备注</th>
                <th>是否失保计算</th>
            </tr>
        </table>
        <div id="queryIng" class="message"><span>正在查询...</span></div></td>
        <div id="queryError" class="message"><span>未查询到工资信息！</span></div>
    </div>
</div>
<div class="footer">
    <p>江西省南昌市小蓝经济开发区汇仁大道689号（汇仁大道与金沙大道交汇处）</p>
    <p>邮编：330052 联系电话：0791-85772111(传真)、0791-85772112</p>
    <p>Copyright@http://www.jxhlxy.com.cn all rights reserved 赣ICP备0971111号</p>
</div>
</body>
</html>

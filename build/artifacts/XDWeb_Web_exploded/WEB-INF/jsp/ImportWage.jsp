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
<script  type="text/javascript"  >
$(document).ready(function(e) {
	$("#month").val("${month}");
	$("#month").find("option[text='${month}']").attr("selected",true);
	$("#year").val("${year}");
	$("#year").find("option[text='${year}']").attr("selected",true);
	
});
function onChange(obj){
	if(obj.value.substring(obj.value.length-3,obj.value.length)!="xls"){
		alert("请上传2003或2007格式EXCEL文件！");
		obj.outerHTML += '';   
		obj.value ="";  
	}
}

function  onSubmit(){
	var fitemsfile=document.getElementById("clientFile").value;
	var fitemsfileJX=document.getElementById("clientFileJX").value;
	var fitemsfileJT=document.getElementById("clientFileJT").value;
	var fitemsfileQT=document.getElementById("clientFileQT").value;
	if(fitemsfile==""&&fitemsfileJX==""&&fitemsfileJT==""&&fitemsfileQT==""){
		alert("请选择文件上传");
		return;
	}
	if(fitemsfile!=""){
		if(fitemsfile.substring(fitemsfile.length-3,fitemsfile.length)!="xls"){
			alert("请上传2003或2007格式EXCEL文件！");
			return;
		}
	}
	if(fitemsfileJX!=""){
		if(fitemsfileJX.substring(fitemsfileJX.length-3,fitemsfileJX.length)!="xls"){
			alert("请上传2003或2007格式EXCEL文件！");
			return;
		}
	}
	if(fitemsfileJT!=""){
		if(fitemsfileJT.substring(fitemsfileJT.length-3,fitemsfileJT.length)!="xls"){
			alert("请上传2003或2007格式EXCEL文件！");
			return;
		}
	}
	if(fitemsfileQT!=""){
		if(fitemsfileQT.substring(fitemsfileQT.length-3,fitemsfileQT.length)!="xls"){
			alert("请上传2003或2007格式EXCEL文件！");
			return;
		}
	}
	document.getElementById("uploadmsg").innerHTML="正在导入数据，请稍等……";
	var form = document.getElementById("fm");
	form.method = "post";
	form.submit();
}
</script>
</head>

<body>
<c:url var="action" value="/ImportWage/Add"  ></c:url>
<form method="post" ENCTYPE="multipart/form-data" id="fm"  action="${action}"   >
<div class="banner"></div>
<div class="nav1"><h1>为管理员提供的服务</h1></div>
<div class="nav2"><h1>教职工工资费用导入</h1></div>
<div class="main">
    <div class="navgate">
        <ul>
            <li><a href="<c:url value='/ImportWage' />" class="current">导入工资</a></li>
            <li><a href="<c:url value='/ImportLog' />">录入工号</a></li>
        </ul>
    </div>
    <div class="main1">
        <table cellpadding="0" cellspacing="0" border="0" class="tabView">
            <tr>
                <td width="160" align="right">选择年份：</td>
                <td width="800" align="left">
                    <div class="bk1">
                        <select name="year" id="year" >
                            <option>2015</option>
                            <option>2016</option>
                            <option>2017</option>
                            <option>2018</option>
                            <option>2019</option>
                            <option>2020</option>
                            <option>2021</option>
                        </select>
                    </div>
                    <div class="bk1">选择月份：</div>
                    <div class="bk1">
                        <select name="month" id="month" >
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                            <option>6</option>
                            <option>7</option>
                            <option>8</option>
                            <option>9</option>
                            <option>10</option>
                            <option>11</option>
                            <option>12</option>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="160" align="right">工资表：</td>
                <td width="800" align="left">
                    <div class="bk">
                        <input type="file" class="fileStyle" name="clientFile" id="clientFile" onchange="onChange(this);" />
                        <a target="blank" href="<c:url value='/resources/xls/jbgz.xls' />" >下载模板</a>
                        
                    </div>
                </td>
            </tr>
            <tr style="display: none;">
                <td width="160" align="right">基本绩效工资表：</td>
                <td width="800" align="left">
                    <div class="bk">
                        <input type="file" class="fileStyle" name="clientFileJX" id="clientFileJX" accept=".xls"  onchange="onChange(this);" />
                        <a target="blank" href="<c:url value='/resources/xls/jxgz.xls' />" >下载模板</a>
                    </div>
                </td>
            </tr>
            <tr style="display: none;">
                <td width="160" align="right">当月岗位带班津贴课贴：</td>
                <td width="800" align="left">
                    <div class="bk">
                        <input type="file" class="fileStyle" name="clientFileJT" id="clientFileJT" accept=".xls"  onchange="onChange(this);" />
                        <a target="blank" href="<c:url value='/resources/xls/jtgz.xls' />" >下载模板</a>
                    </div>
                </td>
            </tr>
            <tr style="display: none;">
                <td width="160" align="right">其他工资：</td>
                <td width="800" align="left">
                    <div class="bk">
                        <input type="file" class="fileStyle"  name="clientFileQT" id="clientFileQT" accept=".xls"  onchange="onChange(this);" />
                        <a target="blank" href="<c:url value='/resources/xls/qtgz.xls' />" >下载模板</a>
                    </div>
                </td>
            </tr>
        </table>
    </div>
   
    <div class="main1">
        
        <table cellpadding="0" cellspacing="0" border="0" class="tabView">
            <tr>
                <td width="160" align="right"></td>
                <td width="800" align="left">
                    <div class="bk">
                    	<div id="uploadmsg" style="color: red;" >${msg}</div>
                        <input type="button" id="btnimport" class="btn" value="导 入" onclick="onSubmit();" />
                    </div>
                </td>
            </tr>
        </table>
    </div>
    <div class="main1">
        <table cellpadding="0" cellspacing="0" border="0" class="tabView">
            <tr>
                <th width="160" align="right">模板说明</th>
                <td width="800" align="left">
            
                </td>
            </tr>
            <tr>
                <td width="160" align="right"></td>
                <td width="800" align="left">
                    <div class="bk">
                        <p>程序将从excel的第二行起读取数据，第一行为表头。请上传2003、2007格式EXCEL文件</p>
                        <p><strong>工资表表头包括可下载模板查看</strong></p> 
<%--                         <p><strong>基本工资表表头包括：</strong>序号，工号，所属部门，姓名，岗位工资，薪级工资，独子费，津贴，补发其它，应发金额，工会会费，公积金，社保医保，预扣养老保险，省直医保，个税，实发工资</p> 
                        <p><strong>基本绩效工资表表头包括：</strong>序号，工号，所属部门，姓名，基本绩效工资发放标准，减少额度，应发金额，个税，实发工资</p>
                        <p><strong>当月岗位带班津贴课贴表表头包括：</strong>序号,工号,部门,姓名,聘用职工基础工资,岗位津贴标准,当月岗位津贴,当月课时津贴,当月班主任带班津贴,坐班/值班/综治津贴,加班津贴,第二课堂/兴趣班/社团课时津贴,补发/培训费/下企业锻炼津贴/奖励	竞赛津贴,出卷阅卷改卷监考,职务津贴,新校工资,应发金额,社保/医保,扣公积金,省直医保,扣房租水电,个人所得税,实发工资</p>
                        <p><strong>其它工资发放表，程序将从excel的第二行起读取数据，第一行为表头。 表头包括：</strong>序号，工号，部门，姓名，工资类别，应发金额，个人所得税，扣发，实发金额，备注</p> --%>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="160" align="right"></td>
                <td width="800" align="left">
   
                </td>
            </tr>
        </table>
    </div>
</div>
<div class="footer">
    <p>江西省南昌市小蓝经济开发区汇仁大道689号（汇仁大道与金沙大道交汇处） 邮编：330052</p>
    <p>联系电话：0791-85772111(传真)、0791-85772112 Copyright@http://www.jxhlxy.com.cn all rights reserved</p>
    <p>赣ICP备0971111号 推荐分辨率：1024*768</p>
</div>
</form>
</body>
</html>
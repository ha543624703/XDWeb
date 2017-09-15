<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="format-detection" content="telephone=no">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>江西卫生职业学院-后勤产业处维修单</title>
<link href="<c:url value='/resources/repair/css/wxd.css' />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/repair/times/jedate.js' />"></script>
<script>
$(document).ready(function() {
	$('.inp').hover(function() {
		$(this).addClass('add');
	},function(){
		$(this).removeClass('add');
	});
	$('.inp1').hover(function() {
		$(this).addClass('add');
	},function(){
		$(this).removeClass('add');
	});
	$('.sel').hover(function() {
		$(this).addClass('add');
	},function(){
		$(this).removeClass('add');
	});
	$('.Area').hover(function() {
		$(this).addClass('add');
	},function(){
		$(this).removeClass('add');
	});
});
</script>
</head>
<body>
<form id="form1" action="setRepair" method="post">
<div class="banner"><h1>后勤产业处维修单</h1></div>
<div class="main">
    <div class="line">
        <h1>报修人班级（教职工部门）<em>*</em></h1>
        <p>学生请填写班级，教职工填写部门</p>
        <div class="inp"><input type="text" name="classno" class="txt" /></div>
    </div>
    <div class="line">
        <h1>报修人姓名<em>*</em></h1>
        <div class="inp1"><input type="text" name="name" class="txt" /></div>
    </div>
    <div class="line">
        <h1>报修人电话<em>*</em></h1>
        <div class="inp1"><input type="text" name="phone" class="txt" /></div>
    </div>
    <div class="line">
        <h1>报修场所<em>*</em></h1>
        <div class="sel">
            <select name="place">
                <option selected value="">请选择</option>
                <option value="学生宿舍">学生宿舍</option>
                <option value="教学楼">教学楼</option>
                <option value="实训楼">实训楼</option>
                <option value="活动中心">活动中心</option>
                <option value="食堂">食堂</option>
                <option value="门卫室">门卫室</option>
                <option value="室外公共场所">室外公共场所</option>
            </select>
        </div>
    </div>
    <div class="line">
        <h1>楼栋信息<em>*</em></h1>
        <p>先选择楼栋号，再填写</p>
        <div class="ldInfo1">
            <div class="sel">
                <select name="buildingno">
                    <option selected value="">请选择</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="其他">其他</option>
                </select>
            </div>
        </div>
        <div class="ldInfo2">
            <div class="inp1"><input type="text" name="buildingdescribe" class="txt" placeholder="请填写" /></div>
        </div>
    </div>
    <div class="line">
        <h1>报修项目（报修提示：未在选择栏中的维修项目，请在故障说明中文字描述即可。当天下午4：00前的报修，当天会派工维修，如遇突发紧急情况请先报单后再拨打各服务类维修电话。）</h1>
        <p>水电类维修项目</p>
        <div class="sel">
            <select name="hydropower">
                <option selected value="">请选择</option>
                <option value="灯具">灯具</option>
                <option value="开关插座">开关插座</option>
                <option value="电表">电表</option>
                <option value="线路">线路</option>
                <option value="配电箱">配电箱</option>
                <option value="电风扇">电风扇</option>
                <option value="办公场所空调">办公场所空调</option>
                <option value="水龙头">水龙头</option>
                <option value="冲水延时阀">冲水延时阀</option>
                <option value="水表">水表</option>
                <option value="管道疏通等水电设备">管道疏通等水电设备</option>
                <option value="寝室空调">寝室空调请直接拨打4008899315，无需填单</option>
            </select>
        </div>
        <p>家具类维修项目</p>
        <div class="sel">
            <select name="furniture">
                <option selected value="">请选择</option>
                <option value="门">门</option>
                <option value="窗">窗</option>
                <option value="锁">锁</option>
                <option value="玻璃">玻璃</option>
                <option value="床铺">床铺</option>
                <option value="桌椅">桌椅</option>
                <option value="蚊帐杆">蚊帐杆</option>
            </select>
        </div>
        <p>其他类维修项目</p>
        <div class="sel">
            <select name="other">
                <option selected value="">请选择</option>
                <option value="房屋漏水">房屋漏水</option>
                <option value="地砖破损">地砖破损</option>
                <option value="蹲便池与盥洗池">蹲便池与盥洗池</option>
            </select>
        </div>
    </div>
    <div class="line">
        <h1>故障说明<em>*</em></h1>
        <p>请尽量描述清楚故障部位及故障详细情况。</p>
        <div class="Area">
            <textarea rows="6" name="explain" placeholder="请输入内容..." class="txtArea"></textarea>
        </div>
    </div>
    <!-- 
    <div class="line">
        <h1>图片上传</h1>
        <p>支持 jpg, png, gif, bmp等图片格式</p>
        <div class="photoArea">
            <a href="#">
            <div class="photoBox">   
                <div class="tpImg"></div>
                <div class="tpFont"><p>点击选择图片<br />（需小于500M）</p></div>
            </div>
            </a>
        </div>
    </div> -->
    <div class="line">
        <h1>日期时间<em>*</em></h1>
        <div class="inp2"><input id="dateinfo" name="repairtime" type="text" class="txt" placeholder="请选择日期时间" readonly /></div>
    </div> 
    <div class="line">
        <h1>核对维修项目后，报修人签名验收</h1>
        <div class="inp1"><input type="text" name="repairacceptance" class="txt" /></div>
    </div> 
    <div class="line">
        <h1>维修好后工作人员请手写签名</h1>
        <div class="inp1"><input type="text" name="repairedacceptance" class="txt" /></div>
    </div> 
    <div class="btn"><input id="setRepiar" type="button" class="sub" value="提交报修" /></div>
</div>
<script type="text/javascript">
jeDate
({
	dateCell:"#dateinfo",
	format:"YYYY-MM-DD hh:mm:ss",
	isinitVal:true,
	isTime:true,
	minDate:"2014-09-19 00:00:00",
	okfun:function(val){alert(val)}
})
</script>
</form>
</body>
</html>
<script type="text/javascript">
function checkForm()
{
	if ($("input[name='classno']").val()=="")
	{
		alert("班级或部门不能为空");
		return false;
	}
	if ($("input[name='name']").val()=="")
	{
		alert("报修人姓名不能为空");
		return false;
	}
	if ($("input[name='phone']").val()=="")
	{
		alert("报修人电话不能为空");
		return false;
	}
	if ($("select[name='place']").val()=="")
	{
		alert("报修场所不能为空");
		return false;
	}
	if ($("select[name='buildingno']").val()=="")
	{
		alert("楼栋号不能为空");
		return false;
	}
	if ($("textarea[name='explain']").val()=="")
	{
		alert("故障说明不能为空");
		return false;
	}
	if ($("input[name='repairtime']").val()=="")
	{
		alert("日期时间不能为空");
		return false;
	}
	return true;
}
$("#setRepiar").click(function(){
	if (checkForm())
	{
		var form = document.getElementById("form1");
		form.method = "post";
		form.submit();
	}
})
</script>
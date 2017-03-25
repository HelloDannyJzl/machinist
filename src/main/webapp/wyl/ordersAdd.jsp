<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>创建订单</title>
	<script type="text/javascript" src = "<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	function checksubmit(){
    		var projectid = $('#projectid').val().trim();
            var farmername = $('#farmername').val().trim();
            var phone = $('#phone').val().trim();
            var number = $('#number').val().trim();
            var price = $('#price').val().trim();
            if(projectid==""||farmername==""||phone==""||number==""||price==""){
                alert("字段不能为空");
                return;
            }
            number = parseInt(number);
            price = parseFloat(price);
            if(number<=0||price<=0){
                alert("用工数量和采摘单价不合适");
                return;
            }
            if($('#starttime').val()=="上工时间" || $('#endtime').val()=="下工时间" ){
    			alert("用工时间不能为空！");
    			return ;
    		}
         
          ajaxAddOrders();
    	}
	function ajaxAddOrders(){
		$.ajax({
			type:"post",
			url :"<%=request.getContextPath()%>/orders/addOrders.do",
			data:{
				projectid : $('#projectid').val().trim(),
				farmername : $('#farmername').val().trim() ,
				starttime : $('#starttime').val().trim() ,
				endtime: $('#endtime').val().trim() ,
				number: $('#number').val().trim() ,
				price: $('#price').val().trim() ,
			},
			dataType :"json",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success:function(data){
				if(data.message=="1"){
					alert("添加订单成功，点击确定返回可用项目页")
					 $('#addordersform').submit();
				}else{
					alert("添加订单失败")
				}
			},
			error:function(data){
				alert("添加订单失败")
			}
		})
	}
	</script>
</head>
<body>
	<a style="margin-right: 0px;white-space: normal;display: inline-block;"href="<%=request.getContextPath()%>/orders/queryOrders.do"><h2>订单管理</h2></a><h3 style="margin-right: 0px;white-space: normal;display: inline-block;">/创建订单</h3>
	<hr/>
	<form method="post" id="addordersform" action="<%=request.getContextPath()%>/project/queryAllProject.do"/>
	项目编号：<input type="text" id="projectid" name="projectid" value="${map.projectid}" readonly="readonly"/><br><br>
	农&nbsp;&nbsp;户：<input size="10px;type="text" id="farmername" name="farmername" value="${map.farmername}" readonly="readonly"/>&nbsp;<input type="text" id="phone" name="phone" value="${map.phone}" readonly="readonly"/><br><br>
	用工时间：<input size="12px;"  type="text" id = "starttime"  name="starttime" value="上工时间" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm',minDate: '%y-%M-%d-%H-%m'})"/> 
								 -- <input size="12px;" type="text" id = "endtime" name="endtime" value="下工时间" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm',minDate: '#F{$dp.$D(\'starttime\')}'})"/><br><br>
	需求人数：<input style="width: 50px;" type="number" id="number" name="number" min ="1"/> &nbsp;人<br><br>
	采摘单价：<input style="width: 50px;" type="number" id="price" name="price" min ="0"/>&nbsp;元/斤<br><br>
	<a href="<%=request.getContextPath()%>/project/queryProject.do">取消</a>
	<input type="button" value="添加订单" onclick="checksubmit()"/>
	</form>
</body>
</html>
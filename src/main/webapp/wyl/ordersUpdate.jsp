<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>修改订单</title>
	<script type="text/javascript" src = "<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		
	function checksubmit(){
    		var ordersid = $('#ordersid').val().trim();
            var farmername = $('#farmername').val().trim();
            var phone = $('#phone').val().trim();
            var number = $('#number').val().trim();
            var price = $('#price').val().trim();
            if(ordersid==""||farmername==""||phone==""||number==""||price==""){
                alert("字段不能为空");
                return;
            }
            if(!phone.match(/^1[3|4|5|7|8][0-9]\d{4,8}$/)){
            	 alert("手机号码不合法");
                 return;
            }
            number = parseInt(number);
            price = parseFloat(price);
            if(number<=0||price<=0){
                alert("用工数量和采摘单价不合适");
                return;
            }
            ajaxUpdateOrders();
    	}
	function ajaxUpdateOrders(){
		$.ajax({
			type:"post",
			url :"<%=request.getContextPath()%>/orders/updateOrders.do",
			data:{
				ordersid : $('#ordersid').val().trim(),
				starttime : $('#starttime').val().trim() ,
				endtime: $('#endtime').val().trim() ,
				number: $('#number').val().trim() ,
				price: $('#price').val().trim() ,
			},
			dataType :"json",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success:function(data){
				if(data.message=="1"){
					alert("修改订单成功");
					location.href = "<%=request.getContextPath()%>/orders/queryOrders.do";
				}else{
					alert("修改订单失败")
				}
			},
			error:function(data){
				alert("修改订单失败")
			}
		})
	}
	
	</script>
</head>
<body>
	<h2><b>订单管理中心/修改订单</b></h2>
	<hr/>
	
	订单编号：<input type="text" id="ordersid" name="ordersid" value="${dataMap.ordersid}" readonly="readonly"/><br><br>
	农&nbsp;&nbsp;户：<input size="10px;type="text" id="farmername" name="farmername" value="${dataMap.farmername}" readonly="readonly"/>&nbsp;<input type="text" id="phone" name="phone" value="${dataMap.phone}" readonly="readonly"/><br><br>
	 <input type="hidden" id="oldtime" name="oldtime" value="${dataMap.starttime}" />
	用工时间：<input size="15px;"  type="text" id = "starttime"  name="starttime" value="${dataMap.starttime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:  '#F{$dp.$D(\'oldtime\')}'})" />
								 -- <input size="15px;" type="text" id = "endtime" name="endtime" value="${dataMap.endtime}" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm',minDate: '#F{$dp.$D(\'starttime\')}'})"/><br><br>
	需求人数：<input style="width: 50px;" type="number" id="number" name="number" min ="1" value="${dataMap.number}"/> &nbsp;人<br><br>
	采摘单价：<input style="width: 50px;" type="number" id="price" name="price" min ="0" value="${dataMap.price}"/>&nbsp;元/斤<br><br>
	<input type="button" value="修改订单" onclick="checksubmit()" />
</body>
</html>
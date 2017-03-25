<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员主页</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqPaginator.min.js"></script>
<title>农户审核</title>
<script type="text/javascript">

	function passCheck(){
		var id = $("#id").val();
		backMessage(id);
	}
	
	function backMessage(id){
		$.ajax({
			type:"POST",
			url:"<%=request.getContextPath()%>/manage/updateFlag.do",
			data:{
				id : id,
			},
			dataType:"json",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			success:function(data){
				if(data.message=="1"){
					alert("操作成功，点击确定返回审核用户主页。");
					$("#updateFlag").submit();
				}else{
					alert("不好意思审核失败！哈哈");
				}
			},
			error:function(data){
				alert("出现了世界上没有出现过的问题导致审核失败");
			}
		});
	}
	
	function noPass(){
		var id = $("#id").val();
		var reason = $("#reason").val().trim();
		if(reason==""){
			alert("请说明不通过原因");
			return;
		}
		backNoMessage(id,reason);
	}
	
	function backNoMessage(id,reason){
		$.ajax({
			type:"POST",
			url:"<%=request.getContextPath()%>/manage/updateFlag.do",
			data:{
				id : id,
				reason : reason
			},
			dataType:"json",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			success:function(data){
				if(data.message=="1"){
					alert("操作成功，点击确定返回审核用户主页。");
					$("#updateFlag").submit();
				}else{
					alert("不好意思审核失败！哈哈");
				}
			},
			error:function(data){
				alert("出现了世界上没有出现过的问题导致审核失败");
			}
		});
	}

</script>
</head>
<body>
	<div style="background-color: #AFEEEE;height: 130px;">
			<a style="margin-right: 0px;white-space: normal;display: inline-block;" href="<%=request.getContextPath() %>/manage/queryAllLoginUser.do"><h2>审核中心</h2></a><h3 style="margin-right: 0px;white-space: normal;display: inline-block;">/审核农户</h3>
	</div>
	<form method="post" id="updateFlag" action="<%=request.getContextPath()%>/manage/queryAllLoginUser.do">
		<center>
			<c:forEach items="${userlist}" var="user">
			<input type="hidden" id="id" name="id" value="${user.id}"/>
			工人姓名:&nbsp;<input style="border: 0px; margin: 20px;" type="text" id="name" name="name" value="${user.name}" readonly="readonly" />
			工人年龄:&nbsp;<input style="border: 0px; margin: 20px;" type="text" id="age" name="age" value="${user.age}"/readonly="readonly"><br>
			身份证号:&nbsp;<input style="border: 0px; margin: 20px;" type="text" id="idcard" name="idcard" value="${user.idcard}"/readonly="readonly"><br>
			联系方式:&nbsp;<input style="border: 0px; margin: 20px;" type="text" id="phone" name="phone" value="${user.phone}" readonly="readonly"/>
			家庭住址:&nbsp;<input style="border: 0px; margin: 20px;" type="text" id="adress" name="adress" value="${user.adress}"readonly="readonly"/><br>
		    <hr width="1060px;">
			种植地点:&nbsp;<input style="border: 0px; margin: 20px;" type="text" id="plantadress" name="account" value="${user.plantadress}"readonly="readonly"/>
			种植亩数:&nbsp;<input style="border: 0px; margin: 20px;" type="text" id="plantnumber" name="account" value="${user.plantnumber}"readonly="readonly"/><br>
			种植品种:&nbsp;<input style="border: 0px; margin: 20px;" type="text" id="plantbreed" name="account" value="${user.plantbreed}"readonly="readonly"/>
						<input type="button" value="通过" onclick="passCheck()"/>&nbsp;&nbsp;
						<input type="button" value="不通过" onclick="noPass()"/>
						<input style="height: 90px; widows: 90px;" type="text" id="reason" value="说明拒绝原因" onfocus="javascript:if(this.value=='说明拒绝原因')this.value='';">
			</c:forEach>
	</center>
	</form>
</body>
</html>
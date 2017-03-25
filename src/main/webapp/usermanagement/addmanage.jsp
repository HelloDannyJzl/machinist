<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<title>添加管理员主页面</title>
<script type="text/javascript">
	function checkMessage(){
		var name = $("#name").val().trim();
		var idcard = $("#idcard").val();
		var age = $("#age").val().trim();;
		var phone = $("#phone").val().trim();
		var adress = $("#adress").val().trim();
		var gender = $("#gender").val().trim();
		var account = $("#account").val();
		if(name==""||adress==""||age < 0||gender==""||account==""){
			alert("注册信息不能为空！");
			return;
		}
		if(!phone.match(/^1[3|4|5|7|8][0-9]\d{4,8}$/)){
			alert("手机号码不合法！");
			return;
		}
		if(!idcard.match(/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/)){
			alert("身份证号码不合法！");
			return;
		}
		addFarmer(name,idcard,age,phone,adress,gender,account);
	}
	
	function addFarmer(name,idcard,age,phone,adress,gender,account){
		$.ajax({
			type : "POST",
			url : "<%=request.getContextPath()%>/manage/addManage.do",
			data :{
				name :name,
				idcard : idcard,
				age : age,
				phone : phone,
				adress : adress,
				gender : gender,
				account : account
			},
			dataType : "json",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data){
				if(data.message=="1"){
					alert("添加管理员成功,点击确认返回管理员主页面");
					$("#foreman").submit();
				}
			}
		});
	}


</script>
</head>
<body>
		<div style="background-color: #AFEEEE;height: 160px;">
			<a style="margin-right: 0px;white-space: normal;display: inline-block;" href="<%=request.getContextPath() %>/user/manage.jsp"><h2>管理员主页面</h2></a><h3 style="margin-right: 0px;white-space: normal;display: inline-block;">\用户管理</h3>
			<hr>
	</div>
	<br><br>
	<center>
		<form method="post" id="foreman" action="<%=request.getContextPath()%>/user/manage.jsp">
					姓&nbsp;名&nbsp;：<input style="width: 240px;height: 25px;background-color: Moccasin ;" type="text" id="name" name="name" /><br><br>
					身份证号：<input style="width: 240px;height: 25px;background-color: Moccasin ;" type="text" id="idcard" name="idcard"/><br><br>
					联系方式：<input style="width: 240px;height: 25px;background-color: Moccasin ;" type="text" id="phone" name="phone" /><br><br>
					年&nbsp;龄&nbsp;：<input style="width: 240px;height: 25px;background-color: Moccasin ;" type="text"id="age" name="age"/><br><br>
					性&nbsp;别&nbsp;：<input style="width: 240px;height: 25px;background-color: Moccasin ;" type="text" id="gender" name="gender" /><br><br>
					家庭住址：<input style="width: 240px;height: 25px;background-color: Moccasin ;" type="text" id="adress" name="adress" /><br><br>
					结算账号：<input style="width: 240px;height: 25px;background-color: Moccasin ;" type="text" id=account name="account"/><br><br>
					<input style= "height:25px;width:90px; background-color: Moccasin ;" type="button" value="添加" onclick="checkMessage()"/>
		</form>
	</center>
</body>
</html>
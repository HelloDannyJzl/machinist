<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<title>农户注册页面</title>
<script type="text/javascript">
	
	function checkMessage(){
		var name = $("#name").val().trim();
		var idcard = $("#idcard").val();
		var age = $("#age").val().trim();
		var phone = $("#phone").val().trim();
		var adress = $("#adress").val().trim();
		var plantbreed = $("#plantbreed").val().trim();
		var plantadress = $("#plantadress").val().trim();
		var plantnumber = $("#plantnumber").val();
		var gender = $("#gender").val().trim();
		if(name==""||adress==""||plantbreed==""||plantnumber < 0||age < 0||plantadress==""||gender==""){
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
		addFarmer(name,idcard,age,phone,adress,plantbreed,plantadress,plantnumber,gender);
	}
	
	function addFarmer(name,idcard,age,phone,adress,plantbreed,plantadress,plantnumber,gender){
		$.ajax({
			type : "POST",
			url : "<%=request.getContextPath()%>/user/addFarmer.do",
			data :{
				name :name,
				idcard : idcard,
				age : age,
				phone : phone,
				adress : adress,
				plantbreed : plantbreed,
				plantadress : plantadress,
				plantnumber : plantnumber,
				gender : gender
			},
			dataType : "json",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data){
				if(data.message=="1"){
					alert("注册信息提交成功，管理员正在审核,点击确认返回民力网注册主页面");
					$("#farmer").submit();
				}
			}
		});
	}


</script>
</head>
<body>
	<center>
		<div style="background: PaleTurquoise ; min-height: 190px;margin-top: 0pt;">
			<h2><b>民力网农户注册页面</b></h2>
			<hr width="750px;"/>
			<h3><b>机械师团队制作</b></h3>		
		</div>
		<h2>农户注册必填信息</h2>
		<form method="post" id="farmer" action="<%=request.getContextPath()%>/user/enter1.jsp">
			农户姓名：<input style="width: 240px;height: 25px;background-color: Moccasin ;" type="text" id="name" name="name" /><br><br>
			身份证号：<input style="width: 240px;height: 25px;background-color: Moccasin ;" type="text" id="idcard" name="idcard"/><br><br>
			联系方式：<input style="width: 240px;height: 25px;background-color: Moccasin ;" type="text" id="phone" name="phone" /><br><br>
			农户年龄：<input style="width: 240px;height: 25px;background-color: Moccasin ;" type="number"id="age" name="age"/><br><br>
			农户性别：<input style="width: 240px;height: 25px;background-color: Moccasin ;" type="text" id="gender" name="gender" /><br><br>
			家庭住址：<input style="width: 240px;height: 25px;background-color: Moccasin ;" type="text" id="adress" name="adress" /><br><br>
			种植地址：<input style="width: 240px;height: 25px;background-color: Moccasin ;" type="text" id="plantadress" name="plantadress" /><br><br>
			种植品种：<input style="width: 240px;height: 25px;background-color: Moccasin ;" type="text" id="plantbreed" name="plantbreed" /><br><br>
			种植亩数:<input style="width: 240px;height: 25px;background-color: Moccasin ;" type="number" id="plantnumber" name="plantnumber" /><br><br>
			<input style= "height:25px;width:90px; background-color: Moccasin ;" type="button" value="注册" onclick="checkMessage()"/>
		</form>
	</center>
</body>
</html>
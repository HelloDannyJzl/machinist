<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<title>民力网登录首页</title>
<script type="text/javascript">
	
	function checkMessage(){
		var userName = $("#username").val()
		var passWord = $("#password").val()
		if(userName==""||passWord==""){
			alert("账户密码都不能为空");
			return;
		}
		checkLogin(userName,passWord);
	}
	
	function checkLogin(userName,passWord){
		$.ajax({
			type : "POST",
			url : "<%=request.getContextPath()%>/user/checkLogin.do",
			data : {
				userName : userName,
				passWord : passWord
			},
			dataType : "json",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data){
				if(data.message=="1"){
					alert("账号密码不匹配");
				}else if(data.message=="2"){
					$("#login").submit();
				}else if(data.message=="3"){
					alert("正在审核中，审核后方可登录");
				}
			},
			error : function(data){
				alert("账号密码不匹配");
				
			}
		});
	}
	
	function register(){
		location.href="<%=request.getContextPath()%>/user/login.jsp";
	}
	
</script>
</head>
<body>
	<center>
		<div style="background: PaleTurquoise ; min-height: 190px;margin-top: 0pt;bottom: 0px;">
				<br><br>
				<h2><b>民力网用户登录</b></h2>
				<hr width="750px;"/>
				<h3><b>机械师团队制作</b></h3>
		</div><br><br><br><br>
		<form method="post" id="login" action="<%=request.getContextPath()%>/user/logins.do">
			账&nbsp;号&nbsp;&nbsp;<input style="width: 240px;height: 25px;background-color: Moccasin ;" type="text" id="username"  name="username" /><br><br>
			密&nbsp;码&nbsp;&nbsp;<input style="width: 240px;height: 25px;background-color: Moccasin ;" type="password" id="password"  name="password"/><br><br><br>
			<input style= "height:25px;width:60px; background-color: Moccasin ;" type="button" value="登录" onclick="checkMessage()"/>&nbsp;&nbsp;
			<input style= "height:25px;width:60px; background-color: Moccasin ;" type="button" value="注册" onclick="register()"/>
		</form>
	</center>
</body>
</html>
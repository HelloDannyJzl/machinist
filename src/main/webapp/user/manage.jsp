<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员</title>
</head>
<body>
	<center>
		<div style="background: PaleTurquoise ; min-height: 190px;margin-top: 0pt;">
			<h1><b>机械师制作团队</b></h1>
			<hr width="750px;"/>
			<h2><b>管理员主页面</b></h2>
		</div>
		<br><br><br>
		<h2> <a href="<%=request.getContextPath()%>/project/queryAllProject.do">/********项目管理********/</a> </h2>
		<h2> <a href="<%=request.getContextPath()%>/orders/queryOrders.do">/********查看所有订单********/</a> </h2>
		<h2><a href="<%=request.getContextPath() %>/manage/queryAllUser.do">/********用户管理*********/</a></h2>
		<h2><a href="<%=request.getContextPath()%>/role/queryRole.do">/******角色管理******/</a></h2>
		<h2><a href="<%=request.getContextPath() %>/usermanagement/addmanage.jsp">/********添加管理员*********/</a></h2>
		<h2><a href="<%=request.getContextPath() %>/manage/queryAllLoginUser.do">/********审核新用户*********/</a></h2>
	</center>
</body>
</html>
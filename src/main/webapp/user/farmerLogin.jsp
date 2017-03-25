<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>农户登录</title>
</head>
<body>
	<center>
		<div style="background: PaleTurquoise ; min-height: 190px;margin-top: 0pt;">
			<h1><b>机械师制作团队</b></h1>
			<hr width="750px;"/>
			<h2><b>农户主页面</b></h2>
		</div>
		<br><br><br>
		<h2> <a href="<%=request.getContextPath()%>/project/queryAllProject.do">查看可用项目</a> </h2>
		<a href="<%=request.getContextPath()%>/orders/queryOrders.do"><h2 >订单管理 </h2></a>
		<h2> <a href="<%=request.getContextPath()%>/manage/toupdateUser.do">编辑我的信息</a> </h2>
	</center>
</body>
</html>
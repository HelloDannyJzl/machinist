<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工头主页面</title>
</head>
<body>
	<center>
		<div style="background: PaleTurquoise ; min-height: 190px;margin-top: 0pt;">
			<h1><b>机械师制作团队</b></h1>
			<hr width="750px;"/>
			<h2><b>工头主页面</b></h2>
			<h2> <a href="<%=request.getContextPath()%>/orders/queryOrders.do">/********查看所有可用订单********/</a> </h2>
			<h2> <a href="<%=request.getContextPath()%>/orders/queryOrdersByForeman.do">/********订单查询********/</a> </h2>
			<h2><a href="<%=request.getContextPath()%>/manage/toupdateUser.do">/********编辑我的信息********/</a> </h2>
		</div>
	</center>
</body>
</html>
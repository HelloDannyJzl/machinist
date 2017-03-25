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
<script type="text/javascript">

	function checkAll(){
		location.href="<%=request.getContextPath()%>/manage/queryAllLoginUser.do";
	}

	function checkFarman(){
		location.href="<%=request.getContextPath()%>/manage/queryFFW.do?message=2&flag=3&locate=4";
	}
	
	function checkWorker(){
		location.href="<%=request.getContextPath()%>/manage/queryFFW.do?message=1&flag=3&locate=4";
	}
	
	function checkForman(){
		location.href="<%=request.getContextPath()%>/manage/queryFFW.do?message=0&flag=3&locate=4";
	}
	
	function checkUser(id){
		location.href="<%=request.getContextPath()%>/manage/checkUser.do?id="+id;
	}
	

</script>
</head>
<body>
	<div style="background-color: #AFEEEE;height: 130px;">
			<a style="margin-right: 0px;white-space: normal;display: inline-block;" href="<%=request.getContextPath() %>/user/manage.jsp"><h2>管理员主页面</h2></a><h3 style="margin-right: 0px;white-space: normal;display: inline-block;">/审核新用户</h3>
			<hr>
	</div>

	<input type="hidden" id="currentpage" name="currentpage" value="${currentpage}"/>
	<input type="hidden" id="totalpage" name="totalpage" value="${totalpage}"/>
<div style="height: 30px;">
</div>
	<input  style= "height:25px;width:90px; background-color: PaleTurquoise ;" type="button" value="所有新用户"  onclick="checkAll()"/>&nbsp;
	<input  style= "height:25px;width:90px; background-color: PaleTurquoise ;" type="button" value="审核新农户"  onclick="checkFarman()"/>&nbsp;
	<input  style= "height:25px;width:90px; background-color: PaleTurquoise ;" type="button" value="审核新工头"  onclick="checkForman()"/>&nbsp;
	<input  style= "height:25px;width:90px; background-color: PaleTurquoise ;" type="button" value="审核新工人"  onclick="checkWorker()"/>&nbsp;

	<table  frame="box" rules="all"  width="100%" height="35%">
		<tr style="text-align: center;background-color: LightSkyBlue;">
			<th style="text-align:center" height="70">用户名</th>
			<th style="text-align:center" height="70">年龄</th>
			<th style="text-align:center" height="70">性别</th>
			<th style="text-align:center" height="70">身份证号</th>
			<th style="text-align:center" height="70">联系方式</th>
			<th style="text-align:center" height="70">家庭住址</th>
			<th style="text-align:center" height="70">角色属性</th>
			<th style="text-align:center" height="70">用户状态</th>
			<th style="text-align:center" height="70">操作</th>
		</tr>
		<c:forEach items="${userlist}" var="user">
			<tr style="text-align: center;background-color: PaleTurquoise;">
				<td height="40" align="center">${user.name}</td>
				<td align="center">${user.age}</td>
				<td align="center">${user.gender}</td>
				<td align="center">${user.idcard}</td>
				<td align="center">${user.phone}</td>
				<td align="center">${user.adress}</td>
				<td align="center">${user.rolename}</td>
				<td align="center">
					<c:if test="${user.flag=='3'}"><c:out value="待审核" >
					</c:out></c:if> 
				</td>
				<td align="center"><input type="button" value="审核" onclick="checkUser('${user.id}')"/></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
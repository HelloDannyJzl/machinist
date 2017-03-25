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

var currentpage = 0;//当前页数
var totalpage = 0;
$(function(){
	totalpage=parseInt($('#totalpage').val());
	currentpage=parseInt($('#currentpage').val());
	$.jqPaginator('#jqpaginatorid',{
		totalPages:totalpage,
		currentPage:currentpage,
		visiblePages:10,
		first:'<li class="first"><a href="<%=request.getContextPath()%>/manage/queryAllUser.do?totalpage='+totalpage+'&nextpage=1">首页</a></li>',
		prev:'<li class="prev"><a href="<%=request.getContextPath()%>/manage/queryAllUser.do?totalpage='+totalpage+'&nextpage='+(currentpage-1)+'">上一页</a></li>',
		next:'<li class="next"><a href="<%=request.getContextPath()%>/manage/queryAllUser.do?totalpage='+totalpage+'&nextpage='+(currentpage+1)+'">下一页</a></li>',
		last:'<li class="next"><a href="<%=request.getContextPath()%>/manage/queryAllUser.do?totalpage='+totalpage+'&nextpage='+totalpage+'">尾页</a></li>',
		page:'<li class="page"><a href="<%=request.getContextPath()%>/manage/queryAllUser.do?totalpage='+totalpage+'&nextpage={{page}}">{{page}}</a></li>',
		onPageChange:function(num,type){//页面改变响应事件
		}
	});
});

	function lookMessage(){
		var id = $("input[name='userid']:checked").val();
		if(id==undefined){
			alert("请选中角色之后再做相关操作");
			return;
		}
		location.href="<%=request.getContextPath()%>/manage/toupdateUser.do?id="+id;
	}
	
	function deleteUser(){
		var id = $("input[name='userid']:checked").val();
		if(id==undefined){
			alert("请选中角色之后再做相关操作");
			return;
		}
		alert("确认删除？");
		location.href="<%=request.getContextPath()%>/manage/deleteUser.do?id="+id;
	}
	
	function queryAllUser(){
		location.href="<%=request.getContextPath()%>/manage/queryAllUser.do";
	}
	
	function queryForman(){
		location.href="<%=request.getContextPath()%>/manage/queryFFW.do?message=0&flag=1";
	}
	
	function queryWorker(){
		location.href="<%=request.getContextPath()%>/manage/queryFFW.do?message=1&flag=1";
	}
	
	function queryFarmer(){
		location.href="<%=request.getContextPath()%>/manage/queryFFW.do?message=2&flag=1";
	}
	
	function queryName(){
		var name = $("#name").val().trim();
		location.href="<%=request.getContextPath()%>/manage/queryName.do?name="+name;
	}
	
	
	function checkFarman(){
		location.href="<%=request.getContextPath()%>/manage/queryFFW.do?message=2&flag=3";
	}
	
</script>
</head>
<body>
	<div style="background-color: #AFEEEE;height: 130px;">
			<a style="margin-right: 0px;white-space: normal;display: inline-block;" href="<%=request.getContextPath() %>/user/manage.jsp"><h2>管理员主页面</h2></a><h3 style="margin-right: 0px;white-space: normal;display: inline-block;">/用户管理</h3>
			<hr>
	</div>
	<input type="hidden" id="currentpage" name="currentpage" value="${currentpage}"/>
	<input type="hidden" id="totalpage" name="totalpage" value="${totalpage}"/>
<div style="height: 30px;">
</div>
	<input  style= "height:25px;width:90px; background-color: PaleTurquoise ;" type="button" value="删除用户"  onclick="deleteUser()"/>&nbsp;<br><br>
	<input style= "height:25px;width:100px; background-color: PaleTurquoise" type="button" value="查看详情/修改" onclick="lookMessage()" />
	<input  style= "height:25px;width:90px; background-color: PaleTurquoise ;" type="button" value="查询所有用户"  onclick="queryAllUser()"/>&nbsp;
	<input  style= "height:25px;width:90px; background-color: PaleTurquoise ;" type="button" value="查询所有农户"  onclick="queryFarmer()"/>&nbsp;
	<input  style= "height:25px;width:90px; background-color: PaleTurquoise ;" type="button" value="查询所有工头"  onclick="queryForman()"/>&nbsp;
	<input  style= "height:25px;width:90px; background-color: PaleTurquoise ;" type="button" value="查询所有工人"  onclick="queryWorker()"/>&nbsp;
	<input  style= "height:25px;width:90px; background-color: PaleTurquoise ;" type="button" value="名字查询" onclick="queryName()"/>
	<input  style= "height:25px;width:250px; background-color: Moccasin ;" type="text" id="name" name="name"/><br><br>

	<table  frame="box" rules="all"  width="100%" height="35%">
		<tr style="text-align: center;background-color: LightSkyBlue;">
			<th style="text-align:center" height="70">选择</th>
			<th style="text-align:center" height="70">用户名</th>
			<th style="text-align:center" height="70">年龄</th>
			<th style="text-align:center" height="70">性别</th>
			<th style="text-align:center" height="70">身份证号</th>
			<th style="text-align:center" height="70">联系方式</th>
			<th style="text-align:center" height="70">家庭住址</th>
			<th style="text-align:center" height="70">角色属性</th>
			<th style="text-align:center" height="70">用户状态</th>
		</tr>
		<c:forEach items="${userlist}" var="user">
			<tr style="text-align: center;background-color: PaleTurquoise;">
				<td height="40" align="center"><input type="radio" name="userid" value="${user.id}" /></td>
				<td align="center">${user.name}</td>
				<td align="center">${user.age}</td>
				<td align="center">${user.gender}</td>
				<td align="center">${user.idcard}</td>
				<td align="center">${user.phone}</td>
				<td align="center">${user.adress}</td>
				<td align="center">${user.rolename}</td>
				<td align="center">
					<c:if test="${user.flag=='1'}"><c:out value="已审核">
					</c:out></c:if> 
					<c:if test="${user.flag=='3'}"><c:out value="待审核" >
					</c:out></c:if> 
				</td>
			</tr>
		</c:forEach>
	</table>
	<ul id="jqpaginatorid" class="pagination"></ul>
</body>
</html>
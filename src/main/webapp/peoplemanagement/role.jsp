<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>书籍管理页面</title>
 <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqPaginator.min.js"></script>
<title>Insert title here</title>
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
			first:'<li class="first"><a href="<%=request.getContextPath()%>/role/queryRole.do?totalpage='+totalpage+'&nextpage=1">首页</a></li>',
			prev:'<li class="prev"><a href="<%=request.getContextPath()%>/role/queryRole.do?totalpage='+totalpage+'&nextpage='+(currentpage-1)+'">上一页</a></li>',
			next:'<li class="next"><a href="<%=request.getContextPath()%>/role/queryRole.do?totalpage='+totalpage+'&nextpage='+(currentpage+1)+'">下一页</a></li>',
			last:'<li class="next"><a href="<%=request.getContextPath()%>/role/queryRole.do?totalpage='+totalpage+'&nextpage='+totalpage+'">尾页</a></li>',
			page:'<li class="page"><a href="<%=request.getContextPath()%>/role/queryRole.do?totalpage='+totalpage+'&nextpage={{page}}">{{page}}</a></li>',
			onPageChange:function(num,type){//页面改变响应事件
			}
		});
	});
	
	function addRole(){
		location.href="<%=request.getContextPath()%>/peoplemanagement/addRole.jsp";
	}
	
	function updateRole(){
		var id = $("input[name='roleid']:checked").val();
		if(id==undefined){
			alert("请选中角色之后再进行操作");
			return;
		}
		location.href="<%=request.getContextPath()%>/role/toUpdateRole.do?id="+id;
	}
	
	function deleteRole(){
		var id = $("input[name='roleid']:checked").val();
		if(id==undefined){
			alert("请选中角色之后再进行操作");
			return;
		}
		location.href="<%=request.getContextPath()%>/role/deleteRole.do?id="+id;
	}
</script>
</head>
<body >
<div style="background-color: #AFEEEE;height: 130px;">
			<a style="margin-right: 0px;white-space: normal;display: inline-block;" href="<%=request.getContextPath() %>/user/manage.jsp"><h2>管理员主页面</h2></a><h3 style="margin-right: 0px;white-space: normal;display: inline-block;">/用户管理</h3>
			<hr>
	</div>
	<input type="hidden" id="currentpage" name="currentpage" value="${currentpage}"/>
	<input type="hidden" id="totalpage" name="totalpage" value="${totalpage}"/><br><br>
	<input style="background: LightSkyBlue ;" type="button" value="添加角色" onclick="addRole()"/>&nbsp;&nbsp;
	<input style="background: LightSkyBlue ;" type="button" value="修改角色" onclick="updateRole()"/>&nbsp;&nbsp;
	<input style="background: LightSkyBlue ;" type="button" value="删除角色" onclick="deleteRole()"/><br><br>
	<table frame="box" rules="all" width="100%" height="35%">
		<tr style="text-align: center;background-color: LightSkyBlue;">
			<th style="text-align:center" height="70" ><b>选择</b></th>
			<th style="text-align:center" height="70"><b>角色类型</b></th>
			<th style="text-align:center" height="70"><b>创建时间</b></th>
			<th style="text-align:center" height="70"><b>更改时间</b></th>
			<th style="text-align:center" height="70"><b>更改人id</b></th>
		</tr>
		
		<c:forEach items="${rolelist}" var="role">
			<tr style="text-align: center;background-color: PaleTurquoise;">
				<td height="40" align="center"><input type="radio" name="roleid" value="${role.id}"/></td>
				<td align="center">${role.rolename}</td>
				<td align="center"><fmt:formatDate value="${role.createtime}" pattern="YYYY-MM-dd"/></td>
				<td align="center"><fmt:formatDate value="${role.updatetime}" pattern="YYYY-MM-dd"/> </td>
				<td align="center">${role.userid}</td>
			</tr>
		</c:forEach>
	</table>
	<ul id="jqpaginatorid" class="pagination"></ul>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqPaginator.min.js"></script>
<title>修改用户</title>
<script type="text/javascript">
	function black(){
		location.href="<%=request.getContextPath()%>/manage/queryAllUser.do";
	}
	
	function updateUser(){
		var name = $("#name").val().trim();
		var adress = $("#adress").val().trim();
		var age = $("#age").val().trim();
		var gender = $("#gender").val().trim();
		var phone = $("#phone").val().trim();
		var plantnumber = $("#plantnumber").val().trim();
		var plantadress = $("#plantadress").val().trim();
		var plantbreed = $("#plantbreed").val().trim();
		if(name==""||adress==""||age==""||gender==""||plantnumber==""||plantadress==""||plantbreed==""){
			alert("该页面信息为用户基本信息，全不能为kong");
			return;
		}
		if(!phone.match(/^1[3|4|5|7|8][0-9]\d{4,8}$/)){
			alert("手机号码不合法！");
			return;
		}
		$("#updateCheck").submit();
	}


</script>

</head>
<body>
	<div >
			<a style="margin-right: 0px;white-space: normal;display: inline-block;" href="<%=request.getContextPath() %>/manage/queryAllUser.do"><h2>管理中心</h2></a><h2 style="margin-right: 0px;white-space: normal;display: inline-block;">/用户详情/修改信息</h2>
	</div>
	
	<div style="margin-left: 45%;">
		<form method="post" id="updateCheck" action="<%=request.getContextPath()%>/manage/updateUser.do">
				<c:forEach items="${userList}" var="user">
								<input type="hidden" id="id" name="id" value="${user.id}" />
					员工姓名:&nbsp;<input style="margin-right: 40px; border: 0px;border-top: 50px;" type="text" id="name" name="name" value="${user.name}"/>
					联系方式:&nbsp;<input style="margin-right: 40px; border: 0px;" type="text" id="phone" name="phone" value="${user.phone}"/><br><br>
					员工年龄:&nbsp;<input style="margin-right: 40px; border: 0px;" type="text" id="age" name="age" value="${user.age}"/>
					员工性别&nbsp;:<input style="margin-right: 40px; border: 0px;" type="text" id="gender" name="gender" value="${user.gender}"/><br><br>
					角色名称:&nbsp;<input style="margin-right: 40px; border: 0px;" type="text" id="rolename" name="rolename" value="${user.rolename}"/>
					家庭住址:&nbsp;<input style="margin-right: 40px; border: 0px;" type="text" id="adress" name="adress" value="${user.adress}"/><br><br>
					身份证号:&nbsp;<input style="margin-right: 40px; border: 0px;" type="text" id="idcard" name="idcard" value="${user.idcard}" readonly="readonly"/><br>
					<hr>
					种植地址:&nbsp;<input style="margin-right: 40px; border: 0px;" type="text" id="plantadress" name="plantadress" value="${user.plantadress}"/>
					种植亩数:&nbsp;<input style="margin-right: 40px; border: 0px;" type="text" id="plantnumber" name="plantnumber" value="${user.plantnumber}"/><br><br>
					种植品种:&nbsp;<input style="margin-right: 40px; border: 0px;" type="text" id="plantbreed" name="plantbreed" value="${user.plantbreed}"/><br><br>
					<input type="button"  value="返回" onclick="black()"/>&nbsp;
					<input type="button" value="确认修该"onclick="updateUser()"/>
				</c:forEach>
		</form>
	</div>

</body>
</html>
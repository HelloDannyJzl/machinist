<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<title>角色修改主页面</title>
<script type="text/javascript">

	function updateRole(){
		var roleName = $("#rolename").val().trim();
		var updateName = $("#updatename").val().trim();
		if(updateName == ""){
			alert("角色名称不能为空！！");
			return;
		}
		if(roleName == updateName){
			alert("修改后的角色名称与原角色名称一致！这样就没意思啦！");
			return;
		}
		checkRoleName(updateName);
	}
	
	function checkRoleName(updateName){
		var id = $("#id").val();
		$.ajax({
			type : "POST",
			url : "<%=request.getContextPath()%>/role/checkRole.do",
			data : {
				id :id,
				updateName : updateName
			},
			dataType : "json",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data){
				if(data.message=="2"){
					alert("该角色已经存在！")
				}else{
					$("#updateRoleName").submit();
				}
			},
			error:function(data){
				alert("修改角色失败："+data);
			}
		});
	}
	
	function homePage(){
		location.href="<%=request.getContextPath()%>/role/queryRole.do";
	}
	

</script>
</head>
<body>
	<center>
		<div style="background: PaleTurquoise ; min-height: 190px;margin-top: 0pt;">
			<h1><b>机械师制作团队</b></h1>
			<hr width="750px;"/>
			<h2><b>修改角色页面</b></h2>
		</div>
	</center>
	<center>
		<div>
		<form method="post" id="updateRoleName" action="<%=request.getContextPath()%>/role/updateRole.do">
					 <input  type="hidden" id="roleid" name="roleid" value="${roleMap.id}" /><br><br><br><br>
			角色原名称&nbsp;：<input style= "height:25px;width:250px; background-color: Moccasin ;" type="text" id="rolename" name="rolename" value="${roleMap.rolename}" readonly="readonly"/><br><br><br><br>
			修改角色为&nbsp;：<input style= "height:25px;width:250px; background-color: Moccasin ;" type="text" id="updatename" name="updatename" /><br><br>
					  <input style= "height:25px;width:90px; background-color: PaleTurquoise ;" type="button" value="确认修改" onclick="updateRole()"/>&nbsp;&nbsp;
					  <input style= "height:25px;width:90px; background-color: PaleTurquoise ;" type="button" value="返回角色管理主页" onclick="homePage()"/>
					  
		</form>
	</div>
	</center>
</body>
</html>
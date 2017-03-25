<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<title><b>添加角色主页</b></title>
<script type="text/javascript">
	function addRole(){
		var roleName = $("#roleName").val().trim();
		if(roleName==""){
			alert("角色名称不能为空！");
			return;
		}
		toAddRole(roleName);
	}
	
	function toAddRole(roleName){
		$.ajax({
			type : "POST",
			url : "<%=request.getContextPath()%>/role/checkRole.do",
			data :{
				roleName : roleName
			},
			dataType : "json",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data){
				if(data.message=="1"){
					$("#addRole").submit();
				}else{
					alert("该角色已经存在！");
				}
			},
			error:function(data){
				alter("添加角色失败："+data);
			}
		});
	}
	
	function homePage(){
		location.href="<%=request.getContextPath()%>/role/queryRole.do";
	}


</script>
</head>
<body style="background: PaleTurquoise ;">
	<br><br><br><br>
	<center>
		<form method="post" id="addRole" action="<%=request.getContextPath()%>/role/addRole.do">
			新增角色名称：<input style= "height:25px;width:250px; background-color: Moccasin ;" type="text" id="roleName" name="roleName"/><br><br>
				 	 <input type="button" value="提交" onclick="addRole()"/>&nbsp;&nbsp;
				 	 <input style= "height:25px;width:90px; background-color: PaleTurquoise ;" type="button" value="返回角色管理主页" onclick="homePage()"/>
		</form>
	</center>
</body>
</html>
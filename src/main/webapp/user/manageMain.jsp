<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../head.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <title>短工宝后台管理系统</title>
    </head>
    <frameset rows="88,*" cols="*" frameborder="no" border="0" framespacing="0">
        <frame src="manageTop.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
        <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
            <frame src="manageLeft.jsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
            <frame src="<%=request.getContextPath()%>/project/queryAllProject.do" name="rightFrame" id="rightFrame" title="rightFrame" />
        </frameset>
    </frameset>
    <noframes>
        <body>
            <div class="loginbm">©版权所有 machinist开发团队 2015-2016</div>
        </body>
    </noframes>
</html>

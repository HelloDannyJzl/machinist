<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>可用项目页</title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jqPaginator.min.js"></script>
    <script type="text/javascript">

        var totalpage = 0;
	    var currentpage = 0; 
    	$(function(){
    		totalpage = parseInt($('#totalpage').val());
            currentpage = parseInt($('#currentpage').val());
            $.jqPaginator('#jqpaginatorid',{
                totalPages : totalpage,
                currentPage : currentpage,
                visiblePages : 10,
                first:'<li class="first"><a href="<%=request.getContextPath()%>/project/queryAllProject.do?totalpage='+totalpage+'&nextpage=1">首页</a></li>',
                prev:'<li class="prev"><a href="<%=request.getContextPath()%>/project/queryAllProject.do?totalpage='+totalpage+'&nextpage='+(currentpage-1)+'">上一页</a></li>',
                next:'<li class="next"><a href="<%=request.getContextPath()%>/project/queryAllProject.do?totalpage='+totalpage+'&nextpage='+(currentpage+1)+'">下一页</a></li>',
                last:'<li class="next"><a href="<%=request.getContextPath()%>/project/queryAllProject.do?totalpage='+totalpage+'&nextpage='+totalpage+'">尾页</a></li>',
                page:'<li class="page"><a href="<%=request.getContextPath()%>/project/queryAllProject.do?totalpage='+totalpage+'&nextpage={{page}}">{{page}}</a></li>',
                onPageChange: function (num, type) {//页面改变响应事件
                }
    		});
    	});
        
    </script>
</head>
    <body style="padding:10px;">
    <div style="background-color: #AFEEEE;height: 130px;">
			<a style="margin-right: 0px;white-space: normal;display: inline-block;" href="<%=request.getContextPath() %>/user/farmerLogin.jsp"><h2>农户主页</h2></a><h3 style="margin-right: 0px;white-space: normal;display: inline-block;">/可用项目</h3>
			<hr>
	</div><br><br>
        <input type="hidden" id="totalpage" name="totalpage" value="${totalpage}" />
        <input type="hidden" id="currentpage" name="currentpage" value="${currentpage}" />
        <h3 >可用项目列表</h3><br/>
        
        <table frame="box" rules="all" width="100%">
            <tr>
                <th style="text-align:center"height="70px">项目编号</th>
                <th style="text-align:center"height="70px">项目名称</th>
                <th style="text-align:center"height="70px">项目地址</th>
                <th style="text-align:center"height="70px">项目开始时间</th>
                <th style="text-align:center"height="70px">项目状态</th>
                <th style="text-align:center"height="70px">操作</th>
            </tr>
            <c:forEach items="${datalist}" var="type">
                <tr>
                    <td align="center"height="40px">${type.pronumber}</td>
                    <td align="center"height="40px">${type.proname}</td>
                    <td align="center"height="40px">${type.proaddress}</td>
                    <td align="center"height="40px">${type.starttime}</td>
                    <td align="center"height="40px">
                        <c:if test="${type.flag==1}">项目进行中</c:if>
                        <c:if test="${type.flag==0}">项目已结束</c:if>
                    </td>
                    <td align="center"height="40px">
                    	<a href="<%=request.getContextPath()%>/orders/toAddOrders.do?pronumber=${type.pronumber}">添加订单</a>
                    </td>
                 </tr>
            </c:forEach>
            
        </table>
        <ul id="jqpaginatorid" class="pagination"></ul>
    </body>
</html>




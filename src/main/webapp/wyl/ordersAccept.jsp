<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>可用订单</title>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
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
                first:'<li class="first"><a href="<%=request.getContextPath()%>/orders/queryOrders.do?totalpage='+totalpage+'&nextpage=1">首页</a></li>',
                prev:'<li class="prev"><a href="<%=request.getContextPath()%>/orders/queryOrders.do?totalpage='+totalpage+'&nextpage='+(currentpage-1)+'">上一页</a></li>',
                next:'<li class="next"><a href="<%=request.getContextPath()%>/orders/queryOrders.do?totalpage='+totalpage+'&nextpage='+(currentpage+1)+'">下一页</a></li>',
                last:'<li class="next"><a href="<%=request.getContextPath()%>/orders/queryOrders.do?totalpage='+totalpage+'&nextpage='+totalpage+'">尾页</a></li>',
                page:'<li class="page"><a href="<%=request.getContextPath()%>/orders/queryOrders.do?totalpage='+totalpage+'&nextpage={{page}}">{{page}}</a></li>',
                onPageChange: function (num, type) {//页面改变响应事件
                }
    		});
    	});
    	
    	function ajaxAcceptOrders(ordersid){
    		$.ajax({
    			type:"post",
    			url :"<%=request.getContextPath()%>/orders/acceptOrders.do",
    			data:{
    				ordersid:ordersid
    			},
    			dataType:"json",
    			contentType : "application/x-www-form-urlencoded; charset=utf-8",
    			success:function(data){
    				if(data.message=="1"){
    					alert("接单成功，点击确定返回可用订单页面")
    					location.href = "<%=request.getContextPath()%>/orders/queryOrders.do";
    				}else{
    					alert("接单失败")
    				}
    			},
    			error:function(data){
    				alert("接单失败")
    			}
    		})
    	}
    	
    </script>
</head>
<body style="padding:10px;">
	<!--所有 4 个内边距都是 10px -->
	<div style="background-color: #AFEEEE;height: 130px;">
			<a style="margin-right: 0px;white-space: normal;display: inline-block;" href="<%=request.getContextPath() %>/user/foremanLogin.jsp"><h2>工头主页</h2></a><h3 style="margin-right: 0px;white-space: normal;display: inline-block;">/可用订单</h3>
			<hr>
	</div><br><br>
	<!-- 页面缓存总页数和当前页数 都放在隐藏域里面 -->
		<input type="hidden" id="totalpage" name="totalpage" value="${totalpage}" />
        <input type="hidden" id="currentpage" name="currentpage" value="${currentpage}" />
      
        <table frame="box" rules="all" width="100%">
        	<tr>
        		<th style="text-align:center;"height="70px">项目编号</th>
        		<th style="text-align:center;"height="70px">订单编号</th>
        		<th style="text-align:center;"height="70px">农户姓名</th>
        		<th style="text-align:center;"height="70px">下单时间</th>
        		<th style="text-align:center;"height="70px">用工时间（起）</th>
        		<th style="text-align:center;"height="70px">用工时间（止）</th>
        		<th style="text-align:center;"height="70px">用工数量</th>
        		<th style="text-align:center;"height="70px">采摘单价</th>
        		<th style="text-align:center;"height="70px">操作</th>
        	</tr>
        	 <c:forEach items="${queryAllOrders}" var="type">
        	 	<tr>
                    <td align="center"height="40px">${type.projectid}</td>
                    <td align="center"height="40px">${type.ordersid}</td>
                    <td align="center"height="40px">${type.farmername}</td>
                    <td align="center"height="40px"><fmt:formatDate value="${type.createtime}" pattern="yyyy-MM-dd"/></td>
                    <td align="center"height="40px"><fmt:formatDate value="${type.starttime}" pattern="yyyy-MM-dd"/></td>
                    <td align="center"height="40px"><fmt:formatDate value="${type.endtime}" pattern="yyyy-MM-dd"/></td>
                    <td align="center"height="40px">${type.number}</td>
                    <td align="center"height="40px">${type.price}</td>
                    <td align="center">
                    	<input type="button" value="接单" onclick="ajaxAcceptOrders('${type.ordersid}')" style="margin:0px; padding:0px; width:60px; height:30px; line-height:30px;border:0px;"/>
                    </td>
                   </tr>
        	</c:forEach>
        </table>
        <ul id="jqpaginatorid" class="pagination"></ul>
	</body>
</html>
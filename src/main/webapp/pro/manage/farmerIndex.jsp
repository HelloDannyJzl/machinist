<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>项目管理页</title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jqPaginator.min.js"></script>
    <script type="text/javascript">

        var totalpage = 0; //总页数
        var currentpage = 0; //当前页数

        /**
         * 加载分页js代码
         */
        $(function (){
            totalpage = parseInt($('#totalpage').val());
            currentpage = parseInt($('#currentpage').val());
            $.jqPaginator('#jqpaginatorid',{
                totalPages:totalpage,
                currentPage:currentpage,
                visiblePages:10,
                first:'<li class="first"><a href="<%=request.getContextPath()%>/project/queryAllProject.do?totalpage='+totalpage+'&nextpage=1">首页</a></li>',
                prev:'<li class="prev"><a href="<%=request.getContextPath()%>/project/queryAllProject.do?totalpage='+totalpage+'&nextpage='+(currentpage-1)+'">上一页</a></li>',
                next:'<li class="next"><a href="<%=request.getContextPath()%>/project/queryAllProject.do?totalpage='+totalpage+'&nextpage='+(currentpage+1)+'">下一页</a></li>',
                last:'<li class="next"><a href="<%=request.getContextPath()%>/project/queryAllProject.do?totalpage='+totalpage+'&nextpage='+totalpage+'">尾页</a></li>',
                page:'<li class="page"><a href="<%=request.getContextPath()%>/project/queryAllProject.do?totalpage='+totalpage+'&nextpage={{page}}">{{page}}</a></li>',
                onPageChange: function (num, type) {//页面改变响应事件
                }
            });
        });

        
        function addOrders(id){
        	if(id==""){
                alert("id不能为空");
                return;
            }
        	location.href = "<%=request.getContextPath()%>/orders/addOrders.do?id="+id;
        }
        
        function queryOrders(id){
        	if(id==""){
                alert("id不能为空");
                return;
            }
        	location.href = "<%=request.getContextPath()%>/orders/queryAllOrders.do?id="+id;
        }
        
    </script>
</head>
    <body style="padding:10px;">
        <input type="hidden" id="totalpage" name="totalpage" value="${totalpage}" />
        <input type="hidden" id="currentpage" name="currentpage" value="${currentpage}" />
        <h1 style="text-align:center;">农户主页</h1><br/>
        <h2 >可用项目列表</h2><br/>
          <p> <a href="<%=request.getContextPath()%>/orders/queryAllOrders.do">订单管理 </a> </p>
        
        <table frame="box" rules="all" width="100%">
            <tr>
                <th style="text-align:center">选择</th>
                <th style="text-align:center">项目编号</th>
                <th style="text-align:center">项目名称</th>
                <th style="text-align:center">项目地址</th>
                <th style="text-align:center">项目开始时间</th>
                <th style="text-align:center">项目结束时间</th>
                <th style="text-align:center">项目状态</th>
                <th style="text-align:center">操作</th>
            </tr>
            <c:forEach items="${datalist}" var="type">
                <tr>
                    <td align="center"><input type="radio" name="typeid" value="${type.id}"/></td>
                    <td align="center">${type.pronumber}</td>
                    <td align="center">${type.proname}</td>
                    <td align="center">${type.proaddress}</td>
                    <td align="center">
                    	<c:if test="${type.starttime==null}">项目未开始</c:if>
                    	<fmt:formatDate value="${type.starttime}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td align="center"><fmt:formatDate value="${type.endtime}" pattern="yyyy-MM-dd"/></td>
                    <td align="center">
                        <c:if test="${type.flag==1}">项目进行中</c:if>
                        <c:if test="${type.flag==0}">项目已结束</c:if>
                    </td>
                    <td align="center">
                    	 <input type="button" value="添加订单" onclick="addOrders('${type.id}')"/>
                    	 <input type="button" value="查看订单" onclick="queryOrders('${type.id}')"/>
                    </td>
                    
                    
                    
                 </tr>
            </c:forEach>
            
        </table>
        <ul id="jqpaginatorid" class="pagination"></ul>
    </body>
</html>




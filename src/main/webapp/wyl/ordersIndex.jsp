<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>订单管理</title>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jqPaginator.min.js"></script>
    <script type="text/javascript">
	    var totalpage = 0;
	    var currentpage = 0; 
	    var projectid = ""; //模糊项目编号查询参数
	    var ordersid = ""; //模糊订单编号查询参数
	    var flag = ""; //模糊订单状态查询参数
	    
    	$(function(){
    		totalpage = parseInt($('#totalpage').val());
            currentpage = parseInt($('#currentpage').val());
            
            projectid = $('#projectid').val().trim();
            ordersid = $('#ordersid').val().trim();
            flag = $('#flag').val();
        	
        	
            $.jqPaginator('#jqpaginatorid',{
                totalPages : totalpage,
                currentPage : currentpage,
                visiblePages : 10,
                first:'<li class = "first"><a href = "javascript:void(0);">首页</a></li>',
				prev:'<li class = "prev"><a href = "javascript:void(0);">上一页</a></li>',
				next:'<li class = "next"><a href = "javascript:void(0);">下一页</a></li>',
				last:'<li class = "next"><a href = "javascript:void(0);">尾一页</a></li>',
				page:'<li class = "page"><a href = "javascript:void(0);">{{page}}</a></li>',
                onPageChange: function (num, type) {
                	if(type == "change"){
						var likeprojectid = $('#likeprojectid').val();
						var likeordersid = $('#likeordersid').val();
						var flag =  $('#flag').val();
						location.href =
							"<%=request.getContextPath()%>/orders/queryOrders.do?projectid="+projectid+"&ordersid="+ordersid+"&flag="+flag+"&totalpage="+totalpage+"&nextpage="+num;
					}
                }
    		});
    	});
    	
    	/**
    	*修改订单
    	*/
    	function updateOrders(){
    		var ordersid = $("input[name='typeid']:checked").val();
            if(ordersid==undefined){//不知道是什么类型 未定义的
                alert("请选择要修改的数据");
                return;
            }
            checkUpdate(ordersid);
        }
    	
    	function checkUpdate(ordersid){
    		$.ajax({
    			type:"POST",
    			url:"<%=request.getContextPath()%>/orders/checkFlag.do",
    			data:{
    				ordersid:ordersid
    			},
    			dataType : "json",
    			contentType : "application/x-www-form-urlencoded; chartsrt=utf-8",
    			success : function(data){
    				if(data.message==1||data.message==0){
    					  location.href = "<%=request.getContextPath()%>/orders/toUpdateOrders.do?ordersid="+ordersid;	
    				}else {
    					alert("此订单无法修改");
    				}
    			},
    			error : function(data){
    				alert("checkUpdate");
    			}
    		});
    	}
		/**
		*删除订单
		*/
    	 function deleteOrders() {
             var ordersid = $("input[name='typeid']:checked").val();
             if(ordersid==undefined){
                 alert("请选择要删除的数据");
                 return;
             }
             checkDelete(ordersid);
         }
		
    	 function checkDelete(ordersid){
     		$.ajax({
     			type:"POST",
     			url:"<%=request.getContextPath()%>/orders/checkFlag.do",
     			data:{
     				ordersid:ordersid
     			},
     			dataType : "json",
     			contentType : "application/x-www-form-urlencoded; chartsrt=utf-8",
     			success : function(data){
     				if(data.message==3||data.message==1||data.message==0){
     					ajaxDeleteOrders(ordersid);
     				}else {
     					alert("此订单无法删除");
     				}
     			},
     			error : function(data){
     				alert("checkDelete");
     			}
     		});
     	}
    	 
    	 function ajaxDeleteOrders(ordersid){
    		 $.ajax({
    				type:"post",
    				url :"<%=request.getContextPath()%>/orders/deleteOrders.do",
    				data:{
    					ordersid : ordersid,
    				},
    				dataType :"json",
    				contentType : "application/x-www-form-urlencoded; charset=utf-8",
    				success:function(data){
    					if(data.message=="1"){
    						alert("删除订单成功");
    						location.href = "<%=request.getContextPath()%>/orders/queryOrders.do";
    					}else{
    						alert("删除订单失败")
    					}
    				},
    				error:function(data){
    					alert("删除订单失败")
    				}
    			})
    	 }
    	 
    	 
    	 function queryOrders(){
         	var projectid = $('#projectid').val().trim();
         	var ordersid = $('#ordersid').val().trim();
         	var flag = $('#flag').val();
         	location.href = "<%=request.getContextPath()%>/orders/queryOrders.do?projectid="+projectid+"&ordersid="+ordersid+"&flag="+flag;
         }																		
    	 
    </script>
</head>
<body style="padding:10px;">
	<!--所有 4 个内边距都是 10px -->
	<div style="background-color: #AFEEEE;height: 130px;">
			<a style="margin-right: 0px;white-space: normal;display: inline-block;" href="<%=request.getContextPath() %>/user/farmerLogin.jsp"><h2>农户主页</h2></a><h3 style="margin-right: 0px;white-space: normal;display: inline-block;">/订单管理</h3>
			<hr>
	</div><br><br>
	<!-- 页面缓存总页数和当前页数 都放在隐藏域里面 -->
		<input type="hidden" id="totalpage" name="totalpage" value="${totalpage}" />
        <input type="hidden" id="currentpage" name="currentpage" value="${currentpage}" />
        <input type="button" value="修改订单" onclick="updateOrders()"/>&nbsp;&nbsp;
        <input type="button" value="删除订单" onclick="deleteOrders()"/>&nbsp;&nbsp;<br/><br/>
	    <table >
				<td>
					项目编号：<input type="text" id = "projectid" name = "projectid" value = "${likeprojectid}"/>&nbsp;&nbsp;&nbsp;
					订单编号：<input type="text" id = "ordersid" name = "ordersid" value = "${likeordersid}"/>&nbsp;&nbsp;&nbsp;
					订单状态：<select id = "flag" name = "flag">
								<option value="5" <c:if test="${flag == '5'}"> selected = "selected"</c:if>>请选择状态</option>
								<option value="0" <c:if test="${flag == '0'}"> selected = "selected"</c:if>>待审核</option>
								<option value="1" <c:if test="${flag == '1'}"> selected = "selected"</c:if>>待接单</option>
								<option value="2" <c:if test="${flag == '2'}"> selected = "selected"</c:if>>进行中</option>
								<option value="3" <c:if test="${flag == '3'}"> selected = "selected"</c:if>>已完成</option>
								<option value="4" <c:if test="${flag == '4'}"> selected = "selected"</c:if>>已删除</option>
						    </select>
					<input type="button" value = "查询" onclick="queryOrders()" />
				</td>
		</table>
	    <br/><br/>
      
        <table frame="box" rules="all" width="100%">
        	<tr>
        		<th style="text-align:center;"height="70px">选择</th>
        		<th style="text-align:center;"height="70px">项目编号</th>
        		<th style="text-align:center;"height="70px">订单编号</th>
        		<th style="text-align:center;"height="70px">农户姓名</th>
        		<th style="text-align:center;"height="70px">下单时间</th>
        		<th style="text-align:center;"height="70px">用工时间（起）</th>
        		<th style="text-align:center;"height="70px">用工时间（止）</th>
        		<th style="text-align:center;"height="70px">用工数量</th>
        		<th style="text-align:center;"height="70px">采摘单价</th>
        		<th style="text-align:center;"height="70px">工头姓名</th>
        		<th style="text-align:center;"height="70px">订单状态</th>
        		<th style="text-align:center;"height="70px">修改时间</th>
        	</tr>
        	 <c:forEach items="${queryAllOrders}" var="type">
        	 	<tr>
        			<td align="center"height="40px"><input type="radio" name="typeid" value="${type.ordersid}"/></td>
                    <td align="center"height="40px">${type.projectid}</td>
                    <td align="center"height="40px">${type.ordersid}</td>
                    <td align="center"height="40px">${type.farmername}</td>
                    <td align="center"height="40px"><fmt:formatDate value="${type.createtime}" pattern="yyyy-MM-dd"/></td>
                    <td align="center"height="40px"><fmt:formatDate value="${type.starttime}" pattern="yyyy-MM-dd"/></td>
                    <td align="center"height="40px"><fmt:formatDate value="${type.endtime}" pattern="yyyy-MM-dd"/></td>
                    <td align="center"height="40px">${type.number}</td>
                    <td align="center"height="40px">${type.price}</td>
                    <td align="center"height="40px">${type.foremanname}</td>
                    <td align="center"height="40px">
                        <c:if test="${type.flag==0}">待审核</c:if>
                        <c:if test="${type.flag==1}">待接单</c:if>
                        <c:if test="${type.flag==2}">进行中</c:if>
                        <c:if test="${type.flag==3}">已完成</c:if>
                        <c:if test="${type.flag==4}">已删除</c:if>
                    </td>
                    <td align="center"height="40px"><fmt:formatDate value="${type.updatetime}" pattern="yyyy-MM-dd"/></td>
                   </tr>
        	</c:forEach>
        </table>
        <ul id="jqpaginatorid" class="pagination"></ul>
	</body>
</html>
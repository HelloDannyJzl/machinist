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
        var flag = ""; //模糊项目状态查询参数
        var pronumber = "";//模糊项目编号查询参数
        var proname = "";//模糊项目名称查询参数
        
        /**
         * 加载分页js代码
         */
        $(function (){
            totalpage = parseInt($('#totalpage').val());
            currentpage = parseInt($('#currentpage').val());
            pronumber = $('#pronumber').val();
            proname = $('#proname').val();
           	flag = $('#flag').val();
            
            
            $.jqPaginator('#jqpaginatorid',{
                totalPages:totalpage,
                currentPage:currentpage,
                visiblePages:10,
                first:'<li class = "first"><a href = "javascript:void(0);">首页</a></li>',
				prev:'<li class = "prev"><a href = "javascript:void(0);">上一页</a></li>',
				next:'<li class = "next"><a href = "javascript:void(0);">下一页</a></li>',
				last:'<li class = "next"><a href = "javascript:void(0);">尾一页</a></li>',
				page:'<li class = "page"><a href = "javascript:void(0);">{{page}}</a></li>',
               onPageChange: function (num, type) {//页面改变响应事件
            	   if(type == "change"){
            		   var likepronumber = $('#likepronumber').val();
						var likeproname = $('#likeproname').val();
						var flag =  $('#flag').val();
						location.href =
							"<%=request.getContextPath()%>/project/queryAllProject.do?flag="+flag+"&totalpage="+totalpage+"&nextpage="+num+"&proname="+proname+"&pronumber="+pronumber;
					}
                }
            });
        });

        /**
        *添加项目
        */
        function addProject() {
            location.href = "<%=request.getContextPath()%>/project/toAddProject.do";
        }

        /**
         *修改项目
         *id由点选确定
         */
        function updateProject() {
            var id = $("input[name='typeid']:checked").val();
            if(id==undefined){
                alert("请选择要修改的数据");
                return;
            }
            location.href = "<%=request.getContextPath()%>/project/toUpdateProject.do?id="+id;
        }

        /**
        *根据条件查询不同状态的所有项目
        */
        function queryProjectByFactor() {
        	var flag =  $('#flag').val();
        	var pronumber =  $('#pronumber').val().trim();
        	var proname =  $('#proname').val().trim();
            location.href = "<%=request.getContextPath()%>/project/queryAllProject.do?flag="+flag+"&proname="+proname+"&pronumber="+pronumber;
        }
        
        /**
        *根据项目编号查询此项目下的订单
        */
        function queryOrders(pronumber) {
        	  if(pronumber==undefined | pronumber==''){
                  alert("项目编号不能为空");
                  return;
              }
      	      location.href = "<%=request.getContextPath()%>/orders/queryOrders.do?projectid="+pronumber;
        }
        
        
      	/**
      	*开始/结束/删除项目
      	*/
        function startEndProject(id,flag) {
        	if(flag=='0'){
        		flag = '1';
        	}else if(flag=='1'){
        		flag = '2';
        	}else if(flag=='2'){
        		flag = '3';
        	}
        	if(flag==undefined | flag==''){
                alert("项目标识位不能为空");
                return;
            }
        	ajaxStartEndPro(id,flag);
        }
      	
      	
        function ajaxStartEndPro(id,flag){
		$.ajax({
			type : "POST",
			url : "<%=request.getContextPath()%>/project/ajaxStartEndPro.do",
			data : {
				id : id,
				flag : flag
			},
			dataType : "json",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success:function(data){
				if(data.message=="1"){
					location.href = "<%=request.getContextPath()%>/project/queryAllProject.do";
					alert("修改项目状态成功！");
				}else{
					alert("状态修改失败！");
				}
			},
			error:function(data){
				alert("修改失败："+data);
			}
		});
	}
        
        
        
        
        
        
    </script>
</head>
    <body style="padding:10px;">
    <div style="background-color: #AFEEEE;height: 130px;">
			<a style="margin-right: 0px;white-space: normal;display: inline-block;" href="<%=request.getContextPath() %>/user/manage.jsp"><h2>管理员主页面</h2></a><h3 style="margin-right: 0px;white-space: normal;display: inline-block;">/项目管理</h3>
			<hr>
	</div><br><br>
        <input type="hidden" id="totalpage" name="totalpage" value="${totalpage}" />
        <input type="hidden" id="currentpage" name="currentpage" value="${currentpage}" />
        <input style= "height:25px;width:90px; background-color: PaleTurquoise ;" type="button" value="添加项目" onclick="addProject()"/>&nbsp;&nbsp;&nbsp;
        <input style= "height:25px;width:90px; background-color: PaleTurquoise ;" type="button" value="修改项目" onclick="updateProject()"/>&nbsp;&nbsp;&nbsp;
        <br/><br/>
    		<legend>根据条件查询项目</legend>
         <table >
				<td>
					项目编号：<input type="text" id ="pronumber" name="pronumber" value = "${likepronumber}" style="width:110px;height:25px"/>
		                		 &nbsp;&nbsp;&nbsp;
					项目名称：<input type="text" id ="proname" name= "proname" value = "${likeproname}" style="width:110px;height:25px"/>
		                		 &nbsp;&nbsp;&nbsp;	
		                              订单状态：<select id = "flag" name = "flag">
								<option value="4" <c:if test="${flag == '4'}"> selected = "selected"</c:if>>所有项目</option>
								<option value="0" <c:if test="${flag == '0'}"> selected = "selected"</c:if>>未开启的项目</option>
								<option value="1" <c:if test="${flag == '1'}"> selected = "selected"</c:if>>已启动的项目</option>
								<option value="2" <c:if test="${flag == '2'}"> selected = "selected"</c:if>>已结束的项目</option>
								<option value="3" <c:if test="${flag == '3'}"> selected = "selected"</c:if>>已删除的项目</option>
							</select>
					<input style= "height:25px;width:90px; background-color: PaleTurquoise ;" type="button" value="查询" onclick="queryProjectByFactor()" />
				</td>
		</table>
		<br/>
        <table frame="box" rules="all" width="100%">
            <tr>
                <th style="text-align:center"height="70px">选择</th>
                <th style="text-align:center"height="70px">项目编号</th>
                <th style="text-align:center"height="70px">项目名称</th>
                <th style="text-align:center"height="70px">项目地址</th>
                <th style="text-align:center"height="70px">项目开始时间</th>
                <th style="text-align:center"height="70px">项目结束时间</th>
                <th style="text-align:center"height="70px">项目状态--->相应操作</th>
                <th style="text-align:center"height="70px">查询项目下订单</th>
                
            </tr>
            <c:forEach items="${datalist}" var="type">
                <tr>
                    <td align="center"height="40px"><input type="radio" name="typeid" value="${type.id}"/></td>
                    <td align="center">${type.pronumber}</td>
                    <td align="center">${type.proname}</td>
                    <td align="center">${type.proaddress}</td>
                    <td align="center">
                    	<c:if test="${type.flag==0}">项目未开始</c:if>
                    	<fmt:formatDate value="${type.starttime}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td align="center">
                    	<c:if test="${type.flag==1}">项目进行中</c:if>
                    	<fmt:formatDate value="${type.endtime}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td align="center">
                        <c:if test="${type.flag==0}">未启动 ---><input type="button" value="启动项目" onclick="startEndProject('${type.id}','${type.flag}')"/> </c:if>
                        <c:if test="${type.flag==1}">进行中 ---><input type="button" value="结束项目" onclick="startEndProject('${type.id}','${type.flag}')"/> </c:if>
                        <c:if test="${type.flag==2}">已结束 ---><input type="button" value="删除项目" onclick="startEndProject('${type.id}','${type.flag}')"/></c:if>
                        <c:if test="${type.flag==3}">已删除</c:if>
                    </td> 
                    <td align="center">
                    	 <input type="button" value="查看订单" onclick="queryOrders('${type.pronumber}')"/>
                    </td> </tr>
            </c:forEach>
        </table>
        <ul id="jqpaginatorid" class="pagination"></ul>
    </body>
</html>




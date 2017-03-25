<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../head.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript">
            $(function(){
                $(".menuson li").click(function(){
                    $(".menuson li.active").removeClass("active")
                    $(this).addClass("active");
                });

                $('.title').click(function(){
                    var $ul = $(this).next('ul');
                    $('dd').find('ul').slideUp();
                    if($ul.is(':visible')){
                        $(this).next('ul').slideUp();
                    }else{
                        $(this).next('ul').slideDown();
                    }
                });
            })
        </script>
    </head>
    <body style="background:#f0f9fd;">
        <div class="lefttop"><span></span>系统功能</div>
        <dl class="leftmenu">
            <dd><div class="title"><span><img src="<%=request.getContextPath()%>/css/images/backstage/leftico04.png" /></span>项目管理</div>
                <ul class="menuson">
                    <li><cite></cite><a href="<%=request.getContextPath()%>/project/queryAllProject.do" target="rightFrame">项目管理</a><i></i></li>
                    <li><cite></cite><a href="<%=request.getContextPath()%>/orders/queryOrders.do" target="rightFrame">订单管理</a><i></i></li>
                </ul>
            </dd>
            <dd><div class="title"><span><img src="<%=request.getContextPath()%>/css/images/backstage/leftico04.png" /></span>用户管理</div>
                <ul class="menuson">
                    <li><cite></cite><a href="<%=request.getContextPath()%>/manage/queryAllUser.do" target="rightFrame">用户管理</a><i></i></li>
                    <li><cite></cite><a href="<%=request.getContextPath()%>/role/queryRole.do" target="rightFrame">角色管理</a><i></i></li>
                    <li><cite></cite><a href="<%=request.getContextPath()%>/usermanagement/addmanage.jsp" target="rightFrame">添加管理员</a><i></i></li>
                    <li><cite></cite><a href="<%=request.getContextPath()%>/manage/queryAllLoginUser.do" target="rightFrame">审核新用户</a><i></i></li>
                </ul>
            </dd>
        </dl>
    </body>
</html>

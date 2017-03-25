<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<title>注册主页面</title>
    <style>
        #content{
            width: 1100px;
            height: 300px;
            margin: 300px  auto  0 auto;
        }
        ul,li{
            list-style: none;
        }
        li{
            float: left;
            margin: 20px
        }
            a{
            text-decoration: none;
            color: blue;
        }
        a:hover{
            color: teal;
            text-decoration: none;
        }
        .block{
            width: 300px;
            height: 200px;
            border-radius: 10px;
            text-align: center;
            background: PaleTurquoise;
        }
        .block h3{
            padding: 10px 0 0 0;
            font-size: 30px;
        }
        .block p{
            padding: 5px  20px ;
        }
    </style>
</head>
<body>
<h2>机械师团队制作</h2>
    <div id="content">
        <ul>
            <li>
              <div class="block">
	                <a href="<%=request.getContextPath()%>/user/farmer.jsp">
	                        <h3>农户注册</h3>
	                        <p>农户注册简绍农户注册简绍农户注册简绍农户注册简绍农户注册简绍农户注册简绍农户注册简绍农户注册简绍</p>
	                </a>
              </div>
                
            </li>
            <li>
                <div class="block">
	                <a href="<%=request.getContextPath()%>/user/foreman.jsp">
	                        <h3>工头注册</h3>
	                        <p>工人注册简绍工人注册简绍工人注册简绍工人注册简绍工人注册简绍工人注册简绍工人注册简绍工人注册简绍工</p>
	                </a>
                </div>
            </li>
            <li>
                 <div class="block">
	                <a href="<%=request.getContextPath()%>/user/worker.jsp">
	                        <h3>工人注册</h3>
	                        <p>工人注册简绍工人注册简绍工人注册简绍工人注册简绍工人注册简绍工人注册简绍工人注册简绍工人注册简绍工人注册简绍</p>
	                </a>
                 </div>
            </li>
        </ul>
    </div>
</body>

</html>

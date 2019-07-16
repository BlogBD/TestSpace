<%--
  Created by IntelliJ IDEA.
  User: zengwei
  Date: 2019/7/16
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

%>
<html>
<head>
    <base href="<%out.write(basePath);%>"/>
    <title>首页</title>
</head>
<body >
<div style="text-align: center">
    <h3>首页</h3><br>
    <a href="login.jsp">登陆</a><br>
</div>
</body>
</html>

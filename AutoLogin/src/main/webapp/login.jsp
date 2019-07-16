<%--
  Created by IntelliJ IDEA.
  User: zengwei
  Date: 2019/7/16
  Time: 15:05
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
    <title>登陆</title>
</head>
<body style="text-align: center">
<form method="post" action="LoginServlet">
    账号:<input type="text" name="username"><br>
    密码:<input type="password" name="password"><br>
    <input type="checkbox" name="auto_login">自动登录<br>
    <input type="submit" value="登陆">
</form>
</body>
</html>

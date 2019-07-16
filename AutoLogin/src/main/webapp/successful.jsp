<%--
  Created by IntelliJ IDEA.
  User: zengwei
  Date: 2019/7/16
  Time: 17:15
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
    <title>登陆成功</title>
</head>
<body style="text-align: center">
<h2>登陆成功</h2>

</body>
</html>

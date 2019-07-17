<%--
  Created by IntelliJ IDEA.
  User: zengwei
  Date: 2019/7/16
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java "  isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <base href="<%out.write(basePath);%>"/>
    <title>登陆成功</title>
</head>
<body style="text-align: center">
<h2>登陆成功</h2>
<c:if test="${not empty user}">
欢迎您，${user.username}!
</c:if>
<c:if test="${empty user}">
    您好，请登陆！
</c:if>
</body>
</html>

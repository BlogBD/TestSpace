<%--
  Created by IntelliJ IDEA.
  User: zengwei
  Date: 2019/7/15
  Time: 11:20
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
    <title>Title</title>
</head>
<body>
<h3>这是demo02.jsp页面</h3>
</body>
</html>

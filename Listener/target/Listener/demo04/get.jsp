<%--
  Created by IntelliJ IDEA.
  User: zengwei
  Date: 2019/7/14
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%out.write(basePath);%>"/>
    <title>测试监听器的session</title>
</head>
<body>
${bean02.name}
</body>
</html>

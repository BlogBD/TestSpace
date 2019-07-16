<%--
  Created by IntelliJ IDEA.
  User: zengwei
  Date: 2019/7/14
  Time: 22:44
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
<%
    session.setAttribute("name","aobame");
    session.setAttribute("name","zhangsan");
    session.removeAttribute("name");
%>
</body>
</html>

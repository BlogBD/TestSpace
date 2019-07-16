<%@ page import="com.listener.demo.dao.domain.Bean02" %><%--
  Created by IntelliJ IDEA.
  User: zengwei
  Date: 2019/7/14
  Time: 23:16
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
    Bean02 bean02=new Bean02();
    bean02.setName("zs");
    session.setAttribute("bean02",bean02);

%>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: zengwei
  Date: 2019/7/19
  Time: 17:51
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
    <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
</head>
<body>
<form action="/BaseServlet/ServletDemo01?method=addStu" method="post">
    用户：<input type="text" name="username"><br>
    <button>提交</button>
</form>
<a href="ServletDemo01?method=delStu">删除学生</a><br>
<button onclick="fn()">按钮 </button>
<script>
    function fn() {
        $.post("ServletDemo01",{"method":"checkUser","user":"tom"},function (data) {
            alert(data);
        },"json");
    }
</script>
</body>
</html>

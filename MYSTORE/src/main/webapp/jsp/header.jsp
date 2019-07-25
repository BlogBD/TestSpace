<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>关于我们</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
</head>

<body>
<div class="container-fluid">
    <div class="col-md-4">
        <img src="${pageContext.request.contextPath}/img/logo2.png"/>
    </div>
    <div class="col-md-5">
        <img src="${pageContext.request.contextPath}/img/header.png"/>
    </div>
    <div class="col-md-3" style="padding-top:20px">
        <ol class="list-inline">
            <c:if test="${empty loginUser}">
                <%--未登录显示--%>
                <li><a href="${pageContext.request.contextPath}/UserServlet?method=loginUI">登录</a></li>
                <li><a href="${pageContext.request.contextPath}/UserServlet?method=registUI">注册</a></li>
            </c:if>

            <c:if test="${not empty loginUser}">
                <%--登陆成功显示--%>
                <li>欢迎 ${loginUser.username}</li>
                <li>   <a href="${pageContext.request.contextPath}/UserServlet?method=logout">退出</a></li>
            </c:if>
            <li><a href="${pageContext.request.contextPath}/jsp/cart.jsp">购物车</a></li>
            <li><a href="${pageContext.request.contextPath}/jsp/order_list.jsp">我的订单</a></li>
        </ol>
    </div>
</div>
<!--
    描述：导航条
-->
<div class="container-fluid">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp">首页</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" id="myUL">
                    <%--<c:forEach items="${allCats}" var="c">
                    <li><a href="#">${c.cname}</a></li>
                    </c:forEach>--%>
                </ul>
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</div>
</body>
<script>
    $(function () {
        //向服务端发起一个ajax请求，以json格式返回
        var  url="${pageContext.request.contextPath}/CategoryServlet";
        var obj={"method":"findAllCats"};
        $.post(url,obj,function (data) {
            //获取到服务端返回的来的数据，存放在data中
            $.each(data,function (i,obj) {
                //'${pageContext.request.contextPath}/ProductServlet?method=findProductsWithCidAndPage&num=1&cid="+obj.cid+"'
                var li="<li><a href='${pageContext.request.contextPath}/ProductServlet?method=findProductsWithCidAndPage&num=1&cid="+obj.cid+"'>"+obj.cname+"</a><li>";
                $("#myUL").append(li);
            });
        },"json");
    });
</script>
</html>
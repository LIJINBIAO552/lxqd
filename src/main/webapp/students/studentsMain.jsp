<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>肇庆学院离校清单管理系统</title>

    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="apple-touch-icon" href="apple-touch-icon.png">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/light-box.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/templatemo-main.css">

    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">

    <script src="${pageContext.request.contextPath}/js/modernizr-2.8.3-respond-1.4.2.min.js"></script>
</head>
<body>
<div class="sequence">
    <div class="seq-preloader">
        <svg width="39" height="16" viewBox="0 0 39 16" xmlns="http://www.w3.org/2000/svg" class="seq-preload-indicator">
            <g fill="#F96D38">
                <path class="seq-preload-circle seq-preload-circle-1" d="M3.999 12.012c2.209 0 3.999-1.791 3.999-3.999s-1.79-3.999-3.999-3.999-3.999 1.791-3.999 3.999 1.79 3.999 3.999 3.999z"/>
                <path class="seq-preload-circle seq-preload-circle-2" d="M15.996 13.468c3.018 0 5.465-2.447 5.465-5.466 0-3.018-2.447-5.465-5.465-5.465-3.019 0-5.466 2.447-5.466 5.465 0 3.019 2.447 5.466 5.466 5.466z"/>
                <path class="seq-preload-circle seq-preload-circle-3" d="M31.322 15.334c4.049 0 7.332-3.282 7.332-7.332 0-4.049-3.282-7.332-7.332-7.332s-7.332 3.283-7.332 7.332c0 4.05 3.283 7.332 7.332 7.332z"/></g></svg>
    </div>
</div>
<nav>
    <div class="logo">
        <img src="${pageContext.request.contextPath}/img/studentsLogin/logo.png" alt="">
    </div>
    <div class="mini-logo">
        <img src="${pageContext.request.contextPath}/img/studentsLogin/mini_logo.png" alt="">
    </div>
    <ul>
        <li><a href="#1"><i class="fa fa-home"></i> <em>首  页&nbsp;&nbsp;</em></a></li>
        <li><a href="#2"><i class="fa fa-user"></i> <em>离校流程</em></a></li>
        <li><a href="#3"><i class="fa fa-pencil"></i> <em>离校清单</em></a></li>
        <li><a href="#4"><i class="fa fa-image"></i> <em>个人资料</em></a></li>
        <li><a id="logout" style="cursor:pointer;"><i class="fa fa-envelope"></i> <em>退  出&nbsp;&nbsp;</em></a></li>
    </ul>
</nav>

<div class="slides">
    <div class="slide" id="1">
        <div class="content first-content">
            <%--<div class="container-fluid">--%>
            <iframe style="width: 100%;height: 100%" src="${pageContext.request.contextPath}/students/studentsPage1.jsp"></iframe>
            <%--</div>--%>
        </div>
    </div>
    <div class="slide" id="2">
        <div class="content second-content">
            <%--<div class="container-fluid">--%>
            <iframe style="width: 100%;height: 100%" src="${pageContext.request.contextPath}/students/selectProcedure.do"></iframe>
            <%--</div>--%>
        </div>
    </div>
    <div class="slide" id="3">
        <div class="content third-content">
            <%--<div class="container-fluid">--%>
            <iframe style="width: 100%;height: 100%" src="${pageContext.request.contextPath}/students/StudentsMain.do"></iframe>
            <%--</div>--%>
        </div>
    </div>
    <div class="slide" id="4">
        <div class="content fourth-content">
            <%--<div class="container-fluid">--%>
            <iframe style="width: 100%;height: 100%" src="${pageContext.request.contextPath}/students/selectMyselfOfStudent.do"></iframe>
            <%--</div>--%>
        </div>
    </div>
</div>

<div class="footer">
    <div class="content">
        <p><a href="#"> Copyright &copy; 2019  </a> ZQU.LXQD <a href="#">by</a> <a href="#" target="_parent">Mr Li</a></p>
        <p><a href="https://www.zqu.edu.cn/" target="_blank" title="肇庆学院官网">地址：中国广东省肇庆市端州区肇庆大道</a>- 肇庆学院
        </p>
    </div>
</div>


<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.js"></script>
<script>window.jQuery || document.write('<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"><\/script>')</script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/datepicker.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
<script>
    $("#logout").click(function () {
        window.location.href = "${pageContext.request.contextPath}/students/userLogout.do";
    })
</script>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" class="no-js">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>离校清单管理系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo.css" />
    <!--必要样式-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/component.css" />
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <!--[if IE]>
    <script src="${pageContext.request.contextPath}/js/html5.js"></script>
    <![endif]-->

</head>
<body>
<%
    String UserId = "";
    String UserPassword = "";
    //获取当前站点的所有Cookie
    Cookie[] cookies = request.getCookies();
    if (cookies != null){
        for (int i = 0; i < cookies.length; i++) {
            //对cookies中的数据进行遍历，找到用户名、密码的数据
            if ("StudentId".equals(cookies[i].getName())) {
                UserId = cookies[i].getValue();
            } else if ("StudentPassword".equals(cookies[i].getName())) {
                UserPassword = cookies[i].getValue();
            }
        }
    }
%>
<div class="container demo-1">
    <div class="content">
        <div id="large-header" class="large-header">
            <canvas id="demo-canvas"></canvas>
            <div class="logo_box">
                <h2>&nbsp;肇庆学院</h2>
                <form name="f">
                    <div class="input_outer">
                        <span class="u_user"></span>
                        <input class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入学号" id="userid" name="userid">
                    </div>
                    <div class="input_outer">
                        <span class="us_uer"></span>
                        <input class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="请输入密码"
                               id="userPass" name="userPass">
                    </div>
                    <div class="mb2"><a id="userLoginButton" class="act-but submit" href="javascript:;" style="color: #FFFFFF" onclick="submitUserLogin()" >登录</a></div>
                </form>
            </div>
        </div>
    </div>
</div><!-- /container -->
<script src="${pageContext.request.contextPath}/js/TweenLite.min.js"></script>
<script src="${pageContext.request.contextPath}/js/EasePack.min.js"></script>
<script src="${pageContext.request.contextPath}/js/rAF.js"></script>
<script src="${pageContext.request.contextPath}/js/demo-1.js"></script>
</body>
<script>
    <!--提交登录-->
    function submitUserLogin() {

        var uid = $("#userid").val();
        var upass = $("#userPass").val();
        if (uid == "") {
            alert("用户名不能为空！")
        } else if (upass == "") {
            alert("密码不能为空！")
        } else {
            $.ajax({
                url: "${pageContext.request.contextPath}/students/userLogin.do",
                type: "POST",
                data: {
                    userid: uid,
                    userPass: upass,
                },
                success: return_data1,
                error: alter_error1
            });

            function return_data1(data) {
                if (data == "success_login") {
                    window.location.href = "${pageContext.request.contextPath}/students/studentsMain.jsp";
                } else {
                    alert(data);
                    window.location.replace("${pageContext.request.contextPath}/students/login.jsp");
                }
            }
            function alter_error1() {
                alert("数据获取失败")
            }
        }
    }
</script>
</html>
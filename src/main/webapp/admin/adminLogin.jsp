<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"--%>
<%--"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>系统登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/proving.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminLogin.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/proving.js"></script>
</head>
<body>
<%
    String AdminId = "";
    String AdminPassword = "";
    //获取当前站点的所有Cookie
    Cookie[] cookies = request.getCookies();
    if (cookies != null){
        for (int i = 0; i < cookies.length; i++) {
            //对cookies中的数据进行遍历，找到用户名、密码的数据
            if ("AdminId".equals(cookies[i].getName())) {
                AdminId = cookies[i].getValue();
            } else if ("AdminPassword".equals(cookies[i].getName())) {
                AdminPassword = cookies[i].getValue();
            }
        }
    }
%>
<div class="login-box">
    <h1>离校清单系统后台登录</h1>
    <form>
        <div class="name">
            <label>管理员账号：</label>
            <input type="text" tabindex="1" autocomplete="off" name="adminid" id="adminid" value="<%=AdminId%>" />
        </div>
        <div class="password">
            <label>密  码：</label>
            <input type="password" maxlength="16" tabindex="2" name="adminPass" id="adminPass" value="<%=AdminPassword%>"/>
        </div>
        <div class="code" style="padding-top: 25px">
            <label>滑动验证：</label>
            <div id="slideBar" style="float: right;padding-right: 15px;"></div>
        </div>
        <div class="remember" style="float: right;padding-right: 35%">
            <input type="checkbox" tabindex="4" value="None" id="checkbox" name="checkRemem"/>
            <label>记住密码</label>
        </div>
        <div class="login">
            <button type="button" tabindex="5" onclick="submitLogin()" id="loginButton">登录</button>
        </div>
    </form>
</div>

<div class="screenbg">
    <ul>
        <li><a href="javascript:;"><img src="${pageContext.request.contextPath}/img/adminLogin.jpg"></a></li>
    </ul>
</div>

</body>
<script type="text/javascript">
    $(function(){
        $(".screenbg ul li").each(function(){
            $(this).css("opacity","0");
        });
        $(".screenbg ul li:first").css("opacity","1");
        var index = 0;
        var t;
        var li = $(".screenbg ul li");
        var number = li.length;
        function change(index){
            li.css("visibility","visible");
            li.eq(index).siblings().animate({opacity:0},3000);
            li.eq(index).animate({opacity:1},3000);
        }
        function show(){
            index = index + 1;
            if(index<=number-1){
                change(index);
            }else{
                index = 0;
                change(index);
            }
        }
        t = setInterval(show,8000);
        //根据窗口宽度生成图片宽度
        var width = $(window).width();
        $(".screenbg ul img").css("width",width+"px");
    });
    window.onload = function(){
        var dataList = ["0","1"];
        var options = {
            dataList: dataList,
            success:function(){
                console.log("show");
            },
            fail: function(){
                console.log("fail");
            }
        };
        SliderBar("slideBar", options);
    }
</script>
<script>
    function submitLogin() {
        var id = $("#adminid").val();
        var pass = $("#adminPass").val();
        var remember = $("#checkbox").val()
        if (id == ""){
            alert("用户名不能为空！")
        } else if (pass == ""){
            alert("密码不能为空！")
        } else {
            $.ajax({
                url: "${pageContext.request.contextPath}/admin/adminLogin.do",
                type:"POST",
                data: {
                    adminid: id,
                    adminPass:pass,
                    checkRemem:remember,
                },
                success: return_data,
                error: alter_error
            });

            function return_data(data) {
                if (data.indexOf("admin") >= 0) {
                    console.log("data");
                    window.location.href="${pageContext.request.contextPath}/"+data;
                }else {
                    alert(data);
                    window.location.replace("${pageContext.request.contextPath}/admin/adminLogin.jsp");
                }
            }
            function alter_error() {
                alert("数据获取失败")
            }
        }
    }
</script>
</html>

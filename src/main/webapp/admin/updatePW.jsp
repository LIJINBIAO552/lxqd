<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="UTF-8">
    <title>修改密码</title>

    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.css" rel="stylesheet">
    <%--<link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">--%>
    <link href="${pageContext.request.contextPath}/css/updatePW.css" rel="stylesheet">

    <script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

</head>
<body>
<div class="change-code" id="change-code">
    <div class="change-code-major">
        <div><p>旧密码：<a id="opw" style="color: red"></a></p><input type="password" placeholder="请输入原密码" id="oldadminpass"
                                                                  name="oldadminpass"><span
                id="result1"></span></div>
        <div><p>新密码：<a id="npw" style="color: red"></a></p><input type="password" placeholder="请输入6到16位的数字或字母,字母区分大小写"
                                                                  id="adminpass"
                                                                  name="adminpass"><span id="result3"></span>
        </div>
        <div><p>确认新密码：<a id="qnpw" style="color: red"></a></p><input type="password" placeholder="请再次确认密码"
                                                                     id="adminpass1" name="adminpass1"><span
                id="result2"></span></div>
    </div>
    <div class="anniu">
        <button type="button" onclick="updateadminpw()" id="anniu">确认修改</button>
    </div>
</div>
</body>
<script>
    // 修改密码
    function updatePW(id) {
        return document.getElementById(id);
    }

    updatePW("oldadminpass").onblur = function () {
        str = new String(this.value);
        var reg = /^[0-9a-zA-Z]+$/;
        if (str.length == 0) {
            $('#opw').html("输入不能为空！");
            updatePW("result1").className = "wrong";
        }
        else if (str.length <= 5) {
            $('#opw').html("输入长度小于六位！");
            updatePW("result1").className = "wrong";
        } else if (str.length >= 16) {
            $('#opw').html("输入长度大于十六位！");
            updatePW("result1").className = "wrong";
        }
        else if (!reg.test(str)) {
            $('#opw').html("含有非法字符");
            updatePW("result1").className = "wrong";
        }
        else {
            $('#opw').html('');
            updatePW("result1").className = "right";
            updatePW("anniu").disabled = false;
        }
    }
    updatePW("adminpass").onblur = function () {
        str = new String(this.value);
        var reg = /^[0-9a-zA-Z]+$/;
        if (str.length == 0) {
            $('#npw').html("输入不能为空！");
            updatePW("result3").className = "wrong";
        } else if (str == updatePW("oldadminpass").value) {
            $('#npw').html("与旧密码一样！");
            updatePW("result3").className = "wrong";
        } else if (str.length <= 5) {
            $('#npw').html("输入长度小于六位！");
            updatePW("result3").className = "wrong";
        } else if (str.length >= 16) {
            $('#opw').html("输入长度大于十六位！");
            updatePW("result3").className = "wrong";
        }
        else if (!reg.test(str)) {
            $('#npw').html("含有非法字符");
            updatePW("result3").className = "wrong";
        }
        else {
            $('#npw').html('');
            updatePW("result3").className = "right";
            updatePW("anniu").disabled = false;
        }
    }
    updatePW("adminpass1").onblur = function () {
        //alert(qr);
        if (this.value == "") {
            $('#qnpw').html("输入不能为空！");
            updatePW("result2").className = "wrong";
        }
        else if (this.value == updatePW("adminpass").value) {
            $('#qnpw').html('');
            updatePW("result2").className = "right";
            updatePW("anniu").disabled = false;
        }
        else {

            $('#qnpw').html("两次输入不一致，请重新输入！");
            updatePW("result2").className = "wrong";
        }
    }
    updatePW("anniu").onmouseover = function () {
        if (updatePW("result3").className == "right" && updatePW("result2").className == "right" && updatePW("result1").className == "right") {
            updatePW("anniu").disabled = false;
            updatePW("anniu").className = "xiaoshou";
        }
        else {
            updatePW("anniu").disabled = true;
        }
    }
</script>
<script>
    function updateadminpw() {
        var oldap = $("#oldadminpass").val();
        var ap = $("#adminpass").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/admin/updateMyselfOfPW.do",
            type: "POST",
            data: {
                oldadminpass: oldap,
                adminpass: ap
            },
            success: return_result,
            error: alter_error0
        });

        function return_result(data) {
            if (data === "1") {
                alert("更新密码成功，请重新登录！");
                window.parent.location.replace("${pageContext.request.contextPath}/admin/adminLogout.do");
            } else if (data === "0") {
                console.log("+++++++++++++++" + data);
                alert("旧密码输入错误，请重新输入!");
                $("#oldadminpass").val('');
                $("#adminpass").val('');
                $("#adminpass1").val('');
            }
        }

        function alter_error0() {
            alert("密码更新失败，请再试一次。。。")
        }
    }

</script>
</html>
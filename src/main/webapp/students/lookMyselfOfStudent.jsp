<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%--//时间格式化引用--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>查看个人资料</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lookMyselfOfStudent.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
</head>
<body>
<form action="${pageContext.request.contextPath}/students/updateMyselfOfStudent.do" id="UpdateStuInfo" style="height: 100%">
    <div class="header">
        <div class="fang"></div>
        用户信息
    </div>
    <div class="body">
        <div class="body-left">
            <div>姓名：</div>
            <input type="text" value="${MySelfOfstudents.studname}" id="studname" name="studname">
            <div>性别：</div>
            <input type="text" value="${MySelfOfstudents.studsex}" id="studsex" name="studsex">
            <div>年龄：</div>
            <input type="text" value="${MySelfOfstudents.studage}" id="studage" name="studage">
            <div>学院：</div>
            <input type="text" value="${MySelfOfstudents.department.departname}" id="departname" name="departname">
            <div>学号：</div>
            <input type="text" value="${MySelfOfstudents.studid}" id="studid" name="studid">
            <div>专业：</div>
            <input type="text" value="${MySelfOfstudents.major}" id="major" name="major">
            <div>班级：</div>
            <input type="text" value="${MySelfOfstudents.sclass}" id="sclass" name="sclass">
            <div>年级：</div>
            <input type="text" value="${MySelfOfstudents.grade}" id="grade" name="grade">
            <div>本/专：</div>
            <input type="text" value="${MySelfOfstudents.education}" id="education" name="education">
            <div>备注：</div>
            <textarea name="remark" id="remark" cols="52" rows="6" style="width: 90%; height: 11.5%;">${MySelfOfstudents.remark}</textarea>
        </div>
        <div class="body-right">
            <div style="height: 180px;width: 300px;text-align: center">
                <img id="studentsPhoto" src="${pageContext.request.contextPath}${MySelfOfstudents.studphoto}"
                     style="height: 210px;width: 150px;margin-right: 20px;margin-top: 5px;">
                <label>
                    <input type="file" id="userphoto" name="userphoto" style="height: 22px;width: 150px;padding-left: 0px;margin-right: 20px;font-size: 12px;
                           color:#0bd;border: 1px solid #0bd;background-color: white;position: relative;top:0px;cursor: pointer;">
                </label>
            </div>
            <div style="padding-top: 50px">
                <div>身份证号：</div>
                <input type="text" value="${MySelfOfstudents.studiden}" id="studiden"
                       name="studiden">
                <div>住宿：</div>
                <input type="text" value="${MySelfOfstudents.dormitory}" id="dormitory" name="dormitory">
                <div>个人联系电话（长号）：</div>
                <input type="text" value="${MySelfOfstudents.studphone1}" id="studphone1" name="studphone1">
                <div>短号：</div>
                <input type="text" value="${MySelfOfstudents.studphone2}" id="studphone2" name="studphone2">
                <div>籍贯：</div>
                <input type="text" value="${MySelfOfstudents.birthplace}" id="birthplace" name="birthplace">
                <div>家庭详细地址：</div>
                <input type="text" value="${MySelfOfstudents.adress}" id="adress" name="adress">
                <div>家长联系电话：</div>
                <input type="text" value="${MySelfOfstudents.familyphone}" id="familyphone" name="familyphone">
            </div>
        </div>
        <p class="cl"></p>
    </div>
    <div class="tail">
        <button type="submit" value="Submit" class="save" form="UpdateStuInfo">保存</button>
        <button type="button" class="close">取消</button>
    </div>
</form>
</body>
<script>
    $(function () {
        $("#studid").attr("readonly", "readonly");
        $("#departname").attr("readonly", "readonly");
        $("#studname").attr("readonly", "readonly");
        $("#studsex").attr("readonly", "readonly");
        $("#studage").attr("readonly", "readonly");
        $("#studiden").attr("readonly", "readonly");
        $("#major").attr("readonly", "readonly");
        $("#sclass").attr("readonly", "readonly");
        $("#grade").attr("readonly", "readonly");
        $("#education").attr("readonly", "readonly");
        $("#dormitory").attr("readonly", "readonly");
        if ($("#studentsPhoto").attr("src").length === 0) {
            $("#studentsPhoto").attr("src","${pageContext.request.contextPath}/img/头像.png");
        }
    });
</script>
<script>
    $(function () {
        $("#userphoto").change(function () {
            if($(this).val() != ""){
                var photoObj = document.getElementById("userphoto").files[0];
                var formfile = new FormData();
                formfile.append("userphoto",photoObj);
                if (typeof (photoObj) === "undefined" || photoObj.size <= 0) {
                    alert("图片格式不正确！")
                    return;
                } else {
                    var data = formfile;
                    $.ajax({
                        url: "${pageContext.request.contextPath}/students/UploadStudentsPhoto.do",
                        type: "POST",
                        data: data,
                        cache: false,//上传文件无需缓存
                        processData: false,//用于对data参数进行序列化处理 这里必须false
                        contentType: false, //必须
                        success: return_data,
                        error: alter_error
                    });
                    function return_data(data) {
                        if (data == "upload_ok") {
                            alert("头像更新成功");
                            window.location.href = "${pageContext.request.contextPath}/students/selectMyselfOfStudent.do";
                        } else if (data == "upload_no") {
                            alert("头像更新失败");
                            window.location.href = "${pageContext.request.contextPath}/students/selectMyselfOfStudent.do";
                        }else if (data === "no_studid") {
                            alert("照片命名错误！")
                            window.location.href = "${pageContext.request.contextPath}/students/selectMyselfOfStudent.do";
                        }
                    }
                    function alter_error() {
                        alert("提交失败");
                        window.location.href = "${pageContext.request.contextPath}/students/selectMyselfOfStudent.do";
                    }
                }
            }
        })
    })

</script>
</html>
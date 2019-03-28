<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%--//时间格式化引用--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="UTF-8">
	<title>查看个人资料</title>
	<style type="text/css">
		html, body, div, ul, li, h1, h2, h3, h4, h5, h6, p, dl, dt, dd, ol, form, input, textarea, th, td, select {
			margin: 0;
			padding: 0;
		}

		body {
			background-color: #f4f4f4;
		}

		.wall {
			height: 40px;
		}

		form {
			width: 60%;
			background-color: white;
			margin: auto;
		}

		form .header {
			width: 90%;
			margin: auto;
			border-bottom: 1px solid #e1e1e1;
			padding-top: 14px;
			padding-bottom: 14px;
		}

		.fang {
			height: 9px;
			width: 9px;
			display: inline-block;
			background-color: #0bd;
			margin-right: 16px;
		}

		form .body {
			padding-top: 0px;
			width: 86%;
			margin: auto;
			font-size: 12px;
		}

		form .body .body-left {
			float: left;
			width: 50%;
			border-right: 1px solid #e1e1e1;
		}

		form .body .body-right {
			float: left;
			width: 45.5%;
			padding-left: 4.3%;
		}

		.cl {
			clear: both;
		}

		form .body .body-left div, .body .body-right div {
			height: 28px;
			line-height: 28px;
			padding-top: 2px;
		}

		form .body .body-left input, .body .body-right input {
			height: 30px;
			border: 1px solid #e1e1e1;
			padding-left: 9px;
			width: 90%;
		}

		select {
			height: 30px;
			width: 110px;
		}

		form .tail {
			text-align: center;
			padding-top: 40px;
			padding-bottom: 40px;
		}

		form .tail .save {
			height: 30px;
			width: 94px;
			padding-left: 22px;
			padding-right: 22px;
			font-size: 12px;
			color: #ddd;
			border: 1px solid #ddd;
			background-color: white;
			cursor: pointer;
			margin-right: 20px;
		}

		form .tail .close {
			height: 30px;
			width: 94px;
			padding-left: 22px;
			padding-right: 22px;
			font-size: 12px;
			color: #666;
			border: 1px solid #666;
			background-color: white;
			cursor: pointer;
		}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
</head>
<body>
<%--<div class="wall"></div>--%>
<form action="${pageContext.request.contextPath}/students/updateMyselfOfStudent.do" id="UpdateStuInfo">
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
			<textarea name="remark" id="remark" cols="50" rows="10">${MySelfOfstudents.remark}</textarea>
		</div>
		<div class="body-right">
			<div style="height: 180px;width: 300px;">
				<img id="studentsPhoto" src="${MySelfOfstudents.studphoto}"
					 style="height: 150px;width: 150px;margin-right: 20px;margin-top: 5px;">
				<input type="file" id="userphoto" name="userphoto" placeholder="修改头像"
					   style="height: 22px;width: 150px;padding-left: 0px;padding-right: 0px;font-size: 12px;color:#0bd;border: 1px solid #0bd;background-color: white;position: relative;top:0px;cursor: pointer;">
				<button type="button" id="doupload"  value="提交">提交</button>
			</div>
			<div>身份证号：</div>
			<input type="text" value="${pageContext.request.contextPath}${MySelfOfstudents.studiden}" id="studiden"
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
		<p class="cl"></p>
	</div>
	<div class="tail">
		<button type="submit" value="Submit" class="save" form="UpdateStuInfo">保存</button>
		<button type="button" class="close">取消</button>
	</div>
</form>
<div class="wall"></div>
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
        $("#doupload").click(function () {
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
                    }
                }
                function alter_error() {
                    alert("提交失败");
                    window.location.href = "${pageContext.request.contextPath}/students/selectMyselfOfStudent.do";
                }
            }
        })
    })

</script>
</html>
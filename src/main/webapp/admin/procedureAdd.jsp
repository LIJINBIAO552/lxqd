<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/layui.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modules/laydate/default/laydate.css">
    <style type="text/css">
        .proceditAdd{
            width: 800px;
            height: 400px;
            font-size: 18px;
            padding-left: 90px;
        }
        .proceditAdd div p{
            font-size: 18px;
            padding-top: 10px;
        }
        .proceditAdd h3{
            text-align: center;
            padding-top: 30px;
        }

    </style>
</head>
<body>
<form class="proceditAdd" action="${pageContext.request.contextPath}/admin/addProcedure.do">
    <div>
        <h3>添加离校通知</h3>
        <p>
            <span>选择时间：</span>
            <input type="text" class="layui-input" style="width: 200px;" id="eventtime" name="eventtime">
        </p>
        <p> 添加内容：</p>
        <p>
            <textarea placeholder="请输入内容" style="width: 600px;height: 100px" name="event"></textarea>
        </p>
        <p style="text-align: center">
            <button class="layui-btn layui-btn-primary" type="submit" id="procedureAdd" value="确认添加">确认添加</button>
        </p>
    </div>
</form>
<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/lay/modules/laydate.js"></script>
</body>
<script>
    layui.use(['laydate'], function () {
        laydate = layui.laydate;
        //日期
        laydate.render({
            elem: '#eventtime'
        });
    });
</script>
<script>
    $("#procedureAdd").submit(function () {
        alert("提交成功！")
    })
</script>

</html>
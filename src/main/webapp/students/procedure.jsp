<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%--//时间格式化引用--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>离校流程</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/procedureStyle.css">
    <style>
        .page { width:100%;background:#F0F0F0 repeat-x; }
    </style>

</head>
<body>
<div class="page">
    <div class="box">
        <ul class="event_year">
        </ul>
        <ul class="event_list">
        </ul>

        <div class="clearfix"></div>

    </div>

</div>

<script src="${pageContext.request.contextPath}/js/jquery.min_v1.0.js" type="text/javascript"></script>

<script>
    var prolist = jQuery.parseJSON('${procedureList}');
    $(function () {
        var yearflag = true;
        var yeartemp = "";
        $.each(prolist,function (index,procedure) {
            var ptime = (procedure.eventtime).split("-");
            var pyear = ptime[0];
            var pmonth = ptime[1];
            var pday = ptime[2];
            if (yearflag) {
                yeartemp = pyear;
                $(".event_year").append("<li class=\"current\"><label for=\"" + pyear + "\">"+ pyear +"</label></li>");
                $(".event_list").append("<div><h3 id=\""+pyear + "\">"+pyear+"</h3></div>");
                yearflag = false;
            }
            if (yeartemp === pyear) {
                //添加同一年的事件
                $(".event_list>div").eq(0).append("<li><span>"+pmonth+"月"+pday+"日"+"</span><p><span>"+procedure.event+"</span></p></li>");
            }else{
                yeartemp = pyear;
                //获取新的一年，并把判断年份更新
                $('.event_year>li').removeClass('current');
                $(".event_year").prepend("<li class=\"current\"><label for=\"" + pyear + "\">"+ pyear +"</label></li>");
                $(".event_list").prepend("<div><h3 id=\""+pyear + "\">"+pyear+"</h3></div>");
                $(".event_list>div").eq(0).append("<li><span>"+pmonth+"月"+pday+"日"+"</span><p><span>"+procedure.event+"</span></p></li>");
            }
        })
    })

</script>



<script type="text/javascript">
    $(function(){
        $('label').click(function(){
            $('.event_year>li').removeClass('current');
            $(this).parent('li').addClass('current');
            var year = $(this).attr('for');
            $('#'+year).parent().prevAll('div').slideUp(800);
            $('#'+year).parent().slideDown(800).nextAll('div').slideDown(800);
        });
    });
</script>

</body>

</html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/adminStyle.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/layui.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modules/laydate/default/laydate.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/procedureEdit.css">
    <style>
        .page { width:100%;background:#F0F0F0 repeat-x; }
        .formStyle{
            padding-bottom:10px ;
        }
        .formStyle button{
            float:right;
            margin-right:30%;
            margin-top:10px;
        }
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
<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/lay/modules/table.js"></script>
<script src="${pageContext.request.contextPath}/js/lay/modules/laypage.js"></script>
<script src="${pageContext.request.contextPath}/js/lay/modules/layer.js"></script>
<script src="${pageContext.request.contextPath}/js/lay/modules/laydate.js"></script>
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
            var pid = procedure.id;
            if (yearflag) {
                yeartemp = pyear;
                $(".event_year").append("<li class=\"current\"><label for=\"" + pyear + "\">"+ pyear +"</label></li>");
                $(".event_list").append("<div><h3 id=\""+pyear + "\">"+pyear+"</h3></div>");
                yearflag = false;
            }
            if (yeartemp === pyear) {
                //添加同一年的事件
                $(".event_list>div").eq(0).append("<li><form class=\'formStyle\' action=\'${pageContext.request.contextPath}/admin/updateProcedure.do\'>" +
                "<input style=\'display: none\' id=\""+pid+"\""+" name=\"id\""+"value=\'"+pid+"\'>"+
                    "<input style=\'display: none\' name=\"eventtime\""+"value=\'"+procedure.eventtime+"\'>"+
                    "<span>"+pmonth+"月"+pday+"日"+"</span>"+
                    "<p><textarea cols =\"50\" rows=\"auto\" name=\"event\">"+procedure.event+"</textarea>" +
                    "</p><button class=\"procedit\" type=\"submit\" value=\"Submit\">确认修改</button>" +
                    "</form></li>");
            }else{
                yeartemp = pyear;
                //获取新的一年，并把判断年份更新
                $('.event_year>li').removeClass('current');
                $(".event_year").prepend("<li class=\"current\"><label for=\"" + pyear + "\">"+ pyear +"</label></li>");
                $(".event_list").prepend("<div><h3 id=\""+pyear + "\">"+pyear+"</h3></div>");
                $(".event_list>div").eq(0).append("<li><form class=\'formStyle\' action=\'${pageContext.request.contextPath}/admin/updateProcedure.do\'>" +
                    "<input style=\'display: none\' id=\""+pid+"\""+" name=\"id\""+"value=\'"+pid+"\'>"+
                    "<input style=\'display: none\' name=\"eventtime\""+"value=\'"+procedure.eventtime+"\'>"+
                    "<span>"+pmonth+"月"+pday+"日"+"</span>"+
                    "<p><textarea cols =\"50\" rows=\"auto\" name=\"event\">"+procedure.event+"</textarea>" +
                    "</p><button class=\"procedit\" type=\"submit\" value=\"Submit\">确认修改</button>" +
                    "</form></li>");
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
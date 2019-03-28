<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%--//时间格式化引用--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>修改离校清单模板</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lookMyselfLxqd.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
</head>
<body>
<%--<span>${students1}</span>--%>
<div id="pdfContainer" class="container">
    <form action="${pageContext.request.contextPath}/admin/lookUpdateLxqd.do" method="post" id="updateLxqdForm">
        <input style="display: none" type="text" id="lxqdId" name="lxqdId" value="${students1.lxqd.lxqdId}">
        <h1><input type="text" style="height:40px;width: 800px; font-size: 28px;text-align: center;font-weight: bold; " id="title"
                   name="title" value="${students1.lxqd.title}"></h1>
        <div class="header">
            <div class="header-left">
                <p class="word">二级学院（盖章）:</p>
                <p class="underline" style="width: 280px;height: 19px"></p>
            </div>
            <div class="header-right">
            <span style="display: none">
            </span>
                <p class="underline" style="width: 40px;height: 19px">
                </p>
                <p class="word">年</p>
                <p class="underline" style="width: 30px;height: 19px">
                </p>
                <p class="word">月</p>
                <p class="underline" style="width: 30px;height: 19px">
                </p>
                <p class="word">日</p></div>
        </div>
        <div class="body">
            <div class="body-first">
                <div class="body-first-number" style="width: 60px;">学号</div>
                <div class="nothing"><p class="underline"
                                        style="width: 103px;height:25px;padding-top: 6px"></p></div>
                <div class="body-first-number" style="width: 60px">姓名</div>
                <div class="nothing"><p class="underline"
                                        style="width: 103px;height:25px;padding-top: 6px"></p></div>
                <div class="body-first-number" style="width: 60px">班别</div>
                <div class="body-first-number" style="border: 0;width:200px;padding-left: 10px">
                    <p class="underline"
                       style="width: 199px;height:31px;border-right: 1px solid black;"></p>
                </div>
                <div class="body-first-number" style="width: 84px">所在宿舍</div>
                <div class="body-first-number" style="border: 0;width:157px;padding-left: 10px">
                    <p class="underline" style="width: 30px;height: 23px"></p>
                    <p class="word">区</p>
                    <p class="underline" style="width: 30px;height: 23px"></p>
                    <p class="word">栋</p>
                    <p class="underline" style="width: 30px;height: 23px">
                    <p class="word">房</p>
                </div>
                <%--<div class="cachet6" style="background: none"></div>--%>
            </div>
            <div class="body-third-left">
                <p class="word-bold">学工处（资助中心、宿管中心）：</p>
                <p class="word-bold"><textarea type="text" rows="2" cols="32"
                                               style="line-height: 24px;font-weight: bold;font-size: 14px;padding: 0 8px;"
                                               id="studaffadividesc" name="studaffadividesc">${students1.lxqd.studaffadividesc}</textarea></p>
                <p class="word-normal" style="width:264px;padding-top: 0px">&nbsp&nbsp学生资助管理中心</p>
                <p class="word-normal word px">（经办人签名）：</p>
                <p class="underline" style="width: 64px;position: absolute">
                <p class="font" style="position: relative;bottom: 20px;"></p>
                </p>
                <p class="word-normal word" style="width:264px;padding-top: 1px">&nbsp&nbsp学生宿舍管理中心</p>
                <p class="word-normal word px">（经办人签名）：</p>
                <p class="underline" style="width: 64px;position: absolute;">
                <p class="font" style="position: relative;bottom: 20px"></p>
                </p>
                <div class="date" style="padding-left: 55px;padding-top: 0px">
                    <p class="word-normal-np">&nbsp&nbsp&nbsp（盖章）
                    <%--<div class="cachet7" style="background: none"></div>--%>
                    </p>
                    <span style="display: none">
                </span>
                    <p class="underline" style="width: 40px;height: 19px">
                    </p>
                    <p class="word-normal-np">年</p>
                    <p class="underline" style="width: 24px;height: 19px">
                    </p>
                    <p class="word-normal-np">月</p>
                    <p class="underline" style="width: 24px;height: 19px">
                    </p>
                    <p class="word-normal-np">日</p>
                </div>
            </div>
            <div class="body-third-right">
                <p class="word-bold">后勤管理处：</p>
                <p class="word-bold"><textarea type="text" rows="1" cols="32"
                                               style="line-height: 26px;font-weight: bold;font-size: 14px;padding: 0 8px;"
                                               id="logimanadesc" name="logimanadesc">${students1.lxqd.logimanadesc}</textarea></p>
                <p class="word-normal" style="width:264px;padding-top: 24px">&nbsp&nbsp水电管理服务中心</p>
                <p class="word-normal word px">（经办人签名）：</p>
                <p class="underline" style="width: 64px;position: absolute">
                <p class="font" style="position: relative;bottom: 20px"></p></p>
                <p class="word-normal word" style="width:264px;padding-top: 1px">&nbsp&nbsp饮用水服务中心</p>
                <p class="word-normal word px">（经办人签名）：</p>
                <p class="underline" style="width: 64px;position: absolute">
                <p class="font" style="position: relative;bottom: 20px"></p></p>
                <div class="date" style="padding-left: 55px;padding-top: 0px">
                    <p class="word-normal-np">&nbsp&nbsp&nbsp（盖章）
                    <%--<div class="cachet7" style="background: none"></div>--%>
                    </p>
                    <span style="display: none">
                </span>
                    <p class="underline" style="width: 40px;height: 19px">
                    </p>
                    <p class="word-normal-np">年</p>
                    <p class="underline" style="width: 24px;height: 19px">
                    </p>
                    <p class="word-normal-np">月</p>
                    <p class="underline" style="width: 24px;height: 19px">
                    </p>
                    <p class="word-normal-np">日</p>
                </div>
            </div>
            <div class="body-fourth-left">
                <p class="word-bold">图书馆：</p>
                <p class="word-bold"><textarea type="text" rows="1" cols="32"
                                               style="line-height: 26px;font-weight: bold;font-size: 14px;padding: 0 8px;"
                                               id="librarydesc" name="librarydesc">${students1.lxqd.librarydesc}</textarea></p>
                <div class="body-fourth-right-wall"></div>
                <p class="word-normal word px" style="padding-left: 100px;">经办人签名：</p>
                <p class="underline" style="width: 64px;position: absolute">
                <p class="font" style="position: relative;bottom: 10px"></p></p>
                <div class="date" style="padding-left: 55px;padding-top: 52px">
                    <p class="word-normal-np">&nbsp&nbsp&nbsp（盖章）
                    <%--<div class="cachet7" style="background: none"></div>--%>
                    </p>
                    <span style="display: none">
                </span>
                    <p class="underline" style="width: 40px;height: 19px">
                    </p>
                    <p class="word-normal-np">年</p>
                    <p class="underline" style="width: 24px;height: 19px">
                    </p>
                    <p class="word-normal-np">月</p>
                    <p class="underline" style="width: 24px;height: 19px">
                    </p>
                    <p class="word-normal-np">日</p>
                </div>
            </div>
            <div class="body-fourth-right">
                <p class="word-bold"><textarea type="text" rows="2" cols="32"
                                               style="line-height: 26px;font-weight: bold;font-size: 14px;padding: 0 8px;"
                                               id="hotwater1name" name="hotwater1name">${students1.lxqd.hotwater1name}</textarea></p>
                <p class="word-bold"><textarea type="text" rows="1" cols="32"
                                               style="line-height: 28px;font-weight: bold;font-size: 14px;padding: 0 8px;"
                                               id="hotwater1desc" name="hotwater1desc">${students1.lxqd.hotwater1desc}</textarea></p>
                <p class="word-normal word" style="padding-left: 100px;padding-top: 14px">经办人签名：</p>
                <p class="underline" style="width: 64px;position: absolute">
                <p class="font" style="position: relative;bottom: -5px"></p></p>
                <div class="date" style="padding-left: 55px;padding-top: 63px">
                    <p class="word-normal-np">&nbsp&nbsp&nbsp（盖章）
                    <%--<div class="cachet7" style="background: none"></div>--%>
                    </p>
                    <span style="display: none">
                </span>
                    <p class="underline" style="width: 40px;height: 19px">
                    </p>
                    <p class="word-normal-np">年</p>
                    <p class="underline" style="width: 24px;height: 19px">
                    </p>
                    <p class="word-normal-np">月</p>
                    <p class="underline" style="width: 24px;height: 19px">
                    </p>
                    <p class="word-normal-np">日</p>
                </div>
            </div>
            <div class="body-fifth-left">
                <p class="word-bold">信息中心：</p>
                <p class="word-bold"><textarea type="text" rows="2" cols="32"
                                               style="line-height: 28px;font-weight: bold;font-size: 14px;padding: 0 8px;"
                                               id="educandcompdesc" name="educandcompdesc">${students1.lxqd.educandcompdesc}</textarea></p>
                <p class="word-normal word px" style="padding-top: 33px">（经办人签名）：</p>
                <p class="underline" style="width: 64px;position: absolute">
                <p class="font" style="position: relative;bottom: -20px"></p></p>
                <div class="date" style="padding-left: 55px;padding-top: 44px">
                    <p class="word-normal-np">&nbsp&nbsp&nbsp（盖章）
                    <div class="cachet8" style="background: none"></div>
                    </p>
                    <span style="display: none">
                </span>
                    <p class="underline" style="width: 40px;height: 19px">
                    </p>
                    <p class="word-normal-np">年</p>
                    <p class="underline" style="width: 24px;height: 19px">
                    </p>
                    <p class="word-normal-np">月</p>
                    <p class="underline" style="width: 24px;height: 19px">
                    </p>
                    <p class="word-normal-np">日</p>
                </div>
            </div>
            <div class="body-fifth-right">
                <p class="word-bold"><textarea type="text" rows="1" cols="32"
                                               style="line-height: 28px;font-weight: bold;font-size: 14px;padding: 0 8px;"
                                               id="hotwater2name" name="hotwater2name"
                                               >${students1.lxqd.hotwater2name}</textarea></p>
                <p class="word-bold"><textarea type="text" rows="1" cols="32"
                                               style="line-height: 28px;font-weight: bold;font-size: 14px;padding: 0 8px;"
                                               id="hotwater2desc" name="hotwater2desc"
                                               >${students1.lxqd.hotwater2desc}</textarea></p>
                <p class="word-normal word px" style="padding-left: 100px;padding-top: 33px;">经办人签名：</p>
                <p class="underline" style="width: 64px;position: absolute">
                <p class="font" style="position: relative;bottom: -20px"></p>
                <div class="date" style="padding-left: 55px;padding-top: 42px">
                    <p class="word-normal-np">&nbsp&nbsp&nbsp（盖章）
                    <div class="cachet8" style="background: none"></div>
                    </p>
                    <span style="display: none">
                </span>
                    <p class="underline" style="width: 40px;height: 19px">
                    </p>
                    <p class="word-normal-np">年</p>
                    <p class="underline" style="width: 24px;height: 19px">
                    </p>
                    <p class="word-normal-np">月</p>
                    <p class="underline" style="width: 24px;height: 19px">
                    </p>
                    <p class="word-normal-np">日</p>
                </div>
            </div>
            <div class="body-sixth-left">
                <p class="word-bold">二级学院：</p>
                <p class="word-bold"><textarea type="text" rows="2" cols="32"
                                               style="line-height: 28px;font-weight: bold;font-size: 14px;padding: 0 8px;"
                                               id="acaddesc" name="acaddesc">${students1.lxqd.acaddesc}</textarea></p>
                <p class="word-normal word px" style="padding-left: 85px;padding-top: 13px">（经办人签名）：</p>
                <p class="underline" style="width: 64px;position: absolute">
                <p class="font" style="position: relative;bottom: 0px"></p></p>
                <div class="date" style="padding-left: 55px;padding-top: 30px">
                    <p class="word-normal-np">&nbsp&nbsp&nbsp（盖章）
                    <%--<div class="cachet8" style="background: none"></div>--%>
                    </p>
                    <span style="display: none">
                </span>
                    <p class="underline" style="width: 40px;height: 19px">
                    </p>
                    <p class="word-normal-np">年</p>
                    <p class="underline" style="width: 24px;height: 19px">
                    </p>
                    <p class="word-normal-np">月</p>
                    <p class="underline" style="width: 24px;height: 19px">
                    </p>
                    <p class="word-normal-np">日</p>
                </div>
            </div>
            <div class="body-sixth-right">
                <p class="word-bold"> &nbsp</p>
                <p class="word-bold"><textarea type="text" rows="2" cols="32"
                                               style="line-height: 28px;font-weight: bold;font-size: 14px;padding: 0 8px;"
                                               id="classteacdesc" name="classteacdesc">${students1.lxqd.classteacdesc}</textarea></p>
                <p class="word-normal word px" style="padding-left: 100px;padding-top: 11px">班主任签名：</p>
                <p class="underline" style="width: 64px;position: absolute">
                <p class="font" style="position: relative;bottom: 0px"></p></p>
                <div class="date1" style="padding-left: 140px;padding-top: 30px">
                <span style="display: none">
                </span>
                    <p class="underline" style="width: 40px;height: 19px">
                    </p>
                    <p class="word-normal-np">年</p>
                    <p class="underline" style="width: 24px;height: 19px">
                    </p>
                    <p class="word-normal-np">月</p>
                    <p class="underline" style="width: 24px;height: 19px">
                    </p>
                    <p class="word-normal-np">日</p>
                </div>
            </div>
        </div>
        <div class="buttom">
        <textarea type="text" rows="2" cols="140"
                  style="line-height: 20px; font-size: 15px; padding: 0px; margin: 0px; width: 1123px; height: 42px;"
                  id="remarkdesc" name="remarkdesc">${students1.lxqd.remarkdesc}</textarea>
        </div>
        <div style="text-align: center;padding: 28px 0;">
            <button id="lookflag" type="submit" name="lookflag" value="提交修改">
                提交修改
            </button>
        </div>
    </form>
</div>
</body>
<script>
    function load() {
        var font = document.getElementsByClassName('font');
        for (var i = 0; i < font.length; i++) {
            if (font[i].innerHTML == '') {
                font[i].classList.add('fontWhite');
            } else {
                font[i].classList.add('fontBlue');
            }
        }
    }

    window.onload = load;
</script>
<script>
    $("#updateLxqdForm").submit(function () {
        alert("提交成功！")
    })
</script>
</html>
